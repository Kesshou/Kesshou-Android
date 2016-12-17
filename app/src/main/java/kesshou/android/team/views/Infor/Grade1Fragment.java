package kesshou.android.team.views.infor;


import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.RadarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.RadarData;
import com.github.mikephil.charting.data.RadarDataSet;
import com.github.mikephil.charting.data.RadarEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IRadarDataSet;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import kesshou.android.team.R;
import kesshou.android.team.models.NetWorkCache;
import kesshou.android.team.util.BeautifulColor;
import kesshou.android.team.util.network.MyCallBack;
import kesshou.android.team.util.network.NetworkingClient;
import kesshou.android.team.util.network.api.holder.Error;
import kesshou.android.team.util.network.api.holder.SectionalExamResponse;
import retrofit2.Response;

/**
 * A simple class.
 */
public class Grade1Fragment {


	public Grade1Fragment() {
		// Required empty public constructor
	}


	public View onCreateView(final Context context) {
		// Inflate the layout for this fragment
		final View view = LayoutInflater.from(context.getApplicationContext()).inflate(R.layout.fragment_grade1,null);

		Realm realm = Realm.getDefaultInstance();

		NetWorkCache netWorkCache = realm.where(NetWorkCache.class).findFirst();

		final NetworkingClient networkingClient = new NetworkingClient(context.getApplicationContext());

		final Gson gson = new Gson();

		final RadarChart radarChart1 = (RadarChart) view.findViewById(R.id.chart1);
		final RadarChart radarChart2 = (RadarChart) view.findViewById(R.id.chart2);

		if(netWorkCache.sectionalexamscore1==null&&netWorkCache.sectionalexamscore2==null){

			networkingClient.querySEScore(1, new MyCallBack<List<SectionalExamResponse>>(context.getApplicationContext()) {
				@Override
				public void onSuccess(final Response<List<SectionalExamResponse>> response) {
					Realm realm = Realm.getDefaultInstance();
					realm.executeTransaction(new Realm.Transaction() {
						@Override
						public void execute(Realm realm) {
							NetWorkCache netWorkCache = realm.where(NetWorkCache.class).findFirst();
							netWorkCache.sectionalexamscore1=gson.toJson(response.body());
						}
					});
					realm.close();
					drawChart(radarChart1,"上學期",response.body());
				}

				@Override
				public void onErr(Error error) {

				}
			});

			networkingClient.querySEScore(2, new MyCallBack<List<SectionalExamResponse>>(context.getApplicationContext()) {
				@Override
				public void onSuccess(final Response<List<SectionalExamResponse>> response) {
					Realm realm = Realm.getDefaultInstance();
					realm.executeTransaction(new Realm.Transaction() {
						@Override
						public void execute(Realm realm) {
							NetWorkCache netWorkCache = realm.where(NetWorkCache.class).findFirst();
							netWorkCache.sectionalexamscore2=gson.toJson(response.body());
						}
					});
					realm.close();
					drawChart(radarChart2,"下學期",response.body());
				}

				@Override
				public void onErr(Error error) {

				}
			});

		}else{

			Type listType = new TypeToken<List<SectionalExamResponse>>() {}.getType();
			List<SectionalExamResponse> examResponses1= gson.fromJson(netWorkCache.sectionalexamscore1,listType);
			drawChart(radarChart1,"上學期",examResponses1);

			List<SectionalExamResponse> examResponses2= gson.fromJson(netWorkCache.sectionalexamscore2,listType);
			drawChart(radarChart2,"上學期",examResponses2);

			networkingClient.querySEScore(1, new MyCallBack<List<SectionalExamResponse>>(context.getApplicationContext()) {
				@Override
				public void onSuccess(final Response<List<SectionalExamResponse>> response) {
					//Log.d("oao",response.body().toString());
					Realm realm = Realm.getDefaultInstance();
					realm.executeTransaction(new Realm.Transaction() {
						@Override
						public void execute(Realm realm) {
							NetWorkCache netWorkCache = realm.where(NetWorkCache.class).findFirst();
							netWorkCache.sectionalexamscore1=gson.toJson(response.body());
						}
					});
					realm.close();
					drawChart(radarChart1,"上學期",response.body());
				}

				@Override
				public void onErr(Error error) {

				}
			});

			networkingClient.querySEScore(2, new MyCallBack<List<SectionalExamResponse>>(context.getApplicationContext()) {
				@Override
				public void onSuccess(final Response<List<SectionalExamResponse>> response) {
					Realm realm = Realm.getDefaultInstance();
					realm.executeTransaction(new Realm.Transaction() {
						@Override
						public void execute(Realm realm) {
							NetWorkCache netWorkCache = realm.where(NetWorkCache.class).findFirst();
							netWorkCache.sectionalexamscore2=gson.toJson(response.body());
						}
					});
					realm.close();
					drawChart(radarChart2,"下學期",response.body());
				}

				@Override
				public void onErr(Error error) {

				}
			});
		}


		realm.close();

		return view;
	}

	private void drawChart(RadarChart radarChart,String title,List<SectionalExamResponse> sectionalExamResponses){

		if(sectionalExamResponses.size()>0) {

			radarChart.getDescription().setEnabled(false);

			radarChart.setBackgroundColor(Color.TRANSPARENT);
			radarChart.setWebLineWidth(1f);
			radarChart.setWebColor(Color.LTGRAY);
			radarChart.setWebLineWidthInner(1f);
			radarChart.setWebColorInner(Color.LTGRAY);
			radarChart.setWebAlpha(100);
//			radarChart.setScaleX(1f);
//			radarChart.setScaleY(1f);
//			radarChart.setTouchEnabled(true);

//			radarChart.getDescription().setEnabled(false);
			Description description = new Description();
			description.setText(title);
			description.setTextAlign(Paint.Align.CENTER);
			radarChart.setDescription(description);


			ArrayList<RadarEntry> yVals1 = new ArrayList<>();
			ArrayList<RadarEntry> yVals2 = new ArrayList<>();
			ArrayList<RadarEntry> yVals3 = new ArrayList<>();
			ArrayList<RadarEntry> yVals4 = new ArrayList<>();
			ArrayList<RadarEntry> yVals5 = new ArrayList<>();

			for (int i = 0; i < sectionalExamResponses.size(); i++) {
				if (!sectionalExamResponses.get(i).subject.equals("平均")) {
					yVals1.add(new RadarEntry(sectionalExamResponses.get(i).firstSection));
					yVals2.add(new RadarEntry(sectionalExamResponses.get(i).secondSection));
					yVals3.add(new RadarEntry(sectionalExamResponses.get(i).lastSection));
					yVals4.add(new RadarEntry(sectionalExamResponses.get(i).performance));
					yVals5.add(new RadarEntry(sectionalExamResponses.get(i).average));
				}
			}

			int randomColor = BeautifulColor.getRandomColor();
			RadarDataSet set1 = new RadarDataSet(yVals1, "第一次段考");
			set1.setColor(randomColor);
			set1.setFillColor(randomColor);
			set1.setDrawFilled(true);
			set1.setFillAlpha(180);
			set1.setLineWidth(2f);
			set1.setDrawHighlightCircleEnabled(true);
			set1.setDrawHighlightIndicators(false);

			randomColor = BeautifulColor.getRandomColor();
			RadarDataSet set2 = new RadarDataSet(yVals2, "第二次段考");
			set2.setColor(randomColor);
			set2.setFillColor(randomColor);
			set2.setDrawFilled(true);
			set2.setFillAlpha(180);
			set2.setLineWidth(2f);
			set2.setDrawHighlightCircleEnabled(true);
			set2.setDrawHighlightIndicators(false);

			randomColor = BeautifulColor.getRandomColor();
			RadarDataSet set3 = new RadarDataSet(yVals3, "第三次段考");
			set3.setColor(randomColor);
			set3.setFillColor(randomColor);
			set3.setDrawFilled(true);
			set3.setFillAlpha(180);
			set3.setLineWidth(2f);
			set3.setDrawHighlightCircleEnabled(true);
			set3.setDrawHighlightIndicators(false);

			randomColor = BeautifulColor.getRandomColor();
			RadarDataSet set4 = new RadarDataSet(yVals4, "平時成績");
			set4.setColor(randomColor);
			set4.setFillColor(randomColor);
			set4.setDrawFilled(true);
			set4.setFillAlpha(180);
			set4.setLineWidth(2f);
			set4.setDrawHighlightCircleEnabled(true);
			set4.setDrawHighlightIndicators(false);

			randomColor = BeautifulColor.getRandomColor();
			RadarDataSet set5 = new RadarDataSet(yVals5, "期末平均");
			set5.setColor(randomColor);
			set5.setFillColor(randomColor);
			set5.setDrawFilled(true);
			set5.setFillAlpha(180);
			set5.setLineWidth(2f);
			set5.setDrawHighlightCircleEnabled(true);
			set5.setDrawHighlightIndicators(false);

			ArrayList<IRadarDataSet> sets = new ArrayList<>();
			sets.add(set1);
			sets.add(set2);
			sets.add(set3);
			sets.add(set4);
			sets.add(set5);

			RadarData data = new RadarData(sets);
			data.setValueTextSize(12f);
			data.setDrawValues(true);
			data.setValueTextColor(Color.BLACK);

			radarChart.setData(data);
			radarChart.invalidate();

			radarChart.animateXY(
				1300, 1300,
				Easing.EasingOption.EaseInOutQuad,
				Easing.EasingOption.EaseInOutQuad);

			final XAxis xAxis = radarChart.getXAxis();
			xAxis.setTextSize(12f);
			xAxis.setYOffset(0f);
			xAxis.setXOffset(0f);
			xAxis.setTextColor(Color.BLACK);
//			xAxis.setSpaceMax(1000f);
//			xAxis.setSpaceMin(1000f);
//			xAxis.setAxisMinimum(0f);
//			xAxis.setAxisMaximum(100f);

			final ArrayList<String> xVals = new ArrayList<>();
			for (int i = 0; i < sectionalExamResponses.size(); i++) {
				xVals.add(sectionalExamResponses.get(i).subject);
			}
			xAxis.setValueFormatter(new IAxisValueFormatter() {
				@Override
				public String getFormattedValue(float value, AxisBase axis) {
					return xVals.get(Math.abs((int) value % xVals.size()));
					//return value+"";
				}

				@Override
				public int getDecimalDigits() {
					return 0;
				}
			});

			YAxis yAxis = radarChart.getYAxis();
			yAxis.setLabelCount(11, true);
			yAxis.setTextSize(12f);
			yAxis.setAxisMinimum(0);
			yAxis.setAxisMaximum(100);
			yAxis.setTextColor(Color.BLACK);
			yAxis.setDrawLabels(false);

			LimitLine limitLine = new LimitLine(60);
			limitLine.setLabel("及格線");
			limitLine.setLineColor(Color.RED);
			limitLine.setLineWidth(2);

			LimitLine limitLine1 = new LimitLine(100);
			limitLine1.setLineColor(Color.LTGRAY);
			limitLine1.setLineWidth(2);

			yAxis.addLimitLine(limitLine);
			//yAxis.addLimitLine(limitLine1);

			Legend l = radarChart.getLegend();
			l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
			l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
			l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
			l.setDrawInside(false);
			l.setXEntrySpace(7f);
			l.setYEntrySpace(5f);
			l.setTextColor(Color.BLACK);


			radarChart.invalidate();
		}
	}
}
