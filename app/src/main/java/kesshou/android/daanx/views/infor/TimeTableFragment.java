package kesshou.android.daanx.views.infor;


import android.content.Context;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import kesshou.android.daanx.R;
import kesshou.android.daanx.models.NetWorkCache;
import kesshou.android.daanx.util.network.MyCallBack;
import kesshou.android.daanx.util.network.NetworkingClient;
import kesshou.android.daanx.util.network.api.holder.Error;
import kesshou.android.daanx.util.network.api.holder.TimeTableResponse;
import retrofit2.Call;
import retrofit2.Response;

/**
 * A simple class.
 */
public class TimeTableFragment{


	public TimeTableFragment() {
		// Required empty public constructor
	}


	public View onCreateView(final Context context) {
		// Inflate the layout for this fragment
		context.setTheme(R.style.AppTheme);
		context.getApplicationContext().setTheme(R.style.AppTheme);
		final View view = LayoutInflater.from(context).inflate(R.layout.fragment_time_table, null);

		Realm realm = Realm.getDefaultInstance();

		NetWorkCache netWorkCache =realm.where(NetWorkCache.class).findFirst();


		Log.w("TAG",netWorkCache.toString());

		NetworkingClient networkingClient = new NetworkingClient(context);

		final Gson gson = new Gson();

		if(netWorkCache.timeTable==null){
			networkingClient.queryTimeTable(new MyCallBack<TimeTableResponse>(context) {
				@Override
				public void onSuccess(final Response<TimeTableResponse> response) {
					Realm realm = Realm.getDefaultInstance();
					NetWorkCache netWorkCache = realm.where(NetWorkCache.class).findFirst();
					realm.beginTransaction();
					netWorkCache.timeTable = gson.toJson(response.body(),TimeTableResponse.class);
					realm.commitTransaction();
					realm.close();
					TimeTableResponse timeTableResponse = response.body();
					ArrayList<List<TimeTableResponse.Week>> list = new ArrayList<>();
					list.add(timeTableResponse.week1);
					list.add(timeTableResponse.week2);
					list.add(timeTableResponse.week3);
					list.add(timeTableResponse.week4);
					list.add(timeTableResponse.week5);
					drawTable(view,context,list);
				}

				@Override
				public void onErr(Error error, Call<TimeTableResponse> call) {

				}
			});
		}else{
			TimeTableResponse timeTableResponse = gson.fromJson(netWorkCache.timeTable,TimeTableResponse.class);
			ArrayList<List<TimeTableResponse.Week>> list = new ArrayList<>();
			list.add(timeTableResponse.week1);
			list.add(timeTableResponse.week2);
			list.add(timeTableResponse.week3);
			list.add(timeTableResponse.week4);
			list.add(timeTableResponse.week5);
			drawTable(view,context,list);

			networkingClient.queryTimeTable(new MyCallBack<TimeTableResponse>(context) {
				@Override
				public void onSuccess(final Response<TimeTableResponse> response) {
					Realm realm = Realm.getDefaultInstance();
					NetWorkCache netWorkCache = realm.where(NetWorkCache.class).findFirst();
					realm.beginTransaction();
					netWorkCache.timeTable = gson.toJson(response.body(),TimeTableResponse.class);
					realm.commitTransaction();
					realm.close();
					TimeTableResponse timeTableResponse = response.body();
					ArrayList<List<TimeTableResponse.Week>> list = new ArrayList<>();
					list.add(timeTableResponse.week1);
					list.add(timeTableResponse.week2);
					list.add(timeTableResponse.week3);
					list.add(timeTableResponse.week4);
					list.add(timeTableResponse.week5);
					drawTable(view,context,list);
				}

				@Override
				public void onErr(Error error, Call<TimeTableResponse> call) {

				}
			});
		}

		realm.close();

		return view;
	}

	private void drawTable(View view,Context context,ArrayList<List<TimeTableResponse.Week>> timeTable){
		LinearLayout tableRow1 = (LinearLayout) view.findViewById(R.id.TableRow1);
		LinearLayout tableRow2 = (LinearLayout) view.findViewById(R.id.TableRow2);
		LinearLayout tableRow3 = (LinearLayout) view.findViewById(R.id.TableRow3);
		LinearLayout tableRow4 = (LinearLayout) view.findViewById(R.id.TableRow4);
		LinearLayout tableRow5 = (LinearLayout) view.findViewById(R.id.TableRow5);
		LinearLayout tableRow6 = (LinearLayout) view.findViewById(R.id.TableRow6);

		tableRow1.removeAllViews();
		tableRow2.removeAllViews();
		tableRow3.removeAllViews();
		tableRow4.removeAllViews();
		tableRow5.removeAllViews();
		tableRow6.removeAllViews();

		int max_value = 0;
		int max_item = 0;
		for(int i=0;i<timeTable.size();i++){
			if(max_value<timeTable.get(i).size()){
				max_value = timeTable.get(i).size();
				max_item = i;
			}
		}

		tableRow1.addView(createTextView(context,"",R.color.grey_300));
		for(int i=0;i<max_value;i++) {
			if(i%2==0)
				tableRow1.addView(createTextView(context,timeTable.get(max_item).get(i).start+"\n"+timeTable.get(max_item).get(i).end));
			else
				tableRow1.addView(createTextView(context,timeTable.get(max_item).get(i).start+"\n"+timeTable.get(max_item).get(i).end,R.color.grey_300));
		}

		tableRow2.addView(createTextView(context,"星期一",R.color.grey_300));
		tableRow3.addView(createTextView(context,"星期二",R.color.grey_300));
		tableRow4.addView(createTextView(context,"星期三",R.color.grey_300));
		tableRow5.addView(createTextView(context,"星期四",R.color.grey_300));
		tableRow6.addView(createTextView(context,"星期五",R.color.grey_300));
		for(int i=0;i<max_value;i++){
			if(i%2==1){
				if(i<timeTable.get(0).size()) tableRow2.addView(createTextView(context,timeTable.get(0).get(i).subject,R.color.grey_300));
				else tableRow2.addView(createTextView(context,""));
				if(i<timeTable.get(1).size()) tableRow3.addView(createTextView(context,timeTable.get(1).get(i).subject,R.color.grey_300));
				else tableRow3.addView(createTextView(context,""));
				if(i<timeTable.get(2).size()) tableRow4.addView(createTextView(context,timeTable.get(2).get(i).subject,R.color.grey_300));
				else tableRow4.addView(createTextView(context,""));
				if(i<timeTable.get(3).size()) tableRow5.addView(createTextView(context,timeTable.get(3).get(i).subject,R.color.grey_300));
				else tableRow5.addView(createTextView(context,""));
				if(i<timeTable.get(4).size()) tableRow6.addView(createTextView(context,timeTable.get(4).get(i).subject,R.color.grey_300));
				else tableRow6.addView(createTextView(context,""));
			}else{
				if(i<timeTable.get(0).size()) tableRow2.addView(createTextView(context,timeTable.get(0).get(i).subject));
				else tableRow2.addView(createTextView(context,""));
				if(i<timeTable.get(1).size()) tableRow3.addView(createTextView(context,timeTable.get(1).get(i).subject));
				else tableRow3.addView(createTextView(context,""));
				if(i<timeTable.get(2).size()) tableRow4.addView(createTextView(context,timeTable.get(2).get(i).subject));
				else tableRow4.addView(createTextView(context,""));
				if(i<timeTable.get(3).size()) tableRow5.addView(createTextView(context,timeTable.get(3).get(i).subject));
				else tableRow5.addView(createTextView(context,""));
				if(i<timeTable.get(4).size()) tableRow6.addView(createTextView(context,timeTable.get(4).get(i).subject));
				else tableRow6.addView(createTextView(context,""));
			}
		}
	}


	private TextView createTextView(Context context,String text){
		TextView textView = new TextView(context);
		TableRow.LayoutParams param = new TableRow.LayoutParams(
			TableRow.LayoutParams.MATCH_PARENT,
			TableRow.LayoutParams.MATCH_PARENT, 1.0f);
		textView.setLayoutParams(param);
		textView.setGravity(Gravity.CENTER);
		textView.setTextColor(Color.GRAY);
		textView.setText(text);
		return textView;
	}

	private TextView createTextView(Context context,String text,int color){
		TextView textView = new TextView(context);
		TableRow.LayoutParams param = new TableRow.LayoutParams(
				TableRow.LayoutParams.MATCH_PARENT,
				TableRow.LayoutParams.MATCH_PARENT, 1.0f);
		textView.setLayoutParams(param);
		textView.setGravity(Gravity.CENTER);
		textView.setTextColor(Color.GRAY);
		textView.setBackgroundColor(ContextCompat.getColor(context,color));
		textView.setText(text);
		return textView;
	}
}
