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
import java.util.Vector;

import com.example.group12project.databinding.ActivityUserNamesBinding;

public class user_names extends AppCompatActivity {

    public Vector<String> goToBoard() {
        Vector<String> names = new Vector<String>();
        Intent intent_1 = new Intent(this, Chessboard.class);

        EditText t1 = (EditText) findViewById(R.id.player_one_name);
        EditText t2 = (EditText) findViewById(R.id.player_two_name);
        intent_1.putExtra("WhiteName", String.valueOf(t1.getText()));
        intent_1.putExtra("BlackName", String.valueOf(t2.getText()));
        intent_1.putExtra("Bot", false);
        startActivity(intent_1);

        // Save names entered by users
        names.add(String.valueOf(t1.getText()));
        names.add(String.valueOf(t2.getText()));

        return names;
    }

    public void goBack(){
        Intent intent_2 = new Intent(this, MainActivity.class);
        startActivity(intent_2);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_names);

        Button back_button = (Button) findViewById(R.id.back_button);
        back_button.setOnClickListener(view -> goBack());
        Button start_game = (Button) findViewById(R.id.start_game);
        start_game.setOnClickListener(view -> goToBoard());
    }
}