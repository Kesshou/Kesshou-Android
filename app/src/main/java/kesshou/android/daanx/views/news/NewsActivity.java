package kesshou.android.daanx.views.news;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.analytics.FirebaseAnalytics;

import java.util.List;

import kesshou.android.daanx.R;
import kesshou.android.daanx.util.Adapter.NewsListAdapter;
import kesshou.android.daanx.util.Adapter.NewsListDecoration;
import kesshou.android.daanx.util.UnitConvert;
import kesshou.android.daanx.util.ViewShareUtils;
import kesshou.android.daanx.util.component.DialogUtils;
import kesshou.android.daanx.util.network.MyCallBack;
import kesshou.android.daanx.util.network.NetworkingClient;
import kesshou.android.daanx.util.network.api.holder.AnnounceResponse;
import kesshou.android.daanx.util.network.api.holder.Error;
import kesshou.android.daanx.BaseActivity;
import retrofit2.Call;
import retrofit2.Response;

public class NewsActivity extends BaseActivity {

	List<AnnounceResponse> announces;
	Dialog dialog;
	private FirebaseAnalytics mFirebaseAnalytics;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_news);
		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
		toolbar.setNavigationIcon(R.drawable.btn_back_white);
		toolbar.setNavigationOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				finish();
			}
		});
		toolbar.inflateMenu(R.menu.content_toolbar_menu_white);
		toolbar.setTitle(R.string.main_news_news);

		// Obtain the FirebaseAnalytics instance.
		mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);

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
			if(Build.VERSION.SDK_INT >= 16) {
				textView.setBackground(gd);
			}else{
				textView.setBackgroundDrawable(gd);
			}
			textView.setPadding((int)UnitConvert.Dp2Pixel(6,getApplicationContext()),(int)UnitConvert.Dp2Pixel(6,getApplicationContext()),(int)UnitConvert.Dp2Pixel(6,getApplicationContext()),(int)UnitConvert.Dp2Pixel(6,getApplicationContext()));
			LinearLayout.LayoutParams llp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
			llp.setMargins((int)UnitConvert.Dp2Pixel(6,getApplicationContext()),(int)UnitConvert.Dp2Pixel(6,getApplicationContext()),(int)UnitConvert.Dp2Pixel(6,getApplicationContext()),(int)UnitConvert.Dp2Pixel(6,getApplicationContext()));
			textView.setLayoutParams(llp);

			textView.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View view) {
					dialog = DialogUtils.createLoadingDialog(NewsActivity.this);
					dialog.show();
					makeList(NewsActivity.this,list,((TextView)view).getText().toString());
				}
			});

			layout.addView(textView);
		}

		dialog = DialogUtils.createLoadingDialog(NewsActivity.this);
		dialog.show();
		makeList(this,list,types[0]);

		toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
			@Override
			public boolean onMenuItemClick(MenuItem item) {
				switch (item.getItemId()){
					case R.id.menu_share:
						Bundle bundle = new Bundle();
						bundle.putString(FirebaseAnalytics.Param.ITEM_ID, "Share");
						bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, "Share");
						bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "fn");
						mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SHARE, bundle);
						ViewShareUtils.share(getApplicationContext(),NewsActivity.this,findViewById(R.id.content));
						break;
				}
				return false;
			}
		});
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
				dialog.dismiss();
			}

			@Override
			public void onErr(Error error, Call<List<AnnounceResponse>> call) {
				dialog.cancel();
			}
		});

	}

}
