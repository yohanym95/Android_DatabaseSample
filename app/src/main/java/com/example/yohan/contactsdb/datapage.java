package com.example.yohan.contactsdb;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.SQLException;

public class datapage extends AppCompatActivity {
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datapage);
        textView = findViewById(R.id.tvshowData);


        try{

            ContactsDb db = new ContactsDb(this);
            db.open();
            textView.setText(db.getData());
            db.close();

        }catch(Exception E){

            Toast.makeText(this,E.getMessage(),Toast.LENGTH_SHORT);

        }
    }
}
