package com.example.galgespil;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class IndstillingerAktivitet extends AppCompatActivity implements View.OnClickListener {
Button hovedmenu, rydData;
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
        }
    }
}
