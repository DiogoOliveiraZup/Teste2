package com.teste.teste2.teste2.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.squareup.picasso.Picasso;
import com.teste.teste2.teste2.MainActivity;
import com.teste.teste2.teste2.R;
import com.teste.teste2.teste2.SearchResultActivity;
import com.teste.teste2.teste2.model.MovieInformations;
import com.teste.teste2.teste2.model.MovieSearchInfomations;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.client.HttpClient;

/**
 * Created by Computador on 10/01/2017.
 */

public class AdapterSearch extends BaseAdapter {

    private List<MovieSearchInfomations.SearchEntity> movieItem;
    private Context context;

    public AdapterSearch(Context context, List<MovieSearchInfomations.SearchEntity> movieItem) {
        this.movieItem = movieItem;
        this.context = context;
    }

    @Override
    public int getCount() {
        return movieItem.size();
    }

    @Override
    public Object getItem(int position) {
        return movieItem.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        final View rowView = inflater.inflate(R.layout.basetest, parent, false);
        final MovieSearchInfomations.SearchEntity item = (MovieSearchInfomations.SearchEntity) getItem(position);


        ImageButton miniatureMovie = (ImageButton)rowView.findViewById(R.id.miniatureMovie) ;
        String imgURL;
        if (item.getPoster().contains("N/A")){
            imgURL = "http://www.uploaddeimagens.com.br/images/000/798/274/original/Failed.png?1482711423";
        }else{
            imgURL = item.getPoster();
        }
        Picasso.with(context).load(imgURL).into(miniatureMovie);

        TextView titleText = (TextView)rowView.findViewById(R.id.textTitle) ;
        titleText.setText("Titulo: " + item.getTitle());

        TextView yearText = (TextView)rowView.findViewById(R.id.textYear) ;
        yearText.setText("Ano: " + item.getYear());

        TextView typeText  = (TextView)rowView.findViewById(R.id.texttype) ;
        typeText.setText("Tipo: " + item.getType());


        // Detecta se tocou na imagem //
        miniatureMovie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.i("Teste de Touch", "Tocou na imagem");

                TextView titleText = (TextView)rowView.findViewById(R.id.textTitle) ;

                Log.i("Titulo do Texto", titleText.getText().toString());

                String title = titleText.getText().toString();
                title =  title.replaceAll(" ", "+");
                title =  title.replace("Titulo:", "");

                Log.i("Titulo do Texto", title);

                String url = "http://www.omdbapi.com/?i=" + item.getImdbID() + "&plot=short&r=json";

                sendURL(url);

                //client2.get(this, url, new AsyncHttpResponseHandler());

            }
        });



        // Eu estava retornando null nessa porra por isso as miniaturas nao carregavam //
        return rowView;
    }

    void sendURL(String url){

        Log.i("String URL: ", url);

        AsyncHttpClient client = new AsyncHttpClient();
        client.get(context, url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {

                // Byte sempre converter para String antes de receber o Json //
                String response = new String(responseBody);
                //////////////////////////////////////////////////////////////

                Log.i("Resposta: ", responseBody.toString());

                    final Gson gson = new GsonBuilder().create();

                    final MovieInformations movie = gson.fromJson(response, MovieInformations.class);

                    String returnedText =
                            "Title: " + movie.getTitle() + "\n" +
                                    "Year: " + movie.getYear() + "\n" +
                                    "Rated: " + movie.getRated() + "\n" +
                                    "Released: " + movie.getReleased() + "\n" +
                                    "Runtime: " + movie.getRuntime() + "\n" +
                                    "Genre: " + movie.getGenre() + "\n" +
                                    "Director: " + movie.getDirector() + "\n" +
                                    "Writer: " + movie.getWriter() + "\n" +
                                    "Actors: " + movie.getActors() + "\n" +
                                    "Plot: " + movie.getPlot() + "\n" +
                                    "Language: " + movie.getLanguage() + "\n" +
                                    "Country: " + movie.getCountry() + "\n" +
                                    "Awards: " + movie.getAwards() + "\n" +
                                    "Metascore: " + movie.getMetascore() + "\n" +
                                    "imdbRating: " + movie.getImdbRating() + "\n" +
                                    "imdbVote: " + movie.getImdbVotes() + "\n" +
                                    "Type: " + movie.getType();


                // Enviado uma informação para a Activity qye vai ser carregada //
                    Intent myIntent = new Intent(context, SearchResultActivity.class);
                    myIntent.putExtra("resultSend", returnedText); //Optional parameters // Passar variavel para a outra activity
                    myIntent.putExtra("posterURL", movie.getPoster());
                    myIntent.putExtra("imdbID", movie.getImdbID());
                context.startActivity(myIntent);


            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });

    }
}
