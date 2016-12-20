package kesshou.android.daanx.util.network.api.holder;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 * Created by yoyoIU on 2016/11/12.
 */

public class AbsentstateResponse{


	/**
	 * date : 2016/10/27
	 * type : 公
	 * class : 五
	 */

	@SerializedName("date")
	public Date date;
	@SerializedName("type")
	public String type;
	@SerializedName("class")
	public String classX;
}
