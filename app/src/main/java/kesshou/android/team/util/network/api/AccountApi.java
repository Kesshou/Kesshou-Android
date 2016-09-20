package kesshou.android.team.util.network.api;

import kesshou.android.team.util.network.api.holder.Login;
import kesshou.android.team.util.network.api.holder.Register;
import kesshou.android.team.util.network.api.holder.Token;
import kesshou.android.team.util.network.api.holder.Update;
import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.PUT;

/*
   Author: Charles Lien(lienching)
   Description: Account Api interface
*/

public interface AccountApi {

    // Login
    @POST("/actmanage/login")
    Call<Token> login(Login usr);


    //Register
    @POST("/actmanage/register")
    Call<Token> create(Register register);

    // Edit Account
    @PUT("/actmanage/updateinfo")
    Call<Object> update(Update usr);



}
