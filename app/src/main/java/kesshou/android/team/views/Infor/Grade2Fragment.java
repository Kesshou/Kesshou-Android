package kesshou.android.team.views.infor;


import android.content.Context;
import android.graphics.Color;
import android.support.v4.util.ArrayMap;
import android.support.v4.util.Pair;
import android.view.LayoutInflater;
import android.view.View;

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

import io.realm.Realm;
import kesshou.android.team.R;
import kesshou.android.team.models.NetWorkCache;
import kesshou.android.team.util.network.MyCallBack;
import kesshou.android.team.util.network.NetworkingClient;
import kesshou.android.team.util.network.api.holder.Error;
import kesshou.android.team.util.network.api.holder.HistoryScoreResponse;
import retrofit2.Response;

import static kesshou.android.team.util.BeautifulColor.getRandomColor;

/**
 * A simple class.
 */
public class Grade2Fragment {


	public Grade2Fragment() {
		// Required empty public constructor
	}


	public View onCreateView(final Context context) {
		// Inflate the layout for this fragment
		final View view = LayoutInflater.from(context.getApplicationContext()).inflate(R.layout.fragment_grade2, null);

		Realm realm = Realm.getDefaultInstance();

		NetWorkCache netWorkCache = realm.where(NetWorkCache.class).findFirst();

		final NetworkingClient networkingClient = new NetworkingClient(context);

		final Gson gson = new Gson();

		if (netWorkCache.histroyScore31 == null || netWorkCache.histroyScore32 == null) {
			networkingClient.queryHScore(3, 1, new MyCallBack<List<HistoryScoreResponse>>(context) {
				@Override
				public void onSuccess(final Response<List<HistoryScoreResponse>> response1) {
					networkingClient.queryHScore(3, 2, new MyCallBack<List<HistoryScoreResponse>>(context) {
						@Override
						public void onSuccess(Response<List<HistoryScoreResponse>> response2) {
							if (response1.body().isEmpty() && response2.body().isEmpty()) {
								view.findViewById(R.id.grade3).setVisibility(View.GONE);
							} else {
								Realm realm = Realm.getDefaultInstance();
								NetWorkCache netWorkCache = realm.where(NetWorkCache.class).findFirst();
								realm.beginTransaction();
								netWorkCache.histroyScore31 = gson.toJson(response1.body());
								netWorkCache.histroyScore32 = gson.toJson(response2.body());
								realm.commitTransaction();
								realm.close();

								drawChart(context, view, R.id.chart3, parserDate(response1.body(), response2.body()));
							}
						}

						@Override
						public void onErr(Error error) {

						}
					});
				}

				@Override
				public void onErr(Error error) {

				}
			});
		} else {
			Type listType = new TypeToken<List<HistoryScoreResponse>>() {}.getType();
			List<HistoryScoreResponse> historyScore1= gson.fromJson(netWorkCache.histroyScore31,listType);
			List<HistoryScoreResponse> historyScore2= gson.fromJson(netWorkCache.histroyScore32,listType);
			drawChart(context, view, R.id.chart3, parserDate(historyScore1, historyScore2));

			networkingClient.queryHScore(3, 1, new MyCallBack<List<HistoryScoreResponse>>(context) {
				@Override
				public void onSuccess(final Response<List<HistoryScoreResponse>> response1) {
					networkingClient.queryHScore(3, 2, new MyCallBack<List<HistoryScoreResponse>>(context) {
						@Override
						public void onSuccess(Response<List<HistoryScoreResponse>> response2) {
							if (response1.body().isEmpty() && response2.body().isEmpty()) {
								view.findViewById(R.id.grade3).setVisibility(View.GONE);
							} else {
								Realm realm = Realm.getDefaultInstance();
								NetWorkCache netWorkCache = realm.where(NetWorkCache.class).findFirst();
								realm.beginTransaction();
								netWorkCache.histroyScore31 = gson.toJson(response1.body());
								netWorkCache.histroyScore32 = gson.toJson(response2.body());
								realm.commitTransaction();
								realm.close();

								drawChart(context, view, R.id.chart3, parserDate(response1.body(), response2.body()));
							}
						}

						@Override
						public void onErr(Error error) {

						}
					});
				}

				@Override
				public void onErr(Error error) {

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
							if (response1.body().isEmpty() && response2.body().isEmpty()) {
								view.findViewById(R.id.grade2).setVisibility(View.GONE);
							} else {
								Realm realm = Realm.getDefaultInstance();
								NetWorkCache netWorkCache = realm.where(NetWorkCache.class).findFirst();
								realm.beginTransaction();
								netWorkCache.histroyScore21 = gson.toJson(response1.body());
								netWorkCache.histroyScore22 = gson.toJson(response2.body());
								realm.commitTransaction();
								realm.close();

								drawChart(context, view, R.id.chart2, parserDate(response1.body(), response2.body()));
							}
						}

						@Override
						public void onErr(Error error) {

						}
					});
				}

				@Override
				public void onErr(Error error) {

				}
			});
		} else {
			Type listType = new TypeToken<List<HistoryScoreResponse>>() {}.getType();
			List<HistoryScoreResponse> historyScore1= gson.fromJson(netWorkCache.histroyScore21,listType);
			List<HistoryScoreResponse> historyScore2= gson.fromJson(netWorkCache.histroyScore22,listType);
			drawChart(context, view, R.id.chart2, parserDate(historyScore1, historyScore2));

			networkingClient.queryHScore(2, 1, new MyCallBack<List<HistoryScoreResponse>>(context) {
				@Override
				public void onSuccess(final Response<List<HistoryScoreResponse>> response1) {
					networkingClient.queryHScore(2, 2, new MyCallBack<List<HistoryScoreResponse>>(context) {
						@Override
						public void onSuccess(Response<List<HistoryScoreResponse>> response2) {
							if (response1.body().isEmpty() && response2.body().isEmpty()) {
								view.findViewById(R.id.grade2).setVisibility(View.GONE);
							} else {
								Realm realm = Realm.getDefaultInstance();
								NetWorkCache netWorkCache = realm.where(NetWorkCache.class).findFirst();
								realm.beginTransaction();
								netWorkCache.histroyScore21 = gson.toJson(response1.body());
								netWorkCache.histroyScore22 = gson.toJson(response2.body());
								realm.commitTransaction();
								realm.close();

								drawChart(context, view, R.id.chart2, parserDate(response1.body(), response2.body()));
							}
						}

						@Override
						public void onErr(Error error) {

						}
					});
				}

				@Override
				public void onErr(Error error) {

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
							if (response1.body().isEmpty() && response2.body().isEmpty()) {
								view.findViewById(R.id.grade1).setVisibility(View.GONE);
							} else {
								Realm realm = Realm.getDefaultInstance();
								NetWorkCache netWorkCache = realm.where(NetWorkCache.class).findFirst();
								realm.beginTransaction();
								netWorkCache.histroyScore11 = gson.toJson(response1.body());
								netWorkCache.histroyScore12 = gson.toJson(response2.body());
								realm.commitTransaction();
								realm.close();

								drawChart(context, view, R.id.chart1, parserDate(response1.body(), response2.body()));
							}
						}

						@Override
						public void onErr(Error error) {

						}
					});
				}

				@Override
				public void onErr(Error error) {

				}
			});
		} else {
			Type listType = new TypeToken<List<HistoryScoreResponse>>() {}.getType();
			List<HistoryScoreResponse> historyScore1= gson.fromJson(netWorkCache.histroyScore11,listType);
			List<HistoryScoreResponse> historyScore2= gson.fromJson(netWorkCache.histroyScore12,listType);
			drawChart(context, view, R.id.chart1, parserDate(historyScore1, historyScore2));

			networkingClient.queryHScore(1, 1, new MyCallBack<List<HistoryScoreResponse>>(context) {
				@Override
				public void onSuccess(final Response<List<HistoryScoreResponse>> response1) {
					networkingClient.queryHScore(1, 2, new MyCallBack<List<HistoryScoreResponse>>(context) {
						@Override
						public void onSuccess(Response<List<HistoryScoreResponse>> response2) {
							if (response1.body().isEmpty() && response2.body().isEmpty()) {
								view.findViewById(R.id.grade1).setVisibility(View.GONE);
							} else {
								Realm realm = Realm.getDefaultInstance();
								NetWorkCache netWorkCache = realm.where(NetWorkCache.class).findFirst();
								realm.beginTransaction();
								netWorkCache.histroyScore11 = gson.toJson(response1.body());
								netWorkCache.histroyScore12 = gson.toJson(response2.body());
								realm.commitTransaction();
								realm.close();

								drawChart(context, view, R.id.chart1, parserDate(response1.body(), response2.body()));
							}
						}

						@Override
						public void onErr(Error error) {

						}
					});
				}

				@Override
				public void onErr(Error error) {

				}
			});
		}


		realm.close();

		return view;
	}

	private void drawChart(Context context, View view, int chartID, final ArrayMap<String, Pair<HistoryScoreResponse, HistoryScoreResponse>> historyScore) {
		HorizontalBarChart barChart = (HorizontalBarChart) view.findViewById(chartID);

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
				stringBuilder.append(" \n ");
//				if(historyScore.valueAt(Math.abs((int) value % historyScore.size())).first!=null) {
//					stringBuilder.append("(");
//					stringBuilder.append(historyScore.valueAt(Math.abs((int) value % historyScore.size())).first.type);
//					stringBuilder.append(" ");
//					stringBuilder.append(historyScore.valueAt(Math.abs((int) value % historyScore.size())).first.credit);
//					stringBuilder.append(")");
//					stringBuilder.append(" \n ");
//				}
//				if(historyScore.valueAt(Math.abs((int) value % historyScore.size())).second!=null) {
//					stringBuilder.append("(");
//					stringBuilder.append(historyScore.valueAt(Math.abs((int) value % historyScore.size())).second.type);
//					stringBuilder.append(" ");
//					stringBuilder.append(historyScore.valueAt(Math.abs((int) value % historyScore.size())).second.credit);
//					stringBuilder.append(")");
//				}
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
		float groupSpace = 0.2f;
		float barSpace = 0.0f; // x2 DataSet
		float barWidth = 0.40f; // x2 DataSet
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
		set1.setColor(getRandomColor());
		set1.setHighLightAlpha(180);
		BarDataSet set2 = new BarDataSet(yVals2, context.getString(R.string.infor_grade2_semember2));
		set2.setColor(getRandomColor());
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


}

//	private void drawChart(Context context, View view, int chartID, final List<HistoryScoreResponse> historyScore1, List<HistoryScoreResponse> historyScore2){
//
//		HorizontalBarChart barChart = (HorizontalBarChart) view.findViewById(chartID);
//
//		barChart.setDrawBarShadow(false);
//
//		barChart.setDrawValueAboveBar(true);
//
//		barChart.getDescription().setEnabled(false);
//
//		barChart.setPinchZoom(false);
//
//		barChart.setDoubleTapToZoomEnabled(false);
//
//		barChart.setDrawGridBackground(false);
//
//		XAxis xl = barChart.getXAxis();
//		xl.setPosition(XAxis.XAxisPosition.BOTTOM);
//		xl.setDrawAxisLine(true);
//		xl.setDrawGridLines(false);
//		xl.setGranularity(1f);
//		xl.setCenterAxisLabels(true);
//		xl.setValueFormatter(new IAxisValueFormatter() {
//			@Override
//			public String getFormattedValue(float value, AxisBase axis) {
//				return historyScore1.get(Math.abs((int)value%historyScore1.size())).subject;
//			}
//
//			@Override
//			public int getDecimalDigits() {
//				return 0;
//			}
//		});
//
//		YAxis yl = barChart.getAxisLeft();
//		yl.setDrawAxisLine(true);
//		yl.setDrawGridLines(true);
//		yl.setAxisMinimum(0f); // this replaces setStartAtZero(true)
//
//		YAxis yr = barChart.getAxisRight();
//		yr.setDrawAxisLine(true);
//		yr.setDrawGridLines(false);
//		yr.setAxisMinimum(0f); // this replaces setStartAtZero(true)
//
//		//float barWidth = 9f;
//		//float spaceForBar = 10f;
//		float groupSpace = 0.1f;
//		float barSpace = 0.05f; // x4 DataSet
//		float barWidth = 0.40f; // x4 DataSet
//		ArrayList<BarEntry> yVals1 = new ArrayList<>();
//		ArrayList<BarEntry> yVals2 = new ArrayList<>();
//
//		for(int i=0;i<historyScore1.size();i++){
//			yVals1.add(new BarEntry(i,historyScore1.get(i).score));
//		}
//
//		for(int i=0;i<historyScore2.size();i++){
//			yVals2.add(new BarEntry(i,historyScore2.get(i).score));
//		}
//
//		BarDataSet set1 = new BarDataSet(yVals1,context.getString(R.string.infor_grade2_semember1));
//		set1.setColor(getRandomColor());
//		BarDataSet set2 = new BarDataSet(yVals2,context.getString(R.string.infor_grade2_semember2));
//		set2.setColor(getRandomColor());
//
//
//		BarData data = new BarData(set1,set2);
//		data.setValueTextSize(10f);
//		data.setBarWidth(barWidth);
//		barChart.setData(data);
//
//		barChart.getXAxis().setAxisMaximum(barChart.getBarData().getGroupWidth(groupSpace, barSpace) * (Math.max(historyScore1.size(),historyScore2.size())));
//		barChart.getXAxis().setAxisMinimum(0);
//		barChart.getXAxis().setLabelCount(Math.max(historyScore1.size(),historyScore2.size()),false);
//		barChart.groupBars(0, groupSpace, barSpace);
//
//		barChart.invalidate();
//
//
//		barChart.setFitBars(true);
//		barChart.animateY(2500);
//
//		Legend l = barChart.getLegend();
//		l.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
//		l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);
//		l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
//		l.setDrawInside(false);
//		l.setFormSize(8f);
//		l.setXEntrySpace(4f);
//	}