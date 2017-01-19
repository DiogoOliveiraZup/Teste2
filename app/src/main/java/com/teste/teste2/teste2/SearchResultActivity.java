package com.teste.teste2.teste2;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.net.DatagramPacket;

public class SearchResultActivity extends AppCompatActivity {

    String result;
    TextView resultText;
    ImageView miniatureMovie;
    ImageView backgroundMovie;

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


        manageFavorite = (ImageButton)findViewById(R.id.favoriteButton);

        manageFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                manageFavorites();
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

    void manageFavorites(){

        Intent data = null;
        manageFavorite.setImageURI(data.getData());
        Bitmap bm=((BitmapDrawable)manageFavorite.getDrawable()).getBitmap();
        saveImageFile(bm);

    }

    public String saveImageFile(Bitmap bitmap) {
        FileOutputStream out = null;
        String filename = getFilename();
        try {
            out = new FileOutputStream(filename);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, out);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return filename;
    }

    public String getFilename() {

        Intent idMovie = getIntent();
        String miniatureURL = idMovie.getStringExtra("imdbID");

        File file = new File(Environment.getExternalStorageDirectory()
                .getPath(), "TestFolder");
        if (!file.exists()) {
            file.mkdirs();
        }
        String uriSting = (file.getAbsolutePath() + "/"
                + miniatureURL + ".jpg");

        Log.i("MovieLocation: ", "uriSting");

        return uriSting;
    }
}
