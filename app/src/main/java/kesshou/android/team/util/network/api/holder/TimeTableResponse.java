package kesshou.android.team.util.network.api.holder;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by yoyoIU on 2016/11/14.
 */

public class TimeTableResponse {

	@SerializedName("week1")
	public List<Week> week1;
	@SerializedName("week2")
	public List<Week> week2;
	@SerializedName("week3")
	public List<Week> week3;
	@SerializedName("week4")
	public List<Week> week4;
	@SerializedName("week5")
	public List<Week> week5;

	public static class Week {
		/**
		 * start : 8:20
		 * end : 9:10
		 * subject : 輸配電學
		 * teacher : 林志昌
		 */

		@SerializedName("start")
		public String start;
		@SerializedName("end")
		public String end;
		@SerializedName("subject")
		public String subject;
		@SerializedName("teacher")
		public String teacher;
	}
}
