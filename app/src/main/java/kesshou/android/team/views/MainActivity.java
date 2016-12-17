package kesshou.android.team.views;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;

import kesshou.android.team.R;
import kesshou.android.team.util.Adapter.FragmentViewPagerAdapter;
import kesshou.android.team.views.main.ForumFragment;
import kesshou.android.team.views.main.InforFragment;
import kesshou.android.team.views.main.MenuFragment;
import kesshou.android.team.views.main.NewsFragment;

/*
    Author: Charles Lien (lienching)
    Description: This is the main activity of this app.
*/

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
	    setContentView(R.layout.activity_main);
	    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
	    toolbar.setElevation(0);
	    toolbar.setPopupTheme(R.style.AppTheme);
	    toolbar.setBackgroundResource(R.color.colorPrimary);
	    toolbar.setTitle(getTitle());
	    toolbar.setTitleTextColor(Color.WHITE);
	    //getSupportActionBar().setElevation(0);

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
	    ((ImageView)imgInfor.findViewById(R.id.tab)).setImageResource(R.drawable.btn_infor_selector);
	    tabLayout.getTabAt(0).setCustomView(imgInfor);
	    View imgForum =  getLayoutInflater().inflate(R.layout.custom_tab,null);
	    ((ImageView)imgForum.findViewById(R.id.tab)).setImageResource(R.drawable.btn_forum_selector);
	    tabLayout.getTabAt(1).setCustomView(imgForum);
	    View imgNews =  getLayoutInflater().inflate(R.layout.custom_tab,null);
	    ((ImageView)imgNews.findViewById(R.id.tab)).setImageResource(R.drawable.btn_new_selector);
	    tabLayout.getTabAt(2).setCustomView(imgNews);
	    View imgMenu =  getLayoutInflater().inflate(R.layout.custom_tab,null);
	    ((ImageView)imgMenu.findViewById(R.id.tab)).setImageResource(R.drawable.btn_menu_selector);
	    tabLayout.getTabAt(3).setCustomView(imgMenu);
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
