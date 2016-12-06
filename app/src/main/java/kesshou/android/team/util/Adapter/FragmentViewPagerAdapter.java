package kesshou.android.team.util.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yoyoIU on 2016/10/17.
 */

public class FragmentViewPagerAdapter extends FragmentPagerAdapter {
	private List<Fragment> fragments;

	public FragmentViewPagerAdapter(FragmentManager manager, ArrayList<Fragment> fragments) {
		super(manager);
		this.fragments = fragments;

	}

	@Override
	public Fragment getItem(int position) {
		return fragments.get(position);
	}

	@Override
	public int getCount() {
		return fragments.size();
	}

}
