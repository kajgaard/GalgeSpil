package com.example.galgespil;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


import static com.example.galgespil.StartSpilAktivitet.logik;

public class IndstillingerAktivitet extends AppCompatActivity implements View.OnClickListener {
Button hovedmenu, rydData, ordFraDR;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_indstillinger_aktivitet);

        hovedmenu = findViewById(R.id.tilHovedfraInd);
        hovedmenu.setOnClickListener(this);

        rydData = findViewById(R.id.sletDataknap);
        rydData.setOnClickListener(this);

        ordFraDR = findViewById(R.id.dummyKnap1);

        if(Logik.skalOrdHentes){
            ordFraDR.setText("Ord fra DR = JA");

        }else{
            ordFraDR.setText("Ord fra DR = NEJ");

        }

        ordFraDR.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        if(v==hovedmenu){

            Intent i = new Intent(this, Hovedmenu.class);
            this.startActivity(i);

        }else if (v == rydData){

            SharedPreferences settings = getApplicationContext().getSharedPreferences("shared preferences", Context.MODE_PRIVATE);
            settings.edit().remove("highscores").apply();

            //må ikke slettes ellers kaos
            logik.highScoreList.removeAll(logik.highScoreList);

            Toast.makeText(this, "Data slettet!", Toast.LENGTH_SHORT).show();

        }else if(v == ordFraDR){

            if(Logik.skalOrdHentes == true){
                ordFraDR.setText("Ord fra DR = NEJ");
                Logik.skalOrdHentes = false;
                logik.muligeOrd.clear();

                //Ikke smart løsning - men det virker
                logik.muligeOrd.add("bil");
                logik.muligeOrd.add("computer");
                logik.muligeOrd.add("programmering");
                logik.muligeOrd.add("motorvej");
                logik.muligeOrd.add("busrute");
                logik.muligeOrd.add("gangsti");
                logik.muligeOrd.add("skovsnegl");
                logik.muligeOrd.add("solsort");
                logik.muligeOrd.add("nitten");

                System.out.println("muligeOrd = " + logik.muligeOrd);

                logik.nulstil();

            } else {

                Logik.skalOrdHentes = true;
                ordFraDR.setText("Ord fra DR = JA");

            }

        }
    }
}
