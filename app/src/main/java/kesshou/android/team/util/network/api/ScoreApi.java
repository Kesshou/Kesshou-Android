package kesshou.android.team.util.network.api;

import java.util.List;

import kesshou.android.team.util.network.api.holder.AttitudeStatusResponse;
import kesshou.android.team.util.network.api.holder.HistoryScore;
import kesshou.android.team.util.network.api.holder.ScoreQueryResponse;
import kesshou.android.team.util.network.api.holder.SectionalExamScore;
import kesshou.android.team.util.network.api.holder.Token;
import retrofit2.Response;
import retrofit2.http.POST;

/*
   Author: Charles Lien(lienching)
   Description: Score Api interface
*/
public interface ScoreApi {

    @POST("/scorequery/historyscore")
    Response<ScoreQueryResponse> queryHScore(HistoryScore score); // History Score

    @POST("/scorequery/sectionalexamscore")
    Response<ScoreQueryResponse> querySEScore(SectionalExamScore score); // Sectional Exam Score

    @POST("/scorequery/attitudestatus")
    Response<List<AttitudeStatusResponse>> queryAS(Token token); // Attitude Status

}
