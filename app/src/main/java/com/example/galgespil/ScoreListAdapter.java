package com.example.galgespil;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.ArrayList;

public class ScoreListAdapter extends ArrayAdapter<Score> {

    //Denne klasse er lavet med inspiration/hjælp fra https://www.youtube.com/watch?v=E6vE8fqQPTE

    private Context mContext;
    int mResource;
    int placering = 0;

    public ScoreListAdapter(@NonNull Context context, int resource, ArrayList<Score> objects) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        String navn = getItem(position).getNavn();
        String score = ""+getItem(position).getScore();

        LayoutInflater inflater = LayoutInflater.from(mContext);

        convertView = inflater.inflate(mResource,parent,false);

        TextView tvNavn = (TextView) convertView.findViewById(R.id.tvnavn);
        TextView tvScore = (TextView) convertView.findViewById(R.id.tvScore);
        TextView tvplacering = (TextView) convertView.findViewById(R.id.tvplacering);

        tvNavn.setText(navn);
        tvScore.setText(score);

        placering++;
        tvplacering.setText("" + placering);

        return convertView;


    }
}
