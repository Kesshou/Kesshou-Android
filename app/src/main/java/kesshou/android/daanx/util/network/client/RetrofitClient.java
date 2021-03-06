package kesshou.android.daanx.util.network.client;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import kesshou.android.daanx.util.network.Config;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by yoyoIU on 2016/9/19.
 */
public class RetrofitClient {

	private static Context mContext;
	private static Gson gson;

	private static class RetrofitClientHolder{
		static Retrofit instance = new Retrofit.Builder()
			.baseUrl(Config.getAPIPath())
			.client(OkHttpUtil.getInstance(mContext))
			.addConverterFactory(GsonConverterFactory.create(gson))
			.build();
	}

	private RetrofitClient() {

	}

	public static Retrofit getInstance(Context context){
		if (context != null) {
			mContext = context.getApplicationContext();
		}

		gson = new GsonBuilder()
			.setDateFormat("yyyy/MM/dd")
			.serializeNulls()
			.create();
		return RetrofitClientHolder.instance;
	}

}
