package com.edccorp.pokedex;

import com.nightonke.boommenu.BoomButtons.SimpleCircleButton;

/**
 * Created by ribei on 25/03/2017.
 */

public class BuilderManager {

    private static int[] imageResources = new int[]{

            R.drawable.ic_pokedex,
            R.drawable.ic_pokeball,
            R.drawable.ic_region,
            R.drawable.ic_egg,


    };

    private static int imageResourceIndex = 0;

    static int getImageResource() {
        if (imageResourceIndex >= imageResources.length) imageResourceIndex = 0;
        return imageResources[imageResourceIndex++];
    }

    public static SimpleCircleButton.Builder getSimpleCircleButtonBuilder() {
        return new SimpleCircleButton.Builder()
                .normalImageRes(getImageResource());
    }

    private static BuilderManager ourInstance = new BuilderManager();

    public static BuilderManager getInstance() {
        return ourInstance;
    }

    private BuilderManager() {

    }
}

