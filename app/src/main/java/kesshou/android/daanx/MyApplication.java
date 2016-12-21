package kesshou.android.daanx;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;

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
			.schemaVersion(1)
			.deleteRealmIfMigrationNeeded()
			.build();
		Realm.setDefaultConfiguration(realmConfiguration);

	}
}
