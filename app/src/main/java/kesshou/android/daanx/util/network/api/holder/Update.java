package kesshou.android.daanx.util.network.api.holder;

import com.google.gson.annotations.SerializedName;

/*
   Author: Charles Lien(lienching)
   Description: Update Information JSON holder
*/
public class Update {

    @SerializedName("password")
    public String password;

    @SerializedName("new_school_pwd")
    public String new_spwd;

    @SerializedName("new_nick")
    public String new_nick;

    @SerializedName("new_password")
    public String new_password;

    @SerializedName("new_email")
    public String new_email;
}
