package com.teste.teste2.teste2.model;

/**
 * Created by Computador on 18/01/2017.
 */

// Cria primeiro as variaveis
    // Cria os Getters e Setters
    // Cria o Construtor

public class FavoritesInformations {

    int movieMiniature;
    String Title;

    public FavoritesInformations(int movieMiniature, String title) {
        this.movieMiniature = movieMiniature;
        Title = title;
    }

    public int getMovieMiniature() {
        return movieMiniature;
    }

    public void setMovieMiniature(int movieMiniature) {
        this.movieMiniature = movieMiniature;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }
}
