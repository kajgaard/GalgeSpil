package com.example.galgespil;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;


public class VundetSpilAktivitet extends AppCompatActivity implements View.OnClickListener {

    Button tilHovedmenu, spilIgen, gemHighscore;
    TextView ordGættet, tidSlut, status, score, forkerteBog;
    EditText skrivNavn;
    ScoreList scorelist = new ScoreList();






    int point = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_vundet_spil_aktivitet);

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

        forkerteBog = findViewById(R.id.forkerteBog);
        forkerteBog.setText("Du gættede " + StartSpilAktivitet.logik.getAntalForkerteBogstaver() + " bogstaver forkert");

        tidSlut = findViewById(R.id.tidSlutTV);
        tidSlut.setText("Din tid er: "+ tidFraSpil + " sekunder");

        score = findViewById(R.id.score);
        score.setText("Din score er: " + udregnScore());

        gemHighscore = findViewById(R.id.gemHighscoreKnap);
        gemHighscore.setOnClickListener(this);

        skrivNavn = findViewById(R.id.skrivNavn);
        skrivNavn.setHint("Dit navn");


    }


    @Override
    public void onClick(View v) {
        if(v == tilHovedmenu){
            Intent i = new Intent(this,Hovedmenu.class);
            this.startActivity(i);
            StartSpilAktivitet.logik.nulstil();
        } else if (v == spilIgen){
            StartSpilAktivitet.logik.nulstil();
            Intent i = new Intent(this,StartSpilAktivitet.class);
            this.startActivity(i);


        }else if (v == gemHighscore){
            //TODO: åbn fragtment hvor man kan skrive sit navn.
            Score person = new Score(skrivNavn.getText().toString(),udregnScore());
            //person.setNavn(""+skrivNavn.getText());
            //person.setScore(udregnScore());
            SharedPreferences mPrefs = getSharedPreferences("Test", MODE_PRIVATE);
            Gson gson = new Gson();
            String json = mPrefs.getString("test", "");
            ScoreList scorelist = gson.fromJson(json, ScoreList.class);

            scorelist.highScoreList.add(person);


            SharedPreferences.Editor prefsEditor = mPrefs.edit();
            Gson gson1 = new Gson();
            String json1 = gson1.toJson(scorelist);
            prefsEditor.putString("test", json1);
            prefsEditor.apply();

        }
    }


    public int udregnScore(){
        //Brugeren har som udgangspunkt en score på 1000, hvorefter antallet af
        // sekunder bliver trukket fra, samt en penalty på 10 point pr. forkert gættet bogstav
        String tidFraSpil = getIntent().getStringExtra("tid");

        int resultTime = Integer.parseInt(tidFraSpil);
        int penalty = StartSpilAktivitet.logik.getAntalForkerteBogstaver();
        point = 1000 - resultTime - (penalty*10);
        return point;
    }
}
