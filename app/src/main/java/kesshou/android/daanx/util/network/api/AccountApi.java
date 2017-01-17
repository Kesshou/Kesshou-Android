package kesshou.android.daanx.util.network.api;

import kesshou.android.daanx.util.network.api.holder.CheckRegist;
import kesshou.android.daanx.util.network.api.holder.Login;
import kesshou.android.daanx.util.network.api.holder.Register;
import kesshou.android.daanx.util.network.api.holder.StatusResponse;
import kesshou.android.daanx.util.network.api.holder.Token;
import kesshou.android.daanx.util.network.api.holder.Update;
import kesshou.android.daanx.util.network.api.holder.UserInfoResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;

/*
   Author: Charles Lien(lienching)
   Description: Account Api interface
*/

public interface AccountApi {

    // Login
    @POST("actmanage/login")
    Call<Token> login(@Body Login usr);

    //Register
    @POST("actmanage/register")
    Call<Token> create(@Body Register register);

    // Edit Account
    @PUT("actmanage/updateinfo")
    Call<StatusResponse> update(@Body Update usr);

	//Check Account
	@POST("actmanage/confirmAccount")
	Call<StatusResponse> checkAccount(@Body CheckRegist checkAccount);

	//CheckNick
	@POST("actmanage/confirmNick")
	Call<StatusResponse> checkNick(@Body CheckRegist checkNick);

	//CheckSchoolAccount
	@POST("actmanage/confirmSchool")
	Call<StatusResponse> checkSchool(@Body CheckRegist checkSchoolAccount);

	//GetUserInfo
	@GET("actmanage/getUserInfo")
	Call<UserInfoResponse> getUserInfo();

	@FormUrlEncoded
	@POST("actmanage/setNoti")
	Call<StatusResponse> setNoti(@Field("fcm_token") String fcm_token,@Field("is_noti") boolean is_noti);
}
