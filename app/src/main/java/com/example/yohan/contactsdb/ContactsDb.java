package com.example.yohan.contactsdb;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ContactsDb {

    public static final String KEY_ID = "id";
    public static final String KEY_Name = "Name";
    public static final String KEY_CELL = "Cel_No";

    private final String DATABASE_NAME = "ContactsDB";
    private final String DATABASE_TABLE = "ContactsTabale";
    private final int DATABASE_VERSION = 1;

    private DBHelper ourHelper;
    private final Context ourContext;
    private SQLiteDatabase ourDatabse;

    public ContactsDb(Context context){
        ourContext=context;
    }


    public class DBHelper  extends SQLiteOpenHelper{

        public DBHelper(Context context){

            super(context,DATABASE_NAME,null,DATABASE_VERSION);
        }


        @Override
        public void onCreate(SQLiteDatabase db) {

            String sqlCode= "CREATE TABLE "+DATABASE_TABLE+" ("+KEY_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+KEY_Name+" TEXT NOT NULL, "+KEY_CELL+" TEXT NOT NULL);";

            db.execSQL(sqlCode);

        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

            db.execSQL("DROP TABLE IF EXISTS "+DATABASE_TABLE);
            onCreate(db);

        }



    }

    public ContactsDb open() throws SQLException {

        ourHelper = new DBHelper(ourContext);
        ourDatabse = ourHelper.getWritableDatabase();
        return this;
    }

    public void close(){

        ourHelper.close();


    }

    public long CreateEntry(String name, String cell){

        ContentValues cv = new ContentValues();
        cv.put(KEY_Name,name);
        cv.put(KEY_CELL,cell);

        return  ourDatabse.insert(DATABASE_TABLE,null,cv);


    }

    public String getData(){

        String [] columns = {KEY_ID,KEY_Name,KEY_CELL};
        Cursor c = ourDatabse.query(DATABASE_TABLE,columns,null,null,null,null,null);

        String results="";

        int iROWID = c.getColumnIndex(KEY_ID);
        int iNAME = c.getColumnIndex(KEY_Name);
        int iCELL = c.getColumnIndex(KEY_CELL);

        for (c.moveToFirst();!c.isAfterLast();c.moveToNext()){
            results = c.getString(iROWID)+": "+c.getString(iNAME)+" "+c.getString(iCELL)+"\n"+"\n";

        }

        c.close();


        return results;
    }

    public long deleteEntry(String rowID){
        return ourDatabse.delete(DATABASE_TABLE,KEY_ID+"=?",new String[]{rowID});

    }

    public long updatEntry(String rowID, String name, String cell){

        ContentValues cv = new ContentValues();
        cv.put(KEY_ID,rowID);
        cv.put(KEY_Name,name);
        cv.put(KEY_CELL,cell);

        return ourDatabse.update(DATABASE_TABLE,cv,KEY_ID+"=?",new String[]{rowID});

    }



}
