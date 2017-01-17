package kesshou.android.daanx;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.Configuration;
import android.os.Build;

import java.util.Locale;

import kesshou.android.daanx.util.LanguageUtil;

/**
 * Created by yoyo930021 on 2017/1/16.
 */

public class MyContextWrapper extends ContextWrapper {

	public MyContextWrapper(Context base) {
		super(base);
	}

	@SuppressWarnings("deprecation")
	public static ContextWrapper wrap(Context context, String language) {
		Configuration config = context.getResources().getConfiguration();
		Locale sysLocale = null;
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
			sysLocale = getSystemLocale(config);
		} else {
			sysLocale = getSystemLocaleLegacy(config);
		}
		if (!language.equals("") && !sysLocale.getLanguage().equals(language)) {
			Locale locale = LanguageUtil.getSetLocale();
			Locale.setDefault(locale);
			if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
				setSystemLocale(config, locale);
			} else {
				setSystemLocaleLegacy(config, locale);
			}
			if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
				context = context.createConfigurationContext(config);
			} else {
				context.getResources().updateConfiguration(config, context.getResources().getDisplayMetrics());
			}
		}
		return new MyContextWrapper(context);
	}

	@SuppressWarnings("deprecation")
	public static Locale getSystemLocaleLegacy(Configuration config){
		return config.locale;
	}

	@TargetApi(Build.VERSION_CODES.N)
	public static Locale getSystemLocale(Configuration config){
		return config.getLocales().get(0);
	}

	@SuppressWarnings("deprecation")
	public static void setSystemLocaleLegacy(Configuration config, Locale locale){
		config.locale = locale;
	}

	@TargetApi(Build.VERSION_CODES.N)
	public static void setSystemLocale(Configuration config, Locale locale){
		config.setLocale(locale);
	}
}
