package kesshou.android.team.util.Adapter.holder;

import android.view.View;

/**
 * Created by yoyoIU on 2016/10/25.
 */

public class Menu {
	public int icon;
	public String name;
	public String viewText;
	public View.OnClickListener onClickListener;

	public Menu(int i, String n, String vt, View.OnClickListener o){
		icon = i;
		name = n;
		viewText = vt;
		onClickListener = o;
	}
}
