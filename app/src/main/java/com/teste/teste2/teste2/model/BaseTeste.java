package com.teste.teste2.teste2.model;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TableLayout;

import com.teste.teste2.teste2.R;

public class BaseTeste extends Fragment {

    ImageButton buttonClick;
    View rootView;

        @Override
    public void onCreate(Bundle savedInstanceState) {


        /*
        rootView = inflater.inflate(R.layout.basetest, this.container, false);

        inflater = LayoutInflater.from(getActivity());
        linear = (LinearLayout) rootView.findViewById(R.id.mainTable);
        final View layout = inflater.inflate(R.layout.basetest, null);

        linear.addView(layout);
        */

        Log.i("Left", "onCreate()");

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {

        //


        TableLayout linear = (TableLayout) parent; // Meu TableLayout recebido do MainActivity(Onde o Layout vai ser filho)
        final View layout = inflater.inflate(R.layout.basetest, null); // Meu custom Layout XML

        layout.getRootView();

            Log.i("Pegando o RootView", layout.getRootView().toString());

            linear.addView(layout); // Adiciona meu Layout ao TableLayout

            rootView = layout.getRootView();

            buttonClick = (ImageButton) rootView.findViewById(R.id.miniatureMovie);



        buttonClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showMovie();
            }
        });



        // Inflate the layout for this fragment
        Log.i("Fragmento criado", "True");
        return rootView;
    }

    public void showMovie(){

        Log.i("Miniatura Clicada", "RespondeuToque");

        //int id = v.getId();
        //String name = getResources().getResourceEntryName(id);
        //Log.i("Id do botao:", name);


    }
}