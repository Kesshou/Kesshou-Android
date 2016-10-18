package kesshou.android.team.util.network.api.holder;

import com.google.gson.annotations.SerializedName;

/*
   Author: Charles Lien(lienching)
   Description: Attitude Status Response JSON holder
*/
public class AttitudeStatusResponse {

    @SerializedName("date")
    public String date;

    @SerializedName("item")
    public String item;

    @SerializedName("text")
    public String content;
}
