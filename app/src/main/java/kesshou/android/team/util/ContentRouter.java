package kesshou.android.team.util;


import android.content.Context;
import android.view.View;

import kesshou.android.team.R;
import kesshou.android.team.views.infor.Grade1Fragment;
import kesshou.android.team.views.infor.Grade2Fragment;
import kesshou.android.team.views.infor.PrizeFragment;
import kesshou.android.team.views.infor.RecordFragment;
import kesshou.android.team.views.infor.TimeTableFragment;
import kesshou.android.team.views.menu.FeedBackFragment;
import kesshou.android.team.views.news.CalcFragment;
import kesshou.android.team.views.news.LinkFragment;
import kesshou.android.team.views.news.QandAFragment;

/**
 * Created by yoyoIU on 2016/11/8.
 */

public class ContentRouter {
	public static View getFragment(int type, Context context){
		View fg;
		switch (type){
			case R.string.main_infor_timetable:
				fg = new TimeTableFragment().onCreateView(context);
				break;
			case R.string.main_infor_record:
				fg = new RecordFragment().onCreateView(context);
				break;
			case R.string.main_infor_prize:
				fg = new PrizeFragment().onCreateView(context);
				break;
			case R.string.main_infor_grade1:
				fg = new Grade1Fragment().onCreateView(context);
				break;
			case R.string.main_infor_grade2:
				fg = new Grade2Fragment().onCreateView(context);
				break;
			case R.string.main_news_calc:
				fg = new CalcFragment().onCreateView(context);
				break;
			case R.string.main_news_qanda:
				fg = new QandAFragment().onCreateView(context);
				break;
			case R.string.main_news_daanabout:
				fg = new LinkFragment().onCreateView(context);
				break;
			case R.string.main_menu_opinion:
				fg = new FeedBackFragment().onCreateView(context);
				break;
			default:
				fg = new View(context);
				break;
		}
		return fg;
	}
}