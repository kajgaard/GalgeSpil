package com.example.galgespil;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;



public class AfsluttetSpilAktivitet extends AppCompatActivity implements View.OnClickListener {

    Button tilHovedmenu;
    TextView ordGættet, tidSlut, status, forkerteBog;
    Logik logik = new Logik();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_afsluttet_spil_aktivitet);

        String statusFraSpil = getIntent().getStringExtra("status");
        String forkerteBogFraSpil = getIntent().getStringExtra("forkerteBogstaver");
        String tidFraSpil = getIntent().getStringExtra("tid");

        tilHovedmenu = findViewById(R.id.tilHovedmenu);
        tilHovedmenu.setOnClickListener(this);

        status = findViewById(R.id.status);
        status.setText(statusFraSpil);


        ordGættet = findViewById(R.id.ordGættet);
        ordGættet.setText("Ordet du skulle gætte var: \n"+ logik.getOrdet());

        forkerteBog = findViewById(R.id.forkerteBog);
        forkerteBog.setText("Du gættede "+ forkerteBogFraSpil + " bogstaver forkert");


        tidSlut = findViewById(R.id.tid);
        //tidSlut.setText("Din tid er: "+ tidFraSpil + " sekunder");


    }

    @Override
    public void onClick(View v) {
        if(v == tilHovedmenu){
            Intent i = new Intent(this,Hovedmenu.class);
            this.startActivity(i);
        }
    }
}
