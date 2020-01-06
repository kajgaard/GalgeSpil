package com.example.galgespil;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.animation.CycleInterpolator;
import android.view.animation.TranslateAnimation;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

import nl.dionsegijn.konfetti.KonfettiView;
import nl.dionsegijn.konfetti.models.Shape;

import static com.example.galgespil.StartSpilAktivitet.logik;


public class VundetSpilAktivitet extends AppCompatActivity implements View.OnClickListener {

    Button tilHovedmenu, spilIgen, gemHighscore;
    TextView ordGættet, tidSlut, status, score, forkerteBog;
    EditText skrivNavn;
    KonfettiView viewKonfetti;
    MediaPlayer player;




    int point = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        setContentView(R.layout.activity_vundet_spil_aktivitet);

        String statusFraSpil = getIntent().getStringExtra("status");
        String tidFraSpil = getIntent().getStringExtra("tid");

        player = MediaPlayer.create(this,R.raw.congratuationsvoice);

        tilHovedmenu = findViewById(R.id.tilHovedmenu);
        tilHovedmenu.setOnClickListener(this);

        status = findViewById(R.id.status);
        status.setText(statusFraSpil);

        spilIgen = findViewById(R.id.spilIgenKnap);
        spilIgen.setOnClickListener(this);

        ordGættet = findViewById(R.id.ordGættet);
        ordGættet.setText("Tillykke du gættede ordet: \n"+ logik.getOrdet());

        forkerteBog = findViewById(R.id.forkerteBog);
        forkerteBog.setText("Du gættede " + logik.getAntalForkerteBogstaver() + " bogstaver forkert");

        tidSlut = findViewById(R.id.tidSlutTV);
        tidSlut.setText("Din tid er: "+ tidFraSpil + " sekunder");

        score = findViewById(R.id.score);
        score.setText("Du scorede " + udregnScore()+" point");

        gemHighscore = findViewById(R.id.gemHighscoreKnap);
        gemHighscore.setOnClickListener(this);

        skrivNavn = findViewById(R.id.skrivNavn);
        skrivNavn.setHint("Dit navn");

        //Nedenstående kodestykke er fra https://android-arsenal.com/details/1/5884#!description, jeg har ikke personliggjort det helt vildt, da det er skrevet i kotlin, så kan ikke helt finde ud af hvordan mulighederne er

        final KonfettiView konfettiView = findViewById(R.id.viewKonfetti);
        konfettiView.build()
                .addColors(Color.rgb(234, 117, 240), Color.rgb(36, 240, 192), Color.rgb(245, 152, 66), Color.rgb(
                        66, 173, 245
                ), Color.rgb(245, 78, 66), Color.rgb(99, 191, 78))
                .setSpeed(2f, 8f)
                .setFadeOutEnabled(true)
                .setTimeToLive(2000L)
                .addShapes(Shape.RECT, Shape.CIRCLE)
                .setPosition(0f, 359f, -359f, 0f)
                .setDirection(0,359)
                .stream(200, 1500L);
    }


    @Override
    public void onClick(View v) {
        if(v == tilHovedmenu){
            Intent i = new Intent(this,Hovedmenu.class);
            this.startActivity(i);
            logik.nulstil();

            finish();

        } else if (v == spilIgen){
            logik.nulstil();
            Intent i = new Intent(this,StartSpilAktivitet.class);
            this.startActivity(i);

            finish();


        }else if (v == gemHighscore){

            if(!(skrivNavn.getText().toString().trim().length() > 0)){
                skrivNavn.startAnimation(shakeError());
                Toast.makeText(this, "Indtast dit navn for at gemme!", Toast.LENGTH_SHORT).show();
                return;
            }else{
                Score person = new Score(skrivNavn.getText().toString(),udregnScore());
                Logik.highScoreList.add(person);
                //person.setNavn(""+skrivNavn.getText());
                //person.setScore(udregnScore());

                gemData();


                gemHighscore.setEnabled(false);

                //gemmer keyboardet når gem knap trykkes
                skrivNavn.onEditorAction(EditorInfo.IME_ACTION_DONE);

                logik.erListeTom = false;

                Toast.makeText(this, "Highscore gemt!", Toast.LENGTH_SHORT).show();

            }


        }
    }

    public void gemData(){
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(Logik.highScoreList);
        editor.putString("highscores",json);
        editor.apply();

        skrivNavn.setEnabled(false);
    }

    private void loadData(){
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("highscores",null);
        Type type = new TypeToken<ArrayList<Score>>() {}.getType();
        Logik.highScoreList = gson.fromJson(json,type);

        if(Logik.highScoreList == null){
            System.out.println("ARRAYLIST FINDES IKKE");
        }
    }

    public int udregnScore(){
        //Brugeren har som udgangspunkt en score på 1000, hvorefter antallet af
        // sekunder bliver trukket fra, samt en penalty på 10 point pr. forkert gættet bogstav
        String tidFraSpil = getIntent().getStringExtra("tid");

        int resultTime = Integer.parseInt(tidFraSpil);
        int penalty = logik.getAntalForkerteBogstaver();
        point = 1000 - resultTime - (penalty*10);
        return point;
    }


    public TranslateAnimation shakeError() {
        TranslateAnimation shake = new TranslateAnimation(0, 10, 0, 0);
        shake.setDuration(500);
        shake.setInterpolator(new CycleInterpolator(7));
        return shake;
    }

    public static int getScreenWidth() {
        return Resources.getSystem().getDisplayMetrics().widthPixels;
    }

    /*public void play(View v){
        Mediaplayer.
    }

     */


}
