package kesshou.android.daanx.models;

import io.realm.RealmObject;

/**
 * Created by yoyoIU on 2016/12/19.
 */

public class NotiSetting extends RealmObject {
	public String fcm_token;
	public boolean is_noti;
}
