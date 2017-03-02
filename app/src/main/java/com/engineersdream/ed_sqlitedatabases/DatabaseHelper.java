package com.engineersdream.ed_sqlitedatabases;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by DINESH on 06-12-2016.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "Students.db";
    public static final String TABLE_NAME = "students_table";
    public static final String COL1 = "ID";
    public static final String COL2 = "NAME";
    public static final String COL3 = "SURNAME";
    public static final String COL4 = "MARKS";


    public DatabaseHelper(Context context) {   //whenever this constructor is called, Databse will be created
        super(context, DATABASE_NAME, null, 1);

       // SQLiteDatabase db = this.getWritableDatabase();        // creates your database and table
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
db.execSQL("create table " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, NAME TEXT, SURNAME TEXT, MARKS INTEGER) "); //Takes String as i/p
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    db.execSQL("DROP TABLE IF EXISTS" + TABLE_NAME);
        onCreate(db);
    }

    public boolean insertdata(String name, String surname, String marks)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL2,name);
        contentValues.put(COL3,surname);
        contentValues.put(COL4,marks);
        long result = db.insert(TABLE_NAME,null,contentValues);     //if there is an error it returns -1, if successful it returns inserted row value
          if(result == -1)
          {
             return false;
          }else
          {
              return true;
          }
    }

    public Cursor getAllData()           //Cursor provides random read-write  access to the result set returned
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_NAME,null);
        return res;
    }

    public boolean modifydata(String id, String name, String surname, String marks)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL1,id);
        contentValues.put(COL2,name);
        contentValues.put(COL3,surname);
        contentValues.put(COL4,marks);
        db.update(TABLE_NAME, contentValues,"ID = ?",new String[] { id } );
    return true;
    }

    public Integer deleteData(String id)
      {
        SQLiteDatabase db = this.getWritableDatabase();
       return db.delete(TABLE_NAME, "ID = ?", new String[] {id});          //IF DATA IS DELETED IT RETURNS THE NUMBER OF AFFECTED ROWS, IF NO DATA IS DELETED IT RETURNS 0
    }

}
