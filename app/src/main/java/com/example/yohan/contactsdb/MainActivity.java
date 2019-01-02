package com.example.yohan.contactsdb;

import android.content.Intent;
import android.database.SQLException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText etname,ettelno;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etname = findViewById(R.id.etName);
        ettelno = findViewById(R.id.etCelNo);
    }


    public void SubmitData(View v){

        String name = etname.getText().toString().trim();
        String cell = ettelno.getText().toString().trim();

        try{
            ContactsDb db = new ContactsDb(this);
            db.open();
            db.CreateEntry(name,cell);
            db.close();
            Toast.makeText(MainActivity.this,"Successfully saved!",Toast.LENGTH_SHORT).show();
            etname.setText("");
            ettelno.setText("");
        }catch (SQLException e){
            Toast.makeText(MainActivity.this,e.getMessage(),Toast.LENGTH_SHORT).show();
        }

    }

    public void ShowData(View v){

        Intent i = new Intent(MainActivity.this, datapage.class);
        startActivity(i);


    }



    public void EditData(View v){

        try{
            ContactsDb db = new ContactsDb(this);
            db.open();
            db.updatEntry("1","Yohan","0717502571");
            db.close();
            Toast.makeText(MainActivity.this,"Successfully added",Toast.LENGTH_LONG);



        }catch (SQLException e){

            Toast.makeText(MainActivity.this,e.getMessage(),Toast.LENGTH_LONG);


        }

    }

    public void DeleteData(View v){

        ContactsDb DB = new ContactsDb(this);
        DB.open();
        DB.deleteEntry("2");
        DB.close();
        Toast.makeText(MainActivity.this,"Successfully Deleted",Toast.LENGTH_LONG);

        try{

            ContactsDb db = new ContactsDb(this);
            db.open();
            db.deleteEntry("2");
            db.close();
            Toast.makeText(MainActivity.this,"Successfully Deleted",Toast.LENGTH_LONG);

        }catch (SQLException E){
            Toast.makeText(MainActivity.this, E.getMessage(),Toast.LENGTH_LONG);

        }


    }
}
