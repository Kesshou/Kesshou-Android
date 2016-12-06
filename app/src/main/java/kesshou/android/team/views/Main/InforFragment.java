package kesshou.android.team.views.Main;


import android.graphics.drawable.GradientDrawable;
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
import kesshou.android.team.R;
import kesshou.android.team.models.Setting;
import kesshou.android.team.util.ActivityUtils;
import kesshou.android.team.util.Adapter.ListDecoration;
import kesshou.android.team.util.Adapter.MenuListAdapter;
import kesshou.android.team.util.Adapter.holder.Menu;

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
			gd.setColor(ContextCompat.getColor(getActivity().getApplicationContext(), R.color.blue_500));
			gd.setShape(GradientDrawable.OVAL);
			gd.setStroke(8, ContextCompat.getColor(getActivity().getApplicationContext(), R.color.white));
			headerImage.setBackground(gd);
			headerImage.setText(setting.classX.charAt(0)+"");
			headerImage.setTextSize(48);

			TextView headerName = (TextView) rootView.findViewById(R.id.header_name);
			headerName.setText(setting.name);


			ArrayList<Menu> menus = new ArrayList<>();
			Menu timeTable = new Menu(R.drawable.ic_infor_gray, getString(R.string.main_infor_timetable), "", new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					ActivityUtils.openContent(getActivity(),R.string.main_infor_timetable,R.string.main_infor_timetable,R.string.main_infor_timetable_help);
				}
			});
			menus.add(timeTable);
			Menu record = new Menu(R.drawable.ic_infor_accent, getString(R.string.main_infor_record), "", new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					ActivityUtils.openContent(getActivity(),R.string.main_infor_record,R.string.main_infor_record,R.string.main_infor_record_help);
				}
			});
			menus.add(record);
			Menu prize = new Menu(R.drawable.ic_infor_gray, getString(R.string.main_infor_prize), "", new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					ActivityUtils.openContent(getActivity(),R.string.main_infor_prize,R.string.main_infor_prize,R.string.main_infor_prize_help);
				}
			});
			menus.add(prize);
			Menu grade1 = new Menu(R.drawable.ic_infor_accent, getString(R.string.main_infor_grade1), "", new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					ActivityUtils.openContent(getActivity(),R.string.main_infor_grade1,R.string.main_infor_grade1,R.string.main_infor_grade1_help);
				}
			});
			menus.add(grade1);
			Menu grade2 = new Menu(R.drawable.ic_infor_accent, getString(R.string.main_infor_grade2), "", new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					ActivityUtils.openContent(getActivity(),R.string.main_infor_grade2,R.string.main_infor_grade2,R.string.main_infor_grade2_help);
				}
			});
			menus.add(grade2);

			RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.menu_list);
			MenuListAdapter menuListAdapter = new MenuListAdapter(menus);
			LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
			layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
			recyclerView.setLayoutManager(layoutManager);
			recyclerView.setAdapter(menuListAdapter);
			recyclerView.addItemDecoration(new ListDecoration());
			recyclerView.setItemAnimator(new DefaultItemAnimator());

		}

		return rootView;
	}

	@Override
	public void onDestroyView(){
		super.onDestroyView();
		if(rootView != null){
			((ViewGroup) rootView.getParent()).removeView(rootView);
		}
	}

}
