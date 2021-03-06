package com.teste.teste2.teste2.adapter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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

import static android.content.Context.MODE_PRIVATE;

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
        miniatureMovie.setImageBitmap(itemPosicao.getMovieMiniature());

        TextView textTitle = (TextView)convertView.findViewById(R.id.textTitle);
        textTitle.setText(itemPosicao.getTitle());

        final String movieID = itemPosicao.getMovieID();
        final String description = itemPosicao.getDescription();


        miniatureMovie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Enviado uma informação para a Activity qye vai ser carregada //

                boolean openByFavorites = true;
                Intent myIntent = new Intent(context, SearchResultActivity.class);
                myIntent.putExtra("description", description); //Optional parameters // Passar variavel para a outra activity
                myIntent.putExtra("posterLocalURL", movieID);
                myIntent.putExtra("openByFavorites", openByFavorites);
                context.startActivity(myIntent);

            }
        });

        return convertView;
    }


}
