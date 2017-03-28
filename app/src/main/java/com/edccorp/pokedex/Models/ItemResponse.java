package com.edccorp.pokedex.Models;

import java.util.ArrayList;

/**
 * Created by ribei on 25/03/2017.
 */

public class ItemResponse {

    private ArrayList<ItemModel> results;

    public ArrayList<ItemModel> getResults() {
        return results;
    }

    public void setResults(ArrayList<ItemModel> results) {
        this.results = results;
    }

}
