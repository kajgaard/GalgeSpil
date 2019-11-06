package com.example.galgespil;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.gson.Gson;

import java.util.Collections;


public class HighscoresAktivitet extends AppCompatActivity {

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_highscores_aktivitet);

        SharedPreferences mPrefs = getSharedPreferences("Test", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = mPrefs.getString("test", "");
        ScoreList scorelist = gson.fromJson(json, ScoreList.class);
        System.out.println("Hent Json Objekt: " + scorelist.toString());

        listView = findViewById(R.id.listView);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, android.R.id.text1, Collections.singletonList(scorelist.toString()));

        listView.setAdapter(adapter);

    }


}
