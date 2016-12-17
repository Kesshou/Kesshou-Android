package kesshou.android.team.util.Adapter;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.List;

import kesshou.android.team.R;
import kesshou.android.team.util.BeautifulColor;
import kesshou.android.team.util.network.api.holder.AnnounceResponse;
import kesshou.android.team.views.news.ItemActivity;

/**
 * Created by yoyoIU on 2016/10/25.
 */

public class NewsListAdapter extends RecyclerView.Adapter<NewsListAdapter.ViewHolder> {

	private Activity activity;
	private List<AnnounceResponse> announces;

	public class ViewHolder extends RecyclerView.ViewHolder {
		public LinearLayout listItem;
		public TextView imageIcon;
		public TextView txtTitle;
		public TextView txtContent;
		public ViewHolder(View v) {
			super(v);
			listItem = (LinearLayout) v.findViewById(R.id.list_item);
			imageIcon = (TextView) v.findViewById(R.id.item_head);
			txtTitle = (TextView) v.findViewById(R.id.title);
			txtContent = (TextView) v.findViewById(R.id.content);
		}
	}

	public NewsListAdapter(Activity act,List<AnnounceResponse> data) {
		activity = act;
		announces = data;
	}

	@Override
	public NewsListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View v = LayoutInflater.from(parent.getContext())
			.inflate(R.layout.news_list, parent, false);
		ViewHolder vh = new ViewHolder(v);
		return vh;
	}

	@Override
	public void onBindViewHolder(ViewHolder holder, final int position) {
		holder.listItem.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Intent intent = new Intent();
				intent.setClass(activity, ItemActivity.class);
				Bundle bundle = new Bundle();
				bundle.putString("title",announces.get(position).title);
				bundle.putString("author",announces.get(position).author);
				bundle.putString("date",announces.get(position).date);
				bundle.putString("sort",announces.get(position).sort);
				bundle.putString("body",announces.get(position).body);
				bundle.putString("linked",announces.get(position).linked);
				Gson gson = new Gson();
				bundle.putString("file",gson.toJson(announces.get(position).file));
				intent.putExtras(bundle);
				activity.startActivity(intent);
			}
		});
		GradientDrawable gd = new GradientDrawable();
		gd.setColor(getAuthorColor(announces.get(position).author.charAt(0)));
		gd.setShape(GradientDrawable.OVAL);
		holder.imageIcon.setBackground(gd);
		holder.imageIcon.setText(String.valueOf(announces.get(position).author.charAt(0)));
		holder.txtTitle.setText(announces.get(position).title);
		holder.txtContent.setText(announces.get(position).summary);
	}

	@Override
	public int getItemCount() {
		return announces.size();
	}

	private int getAuthorColor(char text){
		switch (text){
			case '人':
				return BeautifulColor.getColor(0);
			case '圖':
				return BeautifulColor.getColor(1);
			case '實':
				return BeautifulColor.getColor(2);
			case '學':
				return BeautifulColor.getColor(3);
			case '輔':
				return BeautifulColor.getColor(4);
			case '教':
				return BeautifulColor.getColor(5);
			default:
				return BeautifulColor.getColor(6);
		}
	}
}
