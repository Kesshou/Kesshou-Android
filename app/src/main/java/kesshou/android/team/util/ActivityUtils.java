package kesshou.android.team.util;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;

import kesshou.android.team.views.ContentActivity;

/**
 * Created by yoyoIU on 2016/11/8.
 */

public class ActivityUtils {
	public static void openContent(Context context, int type, int title, int titleHelp){
		Intent intent = new Intent();
		intent.setClass(context.getApplicationContext(), ContentActivity.class);
		Bundle bundle = new Bundle();
		bundle.putInt("type", type);
		bundle.putString("title",context.getString(title));
		bundle.putString("title_help",context.getString(titleHelp));
		intent.putExtras(bundle);
		ContextCompat.startActivity(context,intent,bundle);
	}
}
