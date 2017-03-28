package com.edccorp.pokedex.Models;

import java.util.ArrayList;

/**
 * Created by ribei on 25/03/2017.
 */

public class PokemonResponse {

    private ArrayList<PokemonModel> results;

    public ArrayList<PokemonModel> getResults() {
        return results;
    }

    public void setResults(ArrayList<PokemonModel> results) {
        this.results = results;
    }

}
