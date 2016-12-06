package kesshou.android.team.util.network.api;

import java.util.List;

import kesshou.android.team.util.network.api.holder.CalenderResponse;
import retrofit2.Call;
import retrofit2.http.GET;

/*
   Author: Charles Lien(lienching)
   Description: Calender Api interface
*/
public interface CalenderApi {

    @GET("calender")
    Call<List<CalenderResponse>> getCalender();
}
