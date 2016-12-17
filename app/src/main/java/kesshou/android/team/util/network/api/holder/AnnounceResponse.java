package kesshou.android.team.util.network.api.holder;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by yoyoIU on 2016/12/13.
 */

public class AnnounceResponse {


	@SerializedName("id")
	public int id;
	@SerializedName("key")
	public int key;
	@SerializedName("title")
	public String title;
	@SerializedName("date")
	public String date;
	@SerializedName("body")
	public String body;
	@SerializedName("linked")
	public String linked;
	@SerializedName("author")
	public String author;
	@SerializedName("lifttime")
	public String lifttime;
	@SerializedName("sort")
	public String sort;
	@SerializedName("createdAt")
	public String createdAt;
	@SerializedName("updatedAt")
	public String updatedAt;
	@SerializedName("summary")
	public String summary;
	@SerializedName("file")
	public List<FileBean> file;

	public static class FileBean {
		/**
		 * id : 551
		 * news_key : 785
		 * type : file
		 * file_name : 2aecf5ba03a7410b413b80e8a5123a7d_15459300A00_ATTCH2.pdf
		 * file_src : http://ta.taivs.tp.edu.tw/news/upload/1/2aecf5ba03a7410b413b80e8a5123a7d_15459300A00_ATTCH2.pdf
		 * createdAt : 2016-12-14T07:23:02.000Z
		 * updatedAt : 2016-12-14T07:23:02.000Z
		 */

		@SerializedName("id")
		public int id;
		@SerializedName("news_key")
		public int newsKey;
		@SerializedName("type")
		public String type;
		@SerializedName("file_name")
		public String fileName;
		@SerializedName("file_src")
		public String fileSrc;
		@SerializedName("createdAt")
		public String createdAt;
		@SerializedName("updatedAt")
		public String updatedAt;
	}
}
