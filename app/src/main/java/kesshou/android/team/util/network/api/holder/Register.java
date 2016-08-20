package kesshou.android.team.util.network.api.holder;

import com.google.gson.annotations.SerializedName;

/*
   Author: Charles Lien(lienching)
   Description: Register Information JSON holder
*/
public class Register {

    @SerializedName("email")
    String usr_email;

    @SerializedName("password")
    String usr_passwd;

    @SerializedName("user_group")
    String usr_group;

    @SerializedName("school_account")
    String school_account;

    @SerializedName("school_pwd")
    String school_pwd;

    @SerializedName("nick")
    String nickname;

}
