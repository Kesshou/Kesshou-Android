package kesshou.android.team.views.Infor;


import android.content.Context;
import android.support.v4.util.ArrayMap;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

import io.realm.Realm;
import kesshou.android.team.R;
import kesshou.android.team.models.NetWorkCache;
import kesshou.android.team.util.network.MyCallBack;
import kesshou.android.team.util.network.NetworkingClient;
import kesshou.android.team.util.network.api.holder.AbsentstateResponse;
import kesshou.android.team.util.network.api.holder.Error;
import retrofit2.Response;

/**
 * A simple class.
 */
public class RecordFragment {


	public RecordFragment() {
		// Required empty public constructor
	}


	public View onCreateView(final Context context) {
		// Inflate the layout for this fragment
		final View view = LayoutInflater.from(context).inflate(R.layout.fragment_record, null);

		final Realm realm = Realm.getDefaultInstance();

		final NetWorkCache netWorkCache = realm.where(NetWorkCache.class).findFirst();
		NetworkingClient networkingClient = new NetworkingClient(context.getApplicationContext());
		final Gson gson = new Gson();

		if(netWorkCache.record==null){
			networkingClient.getABS(new MyCallBack<List<AbsentstateResponse>>(context) {
				@Override
				public void onSuccess(Response<List<AbsentstateResponse>> response) {
					realm.beginTransaction();
					netWorkCache.record=gson.toJson(response.body());
					realm.commitTransaction();

					drawTop(view,response.body());
					drawBody(context,view,response.body());
				}

				@Override
				public void onErr(Error error) {

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
					realm.beginTransaction();
					netWorkCache.record=gson.toJson(response.body());
					realm.commitTransaction();

					drawTop(view,response.body());
					drawBody(context,view,response.body());
				}

				@Override
				public void onErr(Error error) {

				}
			});
		}


		realm.close();
		return view;
	}

	private void drawTop(View view,List<AbsentstateResponse> absentstate){
		ArrayMap<String,Integer> date = parseData(absentstate);
		int public_leave = (date.get("公")!=null?date.get("公"):0);
		int sick_leave = (date.get("病")!=null?date.get("病"):0);
		int thing_leave = (date.get("事")!=null?date.get("事"):0);
		int dead_leave = (date.get("喪")!=null?date.get("喪"):0);
		int absence = (date.get("缺")!=null?date.get("缺"):0);
		int late = (date.get("遲")!=null?date.get("遲"):0);
		int cutting = (date.get("曠")!=null?date.get("曠"):0);

		TextView textView = (TextView) view.findViewById(R.id.public_leave);
		textView.setText(String.valueOf(public_leave));

		textView = (TextView) view.findViewById(R.id.sick_leave);
		textView.setText(String.valueOf(sick_leave));

		textView = (TextView) view.findViewById(R.id.thing_leave);
		textView.setText(String.valueOf(thing_leave));

		textView = (TextView) view.findViewById(R.id.dead_leave);
		textView.setText(String.valueOf(dead_leave));

		textView = (TextView) view.findViewById(R.id.absence);
		textView.setText(String.valueOf(absence));

		textView = (TextView) view.findViewById(R.id.late);
		textView.setText(String.valueOf(late));

		textView = (TextView) view.findViewById(R.id.cutting);
		textView.setText(String.valueOf(cutting));
	}

	private void drawBody(Context context,View view,List<AbsentstateResponse> absentstate){

		LinearLayout layout = (LinearLayout) view.findViewById(R.id.body);

		layout.removeAllViews();

		for(int i=0;i<absentstate.size();i++) {
			View item = LayoutInflater.from(context).inflate(R.layout.record_list_item, null);
			TextView txtDate = (TextView) item.findViewById(R.id.date);
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd", Locale.getDefault());
			txtDate.setText(simpleDateFormat.format(absentstate.get(i).date));

			TextView txtNum = (TextView) item.findViewById(R.id.num);
			txtNum.setText(absentstate.get(i).classX);

			TextView txtText = (TextView) item.findViewById(R.id.text);
			txtText.setText(convertAll(absentstate.get(i).type));

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
