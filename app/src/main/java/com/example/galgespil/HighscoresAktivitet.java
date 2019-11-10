package com.example.galgespil;

import androidx.appcompat.app.AppCompatActivity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ListView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;



public class HighscoresAktivitet extends AppCompatActivity {

    ListView listView;



    ArrayList<Score> mListe = new ArrayList<>();







    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_highscores_aktivitet);



        listView = findViewById(R.id.listView);

        loadData();

        //Se note i subklasse
        //Logik.highScoreList.sort(new ScoreSorter());



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
            System.out.println("Listen er null");
        }

    }


}


/*
Virker kun med min API 26 og tlf er 21
public class ScoreSorter implements Comparator<Score>
{


    @Override
    public int compare(Score o1, Score o2) {
        return o2.getScore().compareTo(o1.getScore());
    }
}

 */
