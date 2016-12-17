package kesshou.android.team.views.main;


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
import android.widget.Toast;

import java.util.ArrayList;

import io.realm.Realm;
import kesshou.android.team.R;
import kesshou.android.team.models.Setting;
import kesshou.android.team.util.ActivityUtils;
import kesshou.android.team.util.Adapter.MenuTableAdapter;
import kesshou.android.team.util.Adapter.TableDecoration;
import kesshou.android.team.util.Adapter.holder.Menu;
import kesshou.android.team.views.StartActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class MenuFragment extends Fragment {

	private View rootView;

	public MenuFragment() {
		// Required empty public constructor
	}


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	                         Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		if(rootView == null) {
			rootView = inflater.inflate(R.layout.fragment_menu, container, false);


			ArrayList<Menu> menus = new ArrayList<>();
			Menu meData = new Menu(R.drawable.ic_infor_gray, "個人資料", "", new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					Toast.makeText(getActivity().getApplicationContext(), "i am touch timetable", Toast.LENGTH_SHORT).show();
				}
			});
			menus.add(meData);
			Menu wifi = new Menu(R.drawable.ic_infor_accent, "自動連線學校Wi-Fi", "", new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					Toast.makeText(getActivity().getApplicationContext(), "i am touch record", Toast.LENGTH_SHORT).show();
				}
			});
			menus.add(wifi);
			Menu noti = new Menu(R.drawable.ic_infor_gray, "通知設定", "", new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					Toast.makeText(getActivity().getApplicationContext(), "i am touch prize", Toast.LENGTH_SHORT).show();
				}
			});
			menus.add(noti);
			Menu opinion = new Menu(R.drawable.ic_infor_accent, getString(R.string.main_menu_opinion), "", new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					ActivityUtils.openContent(getActivity(),R.string.main_menu_opinion,R.string.main_menu_opinion,R.string.main_menu_opinion_help);
				}
			});
			menus.add(opinion);
			Menu logout = new Menu(R.drawable.ic_infor_accent, "登出", "", new View.OnClickListener() {
				@Override
				public void onClick(View view) {

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
			menus.add(logout);

			RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.menu_list);
			MenuTableAdapter menuListAdapter = new MenuTableAdapter(menus);
			GridLayoutManager layoutManager = new GridLayoutManager(getActivity().getApplicationContext(), 2);
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
