package kesshou.android.team.util.network.api;

import java.util.List;

import kesshou.android.team.util.network.api.holder.CalenderResponse;
import kesshou.android.team.util.network.api.holder.Token;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/*
   Author: Charles Lien(lienching)
   Description: Calender Api interface
*/
public interface CalenderApi {

    @POST("/calender")
    Call<List<CalenderResponse>> getCurrentCalender(@Body Token token);
}
