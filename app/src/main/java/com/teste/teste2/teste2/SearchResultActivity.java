package com.teste.teste2.teste2;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;
import com.squareup.picasso.Picasso;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.DatagramPacket;
import java.net.MalformedURLException;
import java.net.URL;

public class SearchResultActivity extends AppCompatActivity {

    String result;
    TextView resultText;
    ImageView miniatureMovie;
    ImageView backgroundMovie;
    String title;
    String id;

    boolean isFavorite;

    String allMoviesID;
    String allTitles;

    private ImageButton manageFavorite;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Roda animação
        setContentView(R.layout.activity_search_result);

        // Solicitar permissao do usuario //
        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                1);
        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                1);
        /////////////////////////////////////


        manageFavorite = (ImageButton)findViewById(R.id.favoriteButton);

        manageFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!isFavorite) {
                    try {
                        manageFavorites();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        resultText = (TextView) findViewById(R.id.resultText);

        miniatureMovie = (ImageView)findViewById(R.id.searchMiniatura);

        backgroundMovie = (ImageView)findViewById(R.id.searchBackground);

        Intent intentMinuature = getIntent();
        String miniatureURL = intentMinuature.getStringExtra("posterURL");
        Context context = null;
        Picasso.with(context).load(miniatureURL).into(miniatureMovie);
        Picasso.with(context).load(miniatureURL).into(backgroundMovie);

        getWindow().getAttributes().windowAnimations = R.style.Fade;

        // Recebe a variavel da activity anterior
        Intent intent = getIntent();
        result = intent.getStringExtra("resultSend"); //if it's a string you stored.
        title = intent.getStringExtra("title");
        id = intent.getStringExtra("imdbID");

        Log.i("Title", title);

        resultText.setText(result);

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        // Roda animação
        overridePendingTransition(R.anim.slide_right, R.anim.slide_left);
    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("SearchResult Page") // TODO: Define a title for the content shown.
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


    @Override
    protected void onResume() {
        super.onResume();

        // Variavel para receber um Shared Preferences //
        SharedPreferences pref2 = getSharedPreferences("MyPref", MODE_PRIVATE);

        // Verifico se contem a chave no meu SharedPreferences com o valor allMoviesID e se tiver o valor eu seto da chave na String //
        if (pref2.contains("allMoviesID")){
            allMoviesID = pref2.getString("allMoviesID", null);
            Log.i("MoviesID", allMoviesID);
            allTitles = pref2.getString("allTitles", null);
            Log.i("AllTitles", allTitles);

            if (allMoviesID.contains(id)){
                isFavorite = true;
                manageFavorite.setBackgroundResource(R.mipmap.isfavorite);
            }
            else{
                isFavorite = false;
            }

        }
        else{
            allMoviesID = "";
            allTitles = "";
            Log.i("MoviesID", "Nao Encontrada");
        }
        /////////////////////////////////////////////////////////////////////////////

   }

    // Eu chamo essa funcao quando o Usuario tocar no botao de Favoritos //
    Bitmap manageFavorites() throws IOException {

        Intent intentMinuature = getIntent();
        String miniatureURL = intentMinuature.getStringExtra("posterURL");

        Intent intentID = getIntent();
        String intentIMDBID = intentID.getStringExtra("imdbID");

            // Baixar Imagem de URL //
            URL m;
            InputStream i = null;
            BufferedInputStream bis = null;
            ByteArrayOutputStream out =null;
            try {
                m = new URL(miniatureURL);
                i = (InputStream) m.getContent();
                bis = new BufferedInputStream(i,1024 * 8);
                out = new ByteArrayOutputStream();
                int len=0;
                byte[] buffer = new byte[1024];
                while((len = bis.read(buffer)) != -1){
                    out.write(buffer, 0, len);
                }
                out.close();
                bis.close();
            } catch (MalformedURLException e1) {
                e1.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            byte[] data = out.toByteArray();
            Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
            //Drawable d = Drawable.createFromStream(i, "src");

        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 40, bytes);

        // Caminho onde sera salvo o arquivo //
        File f = new File(Environment.getExternalStorageDirectory()
                + File.separator + intentIMDBID + ".png");
        ///////////////////////////////////////

        // Retorna o nome do caminho e o nome do arquivo //
        Log.i("Teste", f.toString());
        ///////////////////////////////////////

        f.createNewFile();

//write the bytes in file
        FileOutputStream fo = new FileOutputStream(f);
        fo.write(bytes.toByteArray());

// remember close de FileOutput
        fo.close();

        // Salva as informações do filme //
        SharedPreferences pref = getSharedPreferences("MyPref", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();

        // Salvando valores //
        // Recebe a variavel da activity anterior
        Intent intent = getIntent();
        String resultDescription = intent.getStringExtra("resultSend"); //if it's a string you stored.
        editor.putString(intentIMDBID, resultDescription); // Salvando a descrição do Filme //

        editor.putString("allMoviesID", allMoviesID + intentIMDBID + ",");

        editor.putString("allTitles", allTitles + title + ",");

        editor.putString(intentIMDBID+"LinkImage", f.toString()); // Salvando o caminho da imagem
        editor.commit(); // Sempre usar isso para salvar após uma alteração

        Log.i("ID Salvo: ", intentIMDBID);

        //editor.putBoolean("key_name1", true);           // Saving boolean - true/false
        //editor.putInt("key_name2", "int value");        // Saving integer
        //editor.putFloat("key_name3", "float value");    // Saving float
        //editor.putLong("key_name4", "long value");      // Saving long
        //editor.putString("key_name5", "string value");  // Saving string

        //editor.commit(); // Sempre usar isso para salvar após uma alteração

        // Restaurando Valores //
        //boolean userFirstLogin= pref.getBoolean("key_name1", true);  // getting boolean
        //int pageNumber=pref.getInt("key_name2", 0);             // getting Integer
        //float amount=pref.getFloat("key_name3", null);          // getting Float
        //long distance=pref.getLong("key_name4", null);          // getting Long
        //String email=pref.getString("key_name5", null);         // getting String
        //return bitmap;

        // Deletando Valores //
       // editor.remove("key_name3"); // will delete key key_name3
       // editor.remove("key_name4"); // will delete key key_name4

       // editor.commit(); // commit changes

        // Limpar os dados do Shared Preference //
        //editor.clear();
        //editor.commit();

        return bitmap;
    }

}
