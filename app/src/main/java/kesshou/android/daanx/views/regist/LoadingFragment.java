package kesshou.android.daanx.views.regist;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.firebase.analytics.FirebaseAnalytics;

import io.realm.Realm;
import kesshou.android.daanx.R;
import kesshou.android.daanx.models.NetWorkCache;
import kesshou.android.daanx.models.NotiSetting;
import kesshou.android.daanx.models.Setting;
import kesshou.android.daanx.util.component.ToastUtils;
import kesshou.android.daanx.util.network.MyCallBack;
import kesshou.android.daanx.util.network.NetworkingClient;
import kesshou.android.daanx.util.network.api.holder.Error;
import kesshou.android.daanx.util.network.api.holder.Register;
import kesshou.android.daanx.util.network.api.holder.StatusResponse;
import kesshou.android.daanx.util.network.api.holder.Token;
import kesshou.android.daanx.util.network.api.holder.UserInfoResponse;
import kesshou.android.daanx.views.StartActivity;
import retrofit2.Call;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoadingFragment extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	                         Bundle savedInstanceState) {
		// Inflate the layout for this fragment

		final Register register=((StartActivity) getActivity()).register;

		final NetworkingClient networkingClient = new NetworkingClient(getActivity().getApplicationContext());

		networkingClient.register(register, new MyCallBack<Token>(getActivity().getApplicationContext()) {
			@Override
			public void onSuccess(final Response<Token> token) {

				Realm realm = Realm.getDefaultInstance();
				realm.beginTransaction();
				realm.where(Setting.class).findAll().deleteAllFromRealm();
				realm.where(NetWorkCache.class).findAll().deleteAllFromRealm();
				Setting setting=realm.createObject(Setting.class);
				setting.logined=true;
				setting.email=register.usr_email;
				setting.password=register.usr_passwd;
				setting.nick=register.nickname;
				setting.usr_group=register.usr_group;
				setting.token=token.body().token;
				setting.locale="Auto";
				realm.commitTransaction();



				networkingClient.getUserInfo(new MyCallBack<UserInfoResponse>(getActivity().getApplicationContext()) {
					@Override
					public void onSuccess(final Response<UserInfoResponse> response) {
						Realm realm = Realm.getDefaultInstance();
						realm.executeTransaction(new Realm.Transaction() {
							@Override
							public void execute(Realm realm) {
								Setting setting=realm.where(Setting.class).findFirst();
								setting.classX=response.body().classX;
								setting.name=response.body().name;
							}
						});
						realm.close();

						Bundle bundle = new Bundle();
						bundle.putString(FirebaseAnalytics.Param.ITEM_ID, "Sign up");
						bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, "Sign up");
						bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "doing");
						((StartActivity)getActivity()).mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SIGN_UP, bundle);

						((StartActivity)getActivity()).toMainActivity();
					}

					@Override
					public void onErr(Error error, Call<UserInfoResponse> call) {

					}
				});

				NotiSetting notiSetting = realm.where(NotiSetting.class).findFirst();
				networkingClient.setNoti(notiSetting.fcm_token, notiSetting.is_noti, new MyCallBack<StatusResponse>(getActivity().getApplicationContext()) {
					@Override
					public void onSuccess(Response<StatusResponse> response) {
						Log.d("fcm_token",response.body().status);
					}

					@Override
					public void onErr(Error error, Call<StatusResponse> call) {
						call.clone().enqueue(this);
					}
				});

				realm.close();
			}

			@Override
			public void onErr(Error error, Call<Token> call) {
				switch (error.code){
					case 102:
						ToastUtils.makeTextAndShow(getActivity().getApplicationContext(),error.message, Toast.LENGTH_LONG,Gravity.CENTER);
						FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
						ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
						Fragment fg= new SchoolFragment();
						ft.replace(R.id.fm, fg, "f_m");
						ft.commit();
						break;
				}
			}
		});



		return inflater.inflate(R.layout.fragment_loading, container, false);
	}

}
