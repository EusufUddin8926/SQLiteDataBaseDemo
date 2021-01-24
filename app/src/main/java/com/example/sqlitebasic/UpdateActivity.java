package com.example.sqlitebasic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class UpdateActivity extends AppCompatActivity {

    private TextView indexTextView;
    private EditText nameEditText,codeEditText,typeEditText;
    private Button updateButton;

    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        final Model updateModel= (Model) getIntent().getSerializableExtra("update");
        databaseHelper =  new DatabaseHelper(UpdateActivity.this);
        SQLiteDatabase sqLiteDatabase= databaseHelper.getWritableDatabase();

        indexTextView= findViewById(R.id.updateIndexId);
        nameEditText= findViewById(R.id.updatePlayerNameId);
        codeEditText= findViewById(R.id.updateCodeId);
        typeEditText= findViewById(R.id.updatePlayerTypeId);
        updateButton= findViewById(R.id.viewUpdateButtonId);

        indexTextView.setText(updateModel.getId());
        nameEditText.setText(updateModel.getName());
        typeEditText.setText(updateModel.getType());
        codeEditText.setText(updateModel.getCode());

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String id= updateModel.getId();
                String name= nameEditText.getText().toString();
                String type= typeEditText.getText().toString();
                String code= codeEditText.getText().toString();
                updateData(id,name,type,code);

            }
        });

    }

    private void updateData(String id, String name, String type, String code) {

        long isUpdate= databaseHelper.update(id,name,type,code);
        if(isUpdate==-1){
            Toast.makeText(this, "Update Fail", Toast.LENGTH_SHORT).show();
        }
        else if(isUpdate==0){
            Toast.makeText(this, "Update Fail", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(this, "Update Success", Toast.LENGTH_SHORT).show();
            Intent intent= new Intent(UpdateActivity.this,MainActivity.class);
            startActivity(intent);
            finish();
        }
    }
}