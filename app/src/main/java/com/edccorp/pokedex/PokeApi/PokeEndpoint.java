package com.edccorp.pokedex.PokeApi;

import retrofit2.Call;

import com.edccorp.pokedex.Models.ItemResponse;
import com.edccorp.pokedex.Models.PokemonModel;
import com.edccorp.pokedex.Models.PokemonResponse;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by ribei on 25/03/2017.
 */

public interface PokeEndpoint {

    @GET("pokemon")
    Call<PokemonResponse> listPokemons(@Query("limit") int limit, @Query("offset") int offset);

    @GET("item")
    Call<ItemResponse> listItems(@Query("limit") int limit, @Query("offset") int offset);

    @GET("pokemon/{id}")
    Call<PokemonModel> getPokemon(@Path("id") String id);

}
