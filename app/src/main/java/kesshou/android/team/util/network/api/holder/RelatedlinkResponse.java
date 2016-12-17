package kesshou.android.team.util.network.api.holder;

import com.google.gson.annotations.SerializedName;

/**
 * Created by yoyoIU on 2016/12/16.
 */

public class RelatedlinkResponse {


	/**
	 * id : 1
	 * name : 大安高工
	 * link : http://www.taivs.tp.edu.tw
	 * createdAt : null
	 * updatedAt : null
	 */

	@SerializedName("id")
	public int id;
	@SerializedName("name")
	public String name;
	@SerializedName("link")
	public String link;
	@SerializedName("createdAt")
	public String createdAt;
	@SerializedName("updatedAt")
	public String updatedAt;
}
