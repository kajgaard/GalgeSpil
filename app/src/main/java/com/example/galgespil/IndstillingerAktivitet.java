package com.example.galgespil;

import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import static com.example.galgespil.StartSpilAktivitet.logik;

public class IndstillingerAktivitet extends AppCompatActivity implements View.OnClickListener {
Button hovedmenu, rydData, ordFraDR;
    private Context context;
    CoordinatorLayout coordinatorLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_indstillinger_aktivitet);

        hovedmenu = findViewById(R.id.tilHovedfraInd);
        hovedmenu.setOnClickListener(this);

        rydData = findViewById(R.id.sletDataknap);
        rydData.setOnClickListener(this);

        ordFraDR = findViewById(R.id.dummyKnap1);
        ordFraDR.setText("Ord fra DR = NEJ");
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

            } else {
                Logik.skalOrdHentes = true;
                ordFraDR.setText("Ord fra DR = JA");
            }


        }
    }
}
