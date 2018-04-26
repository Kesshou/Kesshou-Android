package kesshou.android.daanx.util.Adapter.holder;

import android.graphics.Color;
import android.view.View;

/**
 * Created by yoyoIU on 2016/10/25.
 */

public class Menu {
	public int icon;
	public String name;
	public String viewText;
	public View.OnClickListener onClickListener;
	public int color;

	public Menu(int i, String n, String vt, View.OnClickListener o){
		icon = i;
		name = n;
		viewText = vt;
		onClickListener = o;
		color = Color.GRAY;
	}

	public Menu(int i,int c, String n, String vt, View.OnClickListener o){
		icon = i;
		name = n;
		viewText = vt;
		onClickListener = o;
		color = c;
	}
}
