package kesshou.android.daanx.service;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

import io.realm.Realm;

import io.realm.RealmResults;
import kesshou.android.daanx.models.NotiSetting;
import kesshou.android.daanx.models.Setting;
import kesshou.android.daanx.util.network.MyCallBack;
import kesshou.android.daanx.util.network.NetworkingClient;
import kesshou.android.daanx.util.network.api.holder.Error;
import kesshou.android.daanx.util.network.api.holder.StatusResponse;
import retrofit2.Call;
import retrofit2.Response;


/**
 * Created by yoyoIU on 2016/12/19.
 */

/*
I believe Google deprecated this .
Token updates are now handled in the messaging service
2018/07/23 JellyKuo
 */
// TODO: Review and remove when applicable

//public class MyFirebaseInstanceIDService extends FirebaseInstanceIdService {
//
//	private static final String TAG = "MyFirebaseIIDService";
//
//	/**
//	 * Called if InstanceID token is updated. This may occur if the security of
//	 * the previous token had been compromised. Note that this is called when the InstanceID token
//	 * is initially generated so this is where you would retrieve the token.
//	 */
//	// [START refresh_token]
//	@Override
//	public void onTokenRefresh() {
//		// Get updated InstanceID token.
//		String refreshedToken = FirebaseInstanceId.getInstance().getToken();
//		Log.d(TAG, "Refreshed token: " + refreshedToken);
//
//		// If you want to send messages to this application instance or
//		// manage this apps subscriptions on the server side, send the
//		// Instance ID token to your app server.
//		saveRegistration(refreshedToken);
//		ifSendServerOrNot(refreshedToken);
//	}
//	// [END refresh_token]
//
//	private void saveRegistration(String token){
//		Realm realm = Realm.getDefaultInstance();
//		if(realm.where(NotiSetting.class).findAll().size()==0){
//			realm.beginTransaction();
//			NotiSetting notiSetting = realm.createObject(NotiSetting.class);
//			notiSetting.fcm_token = token;
//			notiSetting.is_noti = true;
//			realm.commitTransaction();
//		}else{
//			NotiSetting notiSetting = realm.where(NotiSetting.class).findFirst();
//			realm.beginTransaction();
//			notiSetting.fcm_token = token;
//			realm.commitTransaction();
//		}
//		realm.close();
//	}
//
//	private void ifSendServerOrNot(String token){
//		Realm realm = Realm.getDefaultInstance();
//		RealmResults<Setting> settings = realm.where(Setting.class).findAll();
//		if(settings.size()!=0&&settings.get(0).logined){
//			NotiSetting notiSetting = realm.where(NotiSetting.class).findFirst();
//			new NetworkingClient(getApplicationContext()).setNoti(token, notiSetting.is_noti, new MyCallBack<StatusResponse>(getApplicationContext()) {
//				@Override
//				public void onSuccess(Response<StatusResponse> response) {
//					Log.d("fcm_token",response.body().status);
//				}
//
//				@Override
//				public void onErr(Error error, Call<StatusResponse> call) {
//					call.clone().enqueue(this);
//				}
//			});
//		}
//		realm.close();
//	}
//}