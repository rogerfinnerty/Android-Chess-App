package com.example.group12project;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

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

import org.w3c.dom.Text;

public class Leaderboard extends AppCompatActivity {
    public String winner, loser;

    /**
     * Checks whether a username is already in the leaderboard
     * @param name
     * @param leaderboard
     * @return Boolean
     */
    public boolean check_name(String name, TableLayout leaderboard){
        // Iterate through the rows of the table
        for (int i = 0; i < leaderboard.getChildCount(); i++) {
            View row = leaderboard.getChildAt(i);
            // Check if the row is a TableRow
            if (row instanceof TableRow) {
                TableRow tableRow = (TableRow) row;
                // Get the first TextView in the row
                TextView nameTextView = (TextView) tableRow.getChildAt(0);
                // Get the text from the TextView
                String test_name = nameTextView.getText().toString();
                if (name == test_name) {
                    return true;
                }
                }
            }
        return false;
    }

    /**
     * Adds a win to a user already in the leaderboard
     * @param username
     * @param leaderboard
     */
    public void add_win(String username, TableLayout leaderboard){
        // Iterate through the rows of the table
        for (int i = 0; i < leaderboard.getChildCount(); i++) {
            View row = leaderboard.getChildAt(i);
            // Check if the row is a TableRow
            if (row instanceof TableRow) {
                TableRow tableRow = (TableRow) row;
                // Get the first TextView in the row
                TextView nameTextView = (TextView) tableRow.getChildAt(0);
                // Get the text from the TextView
                String test_name = nameTextView.getText().toString();
                // Compare the name to the test name
                if ( username == test_name ) {
                    TextView winsTextView = (TextView) tableRow.getChildAt(1);
                    int wins = Integer.parseInt(winsTextView.getText().toString());
                    wins++;
                    winsTextView.setText(Integer.toString(wins));
                }
            }
        }
    }

    /**
     * Updates the leaderboard with either a new entry or by adding a win to
     * an existing winner
     * @param username
     * @param winner Boolean
     * @param leaderboard
     */
    public void update_leaderboard(String username, boolean winner, TableLayout leaderboard){
        if ( !check_name(username,leaderboard) ){
            // Create a new TableRow
            TableRow newRow = new TableRow(this);
            // Create a new TextView to hold the user's name
            TextView nameView = new TextView(this);
            nameView.setText(username);
            // Add the TextView to the TableRow
            newRow.addView(nameView);
            // Add the new TableRow to the TableLayout
            TextView winsTextView = new TextView(this);
            newRow.addView(winsTextView);
            // Initialize win column based on outcome
            if (winner){
                winsTextView.setText("1");
            }
            else {
                winsTextView.setText("0");
            }
            // Add row to leaderboard
            leaderboard.addView(newRow);
        }
        else {
            add_win(username,leaderboard);
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

        // Home button
        Button home_btn = (Button) findViewById(R.id.home_button);
        home_btn.setOnClickListener(view -> goToHome());

        // Get player names
        Bundle extras = getIntent().getExtras();
        if(extras != null){
            winner = extras.getString("Winner");
            loser = extras.getString("Loser");
        }
        TableLayout leaderboard = findViewById(R.id.leaderboard);
        // Update leaderboard
        update_leaderboard(winner, true, leaderboard);
        update_leaderboard(loser,false,leaderboard);
    }

}