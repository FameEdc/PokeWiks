package com.edccorp.pokedex.Models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;


/**
 * Created by ribei on 25/03/2017.
 */

public class PokemonModel implements Parcelable {

    private int number;
    private String url;
    private String name;


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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getNumber() {
        String[] urlPart = url.split("/");
        return Integer.parseInt(urlPart[urlPart.length - 1]);
    }

    public void setNumber(int number) {
        this.number = number;
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
}
