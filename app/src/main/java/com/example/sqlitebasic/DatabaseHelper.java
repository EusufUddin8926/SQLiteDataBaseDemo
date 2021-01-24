package com.example.sqlitebasic;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String databaseName="Student.db";
    public static final String tableName= "student_info";
    public static final String playerId="_id";
    public static final String playerCode="code";
    public static final String playerName="name";
    public static final String playerType="type";
    public static final int versionNo = 2;
    public static final String createtable="CREATE TABLE "+tableName+" ("+playerId+"  INTEGER PRIMARY KEY AUTOINCREMENT," +
            ""+playerName+" VARCHAR(100),"+playerType+" VARCHAR(50),"+playerCode+" INTEGER); ";

    public static final String Upgradetable="DROP TABLE IF EXISTS "+tableName;
    public static final String displaytable=" SELECT * FROM " +tableName;


    private Context context;


    public DatabaseHelper(@Nullable Context context) {
        super(context, databaseName, null, versionNo);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try{
            db.execSQL(createtable);
            Toast.makeText(context, "Table Created", Toast.LENGTH_SHORT).show();

        }catch (Exception e ){
            Toast.makeText(context, "Table Not Created", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        try{
            Toast.makeText(context, " Upgraded", Toast.LENGTH_SHORT).show();
            db.execSQL(Upgradetable);
            onCreate(db);
        }catch(Exception e) {
            Toast.makeText(context, "Not Upgraded", Toast.LENGTH_SHORT).show();
        }

    }

    public long insert(String name, String type,String code){

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(playerName, name);
        contentValues.put(playerType, type);
        contentValues.put(playerCode, code);

        long value=  sqLiteDatabase.insert(tableName,null,contentValues);


        return value;
    }

    public Cursor Show(){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(displaytable,null);
        return cursor;
    }

    public int delete(String id){
        SQLiteDatabase sqLiteDatabase= this.getWritableDatabase();
        int value= sqLiteDatabase.delete(tableName,playerId+" = ?",new String[]{id});
        return value;
    }

    public long update(String id,String name, String code, String type){
        SQLiteDatabase sqLiteDatabase= this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();

        contentValues.put(playerId,id);
        contentValues.put(playerName,name);
        contentValues.put(playerType,type);
        contentValues.put(playerCode,code);

        long value= sqLiteDatabase.update(tableName,contentValues,playerId+" = ?",new String[]{id});
        return value;

    }
}
