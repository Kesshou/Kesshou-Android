package kesshou.android.team.util;

import android.graphics.Color;

import java.util.Random;

/**
 * Created by yoyoIU on 2016/12/13.
 */

public class BeautifulColor {

	public static int getColor(int i){
		int[] color = {Color.parseColor("#F44336"), Color.parseColor("#FF9800"), Color.parseColor("#FFC107"), Color.parseColor("#4CAF50"), Color.parseColor("#2196F3"), Color.parseColor("#673AB7"), Color.parseColor("#03A9F4"), Color.parseColor("#4CAF50"), Color.parseColor("#FFEB3B"), Color.parseColor("#FF5722"), Color.parseColor("#607D8B"), Color.parseColor("#E91E63"), Color.parseColor("#3F51B5"), Color.parseColor("#00BCD4"), Color.parseColor("#8BC34A"), Color.parseColor("#795548"), Color.parseColor("#9C27B0"), Color.parseColor("#CDDC39"), Color.parseColor("#9E9E9E"),};
		if(i<color.length) return color[i];
		else return color[0];
	}

	public static int getRandomColor() {
		Random rnd = new Random();
		int[] color = {Color.parseColor("#F44336"), Color.parseColor("#FF9800"), Color.parseColor("#FFC107"), Color.parseColor("#4CAF50"), Color.parseColor("#2196F3"), Color.parseColor("#673AB7"), Color.parseColor("#03A9F4"), Color.parseColor("#4CAF50"), Color.parseColor("#FFEB3B"), Color.parseColor("#FF5722"), Color.parseColor("#607D8B"), Color.parseColor("#E91E63"), Color.parseColor("#3F51B5"), Color.parseColor("#00BCD4"), Color.parseColor("#8BC34A"), Color.parseColor("#795548"), Color.parseColor("#9C27B0"), Color.parseColor("#CDDC39"), Color.parseColor("#9E9E9E"),};
		return color[rnd.nextInt(color.length)];
	}
}

