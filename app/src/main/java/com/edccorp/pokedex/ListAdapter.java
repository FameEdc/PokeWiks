package com.edccorp.pokedex;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.edccorp.pokedex.Models.Pokemon;

import java.util.ArrayList;

/**
 * Created by ribei on 25/03/2017.
 */

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder>{

    private ArrayList<Pokemon> dataset;
    private Context context;



    public ListAdapter(Context context) {
        this.context = context;
        dataset = new ArrayList<>();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pokemon, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Pokemon p = dataset.get(position);
        holder.numberTxtView.setText(p.getName());

        Glide.with(context)
                .load("http://pokeapi.co/media/sprites/pokemon/" + p.getNumber() + ".png")
                .centerCrop()
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.pokeImgView);

    }

    @Override
    public int getItemCount() {

        return dataset.size();
    }

    public void addList(ArrayList<Pokemon> listPokemon) {

        dataset.addAll(listPokemon);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView pokeImgView;
        private TextView numberTxtView;


        public ViewHolder(View itemView) {
            super(itemView);

            pokeImgView = (ImageView) itemView.findViewById(R.id.pokeImgView);
            numberTxtView = (TextView) itemView.findViewById(R.id.numberTxtView);
        }
    }
}
