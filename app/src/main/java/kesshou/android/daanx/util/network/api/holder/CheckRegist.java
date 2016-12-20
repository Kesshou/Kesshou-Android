package kesshou.android.daanx.util.network.api.holder;

import com.google.gson.annotations.SerializedName;

/**
 * Created by yoyoIU on 2016/11/11.
 */

public class CheckRegist {

	@SerializedName("account")
	public String account;

	@SerializedName("nick")
	public String nick;

	@SerializedName("schoolAccount")
	public String schoolAccount;

	@SerializedName("schoolPwd")
	public String schoolPwd;
}
