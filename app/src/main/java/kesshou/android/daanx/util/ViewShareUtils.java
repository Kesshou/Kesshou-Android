package kesshou.android.daanx.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.net.Uri;
import android.util.Log;
import android.view.View;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by yoyoIU on 2016/12/8.
 */


public class ViewShareUtils {
	// 获取指定Activity的截屏，保存到png文件
//	private static Bitmap takeScreenShot(Activity activity) {
//		// View是你需要截图的View
//		View view = activity.getWindow().getDecorView();
//		view.setDrawingCacheEnabled(true);
//		view.buildDrawingCache();
//		Bitmap b1 = view.getDrawingCache();
//
//		// 获取状态栏高度
//		Rect frame = new Rect();
//		activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);
//		int statusBarHeight = frame.top;
//		Log.i("TAG", "" + statusBarHeight);
//
//		// 获取屏幕长和高
//		DisplayMetrics metrics = new DisplayMetrics();
//		activity.getWindowManager().getDefaultDisplay().getMetrics(metrics);
//		int width = metrics.widthPixels;
//		int height = metrics.heightPixels;
//		// 去掉标题栏
//		// Bitmap b = Bitmap.createBitmap(b1, 0, 25, 320, 455);
//		Bitmap b = Bitmap.createBitmap(b1, 0, statusBarHeight, width, height
//			- statusBarHeight);
//		view.destroyDrawingCache();
//		return b;
//	}

	private static Bitmap loadBitmapFromView(View v) {
		Bitmap b = Bitmap.createBitmap( v.getMeasuredWidth(), v.getMeasuredHeight(), Bitmap.Config.ARGB_8888);
		Canvas c = new Canvas(b);
		c.drawColor(Color.parseColor("#FAFAFA"));
		v.layout(v.getLeft(), v.getTop(), v.getRight(), v.getBottom());
		v.draw(c);
		return b;
	}

	// 保存到sdcard
	private static void savePic(Bitmap b, String strFileName) {

		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(strFileName);
			b.compress(Bitmap.CompressFormat.JPEG, 90, fos);
			fos.flush();
			fos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 程序入口
	private static String shoot(Context context, View view) {
		String strFileName = context.getExternalCacheDir() + "/" + String.valueOf(System.currentTimeMillis()) + ".png";
		Log.d("TAG",strFileName);
		ViewShareUtils.savePic(ViewShareUtils.loadBitmapFromView(view), strFileName);
		return strFileName;
	}

	public static void share(Context context, Activity a, View view){
		Intent intent  = new Intent(Intent.ACTION_SEND);
		File file = new File(shoot(context,view));
		intent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(file));
		intent.setType("image/jpg");
		Intent chooser = Intent.createChooser(intent, "Share screen shot to");
		if(intent.resolveActivity(context.getPackageManager()) != null){
			a.startActivity(chooser);
		}
	}
}

