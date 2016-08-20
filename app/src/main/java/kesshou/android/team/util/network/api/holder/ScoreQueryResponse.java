package kesshou.android.team.util.network.api.holder;

import com.google.gson.JsonArray;
import com.google.gson.annotations.SerializedName;

/*
   Author: Charles Lien(lienching)
   Description: Score Query Response JSON holder
*/
public class ScoreQueryResponse {

    @SerializedName("subjects")
    JsonArray subjects;

}
