package kesshou.android.team.util.network.api.holder;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/*
   Author: Charles Lien(lienching)
   Description: Attitude StatusResponse Response JSON holder
*/
public class AttitudeStatusResponse{


	@SerializedName("count")
	public CountBean count;
	@SerializedName("status")
	public List<Attitude> status;

	public static class Attitude{
		/**
		 * date : 2016/11/03
		 * item : 嘉獎１次
		 * text : 9-10月閱讀推薦文章達5篇
		 */

		@SerializedName("date")
		public String date;
		@SerializedName("item")
		public String item;
		@SerializedName("text")
		public String text;
	}

	public static class CountBean{
		/**
		 * smallcite : 19
		 * smallfault : 0
		 * middlecite : 7
		 * middlefault : 0
		 * bigcite : 0
		 * bigfault : 0
		 */

		@SerializedName("smallcite")
		public int smallcite;
		@SerializedName("smallfault")
		public int smallfault;
		@SerializedName("middlecite")
		public int middlecite;
		@SerializedName("middlefault")
		public int middlefault;
		@SerializedName("bigcite")
		public int bigcite;
		@SerializedName("bigfault")
		public int bigfault;
	}
}
