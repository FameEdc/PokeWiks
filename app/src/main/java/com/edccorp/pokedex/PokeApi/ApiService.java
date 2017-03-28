package com.edccorp.pokedex.PokeApi;

import retrofit2.Call;

import com.edccorp.pokedex.Models.ItemResponse;
import com.edccorp.pokedex.Models.PokemonResponse;
import com.edccorp.pokedex.Models.StatModel;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by ribei on 25/03/2017.
 */

public interface ApiService {

    @GET("pokemon")
    Call<PokemonResponse> getListPokemon(@Query("limit") int limit, @Query("offset") int offset);

    @GET("item")
    Call<ItemResponse> getListItem(@Query("limit") int limit, @Query("offset") int offset);

    /*@GET("pokemon/{id}")
    Call<StatModel> getStatPok(@Path("id") String id); */

}
