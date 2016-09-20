package kesshou.android.team.util.network;

import android.content.Context;
import android.util.Log;

import java.io.IOException;
import java.lang.annotation.Annotation;

import kesshou.android.team.util.network.api.holder.Error;
import kesshou.android.team.util.network.client.RetrofitClient;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Response;

/**
 * Created by yoyoIU on 2016/9/20.
 */
public class ErrorUtils {

	private static final String TAG = "NetworkingClient";

	public static Error parseError(Response<?> response, Context context) {
		Converter<ResponseBody, Error> converter =
			RetrofitClient.getInstance(context)
				.responseBodyConverter(Error.class, new Annotation[0]);

		Error error;

		try {
			error = converter.convert(response.errorBody());
		} catch (IOException e) {
			return new Error();
		}

		return error;
	}

	public static void logError(Error error){
		Log.e(TAG,error.toString());
	}
}
