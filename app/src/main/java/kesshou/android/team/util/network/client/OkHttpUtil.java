package kesshou.android.team.util.network.client;

import android.content.Context;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * Created by yoyoIU on 2016/9/19.
 */
public class OkHttpUtil {

	private static Context mContext;
	private static final int HTTP_RESPONSE_DISK_CACHE_MAX_SIZE = 10 * 1024 * 1024;

	private static class OkHttpUtilHolder{
		static Cache cache = new Cache(mContext.getCacheDir(), HTTP_RESPONSE_DISK_CACHE_MAX_SIZE);

		public static OkHttpClient instance = new OkHttpClient.Builder()
			.addInterceptor(getHttpLoggingInterceptor())
			.retryOnConnectionFailure(true)
			.cache(cache)
			.build();
	}

	private OkHttpUtil() {

	}

	public static OkHttpClient getInstance(Context context){
		if (context != null) {
			mContext = context;
		}
		return OkHttpUtilHolder.instance;
	}

	public static HttpLoggingInterceptor getHttpLoggingInterceptor() {
		HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
		loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
		return loggingInterceptor;
	}


}
