package kesshou.android.team.views.news;


import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import com.github.tibolte.agendacalendarview.AgendaCalendarView;
import com.github.tibolte.agendacalendarview.CalendarPickerController;
import com.github.tibolte.agendacalendarview.models.BaseCalendarEvent;
import com.github.tibolte.agendacalendarview.models.CalendarEvent;
import com.github.tibolte.agendacalendarview.models.DayItem;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import io.realm.Realm;
import kesshou.android.team.R;
import kesshou.android.team.models.NetWorkCache;
import kesshou.android.team.util.BeautifulColor;
import kesshou.android.team.util.network.MyCallBack;
import kesshou.android.team.util.network.NetworkingClient;
import kesshou.android.team.util.network.api.holder.CalenderResponse;
import kesshou.android.team.util.network.api.holder.Error;
import retrofit2.Response;

import static com.github.mikephil.charting.charts.Chart.LOG_TAG;

/**
 * A simple class.
 */
public class CalcFragment implements CalendarPickerController{


	public CalcFragment() {
		// Required empty public constructor
	}


	public View onCreateView(final Context context) {
		// Inflate the layout for this fragment
		final View view = LayoutInflater.from(context.getApplicationContext()).inflate(R.layout.fragment_calc,null);

		Realm realm = Realm.getDefaultInstance();

		NetWorkCache netWorkCache = realm.where(NetWorkCache.class).findFirst();

		final NetworkingClient networkingClient = new NetworkingClient(context.getApplicationContext());

		final Gson gson = new Gson();

		final AgendaCalendarView calendarView = (AgendaCalendarView) view.findViewById(R.id.agenda_calendar_view);

		if(netWorkCache.calc==null){
			networkingClient.getCalender(new MyCallBack<List<CalenderResponse>>(context) {
				@Override
				public void onSuccess(Response<List<CalenderResponse>> response) {
					Realm realm = Realm.getDefaultInstance();
					realm.beginTransaction();
					NetWorkCache netWorkCache = realm.where(NetWorkCache.class).findFirst();
					netWorkCache.calc=gson.toJson(response.body());
					realm.commitTransaction();
					drawBody(calendarView,response.body());
				}

				@Override
				public void onErr(Error error) {

				}
			});
		}else{
			Type listType = new TypeToken<List<CalenderResponse>>() {}.getType();
			List<CalenderResponse> calenders = gson.fromJson(netWorkCache.calc,listType);
			drawBody(calendarView,calenders);

			networkingClient.getCalender(new MyCallBack<List<CalenderResponse>>(context) {
				@Override
				public void onSuccess(Response<List<CalenderResponse>> response) {
					Realm realm = Realm.getDefaultInstance();
					realm.beginTransaction();
					NetWorkCache netWorkCache = realm.where(NetWorkCache.class).findFirst();
					netWorkCache.calc=gson.toJson(response.body());
					realm.commitTransaction();
					drawBody(calendarView,response.body());
				}

				@Override
				public void onErr(Error error) {

				}
			});
		}

		realm.close();

		return view;
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
