package kesshou.android.team.util.network.client;

import android.content.Context;

import java.io.File;
import java.io.IOException;

import io.realm.Realm;
import kesshou.android.team.models.Setting;
import kesshou.android.team.util.network.NetworkingClient;
import okhttp3.Authenticator;
import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.Route;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * Created by yoyoIU on 2016/9/19.
 */
public class OkHttpUtil {

	private static File mContext;
	private static final int HTTP_RESPONSE_DISK_CACHE_MAX_SIZE = 10 * 1024 * 1024;
	private static Interceptor mTokenInterceptor;
	private static Authenticator mAuthenticator;

	private static class OkHttpUtilHolder{
		static Cache cache = new Cache(mContext, HTTP_RESPONSE_DISK_CACHE_MAX_SIZE);

		public static OkHttpClient instance = new OkHttpClient.Builder()
			.addInterceptor(getHttpLoggingInterceptor())
			//.retryOnConnectionFailure(true)
			.authenticator(mAuthenticator)
			.addNetworkInterceptor(mTokenInterceptor)
			.cache(cache)
			.build();
	}

	private OkHttpUtil() {

	}

	public static OkHttpClient getInstance(final Context context){
		if (context != null) {
			mContext = context.getCacheDir();
		}

		mTokenInterceptor = new Interceptor() {
			@Override public Response intercept(Chain chain) throws IOException {
				Request originalRequest = chain.request();
				Realm realm = Realm.getDefaultInstance();
				Setting setting = realm.where(Setting.class).findFirst();
				Request authorised = originalRequest.newBuilder().build();
				if(setting!=null) {
					authorised = originalRequest.newBuilder()
						.header("Authorization", setting.token)
						.build();
				}
				realm.close();
				return chain.proceed(authorised);
			}
		};

		mAuthenticator = new Authenticator() {
			@Override public Request authenticate(Route route, Response response)
				throws IOException {
				new NetworkingClient(context).login();
				Realm realm = Realm.getDefaultInstance();
				Setting setting = realm.where(Setting.class).findFirst();
				Request request = response.request().newBuilder()
					.addHeader("Authorization", setting.token)
					.build();
				realm.close();
				return request;
			}
		};
		return OkHttpUtilHolder.instance;
	}

	private static HttpLoggingInterceptor getHttpLoggingInterceptor() {
		HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
		loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
		return loggingInterceptor;
	}


}
