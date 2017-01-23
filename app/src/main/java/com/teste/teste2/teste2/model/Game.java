package com.teste.teste2.teste2.model;

import android.graphics.Bitmap;

/**
 * Created by DiogoZup on 23/01/2017.
 */

public class Game {
    private String name;
    private Bitmap imageSource;

    public Game (Bitmap imageSource, String name) {
        this.name = name;
        this.imageSource = imageSource;
    }

    public String getName() {
        return name;
    }

    public Bitmap getImageSource() {
        return imageSource;
    }
}
