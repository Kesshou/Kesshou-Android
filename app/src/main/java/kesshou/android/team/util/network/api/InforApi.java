package kesshou.android.team.util.network.api;

import java.util.List;

import kesshou.android.team.util.network.api.holder.AbsentstateResponse;
import kesshou.android.team.util.network.api.holder.AttitudeStatusResponse;
import kesshou.android.team.util.network.api.holder.HistoryScoreResponse;
import kesshou.android.team.util.network.api.holder.SectionalExamResponse;
import kesshou.android.team.util.network.api.holder.TimeTableResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/*
   Author: Charles Lien(lienching)
   Description: Score Api interface
*/
public interface InforApi {

	@GET("scorequery/historyscore/{grade}/{semester}")
	Call<List<HistoryScoreResponse>> queryHScore(@Path("grade") int grade, @Path("semester") int semester); // History Score

	@GET("scorequery/sectionalexamscore/{semester}")
	Call<List<SectionalExamResponse>> querySEScore(@Path("semester") int semester); // Sectional Exam Score

	@GET("attitudestatus")
	Call<AttitudeStatusResponse> getATS(); // Attitude Status

	@GET("absentstate")
	Call<List<AbsentstateResponse>> getABS(); //Absent State

	@GET("curriculum")
	Call<TimeTableResponse> getTimeTable(); //TimeTableResponse







}
