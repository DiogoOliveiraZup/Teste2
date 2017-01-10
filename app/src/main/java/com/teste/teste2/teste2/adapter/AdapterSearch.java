package com.teste.teste2.teste2.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.teste.teste2.teste2.MainActivity;
import com.teste.teste2.teste2.R;
import com.teste.teste2.teste2.model.MovieInformations;
import com.teste.teste2.teste2.model.MovieSearchInfomations;

import java.util.List;

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
        View rowView = inflater.inflate(R.layout.basetest, parent, false);
        MovieSearchInfomations.SearchEntity item = (MovieSearchInfomations.SearchEntity) getItem(position);


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

        // Eu estava retornando null nessa porra por isso as miniaturas nao carregavam //
        return rowView;
    }
}
