package kesshou.android.daanx.util.Adapter;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import kesshou.android.daanx.R;
import kesshou.android.daanx.util.Adapter.holder.Menu;

/**
 * Created by yoyoIU on 2016/10/25.
 */

public class MenuListAdapter extends RecyclerView.Adapter<MenuListAdapter.ViewHolder> {

	private ArrayList<Menu> menus;
	private Context mContext;

	class ViewHolder extends RecyclerView.ViewHolder {
		ConstraintLayout constraintLayout;
		ImageView imageIcon;
		TextView textName;
		TextView textText;
		ViewHolder(View v) {
			super(v);
			constraintLayout = (ConstraintLayout) v.findViewById(R.id.list_item);
			imageIcon = (ImageView) v.findViewById(R.id.icon);
			textName = (TextView) v.findViewById(R.id.name);
			textText = (TextView) v.findViewById(R.id.view_text);
		}
	}

	public MenuListAdapter(ArrayList<Menu> data,Context context) {
		menus = data;
		mContext = context;
	}

	@Override
	public MenuListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View v = LayoutInflater.from(parent.getContext())
			.inflate(R.layout.menu_list, parent, false);
		return new ViewHolder(v);
	}

	@Override
	public void onBindViewHolder(ViewHolder holder, int position) {
		holder.constraintLayout.setOnClickListener(menus.get(position).onClickListener);
		holder.imageIcon.setImageDrawable(tintDrawable(ContextCompat.getDrawable(mContext,menus.get(position).icon),ColorStateList.valueOf(menus.get(position).color)));
		holder.textName.setText(menus.get(position).name);
		holder.textText.setText(menus.get(position).viewText);
	}

	@Override
	public int getItemCount() {
		return menus.size();
	}

	private static Drawable tintDrawable(Drawable drawable, ColorStateList colors) {
		final Drawable wrappedDrawable = DrawableCompat.wrap(drawable);
		DrawableCompat.setTintList(wrappedDrawable, colors);
		return wrappedDrawable;
	}
}

