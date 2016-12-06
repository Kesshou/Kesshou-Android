package kesshou.android.team.util.Adapter;

import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import kesshou.android.team.R;
import kesshou.android.team.util.Adapter.holder.Menu;

/**
 * Created by yoyoIU on 2016/10/25.
 */

public class MenuListAdapter extends RecyclerView.Adapter<MenuListAdapter.ViewHolder> {

	private ArrayList<Menu> menus;

	public class ViewHolder extends RecyclerView.ViewHolder {
		public ConstraintLayout constraintLayout;
		public ImageView imageIcon;
		public TextView textName;
		public TextView textText;
		public ViewHolder(View v) {
			super(v);
			constraintLayout = (ConstraintLayout) v.findViewById(R.id.list_item);
			imageIcon = (ImageView) v.findViewById(R.id.icon);
			textName = (TextView) v.findViewById(R.id.name);
			textText = (TextView) v.findViewById(R.id.view_text);
		}
	}

	public MenuListAdapter(ArrayList<Menu> data) {
		menus = data;
	}

	@Override
	public MenuListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View v = LayoutInflater.from(parent.getContext())
			.inflate(R.layout.menu_list, parent, false);
		ViewHolder vh = new ViewHolder(v);
		return vh;
	}

	@Override
	public void onBindViewHolder(ViewHolder holder, int position) {
		holder.constraintLayout.setOnClickListener(menus.get(position).onClickListener);
		holder.imageIcon.setImageResource(menus.get(position).icon);
		holder.textName.setText(menus.get(position).name);
		holder.textText.setText(menus.get(position).viewText);
	}

	@Override
	public int getItemCount() {
		return menus.size();
	}
}
