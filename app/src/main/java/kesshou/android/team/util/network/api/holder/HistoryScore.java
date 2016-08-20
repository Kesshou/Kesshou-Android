package kesshou.android.team.util.network.api.holder;

import com.google.gson.annotations.SerializedName;

/*
   Author: Charles Lien(lienching)
   Description: History Score Information JSON holder
*/
public class HistoryScore {

    @SerializedName("grade")
    String grade;

    @SerializedName("semester")
    String semester;

    @SerializedName("token")
    String token;
}
