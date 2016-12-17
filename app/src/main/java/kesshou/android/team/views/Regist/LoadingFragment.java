package kesshou.android.team.views.regist;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import kesshou.android.team.R;
import kesshou.android.team.models.Setting;
import kesshou.android.team.util.component.ToastUtils;
import kesshou.android.team.util.network.MyCallBack;
import kesshou.android.team.util.network.NetworkingClient;
import kesshou.android.team.util.network.api.holder.Error;
import kesshou.android.team.util.network.api.holder.Register;
import kesshou.android.team.util.network.api.holder.Token;
import kesshou.android.team.util.network.api.holder.UserInfoResponse;
import kesshou.android.team.views.StartActivity;
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
				RealmConfiguration realmConfiguration = realm.getConfiguration();
				realm.close();
				Realm.deleteRealm(realmConfiguration);
				realm = Realm.getDefaultInstance();
				realm.executeTransaction(new Realm.Transaction() {
					@Override
					public void execute(Realm realm) {
						Setting setting=realm.createObject(Setting.class);
						setting.logined=true;
						setting.email=register.usr_email;
						setting.password=register.usr_passwd;
						setting.nick=register.nickname;
						setting.usr_group=register.usr_group;
						setting.token=token.body().token;
					}
				});


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

						((StartActivity)getActivity()).toMainActivity();
					}

					@Override
					public void onErr(Error error) {

					}
				});
			}

			@Override
			public void onErr(Error error) {
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
