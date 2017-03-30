package com.edccorp.pokedex.Activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.edccorp.pokedex.Models.PokemonModel;
import com.edccorp.pokedex.Models.PokemonStats;
import com.edccorp.pokedex.PokeApi.PokeEndpoint;
import com.edccorp.pokedex.PokeApplication;
import com.edccorp.pokedex.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StatActivity extends AppCompatActivity {

    private static final String EXTRA_POKE = "EXTRA_POKE";
    private static final String TAG = StatActivity.class.getSimpleName();
    private PokeEndpoint pokeApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stat);
        pokeApi = (PokeEndpoint) getApplication().getSystemService(PokeApplication.POKE_API);
        PokemonModel pokM = getIntent().getParcelableExtra(EXTRA_POKE);
        TextView txtName = (TextView) findViewById(R.id.txtName);
        getPokeStats(pokM.getId());
        txtName.setText(pokM.getName());
        ImageView poke = (ImageView) findViewById(R.id.imgPok);
        Glide.with(this)
                .load(pokM.getImageUrl())
                .into(poke);
    }

    private void getPokeStats(String pokeId) {
        final LayoutInflater layoutInflater = LayoutInflater.from(this);
        final TableLayout tableLayout = (TableLayout) findViewById(R.id.tableLayout);
        Call<PokemonModel> pokemonResponseCall = pokeApi.getPokemon(pokeId);

        pokemonResponseCall.enqueue(new Callback<PokemonModel>() {
            @Override
            public void onResponse(Call<PokemonModel> call, final Response<PokemonModel> response) {
                if (response.isSuccessful()) {

                    final PokemonModel pokemon = response.body();
                    for (PokemonStats pokemonStats : pokemon.getStats()) {
                        View row = layoutInflater.inflate(R.layout.stat_row, null);
                        ((TextView) row.findViewById(R.id.label)).setText(pokemonStats.getName());
                        ((TextView) row.findViewById(R.id.value)).setText("" + pokemonStats.getValue());
                        tableLayout.addView(row);
                    }
                } else {
                    Log.e(TAG, " onResponse: " + response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<PokemonModel> call, Throwable t) {
                Log.e(TAG, " onFailure: " + t.getMessage());

            }
        });
    }

    public static Intent newIntent(Context context, PokemonModel p) {
        Intent intent = new Intent(context, StatActivity.class);
        intent.putExtra(EXTRA_POKE, p);
        return intent;
    }
}