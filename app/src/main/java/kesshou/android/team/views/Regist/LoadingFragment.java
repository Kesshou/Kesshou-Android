package kesshou.android.team.views.Regist;


import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import io.realm.Realm;
import kesshou.android.team.R;
import kesshou.android.team.models.Setting;
import kesshou.android.team.util.network.ErrorUtils;
import kesshou.android.team.util.network.NetworkingClient;
import kesshou.android.team.util.network.api.holder.Error;
import kesshou.android.team.util.network.api.holder.Register;
import kesshou.android.team.util.network.api.holder.Token;
import kesshou.android.team.views.StartActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoadingFragment extends Fragment {

	private Realm realm;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	                         Bundle savedInstanceState) {
		// Inflate the layout for this fragment

		final Register register=((StartActivity) getActivity()).register;

		new NetworkingClient(getActivity().getApplicationContext()).register(register, new Callback<Token>() {
			@Override
			public void onResponse(Call<Token> call, Response<Token> response) {
				if(response.isSuccessful()){
					Token token=response.body();

					realm=Realm.getDefaultInstance();
					realm.beginTransaction();
					Setting setting=realm.createObject(Setting.class);
					setting.logined=true;
					setting.email=register.usr_email;
					setting.password=register.usr_passwd;
					setting.nick=register.nickname;
					setting.usr_group=register.usr_group;
					setting.token=token.token;
					realm.commitTransaction();

					((StartActivity)getActivity()).toMainActivity();
				}else{
					Error error = ErrorUtils.parseError(response,getActivity().getApplication());
					ErrorUtils.logError(error);

					Toast.makeText(getActivity().getApplicationContext(),error.message,Toast.LENGTH_SHORT).show();
					switch (error.message){
						case "學校驗證錯誤":
							break;
						case "非法字元":
							break;
						case "帳號已被使用":
							break;
						case "暱稱已被使用":
							break;
					}
				}
			}

			@Override
			public void onFailure(Call<Token> call, Throwable t) {

			}
		});



		return inflater.inflate(R.layout.fragment_loading, container, false);
	}

}
