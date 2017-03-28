package com.edccorp.pokedex.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.edccorp.pokedex.Models.PokemonModel;
import com.edccorp.pokedex.Models.StatModel;
import com.edccorp.pokedex.PokeApi.ApiService;
import com.edccorp.pokedex.R;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class StatActivity extends AppCompatActivity {

    public static final String ARG_EXAMPLE_IMAGE_URL = "example_image_url";
    //private Retrofit retrofit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stat);


        PokemonModel pokM = (PokemonModel)getIntent().getExtras().get("NAME");
        TextView txtName = (TextView)findViewById(R.id.txtName);

        txtName.setText(pokM.getName());

        ImageView poke = (ImageView)findViewById(R.id.imgPok);


        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        String exampleImageUrl = extras.getString(ARG_EXAMPLE_IMAGE_URL);

        Glide.with(this)
                .load(exampleImageUrl)
                .into(poke);

        /*TextView statAtk = (TextView) findViewById(R.id.txtAtk);
        TextView statDef = (TextView) findViewById(R.id.txtDef);

        getStatData(pokM); */

    }

    /*private void getStatData(PokemonModel pok) {

        ApiService service = PokedexActivity.retrofit.create(ApiService.class);
        Call<StatModel> statModelCall = service.getStatPok(String.valueOf(pok.getNumber()));

        statModelCall.enqueue(new Callback<StatModel>() {
            @Override
            public void onResponse(Call<StatModel> call, Response<StatModel> response) {

                 response.body().getStatDetails().get(4);

            }

            @Override
            public void onFailure(Call<StatModel> call, Throwable t) {

            }
        });

    } */




}

