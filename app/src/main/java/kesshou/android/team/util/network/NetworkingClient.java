package kesshou.android.team.util.network;


import android.content.Context;

import java.util.List;

import io.realm.Realm;
import kesshou.android.team.models.Setting;
import kesshou.android.team.util.network.api.AccountApi;
import kesshou.android.team.util.network.api.CalenderApi;
import kesshou.android.team.util.network.api.InforApi;
import kesshou.android.team.util.network.api.holder.AbsentstateResponse;
import kesshou.android.team.util.network.api.holder.AttitudeStatusResponse;
import kesshou.android.team.util.network.api.holder.CalenderResponse;
import kesshou.android.team.util.network.api.holder.CheckRegist;
import kesshou.android.team.util.network.api.holder.HistoryScoreResponse;
import kesshou.android.team.util.network.api.holder.Login;
import kesshou.android.team.util.network.api.holder.Register;
import kesshou.android.team.util.network.api.holder.SectionalExamResponse;
import kesshou.android.team.util.network.api.holder.StatusResponse;
import kesshou.android.team.util.network.api.holder.TimeTableResponse;
import kesshou.android.team.util.network.api.holder.Token;
import kesshou.android.team.util.network.api.holder.Update;
import kesshou.android.team.util.network.api.holder.UserInfoResponse;
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
    private InforApi inforApi;
    private CalenderApi calenderApi;
	private Context mContext;


    public NetworkingClient(Context context) {
	    mContext=context.getApplicationContext();
        retrofit = RetrofitClient.getInstance(mContext);

        accountApi = retrofit.create(AccountApi.class);
        inforApi = retrofit.create(InforApi.class);
        calenderApi = retrofit.create(CalenderApi.class);
    }

    // Account System

	/*
        Author: IU(yoyo930021)
        Description:
            This function's purpose is to communicate with login api
        Usage:
            parameter:
                Login usr: login information
                Callback<Token> callback: Callback
     */
	public void login(Login login,Callback<Token> callback) {

		Call<Token> call = accountApi.login(login);
		call.enqueue(callback);
	}

    /*
        Author: Charles Lien( lienching),IU(yoyo930021)
        Description:
            This function's purpose is to communicate with login api
        Usage:
            parameter:
                Login usr: login information
                Callback<Token> callback: Callback
     */
    public void login() {
	    Realm realm = Realm.getDefaultInstance();
	    Setting setting = realm.where(Setting.class).findFirst();
	    Login usr = new Login();
	    usr.usr_account = setting.email;
	    usr.usr_password = setting.password;
        Call<Token> call = accountApi.login(usr);
	    String token = "";
	    try {
		    token = call.execute().body().token;
	    }catch (Exception e){}

	    realm.beginTransaction();
	    setting.token = token;
		realm.commitTransaction();
	    realm.close();
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
        Author: IU(yoyo930021)
        Description:
            This function's purpose is to communicate with checkAccount api
        Usage:
            parameter:


     */
	public void checkAccount(CheckRegist checkAccount, Callback<StatusResponse> callback) {
		Call<StatusResponse> call = accountApi.checkAccount(checkAccount);
		call.enqueue(callback);
	}

	/*
        Author: IU(yoyo930021)
        Description:
            This function's purpose is to communicate with checkNick api
        Usage:
            parameter:


     */
	public void checkNick(CheckRegist checkNick, Callback<StatusResponse> callback) {
		Call<StatusResponse> call = accountApi.checkNick(checkNick);
		call.enqueue(callback);
	}

	/*
        Author: IU(yoyo930021)
        Description:
            This function's purpose is to communicate with checkNick api
        Usage:
            parameter:


     */
	public void checkSchool(CheckRegist checkSchool, Callback<StatusResponse> callback) {
		Call<StatusResponse> call = accountApi.checkSchool(checkSchool);
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
    public void update(Update usr,Callback<StatusResponse> callback) {
	    Call<StatusResponse> call = accountApi.update(usr);
	    call.enqueue(callback);
    }

	/*
        Author: IU(yoyo930021)
        Description:
            This function's purpose is to communicate with  api
        Usage:
            parameter:


     */
	public void getUserInfo(Callback<UserInfoResponse> callback) {
		Call<UserInfoResponse> call = accountApi.getUserInfo();
		call.enqueue(callback);
	}



    // Infor System

	/*
        Author: IU(yoyo930021)
        Description:
            This function's purpose is to communicate with  api
        Usage:
            parameter:


     */
	public void queryTimeTable(Callback<TimeTableResponse> callback) {
		Call<TimeTableResponse> call = inforApi.getTimeTable();
		call.enqueue(callback);
	}

	/*
        Author: IU(yoyo930021)
        Description:
            This function's purpose is to communicate with  api
        Usage:
            parameter:


     */
	public void getABS(Callback<List<AbsentstateResponse>> callback) {
		Call<List<AbsentstateResponse>> call = inforApi.getABS();
		call.enqueue(callback);
	}


    /*
        Author: Charles Lien( lienching),IU(yoyo930021)
        Description:
            This function's purpose is to communicate with history score query api
        Usage:
            parameter:
                HistoryScore score : Query Information:
	            Callback<SectionalExamResponse> callback: Callback
     */
    public void queryHScore(int grade,int semester,Callback<List<HistoryScoreResponse>> callback) {
	    Call<List<HistoryScoreResponse>> call = inforApi.queryHScore(grade,semester);
        call.enqueue(callback);
    }

    /*
        Author: Charles Lien(lienching),IU(yoyo930021)
        Description:
            This function's purpose is to communicate with sectional exam score query api
        Usage:
            parameter:
                SectionalExamScore score : Query Information:
                Callback<SectionalExamResponse> callback: Callback
     */
    public void querySEScore(int semester,Callback<List<SectionalExamResponse>> callback) {
        Call<List<SectionalExamResponse>> call = inforApi.querySEScore(semester);
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
    public void getATS(Callback<AttitudeStatusResponse> callback) {
        Call<AttitudeStatusResponse> call = inforApi.getATS();
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
    public void getCalender(Callback<List<CalenderResponse>> callback) {
        Call<List<CalenderResponse>> call = calenderApi.getCalender();
        call.enqueue(callback);
    }



}

