package com.example.group12project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class Rules extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rules);

        Button back = findViewById(R.id.back_button_rules);
        back.setOnClickListener(view -> goBack());
    }

    public void goBack(){
        Intent home = new Intent(this, MainActivity.class);
        startActivity(home);
    }
}