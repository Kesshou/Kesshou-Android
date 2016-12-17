package kesshou.android.team.util.network.api;

import kesshou.android.team.util.network.api.holder.StatusResponse;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by yoyoIU on 2016/12/17.
 */

public interface MenuApi {

	@FormUrlEncoded
	@POST("feedback")
	Call<StatusResponse> postFB(@Field("feedClass") String feedClass,@Field("commit") String commit,@Field("system") String system);
}
