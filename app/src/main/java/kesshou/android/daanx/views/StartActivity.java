package kesshou.android.daanx.views;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.google.firebase.analytics.FirebaseAnalytics;

import io.realm.Realm;
import io.realm.RealmResults;
import kesshou.android.daanx.R;
import kesshou.android.daanx.models.NetWorkCache;
import kesshou.android.daanx.models.Setting;
import kesshou.android.daanx.util.network.MyCallBack;
import kesshou.android.daanx.util.network.NetworkingClient;
import kesshou.android.daanx.util.network.api.holder.Error;
import kesshou.android.daanx.util.network.api.holder.Register;
import kesshou.android.daanx.util.network.api.holder.UserInfoResponse;
import kesshou.android.daanx.views.regist.LoginFragment;
import retrofit2.Call;
import retrofit2.Response;

public class StartActivity extends AppCompatActivity {

	public Register register;
	private FirebaseAnalytics mFirebaseAnalytics;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// Obtain the FirebaseAnalytics instance.
		mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);

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
			setContentView(R.layout.activity_start);
			new NetworkingClient(getApplicationContext()).getUserInfo(new MyCallBack<UserInfoResponse>(getApplicationContext()) {
				@Override
				public void onSuccess(final Response<UserInfoResponse> response) {
					Realm realm = Realm.getDefaultInstance();

					realm.executeTransaction(new Realm.Transaction() {
						@Override
						public void execute(Realm realm) {
							Setting setting=realm.where(Setting.class).findFirst();
							setting.nick=response.body().nick;
							setting.usr_group=response.body().group;
							setting.classX=response.body().classX;
							setting.name=response.body().name;
						}
					});

					realm.close();

					toMainActivity();
				}

				@Override
				public void onErr(Error error, Call<UserInfoResponse> call) {
					toMainActivity();
				}
			});
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
