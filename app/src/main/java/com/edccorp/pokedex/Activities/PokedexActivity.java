package com.edccorp.pokedex.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.edccorp.pokedex.Adapters.PokedexAdapter;
import com.edccorp.pokedex.Models.PokemonModel;
import com.edccorp.pokedex.Models.PokemonResponse;
import com.edccorp.pokedex.PokeApi.PokeEndpoint;
import com.edccorp.pokedex.R;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import static com.edccorp.pokedex.PokeApplication.POKE_API;

public class PokedexActivity extends AppCompatActivity {

    private static final String TAG = "POKEDEX";
    private PokedexAdapter pokedexAdapter;
    private int offset;

    private boolean fitLoad;
    private PokeEndpoint pokeEndpoint;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokedex);
        pokeEndpoint = (PokeEndpoint) getApplication().getSystemService(POKE_API);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        pokedexAdapter = new PokedexAdapter(this);
        recyclerView.setAdapter(pokedexAdapter);
        recyclerView.setHasFixedSize(true);
        final GridLayoutManager layoutManager = new GridLayoutManager(this, 3);
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 0) {
                    int visibleItemCount = layoutManager.getChildCount();
                    int totalItemCount = layoutManager.getItemCount();
                    int pastVisibleItems = layoutManager.findFirstVisibleItemPosition();
                    if (fitLoad) {
                        if ((visibleItemCount + pastVisibleItems) >= totalItemCount ) {
                            Log.i(TAG, " Final.");

                            fitLoad = false;
                            offset += 20;
                            getData(offset);
                        }
                    }
                }
            }
        });
        fitLoad = true;
        offset = 0;

        getData(offset);
    }

    private void getData(int offset) {
        Call<PokemonResponse> pokemonResponseCall = pokeEndpoint.listPokemons(20, offset);

        pokemonResponseCall.enqueue(new Callback<PokemonResponse>() {
            @Override
            public void onResponse(Call<PokemonResponse> call, Response<PokemonResponse> response) {
                fitLoad = true;
                if (response.isSuccessful()) {
                    PokemonResponse pokemonResponse = response.body();
                    ArrayList<PokemonModel> listPokemonModel = pokemonResponse.getResults();
                    pokedexAdapter.addList(listPokemonModel);
                } else {
                    Log.e(TAG, " onResponse " + response.errorBody());
                }
            }


            @Override
            public void onFailure(Call<PokemonResponse> call, Throwable t) {
                fitLoad = true;
                Log.e(TAG, " onFailure " + t.getMessage());
            }
        });
    }
}
