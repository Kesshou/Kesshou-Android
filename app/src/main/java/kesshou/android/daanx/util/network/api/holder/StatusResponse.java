package kesshou.android.daanx.util.network.api.holder;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

/**
 * Created by yoyoIU on 2016/11/11.
 */

public class StatusResponse extends RealmObject {

	@SerializedName("success")
	public String status;

}
