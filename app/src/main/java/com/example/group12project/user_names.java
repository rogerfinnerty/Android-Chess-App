package com.example.group12project;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.group12project.databinding.ActivityUserNamesBinding;

public class user_names extends AppCompatActivity {

    public void goToBoard() {
        Intent intent_1 = new Intent(this, Chessboard.class);
        startActivity(intent_1);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_names);

        Button start_game = (Button) findViewById(R.id.start_game);
        start_game.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToBoard();
            }
        });
    }
}