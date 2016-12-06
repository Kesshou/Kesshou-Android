package kesshou.android.team.util.network.api.holder;

import com.google.gson.annotations.SerializedName;

/**
 * Created by yoyoIU on 2016/11/17.
 */

public class HistoryScoreResponse{


	/**
	 * subject : string
	 * type : 必
	 * credit : 0
	 * score : 0
	 * makeup : 0
	 * retake : 0
	 * qualify : 0
	 */

	@SerializedName("subject")
	public String subject;
	@SerializedName("type")
	public String type;
	@SerializedName("credit")
	public int credit;
	@SerializedName("score")
	public int score;
	@SerializedName("makeup")
	public int makeup;
	@SerializedName("retake")
	public int retake;
	@SerializedName("qualify")
	public int qualify;
}
