package kesshou.android.team.util;

import android.content.Context;
import android.util.DisplayMetrics;

/**
 * Created by yoyoIU on 2016/11/7.
 */

public class UnitConvert {

	/**
	 * Covert dp to px
	 * @param dp
	 * @param context
	 * @return pixel
	 */
	public static float Dp2Pixel(float dp, Context context){
		float px = dp * getDensity(context.getApplicationContext());
		return px;
	}
	/**
	 * Covert px to dp
	 * @param px
	 * @param context
	 * @return dp
	 */
	public static float Pixel2Dp(float px, Context context){
		float dp = px / getDensity(context.getApplicationContext());
		return dp;
	}
	/**
	 * 取得螢幕密度
	 * 120dpi = 0.75
	 * 160dpi = 1 (default)
	 * 240dpi = 1.5
	 * @param context
	 * @return
	 */
	public static float getDensity(Context context){
		DisplayMetrics metrics = context.getApplicationContext().getResources().getDisplayMetrics();
		return metrics.density;
	}

	public static float getScreenWidth(Context context){
		DisplayMetrics metrics = context.getApplicationContext().getResources().getDisplayMetrics();
		return metrics.widthPixels;
	}

	public static float getScreenHeight(Context context){
		DisplayMetrics metrics = context.getApplicationContext().getResources().getDisplayMetrics();
		return metrics.heightPixels;
	}
}
