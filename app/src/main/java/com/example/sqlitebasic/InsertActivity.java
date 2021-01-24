package com.example.sqlitebasic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class InsertActivity extends AppCompatActivity {

    private EditText nameEdittext,typeEdittext,codeEdittext;
    private Button insertButton,viewButton;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);

        databaseHelper = new DatabaseHelper(InsertActivity.this);
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();

        nameEdittext = findViewById(R.id.insertNameId);
        typeEdittext = findViewById(R.id.insertTypeId);
        codeEdittext = findViewById(R.id.insertCodeId);
        insertButton = findViewById(R.id.insertButtonId);
        viewButton = findViewById(R.id.listButtonId);

        insertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = nameEdittext.getText().toString();
                String type = typeEdittext.getText().toString();
                String code = codeEdittext.getText().toString();

                insertData(name,type,code);
            }
        });

        viewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(InsertActivity.this,MainActivity.class));
            }
        });
    }

    private void insertData(String name, String type, String code) {

        long value=  databaseHelper.insert(name,type,code);

        if(value==-1){
            Toast.makeText(this, "Insert Failed", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(this, "Insert Successfully", Toast.LENGTH_SHORT).show();
            nameEdittext.getText().clear();
            typeEdittext.getText().clear();
            codeEdittext.getText().clear();
        }
    }
}