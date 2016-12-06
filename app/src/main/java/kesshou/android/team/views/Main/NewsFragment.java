package kesshou.android.team.views.Main;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;

import kesshou.android.team.R;
import kesshou.android.team.util.Adapter.ListDecoration;
import kesshou.android.team.util.Adapter.MenuListAdapter;
import kesshou.android.team.util.Adapter.holder.Menu;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewsFragment extends Fragment {

	private View rootView;

	public NewsFragment() {
		// Required empty public constructor
	}


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	                         Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		if(rootView == null) {
			rootView = inflater.inflate(R.layout.fragment_news, container, false);

			ArrayList<Menu> menus = new ArrayList<>();
			Menu calc = new Menu(R.drawable.ic_infor_gray, getString(R.string.main_news_calc), "", new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					Toast.makeText(getActivity().getApplicationContext(), "i am touch timetable", Toast.LENGTH_SHORT).show();
				}
			});
			menus.add(calc);
			Menu news = new Menu(R.drawable.ic_infor_accent, getString(R.string.main_news_news), "", new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					Toast.makeText(getActivity().getApplicationContext(), "i am touch record", Toast.LENGTH_SHORT).show();
				}
			});
			menus.add(news);
			Menu map = new Menu(R.drawable.ic_infor_gray, getString(R.string.main_news_map), "", new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					Toast.makeText(getActivity().getApplicationContext(), "i am touch prize", Toast.LENGTH_SHORT).show();
				}
			});
			menus.add(map);
			Menu foodMap = new Menu(R.drawable.ic_infor_accent, getString(R.string.main_news_foodmap), "", new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					Toast.makeText(getActivity().getApplicationContext(), "i am touch grade", Toast.LENGTH_SHORT).show();
				}
			});
			menus.add(foodMap);
			Menu seatState = new Menu(R.drawable.ic_infor_accent, getString(R.string.main_news_seatstate), "", new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					Toast.makeText(getActivity().getApplicationContext(), "i am touch grade", Toast.LENGTH_SHORT).show();
				}
			});
			menus.add(seatState);
			Menu rule = new Menu(R.drawable.ic_infor_accent, getString(R.string.main_news_rule), "", new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					Toast.makeText(getActivity().getApplicationContext(), "i am touch grade", Toast.LENGTH_SHORT).show();
				}
			});
			menus.add(rule);
			Menu qAndA = new Menu(R.drawable.ic_infor_accent, getString(R.string.main_news_qanda), "", new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					Toast.makeText(getActivity().getApplicationContext(), "i am touch grade", Toast.LENGTH_SHORT).show();
				}
			});
			menus.add(qAndA);
			Menu daanAbout = new Menu(R.drawable.ic_infor_accent, getString(R.string.main_news_daanabout), "", new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					Toast.makeText(getActivity().getApplicationContext(), "i am touch grade", Toast.LENGTH_SHORT).show();
				}
			});
			menus.add(daanAbout);

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
