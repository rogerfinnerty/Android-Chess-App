package com.example.group12project;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class user_names_bot extends AppCompatActivity {

    public void goToBoard() {
        Intent intent_1 = new Intent(this, Chessboard.class);

        EditText t1 = (EditText) findViewById(R.id.player_one_name);
        intent_1.putExtra("WhiteName", String.valueOf(t1.getText()));
        intent_1.putExtra("Bot", true);
        startActivity(intent_1);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bot_names);

        Button start_game = (Button) findViewById(R.id.start_game);
        start_game.setOnClickListener(view -> goToBoard());
    }
}