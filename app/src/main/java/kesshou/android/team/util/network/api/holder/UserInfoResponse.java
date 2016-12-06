package kesshou.android.team.util.network.api.holder;

import com.google.gson.annotations.SerializedName;

/**
 * Created by yoyoIU on 2016/11/17.
 */

public class UserInfoResponse {


	/**
	 * name : string
	 * nick : string
	 * class : string
	 * group : student
	 */

	@SerializedName("name")
	public String name;
	@SerializedName("nick")
	public String nick;
	@SerializedName("class")
	public String classX;
	@SerializedName("group")
	public String group;
}
