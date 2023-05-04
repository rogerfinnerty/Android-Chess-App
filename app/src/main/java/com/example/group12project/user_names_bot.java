package com.example.group12project;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;

import androidx.appcompat.app.AppCompatActivity;

public class user_names_bot extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bot_names);

        Button start_game = findViewById(R.id.start_game);
        start_game.setOnClickListener(view -> goToBoard());
        Button back_btn = findViewById(R.id.back_button_bot);
        back_btn.setOnClickListener(view -> goBack());
    }
    public void goToBoard() {
        Intent intent_1 = new Intent(this, Chessboard.class);

        EditText t1 = findViewById(R.id.player_one_name);
        @SuppressLint("UseSwitchCompatOrMaterialCode") Switch s1 = findViewById(R.id.aggr_switch);
        Boolean random = !(s1.isChecked());
        intent_1.putExtra("BlackName", String.valueOf(t1.getText()));
        intent_1.putExtra("Bot", true);
        intent_1.putExtra("Random", random);
        startActivity(intent_1);
    }

    public void goBack(){
        Intent intent_2 = new Intent(this, MainActivity.class);
        startActivity(intent_2);
    }

}