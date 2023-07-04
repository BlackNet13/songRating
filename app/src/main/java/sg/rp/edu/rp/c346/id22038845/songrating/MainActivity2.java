package sg.rp.edu.rp.c346.id22038845.songrating;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity2 extends AppCompatActivity {

    ArrayList<String> strList;
    ListView lvResults;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        lvResults = findViewById(R.id.lv);

        Intent intentReceived = getIntent();
        int position = intentReceived.getIntExtra("pos",0);
        boolean swP = intentReceived.getBooleanExtra("bool",false);

        DBHelper db = new DBHelper(MainActivity2.this);

        ArrayList<Song> data = db.getSongs(position,swP);
        strList = new ArrayList<String>();
        ArrayAdapter listStr = new ArrayAdapter<>(MainActivity2.this, android.R.layout.simple_list_item_1,strList);
        lvResults.setAdapter(listStr);
        db.close();

        for(int i = 0; i <data.size(); i++){
            listStr.add(data.get(i));
            listStr.notifyDataSetChanged();
        }
    }
}