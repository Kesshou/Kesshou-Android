package kesshou.android.daanx.views.menu;


import android.content.Context;
import android.support.v7.widget.SwitchCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Toast;

import io.realm.Realm;
import kesshou.android.daanx.R;
import kesshou.android.daanx.models.NotiSetting;
import kesshou.android.daanx.util.network.MyCallBack;
import kesshou.android.daanx.util.network.NetworkingClient;
import kesshou.android.daanx.util.network.api.holder.Error;
import kesshou.android.daanx.util.network.api.holder.StatusResponse;
import retrofit2.Call;
import retrofit2.Response;

/**
 * A simple class.
 */
public class NotiFragment {


	public NotiFragment() {
		// Required empty public constructor
	}


	public View onCreateView(final Context context) {
		// Inflate the layout for this fragment
		context.setTheme(R.style.AppTheme);
		context.getApplicationContext().setTheme(R.style.AppTheme);
		final View view = LayoutInflater.from(context.getApplicationContext()).inflate(R.layout.fragment_noti,null);

		final NetworkingClient networkingClient = new NetworkingClient(context.getApplicationContext());

		Realm realm = Realm.getDefaultInstance();

		NotiSetting notiSetting = realm.where(NotiSetting.class).findFirst();

		SwitchCompat switchCompat = (SwitchCompat) view.findViewById(R.id.noti_switch);
		switchCompat.setChecked(notiSetting.is_noti);
		switchCompat.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton compoundButton,final boolean b) {
				Realm realm = Realm.getDefaultInstance();
				NotiSetting notiSetting = realm.where(NotiSetting.class).findFirst();
				networkingClient.setNoti(notiSetting.fcm_token, b, new MyCallBack<StatusResponse>(context) {
					@Override
					public void onSuccess(Response<StatusResponse> response) {
						Realm realm = Realm.getDefaultInstance();
						NotiSetting notiSetting = realm.where(NotiSetting.class).findFirst();
						realm.beginTransaction();
						notiSetting.is_noti = b;
						realm.commitTransaction();
						realm.close();
						Toast.makeText(context,"修改成功",Toast.LENGTH_SHORT).show();
					}

					@Override
					public void onErr(Error error, Call<StatusResponse> call) {

					}
				});
				realm.close();
			}
		});


		realm.close();
		return view;
	}



}
