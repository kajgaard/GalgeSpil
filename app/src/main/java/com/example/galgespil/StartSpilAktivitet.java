package com.example.galgespil;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import static android.widget.Toast.LENGTH_SHORT;


public class StartSpilAktivitet extends AppCompatActivity implements View.OnClickListener {
    static Logik logik = new Logik();
    StopUr stopUr = new StopUr();
    Button a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p , q, r, s, t, u, ve, w, x, y, z, ae, oe, aa;
    Button afslutKnap;
    TextView ukendtOrd, liv, tid;
    ImageView hangman, hjerte;
    Runnable opdaterTid;
    Handler handler = new Handler();
    String exception = null;
    Boolean success;
    Context mContext = this;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_spil_aktivitet);
        ukendtOrd = findViewById(R.id.spgtext);

        //Jeg aner simpelthen ikke hvorfor jeg ikke kan løse understående warning.. :(

        if(Logik.skalOrdHentes){
            ukendtOrd.setText("Henter ord fra DR...");
            new AsyncTask(){

                @Override
                protected Object doInBackground(Object[] objects) {

                    try{
                        logik.hentOrdFraDr();
                        success = true;
                        return "Hent af ord gennemført";

                    } catch (Exception ex) {
                        ex.printStackTrace();
                        exception = ex.getMessage();
                        success = false;
                        //publishProgress(ex);

                        return "Hent af ord ikke gennemført" + ex;
                    }

                }

                @Override
                protected void onPostExecute(Object o) {
                    ukendtOrd.setText(""+ logik.getSynligtOrd());

                    if(success == false){
                        Toast.makeText(mContext, "Kunne ikke hente ord fra DR... Fejl: " + exception, Toast.LENGTH_LONG).show();
                    }

                    if(success == true){
                        Toast.makeText(mContext, "Successfuldt hentet ord fra DR ", LENGTH_SHORT).show();
                    }

                }
            }.execute();
            Logik.skalOrdHentes = false;
        }else{

            ukendtOrd.setText(logik.getSynligtOrd());

        }

        //Måske findes der en smartere løsning til dette - men indtil videre er dette en lang klasse :)
        //Grunden er jo fordi at jeg valgte at lave mit eget tastatur, da jeg syntes det var fedt.

        a = findViewById(R.id.buttonA);
        a.setOnClickListener(this);

        b = findViewById(R.id.buttonB);
        b.setOnClickListener(this);

        c = findViewById(R.id.buttonC);
        c.setOnClickListener(this);

        d = findViewById(R.id.buttonD);
        d.setOnClickListener(this);

        e = findViewById(R.id.buttonE);
        e.setOnClickListener(this);

        f = findViewById(R.id.buttonF);
        f.setOnClickListener(this);

        g = findViewById(R.id.buttonG);
        g.setOnClickListener(this);

        h = findViewById(R.id.buttonH);
        h.setOnClickListener(this);

        i = findViewById(R.id.buttonI);
        i.setOnClickListener(this);

        j = findViewById(R.id.buttonJ);
        j.setOnClickListener(this);

        k = findViewById(R.id.buttonK);
        k.setOnClickListener(this);

        l = findViewById(R.id.buttonL);
        l.setOnClickListener(this);

        m = findViewById(R.id.buttonM);
        m.setOnClickListener(this);

        n = findViewById(R.id.buttonN);
        n.setOnClickListener(this);

        o = findViewById(R.id.buttonO);
        o.setOnClickListener(this);

        p = findViewById(R.id.buttonP);
        p.setOnClickListener(this);

        q = findViewById(R.id.buttonQ);
        q.setOnClickListener(this);

        r = findViewById(R.id.buttonR);
        r.setOnClickListener(this);

        s = findViewById(R.id.buttonS);
        s.setOnClickListener(this);

        t = findViewById(R.id.buttonT);
        t.setOnClickListener(this);

        u = findViewById(R.id.buttonU);
        u.setOnClickListener(this);

        ve = findViewById(R.id.buttonV);
        ve.setOnClickListener(this);

        w = findViewById(R.id.buttonW);
        w.setOnClickListener(this);

        x = findViewById(R.id.buttonX);
        x.setOnClickListener(this);

        y = findViewById(R.id.buttonY);
        y.setOnClickListener(this);

        z = findViewById(R.id.buttonZ);
        z.setOnClickListener(this);

        ae = findViewById(R.id.buttonAE);
        ae.setOnClickListener(this);

        oe = findViewById(R.id.buttonOE);
        oe.setOnClickListener(this);

        aa = findViewById(R.id.buttonAA);
        aa.setOnClickListener(this);

        afslutKnap = findViewById(R.id.afslutKnap);
        afslutKnap.setOnClickListener(this);

        liv = findViewById(R.id.antalLiv);
        liv.setText("6");

        tid = findViewById(R.id.tid);
        tid.setText("0");

        hangman = findViewById(R.id.hangman);
        hangman.setImageResource(R.drawable.galgepng);

        hjerte = findViewById(R.id.hearts);
        hjerte.setImageResource(R.drawable.heart3);

        //Stopuret her bruget egentlig kun til at få den samlede tid. Optællingen af sekunder som
        // foregår når spillet spilles er fra en metode som jeg selv har skrevet.
        //Lidt unødigt med metoder med samme funktion, men min egen metode svinger nogle gange
        // med 1 sekund (fordi den runder op tænker jeg) når tiden skulle sendes til VundetspilAktivitet.
        stopUr.start();

        //Sørg for tid bliver opdateret hvert sekund
        opdaterTidMetode();

    }


    @Override
    public void onClick(View v) {

        //Igen - mange gentagelser
        if(v == a){
            String bogstav = a.getText().toString().toLowerCase();
            logik.gætBogstav(bogstav);
            a.setEnabled(false);

        }else if (v == b){
            String bogstav = b.getText().toString().toLowerCase();
            logik.gætBogstav(bogstav);
            b.setEnabled(false);

        }else if(v == c){
            String bogstav = c.getText().toString().toLowerCase();
            logik.gætBogstav(bogstav);
            c.setEnabled(false);

        }else if(v == d){
            String bogstav = d.getText().toString().toLowerCase();
            logik.gætBogstav(bogstav);
            d.setEnabled(false);

        }else if(v == e){
            String bogstav = e.getText().toString().toLowerCase();
            logik.gætBogstav(bogstav);
            e.setEnabled(false);

        }else if(v == f){
            String bogstav = f.getText().toString().toLowerCase();
            logik.gætBogstav(bogstav);
            f.setEnabled(false);

        }else if(v == g){
            String bogstav = g.getText().toString().toLowerCase();
            logik.gætBogstav(bogstav);
            g.setEnabled(false);

        }else if(v == h){
            String bogstav = h.getText().toString().toLowerCase();
            logik.gætBogstav(bogstav);
            h.setEnabled(false);

        }else if(v == i){
            String bogstav = i.getText().toString().toLowerCase();
            logik.gætBogstav(bogstav);
            i.setEnabled(false);

        }else if(v == j){
            String bogstav = j.getText().toString().toLowerCase();
            logik.gætBogstav(bogstav);
            j.setEnabled(false);

        }else if(v == k){
            String bogstav = k.getText().toString().toLowerCase();
            logik.gætBogstav(bogstav);
            k.setEnabled(false);

        }else if(v == l){
            String bogstav = l.getText().toString().toLowerCase();
            logik.gætBogstav(bogstav);
            l.setEnabled(false);

        }else if(v == m){
            String bogstav = m.getText().toString().toLowerCase();
            logik.gætBogstav(bogstav);
            m.setEnabled(false);

        }else if(v == n){
            String bogstav = n.getText().toString().toLowerCase();
            logik.gætBogstav(bogstav);
            n.setEnabled(false);

        }else if(v == o){
            String bogstav = o.getText().toString().toLowerCase();
            logik.gætBogstav(bogstav);
            o.setEnabled(false);

        }else if(v == p){
            String bogstav = p.getText().toString().toLowerCase();
            logik.gætBogstav(bogstav);
            p.setEnabled(false);

        }else if(v == q){
            String bogstav = q.getText().toString().toLowerCase();
            logik.gætBogstav(bogstav);
            q.setEnabled(false);

        }else if(v == r){
            String bogstav = r.getText().toString().toLowerCase();
            logik.gætBogstav(bogstav);
            r.setEnabled(false);

        }else if(v == s){
            String bogstav = s.getText().toString().toLowerCase();
            logik.gætBogstav(bogstav);
            s.setEnabled(false);

        }else if(v == t){
            String bogstav = t.getText().toString().toLowerCase();
            logik.gætBogstav(bogstav);
            t.setEnabled(false);

        }else if(v == u){
            String bogstav = u.getText().toString().toLowerCase();
            logik.gætBogstav(bogstav);
            u.setEnabled(false);

        }else if(v == ve){
            String bogstav = ve.getText().toString().toLowerCase();
            logik.gætBogstav(bogstav);
            ve.setEnabled(false);

        }else if(v == x){
            String bogstav = x.getText().toString().toLowerCase();
            logik.gætBogstav(bogstav);
            x.setEnabled(false);

        }else if(v == y){
            String bogstav = y.getText().toString().toLowerCase();
            logik.gætBogstav(bogstav);
            y.setEnabled(false);

        }else if(v == z){
            String bogstav = z.getText().toString().toLowerCase();
            logik.gætBogstav(bogstav);
            z.setEnabled(false);

        }else if(v == ae){
            String bogstav = ae.getText().toString().toLowerCase();
            logik.gætBogstav(bogstav);
            ae.setEnabled(false);

        }else if(v == oe){
            String bogstav = oe.getText().toString().toLowerCase();
            logik.gætBogstav(bogstav);
            oe.setEnabled(false);

        }else if(v == aa){
            String bogstav = aa.getText().toString().toLowerCase();
            logik.gætBogstav(bogstav);
            aa.setEnabled(false);


        }else if(v == afslutKnap) {

            Intent i = new Intent(this, AfbrudtSpilAktivitet.class);
            i.putExtra("status", "Øv! Du gav op!");
            this.startActivity(i);
            finish();

        }

        opdaterSkærm();

    }

    private void opdaterSkærm(){
        ukendtOrd.setText(logik.getSynligtOrd());
        liv.setText(""+getAntalLiv());
        tid.setText(""+stopUr.getElapsedTimeSecs());

        //Tegn manden lidt efter lidt
        if(getAntalLiv() == 5){
            hangman.setImageResource(R.drawable.forkert1png);
        } else if (getAntalLiv() == 4){
            hangman.setImageResource(R.drawable.forkert2png);
        } else if (getAntalLiv() == 3){
            hangman.setImageResource(R.drawable.forkert3png);
        } else if(getAntalLiv() == 2){
            hangman.setImageResource(R.drawable.forkert4png);
        } else if (getAntalLiv() == 1) {
            hangman.setImageResource(R.drawable.forkert5png);
        } else if (getAntalLiv() == 0){
            hangman.setImageResource(R.drawable.forkert6png);
        }

        if (logik.erSpilletVundet()) {
            stopUr.stop();

            Intent i = new Intent(this, VundetSpilAktivitet.class);

            i.putExtra("status", "Du har vundet!");
            i.putExtra("tid",""+stopUr.getElapsedTimeSecs());
            this.startActivity(i);
            finish();

        }
        if (logik.erSpilletTabt()) {
            stopUr.stop();

            Intent i = new Intent(this, TabtSpilAktivitet.class);
            i.putExtra("status", "Øv! Du tabte!");
            i.putExtra("tid", ""+stopUr.getElapsedTimeSecs());


            this.startActivity(i);
            finish();
        }
    }

    //Mist 1 liv for hvert bogstav der bliver gættet forkert
    public int getAntalLiv(){
        int antalLiv = 6 - logik.getAntalForkerteBogstaver();
        return antalLiv;
    }


    public void opdaterTidMetode(){

        opdaterTid = new Runnable() {

            int antalSekunderGaaet = 0;

            public void run() {

                if (!logik.erSpilletVundet() || !logik.erSpilletTabt()) {
                    antalSekunderGaaet++;
                    tid.setText(""+antalSekunderGaaet);

                    handler.postDelayed(this, 1000); // udfør denne Runnable igen om 1 sekund
                }
            }
        };
        handler.postDelayed(opdaterTid, 1000); // udfør om 1 sekund
    }


}
