package com.edccorp.pokedex.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;

import com.edccorp.pokedex.Activities.StatActivity;
import com.edccorp.pokedex.Models.PokemonModel;

/**
 * Created by ribei on 25/03/2017.
 */

public class PokedexAdapter extends Adapter<PokemonModel> {

    public PokedexAdapter(Context context) {
        super(context);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        final PokemonModel pokemonModel = datasetItem.get(position);
        holder.itemView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = StatActivity.newIntent(context, pokemonModel);
                context.startActivity(i);
            }
        });
    }
}


