package kesshou.android.team.views.Infor;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import com.google.gson.Gson;

import java.util.List;

import io.realm.Realm;
import kesshou.android.team.R;
import kesshou.android.team.models.NetWorkCache;
import kesshou.android.team.models.Strings;
import kesshou.android.team.util.network.MyCallBack;
import kesshou.android.team.util.network.NetworkingClient;
import kesshou.android.team.util.network.api.holder.Error;
import kesshou.android.team.util.network.api.holder.HistoryScoreResponse;
import retrofit2.Response;

/**
 * A simple class.
 */
public class Grade2Fragment{


	public Grade2Fragment() {
		// Required empty public constructor
	}


	public View onCreateView(Context context) {
		// Inflate the layout for this fragment
		View view = LayoutInflater.from(context.getApplicationContext()).inflate(R.layout.fragment_grade2, null);

		final Realm realm = Realm.getDefaultInstance();

		final NetWorkCache netWorkCache = realm.where(NetWorkCache.class).findFirst();

		NetworkingClient networkingClient = new NetworkingClient(context);

		final Gson gson = new Gson();

		if(netWorkCache.histroyScore1!=null && netWorkCache.histroyScore1.size() == 0){
			for(int i=3;i>0;i++){
				final Strings strings = new Strings();
				strings.index = i;
				networkingClient.queryHScore(i, 1, new MyCallBack<List<HistoryScoreResponse>>(context) {
					@Override
					public void onSuccess(Response<List<HistoryScoreResponse>> response) {
						strings.string = gson.toJson(response.body());
						realm.beginTransaction();
						netWorkCache.histroyScore1.add(strings);
						realm.commitTransaction();
					}

					@Override
					public void onErr(Error error) {

					}
				});
			}
		}

		if(netWorkCache.histroyScore2!=null && netWorkCache.histroyScore2.size() == 0){
			for(int i=3;i>0;i++){
				final Strings strings = new Strings();
				strings.index = i;
				networkingClient.queryHScore(i, 2, new MyCallBack<List<HistoryScoreResponse>>(context) {
					@Override
					public void onSuccess(Response<List<HistoryScoreResponse>> response) {
						strings.string = gson.toJson(response.body());
						realm.beginTransaction();
						netWorkCache.histroyScore2.add(strings);
						realm.commitTransaction();
					}

					@Override
					public void onErr(Error error) {

					}
				});
			}
		}



		realm.close();

		return view;
	}

}
