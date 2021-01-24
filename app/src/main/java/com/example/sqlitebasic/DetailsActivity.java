package com.example.sqlitebasic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DetailsActivity extends AppCompatActivity {

    private TextView indexText,nameText,codeText,typeText;
    private Button updateButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        setTitle("Details");
        final Model detailsModel= (Model) getIntent().getSerializableExtra("details");
        setContentView(R.layout.activity_details);
        indexText= findViewById(R.id.detailsIndexId);
        nameText= findViewById(R.id.detailsNameId);
        codeText= findViewById(R.id.detailsCodeId);
        typeText= findViewById(R.id.detailsTypeId);
        updateButton= findViewById(R.id.updateButtonId);

        indexText.setText("Index ID: "+detailsModel.getId());
        nameText.setText("Name: "+detailsModel.getName());

        typeText.setText("Type: "+detailsModel.getType());
        codeText.setText("Code: "+detailsModel.getCode());
        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(DetailsActivity.this,UpdateActivity.class);
                intent.putExtra("update",detailsModel);
                startActivity(intent);
            }
        });
    }
}