package com.edccorp.pokedex.Adapters;

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
import com.edccorp.pokedex.R;

import java.util.ArrayList;

/**
 * Created by ribei on 25/03/2017.
 */

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder> {

    private ArrayList<ItemModel> datasetItem;
    private Context context;


    public ItemAdapter(Context context) {
        this.context = context;
        datasetItem = new ArrayList<>();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        ItemModel item = datasetItem.get(position);
        holder.numberTxtView.setText(item.getName());

        Glide.with(context)
                .load("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/items/" + item.getName() + ".png")
                .centerCrop()
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.itemImgView);


    }

    @Override
    public int getItemCount() {

        return datasetItem.size();
    }

    public void addList(ArrayList<ItemModel> listItem) {

        datasetItem.addAll(listItem);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView itemImgView;
        private TextView numberTxtView;


        public ViewHolder(View itemView) {
            super(itemView);

            itemImgView = (ImageView) itemView.findViewById(R.id.itemImgView);
            numberTxtView = (TextView) itemView.findViewById(R.id.numberTxtView);
        }
    }
}
