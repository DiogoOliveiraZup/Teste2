package com.teste.teste2.teste2;

import android.annotation.TargetApi;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.teste.teste2.teste2.model.BaseTeste;
import com.teste.teste2.teste2.model.MovieInformations;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import static com.teste.teste2.teste2.R.id.mainTable;
import static com.teste.teste2.teste2.R.id.searchField;


public class MainActivity extends FragmentActivity {

    //private Button searchButton;
    private EditText searchText;
    private String returnedText;
    BaseTeste baseTeste;
    int quantity;

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

        baseTeste = new BaseTeste();

        // Evita que o teclado abra quando tiver um ScrollView //
        getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);


        //super.onCreate(savedInstanceState);
        //Button myButton = new Button(this);
       // RelativeLayout myLayout = new RelativeLayout(this);
        //myLayout.addView(myButton);
        //setContentView(myLayout);

        // Contruir um XML programavel Manual //


        /*

        // Programar um Objeto com parametros XML //
        TableLayout mainTable = (TableLayout) findViewById(R.id.mainTable);

        // Table Row Principal //
        TableRow row = new TableRow(this);
        // Aplica os parametros do componente de acordo com o pai, para funcionar busque do Pai, LinearLayout ou RelativeLayout (TableRow, ScrollView nao funciona)
        TableLayout.LayoutParams tableRowParams = new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT,TableLayout.LayoutParams.WRAP_CONTENT);

        float scale = getBaseContext().getResources().getDisplayMetrics().density;

        // Convertendo os valores de px para dp(dp é o usado atualmente pelos programadores) //
        int marginBotton = (int)(5 * scale + 0.5f);
        int marginLeft = (int)(5 * scale + 0.5f);
        int marginRight = (int)(5 * scale + 0.5f);
        int marginTop = (int)(5 * scale + 0.5f);
        tableRowParams.setMargins(marginBotton,marginLeft,marginRight,marginTop);
        row.setLayoutParams(tableRowParams);

        // Adiciona a TableRow para a TableLayout //
        mainTable.addView(row);
        ////////////////////////////////////////

        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        // ImageView da miniatura do Filme ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        ImageView miniatureMovie = new ImageView(this);
        miniatureMovie.setBackgroundResource(R.drawable.common_ic_googleplayservices);

        // Aplica os parametros do componente de acordo com o pai, para funcionar busque do Pai, LinearLayout ou RelativeLayout (TableRow, ScrollView nao funciona)
        TableRow.LayoutParams miniatureMoveParams = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,TableRow.LayoutParams.WRAP_CONTENT);

        // Convertendo os valores de px para dp(dp é o usado atualmente pelos programadores) //
        int marginBottonMiniatureMovie = (int)(0 * scale + 0.5f);
        int marginLeftMiniatureMovie = (int)(5 * scale + 0.5f);
        int marginRightMiniatureMovie = (int)(5 * scale + 0.5f);
        int marginTopMiniatureMovie = (int)(5 * scale + 0.5f);
        int width = (int)(50 * scale + 0.5f);
        int height = (int)(130 * scale + 0.5f);
        miniatureMoveParams.setMargins(marginBottonMiniatureMovie,marginLeftMiniatureMovie,marginRightMiniatureMovie,marginTopMiniatureMovie);
        TableRow.LayoutParams parms = new TableRow.LayoutParams(width,height);
        miniatureMovie.setLayoutParams(parms);
        ////////////////////////////////////////

        // Adiciona a ImageView para a TableRow //
        row.addView(miniatureMovie);
        ////////////////////////////////////////

        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        RelativeLayout relativeLayout = new RelativeLayout(this);
        int relativeLayoutHeight = (int)(125 * scale + 0.5f);
        TableRow.LayoutParams relativeLayoutParams = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, relativeLayoutHeight);
        relativeLayout.setLayoutParams(relativeLayoutParams);
        row.addView(relativeLayout);

        TextView title = new TextView(this);
        TextView title2 = new TextView(this);
        RelativeLayout.LayoutParams titleParams = new RelativeLayout.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,RelativeLayout.LayoutParams.WRAP_CONTENT);

        titleParams.leftMargin = (int)(10 * scale + 0.5f);
        titleParams.topMargin = (int)(11 * scale + 0.5f);


        int marginBottontitle = (int)(0 * scale + 0.5f);
        int marginLefttitle = (int)(0 * scale + 0.5f);
        int marginRighttitle = (int)(0 * scale + 0.5f);
        int marginToptitle = (int)(11 * scale + 0.5f);
        titleParams.setMargins(marginLefttitle, marginToptitle, marginRighttitle, marginBottontitle);
        title.setText("Ace Ventura: Pet Detective dsasssaddaasddasasddasasdasddasasdadad");
        title.setMaxLines(2);
        titleParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        titleParams.addRule(View.TEXT_ALIGNMENT_CENTER);
        title.setTextColor(ContextCompat.getColor(this, R.color.colorVerdelimao));
        title.setTextSize(15f);

        title2.setText("Ace Ventura: Pet Detective dsasssaddaasddasasddasasdasddasasdadad");
        title2.setMaxLines(2);
        titleParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        titleParams.addRule(View.TEXT_ALIGNMENT_CENTER);
        title2.setTextColor(ContextCompat.getColor(this, R.color.colorVerdelimao));
        title2.setTextSize(15f);
        //title.setTextAppearance(android.R.style.TextAppearance_Medium);

        relativeLayout.addView(title, titleParams);
        //relativeLayout.addView(title2, titleParams);
        //titleParams.addRule(relativeLayout.ALIGN_PARENT_LEFT);
        // Convertendo os valores de px para dp(dp é o usado atualmente pelos programadores) //

        Context context = this;

        */

        // Adicionar um custom XML dentro de um Bloco desejado ////////////////////////////////////////////////////////////////////////////////////////////////////
        // Neste caso ele vai pegar os filmes retornados //

        //for (int i = 0; i < 8; i++) {

            /*
            LayoutInflater inflater;
            inflater = LayoutInflater.from(this);
            LinearLayout linear = (LinearLayout) findViewById(R.id.mainTable);
            final View layout = inflater.inflate(R.layout.basetest, null);

            linear.addView(layout);

            */


             //Log.i("Id do botao 2", name);
        //}


       // while (i < 8){
           // return;
        //}


        //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


        //searchButton = (Button) findViewById(R.id.searchButton);
        searchText = (EditText) findViewById(searchField);

        searchText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetSearchText();
            }
        });

        searchText.setImeOptions(EditorInfo.IME_ACTION_DONE);

        // Detecta se o usuario clicou no botao OK do teclado que esta setado no xml do EditText
        searchText.setOnEditorActionListener(new EditText.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    //do here your stuff f
                    //return true;
                    finishedInputMovie();
                }
                return false;
            }

        });

             /* searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               teste();

            }
        });
        */

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }


    public void finishedInputMovie(){

        //searchText.setText("Usuario terminou");

            String movieDigited;
            movieDigited = searchText.getText().toString();
            movieDigited = "https://www.omdbapi.com/?t=" + movieDigited.replaceAll(" ", "+") + "&y=&plot=full&r=json"; // Se nao usar o HTTPS fecha o app na hora com esse erro java.lang.ClassCastException: com.android.okhttp.internal.huc.HttpURLConnectionImpl cannot be cast to javax.net.ssl.HttpsURLConnection
            new JSONTask().execute(movieDigited);

        /*

        HttpURLConnection connection = null;
        BufferedReader reader = null;

        try {
            URL url = new URL(movieDigited);
            connection = (HttpsURLConnection) url.openConnection();
            connection.connect();


            InputStream stream = connection.getInputStream();

            reader = new BufferedReader(new InputStreamReader(stream));

            StringBuffer buffer =  new StringBuffer();

            String line = "";
            while ((line = reader.readLine()) != null){
                buffer.append(line);
            }

            searchText.setText(buffer.toString());

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
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

        */

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
        Bundle savedInstanceState = null;
        quantity = 2;
        LayoutInflater inflater;
        inflater = LayoutInflater.from(this);
        ViewGroup parent = (ViewGroup) findViewById(R.id.mainTable);

        baseTeste.onCreateView(inflater, parent, savedInstanceState, quantity);


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

                //return buffer.toString();

               //searchText.setText(buffer.toString());



                /*
                Iterator<String> iter = movieInfo.keys();
                while (iter.hasNext()) {
                    String key = iter.next();
                    Log.i("JSON Quantidade", key);
                    try {
                        Object value = movieInfo.get(key);
                    } catch (JSONException e) {
                        // Something went wrong!
                    }
                }
                */


                JSONObject movieInfo = new JSONObject(buffer.toString());
                 Gson gson = new GsonBuilder().create();

                // Chamando uma class
                MovieInformations movie = gson.fromJson(movieInfo.toString(),MovieInformations.class);

                returnedText =
                "Titulo: " + movie.getTitle() + "\n" +
                "Year: " + movie.getYear() + "\n" +
                "Rated: " + movie.getRated() + "\n" +
                "Released: " + movie.getReleased()+ "\n" +
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

