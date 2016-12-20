package kesshou.android.daanx.views.news;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.github.tibolte.agendacalendarview.AgendaCalendarView;
import com.github.tibolte.agendacalendarview.CalendarPickerController;
import com.github.tibolte.agendacalendarview.models.BaseCalendarEvent;
import com.github.tibolte.agendacalendarview.models.CalendarEvent;
import com.github.tibolte.agendacalendarview.models.DayItem;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import io.realm.Realm;
import kesshou.android.daanx.R;
import kesshou.android.daanx.models.NetWorkCache;
import kesshou.android.daanx.util.BeautifulColor;
import kesshou.android.daanx.util.ViewShareUtils;
import kesshou.android.daanx.util.network.MyCallBack;
import kesshou.android.daanx.util.network.NetworkingClient;
import kesshou.android.daanx.util.network.api.holder.CalenderResponse;
import kesshou.android.daanx.util.network.api.holder.Error;
import retrofit2.Call;
import retrofit2.Response;

import static com.github.mikephil.charting.charts.Chart.LOG_TAG;

public class CalcActivity extends AppCompatActivity  implements CalendarPickerController {


	private FirebaseAnalytics mFirebaseAnalytics;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_calc);
		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
		toolbar.setNavigationIcon(R.drawable.btn_back_white);
		toolbar.setNavigationOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				finish();
			}
		});
		toolbar.inflateMenu(R.menu.content_toolbar_menu_white);
		toolbar.setTitle(R.string.main_news_calc);

		// Obtain the FirebaseAnalytics instance.
		mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);

		Realm realm = Realm.getDefaultInstance();

		NetWorkCache netWorkCache = realm.where(NetWorkCache.class).findFirst();

		final NetworkingClient networkingClient = new NetworkingClient(getApplicationContext());

		final Gson gson = new Gson();

		final AgendaCalendarView calendarView = (AgendaCalendarView) findViewById(R.id.agenda_calendar_view);

		if(netWorkCache.calc==null){
			networkingClient.getCalender(new MyCallBack<List<CalenderResponse>>(getApplicationContext()) {
				@Override
				public void onSuccess(Response<List<CalenderResponse>> response) {
					Realm realm = Realm.getDefaultInstance();
					realm.beginTransaction();
					NetWorkCache netWorkCache = realm.where(NetWorkCache.class).findFirst();
					netWorkCache.calc=gson.toJson(response.body());
					realm.commitTransaction();
					realm.close();
					drawBody(calendarView,response.body());
				}

				@Override
				public void onErr(Error error, Call<List<CalenderResponse>> call) {

				}
			});
		}else{
			Type listType = new TypeToken<List<CalenderResponse>>() {}.getType();
			List<CalenderResponse> calenders = gson.fromJson(netWorkCache.calc,listType);
			drawBody(calendarView,calenders);

			networkingClient.getCalender(new MyCallBack<List<CalenderResponse>>(getApplicationContext()) {
				@Override
				public void onSuccess(Response<List<CalenderResponse>> response) {
					Realm realm = Realm.getDefaultInstance();
					realm.beginTransaction();
					NetWorkCache netWorkCache = realm.where(NetWorkCache.class).findFirst();
					netWorkCache.calc=gson.toJson(response.body());
					realm.commitTransaction();
					realm.close();
					drawBody(calendarView,response.body());
				}

				@Override
				public void onErr(Error error, Call<List<CalenderResponse>> call) {

				}
			});
		}

		realm.close();

		toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
			@Override
			public boolean onMenuItemClick(MenuItem item) {
				switch (item.getItemId()){
					case R.id.menu_share:
						ViewShareUtils.share(getApplicationContext(),CalcActivity.this,findViewById(R.id.agenda_calendar_view));
						break;
				}
				return false;
			}
		});
	}


	private void drawBody(AgendaCalendarView calendarView,List<CalenderResponse> calenders){
		Calendar minDate = Calendar.getInstance();
		Calendar maxDate = Calendar.getInstance();

		minDate.setTime(calenders.get(calenders.size()-1).start);
		maxDate.setTime(calenders.get(0).end);

		List<CalendarEvent> eventList = new ArrayList<>();
		mockList(eventList,calenders);

		calendarView.init(eventList, minDate, maxDate, Locale.getDefault(), this);
	}

	private void mockList(List<CalendarEvent> eventList,List<CalenderResponse> calenders) {
		for(int i=0;i<calenders.size();i++) {
			Calendar startTime = Calendar.getInstance();
			Calendar endTime = Calendar.getInstance();
			startTime.setTime(calenders.get(i).start);
			endTime.setTime(calenders.get(i).end);
			BaseCalendarEvent event1 = new BaseCalendarEvent(calenders.get(i).content, "", "",
				BeautifulColor.getRandomColor(), startTime, endTime, true);
			eventList.add(event1);
		}
	}

	// region Interface - CalendarPickerController

	@Override
	public void onDaySelected(DayItem dayItem) {
		Log.d(LOG_TAG, String.format("Selected day: %s", dayItem));
	}

	@Override
	public void onEventSelected(CalendarEvent event) {
		Log.d(LOG_TAG, String.format("Selected event: %s", event));
	}

	@Override
	public void onScrollToDate(Calendar calendar) {

	}

	// endregion

	// region Private Methods
}
