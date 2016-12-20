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

import java.util.ArrayList;

import io.realm.Realm;
import kesshou.android.daanx.R;
import kesshou.android.daanx.models.Setting;
import kesshou.android.daanx.util.ActivityUtils;
import kesshou.android.daanx.util.Adapter.ListDecoration;
import kesshou.android.daanx.util.Adapter.MenuListAdapter;
import kesshou.android.daanx.util.Adapter.holder.Menu;
import kesshou.android.daanx.util.BeautifulColor;

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
			gd.setColor(getClassColor(String.valueOf(setting.classX.charAt(0))));
			gd.setShape(GradientDrawable.OVAL);
			gd.setStroke(8, ContextCompat.getColor(getActivity().getApplicationContext(), R.color.white));
			if(Build.VERSION.SDK_INT<16)
				headerImage.setBackgroundDrawable(gd);
			else
				headerImage.setBackground(gd);
			headerImage.setText(String.valueOf(setting.classX.charAt(0)));
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
						ActivityUtils.openContent(getActivity(), R.string.main_infor_timetable, R.string.main_infor_timetable, R.string.main_infor_timetable_help);
					}
				});
				menus.add(timeTable);
				Menu record = new Menu(R.drawable.ic_record, BeautifulColor.getColor(2), getString(R.string.main_infor_record), "", new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						ActivityUtils.openContent(getActivity(), R.string.main_infor_record, R.string.main_infor_record, R.string.main_infor_record_help);
					}
				});
				menus.add(record);
				Menu prize = new Menu(R.drawable.ic_prize, BeautifulColor.getColor(3), getString(R.string.main_infor_prize), "", new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						ActivityUtils.openContent(getActivity(), R.string.main_infor_prize, R.string.main_infor_prize, R.string.main_infor_prize_help);
					}
				});
				menus.add(prize);
				Menu grade1 = new Menu(R.drawable.ic_grade1, BeautifulColor.getColor(4), getString(R.string.main_infor_grade1), "", new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						ActivityUtils.openContent(getActivity(), R.string.main_infor_grade1, R.string.main_infor_grade1, R.string.main_infor_grade1_help);
					}
				});
				menus.add(grade1);
				Menu grade2 = new Menu(R.drawable.ic_grade2, BeautifulColor.getColor(5), getString(R.string.main_infor_grade2), "", new View.OnClickListener() {
					@Override
					public void onClick(View v) {
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

	private int getClassColor(String classX){
		switch (classX){
			case "電":
				return BeautifulColor.getColor(1);
			case "控":
				return BeautifulColor.getColor(2);
			case "冷":
				return BeautifulColor.getColor(3);
			case "資":
				return BeautifulColor.getColor(4);
			case "機":
				return BeautifulColor.getColor(5);
			case "製":
				return BeautifulColor.getColor(6);
			case "汽":
				return BeautifulColor.getColor(7);
			case "建":
				return BeautifulColor.getColor(8);
			case "圖":
				return BeautifulColor.getColor(9);
			case "綜":
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
