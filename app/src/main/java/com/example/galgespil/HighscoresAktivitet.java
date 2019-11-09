package com.example.galgespil;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;




public class HighscoresAktivitet extends AppCompatActivity {

    ListView listView;

    ArrayList<Score> mListe = new ArrayList<>();







    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_highscores_aktivitet);



        listView = findViewById(R.id.listView);
        /*

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, android.R.id.text1, Collections.singletonList(scorelist.toString()));

        listView.setAdapter(adapter);


 */
        loadData();

        Score maria = new Score("Maria",5000 );
        Score mikkel = new Score("Mikkel",3000);
        Score morten = new Score("Morten",709);

        mListe.add(maria);
        mListe.add(mikkel);
        mListe.add(morten);

        ScoreListAdapter adapter = new ScoreListAdapter(this,R.layout.list_item_adapter,Logik.highScoreList);
        listView.setAdapter(adapter);



    }
    private void loadData(){
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("highscores",null);
        Type type = new TypeToken<ArrayList<Score>>() {}.getType();
        Logik.highScoreList = gson.fromJson(json,type);

        if(Logik.highScoreList == null){
            ArrayList<Score> highscorelist = new ArrayList<>();
            Score maria = new Score("Maria",5000 );
            highscorelist.add(maria);
        }
    }


}
