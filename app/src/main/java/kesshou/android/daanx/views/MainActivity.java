
package kesshou.android.daanx.views;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.content.res.AppCompatResources;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.google.firebase.analytics.FirebaseAnalytics;

import java.util.ArrayList;

import kesshou.android.daanx.BaseActivity;
import kesshou.android.daanx.R;
import kesshou.android.daanx.util.Adapter.FragmentViewPagerAdapter;
import kesshou.android.daanx.views.main.ForumFragment;
import kesshou.android.daanx.views.main.InforFragment;
import kesshou.android.daanx.views.main.MenuFragment;
import kesshou.android.daanx.views.main.NewsFragment;

/*
    Author: Charles Lien (lienching)
    Description: This is the main activity of this app.
*/

public class MainActivity extends BaseActivity {

	public FirebaseAnalytics mFirebaseAnalytics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
	    setContentView(R.layout.activity_main);
	    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
	    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) toolbar.setElevation(0);
	    toolbar.setPopupTheme(R.style.AppTheme);
	    toolbar.setBackgroundResource(R.color.colorPrimary);
	    toolbar.setTitle(getTitle());
	    toolbar.setTitleTextColor(Color.WHITE);
	    //getSupportActionBar().setElevation(0);

	    // Obtain the FirebaseAnalytics instance.
	    mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);

	    ViewPager viewPager=(ViewPager) findViewById(R.id.container);
	    ArrayList<Fragment> fragments= new ArrayList<Fragment>() {};
	    fragments.add(new InforFragment());
	    fragments.add(new ForumFragment());
	    fragments.add(new NewsFragment());
	    fragments.add(new MenuFragment());
	    FragmentViewPagerAdapter fragmentViewPagerAdapter=new FragmentViewPagerAdapter(getSupportFragmentManager(),fragments);
	    viewPager.setAdapter(fragmentViewPagerAdapter);

	    TabLayout tabLayout=(TabLayout) findViewById(R.id.tabs);
	    tabLayout.setupWithViewPager(viewPager);


	    View imgInfor = getLayoutInflater().inflate(R.layout.custom_tab,null);
	    ((ImageView)imgInfor.findViewById(R.id.tab)).setImageDrawable(tintDrawable(AppCompatResources.getDrawable(getApplicationContext(),R.drawable.ic_infor),ColorStateList.valueOf(Color.parseColor("#1565C0"))));
	    tabLayout.getTabAt(0).setCustomView(imgInfor);
	    View imgForum =  getLayoutInflater().inflate(R.layout.custom_tab,null);
	    ((ImageView)imgForum.findViewById(R.id.tab)).setImageDrawable(tintDrawable(AppCompatResources.getDrawable(getApplicationContext(),R.drawable.ic_forum),ColorStateList.valueOf(Color.parseColor("#BDBDBD"))));
	    tabLayout.getTabAt(1).setCustomView(imgForum);
	    View imgNews =  getLayoutInflater().inflate(R.layout.custom_tab,null);
	    ((ImageView)imgNews.findViewById(R.id.tab)).setImageDrawable(tintDrawable(AppCompatResources.getDrawable(getApplicationContext(),R.drawable.ic_menu),ColorStateList.valueOf(Color.parseColor("#BDBDBD"))));
	    tabLayout.getTabAt(2).setCustomView(imgNews);
	    View imgMenu =  getLayoutInflater().inflate(R.layout.custom_tab,null);
	    ((ImageView)imgMenu.findViewById(R.id.tab)).setImageDrawable(tintDrawable(AppCompatResources.getDrawable(getApplicationContext(),R.drawable.ic_settings),ColorStateList.valueOf(Color.parseColor("#BDBDBD"))));
	    tabLayout.getTabAt(3).setCustomView(imgMenu);

		tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
			@Override
			public void onTabSelected(TabLayout.Tab tab) {
				if(tab.getCustomView()!=null)
				((ImageView)tab.getCustomView().findViewById(R.id.tab)).setImageDrawable(tintDrawable(((ImageView)tab.getCustomView().findViewById(R.id.tab)).getDrawable(),ColorStateList.valueOf(Color.parseColor("#1565C0"))));
			}

			@Override
			public void onTabUnselected(TabLayout.Tab tab) {
				if(tab.getCustomView()!=null)
				((ImageView)tab.getCustomView().findViewById(R.id.tab)).setImageDrawable(tintDrawable(((ImageView)tab.getCustomView().findViewById(R.id.tab)).getDrawable(),ColorStateList.valueOf(Color.parseColor("#BDBDBD"))));
			}

			@Override
			public void onTabReselected(TabLayout.Tab tab) {

			}
		});
    }

	public static Drawable tintDrawable(Drawable drawable, ColorStateList colors) {
		final Drawable wrappedDrawable = DrawableCompat.wrap(drawable);
		DrawableCompat.setTintList(wrappedDrawable, colors);
		return wrappedDrawable;
	}

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }
}
