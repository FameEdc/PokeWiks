package com.edccorp.pokedex.Models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;


/**
 * Created by ribei on 25/03/2017.
 */

public class PokemonModel implements Parcelable, Item {

    private int number;
    private String url;
    private String name;
    private List<PokemonStats> stats;

    final String imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/";


    protected PokemonModel(Parcel in) {
        number = in.readInt();
        url = in.readString();
        name = in.readString();
    }

    public static final Creator<PokemonModel> CREATOR = new Creator<PokemonModel>() {
        @Override
        public PokemonModel createFromParcel(Parcel in) {
            return new PokemonModel(in);
        }

        @Override
        public PokemonModel[] newArray(int size) {
            return new PokemonModel[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getImageUrl() {
        return imageUrl + getId() + ".png";
    }

    public String getId() {
        String[] urlPart = url.split("/");
        return urlPart[urlPart.length - 1];
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(number);
        dest.writeString(url);
        dest.writeString(name);
    }

    public List<PokemonStats> getStats() {
        return stats;
    }
}
