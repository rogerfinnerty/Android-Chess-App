package com.example.group12project;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class user_names extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_names);

        Button back_button = findViewById(R.id.back_button);
        back_button.setOnClickListener(view -> goBack());
        Button start_game = findViewById(R.id.start_game);
        start_game.setOnClickListener(view -> goToBoard());
    }


    public void goToBoard() {
        Intent intent_1 = new Intent(this, Chessboard.class);

        // add names from EditText elements
        EditText t1 = findViewById(R.id.player_one_name);
        EditText t2 = findViewById(R.id.player_two_name);
        intent_1.putExtra("WhiteName", String.valueOf(t1.getText()));
        intent_1.putExtra("BlackName", String.valueOf(t2.getText()));
        intent_1.putExtra("Bot", false);
        // go to chessboard
        startActivity(intent_1);
    }

    public void goBack(){
        Intent intent_2 = new Intent(this, MainActivity.class);
        startActivity(intent_2);
    }

}