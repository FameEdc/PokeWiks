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
import com.edccorp.pokedex.Models.ItemModel;


import java.util.ArrayList;

/**
 * Created by ribei on 25/03/2017.
 */

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder> {

    private ArrayList<ItemModel> dataset;
    private Context context;

    public ItemAdapter(Context context) {
        this.context = context;
        dataset = new ArrayList<>();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        ItemModel iM = dataset.get(position);
        holder.numberTxtView.setText(iM.getName());

        Glide.with(context)
                .load("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/items/" + iM.getNumber() + ".png")
                .centerCrop()
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.itemImgView);

    }

    @Override
    public int getItemCount() {

        return dataset.size();
    }

    public void addList(ArrayList<ItemModel> listItem) {

        dataset.addAll(listItem);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView itemImgView;
        private TextView numberTxtView;


        public ViewHolder(View itemView) {
            super(itemView);

            itemImgView = (ImageView) itemView.findViewById(R.id.pokeImgView);
            numberTxtView = (TextView) itemView.findViewById(R.id.numberTxtView);
        }
    }
}
