package com.example.galgespil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


public class TabtSpilAktivitet extends AppCompatActivity implements View.OnClickListener {

    Button tilHovedmenu, spilIgen;
    TextView ordGættet, tidSlut, status, forkerteBog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabt_spil_aktivitet);

        String statusFraSpil = getIntent().getStringExtra("status");
        String tidFraSpil = getIntent().getStringExtra("tid");


        tilHovedmenu = findViewById(R.id.tilHovedmenu);
        tilHovedmenu.setOnClickListener(this);

        status = findViewById(R.id.status);
        status.setText(statusFraSpil);

        spilIgen = findViewById(R.id.spilIgenKnap);
        spilIgen.setOnClickListener(this);

        ordGættet = findViewById(R.id.ordGættet);
        ordGættet.setText("Ordet du skulle gætte var: \n"+ StartSpilAktivitet.logik.getOrdet());

        tidSlut = findViewById(R.id.tidSlutTV);
        tidSlut.setText("Din tid er: "+ tidFraSpil + " sekunder");

        forkerteBog = findViewById(R.id.forkerteBog);
        forkerteBog.setText("Du gættede desværre " + StartSpilAktivitet.logik.getAntalForkerteBogstaver()+
                " bogstaver forkert");

    }



    @Override
    public void onClick(View v) {
        if(v == tilHovedmenu){
            Intent i = new Intent(this,Hovedmenu.class);
            this.startActivity(i);
            StartSpilAktivitet.logik.nulstil();
        } else if (v == spilIgen) {
            StartSpilAktivitet.logik.nulstil();
            Intent i = new Intent(this, StartSpilAktivitet.class);
            this.startActivity(i);
            StartSpilAktivitet.logik.nulstil();
        }
    }
}
