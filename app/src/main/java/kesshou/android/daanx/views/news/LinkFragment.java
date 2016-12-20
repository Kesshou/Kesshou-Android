package kesshou.android.daanx.views.news;


import android.content.Context;
import android.graphics.Color;
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
import kesshou.android.daanx.R;
import kesshou.android.daanx.models.NetWorkCache;
import kesshou.android.daanx.util.ChromeTabHelper;
import kesshou.android.daanx.util.UnitConvert;
import kesshou.android.daanx.util.network.MyCallBack;
import kesshou.android.daanx.util.network.NetworkingClient;
import kesshou.android.daanx.util.network.api.holder.Error;
import kesshou.android.daanx.util.network.api.holder.RelatedlinkResponse;
import retrofit2.Call;
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
		context.setTheme(R.style.AppTheme);
		context.getApplicationContext().setTheme(R.style.AppTheme);
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
					realm.close();
					drawBody(context,layout,response.body());
				}

				@Override
				public void onErr(Error error, Call<List<RelatedlinkResponse>> call) {

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
					realm.close();
					drawBody(context,layout,response.body());
				}

				@Override
				public void onErr(Error error, Call<List<RelatedlinkResponse>> call) {

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
					ChromeTabHelper.openTabs(context,links.get(iter).link);
				}
			},0,links.get(i).link.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
			stringBuilder.append(links.get(iter).name);
			stringBuilder.append("\n");
			stringBuilder.append(linkSrc);
			stringBuilder.append("\n");
			stringBuilder.append("\n");
		}

		TextView txtQ = new TextView(context);
		txtQ.setText(stringBuilder);
		txtQ.setMovementMethod(LinkMovementMethod.getInstance());
		txtQ.setTextSize(16);
		txtQ.setTextColor(Color.BLACK);
		txtQ.setPadding((int) UnitConvert.Dp2Pixel(10,context),(int)UnitConvert.Dp2Pixel(10,context),(int)UnitConvert.Dp2Pixel(10,context),(int)UnitConvert.Dp2Pixel(10,context));
		LinearLayout.LayoutParams llpQ = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
		llpQ.setMargins((int)UnitConvert.Dp2Pixel(6,context),(int)UnitConvert.Dp2Pixel(6,context),(int)UnitConvert.Dp2Pixel(6,context),(int)UnitConvert.Dp2Pixel(6,context));
		txtQ.setLayoutParams(llpQ);
		layout.addView(txtQ);
	}


}
