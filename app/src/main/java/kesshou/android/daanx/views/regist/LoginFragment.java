package kesshou.android.daanx.views.regist;


import android.app.Dialog;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.AppCompatButton;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import io.realm.Realm;
import kesshou.android.daanx.R;
import kesshou.android.daanx.models.NetWorkCache;
import kesshou.android.daanx.models.NotiSetting;
import kesshou.android.daanx.models.Setting;
import kesshou.android.daanx.util.component.DialogUtils;
import kesshou.android.daanx.util.network.MyCallBack;
import kesshou.android.daanx.util.network.NetworkingClient;
import kesshou.android.daanx.util.network.api.holder.Error;
import kesshou.android.daanx.util.network.api.holder.Login;
import kesshou.android.daanx.util.network.api.holder.StatusResponse;
import kesshou.android.daanx.util.network.api.holder.Token;
import kesshou.android.daanx.util.network.api.holder.UserInfoResponse;
import kesshou.android.daanx.views.StartActivity;
import retrofit2.Call;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment {


	public LoginFragment() {
		// Required empty public constructor
	}


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	                         Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		View view = inflater.inflate(R.layout.fragment_login, container, false);

		final TextInputEditText inputAccount=(TextInputEditText)view.findViewById(R.id.input_account);

		final TextInputEditText inputPassword=(TextInputEditText) view.findViewById(R.id.input_password);

		AppCompatButton LoginButton = (AppCompatButton) view.findViewById(R.id.LoginButton);
		LoginButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				final Dialog dialog = DialogUtils.createLoadingDialog(getActivity());
				dialog.show();
				Login user = new Login();
				user.usr_account = inputAccount.getText().toString();
				user.usr_password = inputPassword.getText().toString();

				final NetworkingClient networkingClient = new NetworkingClient(getActivity().getApplicationContext());

				networkingClient.login(user, new MyCallBack<Token>(getActivity().getApplicationContext()) {
					@Override
					public void onSuccess(final Response<Token> token) {

						Realm realm = Realm.getDefaultInstance();
						realm.beginTransaction();
						realm.where(Setting.class).findAll().deleteAllFromRealm();
						realm.where(NetWorkCache.class).findAll().deleteAllFromRealm();
						Setting setting=realm.createObject(Setting.class);
						setting.logined=true;
						setting.email=inputAccount.getText().toString();
						setting.password=inputPassword.getText().toString();
						setting.token=token.body().token;
						realm.commitTransaction();



						networkingClient.getUserInfo(new MyCallBack<UserInfoResponse>(getActivity().getApplicationContext()) {
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

								((StartActivity)getActivity()).toMainActivity();

								dialog.dismiss();
							}

							@Override
							public void onErr(Error error, Call<UserInfoResponse> call) {
								dialog.dismiss();
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
					public void onErr(Error error1, Call<Token> call) {
						dialog.dismiss();
					}
				});
			}
		});

		AppCompatButton RegistButton = (AppCompatButton) view.findViewById(R.id.RegistButton);
		RegistButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
				ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
				Fragment fg= new GroupFragment();
				ft.replace(R.id.fm, fg, "f_m");
				ft.commit();
			}
		});

		return view;
	}

}
