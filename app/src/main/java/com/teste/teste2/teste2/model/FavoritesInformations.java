package com.teste.teste2.teste2.model;

import android.graphics.Bitmap;
import android.widget.ImageView;

import java.io.File;

/**
 * Created by Computador on 18/01/2017.
 */

// Cria primeiro as variaveis
    // Cria os Getters e Setters
    // Cria o Construtor

public class FavoritesInformations {

    Bitmap movieMiniature;
    String Title;

    public FavoritesInformations(Bitmap movieMiniature, String title) {
        this.movieMiniature = movieMiniature;
        Title = title;
    }

    public Bitmap getMovieMiniature() {
        return movieMiniature;
    }

    public void setMovieMiniature(Bitmap movieMiniature) {
        this.movieMiniature = movieMiniature;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }
}
