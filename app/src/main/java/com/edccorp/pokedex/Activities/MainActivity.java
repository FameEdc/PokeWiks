package com.edccorp.pokedex.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.edccorp.pokedex.Adapters.ListAdapter;
import com.edccorp.pokedex.Models.PokemonModel;
import com.edccorp.pokedex.Models.PokemonResponse;
import com.edccorp.pokedex.PokeApi.ApiService;
import com.edccorp.pokedex.R;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "POKEDEX";
    private Retrofit retrofit;
    private RecyclerView recyclerView;
    private ListAdapter listAdapter;
    private int offset;

    private boolean fitLoad;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        listAdapter = new ListAdapter(this);
        recyclerView.setAdapter(listAdapter);
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


        retrofit = new Retrofit.Builder()
                .baseUrl("http://pokeapi.co/api/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        fitLoad = true;
        offset = 0;

        getData(offset);
    }

    private void getData(int offset) {

        ApiService service = retrofit.create(ApiService.class);
        Call<PokemonResponse> pokemonResponseCall = service.getListPokemon(20, offset);

        pokemonResponseCall.enqueue(new Callback<PokemonResponse>() {
            @Override
            public void onResponse(Call<PokemonResponse> call, Response<PokemonResponse> response) {

                fitLoad = true;

                if (response.isSuccessful()) {

                    PokemonResponse pokemonResponse = response.body();
                    ArrayList<PokemonModel> listPokemonModel = pokemonResponse.getResults();

                    listAdapter.addList(listPokemonModel);

                }

                else {
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
