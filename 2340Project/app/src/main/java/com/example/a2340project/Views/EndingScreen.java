package com.example.a2340project.Views;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.Date;

import androidx.appcompat.app.AppCompatActivity;

import com.example.a2340project.Models.LeaderBoard;
import com.example.a2340project.Models.Player;
import com.example.a2340project.R;

public class EndingScreen extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ending_screen);
        MediaPlayer mp1 = MediaPlayer.create(EndingScreen.this, R.raw.dungeon_music_loud);
        mp1.start();

        /* LEADER BOARD LIST GETTER */
        List<LeaderBoard.LeaderboardEntry> entries = LeaderBoard.getInstance()
                .getleaderboardEntries();
        LeaderBoard.LeaderboardEntry recentEntry = LeaderBoard.getInstance().getRecentEntry();

        TextView endingTitleTextView = findViewById(R.id.endingTitle);
        Player currPlayer = Player.getInstance();
        if (currPlayer.getHealth() == 0 || currPlayer.getScore() == 0) {
            endingTitleTextView.setText("You Lost!");
        } else {
            endingTitleTextView.setText("You Won!");
        }

        /* DISPLAY LAST SCORE */
        TextView playerNameTextView = findViewById(R.id.recentPlayerName);
        playerNameTextView.setText(recentEntry.getPlayername());
        TextView scoreTextView = findViewById(R.id.recentPlayerScore);
        scoreTextView.setText("Score: " + String.valueOf(recentEntry.getScore()));
        TextView dateTimePlayedTextView = findViewById(R.id.recentDateandTimePlayed);
        Date recentDate = recentEntry.getDateTime();
        dateTimePlayedTextView.setText("Date and Time: " + String.valueOf(recentDate));

        /* LEADERBOARD DISPLAY PT.I */
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);


        /* LEADERBOARD DISPLAY PT.II */
        LeaderboardAdapter adapter = new LeaderboardAdapter(entries);
        recyclerView.setAdapter(adapter);

        /* EXIT BUTTON */
        Button exitBtn = findViewById(R.id.exitGameButton);
        exitBtn.setOnClickListener(v -> {
            mp1.stop(); // stop ending screen music
            Intent exit = new Intent(Intent.ACTION_MAIN);
            exit.addCategory(Intent.CATEGORY_HOME);
            exit.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(exit);
        });

        /* RESTART BUTTON */
        Button restartBtn = findViewById(R.id.restart);
        restartBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mp1.stop(); // stop ending screen music
                Intent i = new Intent(EndingScreen.this, MainActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                i.addFlags(i.FLAG_ACTIVITY_NEW_TASK);
                finish();
                startActivity(i);
            }
        });
    }
}