package com.example.galgespil;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

import static com.example.galgespil.StartSpilAktivitet.logik;


public class Hovedmenu extends AppCompatActivity implements View.OnClickListener {

    Button hjælpKnap, startKnap, highscoresKnap, indstillingerKnap;
    TextView velkomst;


    Logik logik = new Logik();

    @Override
    protected void onCreate(Bundle savedInstanceState) {






        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startKnap = findViewById(R.id.startKnap);
        startKnap.setOnClickListener(this);

        hjælpKnap = findViewById(R.id.hjælpKnap);
        hjælpKnap.setOnClickListener(this);

        highscoresKnap = findViewById(R.id.highscoresKnap);
        highscoresKnap.setOnClickListener(this);

        indstillingerKnap = findViewById(R.id.indstillingerKnap);
        indstillingerKnap.setOnClickListener(this);

        velkomst = findViewById(R.id.textView);
        velkomst.setText("Velkommen til Hangman!");



    }


    @Override
    public void onClick(View v) {
        if (v == startKnap) {

            Intent i = new Intent(this,StartSpilAktivitet.class);
            this.startActivity(i);


        } else if (v == hjælpKnap) {

            Intent i = new Intent(this, HjælpAktivitet.class);
            this.startActivity(i);
        } else if (v == indstillingerKnap){

            Intent i = new Intent(this,IndstillingerAktivitet.class);
            this.startActivity(i);
        } else if (v == highscoresKnap){

            if(!logik.highScoreList.isEmpty()) {
                Intent i = new Intent(this, HighscoresAktivitet.class);
                this.startActivity(i);
            }else{
                Intent i = new Intent(this, TomHighScoreAktivitet.class);
                this.startActivity(i);
            }



        }

    }
}
