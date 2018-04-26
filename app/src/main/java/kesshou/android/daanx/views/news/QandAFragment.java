package kesshou.android.daanx.views.news;


import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.text.method.LinkMovementMethod;
import android.text.util.Linkify;
import android.view.Gravity;
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
import kesshou.android.daanx.util.UnitConvert;
import kesshou.android.daanx.util.network.MyCallBack;
import kesshou.android.daanx.util.network.NetworkingClient;
import kesshou.android.daanx.util.network.api.holder.Error;
import kesshou.android.daanx.util.network.api.holder.QandaResponse;
import retrofit2.Call;
import retrofit2.Response;

/**
 * A simple class.
 */
public class QandAFragment{


	public QandAFragment() {
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

		if(netWorkCache.qanda==null){
			networkingClient.getQanda(new MyCallBack<List<QandaResponse>>(context) {
				@Override
				public void onSuccess(Response<List<QandaResponse>> response) {
					Realm realm = Realm.getDefaultInstance();
					realm.beginTransaction();
					NetWorkCache netWorkCache = realm.where(NetWorkCache.class).findFirst();
					netWorkCache.qanda=gson.toJson(response.body());
					realm.commitTransaction();
					realm.close();
					drawBody(context,layout,response.body());
				}

				@Override
				public void onErr(Error error, Call<List<QandaResponse>> call) {

				}
			});
		}else{
			Type listType = new TypeToken<List<QandaResponse>>() {}.getType();
			List<QandaResponse> qandas = gson.fromJson(netWorkCache.qanda,listType);
			drawBody(context,layout,qandas);

			networkingClient.getQanda(new MyCallBack<List<QandaResponse>>(context) {
				@Override
				public void onSuccess(Response<List<QandaResponse>> response) {
					Realm realm = Realm.getDefaultInstance();
					realm.beginTransaction();
					NetWorkCache netWorkCache = realm.where(NetWorkCache.class).findFirst();
					netWorkCache.qanda=gson.toJson(response.body());
					realm.commitTransaction();
					realm.close();
					drawBody(context,layout,response.body());
				}

				@Override
				public void onErr(Error error, Call<List<QandaResponse>> call) {

				}
			});
		}

		realm.close();

		return view;
	}

	private void drawBody(Context context,LinearLayout layout,List<QandaResponse> qandas){
		layout.removeAllViews();

		GradientDrawable gdQ = new GradientDrawable();
		gdQ.setColor(ContextCompat.getColor(context,R.color.blue_500));
		gdQ.setCornerRadius(UnitConvert.Dp2Pixel(20,context));

		GradientDrawable gdA = new GradientDrawable();
		gdA.setColor(ContextCompat.getColor(context,R.color.grey_200));
		gdA.setCornerRadius(UnitConvert.Dp2Pixel(20,context));

		for(int i=0;i<qandas.size();i++) {
			TextView txtQ = new TextView(context);
			txtQ.setText(qandas.get(i).Q);
			txtQ.setTextSize(16);
			txtQ.setTextColor(Color.BLACK);
			txtQ.setAutoLinkMask(Linkify.ALL);
			txtQ.setMovementMethod(LinkMovementMethod.getInstance());
			if(Build.VERSION.SDK_INT >= 16) {
				txtQ.setBackground(gdQ);
			}else{
				txtQ.setBackgroundDrawable(gdQ);
			}
			txtQ.setGravity(Gravity.LEFT);
			txtQ.setPadding((int)UnitConvert.Dp2Pixel(10,context),(int)UnitConvert.Dp2Pixel(10,context),(int)UnitConvert.Dp2Pixel(10,context),(int)UnitConvert.Dp2Pixel(10,context));
			LinearLayout.LayoutParams llpQ = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
			llpQ.setMargins((int)UnitConvert.Dp2Pixel(6,context),(int)UnitConvert.Dp2Pixel(6,context),(int)UnitConvert.Dp2Pixel(6,context),(int)UnitConvert.Dp2Pixel(6,context));
			llpQ.gravity = Gravity.LEFT | Gravity.CENTER_VERTICAL;
			txtQ.setLayoutParams(llpQ);
			txtQ.setMaxWidth((int)UnitConvert.getScreenWidth(context)/4*3);
			layout.addView(txtQ);

			TextView txtA = new TextView(context);
			txtA.setText(qandas.get(i).A);
			txtA.setTextSize(16);
			txtA.setTextColor(Color.BLACK);
			if(Build.VERSION.SDK_INT >= 16) {
				txtA.setBackground(gdA);
			}else{
				txtA.setBackgroundDrawable(gdA);
			}
			txtA.setGravity(Gravity.RIGHT);
			txtA.setAutoLinkMask(Linkify.ALL);
			txtA.setMovementMethod(LinkMovementMethod.getInstance());
			txtA.setPadding((int)UnitConvert.Dp2Pixel(10,context),(int)UnitConvert.Dp2Pixel(10,context),(int)UnitConvert.Dp2Pixel(10,context),(int)UnitConvert.Dp2Pixel(10,context));
			LinearLayout.LayoutParams llpA = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
			llpA.setMargins((int)UnitConvert.Dp2Pixel(6,context),(int)UnitConvert.Dp2Pixel(6,context),(int)UnitConvert.Dp2Pixel(6,context),(int)UnitConvert.Dp2Pixel(6,context));
			llpA.gravity = Gravity.RIGHT | Gravity.CENTER_VERTICAL;
			txtA.setLayoutParams(llpA);
			txtA.setMaxWidth((int)UnitConvert.getScreenWidth(context)/4*3);
			layout.addView(txtA);
		}
	}


}
