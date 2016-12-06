package kesshou.android.team.util.component;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import kesshou.android.team.R;


/**
 * Created by yoyoIU on 2016/11/7.
 */

public class ToastUtils {

	private static Toast toast;

	public static void makeTextAndShow(Context context,String text, int duration,int gravity) {
		if (toast == null) {
			//如果還沒有用過makeText方法，才使用
			toast = new Toast(context);
			LayoutInflater inflater = LayoutInflater.from(context);
			View view = inflater.inflate(R.layout.toast_layout,null);
			toast.setView(view);
		}
		((TextView)toast.getView().findViewById(R.id.noti_text)).setText(text);
		toast.setDuration(duration);
		toast.setGravity(gravity,0,0);
		toast.show();
	}

}
