package kesshou.android.daanx.views.main;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.analytics.FirebaseAnalytics;

import java.util.ArrayList;

import kesshou.android.daanx.R;
import kesshou.android.daanx.util.ActivityUtils;
import kesshou.android.daanx.util.Adapter.MenuTableAdapter;
import kesshou.android.daanx.util.Adapter.TableDecoration;
import kesshou.android.daanx.util.Adapter.holder.Menu;
import kesshou.android.daanx.util.BeautifulColor;
import kesshou.android.daanx.util.ChromeTabHelper;
import kesshou.android.daanx.views.MainActivity;
import kesshou.android.daanx.views.news.CalcActivity;
import kesshou.android.daanx.views.news.NewsActivity;

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
			Menu calc = new Menu(R.drawable.ic_calc, BeautifulColor.getColor(1), getString(R.string.main_news_calc), "", new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					Bundle bundle = new Bundle();
					bundle.putString(FirebaseAnalytics.Param.ITEM_ID, "Calc");
					bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, "Calc");
					bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "fn");
					((MainActivity)getActivity()).mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.VIEW_ITEM, bundle);
					Intent intent = new Intent();
					intent.setClass(getActivity(), CalcActivity.class);
					startActivity(intent);
				}
			});
			menus.add(calc);
			Menu news = new Menu(R.drawable.ic_news,BeautifulColor.getColor(2), getString(R.string.main_news_news), "", new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					Bundle bundle = new Bundle();
					bundle.putString(FirebaseAnalytics.Param.ITEM_ID, "News");
					bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, "News");
					bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "fn");
					((MainActivity)getActivity()).mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.VIEW_ITEM, bundle);
					Intent intent = new Intent();
					intent.setClass(getActivity(), NewsActivity.class);
					startActivity(intent);
				}
			});
			menus.add(news);
			Menu map = new Menu(R.drawable.ic_map,BeautifulColor.getColor(3), getString(R.string.main_news_map), "", new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					Bundle bundle = new Bundle();
					bundle.putString(FirebaseAnalytics.Param.ITEM_ID, "School map");
					bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, "School map");
					bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "fn");
					((MainActivity)getActivity()).mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.VIEW_ITEM, bundle);
					ChromeTabHelper.openTabs(getActivity(),"http://www.taivs.tp.edu.tw/images/img-building.jpg");
				}
			});
			menus.add(map);
			Menu seatState = new Menu(R.drawable.ic_event_seat,BeautifulColor.getColor(4), getString(R.string.main_news_seatstate), "", new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					Bundle bundle = new Bundle();
					bundle.putString(FirebaseAnalytics.Param.ITEM_ID, "School map");
					bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, "School map");
					bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "fn");
					((MainActivity)getActivity()).mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.VIEW_ITEM, bundle);
					ChromeTabHelper.openTabs(getActivity(),"https://dev.dacsc.club/v1/seatstate");
				}
			});
			menus.add(seatState);
			Menu rule = new Menu(R.drawable.ic_book,BeautifulColor.getColor(5), getString(R.string.main_news_rule), "", new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					Bundle bundle = new Bundle();
					bundle.putString(FirebaseAnalytics.Param.ITEM_ID, "School rules");
					bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, "School rules");
					bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "fn");
					((MainActivity)getActivity()).mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.VIEW_ITEM, bundle);
					ChromeTabHelper.openTabs(getActivity(),"http://military.taivs.tp.edu.tw/node/51");
				}
			});
			menus.add(rule);
			Menu qAndA = new Menu(R.drawable.ic_question_answer,BeautifulColor.getColor(6), getString(R.string.main_news_qanda), "", new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					Bundle bundle = new Bundle();
					bundle.putString(FirebaseAnalytics.Param.ITEM_ID, "School Q&A");
					bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, "School Q&A");
					bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "fn");
					((MainActivity)getActivity()).mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.VIEW_ITEM, bundle);
					ActivityUtils.openContent(getActivity(),R.string.main_news_qanda,R.string.main_news_qanda,R.string.main_news_qanda_help);
				}
			});
			menus.add(qAndA);
			Menu daanAbout = new Menu(R.drawable.ic_school,BeautifulColor.getColor(7), getString(R.string.main_news_daanabout), "", new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					Bundle bundle = new Bundle();
					bundle.putString(FirebaseAnalytics.Param.ITEM_ID, "Daan about");
					bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, "Daan about");
					bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "fn");
					((MainActivity)getActivity()).mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.VIEW_ITEM, bundle);
					ActivityUtils.openContent(getActivity(),R.string.main_news_daanabout,R.string.main_news_daanabout,R.string.main_news_daanabout_help);
				}
			});
			menus.add(daanAbout);

			RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.menu_list);
			MenuTableAdapter menuListAdapter = new MenuTableAdapter(menus,getActivity().getApplicationContext());
			GridLayoutManager layoutManager = new GridLayoutManager(getActivity().getApplicationContext(),2);
			layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
			recyclerView.setLayoutManager(layoutManager);
			recyclerView.setAdapter(menuListAdapter);
			recyclerView.addItemDecoration(new TableDecoration());
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
