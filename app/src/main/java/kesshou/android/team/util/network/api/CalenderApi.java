package kesshou.android.team.util.network.api;

import java.util.List;

import kesshou.android.team.util.network.api.holder.CalenderResponse;

import kesshou.android.team.util.network.api.holder.Token;
import retrofit2.Response;
import retrofit2.http.POST;

/*
   Author: Charles Lien(lienching)
   Description: Calender Api interface
*/
public interface CalenderApi {

    @POST("/calender")
    Response<List<CalenderResponse>> getCurrentCalender(Token token);
}
