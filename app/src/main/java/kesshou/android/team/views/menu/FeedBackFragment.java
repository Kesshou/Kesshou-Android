package kesshou.android.team.views.menu;


import android.content.Context;
import android.os.Build;
import android.support.design.widget.TextInputEditText;
import android.support.v7.widget.AppCompatButton;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.Toast;

import io.realm.Realm;
import kesshou.android.team.R;
import kesshou.android.team.util.network.MyCallBack;
import kesshou.android.team.util.network.NetworkingClient;
import kesshou.android.team.util.network.api.holder.Error;
import kesshou.android.team.util.network.api.holder.StatusResponse;
import retrofit2.Response;

/**
 * A simple class.
 */
public class FeedBackFragment {


	public FeedBackFragment() {
		// Required empty public constructor
	}


	public View onCreateView(final Context context) {
		// Inflate the layout for this fragment
		final View view = LayoutInflater.from(context.getApplicationContext()).inflate(R.layout.fragment_feed_back,null);

		Realm realm = Realm.getDefaultInstance();

		final NetworkingClient networkingClient = new NetworkingClient(context.getApplicationContext());

		AppCompatButton button = (AppCompatButton) view.findViewById(R.id.btn_submit);
		button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				String feedClass = "";
				RadioGroup rg = (RadioGroup) view.findViewById(R.id.rd_group);
				switch(rg.getCheckedRadioButtonId()){
					case R.id.rd_lag:
						feedClass = "lag問題";
						break;
					case R.id.rd_crash:
						feedClass = "crash問題";
						break;
					case R.id.rd_lose:
						feedClass = "功能失效";
						break;
					case R.id.rd_sug:
						feedClass = "建議";
						break;
				}
				String commit=((TextInputEditText) view.findViewById(R.id.fb_commit)).getText().toString();
				StringBuilder system = new StringBuilder();
				system.append("android version：");
				system.append(Build.VERSION.RELEASE);
				system.append("\n");
				system.append("company：");
				system.append(Build.MANUFACTURER);
				system.append("\n");
				system.append("mobile info：");
				system.append(Build.MODEL);
				system.append(" ");
				system.append(Build.BRAND);
				system.append("\n");
				system.append("fingerprint：");
				system.append(Build.FINGERPRINT);

				 networkingClient.postFB(feedClass, commit, system.toString() , new MyCallBack<StatusResponse>(context) {
					 @Override
					 public void onSuccess(Response<StatusResponse> response) {
						 Toast.makeText(context,"送出成功",Toast.LENGTH_SHORT).show();
					 }

					 @Override
					 public void onErr(Error error) {

					 }
				 });
			}
		});

		return view;
	}



}
