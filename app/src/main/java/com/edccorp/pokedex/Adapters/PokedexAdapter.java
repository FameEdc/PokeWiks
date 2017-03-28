package com.edccorp.pokedex.Adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.edccorp.pokedex.Activities.StatActivity;
import com.edccorp.pokedex.Models.PokemonModel;
import com.edccorp.pokedex.R;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by ribei on 25/03/2017.
 */

public class PokedexAdapter extends RecyclerView.Adapter<PokedexAdapter.ViewHolder> {

    private ArrayList<PokemonModel> dataset;
    private Context context;


    public PokedexAdapter(Context context) {
        this.context = context;
        dataset = new ArrayList<>();

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pokemon, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        PokemonModel p = dataset.get(position);
        holder.numberTxtView.setText(p.getName());

        final String exampleUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/" + p.getNumber() + ".png";

        Glide.with(context)
                .load(exampleUrl)
                .centerCrop()
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.pokeImgView);


        holder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context,StatActivity.class);
                i.putExtra("NAME", dataset.get(position));
                i.putExtra(StatActivity.ARG_EXAMPLE_IMAGE_URL, exampleUrl );
                context.startActivity(i);
            }
        });
    }


    @Override
    public int getItemCount() {

        return dataset.size();
    }

    public void addList(ArrayList<PokemonModel> listPokemonModel) {

        dataset.addAll(listPokemonModel);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private View parent;
        private ImageView pokeImgView;
        private TextView numberTxtView;


        public ViewHolder(View itemView) {
            super(itemView);

            parent = itemView;
            pokeImgView = (ImageView) itemView.findViewById(R.id.pokeImgView);
            numberTxtView = (TextView) itemView.findViewById(R.id.numberTxtView);
        }




    }
}


