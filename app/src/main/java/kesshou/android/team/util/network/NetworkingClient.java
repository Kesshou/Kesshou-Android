package kesshou.android.team.util.network;


import android.content.res.Resources;
import android.support.v4.util.Pair;
import android.util.Log;

import java.util.List;

import kesshou.android.team.R;
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
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/*
   Author: Charles Lien(lienching)
   Description: This class handle the communication of our RESTful API
*/

public class NetworkingClient {

    private final String TAG = "NetworkingClient";

    private final Retrofit retrofit;
    private AccountApi accountApi;
    private ScoreApi scoreApi;
    private CalenderApi calenderApi;


    public NetworkingClient() {
        retrofit = new Retrofit.Builder()
                .baseUrl(Config.getAPIPath())
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        accountApi = retrofit.create(AccountApi.class);
        scoreApi = retrofit.create(ScoreApi.class);
        calenderApi = retrofit.create(CalenderApi.class);
    }


    // Account System

    /*
        Author: Charles Lien( lienching)
        Description:
            This function's purpose is to communicate with login api
        Usage:
            parameter:
                Login usr: login information
            return:
                Pair<Integer, Response<Token>>
                    Integer statuscode : Status Code
                    Response<Token> response : Response Body

     */
    public Pair<Integer, Response<Token>> login(Login usr) {
        Response<Token> response = accountApi.login(usr);
        int statuscode = response.code();

        if ( statuscode == 401) {
            Log.d(TAG, Resources.getSystem().getString( R.string.unauthorized ) );
        }
        else if (statuscode == 406) {
            Log.d(TAG, Resources.getSystem().getString( R.string.illegalcontent) );
        }
        else if ( statuscode == 408 ) {
            Log.d(TAG, Resources.getSystem().getString( R.string.token_expired) );
        }

        return new Pair<>(statuscode, response);
    }

    /*
        Author: Charles Lien( lienching)
        Description:
            This function's purpose is to communicate with register api
        Usage:
            parameter:
                Register register: register information
            return:
                Pair<Integer, Response<Token>>
                    Integer statuscode : Status Code
                    Response<Token> response : Response Body

     */
    public Pair<Integer, Response<Token>> register(Register register) {
        Response<Token> response = accountApi.create(register);
        int statuscode = response.code();

        if ( statuscode == 401) {
            Log.d(TAG, Resources.getSystem().getString(R.string.id_used));
        }
        else if (statuscode == 406) {
            Log.d(TAG, Resources.getSystem().getString(R.string.school_unauthorized) +
                    "or" +Resources.getSystem().getString(R.string.illegalcontent));
        }


        return new Pair<>(statuscode, response);
    }

    /*
        Author: Charles Lien( lienching)
        Description:
            This function's purpose is to communicate with update api
        Usage:
            parameter:
                Update usr: update information
            return:
                Integer : status code
     */
    public int update(Update usr) {
        Response<Object> response = accountApi.update(usr);

        return response.code();
    }

    // Score System


    /*
        Author: Charles Lien( lienching)
        Description:
            This function's purpose is to communicate with history score query api
        Usage:
            parameter:
                HistoryScore score : Query Information:
            return:
                Pair<Integer, Response<ScoreQueryResponse>>
                    Integer statuscode : Status Code
                    Response<ScoreQueryResponse> response : Response Body
     */
    public Pair<Integer, Response<ScoreQueryResponse>> queryHScore(HistoryScore score) {
        Response<ScoreQueryResponse> response = scoreApi.queryHScore(score);
        int statuscode = response.code();

        if ( statuscode == 406 ) {
            Log.d(TAG, Resources.getSystem().getString( R.string.school_unauthorized) );
        }
        else if ( statuscode == 408 ) {
            Log.d(TAG, Resources.getSystem().getString( R.string.token_expired) );
        }

        return new Pair<>(statuscode, response);

    }

    /*
        Author: Charles Lien(lienching)
        Description:
            This function's purpose is to communicate with sectional exam score query api
        Usage:
            parameter:
                SectionalExamScore score : Query Information:
            return:
                Pair<Integer, Response<ScoreQueryResponse>>
                    Integer statuscode : Status Code
                    Response<ScoreQueryResponse> response : Response Body
     */
    public Pair<Integer, Response<ScoreQueryResponse>> querySEScore(SectionalExamScore score) {
        Response<ScoreQueryResponse> response = scoreApi.querySEScore(score);
        int statuscode = response.code();

        if ( statuscode == 406 ) {
            Log.d(TAG, Resources.getSystem().getString( R.string.school_unauthorized) );
        }
        else if ( statuscode == 408 ) {
            Log.d(TAG, Resources.getSystem().getString( R.string.token_expired) );
        }

        return new Pair<>(statuscode, response);
    }

    /*
       Author: Charles Lien(lienching)
       Description:
           This function's purpose is to communicate with attitude status query api
       Usage:
           parameter:
               SectionalExamScore score : Query Information:
           return:
               Pair<Integer, Response<List<AttitudeStatusResponse>>
                   Integer statuscode : Status Code
                   Response<List<AttitudeStatusResponse>> response : Response Body
    */
    public Pair<Integer, Response<List<AttitudeStatusResponse>>> queryAS(Token token) {
        Response<List<AttitudeStatusResponse>> response = scoreApi.queryAS(token);
        int statuscode = response.code();

        if ( statuscode == 406 ) {
            Log.d(TAG, Resources.getSystem().getString( R.string.school_unauthorized) );
        }
        else if ( statuscode == 408 ) {
            Log.d(TAG, Resources.getSystem().getString( R.string.token_expired) );
        }

        return new Pair<>(statuscode, response);
    }

    // Calender

    /*
      Author: Charles Lien(lienching)
      Description:
          This function's purpose is to communicate with calender query api
      Usage:
          parameter:
              SectionalExamScore score : Query Information:
          return:
              Pair<Integer, Response<List<CalenderResponse>>
                  Integer statuscode : Status Code
                  Response<List<CalenderResponse>> response : Response Body
   */
    public Pair<Integer, Response<List<CalenderResponse>>> getCurrentCalender(Token token) {
        Response<List<CalenderResponse>> response = calenderApi.getCurrentCalender(token);
        int statuscode = response.code();

        if ( statuscode == 406 ) {
            Log.d(TAG, Resources.getSystem().getString( R.string.school_unauthorized) );
        }
        else if ( statuscode == 408 ) {
            Log.d(TAG, Resources.getSystem().getString( R.string.token_expired) );
        }

        return new Pair<>(statuscode, response);
    }



}

