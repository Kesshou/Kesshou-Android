package kesshou.android.team.util;


import android.content.Context;
import android.view.View;

import kesshou.android.team.R;
import kesshou.android.team.views.Infor.Grade1Fragment;
import kesshou.android.team.views.Infor.Grade2Fragment;
import kesshou.android.team.views.Infor.PrizeFragment;
import kesshou.android.team.views.Infor.RecordFragment;
import kesshou.android.team.views.Infor.TimeTableFragment;

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
			default:
				fg = new View(context);
				break;
		}
		return fg;
	}
}
