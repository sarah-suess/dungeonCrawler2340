package com.example.a2340project.Views;

import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;


import com.example.a2340project.Models.BasePowerUp;
import com.example.a2340project.Models.Enemy;
import com.example.a2340project.Models.EnemyFactory;
import com.example.a2340project.Models.GameEnemyFactory;
import com.example.a2340project.Models.GameWeaponFactory;
import com.example.a2340project.Models.GreenEnemy;
import com.example.a2340project.Models.LeaderBoard;
import com.example.a2340project.Models.Mushroom;

import com.example.a2340project.Models.MushroomDecorator;
import com.example.a2340project.Models.Player;
import com.example.a2340project.Models.Rainbow;
import com.example.a2340project.Models.RainbowDecorator;
import com.example.a2340project.Models.Sword;
import com.example.a2340project.Models.Treasure;
import com.example.a2340project.Models.TreasureDecorator;
import com.example.a2340project.Models.Weapon;
import com.example.a2340project.Models.WeaponFactory;
import com.example.a2340project.R;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.a2340project.ViewModels.MovementStrategy;
import com.example.a2340project.ViewModels.MoveRightStrategy;
import com.example.a2340project.ViewModels.MoveLeftStrategy;
import com.example.a2340project.ViewModels.MoveUpStrategy;
import com.example.a2340project.ViewModels.MoveDownStrategy;
import com.example.a2340project.ViewModels.PlayerPositionObserver;
import com.example.a2340project.ViewModels.PlayerPositionSubject;

import android.widget.Toast;
import android.os.Handler;

import java.util.ArrayList;
import java.util.List;



public class GameScreen extends AppCompatActivity implements PlayerPositionObserver {
    /* PRIVATE VARIABLES */
    private ImageView gameMap;
    private int screenWidth;
    private int screenHeight;
    private Bitmap bitmapChoice;
    private Bitmap playerBitmap;
    private Bitmap scaledPlayerBitmap;
    private Bitmap collisionMap;
    private int playerX;
    private int playerY;
    private int finalScore = 0;
    private int screen = 1;
    private TextView scoreTextView;
    private TextView timerTextView;

    private TextView powerupsTextView;
    private CountDownTimer countDownTimer;
    private boolean gameCompleted = false;
    private Player player = Player.getInstance();
    private MovementStrategy movementStrategy;

    private Enemy enemy1 = new GreenEnemy();
    private Enemy enemy2 = new GreenEnemy();

    private EnemyFactory enemyFactory = new GameEnemyFactory();

    private BasePowerUp powerUp;

    private List<Enemy> activeEnemies = new ArrayList<Enemy>();

    private static final long ENEMY_UPDATE_INTERVAL = 100;
    private Handler enemyHandler = new Handler();
    private Weapon weapon1 = new Sword();
    private WeaponFactory weaponFactory = new GameWeaponFactory();
    private List<Weapon> visibleWeapons = new ArrayList<Weapon>();
    private TextView healthTextView;
    private BasePowerUp powerup1;
    private MediaPlayer mp1;

    /* PAUSE BUTTON VARIABLES */
    private boolean gamePaused = false;
    private AlertDialog pauseDialog;
    private long remainingTime;

    protected void onCreate(Bundle savedInstanceState) {
        player.setScore(10);
        super.onCreate(savedInstanceState);
        initViews();
        setPlayerSpriteGameBitmap();

        // Play game screen music
        mp1 = MediaPlayer.create(GameScreen.this, R.raw.dungeon_music2_loud);
        mp1.start();

        // Initialize the countdown timer with a duration of 120 seconds
        initializeTimer(120000);

        // Initialize first game screen
        initFirstScreen();

        /* CREATE ENEMIES */
        enemy1 = enemyFactory.createEnemy("dragon", getResources());
        enemy2 = enemyFactory.createEnemy("ghost", getResources());
        activeEnemies.add(enemy1);
        activeEnemies.add(enemy2);

        /* CREATE WEAPONS */
        weapon1 = weaponFactory.createWeapon("sword", getResources());
        visibleWeapons.add(weapon1);

        /* CREATE POWERUPS */
        powerup1 = new Mushroom(getResources());
        powerUp = new Mushroom(getResources());
        powerup1.createSprite(getResources());

        /* UPDATING ENEMIES */
        startEnemyUpdate();

        /* PLAYER POSITION SUBJECT */
        PlayerPositionSubject positionSubject = new PlayerPositionSubject();

        // Set positionSubject for movement strategies
        movementStrategy = new MoveDownStrategy(playerX, playerY, positionSubject);

        // Register this GameScreen as an observer
        positionSubject.addObserver(this);

        /* PLAYER MOVEMENT USING STRATEGY PATTERN */
        gameMap.setOnKeyListener((v, keyCode, event) -> {
            if (event.getAction() == KeyEvent.ACTION_DOWN) {
                if (!gamePaused) {
                    switch (keyCode) {
                    case KeyEvent.KEYCODE_DPAD_LEFT:
                        movementStrategy = new MoveLeftStrategy(playerX, playerY, positionSubject);
                        break;
                    case KeyEvent.KEYCODE_DPAD_RIGHT:
                        movementStrategy = new MoveRightStrategy(playerX, playerY, positionSubject);
                        break;
                    case KeyEvent.KEYCODE_DPAD_UP:
                        movementStrategy = new MoveUpStrategy(playerX, playerY, positionSubject);
                        break;
                    case KeyEvent.KEYCODE_DPAD_DOWN:
                        movementStrategy = new MoveDownStrategy(playerX, playerY, positionSubject);
                        break;
                    case KeyEvent.KEYCODE_A:
                        weaponCollected(player, weapon1);
                        break;
                    case KeyEvent.KEYCODE_P:
                        pauseGame();
                        break;
                    default:
                        break;
                    }
                }
                // Use the selected strategy to move the player
                movementStrategy.move(collisionMap);
                // Update playerX and playerY based on the movement
                playerX = movementStrategy.getPlayerX();
                playerY = movementStrategy.getPlayerY();
                // Update weapon with player's movement
                moveWeapon(weapon1);
                // Update the game screen with the new player position
                updateBitmap();
                // Check if player is at goal on 1st screen
                if (screen == 1 && playerX >= 500 && playerY <= 10) {
                    updateScreen(); // call move to next screen function
                }
                // Check if player is at goal on 2nd screen
                if (screen == 2 && playerX >= 150 && playerX <= 250 && playerY <= 275
                        && playerY >= 225) {
                    updateScreen(); // call move to next screen function
                }
                // Check if player is at goal on 3rd screen
                if (screen == 3 && playerX >= 550 && playerY <= 100) {
                    Toast.makeText(this, "You have won!", Toast.LENGTH_LONG).show();
                    updateScreen(); // call move to next screen/finish game
                }
            }
            return false;
        });
        gameMap.setFocusable(true);
        gameMap.requestFocus();

        // Pause game button
        Button pauseGame = findViewById(R.id.pauseButton);
        pauseGame.setOnClickListener(v -> {
            pauseGame();
        });

    }

    /* INITIALIZE TIMER */
    private void initializeTimer(long initialTime) {
        countDownTimer = new CountDownTimer(initialTime, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                // Calculate the number of 10-second intervals
                long secondsLeft = millisUntilFinished / 1000;
                // Update the timer TextView with the remaining time
                if (secondsLeft % 10 == 0) {
                    // Deduct 1 point for each 10-second interval
                    player.setScore(player.getScore() - 1);
                }
                timerTextView.setText("Time: " + secondsLeft);
                updateScoreTextView();  // Update the score TextView
                remainingTime = secondsLeft * 1000;
            }

            @Override
            public void onFinish() {
                // Handle the timer finishing (e.g., end the game)
                timerTextView.setText("Time's up!");
                finishGame();
            }
        }.start();
    }

    /* PAUSE GAME */
    private void pauseGame() {
        gamePaused = true;
        mp1.pause();  // Pause the background music
        countDownTimer.cancel(); // Pause timer

        /* PAUSE DIALOG */
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Game Paused");
        builder.setMessage("Press 'Resume' to continue playing");

        /* RESUME GAME */
        builder.setPositiveButton("Resume", (dialog, which) -> {
            dialog.dismiss();
            gamePaused = false;
            mp1.start();  // Resume the background music
            initializeTimer(remainingTime); // resume timer
        });

        /* QUIT GAME */
        builder.setNegativeButton("Quit", (dialog, which) -> {
            dialog.dismiss();
            finishGame();
        });

        pauseDialog = builder.create();
        pauseDialog.show();
    }

    /* FINISH GAME
     * Add final score to leaderboard and continue to end screen */
    public void finishGame() {
        mp1.stop();
        countDownTimer.cancel();
        // Dismiss the pause dialog if it's showing
        if (pauseDialog != null && pauseDialog.isShowing()) {
            pauseDialog.dismiss();
        }
        // Update the leaderboard with the final score
        LeaderBoard.getInstance().setRecentEntry(player.getPlayerName(), player.getScore());
        // Transition to the EndingScreen activity
        Intent endingIntent = new Intent(this, EndingScreen.class);
        startActivity(endingIntent);
        finish();
    }

    /* UPDATE SCORE TEXT VIEW */
    private void updateScoreTextView() {
        scoreTextView.setText("Score: " + player.getScore());
    }

    /* WHEN PLAYER POSITION CHANGES */
    @Override
    public void onPlayerPositionChanged(int x, int y) {
        // Notify the rendering part (Observer) that the player's position has changed
        updateRender(x, y);
        for (Enemy enemy : activeEnemies) {
            if (hasCollidedWithEnemy(enemy)) {
                if (weapon1.isCollected() && enemy.isAlive() && weapon1.isVisible()) {
                    attack(enemy);
                } else if (enemy.isAlive()) {
                    player.setCollided(true);
                    player.notifyCollisionObservers(enemy);
                    player.setHealth(player.getHealth() - player.getHealthLoss());
                    healthTextView.setText("" + player.getHealth());
                    Log.d("collision", "collision");
                    if (player.getHealth() <= 0) {
                        finishGame();
                    }
                }
            }
        }

        if (hasCollidedWithPowerUp(powerup1) && !powerup1.isCollected()) {
            powerup1.setCollected(true);
            // Make mushroom larger
            if (powerup1 instanceof Mushroom) {
                powerUp = new MushroomDecorator(powerUp);
                powerUp.applyPowerUp(player);
                scaledPlayerBitmap = Bitmap.createScaledBitmap(playerBitmap, player.getSize(),
                        player.getSize(), false);
                updateBitmap();
            } else if (powerup1 instanceof Treasure) { // Increase score by 5
                powerUp = new TreasureDecorator(powerUp);
                powerUp.applyPowerUp(player);
                updateScoreTextView();
            } else if (powerup1 instanceof Rainbow) { // Make health 100
                powerUp = new RainbowDecorator(powerUp);
                powerUp.applyPowerUp(player);
                healthTextView.setText("" + player.getHealth());
            }
        }
    }

    /* UPDATE SCREEN */
    public void updateScreen() {
        switch (screen) {
        case 1:
            // SPRINT 2 - Implement Game Maps as PNGs
            // SPRINT 3 - Convert PNGs to BitMaps
            Bitmap dungeonMapTwoBitmap = BitmapFactory.decodeResource(getResources(),
                    R.drawable.dungeonmaptwo);
            bitmapChoice = dungeonMapTwoBitmap;
            collisionMap = BitmapFactory.decodeResource(getResources(),
                    R.drawable.dungeon_map_two);
            gameMap.setImageBitmap(dungeonMapTwoBitmap);
            playerX = 50;
            playerY = 600;
            screen = 2;

            /* CREATE ENEMIES */
            enemy1 = enemyFactory.createEnemy("green", getResources());
            enemy2 = enemyFactory.createEnemy("grey", getResources());
            activeEnemies.add(enemy1);
            activeEnemies.add(enemy2);

            /* CREATE WEAPONS */
            weapon1 = weaponFactory.createWeapon("axe", getResources());
            visibleWeapons.add(weapon1);

            /* CREATE POWERUPS */
            powerup1 = new Rainbow(getResources());
            powerup1.createSprite(getResources());

            /* UPDATING ENEMIES */
            startEnemyUpdate();
            break;

        case 2:
            // SPRINT 2 - Implement Game Maps as PNGs
            // SPRINT 3 - Convert PNGs to BitMaps
            Bitmap dungeonMapThreeBitmap = BitmapFactory.decodeResource(getResources(),
                    R.drawable.dungeonmapthree);
            bitmapChoice = dungeonMapThreeBitmap;
            collisionMap = BitmapFactory.decodeResource(getResources(),
                    R.drawable.dungeon_map_three);
            gameMap.setImageBitmap(dungeonMapThreeBitmap);
            playerX = 50;
            playerY = 600;

            /* CREATE ENEMIES */
            enemy1 = enemyFactory.createEnemy("dragon", getResources());
            enemy2 = enemyFactory.createEnemy("grey", getResources());
            activeEnemies.add(enemy1);
            activeEnemies.add(enemy2);

            /* CREATE POWERUPS */
            powerup1 = new Treasure(getResources());
            powerup1.createSprite(getResources());

            /* CREATE WEAPONS */
            weapon1 = weaponFactory.createWeapon("spear", getResources());
            visibleWeapons.add(weapon1);

            /* UPDATING ENEMIES */
            startEnemyUpdate();

            screen = 3;
            break;

        case 3:
            finishGame();
            break;

        default:
            finishGame();
        }
    }

    /* ATTACK ENEMY WITH WEAPON */
    public void attack(Enemy enemy) {
        player.setScore(player.getScore() + 50);
        updateScoreTextView();
        enemy.eraseSprite();
        weapon1.eraseWeapon();
        Log.d("enemy", "attacked");
    }

    /* WEAPON COLLECTED */
    public void weaponCollected(Player player, Weapon weapon) {
        if (playerY < weapon1.getY() + 16 && playerY + 16 > weapon1.getY()
                && playerX < weapon1.getX() + 16 && playerX + 16 > weapon1.getX()) {
            weapon.setCollected(true);
            Log.d("weapon", "collected");
        }
    }

    /* MOVE WEAPON */
    public void moveWeapon(Weapon weapon) {
        if (weapon.isCollected()) {
            weapon.setX(playerX);
            weapon.setY(playerY);
        }
    }

    /* PLAYER COLLIDED WITH ENEMY */
    public boolean hasCollidedWithEnemy(Enemy enemy) {
        // Implement collision detection logic here
        // Return true if a collision occurred, otherwise return false
        return playerY < enemy.getY() + 20 && playerY + 20 > enemy.getY()
                && playerX < enemy.getX() + 20 && playerX + 20 > enemy.getX();
    }

    /* PLAYER COLLIDED WITH POWERUP */
    public boolean hasCollidedWithPowerUp(BasePowerUp powerUp) {
        int playerSize = player.getSize();
        int powerUpSize = powerUp.getSize();

        return playerY < powerUp.getY() + 20
                && playerY + 20 > powerUp.getY()
                && playerX < powerUp.getX() + 20
                && playerX + 20 > powerUp.getX();
    }

    /* UPDATE PLAYERS SPRITE WITH MOVEMENT */
    private void updateRender(int x, int y) {
        // Update the player's image position on the game map (ImageView)
        playerX = x;
        playerY = y;

        // Update the game map with the new player position
        updateBitmap();
    }

    /* UPDATE BITMAP WITH MOVEMENT AND ACTIONS */
    private void updateBitmap() {
        Bitmap mutableBitmap = bitmapChoice.copy(Bitmap.Config.ARGB_8888, true);
        Canvas canvas = new Canvas(mutableBitmap);
        Paint paint = new Paint();
        canvas.drawBitmap(bitmapChoice, 0, 0, null);
        canvas.drawBitmap(scaledPlayerBitmap, playerX, playerY, paint);
        enemy1.drawEnemy(canvas, paint);
        enemy2.drawEnemy(canvas, paint);
        weapon1.drawWeapon(canvas, paint);
        gameMap.setImageBitmap(mutableBitmap);
        if (!powerup1.isCollected()) {
            powerup1.drawPowerUp(canvas, paint);
        }
        powerupsTextView.setText(powerUp.description());

    }

    /* ENEMY MOVEMENT IMPLEMENTATION */
    private void startEnemyUpdate() {
        if (!gamePaused) {
            enemyHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    updateEnemies();
                    updateBitmap();
                    enemyHandler.postDelayed(this, ENEMY_UPDATE_INTERVAL);
                }
            }, ENEMY_UPDATE_INTERVAL);
        }
    }

    /* UPDATE ENEMIES */
    private void updateEnemies() {
        if (!gamePaused) {
            enemy1.move(collisionMap);
            enemy2.move(collisionMap);
        }
    }

    /* ADD PLAYER SPRITE TO BITMAP */
    private void setPlayerSpriteGameBitmap() {
        /* PLAYER IMAGE SWITCH CASES */
        switch (player.getSelectedImage()) {
        case 1:
            player.getImage().setImageResource(R.drawable.image1);
            playerBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.image1);
            scaledPlayerBitmap = Bitmap.createScaledBitmap(playerBitmap, player.getSize(),
                    player.getSize(), false);
            break;
        case 2:
            player.getImage().setImageResource(R.drawable.image2);
            playerBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.image2);
            scaledPlayerBitmap = Bitmap.createScaledBitmap(playerBitmap, player.getSize(),
                    player.getSize(), false);
            break;
        case 3:
            player.getImage().setImageResource(R.drawable.image3);
            playerBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.image3);
            scaledPlayerBitmap = Bitmap.createScaledBitmap(playerBitmap, player.getSize(),
                    player.getSize(), false);
            break;
        default:
            player.getImage().setImageResource(R.drawable.image1);
        }
    }

    /* INITIALIZE VIEWS */
    private void initViews() {
        player.setScore(10);
        setContentView(R.layout.game_screen);
        Bundle extras = getIntent().getExtras();
        TextView nameTextView = (TextView) findViewById(R.id.name);
        nameTextView.setText(player.getPlayerName());
        TextView difficultyTextView = (TextView) findViewById(R.id.difficulty);
        difficultyTextView.setText(player.getDifficulty());
        healthTextView = (TextView) findViewById(R.id.health);
        healthTextView.setText("" + player.getHealth());
        powerupsTextView = (TextView) findViewById(R.id.powerups);
        player.setImage(findViewById(R.id.playerImage));
        gameMap = findViewById(R.id.gamemap);
        //get screenwidth
        View parentView = (View) gameMap.getParent();
        screenWidth = parentView.getWidth();
        screenHeight = parentView.getHeight();
        scoreTextView = findViewById(R.id.scoreTextView);
        timerTextView = findViewById(R.id.timerTextView);
    }

    /* INITIALIZE FIRST SCREEN */
    private void initFirstScreen() {
        // Initialize player position, current screen, and first bitmap
        Bitmap dungeonMapOneBitmap = BitmapFactory.decodeResource(getResources(),
                R.drawable.dungeonmapone);
        bitmapChoice = dungeonMapOneBitmap;
        //bitmapChoice = Bitmap.createScaledBitmap(dungeonMapOneBitmap, 256, 256, false);
        collisionMap = BitmapFactory.decodeResource(getResources(), R.drawable.dungeon_map1);
        gameMap.setImageBitmap(dungeonMapOneBitmap);
        playerX = 50;
        playerY = 600;
        screen = 1;
    }
}