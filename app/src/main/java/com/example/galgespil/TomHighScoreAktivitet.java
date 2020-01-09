package com.example.galgespil;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class TomHighScoreAktivitet extends AppCompatActivity implements View.OnClickListener {

    Button lavHighScoreKnap;

    //Jeg har lavet denne her klasse for at lave spillet mere indbydende hvis der spilles første gang

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tom_high_score_aktivitet);

        lavHighScoreKnap = findViewById(R.id.thStartSpilKnap);
        lavHighScoreKnap.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        if(v==lavHighScoreKnap){
            Intent i = new Intent(this,StartSpilAktivitet.class);
            startActivity(i);
            finish();
        }
    }
}
