package com.edccorp.pokedex;

import android.app.Application;

import com.edccorp.pokedex.PokeApi.PokeEndpoint;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class PokeApplication extends Application {
    public static final String POKE_API = "POKE_API";
    private PokeEndpoint endPoint;

    @Override
    public void onCreate() {
        super.onCreate();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://pokeapi.co/api/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        endPoint = retrofit.create(PokeEndpoint.class);
    }

    @Override
    public Object getSystemService(String name) {
        if (POKE_API.equals(name)) {
            return endPoint;
        }
        return super.getSystemService(name);
    }
}
