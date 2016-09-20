package kesshou.android.team.util.network.api;

import java.util.List;

import kesshou.android.team.util.network.api.holder.AttitudeStatusResponse;
import kesshou.android.team.util.network.api.holder.HistoryScore;
import kesshou.android.team.util.network.api.holder.ScoreQueryResponse;
import kesshou.android.team.util.network.api.holder.SectionalExamScore;
import kesshou.android.team.util.network.api.holder.Token;
import retrofit2.Call;
import retrofit2.http.POST;

/*
   Author: Charles Lien(lienching)
   Description: Score Api interface
*/
public interface ScoreApi {

    @POST("/scorequery/historyscore")
    Call<ScoreQueryResponse> queryHScore(HistoryScore score); // History Score

    @POST("/scorequery/sectionalexamscore")
    Call<ScoreQueryResponse> querySEScore(SectionalExamScore score); // Sectional Exam Score

    @POST("/scorequery/attitudestatus")
    Call<List<AttitudeStatusResponse>> queryAS(Token token); // Attitude Status

}
