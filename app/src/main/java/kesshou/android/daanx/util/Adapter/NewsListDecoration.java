package kesshou.android.daanx.util.Adapter;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import kesshou.android.daanx.R;

/**
 * Created by yoyoIU on 2016/11/2.
 */

public class NewsListDecoration extends RecyclerView.ItemDecoration {

	@Override
	public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
		//先初始化一个Paint来简单指定一下Canvas的颜色，就黑的吧！
		Paint paint = new Paint();
		paint.setColor(ContextCompat.getColor(parent.getContext(),R.color.black));

		//获得RecyclerView中总条目数量
		int childCount = parent.getChildCount();

		//遍历一下
		for (int i = 0; i < childCount; i++) {
			//获得子View，也就是一个条目的View，准备给他画上边框
			View childView = parent.getChildAt(i);

			//先获得子View的长宽，以及在屏幕上的位置，方便我们得到边框的具体坐标
			float x = childView.getX();
			float y = childView.getY();
			int width = childView.getWidth();
//			int height = childView.getHeight();
//			int paddingLine = 64;

			//根据这些点画条目的四周的线
			if(i==0){continue;}
			c.drawLine(x, y, x + width, y, paint);//top
//			c.drawLine(x, y, x + width, y, paint);//top
//			c.drawLine(x, y, x, y + height, paint);//left
//			c.drawLine(x + width, y, x + width, y + height, paint);//bottum
//			c.drawLine(x, y + height, x + width, y + height, paint);//right

			//当然了，这里大家肯定是要根据自己不同的设计稿进行画线，或者画一些其他的东西，都可以在这里搞，非常方便
		}
		super.onDraw(c, parent, state);
	}
}
