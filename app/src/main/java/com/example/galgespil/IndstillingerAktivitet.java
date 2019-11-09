package com.example.galgespil;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;

import static com.example.galgespil.StartSpilAktivitet.logik;

public class IndstillingerAktivitet extends AppCompatActivity implements View.OnClickListener {
Button hovedmenu, rydData;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_indstillinger_aktivitet);

        hovedmenu = findViewById(R.id.tilHovedfraInd);
        hovedmenu.setOnClickListener(this);

        rydData = findViewById(R.id.sletDataknap);
        rydData.setOnClickListener(this);




    }

    @Override
    public void onClick(View v) {
        if(v==hovedmenu){
            Intent i = new Intent(this, Hovedmenu.class);
            this.startActivity(i);

        }else if (v == rydData){
            //TODO: Implementer

            //PreferenceManager.getDefaultSharedPreferences(context).edit().clear().apply();
            SharedPreferences settings = getApplicationContext().getSharedPreferences("shared preferences", Context.MODE_PRIVATE);
            settings.edit().remove("highscores").apply();
            logik.highScoreList.removeAll(logik.highScoreList);

            logik.erListeTom = true;



        }
    }
}
