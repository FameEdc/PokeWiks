package com.edccorp.pokedex.Models;

/**
 * Created by ribei on 25/03/2017.
 */

public class ItemModel implements Item {
    private String name;

    private static final String imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/items/";

    public String getName() {
        return name;
    }

    @Override
    public String getImageUrl() {
        return imageUrl + name + ".png";
    }
}
