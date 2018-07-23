package kesshou.android.daanx.service;

import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

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

public class MyFirebaseMessagingService extends FirebaseMessagingService {
	private static final String TAG = "MyFirebaseMsgService";

	// Token updates
    // This used to be in MyFirebaseInstanceIDService
    /**
     * Called if InstanceID token is updated. This may occur if the security of
     * the previous token had been compromised. Note that this is called when the InstanceID token
     * is initially generated so this is where you would retrieve the token.
     */
    @Override
    public void onNewToken(String token) {
        Log.d(TAG, "Refreshed token: " + token);

        // If you want to send messages to this application instance or
        // manage this apps subscriptions on the server side, send the
        // Instance ID token to your app server.
        //sendRegistrationToServer(token);
        Log.d(TAG, "New token: " + token);
        saveRegistration(token);
        ifSendServerOrNot(token);
    }

	/**
	 * Called when message is received.
	 *
	 * @param remoteMessage Object representing the message received from Firebase Cloud Messaging.
	 */
	// [START receive_message]
	@Override
	public void onMessageReceived(RemoteMessage remoteMessage) {
		// [START_EXCLUDE]
		// There are two types of messages data messages and notification messages. Data messages are handled
		// here in onMessageReceived whether the app is in the foreground or background. Data messages are the type
		// traditionally used with GCM. Notification messages are only received here in onMessageReceived when the app
		// is in the foreground. When the app is in the background an automatically generated notification is displayed.
		// When the user taps on the notification they are returned to the app. Messages containing both notification
		// and data payloads are treated as notification messages. The Firebase console always sends notification
		// messages. For more see: https://firebase.google.com/docs/cloud-messaging/concept-options
		// [END_EXCLUDE]

		// TODO(developer): Handle FCM messages here.
		// Not getting messages here? See why this may be: https://goo.gl/39bRNJ
		Log.d(TAG, "From: " + remoteMessage.getFrom());

		// Check if message contains a data payload.
		if (remoteMessage.getData().size() > 0) {
			Log.d(TAG, "Message data payload: " + remoteMessage.getData());
		}

		// Check if message contains a notification payload.
		if (remoteMessage.getNotification() != null) {
			Log.d(TAG, "Message Notification Body: " + remoteMessage.getNotification().getBody());
		}

		// Also if you intend on generating your own notifications as a result of a received FCM
		// message, here is where that should be initiated. See sendNotification method below.
	}
	// [END receive_message]


    // Token registration server update
    // Moved from MyFirebaseInstanceIDService
    private void saveRegistration(String token){
        Realm realm = Realm.getDefaultInstance();
        if(realm.where(NotiSetting.class).findAll().size()==0){
            realm.beginTransaction();
            NotiSetting notiSetting = realm.createObject(NotiSetting.class);
            notiSetting.fcm_token = token;
            notiSetting.is_noti = true;
            realm.commitTransaction();
        }else{
            NotiSetting notiSetting = realm.where(NotiSetting.class).findFirst();
            realm.beginTransaction();
            notiSetting.fcm_token = token;
            realm.commitTransaction();
        }
        realm.close();
    }

    private void ifSendServerOrNot(String token){
        Realm realm = Realm.getDefaultInstance();
        RealmResults<Setting> settings = realm.where(Setting.class).findAll();
        if(settings.size()!=0&&settings.get(0).logined){
            NotiSetting notiSetting = realm.where(NotiSetting.class).findFirst();
            new NetworkingClient(getApplicationContext()).setNoti(token, notiSetting.is_noti, new MyCallBack<StatusResponse>(getApplicationContext()) {
                @Override
                public void onSuccess(Response<StatusResponse> response) {
                    Log.d("fcm_token",response.body().status);
                }

                @Override
                public void onErr(Error error, Call<StatusResponse> call) {
                    call.clone().enqueue(this);
                }
            });
        }
        realm.close();
    }

}
