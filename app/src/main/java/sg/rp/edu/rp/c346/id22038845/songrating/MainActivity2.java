package sg.rp.edu.rp.c346.id22038845.songrating;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class MainActivity2 extends AppCompatActivity {

    ArrayList<String> strList;
    ListView lvResults;
    Button btnStars;

    Spinner ddSpn;
    ArrayAdapter<String> spnA;

    ArrayList<Song> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        lvResults = findViewById(R.id.lv);
        btnStars =findViewById(R.id.btnStars);
        ddSpn = findViewById(R.id.spinner);

        spnA = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item);

        /*int position = intentReceived.getIntExtra("pos",0);
        boolean swP = intentReceived.getBooleanExtra("bool",false);*/

        List<Integer> dist = new ArrayList<>();
        DBHelper db = new DBHelper(MainActivity2.this);
        List<Song> songList =db.getAllSongs();
        for(Song song : songList){
            int year = song.getYear();
            if(!dist.contains(year)){
            dist.add(year);
        }}


        for(int year : dist){
            spnA.add(String.valueOf(year));
        }

        ddSpn.setAdapter(spnA);

        btnStars.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                data = db.getAllSongs(5);
                ArrayAdapter listStr = new ArrayAdapter<>(MainActivity2.this, android.R.layout.simple_list_item_1,strList);
                lvResults.setAdapter(listStr);
                db.close();
                listStr.clear();
                for(int i = 0; i <data.size(); i++){
                    listStr.add(data.get(i));
                    listStr.notifyDataSetChanged();
                }
            }
        });


        data = db.getAllSongs();

        strList = new ArrayList<String>();

        ArrayAdapter listStr = new ArrayAdapter<>(MainActivity2.this, android.R.layout.simple_list_item_1,strList);
        lvResults.setAdapter(listStr);
        db.close();
        listStr.clear(); 
        for(int i = 0; i <data.size(); i++){
            listStr.add(data.get(i));
            listStr.notifyDataSetChanged();
        }

        lvResults.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Song song = data.get(position);
                Intent x = new Intent(MainActivity2.this,MainActivity3.class);
                x.putExtra("data",song);
                startActivity(x);
            }
        });

        ddSpn.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                data = db.getSongByYr(Integer.parseInt(parent.getItemAtPosition(position).toString()));
                ArrayAdapter listStr = new ArrayAdapter<>(MainActivity2.this, android.R.layout.simple_list_item_1,strList);
                lvResults.setAdapter(listStr);
                db.close();
                listStr.clear();
                for(int i = 0; i <data.size(); i++){
                    listStr.add(data.get(i));
                    listStr.notifyDataSetChanged();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }

    @Override
    protected void onResume() {
        super.onResume();
        DBHelper db = new DBHelper(MainActivity2.this);

        List<Integer> dist = new ArrayList<>();
        List<Song> songList =db.getAllSongs();
        for(Song song : songList){
            int year = song.getYear();
            if(!dist.contains(year)){
                dist.add(year);
            }}
        spnA.clear();
        for(int year : dist){
            spnA.add(String.valueOf(year));
        }
        spnA.notifyDataSetChanged();


        data = db.getAllSongs();
        ArrayAdapter listStr = new ArrayAdapter<>(MainActivity2.this, android.R.layout.simple_list_item_1,strList);
        listStr.clear();
        for(int i = 0; i <data.size(); i++){
            listStr.add(data.get(i));
            listStr.notifyDataSetChanged();
        }

    }
}