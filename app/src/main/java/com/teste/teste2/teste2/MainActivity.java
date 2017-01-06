package com.teste.teste2.teste2;

import android.app.Application;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v4.os.AsyncTaskCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

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

import static com.teste.teste2.teste2.R.id.searchField;


public class MainActivity extends AppCompatActivity {

    //private Button searchButton;
    private EditText searchText;
    private String returnedText;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // Apontar para o XML da Acivity

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

        searchText.setText(returnedText);
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


                //returnedText = buffer.toString();

                JSONObject movieInfo = new JSONObject(buffer.toString());
                //String title = movieInfo.getString("Title");
                String finalString = null;

                for (int i = 0; i < movieInfo.length(); i++){

                    //finalString = movieInfo.getJSONArray());

                }



                Log.i("JSON Response", finalString);

                //returnedText = movieInfo.getJSONObject("Title").toString() + "Filme Procurado";

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

