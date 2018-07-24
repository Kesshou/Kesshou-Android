package kesshou.android.daanx.views.main;


import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.analytics.FirebaseAnalytics;

import java.util.ArrayList;

import io.realm.Realm;
import kesshou.android.daanx.R;
import kesshou.android.daanx.models.Setting;
import kesshou.android.daanx.util.ActivityUtils;
import kesshou.android.daanx.util.Adapter.ListDecoration;
import kesshou.android.daanx.util.Adapter.MenuListAdapter;
import kesshou.android.daanx.util.Adapter.holder.Menu;
import kesshou.android.daanx.util.BeautifulColor;
import kesshou.android.daanx.views.MainActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class InforFragment extends Fragment {

	private View rootView;

	public InforFragment() {
		// Required empty public constructor
	}


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	                         Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		if(rootView == null) {
			rootView = inflater.inflate(R.layout.fragment_infor, container, false);

			Realm realm = Realm.getDefaultInstance();
			Setting setting = realm.where(Setting.class).findFirst();

			TextView headerImage = (TextView) rootView.findViewById(R.id.header_image);
			GradientDrawable gd = new GradientDrawable();
			gd.setColor(getClassColor(getClassStr(setting.classX.substring(0,2))));
			gd.setShape(GradientDrawable.OVAL);
			gd.setStroke(8, ContextCompat.getColor(getActivity().getApplicationContext(), R.color.white));
			if(Build.VERSION.SDK_INT<16)
				headerImage.setBackgroundDrawable(gd);
			else
				headerImage.setBackground(gd);
			headerImage.setText(getClassStr(setting.classX.substring(0,2)));
			headerImage.setTextSize(36);

			TextView headerName = (TextView) rootView.findViewById(R.id.header_name);
			headerName.setText(setting.name.trim());
			TextView headerNick = (TextView) rootView.findViewById(R.id.header_nick);
			headerNick.setText(setting.nick.trim());
			TextView headerGroup = (TextView) rootView.findViewById(R.id.header_group);
			headerGroup.setText(setting.usr_group.trim());


			ArrayList<Menu> menus = new ArrayList<>();
			if(setting.usr_group.equals("student")) {
				Menu timeTable = new Menu(R.drawable.ic_view_list, BeautifulColor.getColor(1), getString(R.string.main_infor_timetable), "", new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						Bundle bundle = new Bundle();
						bundle.putString(FirebaseAnalytics.Param.ITEM_ID, "Timetable");
						bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, "Timetable");
						bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "fn");
						((MainActivity)getActivity()).mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.VIEW_ITEM, bundle);
						ActivityUtils.openContent(getActivity(), R.string.main_infor_timetable, R.string.main_infor_timetable, R.string.main_infor_timetable_help);
					}
				});
				menus.add(timeTable);
				Menu record = new Menu(R.drawable.ic_record, BeautifulColor.getColor(2), getString(R.string.main_infor_record), "", new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						Bundle bundle = new Bundle();
						bundle.putString(FirebaseAnalytics.Param.ITEM_ID, "Record");
						bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, "Record");
						bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "fn");
						((MainActivity)getActivity()).mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.VIEW_ITEM, bundle);
						ActivityUtils.openContent(getActivity(), R.string.main_infor_record, R.string.main_infor_record, R.string.main_infor_record_help);
					}
				});
				menus.add(record);
				Menu prize = new Menu(R.drawable.ic_prize, BeautifulColor.getColor(3), getString(R.string.main_infor_prize), "", new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						Bundle bundle = new Bundle();
						bundle.putString(FirebaseAnalytics.Param.ITEM_ID, "Prize");
						bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, "Prize");
						bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "fn");
						((MainActivity)getActivity()).mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.VIEW_ITEM, bundle);
						ActivityUtils.openContent(getActivity(), R.string.main_infor_prize, R.string.main_infor_prize, R.string.main_infor_prize_help);
					}
				});
				menus.add(prize);
				Menu grade1 = new Menu(R.drawable.ic_grade1, BeautifulColor.getColor(4), getString(R.string.main_infor_grade1), "", new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						Bundle bundle = new Bundle();
						bundle.putString(FirebaseAnalytics.Param.ITEM_ID, "Section Grade");
						bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, "Section Grade");
						bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "fn");
						((MainActivity)getActivity()).mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.VIEW_ITEM, bundle);
						ActivityUtils.openContent(getActivity(), R.string.main_infor_grade1, R.string.main_infor_grade1, R.string.main_infor_grade1_help);
					}
				});
				menus.add(grade1);
				Menu grade2 = new Menu(R.drawable.ic_grade2, BeautifulColor.getColor(5), getString(R.string.main_infor_grade2), "", new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						Bundle bundle = new Bundle();
						bundle.putString(FirebaseAnalytics.Param.ITEM_ID, "Semester Grade");
						bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, "Semester Grade");
						bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "fn");
						((MainActivity)getActivity()).mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.VIEW_ITEM, bundle);
						ActivityUtils.openContent(getActivity(), R.string.main_infor_grade2, R.string.main_infor_grade2, R.string.main_infor_grade2_help);
					}
				});
				menus.add(grade2);
			}

			RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.menu_list);
			MenuListAdapter menuListAdapter = new MenuListAdapter(menus,getActivity().getApplicationContext());
			LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
			layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
			recyclerView.setLayoutManager(layoutManager);
			recyclerView.setAdapter(menuListAdapter);
			recyclerView.addItemDecoration(new ListDecoration());
			recyclerView.setItemAnimator(new DefaultItemAnimator());

			realm.close();
		}

		return rootView;
	}

	private String getClassStr(String classX){
		String result = "";
		switch (classX){
			case "電機":
				result = "電";
				break;
			case "控制":
				result = "控";
				break;
			case "電子":
				result = "子";
				break;
			case "資訊":
				result = "訊";
				break;
			case "冷凍":
				result = "冷";
				break;
			case "機械":
				result = "機";
				break;
			case "製圖":
				result = "圖";
				break;
			case "汽車":
				result = "汽";
				break;
			case "建築":
				result = "建";
				break;
			case "圖傳":
				result = "傳";
				break;
			case "綜高":
				result = "高";
				break;
			case "綜職":
			    result = "綜";
			    break;
		}
		return result;
	}

	private int getClassColor(String classX){
		switch (classX){
			case "電":
				return BeautifulColor.getColor(1);
			case "控":
				return BeautifulColor.getColor(2);
			case "冷":
				return BeautifulColor.getColor(3);
			case "訊":
				return BeautifulColor.getColor(4);
			case "機":
				return BeautifulColor.getColor(5);
			case "圖":
				return BeautifulColor.getColor(6);
			case "汽":
				return BeautifulColor.getColor(7);
			case "建":
				return BeautifulColor.getColor(8);
			case "傳":
				return BeautifulColor.getColor(9);
			case "高":
				return BeautifulColor.getColor(10);
			default:
				return BeautifulColor.getColor(11);
		}
	}

	@Override
	public void onDestroyView(){
		super.onDestroyView();
		if(rootView != null){
			((ViewGroup) rootView.getParent()).removeView(rootView);
		}
	}

}
