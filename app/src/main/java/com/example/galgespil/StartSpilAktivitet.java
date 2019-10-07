package com.example.galgespil;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.DecimalFormat;


public class StartSpilAktivitet extends AppCompatActivity implements View.OnClickListener {
    static Logik logik = new Logik();
    StopUr stopUr = new StopUr();
    Button a, b, c, d, afslutKnap;
    TextView ukendtOrd, liv, tid;
    ImageView hangman, hjerte;
    Runnable opdaterTid;
    Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_spil_aktivitet);

        a = findViewById(R.id.buttonA);
        a.setOnClickListener(this);

        b = findViewById(R.id.buttonB);
        b.setOnClickListener(this);

        c = findViewById(R.id.buttonC);
        c.setOnClickListener(this);

        d = findViewById(R.id.buttonD);
        d.setOnClickListener(this);

        afslutKnap = findViewById(R.id.afslutKnap);
        afslutKnap.setOnClickListener(this);

        liv = findViewById(R.id.antalLiv);
        liv.setText("6");
        tid = findViewById(R.id.tid);

        ukendtOrd = findViewById(R.id.spgtext);
        ukendtOrd.setText(logik.getSynligtOrd());

        tid = findViewById(R.id.tid);
        tid.setText("0.00");

        hangman = findViewById(R.id.hangman);
        hangman.setImageResource(R.drawable.galge);

        hjerte = findViewById(R.id.hearts);
        hjerte.setImageResource(R.drawable.heart1);


        stopUr.start();

        opdaterTidMetode();

        /*
        opdaterTid = new Runnable() {
            double antalSekunderGået = 0.0;
            DecimalFormat form = new DecimalFormat("#.#");

            public void run() {
                while (!logik.erSpilletVundet() || logik.erSpilletTabt()) {
                    antalSekunderGået = antalSekunderGået + 0.1;
                    tid.setText("" + Double.valueOf(form.format(antalSekunderGået)));
                    handler.postDelayed(this, 100); // udfør denne Runnable igen om 0,1 sekund
                }

            }

        };
        handler.postDelayed(opdaterTid, 100); // udfør om 1 sekund

         */



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
            String bogstav = d.getText().toString();
            logik.gætBogstav(bogstav);
            d.setEnabled(false);

        }else if(v == afslutKnap){
            Intent i = new Intent(this, AfsluttetSpilAktivitet.class);
            i.putExtra("status", "Øv! Du tabte!");
            i.putExtra("forkerteBogstaver", ""+ logik.getAntalForkerteBogstaver());
            i.putExtra("tid", ""+stopUr.getElapsedTimeSecs());
            this.startActivity(i);

        }
        opdaterSkærm();
        //TODO: Lav et stopur der virker. Evt. med et widget.


    }

    private void opdaterSkærm(){
        ukendtOrd.setText(logik.getSynligtOrd());
        liv.setText(""+getAntalLiv());
        tid.setText(""+stopUr.getElapsedTimeSecs());

        //Tegn manden lidt efter lidt
        if(getAntalLiv() == 5){
            hangman.setImageResource(R.drawable.forkert1);
        } else if (getAntalLiv() == 4){
            hangman.setImageResource(R.drawable.forkert2);
        } else if (getAntalLiv() == 3){
            hangman.setImageResource(R.drawable.forkert3);
        } else if(getAntalLiv() == 2){
            hangman.setImageResource(R.drawable.forkert4);
        } else if (getAntalLiv() == 1) {
            hangman.setImageResource(R.drawable.forkert5);
        } else if (getAntalLiv() == 0){
            hangman.setImageResource(R.drawable.forkert6);
        }

        if (logik.erSpilletVundet()) {
            stopUr.stop();

            Intent i = new Intent(this, AfsluttetSpilAktivitet.class);

            i.putExtra("status", "Du har vundet!");
            i.putExtra("forkerteBogstaver", ""+ logik.getAntalForkerteBogstaver());
            i.putExtra("tid",""+stopUr.getElapsedTimeSecs());
            i.putExtra("erVundet", true);
            this.startActivity(i);

        }
        if (logik.erSpilletTabt()) {
            stopUr.stop();

            Intent i = new Intent(this, AfsluttetSpilAktivitet.class);
            i.putExtra("status", "Øv! Du tabte!");
            i.putExtra("forkerteBogstaver", ""+ logik.getAntalForkerteBogstaver());
            i.putExtra("tid", ""+stopUr.getElapsedTimeSecs());
            i.putExtra("erTabt", true);

            this.startActivity(i);
        }
    }

    //OBS Har selv tilføjet
    public int getAntalLiv(){
        int antalLiv = 6 - logik.getAntalForkerteBogstaver();
        return antalLiv;}

    public void opdaterTidMetode(){

        opdaterTid = new Runnable() {
            int antalSekunderGået = 0;
            //DecimalFormat form = new DecimalFormat("#.#");

            public void run() {
                if (antalSekunderGået++<60) {
                    tid.setText(""+antalSekunderGået);
                    handler.postDelayed(this, 1000); // udfør denne Runnable igen om 0,1 sekund
                }
            }
        };
        handler.postDelayed(opdaterTid, 1000); // udfør om 1 sekund
    }
}
