package sg.rp.edu.rp.c346.id22038845.songrating;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    Button btnInsert, btnShowList;
    //ListView lvResults;
    EditText ed1, ed2, ed3;
    //Spinner ddSpn;
    RadioGroup rdGrp;

    ArrayList<String> strList;

    //boolean asc, asc1, asc2, asc3, asc4 = true;

    int rating = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnInsert = findViewById(R.id.btnInsert);
        btnShowList = findViewById(R.id.btnShowList);

        //ddSpn = findViewById(R.id.spinner);
        //lvResults = findViewById(R.id.lv);

        ed1 = findViewById(R.id.ed1);
        ed2 = findViewById(R.id.ed2);
        ed3 = findViewById(R.id.ed3);
        rdGrp = findViewById(R.id.rdGrp);

        Intent intent = new Intent(MainActivity.this, MainActivity2.class);

        rdGrp.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId == R.id.radio1){
                    rating = 1;
                }else if(checkedId == R.id.radio2){
                    rating = 2;
                }else if(checkedId == R.id.radio3){
                    rating = 3;
                }else if(checkedId == R.id.radio4){
                    rating = 4;
                } else if (checkedId == R.id.radio5) {
                    rating = 5;
                }
            }
        });

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper db = new DBHelper(MainActivity.this);

                db.insertSong(ed1.getText().toString(),ed2.getText().toString(),ed3.getText().toString(), rating);
            }
        });

        btnShowList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper db = new DBHelper(MainActivity.this);
                //ArrayList<String> data = db.getSongContent();
                //intent.putExtra("data",data);
                startActivity(intent);
            }
        });

        /*ddSpn.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //DBHelper db = new DBHelper(MainActivity.this);
                boolean swP = true;
                switch (position){
                    case 1:
                        asc1 = true;
                        swP = asc1;
                        break;
                    case 2:
                        asc1 = false;
                        swP = asc1;
                        break;
                    case 3:
                        asc2 = true;
                        swP = asc2;
                        break;
                    case 4:
                        asc2 = false;
                        swP = asc2;
                        break;
                    case 5:
                        asc3 = true;
                        swP = asc3;
                        break;
                    case 6:
                        asc3 = false;
                        swP = asc3;
                        break;
                    case 7:
                        asc4 = true;
                        swP = asc4;
                        break;
                    case 8:
                        asc4 = false;
                        swP = asc4;
                        break;
                }

                if(position!=0) {
                    intent.putExtra("pos", position);
                    intent.putExtra("bool", swP);
                    startActivity(intent);
                }

                ArrayList<Song> data = db.getSongs(position,swP);
                strList = new ArrayList<String>();
                ArrayAdapter listStr = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1,strList);
                lvResults.setAdapter(listStr);
                db.close();

                for(int i = 0; i <data.size(); i++){
                    listStr.add(data.get(i));
                    listStr.notifyDataSetChanged();
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });*/



    }
}