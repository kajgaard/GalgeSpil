package com.example.galgespil;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;



public class AfsluttetSpilAktivitet extends AppCompatActivity implements View.OnClickListener {

    Button tilHovedmenu, spilIgen, gemHighscore;
    TextView ordGættet, tidSlut, status, forkerteBog, score;


    Logik logik = new Logik();
    int point = 0;



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

        spilIgen = findViewById(R.id.spilIgenKnap);
        spilIgen.setOnClickListener(this);

        ordGættet = findViewById(R.id.ordGættet);
        ordGættet.setText("Ordet du skulle gætte var: \n"+ logik.getOrdet()); //OBS: Muligvis
        // problem da logik ikke er en singleton. Henter den så samme ord fra StartSpil?


        tidSlut = findViewById(R.id.tidSlutTV);
        tidSlut.setText("Din tid er: "+ tidFraSpil + " sekunder");

        opdaterSkærm();

    }

    private void opdaterSkærm(){
        String forkerteBogFraSpil = getIntent().getStringExtra("forkerteBogstaver");
        Boolean erVundet = getIntent().getExtras().getBoolean("erVundet");
        Boolean erTabt = getIntent().getExtras().getBoolean("erTabt");

        //Debugging
        System.out.println("opdaterskærm metode kører");
        if (StartSpilAktivitet.logik.erSpilletVundet()) {
            forkerteBog = findViewById(R.id.forkerteBog);
            forkerteBog.setText("Du gættede " + forkerteBogFraSpil + " bogstaver forkert");

            System.out.println("Spillet er vundet i opdaterskærm");

            //TODO: Ændre posiion på knapperne, da der nu er kæmpe hul. Evt. skal der ny aktivitet til ved gemt/vundet spil.
            score = findViewById(R.id.score);
            score.setText("Din score er: " + getScore());

            gemHighscore = findViewById(R.id.gemHighscoreKnap);
            gemHighscore.setOnClickListener(this);


        } else if (erTabt) {
            forkerteBog = findViewById(R.id.forkerteBog);

            System.out.println("Spillet er vundet i opdaterskærm");

            forkerteBog.setText("Du gættede desværre " + forkerteBogFraSpil + " bogstaver forkert");

            score = findViewById(R.id.score);
            score.setVisibility(View.INVISIBLE); //Det ønskes ikke at scoren vises når spillet er tabt

            gemHighscore = findViewById(R.id.gemHighscoreKnap);
            gemHighscore.setVisibility(View.INVISIBLE);



        }
    }


    @Override
    public void onClick(View v) {
        if(v == tilHovedmenu){
            Intent i = new Intent(this,Hovedmenu.class);
            this.startActivity(i);
            StartSpilAktivitet.logik.nulstil();
        } else if (v == spilIgen){
            logik.nulstil();
            Intent i = new Intent(this,StartSpilAktivitet.class);
            this.startActivity(i);
            StartSpilAktivitet.logik.nulstil();

        }else if (v == gemHighscore){
            //TODO: åbn fragtment hvor man kan skrive sit navn.
        }
    }

    public int getScore(){
        //Brugeren har som udgangspunkt en score på 1000, hvorefter antallet af
        // sekunder bliver trukket fra, samt en penalty på 10 point pr. forkert gættet bogstav
        String tidFraSpil = getIntent().getStringExtra("tid");
        String forkerteBogFraSpil = getIntent().getStringExtra("forkerteBogstaver");
        int resultTime = Integer.parseInt(tidFraSpil);
        int penalty = Integer.parseInt(forkerteBogFraSpil);
        point = 1000 - resultTime - (penalty*10);
        return point;
    }
    /*
    public int getTime(){
        String tidFraSpil = getIntent().getStringExtra("tid");
        int resultTime = Integer.parseInt(tidFraSpil);
        slutTid = resultTime;
        return slutTid;
    }

     */
}
