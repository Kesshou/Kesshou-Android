package kesshou.android.team.util.network.api.holder;

import com.google.gson.annotations.SerializedName;

/*
   Author: Charles Lien(lienching)
   Description: Update Information JSON holder
*/
public class Update {

    @SerializedName("password")
    String password;

    @SerializedName("new_school_pwd")
    String new_spwd;

    @SerializedName("new_nick")
    String new_nick;

    @SerializedName("new_password")
    String new_password;

    @SerializedName("new_email")
    String new_email;
}
