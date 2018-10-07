package com.example.yohan.insertdataapp;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText text1,text2,text3,text4;
    Button button,button2,button3;
    dbclass db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = new dbclass(this);


        text1 = findViewById(R.id.editText);
        text2 = findViewById(R.id.editText2);
        text3 = findViewById(R.id.editText4);
        text4 = findViewById(R.id.editText5);
        button = findViewById(R.id.button);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        addData();
        viewAllData();
        UpdateData();


    }

    public void addData(){
        button.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                       boolean inserted = db.insertdata(text1.getText().toString(),
                        text2.getText().toString(),
                        text3.getText().toString(),
                        text4.getText().toString());

                       if(inserted)
                           Toast.makeText(MainActivity.this, "Data Inserted ",Toast.LENGTH_SHORT).show();
                       else
                           Toast.makeText(MainActivity.this, "Data not Inserted ",Toast.LENGTH_SHORT).show();

                       }

                    }

        );
    }

    public void viewAllData(){
        button2.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                     Cursor res =   db.getAllData();
                     if(res.getCount() == 0){

                         ShowMesseage("Error","Nothing Found");
                         return;

                     }

                     StringBuffer buffer = new StringBuffer();
                     while (res.moveToNext()){

                         buffer.append("STUDENTID : "+res.getString(0)+"\n");
                         buffer.append("Name : "+ res.getString(1)+"\n" );
                         buffer.append("Marks : "+ res.getString(2)+"\n" );
                         buffer.append("Grade : "+ res.getString(3)+"\n\n" );

                     }

                     //show all data
                        ShowMesseage("data",buffer.toString());
                    }
                }
        );
    }

    public void ShowMesseage(String Tittle, String messege){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(Tittle);
        builder.setMessage(messege);
        builder.show();

    }

    public void UpdateData(){
        button3.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                     boolean updated =  db.updateData(text1.getText().toString(),
                             text2.getText().toString(),
                             text3.getText().toString(),
                             text4.getText().toString());

                     if(updated == true)
                         Toast.makeText(MainActivity.this, "Data Updated ",Toast.LENGTH_SHORT).show();
                     else
                         Toast.makeText(MainActivity.this, "Data not Updated ",Toast.LENGTH_SHORT).show();

                    }
                }
        );
    }

}
