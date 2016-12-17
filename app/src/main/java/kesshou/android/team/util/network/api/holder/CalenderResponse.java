package kesshou.android.team.util.network.api.holder;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

/*
   Author: Charles Lien(lienching)
   Description: Calender Response JSON holder
*/
public class CalenderResponse{

	/**
	 * start : 2017/2/13
	 * end : 2017/2/14
	 * content : (教)第 2 學期開學、正式上課
	 */

	@SerializedName("start")
	public Date start;
	@SerializedName("end")
	public Date end;
	@SerializedName("content")
	public String content;
}
