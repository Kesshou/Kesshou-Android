package kesshou.android.daanx;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import kesshou.android.daanx.models.Migration;

/**
 * Created by yoyoIU on 2016/9/17.
 */
public class MyApplication extends Application {

	@Override
	public void onCreate(){
		super.onCreate();

		Realm.init(getApplicationContext());

		RealmConfiguration realmConfiguration = new RealmConfiguration.Builder()
			.name("default.realm")
			.schemaVersion(3)
			.migration(new Migration())
			.build();
		Realm.setDefaultConfiguration(realmConfiguration);

	}
}
