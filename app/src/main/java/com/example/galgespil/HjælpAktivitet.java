package com.example.galgespil;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

public class HjælpAktivitet extends AppCompatActivity {
TextView tekst;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regler_aktivitet);


        //Så jeg kan faktisk også godt finde ud af at gemme i @values/strings
        tekst = findViewById(R.id.forklaringTV);
        tekst.setMovementMethod(new ScrollingMovementMethod());
    }
}
