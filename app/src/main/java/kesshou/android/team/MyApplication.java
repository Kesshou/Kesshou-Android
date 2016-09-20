package kesshou.android.team;

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

		RealmConfiguration realmConfiguration = new RealmConfiguration.Builder(this)
			.name("default.realm")
			.schemaVersion(1)
			.build();
		Realm.setDefaultConfiguration(realmConfiguration);
	}
}
