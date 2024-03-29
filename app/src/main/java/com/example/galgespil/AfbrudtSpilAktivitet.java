package com.example.galgespil;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


public class AfbrudtSpilAktivitet extends AppCompatActivity implements View.OnClickListener {

    Button tilHovedmenu, spilIgen;
    TextView ordGættet, status;
    MediaPlayer player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_afbrudt_spil_aktivitet);

        //Afspil lyd når aktiviteten startes
        player = MediaPlayer.create(this,R.raw.failsound);
        player.start();

        //Finde tekst der siger hvorvidt brugeren har tabt eller vundet (egentlig ikke vigtigt da
        // spilleren kun kan have tabt når denne aktivitet startes, men det er bare for at vise
        // jeg kan bruge getExtra :) )
        String statusFraSpil = getIntent().getStringExtra("status");


        tilHovedmenu = findViewById(R.id.tilHovedmenu);
        tilHovedmenu.setOnClickListener(this);

        status = findViewById(R.id.status);
        status.setText(statusFraSpil);

        spilIgen = findViewById(R.id.spilIgenKnap);
        spilIgen.setOnClickListener(this);

        ordGættet = findViewById(R.id.ordGættet);
        ordGættet.setText("Ordet du skulle gætte var: \n"+ StartSpilAktivitet.logik.getOrdet());

    }



    @Override
    public void onClick(View v) {
        if(v == tilHovedmenu){
            Intent i = new Intent(this,Hovedmenu.class);
            this.startActivity(i);
            StartSpilAktivitet.logik.nulstil();

            finish();

        } else if (v == spilIgen) {
            Intent i = new Intent(this, StartSpilAktivitet.class);
            this.startActivity(i);
            StartSpilAktivitet.logik.nulstil();

            finish();
        }
    }
}
