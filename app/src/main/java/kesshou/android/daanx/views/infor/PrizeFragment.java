package kesshou.android.daanx.views.infor;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.List;

import io.realm.Realm;
import kesshou.android.daanx.R;
import kesshou.android.daanx.models.NetWorkCache;
import kesshou.android.daanx.util.network.MyCallBack;
import kesshou.android.daanx.util.network.NetworkingClient;
import kesshou.android.daanx.util.network.api.holder.AttitudeStatusResponse;
import kesshou.android.daanx.util.network.api.holder.Error;
import retrofit2.Call;
import retrofit2.Response;

/**
 * A simple class.
 */
public class PrizeFragment {


	public PrizeFragment() {
		// Required empty public constructor
	}


	public View onCreateView(final Context context) {
		// Inflate the layout for this fragment
		context.setTheme(R.style.AppTheme);
		context.getApplicationContext().setTheme(R.style.AppTheme);
		final View view = LayoutInflater.from(context).inflate(R.layout.fragment_prize, null);

		Realm realm = Realm.getDefaultInstance();

		NetWorkCache netWorkCache = realm.where(NetWorkCache.class).findFirst();

		final Gson gson = new Gson();

		NetworkingClient networkingClient = new NetworkingClient(context.getApplicationContext());

		if(netWorkCache.prize==null){
			networkingClient.getATS(new MyCallBack<AttitudeStatusResponse>(context) {
				@Override
				public void onSuccess(Response<AttitudeStatusResponse> response) {
					Realm realm = Realm.getDefaultInstance();
					NetWorkCache netWorkCache = realm.where(NetWorkCache.class).findFirst();
					realm.beginTransaction();
					netWorkCache.prize=gson.toJson(response.body());
					realm.commitTransaction();
					realm.close();

					drawTop(view,response.body().count);
					drawBody(context,view,response.body().status);
				}

				@Override
				public void onErr(Error error, Call<AttitudeStatusResponse> call) {

				}
			});
		}else{
			//Type listType = new TypeToken<AttitudeStatusResponse>() {}.getType();
			AttitudeStatusResponse attitudeStatus = gson.fromJson(netWorkCache.prize,AttitudeStatusResponse.class);
			drawTop(view,attitudeStatus.count);
			drawBody(context,view,attitudeStatus.status);


			networkingClient.getATS(new MyCallBack<AttitudeStatusResponse>(context) {
				@Override
				public void onSuccess(Response<AttitudeStatusResponse> response) {
					Realm realm = Realm.getDefaultInstance();
					NetWorkCache netWorkCache = realm.where(NetWorkCache.class).findFirst();
					realm.beginTransaction();
					netWorkCache.prize=gson.toJson(response.body());
					realm.commitTransaction();
					realm.close();

					drawTop(view,response.body().count);
					drawBody(context,view,response.body().status);
				}

				@Override
				public void onErr(Error error, Call<AttitudeStatusResponse> call) {

				}
			});
		}

		realm.close();
		return view;
	}

	private void drawTop(View view, AttitudeStatusResponse.CountBean countBean){
		TextView textView = (TextView) view.findViewById(R.id.smallcite);
		textView.setText(String.valueOf(countBean.smallcite));

		textView = (TextView) view.findViewById(R.id.middlecite);
		textView.setText(String.valueOf(countBean.middlecite));

		textView = (TextView) view.findViewById(R.id.bigcite);
		textView.setText(String.valueOf(countBean.bigcite));

		textView = (TextView) view.findViewById(R.id.smallfault);
		textView.setText(String.valueOf(countBean.smallfault));

		textView = (TextView) view.findViewById(R.id.middlefault);
		textView.setText(String.valueOf(countBean.middlefault));

		textView = (TextView) view.findViewById(R.id.bigfault);
		textView.setText(String.valueOf(countBean.bigfault));
	}

	private void drawBody(Context context, View view, List<AttitudeStatusResponse.Attitude> attitudes){

		LinearLayout layout = (LinearLayout) view.findViewById(R.id.body);

		layout.removeAllViews();

		for(int i=0;i<attitudes.size();i++){
			View item = LayoutInflater.from(context).inflate(R.layout.prize_list_item,null);

			TextView txtDate = (TextView) item.findViewById(R.id.date);
			txtDate.setText(attitudes.get(i).date);

			TextView txtNum = (TextView) item.findViewById(R.id.num);
			txtNum.setText(attitudes.get(i).item);

			TextView txtText = (TextView) item.findViewById(R.id.text);
			txtText.setText(attitudes.get(i).text);

			layout.addView(item);
		}

	}

}
