package com.example.galgespil;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class StartSpilAktivitet extends AppCompatActivity implements View.OnClickListener {
    Logik logik = new Logik();
    Button a, b, c, d, afslutKnap;
    TextView ukendtOrd, liv, tid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_spil_aktivitet);

        a = findViewById(R.id.buttonA);
        a.setOnClickListener(this);

        b = findViewById(R.id.buttonD);
        b.setOnClickListener(this);

        c = findViewById(R.id.buttonC);
        c.setOnClickListener(this);

        d = findViewById(R.id.buttonD);
        d.setOnClickListener(this);

        afslutKnap = findViewById(R.id.afslutKnap);
        afslutKnap.setOnClickListener(this);

        liv = findViewById(R.id.antalLiv);
        tid = findViewById(R.id.tid);

        ukendtOrd = findViewById(R.id.spgtext);
        ukendtOrd.setText(logik.getSynligtOrd());

    }

    @Override
    public void onClick(View v) {
        if(v == a){
            String bogstav = a.getText().toString();
            logik.gætBogstav(bogstav);
            a.setEnabled(false);

        }else if (v == b){
            String bogstav = b.getText().toString();
            logik.gætBogstav(bogstav);
            b.setEnabled(false);

        }else if(v == c){
            String bogstav = c.getText().toString();
            logik.gætBogstav(bogstav);
            c.setEnabled(false);

        }else if(v == d){
            String bogstav = c.getText().toString();
            logik.gætBogstav(bogstav);
            d.setEnabled(false);

        }else if(v == afslutKnap){

        }

    }
}
