package sg.rp.edu.rp.c346.id22038845.songrating;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

import java.util.ArrayList;

public class MainActivity3 extends AppCompatActivity {

    Button btnUpdate, btnDelete, btnCancel;
    EditText ed1, ed2, ed3;
    RadioGroup rdGrp;

    ArrayList<String> strList;

    int rating = 0;

    Song data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        btnUpdate = findViewById(R.id.btnUpdate);
        btnDelete = findViewById(R.id.btnDelete);
        btnCancel = findViewById(R.id.btnCancel);

        ed1 = findViewById(R.id.ed1);
        ed2 = findViewById(R.id.ed2);
        ed3 = findViewById(R.id.ed3);
        rdGrp = findViewById(R.id.rdGrp);

        Intent x = getIntent();
        data = (Song) x.getSerializableExtra("data");
        String title = data.getTitle();
        String singer = data.getSingers();
        int year = data.getYear();
        int stars = data.getStars();

        ed1.setText(title);
        ed2.setText(singer);
        ed3.setText(year+""); //will crash the app or make it act weirdly if you dont convert it to text.

        switch(stars){
            case 1:
                rdGrp.check(R.id.radio1);
                stars = 1;
                break;
            case 2:
                rdGrp.check(R.id.radio2);
                stars = 2;
                break;
            case 3:
                rdGrp.check(R.id.radio3);
                stars = 3;
                break;
            case 4:
                rdGrp.check(R.id.radio4);
                stars = 4;
                break;
            case 5:
                rdGrp.check(R.id.radio5);
                stars = 5;
                break;
        }

        int finalStars = stars;
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(MainActivity3.this);
                data.setSong(ed1.getText().toString(),ed2.getText().toString(), Integer.parseInt(ed3.getText().toString()), finalStars);
                dbh.updateSong(data);
                dbh.close();
                finish();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(MainActivity3.this);
                dbh.deleteSong(data.getId());
                finish();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }

}