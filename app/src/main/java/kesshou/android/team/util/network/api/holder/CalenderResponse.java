package kesshou.android.team.util.network.api.holder;

import com.google.gson.annotations.SerializedName;

import java.util.Date;


/*
   Author: Charles Lien(lienching)
   Description: Calender Response JSON holder
*/
public class CalenderResponse{

    @SerializedName("date")
    public Date data;

    @SerializedName("content")
    public String content;


}
