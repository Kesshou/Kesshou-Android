package kesshou.android.team.views;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import kesshou.android.team.R;
import kesshou.android.team.util.ContentRouter;
import kesshou.android.team.util.ViewShareUtils;

public class ContentActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_content);
		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
		toolbar.setBackgroundColor(Color.TRANSPARENT);
		toolbar.setElevation(0);
		toolbar.setTitle("");
		toolbar.inflateMenu(R.menu.content_toolbar_menu);
		toolbar.setNavigationIcon(R.drawable.btn_back_blue);
		toolbar.setNavigationOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				finish();
			}
		});

//		setSupportActionBar(toolbar);
		getWindow().setStatusBarColor(ContextCompat.getColor(getApplicationContext(),R.color.black_transparent));
//		getSupportActionBar().setDisplayHomeAsUpEnabled(true);

		Bundle bundle = getIntent().getExtras();

		TextView title = (TextView) findViewById(R.id.title);
		title.setText(bundle.getString("title"));
		TextView title_help = (TextView) findViewById(R.id.title_help);
		title_help.setText(bundle.getString("title_help"));

		final LinearLayout layout = (LinearLayout) findViewById(R.id.content);
		View fragment = ContentRouter.getFragment(bundle.getInt("type"),ContentActivity.this);
		layout.addView(fragment);

		LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
		fragment.setLayoutParams(layoutParams);
		fragment.requestLayout();

		toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
			@Override
			public boolean onMenuItemClick(MenuItem item) {
				switch (item.getItemId()){
					case R.id.menu_share:
						ViewShareUtils.share(getApplicationContext(),ContentActivity.this,layout);
						break;
				}
				return false;
			}
		});

	}

}
