package com.example.yohan.insertdataapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class dbclass extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "studentss.db";
    public static final String TABLE_NAME = "EXAMDETAILS";
    public static final String COL_1 = "STUDENTID";
    public static final String COL_2 = "NAME";
    public static final String COL_3 = "MARKS";
    public static final String COL_4 = "GRADES";


    public dbclass(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE "+TABLE_NAME+" (STUDENTID VARCHAR(10), NAME VARCHAR(30), MARKS VARCHAR(10), GRADES VARCHAR(10))");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(sqLiteDatabase);

    }

    public boolean insertdata(String id, String name, String marks, String grade){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COL_1, id);
        cv.put(COL_2, name);
        cv.put(COL_3, marks);
        cv.put(COL_4, grade);
        long result = db.insert(TABLE_NAME,null, cv);


       if(result == -1)
           return  false;
       else
           return true;

    }
    public Cursor getAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_NAME,null);

        return res;

    }

    public boolean updateData(String id, String name, String marks, String grade){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1,id);
        contentValues.put(COL_2,name);
        contentValues.put(COL_3,marks);
        contentValues.put(COL_4,grade);

        db.update(TABLE_NAME,contentValues,"STUDENTID = ?",new String []{id});
        return true;

    }


}
