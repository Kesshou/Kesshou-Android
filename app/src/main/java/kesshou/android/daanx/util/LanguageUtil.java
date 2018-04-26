package kesshou.android.daanx.util;

import android.app.Activity;
import android.content.Intent;

import java.util.Locale;

import io.realm.Realm;
import kesshou.android.daanx.models.Setting;

/**
 * Created by yoyo930021 on 2017/1/16.
 */

public class LanguageUtil {



//	public static void setLanguage(Activity activity,String str) {
//		Resources resources = activity.getApplicationContext().getResources();
//		DisplayMetrics dm = resources.getDisplayMetrics();
//		Configuration config = resources.getConfiguration();
//
//		Locale locale = getLocale(str);
//
//		if (Build.VERSION.SDK_INT < 17) {
//			config.locale = locale;
//		} else {
//			config.setLocale(locale);
//		}
//		if (Build.VERSION.SDK_INT < 25) {
//			resources.updateConfiguration(config, dm);
//		} else {
//
//			activity.applyOverrideConfiguration(config);
//		}
//	}
//
//	public static void setLanguage(Activity activity,Locale locale) {
//		Resources resources = activity.getApplicationContext().getResources();
//		DisplayMetrics dm = resources.getDisplayMetrics();
//		Configuration config = resources.getConfiguration();
//
//
//		if (Build.VERSION.SDK_INT < 17) {
//			config.locale = locale;
//		} else {
//			config.setLocale(locale);
//		}
//		if (Build.VERSION.SDK_INT < 25) {
//			resources.updateConfiguration(config, dm);
//		} else {
//			activity.applyOverrideConfiguration(config);
//		}
//	}

	private static Locale getLocale(String str){
		Locale locale;
		switch (str) {
			case "Auto":
				locale = Locale.getDefault();
				break;
			case "正體中文":
				locale = Locale.TRADITIONAL_CHINESE;
				break;
			case "English":
				locale = Locale.ENGLISH;
				break;
			default:
				locale = Locale.getDefault();
		}
		return locale;
	}

	public static Locale getSetLocale() {
		Realm realm = Realm.getDefaultInstance();
		Setting setting = realm.where(Setting.class).findFirst();
		String str;
		if(setting ==  null ||setting.locale==null){
			str = "Auto";
			if(setting!=null) {
				realm.beginTransaction();
				setting.locale = "Auto";
				realm.commitTransaction();
			}
		}else {
			str = setting.locale;
		}
		realm.close();
		return getLocale(str);
	}

	public static void restart(Activity activity){
		Intent intent = new Intent(activity, activity.getClass());
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
		activity.startActivity(intent);

		android.os.Process.killProcess(android.os.Process.myPid());
		System.exit(0);
	}
}
