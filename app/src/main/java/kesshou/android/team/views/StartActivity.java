package kesshou.android.team.views;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import io.realm.Realm;
import io.realm.RealmResults;
import kesshou.android.team.R;
import kesshou.android.team.models.NetWorkCache;
import kesshou.android.team.models.Setting;
import kesshou.android.team.util.network.api.holder.Register;
import kesshou.android.team.views.Regist.LoginFragment;

public class StartActivity extends AppCompatActivity {

	public Register register;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		Realm realm=Realm.getDefaultInstance();

		RealmResults<Setting> settings = realm.where(Setting.class).findAll();
		if(settings.size()==0||!settings.first().logined){
			setContentView(R.layout.activity_login);
			Toolbar toolbar=(Toolbar)findViewById(R.id.toolbar);
			setSupportActionBar(toolbar);

			register = new Register();

			FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
			Fragment fg= new LoginFragment();
			ft.replace(R.id.fm, fg, "f_m");
			ft.commit();
		}else{
			toMainActivity();
		}

		realm.close();
	}

	public void toMainActivity(){
		Realm realm=Realm.getDefaultInstance();
		if(realm.where(NetWorkCache.class).findAll().size()==0){
			realm.executeTransaction(new Realm.Transaction() {
				@Override
				public void execute(Realm realm) {
					NetWorkCache netWorkCache = realm.createObject(NetWorkCache.class);
				}
			});
		}
		realm.close();
		Intent intent = new Intent();
		intent.setClass(StartActivity.this, MainActivity.class);
		startActivity(intent);
		StartActivity.this.finish();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
	}
}
