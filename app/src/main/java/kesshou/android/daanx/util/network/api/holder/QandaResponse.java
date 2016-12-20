package kesshou.android.daanx.util.network.api.holder;

import com.google.gson.annotations.SerializedName;

/**
 * Created by yoyoIU on 2016/12/15.
 */

public class QandaResponse {


	/**
	 * id : 1
	 * Q : 你喜歡木棉手札嗎？
	 * A : 喜歡
	 * createdAt : null
	 * updatedAt : null
	 */

	@SerializedName("id")
	public int id;
	@SerializedName("Q")
	public String Q;
	@SerializedName("A")
	public String A;
	@SerializedName("createdAt")
	public Object createdAt;
	@SerializedName("updatedAt")
	public Object updatedAt;
}
