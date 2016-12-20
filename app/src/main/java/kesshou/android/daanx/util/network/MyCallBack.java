package kesshou.android.daanx.util.network;

import android.content.Context;
import android.util.Log;
import android.view.Gravity;
import android.widget.Toast;

import kesshou.android.daanx.util.component.ToastUtils;
import kesshou.android.daanx.util.network.api.holder.Error;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by yoyoIU on 2016/11/11.
 */

public abstract class MyCallBack<t> implements Callback<t> {

	private Context context;

	protected MyCallBack(Context mContext){
		context=mContext.getApplicationContext();
	}


	private void onError(Call<t> call,Response<t> response){
		Error error = ErrorUtils.parseError(response,context);
		ErrorUtils.logError(error);

		switch (error.code){
			case 103:
				break;
			case 500:
				break;
			case 501:
				break;
			case 100:
				break;
			case 102:
				break;
			default:
				ToastUtils.makeTextAndShow(context,error.message,Toast.LENGTH_LONG,Gravity.CENTER);
				break;
		}

		onErr(error,call);

	}



	@Override
	public void onResponse(Call<t> call, Response<t> response) {
		if(response.isSuccessful()){
			Log.d("OkHttp",response.raw().toString());
			onSuccess(response);
		}else{
			onError(call,response);
		}
	}

	@Override
	public void onFailure(Call<t> call, Throwable t) {
		Log.e("network error",t.getMessage());
		ToastUtils.makeTextAndShow(context,"網路錯誤或無網路",Toast.LENGTH_SHORT, Gravity.CENTER);
	}

	public abstract void onSuccess(Response<t> response);

	public abstract void onErr(Error error, Call<t> call);
}



