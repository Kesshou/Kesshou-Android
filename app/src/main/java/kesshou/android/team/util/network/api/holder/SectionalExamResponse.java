package kesshou.android.team.util.network.api.holder;

import com.google.gson.annotations.SerializedName;

/*
   Author: IU(yoyo930021)
   Description: Score Query Response JSON holder
*/
public class SectionalExamResponse{

	/**
	 * subject : 國文
	 * first_section : 65
	 * second_section : null
	 * last_section : null
	 * performance : null
	 * average : null
	 */

	@SerializedName("subject")
	public String subject;
	@SerializedName("first_section")
	public int firstSection;
	@SerializedName("second_section")
	public int secondSection;
	@SerializedName("last_section")
	public int lastSection;
	@SerializedName("performance")
	public int performance;
	@SerializedName("average")
	public int average;
}
