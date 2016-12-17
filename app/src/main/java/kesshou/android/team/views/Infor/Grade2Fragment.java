package kesshou.android.team.views.infor;


import android.content.Context;
import android.graphics.Color;
import android.support.v4.util.ArrayMap;
import android.support.v4.util.Pair;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import io.realm.Realm;
import kesshou.android.team.R;
import kesshou.android.team.models.NetWorkCache;
import kesshou.android.team.util.BeautifulColor;
import kesshou.android.team.util.UnitConvert;
import kesshou.android.team.util.network.MyCallBack;
import kesshou.android.team.util.network.NetworkingClient;
import kesshou.android.team.util.network.api.holder.Error;
import kesshou.android.team.util.network.api.holder.HistoryScoreResponse;
import retrofit2.Response;

/**
 * A simple class.
 */
public class Grade2Fragment {


	public Grade2Fragment() {
		// Required empty public constructor
	}

	private ArrayMap<Integer,Boolean> whoLast;
	private int nowPager;

	public View onCreateView(final Context context) {
		// Inflate the layout for this fragment
		final View view = LayoutInflater.from(context.getApplicationContext()).inflate(R.layout.fragment_grade2, null);

		Realm realm = Realm.getDefaultInstance();

		NetWorkCache netWorkCache = realm.where(NetWorkCache.class).findFirst();

		final NetworkingClient networkingClient = new NetworkingClient(context);

		final Gson gson = new Gson();

		whoLast = new ArrayMap<>();
		nowPager = 0;

		if(netWorkCache.histroyScore31==null&&netWorkCache.histroyScore32==null){
			networkingClient.queryHScore(3, 1, new MyCallBack<List<HistoryScoreResponse>>(context) {
				@Override
				public void onSuccess(final Response<List<HistoryScoreResponse>> response1) {
					networkingClient.queryHScore(3, 2, new MyCallBack<List<HistoryScoreResponse>>(context) {
						@Override
						public void onSuccess(Response<List<HistoryScoreResponse>> response2) {
							if(!response1.body().isEmpty()||!response2.body().isEmpty()) {
								Realm realm = Realm.getDefaultInstance();
								NetWorkCache netWorkCache = realm.where(NetWorkCache.class).findFirst();
								realm.beginTransaction();
								netWorkCache.histroyScore31 = gson.toJson(response1.body());
								netWorkCache.histroyScore32 = gson.toJson(response2.body());
								realm.commitTransaction();
								realm.close();
								isLast(context, view, 3, true);
							}else {
								isLast(context, view, 3, false);
							}
						}

						@Override
						public void onErr(Error error) {
							isLast(context,view,3,false);
						}
					});
				}

				@Override
				public void onErr(Error error) {
					isLast(context,view,3,false);
				}
			});
		}else{
			Type listType = new TypeToken<List<HistoryScoreResponse>>() {}.getType();
			List<HistoryScoreResponse> historyScore31= gson.fromJson(netWorkCache.histroyScore31,listType);
			List<HistoryScoreResponse> historyScore32= gson.fromJson(netWorkCache.histroyScore32,listType);
			if(historyScore31.isEmpty()&&historyScore32.isEmpty()) isLast(context,view,3,false);
			else isLast(context,view,3,true);

			networkingClient.queryHScore(3, 1, new MyCallBack<List<HistoryScoreResponse>>(context) {
				@Override
				public void onSuccess(final Response<List<HistoryScoreResponse>> response1) {
					networkingClient.queryHScore(3, 2, new MyCallBack<List<HistoryScoreResponse>>(context) {
						@Override
						public void onSuccess(Response<List<HistoryScoreResponse>> response2) {
							if(!response1.body().isEmpty()||!response2.body().isEmpty()) {
								Realm realm = Realm.getDefaultInstance();
								NetWorkCache netWorkCache = realm.where(NetWorkCache.class).findFirst();
								realm.beginTransaction();
								netWorkCache.histroyScore31 = gson.toJson(response1.body());
								netWorkCache.histroyScore32 = gson.toJson(response2.body());
								realm.commitTransaction();
								realm.close();
								isLast(context, view, 3, true);
							}else {
								isLast(context, view, 3, false);
							}
						}

						@Override
						public void onErr(Error error) {
							isLast(context,view,3,false);
						}
					});
				}

				@Override
				public void onErr(Error error) {
					isLast(context,view,3,false);
				}
			});
		}

		if (netWorkCache.histroyScore21 == null || netWorkCache.histroyScore22 == null) {
			networkingClient.queryHScore(2, 1, new MyCallBack<List<HistoryScoreResponse>>(context) {
				@Override
				public void onSuccess(final Response<List<HistoryScoreResponse>> response1) {
					networkingClient.queryHScore(2, 2, new MyCallBack<List<HistoryScoreResponse>>(context) {
						@Override
						public void onSuccess(Response<List<HistoryScoreResponse>> response2) {
							if(!response1.body().isEmpty()||!response2.body().isEmpty()) {
								Realm realm = Realm.getDefaultInstance();
								NetWorkCache netWorkCache = realm.where(NetWorkCache.class).findFirst();
								realm.beginTransaction();
								netWorkCache.histroyScore21 = gson.toJson(response1.body());
								netWorkCache.histroyScore22 = gson.toJson(response2.body());
								realm.commitTransaction();
								realm.close();
								isLast(context,view,2,true);
							}else{
								isLast(context,view,2,false);
							}
						}

						@Override
						public void onErr(Error error) {
							isLast(context,view,2,false);
						}
					});
				}

				@Override
				public void onErr(Error error) {
					isLast(context,view,2,false);
				}
			});
		} else {
			Type listType = new TypeToken<List<HistoryScoreResponse>>() {}.getType();
			List<HistoryScoreResponse> historyScore21= gson.fromJson(netWorkCache.histroyScore21,listType);
			List<HistoryScoreResponse> historyScore22= gson.fromJson(netWorkCache.histroyScore22,listType);
			if(historyScore21.isEmpty()&&historyScore22.isEmpty()) isLast(context,view,2,false);
			else isLast(context,view,2,true);

			networkingClient.queryHScore(2, 1, new MyCallBack<List<HistoryScoreResponse>>(context) {
				@Override
				public void onSuccess(final Response<List<HistoryScoreResponse>> response1) {
					networkingClient.queryHScore(2, 2, new MyCallBack<List<HistoryScoreResponse>>(context) {
						@Override
						public void onSuccess(Response<List<HistoryScoreResponse>> response2) {
							if(!response1.body().isEmpty()||!response2.body().isEmpty()) {
								Realm realm = Realm.getDefaultInstance();
								NetWorkCache netWorkCache = realm.where(NetWorkCache.class).findFirst();
								realm.beginTransaction();
								netWorkCache.histroyScore21 = gson.toJson(response1.body());
								netWorkCache.histroyScore22 = gson.toJson(response2.body());
								realm.commitTransaction();
								realm.close();
								isLast(context,view,2,true);
							}else{
								isLast(context,view,2,false);
							}
						}

						@Override
						public void onErr(Error error) {
							isLast(context,view,2,false);
						}
					});
				}

				@Override
				public void onErr(Error error) {
					isLast(context,view,2,false);
				}
			});
		}

		if (netWorkCache.histroyScore11 == null || netWorkCache.histroyScore12 == null) {
			networkingClient.queryHScore(1, 1, new MyCallBack<List<HistoryScoreResponse>>(context) {
				@Override
				public void onSuccess(final Response<List<HistoryScoreResponse>> response1) {
					networkingClient.queryHScore(1, 2, new MyCallBack<List<HistoryScoreResponse>>(context) {
						@Override
						public void onSuccess(Response<List<HistoryScoreResponse>> response2) {
							if(!response1.body().isEmpty()&&!response2.body().isEmpty()) {
								Realm realm = Realm.getDefaultInstance();
								NetWorkCache netWorkCache = realm.where(NetWorkCache.class).findFirst();
								realm.beginTransaction();
								netWorkCache.histroyScore11 = gson.toJson(response1.body());
								netWorkCache.histroyScore12 = gson.toJson(response2.body());
								realm.commitTransaction();
								realm.close();
								isLast(context,view,1,true);
							}else{
								isLast(context,view,1,false);
							}
						}

						@Override
						public void onErr(Error error) {
							isLast(context,view,1,false);
						}
					});
				}

				@Override
				public void onErr(Error error) {
					isLast(context,view,1,false);
				}
			});
		} else {
			Type listType = new TypeToken<List<HistoryScoreResponse>>() {}.getType();
			List<HistoryScoreResponse> historyScore11= gson.fromJson(netWorkCache.histroyScore11,listType);
			List<HistoryScoreResponse> historyScore12= gson.fromJson(netWorkCache.histroyScore12,listType);
			if(historyScore11.isEmpty()&&historyScore12.isEmpty()) isLast(context,view,1,false);
			else isLast(context,view,1,true);

			networkingClient.queryHScore(1, 1, new MyCallBack<List<HistoryScoreResponse>>(context) {
				@Override
				public void onSuccess(final Response<List<HistoryScoreResponse>> response1) {
					networkingClient.queryHScore(1, 2, new MyCallBack<List<HistoryScoreResponse>>(context) {
						@Override
						public void onSuccess(Response<List<HistoryScoreResponse>> response2) {
							if(!response1.body().isEmpty()&&!response2.body().isEmpty()) {
								Realm realm = Realm.getDefaultInstance();
								NetWorkCache netWorkCache = realm.where(NetWorkCache.class).findFirst();
								realm.beginTransaction();
								netWorkCache.histroyScore11 = gson.toJson(response1.body());
								netWorkCache.histroyScore12 = gson.toJson(response2.body());
								realm.commitTransaction();
								realm.close();
								isLast(context,view,1,true);
							}else{
								isLast(context,view,1,false);
							}
						}

						@Override
						public void onErr(Error error) {
							isLast(context,view,1,false);
						}
					});
				}

				@Override
				public void onErr(Error error) {
					isLast(context,view,1,false);
				}
			});
		}

		TextView btnUp = (TextView) view.findViewById(R.id.btn_up);
		btnUp.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if((nowPager-1)>0) serPager(context,view,nowPager-1);
			}
		});

		TextView btnDown = (TextView) view.findViewById(R.id.btn_down);
		btnDown.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if((nowPager+1)<4) serPager(context,view,nowPager+1);
			}
		});


		realm.close();

		return view;
	}

	private void serPager(Context context,View view,int i){
		switch (i){
			case 1:
				if(whoLast.get(i)) {
					nowPager = 1;
					Realm realm = Realm.getDefaultInstance();
					NetWorkCache netWorkCache = realm.where(NetWorkCache.class).findFirst();
					Type listType = new TypeToken<List<HistoryScoreResponse>>() {}.getType();
					Gson gson = new Gson();
					List<HistoryScoreResponse> historyScore11= gson.fromJson(netWorkCache.histroyScore11,listType);
					List<HistoryScoreResponse> historyScore12= gson.fromJson(netWorkCache.histroyScore12,listType);
					drawBody(context,view,parserDate(historyScore11,historyScore12));
				}else{
					nowPager = 1;
					drawNull(context,view);
				}
				break;
			case 2:
				if(whoLast.get(i)) {
					nowPager = 2;
					Realm realm = Realm.getDefaultInstance();
					NetWorkCache netWorkCache = realm.where(NetWorkCache.class).findFirst();
					Type listType = new TypeToken<List<HistoryScoreResponse>>() {}.getType();
					Gson gson = new Gson();
					List<HistoryScoreResponse> historyScore21= gson.fromJson(netWorkCache.histroyScore21,listType);
					List<HistoryScoreResponse> historyScore22= gson.fromJson(netWorkCache.histroyScore22,listType);
					drawBody(context,view,parserDate(historyScore21,historyScore22));
				}else{
					nowPager = 2;
					drawNull(context,view);
				}
				break;
			case 3:
				if(whoLast.get(i)){
					nowPager = 3;
					Realm realm = Realm.getDefaultInstance();
					NetWorkCache netWorkCache = realm.where(NetWorkCache.class).findFirst();
					Type listType = new TypeToken<List<HistoryScoreResponse>>() {}.getType();
					Gson gson = new Gson();
					List<HistoryScoreResponse> historyScore31= gson.fromJson(netWorkCache.histroyScore31,listType);
					List<HistoryScoreResponse> historyScore32= gson.fromJson(netWorkCache.histroyScore32,listType);
					drawBody(context,view,parserDate(historyScore31,historyScore32));
				}else{
					nowPager = 3;
					drawNull(context,view);
				}
				break;
		}
	}

	private void isLast(Context context,View view,int i,boolean b){
		whoLast.put(i,b);
		if(whoLast.size()==3){
			if(whoLast.get(3) && (i == 3 || nowPager == 0)){
				nowPager = 3;
				Realm realm = Realm.getDefaultInstance();
				NetWorkCache netWorkCache = realm.where(NetWorkCache.class).findFirst();
				Type listType = new TypeToken<List<HistoryScoreResponse>>() {}.getType();
				Gson gson = new Gson();
				List<HistoryScoreResponse> historyScore31= gson.fromJson(netWorkCache.histroyScore31,listType);
				List<HistoryScoreResponse> historyScore32= gson.fromJson(netWorkCache.histroyScore32,listType);
				drawBody(context,view,parserDate(historyScore31,historyScore32));
			}else if(whoLast.get(2) && (i == 2 || nowPager == 0)){
				nowPager = 2;
				Realm realm = Realm.getDefaultInstance();
				NetWorkCache netWorkCache = realm.where(NetWorkCache.class).findFirst();
				Type listType = new TypeToken<List<HistoryScoreResponse>>() {}.getType();
				Gson gson = new Gson();
				List<HistoryScoreResponse> historyScore21= gson.fromJson(netWorkCache.histroyScore21,listType);
				List<HistoryScoreResponse> historyScore22= gson.fromJson(netWorkCache.histroyScore22,listType);
				drawBody(context,view,parserDate(historyScore21,historyScore22));
			}else if(whoLast.get(1) && (i == 1 || nowPager == 0)){
				nowPager = 1;
				Realm realm = Realm.getDefaultInstance();
				NetWorkCache netWorkCache = realm.where(NetWorkCache.class).findFirst();
				Type listType = new TypeToken<List<HistoryScoreResponse>>() {}.getType();
				Gson gson = new Gson();
				List<HistoryScoreResponse> historyScore11= gson.fromJson(netWorkCache.histroyScore11,listType);
				List<HistoryScoreResponse> historyScore12= gson.fromJson(netWorkCache.histroyScore12,listType);
				drawBody(context,view,parserDate(historyScore11,historyScore12));
			}else if(!whoLast.get(2)&&!whoLast.get(1)&&!whoLast.get(3)){
				nowPager = 0;
				drawNull(context,view);
			}
		}
	}

	private void drawBody(Context context,View view,ArrayMap<String, Pair<HistoryScoreResponse, HistoryScoreResponse>> historyScores){
		LinearLayout layout = (LinearLayout) view.findViewById(R.id.list);
		layout.removeAllViews();

		View item1 = LayoutInflater.from(context).inflate(R.layout.grade2_list_item,null);
		TextView textView1 = (TextView) item1.findViewById(R.id.subject);
		textView1.setText("");
		textView1 = (TextView) item1.findViewById(R.id.type);
		textView1.setText("種類");
		textView1 = (TextView) item1.findViewById(R.id.credit);
		textView1.setText("學分");
		textView1 = (TextView) item1.findViewById(R.id.first);
		textView1.setText("上");
		textView1 = (TextView) item1.findViewById(R.id.second);
		textView1.setText("下");
		layout.addView(item1);

		for(Map.Entry<String, Pair<HistoryScoreResponse, HistoryScoreResponse>> entry:historyScores.entrySet()){
			View item = LayoutInflater.from(context).inflate(R.layout.grade2_list_item,null);
			TextView textView = (TextView) item.findViewById(R.id.subject);
			textView.setText(entry.getKey());
			boolean first = entry.getValue().first!=null;
			boolean second = entry.getValue().second!=null;
			textView = (TextView) item.findViewById(R.id.type);
			if(first) textView.setText(entry.getValue().first.type);
			else textView.setText(entry.getValue().second.type);
			textView = (TextView) item.findViewById(R.id.credit);
			if(first) textView.setText(String.valueOf(entry.getValue().first.credit));
			else textView.setText(String.valueOf(entry.getValue().second.credit));
			textView = (TextView) item.findViewById(R.id.first);
			if(first) {
				textView.setText(String.valueOf(entry.getValue().first.score));
				textView.setTextColor(whatColor(entry.getValue().first.qualify,entry.getValue().first.score));
			}else {
				textView.setText("");
			}
			textView = (TextView) item.findViewById(R.id.second);
			if(second) {
				textView.setText(String.valueOf(entry.getValue().second.score));
				textView.setTextColor(whatColor(entry.getValue().second.qualify,entry.getValue().second.score));
			}else {
				textView.setText("");
			}

			layout.addView(item);
		}

		HorizontalBarChart barChart = new HorizontalBarChart(context);
		LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(
			TableRow.LayoutParams.MATCH_PARENT,
			(int) UnitConvert.Dp2Pixel(500,context));
		barChart.setLayoutParams(param);
		drawChart(context,barChart,historyScores);

		layout.addView(barChart);

		drawTop(view);
	}

	private void drawNull(Context context,View view){
		LinearLayout layout = (LinearLayout) view.findViewById(R.id.list);
		layout.removeAllViews();
		TextView textView = new TextView(context);
		TableRow.LayoutParams param = new TableRow.LayoutParams(
			TableRow.LayoutParams.MATCH_PARENT,
			TableRow.LayoutParams.MATCH_PARENT, 1.0f);
		textView.setLayoutParams(param);
		textView.setGravity(Gravity.CENTER);
		textView.setTextColor(Color.GRAY);
		textView.setText(context.getString(R.string.no_data));
		layout.addView(textView);

		drawTop(view);
	}

	private void drawTop(View view){
		TextView txtChoose = (TextView) view.findViewById(R.id.chooses);
		switch (nowPager){
			case 1:
				txtChoose.setText(view.getResources().getString(R.string.infor_grade2_grade1));
				break;
			case 2:
				txtChoose.setText(view.getResources().getString(R.string.infor_grade2_grade2));
				break;
			case 3:
				txtChoose.setText(view.getResources().getString(R.string.infor_grade2_grade3));
				break;
			default:
				txtChoose.setText("");
				break;
		}

	}


	private int whatColor(int qu,int score){
		if(score>=qu){
			return BeautifulColor.getColor(4);
		}else{
			return BeautifulColor.getColor(0);
		}
	}

	private ArrayMap<String, Pair<HistoryScoreResponse, HistoryScoreResponse>> parserDate(List<HistoryScoreResponse> historyScore1, List<HistoryScoreResponse> historyScore2) {
		ArrayMap<String, Pair<HistoryScoreResponse, HistoryScoreResponse>> list = new ArrayMap<>();

		for (int i = 0; i < historyScore1.size(); i++) {
			HistoryScoreResponse item = new HistoryScoreResponse();
			item.subject = historyScore1.get(i).subject;
			item.type = historyScore1.get(i).type;
			item.credit = historyScore1.get(i).credit;
			item.qualify = historyScore1.get(i).qualify;
			if (historyScore1.get(i).retake != 0) item.score = historyScore1.get(i).retake;
			else if (historyScore1.get(i).makeup != 0) item.score = historyScore1.get(i).makeup;
			else item.score = historyScore1.get(i).score;

			Pair<HistoryScoreResponse, HistoryScoreResponse> pair = new Pair<>(item, null);
			list.put(item.subject, pair);
		}

		for (int i = 0; i < historyScore2.size(); i++) {
			HistoryScoreResponse item = new HistoryScoreResponse();
			item.subject = historyScore2.get(i).subject;
			item.type = historyScore2.get(i).type;
			item.credit = historyScore2.get(i).credit;
			item.qualify = historyScore2.get(i).qualify;
			if (historyScore2.get(i).retake != 0) item.score = historyScore2.get(i).retake;
			else if (historyScore2.get(i).makeup != 0) item.score = historyScore2.get(i).makeup;
			else item.score = historyScore2.get(i).score;

			if (!list.containsKey(historyScore2.get(i).subject)) {
				Pair<HistoryScoreResponse, HistoryScoreResponse> pair = new Pair<>(null, item);
				list.put(historyScore2.get(i).subject, pair);
			} else {
				Pair<HistoryScoreResponse, HistoryScoreResponse> pair = new Pair<>(list.get(historyScore2.get(i).subject).first, item);
				list.put(historyScore2.get(i).subject, pair);
			}
		}

		return list;
	}


	private void drawChart(Context context,HorizontalBarChart barChart, final ArrayMap<String, Pair<HistoryScoreResponse, HistoryScoreResponse>> historyScore) {
		barChart.setDrawBarShadow(false);

		barChart.setDrawValueAboveBar(true);

		barChart.getDescription().setEnabled(false);

		barChart.setPinchZoom(false);

		barChart.setDoubleTapToZoomEnabled(false);

		barChart.setDrawGridBackground(false);

		XAxis xl = barChart.getXAxis();
		xl.setPosition(XAxis.XAxisPosition.BOTTOM);
		xl.setDrawAxisLine(true);
		xl.setDrawGridLines(true);
		xl.setGranularity(1f);
		xl.setCenterAxisLabels(true);
		xl.setValueFormatter(new IAxisValueFormatter() {
			@Override
			public String getFormattedValue(float value, AxisBase axis) {
				StringBuilder stringBuilder = new StringBuilder();
				stringBuilder.append(historyScore.keyAt(Math.abs((int) value % historyScore.size())));
				return stringBuilder.toString();
			}

			@Override
			public int getDecimalDigits() {
				return 0;
			}
		});

		YAxis yl = barChart.getAxisLeft();
		yl.setDrawAxisLine(true);
		yl.setDrawGridLines(true);
		yl.setAxisMinimum(0f); // this replaces setStartAtZero(true)
		yl.setAxisMaximum(100f);

		YAxis yr = barChart.getAxisRight();
		yr.setDrawAxisLine(true);
		yr.setDrawGridLines(true);
		yr.setAxisMinimum(0f); // this replaces setStartAtZero(true)
		yr.setAxisMaximum(100f);

		//float barWidth = 9f;
		//float spaceForBar = 10f;
		float groupSpace = 0.25f;
		float barSpace = 0.025f; // x2 DataSet
		float barWidth = 0.35f; // x2 DataSet
		ArrayList<BarEntry> yVals1 = new ArrayList<>();
		ArrayList<BarEntry> yVals2 = new ArrayList<>();

		for (int i = 0; i < historyScore.size(); i++) {
			if (historyScore.valueAt(i).first != null)
				yVals1.add(new BarEntry(i, historyScore.valueAt(i).first.score));
			else yVals1.add(new BarEntry(i, 0));
			if (historyScore.valueAt(i).second != null)
				yVals2.add(new BarEntry(i, historyScore.valueAt(i).second.score));
			else yVals2.add(new BarEntry(i, 0));
		}

		BarDataSet set1 = new BarDataSet(yVals1, context.getString(R.string.infor_grade2_semember1));
		set1.setColor(BeautifulColor.getColor(2));
		set1.setHighLightAlpha(180);
		BarDataSet set2 = new BarDataSet(yVals2, context.getString(R.string.infor_grade2_semember2));
		set2.setColor(BeautifulColor.getColor(3));
		set2.setHighLightAlpha(180);


		BarData data = new BarData(set2, set1);
		data.setValueTextSize(10f);
		data.setBarWidth(barWidth);
		barChart.setData(data);

		barChart.getXAxis().setAxisMaximum(barChart.getBarData().getGroupWidth(groupSpace, barSpace) * historyScore.size());
		barChart.getXAxis().setAxisMinimum(0);
		barChart.getXAxis().setLabelCount(historyScore.size(), false);
		barChart.groupBars(0, groupSpace, barSpace);

		barChart.invalidate();


		barChart.setFitBars(true);
		barChart.animateY(2500);

		LimitLine limitLine;
		if(historyScore.valueAt(0).first!=null)
			limitLine = new LimitLine(historyScore.valueAt(0).first.qualify);
		else
			limitLine = new LimitLine(historyScore.valueAt(0).second.qualify);
		limitLine.setLabel("");
		limitLine.setTextSize(9f);
		limitLine.setLineColor(Color.RED);
		limitLine.setLineWidth(1);
		yl.addLimitLine(limitLine);

		Legend l = barChart.getLegend();
		l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
		l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);
		l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
		l.setDrawInside(false);
		l.setFormSize(8f);
		l.setXEntrySpace(4f);
	}
}
