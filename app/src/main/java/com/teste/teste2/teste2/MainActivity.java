package com.teste.teste2.teste2;

import android.annotation.TargetApi;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AlertDialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.teste.teste2.teste2.adapter.Adapter;
import com.teste.teste2.teste2.adapter.AdapterFavorites;
import com.teste.teste2.teste2.adapter.AdapterSearch;
import com.teste.teste2.teste2.model.BaseTeste;
import com.teste.teste2.teste2.model.MovieInformations;
import com.teste.teste2.teste2.model.MovieSearchInfomations;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

import cz.msebera.android.httpclient.Header;

import static com.teste.teste2.teste2.R.id.MovieShortStatus;
import static com.teste.teste2.teste2.R.id.searchField;
import static com.teste.teste2.teste2.R.id.textYear;
import static com.teste.teste2.teste2.R.id.texttype;


public class MainActivity extends FragmentActivity {

    //private Button searchButton;
    private EditText searchText;
    private String returnedText;
    BaseTeste baseTeste;
    int quantity;

    private ListView notesList;
    private Adapter adapter;

    private int returnedResults;

    private Boolean firstime;

    private String omdbURLQuickSearch;

    private ListView moviesListView;
    private MovieSearchInfomations movieSearchInfomations;
    AdapterSearch adapterSearch;
    String url = "http://www.omdbapi.com/?s=ice&y=&plot=short&r=json";
    Gson gson;
    AsyncHttpClient client2;


    private ListView favoritesList;
    private AdapterFavorites adapterFavorites;

    private List<MovieInformations> listAnotacao;

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @TargetApi(Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // Apontar para o XML da Acivity


        // Parei aquiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiii //
        favoritesList = (ListView) findViewById(R.id.mainTable2);
        adapterFavorites = new AdapterFavorites(MainActivity.this, movieSearchInfomations.getSearch());
        // Sempre setar o adapter //
        favoritesList.setAdapter(adapterFavorites);


        moviesListView = (ListView) findViewById(R.id.mainTable);

        // Evita que o teclado abra quando tiver um ScrollView //
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        notesList = (ListView) findViewById(R.id.mainTable);

        listAnotacao = new ArrayList<>();
        adapter = new Adapter(this, listAnotacao);

        //searchButton = (Button) findViewById(R.id.searchButton);
        searchText = (EditText) findViewById(searchField);

        firstime = false;

        searchText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!firstime){
                    firstime = true;
                    searchText.setText("");
                }


            }
        });

   searchText.addTextChangedListener(new TextWatcher() {
       @Override
       public void beforeTextChanged(CharSequence s, int start, int count, int after) {
           Log.i("Befora", "Before");

           if (s.length() > 2 && firstime) {
               moviesListView.setAdapter(null);
               adapterSearch = new AdapterSearch(MainActivity.this,null);
               adapterSearch.notifyDataSetChanged();

           }
       }

       @Override
       public void onTextChanged(CharSequence s, int start, int before, int count) {
           Log.i("Change", "Change");
       }

       @Override
       public void afterTextChanged(Editable s) {
           Log.i("Finish", "Finish");

           if (s.length() > 2 && firstime) {

               omdbURLQuickSearch = searchText.getText().toString();
               omdbURLQuickSearch = omdbURLQuickSearch.replaceAll(" ", "+");
               omdbURLQuickSearch = "https://www.omdbapi.com/?s=" + omdbURLQuickSearch + "&y=&plot=full&r=json";

               client2 = new AsyncHttpClient();
               client2.get(MainActivity.this, omdbURLQuickSearch, new AsyncHttpResponseHandler() {
                   @Override
                   public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                       String response = new String(responseBody);
                       gson = new Gson();
                       movieSearchInfomations = gson.fromJson(response, MovieSearchInfomations.class);
                       Log.i("Etapa", "1");
                       if (movieSearchInfomations.getSearch() != null) {
                           adapterSearch = new AdapterSearch(MainActivity.this, movieSearchInfomations.getSearch());
                           Log.i("Etapa", "2");
                           // Sempre setar o adapter //
                           moviesListView.setAdapter(adapterSearch);
                       }

                       Log.i("Etapa", "3");

                       //
                   }

                   @Override
                   public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

                   }
               });


           }
       }
   });

        // Adiciona o botao OK no teclado nativo //
        /*
        searchText.setImeOptions(EditorInfo.IME_ACTION_DONE);
        */
        ///////////////////////////////////////////

        // Detecta se o usuario clicou no botao OK do teclado que esta setado no xml do EditText
        /*searchText.setOnEditorActionListener(new EditText.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    //do here your stuff f
                    //return true;
                    finishedInputMovie();
                }
                return false;
            }

        });*/
        //////////////////////////////////////////////////////////////////////////////////////////


        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    public void finishedInputMovie(){

            String movieDigited;
            movieDigited = searchText.getText().toString();
            movieDigited = "https://www.omdbapi.com/?t=" + movieDigited.replaceAll(" ", "+") + "&y=&plot=full&r=json"; // Se nao usar o HTTPS fecha o app na hora com esse erro java.lang.ClassCastException: com.android.okhttp.internal.huc.HttpURLConnectionImpl cannot be cast to javax.net.ssl.HttpsURLConnection
            new JSONTask().execute(movieDigited);

          //searchText.setText(movieDigited);

    }

    public void URLResponse(){
       // searchText.setText(returnedText);

        overridePendingTransition(R.anim.slide_right, R.anim.slide_right);

        Intent myIntent = new Intent(this, SearchResultActivity.class);
        myIntent.putExtra("resultSend", returnedText); //Optional parameters // Passar variavel para a outra activity
       this.startActivity(myIntent);
    }

    public void resetSearchText(){
        searchText.setText("");
    }

    public void teste(){

        // Cria uma alerta para o usuario //
        new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("Alerta")
                .setMessage("Ok machao")
                .setPositiveButton("Feche saporra aqui", null)
                .show();

    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Main Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());


        // Aqui eu chamo a minha Classe customizada e digo se é para salvar algo no layout atual ou reiniciar o layoyt // O Android sempre recria a Activity após um evento
        /*

        Bundle savedInstanceState = null;
        quantity = 10;
        LayoutInflater inflater;
        inflater = LayoutInflater.from(this);
        ViewGroup parent = (ViewGroup) findViewById(R.id.mainTable);

        for (int i = 0; i < quantity; i++) {
            baseTeste = new BaseTeste();
            baseTeste.onCreateView(inflater, parent, savedInstanceState);
        }
        */

    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }

    public class JSONTask extends AsyncTask <String, String, String>{








        @Override
        protected String doInBackground(String... params) {

            HttpURLConnection connection = null;
            BufferedReader reader = null;

            try {
                URL url = new URL(params[0]);
                connection = (HttpsURLConnection) url.openConnection();
                connection.connect();


                InputStream stream = connection.getInputStream();

                reader = new BufferedReader(new InputStreamReader(stream));

                StringBuffer buffer =  new StringBuffer();

                String line = "";
                while ((line = reader.readLine()) != null){
                    buffer.append(line);
                }

                final JSONObject movieInfo = new JSONObject(buffer.toString());
                final Gson gson = new GsonBuilder().create();

                //String testString = (String) movieInfo.get("Title"); // Buscar uma Key de um JSON

                //String CurrentString = "Fruit: they taste good";
                //String[] separated = CurrentString.split(":");


                returnedResults = 3;

                    for (int i = 0; i < returnedResults; i++) {

                        // Chamando uma class
                        final MovieInformations movie = gson.fromJson(movieInfo.toString(), MovieInformations.class);

                        // A atualizado o UI após a busca do filme não pode ser chamada no BackTask se não o aplicativo trava por isso foi adicionado esse metodo //
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {

                                listAnotacao.add(movie); // Adiciona os textos digitados na array

                                // não esquecer de setar o adapter na lista (SOMENTE SETAR DEPOIS DE UMA ALTERACAO NA LISTA)//
                                notesList.setAdapter(adapter);

                                adapter.notifyDataSetChanged();

                                returnedText =
                                        "Titulo: " + movie.getTitle() + "\n" +
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

                            }
                        });
                }

                //returnedText = movieInfo.getJSONObject("Title").toString() + "Filme Procurado";
                // Retrofit //
                // A unica maneira de alterar algo na UI e usando este codigo assim foi possivel altear a variavel EditText do MainActivity e claro chamar uma funcao na classe principal //
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        URLResponse();
                    }
                });

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            } finally {
                if (connection != null) {
                    connection.disconnect();
                }
                try {
                    if (reader != null) {
                        reader.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
            return null;
        }

        }

}

