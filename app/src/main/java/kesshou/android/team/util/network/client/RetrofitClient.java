package kesshou.android.team.util.network.client;

import android.content.Context;

import kesshou.android.team.util.network.Config;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by yoyoIU on 2016/9/19.
 */
public class RetrofitClient {

	private static Context mContext;

	private static class RetrofitClientHolder{
		public static Retrofit instance = new Retrofit.Builder()
			.baseUrl(Config.getAPIPath())
			.client(OkHttpUtil.getInstance(mContext))
			.addConverterFactory(GsonConverterFactory.create())
			.build();
	}

	private RetrofitClient() {

	}

	public static Retrofit getInstance(Context context){
		if (context != null) {
			mContext = context;
		}
		return RetrofitClientHolder.instance;
	}

}
