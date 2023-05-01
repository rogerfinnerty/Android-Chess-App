// Test comment

package com.example.group12project;

import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.group12project.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

// this stuff is for the makeToast
import android.os.Handler;
import android.widget.Toast;
import android.content.Intent;
public class MainActivity extends AppCompatActivity {

    // Function to switch from main activity to usernames activity
    public void goToUserNames() {
        Intent intent_1 = new Intent(this, user_names.class);
        startActivity(intent_1);
    }

    public void loadGame(){
        Intent intent = new Intent(this, Chessboard.class);
        startActivity(intent);
    }

    public void goToLeaderboard(){
        Intent intent = new Intent(this, Leaderboard.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);    // Display main menu

        Button new_game = (Button) findViewById(R.id.new_game);
        Button load_game = (Button) findViewById(R.id.load_game);
        Button leaderboard = (Button) findViewById(R.id.leaderboard);

        // Buttons switch activities
        new_game.setOnClickListener(view -> goToUserNames());
        load_game.setOnClickListener(view -> loadGame());
        leaderboard.setOnClickListener(view -> goToLeaderboard());
    }
    private void makeToast() {
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(MainActivity.this, "Toast every 5s", Toast.LENGTH_SHORT).show();
                handler.postDelayed(this, 5000);
            }
        });
    }

}