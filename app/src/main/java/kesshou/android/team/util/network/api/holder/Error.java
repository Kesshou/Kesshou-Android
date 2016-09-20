package kesshou.android.team.util.network.api.holder;

import com.google.gson.annotations.SerializedName;

/**
 * Created by yoyoIU on 2016/9/20.
 */
/*
   Author: IU(yoyo930021)
   Description: Error Response JSON holder
*/
public class Error {

	@SerializedName("statuscode")
	public int statusCode;

	@SerializedName("message")
	public String message;
}
