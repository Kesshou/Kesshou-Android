package kesshou.android.team.util.component;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;

import kesshou.android.team.R;
import kesshou.android.team.util.UnitConvert;

/**
 * Created by yoyoIU on 2016/11/7.
 */

public class DialogUtils {
	/**
	 * 得到自定义的progressDialog
	 * @param context
	 * @return
	 */
	public static Dialog createLoadingDialog(Context context) {
		LayoutInflater inflater = LayoutInflater.from(context);
		View v = inflater.inflate(R.layout.loading_dialog, null);        // 得到加载view
		ImageView spaceshipImage = (ImageView) v.findViewById(R.id.img);
		Animation hyperspaceJumpAnimation = AnimationUtils.loadAnimation(context,R.anim.loading_animation); // 加载动画
		hyperspaceJumpAnimation.setInterpolator(new LinearInterpolator());
		spaceshipImage.startAnimation(hyperspaceJumpAnimation);             // 使用ImageView显示动画
		Dialog loadingDialog = new Dialog(context, R.style.loading_dialog); // 创建自定义样式dialog

		loadingDialog.setCancelable(false);// 不可以用"返回键"取消
		loadingDialog.setContentView(v);
		loadingDialog.setTitle("");

		Window dialogWindow = loadingDialog.getWindow();
		WindowManager.LayoutParams lp = dialogWindow.getAttributes();
		dialogWindow.setGravity(Gravity.CENTER);
		lp.width = (int)UnitConvert.Dp2Pixel(100,context); // 宽度
		lp.height = (int)UnitConvert.Dp2Pixel(100,context); // 高度
		dialogWindow.setAttributes(lp);
		return loadingDialog;
	}
}
