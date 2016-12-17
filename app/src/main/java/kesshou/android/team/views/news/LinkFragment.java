package kesshou.android.team.views.news;


import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.support.customtabs.CustomTabsIntent;
import android.support.v4.content.ContextCompat;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import io.realm.Realm;
import kesshou.android.team.R;
import kesshou.android.team.models.NetWorkCache;
import kesshou.android.team.util.UnitConvert;
import kesshou.android.team.util.network.MyCallBack;
import kesshou.android.team.util.network.NetworkingClient;
import kesshou.android.team.util.network.api.holder.Error;
import kesshou.android.team.util.network.api.holder.RelatedlinkResponse;
import retrofit2.Response;

/**
 * A simple class.
 */
public class LinkFragment {


	public LinkFragment() {
		// Required empty public constructor
	}


	public View onCreateView(final Context context) {
		// Inflate the layout for this fragment
		final View view = LayoutInflater.from(context.getApplicationContext()).inflate(R.layout.fragment_qanda,null);

		Realm realm = Realm.getDefaultInstance();

		NetWorkCache netWorkCache = realm.where(NetWorkCache.class).findFirst();

		final NetworkingClient networkingClient = new NetworkingClient(context.getApplicationContext());

		final Gson gson = new Gson();

		final LinearLayout layout = (LinearLayout) view.findViewById(R.id.list);

		if(netWorkCache.relatedlink==null){
			networkingClient.getLink(new MyCallBack<List<RelatedlinkResponse>>(context) {
				@Override
				public void onSuccess(Response<List<RelatedlinkResponse>> response) {
					Realm realm = Realm.getDefaultInstance();
					realm.beginTransaction();
					NetWorkCache netWorkCache = realm.where(NetWorkCache.class).findFirst();
					netWorkCache.relatedlink=gson.toJson(response.body());
					realm.commitTransaction();
					drawBody(context,layout,response.body());
				}

				@Override
				public void onErr(Error error) {

				}
			});
		}else{
			Type listType = new TypeToken<List<RelatedlinkResponse>>() {}.getType();
			List<RelatedlinkResponse> qandas = gson.fromJson(netWorkCache.relatedlink,listType);
			drawBody(context,layout,qandas);

			networkingClient.getLink(new MyCallBack<List<RelatedlinkResponse>>(context) {
				@Override
				public void onSuccess(Response<List<RelatedlinkResponse>> response) {
					Realm realm = Realm.getDefaultInstance();
					realm.beginTransaction();
					NetWorkCache netWorkCache = realm.where(NetWorkCache.class).findFirst();
					netWorkCache.relatedlink=gson.toJson(response.body());
					realm.commitTransaction();
					drawBody(context,layout,response.body());
				}

				@Override
				public void onErr(Error error) {

				}
			});
		}

		realm.close();

		return view;
	}

	private void drawBody(final Context context, LinearLayout layout, final List<RelatedlinkResponse> links){
		layout.removeAllViews();


		SpannableStringBuilder stringBuilder = new SpannableStringBuilder();
		for(int i=0;i<links.size();i++) {
			final int iter = i;
			SpannableString linkSrc = new SpannableString(links.get(iter).link+"\n");
			linkSrc.setSpan(new ClickableSpan() {
				@Override
				public void onClick(View view) {
					CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
					builder.setToolbarColor(ContextCompat.getColor(context,R.color.colorPrimary));
					CustomTabsIntent customTabsIntent = builder.build();
					customTabsIntent.intent.setPackage("com.android.chrome");
					customTabsIntent.launchUrl(context, Uri.parse(links.get(iter).link));
				}
			},0,links.get(i).link.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
			stringBuilder.append(iter+1+".");
			stringBuilder.append(links.get(iter).name);
			stringBuilder.append("\n");
			stringBuilder.append(linkSrc);
			stringBuilder.append("\n");
			stringBuilder.append("\n");
		}

		TextView txtQ = new TextView(context);
		txtQ.setText(stringBuilder);
		txtQ.setMovementMethod(LinkMovementMethod.getInstance());
		txtQ.setTextSize(20);
		txtQ.setTextColor(Color.BLACK);
		txtQ.setPadding((int) UnitConvert.Dp2Pixel(10,context),(int)UnitConvert.Dp2Pixel(10,context),(int)UnitConvert.Dp2Pixel(10,context),(int)UnitConvert.Dp2Pixel(10,context));
		LinearLayout.LayoutParams llpQ = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
		llpQ.setMargins((int)UnitConvert.Dp2Pixel(6,context),(int)UnitConvert.Dp2Pixel(6,context),(int)UnitConvert.Dp2Pixel(6,context),(int)UnitConvert.Dp2Pixel(6,context));
		txtQ.setLayoutParams(llpQ);
		layout.addView(txtQ);
	}


}
