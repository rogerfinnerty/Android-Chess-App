// Test comment

package com.example.group12project;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);    // Display main menu

        Button new_game = findViewById(R.id.new_game);
        Button leaderboard = findViewById(R.id.leaderboard);
        Button new_vs_bot = findViewById(R.id.Bot);
        Button rules_btn = findViewById(R.id.rules);

        new_game.setOnClickListener(view -> goToUserNames());
        new_vs_bot.setOnClickListener(view -> newBot());
        leaderboard.setOnClickListener(view -> goToLeaderboard());
        rules_btn.setOnClickListener(view -> goToRules());
    }

    // Function to switch from main activity to usernames activity
    public void goToUserNames() {
        Intent intent_1 = new Intent(this, user_names.class);
        startActivity(intent_1);
    }

    public void newBot(){
        Intent intent = new Intent(this, user_names_bot.class);
        startActivity(intent);
    }

    public void goToLeaderboard(){
        Intent intent = new Intent(this, Leaderboard.class);
        startActivity(intent);
    }

    protected void goToRules(){
        Intent i = new Intent(this, Rules.class);
        startActivity(i);
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
