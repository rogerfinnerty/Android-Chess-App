package com.example.group12project;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.group12project.databinding.ActivityLeaderboardBinding;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Button;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.w3c.dom.Text;

public class Leaderboard extends AppCompatActivity {

    // Create
    SharedPreferences sharedPref;
    public String winner, loser;

    // Method to save leaderboard data to a preferences file
    public void saveLeaderboardData(SharedPreferences sharedPref, String player, int score) {
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt(player, score);
        editor.apply();
    }

    // Method to retrieve leaderboard information from preferences file
    public Map<String, ?> getLeaderboardData(SharedPreferences sharedPref) {
        return sharedPref.getAll();
    }

    /**
     * Update leaderboard
     * @param sharedPref sharedPreferences object that stores leaderboard information
     * @param name player to add
     * @param is_winner boolean
     */
    public void update_leaderboard (SharedPreferences sharedPref, SharedPreferences.Editor editor, String name, boolean is_winner){
        Map<String, ?> leaderboardData = getLeaderboardData(sharedPref);
        for ( Map.Entry<String, ?> entry : leaderboardData.entrySet() ) {
            String player = entry.getKey();
            if (Objects.equals(player, name) && is_winner) {
                // Add one to an existing player's win total
                int wins = (int) entry.getValue();
                int newValue = (++wins);
                System.out.println(newValue);
                editor.putInt(player, newValue);
                editor.apply();
                return;
            }
        }
        // Player is not in leaderboard, update accordingly
        if (is_winner){
            saveLeaderboardData(sharedPref, name, 1);
        } else {
            saveLeaderboardData(sharedPref, name, 0);
        }
    }

    // Create a leaderboard table from Map of <name, wins> pairs
    public void make_table( TableLayout leaderboard, Map<String,?> leaderboardData ){
        Map<String, ?> leaderboardData1 = getLeaderboardData(sharedPref);
        List<Row> sorted = new ArrayList<>();
        for (Map.Entry<String, ?> entry : leaderboardData1.entrySet()) {
            String player = entry.getKey();
            if(Objects.equals(player, "") || player == null){
                continue;
            }
            int score = (int) entry.getValue();
            sorted.add(new Row(entry.getKey(), (Integer) entry.getValue()));
        }

        // sort leaderboard, then display
        Collections.sort(sorted);
        Collections.reverse(sorted);
        for(Row r : sorted){
            // create row object
            TableRow row = new TableRow(this);
            // Add name to row
            TextView player_name = new TextView(this);
            player_name.setText(r.getName());
            row.addView(player_name);
            // Add wins to row
            TextView wins = new TextView(this);
            wins.setText(String.valueOf(r.getWins()));
            row.addView(wins);
            // Add row to leaderboard
            leaderboard.addView(row);
        }
    }

    // Function to return to home page
    public void goToHome(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderboard);
        // Initialize sharedPreferences object
        sharedPref  = getSharedPreferences("Leaderboard", Context.MODE_MULTI_PROCESS);
        SharedPreferences.Editor editor = sharedPref.edit();
        // Home button
        Button home_btn = (Button) findViewById(R.id.home_button);
        home_btn.setOnClickListener(view -> goToHome());

        // Get player names
        Bundle extras = getIntent().getExtras();
        if(extras != null){
            winner = extras.getString("Winner");
            loser = extras.getString("Loser");

            // Update leaderboard
            update_leaderboard(sharedPref, editor,  winner, true);
            update_leaderboard(sharedPref, editor, loser,false);
        }
        editor.apply();

        // Updated leaderboard data
        Map<String, ?> leaderboardData = getLeaderboardData(sharedPref);
        // Create table from leaderboard data
        TableLayout leaderBoard = findViewById(R.id.leaderboard);
        make_table(leaderBoard,leaderboardData);
    }



}