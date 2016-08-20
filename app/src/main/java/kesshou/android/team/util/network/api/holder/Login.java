package kesshou.android.team.util.network.api.holder;

import com.google.gson.annotations.SerializedName;

/*
   Author: Charles Lien(lienching)
   Description: Login Information JSON holder
*/
public class Login {

    @SerializedName("account")
    String usr_account;

    @SerializedName("password")
    String usr_password;

}
