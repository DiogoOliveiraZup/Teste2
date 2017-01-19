package com.teste.teste2.teste2.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.squareup.picasso.Picasso;
import com.teste.teste2.teste2.R;
import com.teste.teste2.teste2.SearchResultActivity;
import com.teste.teste2.teste2.model.FavoritesInformations;
import com.teste.teste2.teste2.model.MovieInformations;
import com.teste.teste2.teste2.model.MovieSearchInfomations;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;

/**
 * Created by Computador on 10/01/2017.
 */

public class AdapterFavorites extends ArrayAdapter<FavoritesInformations> {

    private Context context;
    private ArrayList<FavoritesInformations> lista;

    // Construtor de Arrayadapter //
    public AdapterFavorites(Context context, ArrayList<FavoritesInformations> lista) {

        super(context, 0, lista);
        this.context = context;
        this.lista = lista;

    }

    // Agora dar um override // botao direito "Generate" Override Methodes " GetView

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        FavoritesInformations itemPosicao = this.getItem(position);

        // Padrao //
        convertView = LayoutInflater.from(this.context).inflate(R.layout.favorites, null);

        // Pegando do Script Miniature Informations//
        ImageButton miniatureMovie = (ImageButton)convertView.findViewById(R.id.miniatureMovie);
        miniatureMovie.setImageResource(itemPosicao.getMovieMiniature());

        TextView textTitle = (TextView)convertView.findViewById(R.id.textTitle);
        textTitle.setText(itemPosicao.getTitle());

        return convertView;
    }
}
