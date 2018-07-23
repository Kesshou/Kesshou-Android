package kesshou.android.daanx.views.main;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.crashlytics.android.Crashlytics;
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
import kesshou.android.daanx.util.LanguageUtil;
import kesshou.android.daanx.views.MainActivity;
import kesshou.android.daanx.views.StartActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class MenuFragment extends Fragment {

	private View rootView;

	public MenuFragment() {
		// Required empty public constructor
	}


	@Override
	public View onCreateView(LayoutInflater inflater, final ViewGroup container,
	                         Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		if(rootView == null) {
			rootView = inflater.inflate(R.layout.fragment_menu, container, false);


			ArrayList<Menu> menus = new ArrayList<>();
			Menu meData = new Menu(R.drawable.ic_info, BeautifulColor.getColor(1), getString(R.string.main_menu_medata), "", new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					Bundle bundle = new Bundle();
					bundle.putString(FirebaseAnalytics.Param.ITEM_ID, "meData");
					bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, "meData");
					bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "fn");
					((MainActivity)getActivity()).mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.VIEW_ITEM, bundle);
					ActivityUtils.openContent(getActivity(),R.string.main_menu_medata,R.string.main_menu_medata,R.string.main_menu_medata_help);
				}
			});
			menus.add(meData);
//			Menu wifi = new Menu(R.drawable.ic_infor, getString(R.string.main_meun_wifi), "", new View.OnClickListener() {
//				@Override
//				public void onClick(View v) {
//					Toast.makeText(getActivity().getApplicationContext(), "i am touch record", Toast.LENGTH_SHORT).show();
//				}
//			});
//			menus.add(wifi);
			Menu noti = new Menu(R.drawable.ic_notifications,BeautifulColor.getColor(2), getString(R.string.main_menu_noti), "", new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					Bundle bundle = new Bundle();
					bundle.putString(FirebaseAnalytics.Param.ITEM_ID, "noti");
					bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, "noti");
					bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "fn");
					((MainActivity)getActivity()).mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.VIEW_ITEM, bundle);
					ActivityUtils.openContent(getActivity(),R.string.main_menu_noti,R.string.main_menu_noti,R.string.main_menu_noti_help);
				}
			});
			menus.add(noti);
			Menu opinion = new Menu(R.drawable.ic_feedback,BeautifulColor.getColor(3), getString(R.string.main_menu_opinion), "", new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					Bundle bundle = new Bundle();
					bundle.putString(FirebaseAnalytics.Param.ITEM_ID, "Feedback");
					bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, "Feedback");
					bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "fn");
					((MainActivity)getActivity()).mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.VIEW_ITEM, bundle);
					ActivityUtils.openContent(getActivity(),R.string.main_menu_opinion,R.string.main_menu_opinion,R.string.main_menu_opinion_help);
				}
			});
			menus.add(opinion);
			Menu language = new Menu(R.drawable.ic_language, BeautifulColor.getColor(5), getString(R.string.main_menu_lang), "", new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					final String[] single_list = {"Auto","正體中文", "English"};
					int chosen = 0;

					AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
					builder.setTitle("選擇語言 (Set Language)");
					Realm realm = Realm.getDefaultInstance();
					Setting setting = realm.where(Setting.class).findFirst();

					switch (setting.locale) {
						case "Auto":
							chosen = 0;
							break;
						case "正體中文":
							chosen = 1;
							break;
						case "English":
							chosen = 2;
							break;
					}
					realm.close();

					builder.setSingleChoiceItems(single_list, chosen, new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {

							String str = single_list[which];

							Realm realm = Realm.getDefaultInstance();
							Setting setting = realm.where(Setting.class).findFirst();
							realm.beginTransaction();
							setting.locale = str;
							realm.commitTransaction();
							realm.close();


							dialog.dismiss();

							LanguageUtil.restart(getActivity());
						}
					});

					AlertDialog dialog = builder.create();
					dialog.show();
				}
			});
			menus.add(language);
			Menu logout = new Menu(R.drawable.ic_close,BeautifulColor.getColor(4), getString(R.string.main_menu_logout), "", new View.OnClickListener() {
				@Override
				public void onClick(View view) {
					Bundle bundle = new Bundle();
					bundle.putString(FirebaseAnalytics.Param.ITEM_ID, "Logout");
					bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, "Logout");
					bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "fn");
					((MainActivity)getActivity()).mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.VIEW_ITEM, bundle);
					AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
					builder.setMessage("是否登出?");
					builder.setTitle("登出");
					builder.setPositiveButton("確定", new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {
							Realm realm = Realm.getDefaultInstance();
							Setting setting = realm.where(Setting.class).findFirst();
							realm.beginTransaction();
							setting.logined=false;
							realm.commitTransaction();
							realm.close();

							Intent intent = new Intent();
							intent.setClass(getActivity(), StartActivity.class);
							startActivity(intent);
							getActivity().finish();
						}
					});
					builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {

						}
					});
					builder.create().show();
				}
			});
			menus.add(logout);

			// Crashlytics test code
            // FIXME: Remove before merge
			Menu crashTest = new Menu(R.drawable.ic_close,BeautifulColor.getRandomColor(),"Force Crashlytics Crash", "", new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Crashlytics.getInstance().crash();
                }
            });
			menus.add(crashTest);

			RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.menu_list);
			MenuListAdapter menuListAdapter = new MenuListAdapter(menus,getActivity().getApplicationContext());
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
