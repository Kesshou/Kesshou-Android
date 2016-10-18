package kesshou.android.team.util.network;


import android.content.Context;

import java.util.List;

import kesshou.android.team.util.network.api.AccountApi;
import kesshou.android.team.util.network.api.CalenderApi;
import kesshou.android.team.util.network.api.ScoreApi;
import kesshou.android.team.util.network.api.holder.AttitudeStatusResponse;
import kesshou.android.team.util.network.api.holder.CalenderResponse;
import kesshou.android.team.util.network.api.holder.HistoryScore;
import kesshou.android.team.util.network.api.holder.Login;
import kesshou.android.team.util.network.api.holder.Register;
import kesshou.android.team.util.network.api.holder.ScoreQueryResponse;
import kesshou.android.team.util.network.api.holder.SectionalExamScore;
import kesshou.android.team.util.network.api.holder.Token;
import kesshou.android.team.util.network.api.holder.Update;
import kesshou.android.team.util.network.client.RetrofitClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;

/*
   Author: Charles Lien(lienching),IU(yoyo930021)
   Description: This class handle the communication of our RESTful API
*/

public class NetworkingClient {

	private Retrofit retrofit;
    private AccountApi accountApi;
    private ScoreApi scoreApi;
    private CalenderApi calenderApi;


    public NetworkingClient(Context context) {
        retrofit = RetrofitClient.getInstance(context);

        accountApi = retrofit.create(AccountApi.class);
        scoreApi = retrofit.create(ScoreApi.class);
        calenderApi = retrofit.create(CalenderApi.class);
    }

    // Account System

    /*
        Author: Charles Lien( lienching),IU(yoyo930021)
        Description:
            This function's purpose is to communicate with login api
        Usage:
            parameter:
                Login usr: login information
                Callback<Token> callback: Callback
     */
    public void login(Login usr,Callback<Token> callback) {
        Call<Token> call = accountApi.login(usr);
	    call.enqueue(callback);
    }

    /*
        Author: Charles Lien( lienching),IU(yoyo930021)
        Description:
            This function's purpose is to communicate with register api
        Usage:
            parameter:
                Register register: register information
	            Callback<Token> callback: Callback

     */
    public void register(Register register,Callback<Token> callback) {
        Call<Token> call = accountApi.create(register);
        call.enqueue(callback);
    }

    /*
        Author: Charles Lien( lienching),IU(yoyo930021)
        Description:
            This function's purpose is to communicate with update api
        Usage:
            parameter:
                Update usr: update information
	            Callback<Object> callback: Callback
     */
    public void update(Update usr,Callback<Object> callback) {
	    Call<Object> call = accountApi.update(usr);
	    call.enqueue(callback);
    }

    // Score System


    /*
        Author: Charles Lien( lienching),IU(yoyo930021)
        Description:
            This function's purpose is to communicate with history score query api
        Usage:
            parameter:
                HistoryScore score : Query Information:
	            Callback<ScoreQueryResponse> callback: Callback
     */
    public void queryHScore(HistoryScore score,Callback<ScoreQueryResponse> callback) {
	    Call<ScoreQueryResponse> call = scoreApi.queryHScore(score);
        call.enqueue(callback);
    }

    /*
        Author: Charles Lien(lienching),IU(yoyo930021)
        Description:
            This function's purpose is to communicate with sectional exam score query api
        Usage:
            parameter:
                SectionalExamScore score : Query Information:
                Callback<ScoreQueryResponse> callback: Callback
     */
    public void querySEScore(SectionalExamScore score,Callback<ScoreQueryResponse> callback) {
        Call<ScoreQueryResponse> call = scoreApi.querySEScore(score);
        call.enqueue(callback);
    }

    /*
       Author: Charles Lien(lienching),IU(yoyo930021)
       Description:
           This function's purpose is to communicate with attitude status query api
       Usage:
           parameter:
               SectionalExamScore score : Query Information:
	           Callback<List<AttitudeStatusResponse>> callback: Callback
    */
    public void queryAS(Token token,Callback<List<AttitudeStatusResponse>> callback) {
        Call<List<AttitudeStatusResponse>> call = scoreApi.queryAS(token);
        call.enqueue(callback);
    }

    // Calender

    /*
      Author: Charles Lien(lienching),IU(yoyo930021)
      Description:
          This function's purpose is to communicate with calender query api
      Usage:
          parameter:
              SectionalExamScore score : Query Information:
	          Callback<List<CalenderResponse>> callback: Callback
   */
    public void getCurrentCalender(Token token,Callback<List<CalenderResponse>> callback) {
        Call<List<CalenderResponse>> call = calenderApi.getCurrentCalender(token);
        call.enqueue(callback);
    }



}

