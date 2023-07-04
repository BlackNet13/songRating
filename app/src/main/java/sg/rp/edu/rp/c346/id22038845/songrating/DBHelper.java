package sg.rp.edu.rp.c346.id22038845.songrating;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VER = 1;
    private static final String DATABASE_NAME = "songs.db";

    private static final String TABLE_SONG = "song";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_TITLE = "title";
    private static final String COLUMN_SINGERS = "singers";
    private static final String COLUMN_YEAR = "year";
    private static final String COLUMN_STARS = "stars";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VER);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableSql = "CREATE TABLE " + TABLE_SONG + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_TITLE + " TEXT,"
                + COLUMN_SINGERS + " TEXT,"
                + COLUMN_YEAR + " INT,"
                + COLUMN_STARS + " INT)";
        db.execSQL(createTableSql);
        Log.i("info", "created tables");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SONG);
        onCreate(db);
    }

    public void insertSong(String title, String singers, String year, int stars) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_TITLE, title);
        values.put(COLUMN_SINGERS, singers);
        values.put(COLUMN_YEAR,Integer.parseInt(year));
        values.put(COLUMN_STARS,stars);
        db.insert(TABLE_SONG, null, values);
        db.close();
    }

    public ArrayList<String> getSongContent() {
        ArrayList<String> songs = new ArrayList<String>();
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {COLUMN_ID, COLUMN_TITLE, COLUMN_SINGERS,COLUMN_YEAR,COLUMN_STARS};
        Cursor cursor = db.query(TABLE_SONG, columns, null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                int colType = cursor.getType(1);
                if(colType == Cursor.FIELD_TYPE_STRING) {
                    songs.add(cursor.getString(1));
                }else if(colType == Cursor.FIELD_TYPE_INTEGER){
                    songs.add(String.valueOf(cursor.getInt(1)));
                }

            } while (cursor.moveToNext());
        }
        //close connection
        cursor.close();
        db.close();

        return songs;
    }

    public ArrayList<Song> getSongs(int pos,boolean asc) {
        ArrayList<Song> songs = new ArrayList<Song>();
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {COLUMN_ID, COLUMN_TITLE, COLUMN_SINGERS, COLUMN_YEAR, COLUMN_STARS};
        String orderBy = " ASC";
        if (!asc) {
            orderBy = " DESC";
        }

        if (pos != 0){
            Cursor cursor= db.query(TABLE_SONG, columns, null, null, null, null, COLUMN_ID + orderBy, null);
            if(pos == 1 || pos == 2){
                cursor = db.query(TABLE_SONG, columns, null, null, null, null, COLUMN_TITLE + orderBy, null);
            }else if(pos == 3 || pos == 4){
                cursor = db.query(TABLE_SONG, columns, null, null, null, null, COLUMN_SINGERS + orderBy, null);
            }else if(pos == 5 || pos == 6){
                cursor = db.query(TABLE_SONG, columns, null, null, null, null, COLUMN_YEAR + orderBy, null);
            }else if(pos==7||pos==8){
                cursor = db.query(TABLE_SONG, columns, null, null, null, null, COLUMN_STARS + orderBy, null);
            }
            if (cursor.moveToFirst()) {
                do {
                    int id = cursor.getInt(0);
                    String title = cursor.getString(1);
                    String singers= cursor.getString(2);
                    int year = cursor.getInt(3);
                    int stars = cursor.getInt(4);
                    Song obj = new Song(id, title, singers, year, stars);
                    songs.add(obj);
                } while (cursor.moveToNext());
            }
            cursor.close();
            db.close();
        }
        return songs;
    }
}
