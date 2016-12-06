package kesshou.android.team.util.network.api.holder;

import com.google.gson.annotations.SerializedName;

/*
   Author: Charles Lien(lienching)
   Description: Register Information JSON holder
*/
public class Register {

    @SerializedName("email")
    public String usr_email;

    @SerializedName("password")
    public String usr_passwd;

    @SerializedName("user_group")
    public String usr_group;

    @SerializedName("school_account")
    public String school_account;

    @SerializedName("school_pwd")
    public String school_pwd;

    @SerializedName("nick")
    public String nickname;

	@SerializedName("name")
	public String name;

}
