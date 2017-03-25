package com.edccorp.pokedex.PokeApi;

import retrofit2.Call;
import com.edccorp.pokedex.Models.PokemonResponse;

import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by ribei on 25/03/2017.
 */

public interface ApiService {

    @GET("pokemon")
    Call<PokemonResponse> getListPokemon(@Query("limit") int limit, @Query("offset") int offset);
}
