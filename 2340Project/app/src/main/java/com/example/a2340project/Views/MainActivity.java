package com.example.a2340project.Views;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.a2340project.R;
import com.example.a2340project.ViewModels.InitialConfigurationClass;

/* MAIN ACTIVITY CLASS */
public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome_screen);

        // Buttons
        Button startBtn = findViewById(R.id.startButton);
        Button exitBtn = findViewById(R.id.exitButton);
        Button howToPlayButton = findViewById(R.id.howToPlayButton);

        // Button handlers
        startBtn.setOnClickListener(v -> {
            Intent game = new Intent(MainActivity.this, InitialConfigurationClass.class);
            startActivity(game);
            finish();
        });

        exitBtn.setOnClickListener(v -> {
            MainActivity.this.finish();

            System.exit(0); // On below line we are exiting our activity
        });

        howToPlayButton.setOnClickListener(v -> showHowToPlayPopup());
    }

    /* POPUP MESSAGE */
    private void showHowToPlayPopup() {
        // Create AlertDialog builder
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        // Get layout inflater
        LayoutInflater inflater = getLayoutInflater();

        // Inflate and set the layout for the dialog
        View popupView = inflater.inflate(R.layout.how_to_play_popup, null);
        builder.setView(popupView);

        // Create and show the AlertDialog
        AlertDialog dialog = builder.create();
        dialog.show();

        // Set up the Close button
        Button closeButton = popupView.findViewById(R.id.closeButton);
        closeButton.setOnClickListener(v -> {
            // Dismiss the dialog when the Close button is clicked
            dialog.dismiss();
        });
    }
}