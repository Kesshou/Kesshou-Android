package kesshou.android.team.views.Infor;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.List;

import io.realm.Realm;
import kesshou.android.team.R;
import kesshou.android.team.models.NetWorkCache;
import kesshou.android.team.util.network.MyCallBack;
import kesshou.android.team.util.network.NetworkingClient;
import kesshou.android.team.util.network.api.holder.AttitudeStatusResponse;
import kesshou.android.team.util.network.api.holder.Error;
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
		final View view = LayoutInflater.from(context).inflate(R.layout.fragment_prize, null);

		final Realm realm = Realm.getDefaultInstance();

		final NetWorkCache netWorkCache = realm.where(NetWorkCache.class).findFirst();

		final Gson gson = new Gson();

		NetworkingClient networkingClient = new NetworkingClient(context.getApplicationContext());

		if(netWorkCache.prize==null){
			networkingClient.getATS(new MyCallBack<AttitudeStatusResponse>(context) {
				@Override
				public void onSuccess(Response<AttitudeStatusResponse> response) {
					realm.beginTransaction();
					netWorkCache.prize=gson.toJson(response.body());
					realm.commitTransaction();

					drawTop(view,response.body().count);
					drawBody(context,view,response.body().status);
				}

				@Override
				public void onErr(Error error) {

				}
			});
		}else{
			networkingClient.getATS(new MyCallBack<AttitudeStatusResponse>(context) {
				@Override
				public void onSuccess(Response<AttitudeStatusResponse> response) {
					realm.beginTransaction();
					netWorkCache.prize=gson.toJson(response.body());
					realm.commitTransaction();

					drawTop(view,response.body().count);
					drawBody(context,view,response.body().status);
				}

				@Override
				public void onErr(Error error) {

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
