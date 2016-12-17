package kesshou.android.team.views.news;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import kesshou.android.team.R;
import kesshou.android.team.util.Adapter.NewsListAdapter;
import kesshou.android.team.util.Adapter.NewsListDecoration;
import kesshou.android.team.util.UnitConvert;
import kesshou.android.team.util.network.MyCallBack;
import kesshou.android.team.util.network.NetworkingClient;
import kesshou.android.team.util.network.api.holder.AnnounceResponse;
import kesshou.android.team.util.network.api.holder.Error;
import retrofit2.Response;

public class NewsActivity extends AppCompatActivity {

	List<AnnounceResponse> announces;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_news);
		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		setTitle(R.string.main_news_news);

		final RecyclerView list = (RecyclerView) findViewById(R.id.list);

		LinearLayout layout = (LinearLayout) findViewById(R.id.tag);

		String[] types={"學生事務","一般事務","新生指南","升學資訊","獎助學金","註冊補助減免","競賽", "榮譽榜"};

		for (String type:types) {
			GradientDrawable gd = new GradientDrawable();
			gd.setColor(ContextCompat.getColor(getApplicationContext(), R.color.deep_purple_500));
			gd.setCornerRadius(UnitConvert.Dp2Pixel(10,getApplicationContext()));

			TextView textView = new TextView(this);
			textView.setText(type);
			textView.setTextColor(Color.WHITE);
			textView.setTextSize(14);
			textView.setBackground(gd);
			textView.setPadding((int)UnitConvert.Dp2Pixel(6,getApplicationContext()),(int)UnitConvert.Dp2Pixel(6,getApplicationContext()),(int)UnitConvert.Dp2Pixel(6,getApplicationContext()),(int)UnitConvert.Dp2Pixel(6,getApplicationContext()));
			LinearLayout.LayoutParams llp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
			llp.setMargins((int)UnitConvert.Dp2Pixel(6,getApplicationContext()),(int)UnitConvert.Dp2Pixel(6,getApplicationContext()),(int)UnitConvert.Dp2Pixel(6,getApplicationContext()),(int)UnitConvert.Dp2Pixel(6,getApplicationContext()));
			textView.setLayoutParams(llp);

			textView.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View view) {
					makeList(NewsActivity.this,list,((TextView)view).getText().toString());
				}
			});

			layout.addView(textView);
		}

		makeList(this,list,types[0]);
	}

	private void makeList(final Context context, final RecyclerView recyclerView, String sort){

		new NetworkingClient(context).getAnnounce(sort,new MyCallBack<List<AnnounceResponse>>(context) {
			@Override
			public void onSuccess(Response<List<AnnounceResponse>> response) {
				if(announces==null) {
					announces = response.body();
					NewsListAdapter newsListAdapter = new NewsListAdapter(NewsActivity.this,announces);
					LinearLayoutManager layoutManager = new LinearLayoutManager(context.getApplicationContext());
					layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
					recyclerView.setLayoutManager(layoutManager);
					recyclerView.setAdapter(newsListAdapter);
					recyclerView.addItemDecoration(new NewsListDecoration());
					recyclerView.setItemAnimator(new DefaultItemAnimator());
				}else{
					announces.clear();
					announces.addAll(response.body());
					recyclerView.getAdapter().notifyDataSetChanged();
				}
			}

			@Override
			public void onErr(Error error) {

			}
		});

	}

}
