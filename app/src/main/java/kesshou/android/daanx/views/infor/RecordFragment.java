package kesshou.android.daanx.views.infor;


import android.content.Context;
import android.support.v4.util.ArrayMap;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import io.realm.Realm;
import kesshou.android.daanx.R;
import kesshou.android.daanx.models.NetWorkCache;
import kesshou.android.daanx.util.BeautifulColor;
import kesshou.android.daanx.util.network.MyCallBack;
import kesshou.android.daanx.util.network.NetworkingClient;
import kesshou.android.daanx.util.network.api.holder.AbsentstateResponse;
import kesshou.android.daanx.util.network.api.holder.Error;
import retrofit2.Call;
import retrofit2.Response;

import static kesshou.android.daanx.R.id.date;

/**
 * A simple class.
 */
public class RecordFragment {


	public RecordFragment() {
		// Required empty public constructor
	}

	private int public_leave_color;
	private int sick_leave_color;
	private int thing_leave_color;
	private int dead_leave_color;
	private int absence_color;
	private int late_color;
	private int cutting_color;


	public View onCreateView(final Context context) {
		// Inflate the layout for this fragment
		context.setTheme(R.style.AppTheme);
		context.getApplicationContext().setTheme(R.style.AppTheme);
		final View view = LayoutInflater.from(context).inflate(R.layout.fragment_record, null);

		Realm realm = Realm.getDefaultInstance();

		NetWorkCache netWorkCache = realm.where(NetWorkCache.class).findFirst();
		NetworkingClient networkingClient = new NetworkingClient(context.getApplicationContext());
		final Gson gson = new Gson();

		if(netWorkCache.record==null){
			networkingClient.getABS(new MyCallBack<List<AbsentstateResponse>>(context) {
				@Override
				public void onSuccess(Response<List<AbsentstateResponse>> response) {
					Realm realm = Realm.getDefaultInstance();
					NetWorkCache netWorkCache = realm.where(NetWorkCache.class).findFirst();
					realm.beginTransaction();
					netWorkCache.record=gson.toJson(response.body());
					realm.commitTransaction();
					realm.close();

					drawTop(view,response.body());
					drawBody(context,view,response.body());
				}

				@Override
				public void onErr(Error error, Call<List<AbsentstateResponse>> call) {

				}
			});
		}else{
			Type listType = new TypeToken<List<AbsentstateResponse>>() {}.getType();
			List<AbsentstateResponse> absentstates = gson.fromJson(netWorkCache.record,listType);
			drawTop(view,absentstates);
			drawBody(context,view,absentstates);

			networkingClient.getABS(new MyCallBack<List<AbsentstateResponse>>(context) {
				@Override
				public void onSuccess(Response<List<AbsentstateResponse>> response) {
					Realm realm = Realm.getDefaultInstance();
					NetWorkCache netWorkCache = realm.where(NetWorkCache.class).findFirst();
					realm.beginTransaction();
					netWorkCache.record=gson.toJson(response.body());
					realm.commitTransaction();
					realm.close();

					drawTop(view,response.body());
					drawBody(context,view,response.body());
				}

				@Override
				public void onErr(Error error, Call<List<AbsentstateResponse>> call) {

				}
			});
		}


		realm.close();
		return view;
	}

	private void drawTop(View view,List<AbsentstateResponse> absentstate){
		ArrayMap<String,Integer> date = parseData(absentstate);
		int public_leave = (date.get("公")!=null?date.get("公"):0);
		public_leave_color = BeautifulColor.getColor(0);
		int sick_leave = (date.get("病")!=null?date.get("病"):0);
		sick_leave_color = BeautifulColor.getColor(1);
		int thing_leave = (date.get("事")!=null?date.get("事"):0);
		thing_leave_color = BeautifulColor.getColor(2);
		int dead_leave = (date.get("喪")!=null?date.get("喪"):0);
		dead_leave_color = BeautifulColor.getColor(3);
		int absence = (date.get("缺")!=null?date.get("缺"):0);
		absence_color = BeautifulColor.getColor(4);
		int late = (date.get("遲")!=null?date.get("遲"):0);
		late_color = BeautifulColor.getColor(5);
		int cutting = (date.get("曠")!=null?date.get("曠"):0);
		cutting_color = BeautifulColor.getColor(6);

		TextView textView = (TextView) view.findViewById(R.id.public_leave);
		textView.setText(String.valueOf(public_leave));
		textView.setTextColor(public_leave_color);

		textView = (TextView) view.findViewById(R.id.sick_leave);
		textView.setText(String.valueOf(sick_leave));
		textView.setTextColor(sick_leave_color);

		textView = (TextView) view.findViewById(R.id.thing_leave);
		textView.setText(String.valueOf(thing_leave));
		textView.setTextColor(thing_leave_color);

		textView = (TextView) view.findViewById(R.id.dead_leave);
		textView.setText(String.valueOf(dead_leave));
		textView.setTextColor(dead_leave_color);

		textView = (TextView) view.findViewById(R.id.absence);
		textView.setText(String.valueOf(absence));
		textView.setTextColor(absence_color);

		textView = (TextView) view.findViewById(R.id.late);
		textView.setText(String.valueOf(late));
		textView.setTextColor(late_color);

		textView = (TextView) view.findViewById(R.id.cutting);
		textView.setText(String.valueOf(cutting));
		textView.setTextColor(cutting_color);
	}

	private void drawBody(Context context,View view,List<AbsentstateResponse> absentstate){

		LinearLayout layout = (LinearLayout) view.findViewById(R.id.body);

		layout.removeAllViews();

		for(int i=0;i<absentstate.size();i++) {
			View item = LayoutInflater.from(context).inflate(R.layout.record_list_item, null);
			TextView txtDate = (TextView) item.findViewById(date);
			txtDate.setText(absentstate.get(i).date);

			TextView txtNum = (TextView) item.findViewById(R.id.num);
			txtNum.setText(absentstate.get(i).classX);

			TextView txtText = (TextView) item.findViewById(R.id.text);
			txtText.setText(convertAll(absentstate.get(i).type));
			txtText.setTextColor(convertColor(absentstate.get(i).type));

			layout.addView(item);
		}
	}

	private String convertAll(String text){
		switch (text){
			case "公":
				return "公假";
			case "事":
				return "事假";
			case "病":
				return "病假";
			case "喪":
				return "喪假";
			case "遲":
				return "遲到";
			case "曠":
				return "曠課";
			case "缺":
				return "缺席";
			default:
				return "";
		}
	}

	private int convertColor(String text){
		switch (text){
			case "公":
				return public_leave_color;
			case "事":
				return thing_leave_color;
			case "病":
				return sick_leave_color;
			case "喪":
				return dead_leave_color;
			case "遲":
				return late_color;
			case "曠":
				return cutting_color;
			case "缺":
				return absence_color;
			default:
				return 0;
		}
	}

	private ArrayMap<String,Integer> parseData(List<AbsentstateResponse> absentstate){
		ArrayMap<String,Integer> types = new ArrayMap<>();
		for(int i=0;i<absentstate.size();i++){
			if(!types.containsKey(absentstate.get(i).type)){
				types.put(absentstate.get(i).type,1);
			}else{
				types.put(absentstate.get(i).type,types.get(absentstate.get(i).type)+1);
			}
		}
		return types;
	}

}
