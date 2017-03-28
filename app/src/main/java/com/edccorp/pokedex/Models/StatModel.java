package com.edccorp.pokedex.Models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by ribei on 25/03/2017.
 */

public class StatModel {

    @SerializedName("")
    private ArrayList<StatDetails> statDetails;

    public StatModel(ArrayList<StatDetails> statDetails) {
        this.statDetails = statDetails;
    }

    public ArrayList<StatDetails> getStatDetails() {
        return statDetails;
    }

    public void setStatDetails(ArrayList<StatDetails> statDetails) {
        this.statDetails = statDetails;
    }
}
