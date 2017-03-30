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
import com.edccorp.pokedex.Models.Item;
import com.edccorp.pokedex.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ribei on 25/03/2017.
 */

public class Adapter<I extends Item> extends RecyclerView.Adapter<Adapter.ViewHolder> {

    protected ArrayList<I> datasetItem = new ArrayList<>();
    protected Context context;

    public Adapter(Context context) {
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        I item = datasetItem.get(position);
        holder.numberTxtView.setText(item.getName());
        Glide.with(context)
                .load(item.getImageUrl())
                .centerCrop()
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.itemImgView);
    }

    @Override
    public int getItemCount() {
        return datasetItem.size();
    }

    public void addList(List<I> listItem) {
        datasetItem.addAll(listItem);
        notifyDataSetChanged();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView itemImgView;
        private TextView numberTxtView;
        ViewHolder(View itemView) {
            super(itemView);
            itemImgView = (ImageView) itemView.findViewById(R.id.itemImgView);
            numberTxtView = (TextView) itemView.findViewById(R.id.numberTxtView);
        }
    }
}
