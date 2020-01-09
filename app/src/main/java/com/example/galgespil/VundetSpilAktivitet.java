package com.example.galgespil;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
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
import nl.dionsegijn.konfetti.KonfettiView;
import nl.dionsegijn.konfetti.models.Shape;

import static com.example.galgespil.StartSpilAktivitet.logik;


public class VundetSpilAktivitet extends AppCompatActivity implements View.OnClickListener {

    Button tilHovedmenu, spilIgen, gemHighscore;
    TextView ordGættet, tidSlut, status, score, forkerteBog;
    EditText skrivNavn;
    MediaPlayer player;

    int point = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_vundet_spil_aktivitet);

        //Status er kendt, da denne aktivitet ellers ikke ville været starter, men det var for at øve put-/getExtra
        String statusFraSpil = getIntent().getStringExtra("status");
        String tidFraSpil = getIntent().getStringExtra("tid");

        //Lyd spilles når aktiviteten begyndes
        player = MediaPlayer.create(this,R.raw.congratuationsvoice);
        player.start();

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

        //Nedenstående kodestykke er fra https://android-arsenal.com/details/1/5884#!description,
        // jeg har personliggjort det lidt, men det er begrænset da det er skrevet i kotlin, så kan ikke helt
        // finde ud af hvordan mulighederne er

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
            //Hvis brugeren prøver at gemme uden at have indtastet et navn, så vises der
            // fejlbesked i Toast og en animation af Edittext
            if(!(skrivNavn.getText().toString().trim().length() > 0)){
                skrivNavn.startAnimation(shakeError());
                Toast.makeText(this, "Indtast dit navn for at gemme!", Toast.LENGTH_SHORT).show();
                return;
            }else{
                Score person = new Score(skrivNavn.getText().toString(),udregnScore());
                Logik.highScoreList.add(person);

                gemData();

                //Gør sådan så knappen kun kan trykkes på én gang. Da brugeren ellers ville
                // kunne gemme samme score flere gange
                gemHighscore.setEnabled(false);

                //gemmer keyboardet når gem knap trykkes
                skrivNavn.onEditorAction(EditorInfo.IME_ACTION_DONE);

                logik.erListeTom = false;

                Toast.makeText(this, "Highscore gemt!", Toast.LENGTH_SHORT).show();

            }


        }
    }

    //Gemner data lokalt på telefonen.
    public void gemData(){
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(Logik.highScoreList);
        editor.putString("highscores",json);
        editor.apply();

        //Så kan der heller ikke skrives i editText når først én highscore er gemt. Det er bare
        // 'federe' at både knap og edittext gøres "u-trykbare"
        skrivNavn.setEnabled(false);
    }


    public int udregnScore(){
        //Brugeren har som udgangspunkt en score på 1000, hvorefter antallet af
        // sekunder bliver trukket fra (* 5), samt en penalty på 50 point pr. forkert gættet bogstav
        String tidFraSpil = getIntent().getStringExtra("tid");

        int resultTime = Integer.parseInt(tidFraSpil);
        int penalty = logik.getAntalForkerteBogstaver();
        point = 1000 - (resultTime * 5) - (penalty*50);
        return point;
    }

//Ryster med edittext feltet når brugeren prøver at gemme og der intet navn er skrevet i
// feltet. Ideen er min egen, men har læst på nettet til hvordan man kunne gøre
    public TranslateAnimation shakeError() {
        TranslateAnimation shake = new TranslateAnimation(0, 10, 0, 0);
        shake.setDuration(500);
        shake.setInterpolator(new CycleInterpolator(7));
        return shake;
    }


}
