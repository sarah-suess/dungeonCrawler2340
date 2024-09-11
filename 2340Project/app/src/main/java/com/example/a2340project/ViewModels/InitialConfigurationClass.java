package com.example.a2340project.ViewModels;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;

import com.example.a2340project.Models.Player;
import com.example.a2340project.R;
import com.example.a2340project.Views.GameScreen;

public class InitialConfigurationClass extends AppCompatActivity {

    private ImageButton spriteImage;
    private int selectedImageResourceId;
    private Player player = Player.getInstance();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.initial_configuration_screen);
        MediaPlayer mp1 = MediaPlayer.create(InitialConfigurationClass.this,
                R.raw.dungeon_music3_loud);
        mp1.start();

        //Add Player Name
        EditText playerName = (EditText) findViewById(R.id.playerNameInput);


        //Select Player Difficulty
        RadioGroup difficulties = findViewById(R.id.difficulties);
        RadioButton easy = findViewById(R.id.easy);
        RadioButton medium = findViewById(R.id.medium);
        RadioButton hard = findViewById(R.id.hard);


        //Select Player Sprite

        RadioButton imageButton1 = findViewById(R.id.imagemessage1);
        RadioButton imageButton2 = findViewById(R.id.imagemessage2);
        RadioButton imageButton3 = findViewById(R.id.imagemessage3);

        ImageButton spriteImage1 = findViewById(R.id.image1);
        ImageButton spriteImage2 = findViewById(R.id.image2);
        ImageButton spriteImage3 = findViewById(R.id.image3);

        RadioGroup radioGroup = findViewById(R.id.radioGroup);

        radioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId == R.id.imagemessage1) {
                selectedImageResourceId = R.drawable.image1;
            } else if (checkedId == R.id.imagemessage2) {
                selectedImageResourceId = R.drawable.image2;
            } else if (checkedId == R.id.imagemessage3) {
                selectedImageResourceId = R.drawable.image3;
            }
        });


        // Allows the player to continue to the game screen after they input a valid name,
        // choose a player model, and select a difficulty.
        Button continueBtn = findViewById(R.id.continueToGameScreen);
        continueBtn.setOnClickListener(v -> {
            Bundle extras = new Bundle();
            if (easy.isChecked()) {
                player.setDifficulty("easy");
            } else if (medium.isChecked()) {
                player.setDifficulty("medium");
            } else if (hard.isChecked()) {
                player.setDifficulty("hard");
            }
            if (imageButton1.isChecked()) {
                player.setSelectedImage(1);
                spriteImage = spriteImage1;
            } else if (imageButton2.isChecked()) {
                player.setSelectedImage(2);
                spriteImage = spriteImage2;
            } else if (imageButton3.isChecked()) {
                player.setSelectedImage(3);
                spriteImage = spriteImage3;
            }

            if (!TextUtils.isEmpty(playerName.getText().toString().trim())) {
                if (easy.isChecked() || medium.isChecked() || hard.isChecked()) {
                    if (imageButton1.isChecked() || imageButton2.isChecked()
                            || imageButton3.isChecked()) {
                        extras.putInt("selectedImageResourceId", selectedImageResourceId);
                        player.setPlayerName(playerName.getText().toString());
                        Intent game = new Intent(InitialConfigurationClass.this, GameScreen.class);
                        game.putExtras(extras);
                        startActivity(game);
                        Intent gameMap = new Intent(InitialConfigurationClass.this,
                                GameScreen.class);
                        gameMap.putExtras(extras);
                        startActivity(gameMap);
                        finish();

                    } else {
                        Toast.makeText(InitialConfigurationClass.this,
                                "Please select a character model.", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(InitialConfigurationClass.this,
                            "Please select a difficulty.", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(InitialConfigurationClass.this,
                        "Please enter a valid name.", Toast.LENGTH_SHORT).show();
            }
            mp1.stop();
        });

    }
}
