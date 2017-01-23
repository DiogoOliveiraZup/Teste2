package com.teste.teste2.teste2;

import android.annotation.TargetApi;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;

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
import com.teste.teste2.teste2.adapter.CoverFlowAdapter;
import com.teste.teste2.teste2.model.FavoritesInformations;
import com.teste.teste2.teste2.model.Game;
import com.teste.teste2.teste2.model.MovieInformations;
import com.teste.teste2.teste2.model.MovieSearchInfomations;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
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
import it.moondroid.coverflow.components.ui.containers.FeatureCoverFlow;

import static com.teste.teste2.teste2.R.id.searchField;


public class MainActivity extends FragmentActivity {

    //private Button searchButton;
    private EditText searchText;
    private String returnedText;

    private ListView notesList;
    private Adapter adapter;

    private int returnedResults;

    private Boolean firstime;

    private String omdbURLQuickSearch;

    private ListView moviesListView;
    private MovieSearchInfomations movieSearchInfomations;
    AdapterSearch adapterSearch;

    Gson gson;
    AsyncHttpClient client2;

    private ListView favoritesList;

    private List<MovieInformations> listAnotacao;


    // Controla a String com todos os Filmes //
    String allMoviesID;
    String[] allMoviesIDSplited;

    String allTitles;
    String[] allTitlesSplited;

    // Novos Efeitos //
    RelativeLayout searchLayout;
    LinearLayout listFavoritesMoviesLayout;
    RelativeLayout textFavoritesMoviesLayout;
    ImageButton searchButton;


    private FeatureCoverFlow coverFlow;
    private CoverFlowAdapter adapter2;
    private ArrayList<Game> games;

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

        coverFlow = (FeatureCoverFlow) findViewById(R.id.coverflow);
        coverFlow.setVisibility(View.GONE); // Esperando ter algum item para ser visivel caso seja visivel sem Item trava o app
        //settingDummyData(); apenas para teste setando itens manuais na lista

        //games = new ArrayList<>();

        // Parei aquiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiii //

       /* ArrayList<FavoritesInformations> lista = new ArrayList<FavoritesInformations>();

        FavoritesInformations filme1 = new FavoritesInformations(R.mipmap.failed, "A Era do Gelo");
        FavoritesInformations filme2 = new FavoritesInformations(R.mipmap.failed, "A Era do Gelo 2");
        FavoritesInformations filme3 = new FavoritesInformations(R.mipmap.failed, "A Era do Gelo3");
        FavoritesInformations filme4 = new FavoritesInformations(R.mipmap.failed, "A Era do Gelo 4");

        lista.add(filme1);
        lista.add(filme2);
        lista.add(filme3);
        lista.add(filme4);

        AdapterFavorites adapterFavorites = new AdapterFavorites(this, lista);
        favoritesList = (ListView) findViewById(R.id.mainTable2);

        favoritesList.setAdapter(adapterFavorites);
        */

        //adapterFavorites = new AdapterFavorites(MainActivity.this, movieSearchInfomations.getSearch());
        // Sempre setar o adapter //
        //favoritesList.setAdapter(adaptador);

        searchLayout = (RelativeLayout)findViewById(R.id.searchLayout);
        listFavoritesMoviesLayout = (LinearLayout)findViewById(R.id.listFavoritesMoviesLayout);
        textFavoritesMoviesLayout = (RelativeLayout)findViewById(R.id.textFavoritesMoviesLayout);
        searchButton = (ImageButton)findViewById(R.id.searchButton);

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listFavoritesMoviesLayout.setVisibility(View.GONE);
                searchLayout.setVisibility(View.VISIBLE);
                textFavoritesMoviesLayout.setVisibility(View.GONE);
            }
        });

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

    private FeatureCoverFlow.OnScrollPositionListener onScrollListener() {
        return new FeatureCoverFlow.OnScrollPositionListener() {
            @Override
            public void onScrolledToPosition(int position) {
                Log.v("MainActiivty", "position: " + position);
            }

            @Override
            public void onScrolling() {
                Log.i("MainActivity", "scrolling");
            }
        };
    }

    private void settingDummyData() {
        //games.add(new Game(R.mipmap.failed, "Assassin Creed 3"));
        //games.add(new Game(R.mipmap.failed, "Avatar 3D"));
        //games.add(new Game(R.mipmap.failed, "Call Of Duty Black Ops 3"));
        //games.add(new Game(R.mipmap.failed, "DotA 2"));
        //games.add(new Game(R.mipmap.failed, "Halo 5"));
        //games.add(new Game(R.mipmap.failed, "Left 4 Dead 2"));
       // games.add(new Game(R.mipmap.failed, "StarCraft"));
       // games.add(new Game(R.mipmap.failed, "The Witcher 3"));
       // games.add(new Game(R.mipmap.failed, "Tom raider 3"));
       // games.add(new Game(R.mipmap.failed, "Need for Speed Most Wanted"));

        adapter2 = new CoverFlowAdapter(this, games);
        coverFlow.setAdapter(adapter2);
        coverFlow.setOnScrollPositionListener(onScrollListener());
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
    protected void onResume() {
        super.onResume();

        // Desativo a secao de pesquisa //
        searchLayout.setVisibility(View.GONE);
        listFavoritesMoviesLayout.setVisibility(View.VISIBLE);
        textFavoritesMoviesLayout.setVisibility(View.VISIBLE);

        // Variavel para receber um Shared Preferences //
        SharedPreferences pref2 = getSharedPreferences("MyPref", MODE_PRIVATE);

        boolean allMoviesIDHaveValue;

        // Verifico se contem a chave no meu SharedPreferences com o valor allMoviesID e se tiver o valor eu seto da chave na String //
        if (pref2.contains("allMoviesID")){
            allMoviesID = pref2.getString("allMoviesID", "");
            Log.i("MoviesID", allMoviesID);
            allMoviesIDSplited = allMoviesID.split(",");

            allTitles = pref2.getString("allTitles", "");
            allTitlesSplited = allTitles.split(",");

            allMoviesIDHaveValue = true;
        }
        else{
            allMoviesIDHaveValue = false;
            allMoviesID = "";
            allTitles = "";
            Log.i("MoviesID", "Nao Encontrada");
        }
        /////////////////////////////////////////////////////////////////////////////

        // Criando uma variável da minha lista //
        ArrayList<FavoritesInformations> lista = new ArrayList<>();
        games = new ArrayList<>();

        if (allMoviesIDHaveValue) {

            for (int i = 0; i < allMoviesIDSplited.length; i++) {



                // Verifico se tem a Key dentro do Shared Preferences //
                //if (pref2.contains("tt0268380")){
                String folderTest = pref2.getString(allMoviesIDSplited[i] + "LinkImage", null);

                String movieID = allMoviesIDSplited[i]; // o id do atual filme na Array
                String description = pref2.getString(allMoviesIDSplited[i], null); // O nome do preference da descricao tem o nome do id
                //Log.i("Descricao Salva: ", resultTest);
                // Log.i("Descricao Salva: ", folderTest);

                // Pegando o Arquivo de imagem do SD Card //
                File f = new File(folderTest);
                Bitmap bmp = BitmapFactory.decodeFile(f.getAbsolutePath());
                //////////////////////////////////

                // Criando e adicionando um item na lista somente para Teste //
                FavoritesInformations filme = new FavoritesInformations(bmp, allTitlesSplited[i], movieID, description); // Preciso fazer a array de Titulos
                lista.add(filme);
                ///////////////////////////////////////////////////////////////

                // Criando e adicionando um item na lista somente para Teste //
                Game filme2 = new Game(bmp, allTitlesSplited[i]); // Preciso fazer a array de Titulos
                games.add(filme2);
                coverFlow.setVisibility(View.VISIBLE);
                ///////////////////////////////////////////////////////////////

                adapter2 = new CoverFlowAdapter(this, games);
                coverFlow.setAdapter(adapter2);
                coverFlow.setOnScrollPositionListener(onScrollListener());

                // Criando uma variável do meu adapter e setando o contexto e a lista completa no meu ListView //
                AdapterFavorites adapterFavorites = new AdapterFavorites(this, lista);
                favoritesList = (ListView) findViewById(R.id.mainTable2);
                favoritesList.setAdapter(adapterFavorites);
                adapterFavorites.notifyDataSetChanged();
                /////////////////////////////////////////////////////////////////////////////////////////////////
                //}
                // else{
                // Log.i("Descricao Salva: ", "Nao Encontrado");
                //}


            }

        }
        else{

                // Setando a lista vazia mesmo procedimento porem a lista foi limpa porque o usuario deletou todos os filmes da coleção //
                AdapterFavorites adapterFavorites = new AdapterFavorites(this, lista);
                favoritesList = (ListView) findViewById(R.id.mainTable2);
                lista.clear();
                favoritesList.setAdapter(adapterFavorites);
                adapterFavorites.notifyDataSetChanged();
        }



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

