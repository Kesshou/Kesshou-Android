package kesshou.android.team.views;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import io.realm.Realm;
import io.realm.RealmResults;
import kesshou.android.team.R;
import kesshou.android.team.models.Setting;
import kesshou.android.team.util.network.api.holder.Register;
import kesshou.android.team.views.Regist.SchoolFragment;

public class StartActivity extends AppCompatActivity {

	private Realm realm;
	public Register register;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		realm=Realm.getDefaultInstance();

		RealmResults<Setting> settings = realm.where(Setting.class).findAll();
		if(settings.size()==0||!settings.first().logined){
			setContentView(R.layout.activity_login);

			register = new Register();

			FragmentTransaction ft = getFragmentManager().beginTransaction();
			Fragment fg= new SchoolFragment();
			ft.replace(R.id.fm, fg, "f_m");
			ft.commit();
		}


	}

	public void toMainActivity(){
		Intent intent = new Intent();
		intent.setClass(StartActivity.this, MainActivity.class);
		startActivity(intent);
		StartActivity.this.finish();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		realm.close();
	}
}
