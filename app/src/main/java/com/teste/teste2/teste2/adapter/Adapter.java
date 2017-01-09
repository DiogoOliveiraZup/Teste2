package com.teste.teste2.teste2.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.teste.teste2.teste2.model.BaseTeste;

import com.teste.teste2.teste2.R;
import com.teste.teste2.teste2.model.MovieInformations;

import java.util.List;

/**
 * Created by DiogoZup on 05/01/2017.
 */

public class Adapter extends ArrayAdapter<MovieInformations> {

    private Context context;

    private List<MovieInformations>  anotacaoList;

    public String getTitte() {
        return titte;
    }

    public String getYear() {
        return year;
    }

    public String getType() {
        return type;
    }

    public void setTitte(String titte) {
        this.titte = titte;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public void setType(String type) {
        this.type = type;
    }

    private String titte;
    private String year;
    private String type;

    public Adapter(Context context, List<MovieInformations> anotacaoList) {

        super(context, 0, anotacaoList);
        this.anotacaoList = anotacaoList;
        this.context = context;

    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {

        MovieInformations item = anotacaoList.get(position);

       if (view == null)
        view = LayoutInflater.from(context).inflate(R.layout.basetest, null);

        ImageButton miniatureMovie = (ImageButton)view.findViewById(R.id.miniatureMovie) ;

        TextView titleText = (TextView)view.findViewById(R.id.textTitle) ;
        titleText.setText("Titulo: " + item.getTitle());

        TextView yearText = (TextView)view.findViewById(R.id.textYear) ;
        yearText.setText("Ano: " + item.getYear());

        TextView typeText  = (TextView)view.findViewById(R.id.texttype) ;
        typeText.setText("Tipo: " + item.getType());

        return view;
        }


}
