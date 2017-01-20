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

    String movieID;
    String description;

    public FavoritesInformations(Bitmap movieMiniature, String title, String movieID, String description) {
        this.movieMiniature = movieMiniature;
        Title = title;
        this.movieID = movieID;
        this.description = description;
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

    public String getMovieID() {
        return movieID;
    }

    public String getDescription() {
        return description;
    }

    public void setMovieID(String movieID) {
        this.movieID = movieID;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
