package kesshou.android.team.util.network.api.holder;

import com.google.gson.annotations.SerializedName;

/*
   Author: Charles Lien(lienching)
   Description: History Score Information JSON holder
*/
public class HistoryScore {

    @SerializedName("grade")
    public String grade;

    @SerializedName("semester")
    public String semester;

    @SerializedName("token")
    public String token;
}
