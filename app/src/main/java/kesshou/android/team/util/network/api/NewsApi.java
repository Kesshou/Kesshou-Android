package kesshou.android.team.util.network.api;

import java.util.List;

import kesshou.android.team.util.network.api.holder.AnnounceResponse;
import kesshou.android.team.util.network.api.holder.CalenderResponse;
import kesshou.android.team.util.network.api.holder.QandaResponse;
import kesshou.android.team.util.network.api.holder.RelatedlinkResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/*
   Author: Charles Lien(lienching)
   Description: Calender Api interface
*/
public interface NewsApi {

    @GET("calendar")
    Call<List<CalenderResponse>> getCalender();

	@GET("announce/{sort}")
	Call<List<AnnounceResponse>> getAnnounce(@Path("sort") String sort);

	@GET("QandA")
	Call<List<QandaResponse>> getQandA();

	@GET("relatedlink")
	Call<List<RelatedlinkResponse>> getLink();
}
