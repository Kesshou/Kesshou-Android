package kesshou.android.team.views;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import java.util.ArrayList;

import kesshou.android.team.R;
import kesshou.android.team.util.Adapter.FragmentViewPagerAdapter;
import kesshou.android.team.views.Main.ForumFragment;
import kesshou.android.team.views.Main.InforFragment;
import kesshou.android.team.views.Main.MenuFragment;
import kesshou.android.team.views.Main.NewsFragment;

/*
    Author: Charles Lien (lienching)
    Description: This is the main activity of this app.
*/

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
	    setContentView(R.layout.activity_main);
	    getSupportActionBar().setElevation(0);

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

	    ImageView imgInfor = (ImageView) getLayoutInflater().inflate(R.layout.custom_tab,null);
	    imgInfor.setImageDrawable(getDrawable(R.drawable.btn_infor_selector));
	    tabLayout.getTabAt(0).setCustomView(imgInfor);
	    ImageView imgForum = (ImageView) getLayoutInflater().inflate(R.layout.custom_tab,null);
	    imgForum.setImageDrawable(getDrawable(R.drawable.btn_forum_selector));
	    tabLayout.getTabAt(1).setCustomView(imgForum);
	    ImageView imgNews = (ImageView) getLayoutInflater().inflate(R.layout.custom_tab,null);
	    imgNews.setImageDrawable(getDrawable(R.drawable.btn_new_selector));
	    tabLayout.getTabAt(2).setCustomView(imgNews);
	    ImageView imgMenu = (ImageView) getLayoutInflater().inflate(R.layout.custom_tab,null);
	    imgMenu.setImageDrawable(getDrawable(R.drawable.btn_menu_selector));
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
