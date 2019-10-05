package com.example.galgespil;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Hovedmenu extends AppCompatActivity implements View.OnClickListener {

    Button hjælpKnap, startKnap, highscoresKnap, indstillingerKnap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startKnap = findViewById(R.id.startKnap);
        startKnap.setOnClickListener(this);

        hjælpKnap = findViewById(R.id.hjælpKnap);
        hjælpKnap.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        if (v == startKnap) {
            Intent i = new Intent(this,StartSpilAktivitet.class);
            this.startActivity(i);
        } else if (v == hjælpKnap) {
            Intent i = new Intent(this, HjælpAktivitet.class);
            this.startActivity(i);

            //i.putExtra("navn","Maria");
            //For at modtage string skriv: String navn = i.getStringExtra("navn");
        }
    }
}
