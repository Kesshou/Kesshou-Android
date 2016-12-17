package kesshou.android.team.views.news;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.customtabs.CustomTabsIntent;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import kesshou.android.team.R;
import kesshou.android.team.util.network.api.holder.AnnounceResponse;

public class ItemActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_news_item);
		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
		Bundle bundle = getIntent().getExtras();
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
		getWindow().setStatusBarColor(ContextCompat.getColor(getApplicationContext(),R.color.black_transparent));

		TextView txtTitle = (TextView) findViewById(R.id.title);
		txtTitle.setText(bundle.getString("title"));
		TextView txtDate = (TextView) findViewById(R.id.date);
		txtDate.setText(bundle.getString("date"));
		TextView txtAuthor = (TextView) findViewById(R.id.author);
		txtAuthor.setText(bundle.getString("author"));
		TextView txtSort = (TextView) findViewById(R.id.sort);
		txtSort.setText(bundle.getString("sort"));

		StringBuilder html = new StringBuilder();
		html.append("<style>");
		html.append("img{");
		html.append("display: inline;\n");
		html.append(" height: auto;\n");
		html.append(" max-width: 100%;}\n");
		html.append("html, body {\n");
		html.append("width:100%;\n");
		html.append("height: 100%;\n");
		html.append("margin: 0px;\n");
		html.append("padding: 0px;\n");
		html.append("}</style></head><body>");
		html.append(bundle.getString("body"));
		if(!bundle.getString("linked").equals("")) {html.append("<br>參考網址：<a href=\""+bundle.getString("linked")+"\">"+bundle.getString("linked")+"</a>");}

		Gson gson = new Gson();
		Type listType = new TypeToken<List<AnnounceResponse.FileBean>>() {}.getType();
		List<AnnounceResponse.FileBean> files = gson.fromJson(bundle.getString("file"),listType);
		if(files.size()>0){
			for(int i=0;i<files.size();i++){
				if(files.get(i).type.equals("img")){
					html.append("<br><img src=\""+files.get(i).fileSrc+"\" alt=\"pageNo\"");
				}
			}
			if(files.toString().equals("file")) html.append("<br>附件檔案:<br>");
			for(int i=0;i<files.size();i++){
				if(files.get(i).type.equals("file")){
					html.append("<li><a href=\"http://drive.google.com/viewerng/viewer?embedded=true&url="+files.get(i).fileSrc+"\">"+files.get(i).fileName+"</a> <a href=\""+files.get(i).fileSrc+"\">下載</li>");
				}
			}
		}
		html.append("</body></html>");


		final WebView webView = (WebView) findViewById(R.id.content_main);
		webView.setBackgroundColor(Color.WHITE);
		WebSettings settings=webView.getSettings();
		settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
		settings.setSupportZoom(true);//开启缩放支持
		settings.setBuiltInZoomControls(true);//开启缩放支持
		settings.setDisplayZoomControls(false); //隐藏webview缩放按钮
		if(Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
			webView.setWebViewClient(new WebViewClient() {
				@Override
				public boolean shouldOverrideUrlLoading(WebView view, String url) {
					if (url.contains("drive.google.com/viewerng/viewer?embedded=true")) {
						CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
						builder.setToolbarColor(ContextCompat.getColor(getApplicationContext(),R.color.colorPrimary));
						CustomTabsIntent customTabsIntent = builder.build();
						customTabsIntent.launchUrl(ItemActivity.this, Uri.parse(url));
					} else {
						Uri uri = Uri.parse(url);
						Intent intent = new Intent(Intent.ACTION_VIEW, uri);
						startActivity(intent);
					}
					return true;
				}

				@Override
				public void onPageFinished(WebView view, String url) {
					if (!webView.getSettings().getLoadsImagesAutomatically()) {
						webView.getSettings().setLoadsImagesAutomatically(true);
					}
					webView.setLayerType(View.LAYER_TYPE_NONE, null);
				}
			});
		}else{
			webView.setWebViewClient(new WebViewClient() {
				@Override
				public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
					if (request.getUrl().toString().contains("drive.google.com/viewerng/viewer?embedded=true")) {
						CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
						builder.setToolbarColor(ContextCompat.getColor(getApplicationContext(),R.color.colorPrimary));
						CustomTabsIntent customTabsIntent = builder.build();
						customTabsIntent.launchUrl(ItemActivity.this, request.getUrl());
					} else {
						Uri uri = request.getUrl();
						Intent intent = new Intent(Intent.ACTION_VIEW, uri);
						startActivity(intent);
					}
					return true;
				}

				@Override
				public void onPageFinished(WebView view, String url) {
					if (!webView.getSettings().getLoadsImagesAutomatically()) {
						webView.getSettings().setLoadsImagesAutomatically(true);
					}
					webView.setLayerType(View.LAYER_TYPE_NONE, null);
				}
			});
		}
		if(Build.VERSION.SDK_INT >= 19) {
			webView.getSettings().setLoadsImagesAutomatically(true);
			webView.setLayerType(View.LAYER_TYPE_HARDWARE, null);
		} else {
			webView.getSettings().setLoadsImagesAutomatically(false);
			webView.getSettings().setRenderPriority(WebSettings.RenderPriority.HIGH);
			webView.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
		}
		webView.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);

		webView.loadDataWithBaseURL(null, html.toString(), "text/html", "utf-8", null);
 	}

}
