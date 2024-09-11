package com.example.a2340project.Models;
import android.widget.ImageView;

import com.example.a2340project.ViewModels.EnemyCollisionObserver;
import com.example.a2340project.ViewModels.PlayerPositionObserver;

import java.util.ArrayList;
import java.util.List;


/// Class that implements player singleton

public class Player {

    /* PRIVATE VARIABLES */
    // Private static instance variable to hold the single instance
    private static volatile Player instance;
    private String name;
    private ImageView image;
    private int health;
    private int healthLoss;
    private int selectedImage;
    private Weapon weapon;
    private int score;
    private String difficulty;
    private int x;  // Add x coordinate
    private int y;
    private List<EnemyCollisionObserver> collisionObservers = new ArrayList<>();
    private List<PlayerPositionObserver> positionObservers = new ArrayList<>();

    private int size = 50;
    private boolean collided = false;


    /* CONSTRUCTOR
    * Private to prevent external instantiation */
    private Player() {
        // Initialize player
        this.score = 10;
    }

    /* PLAYER SINGLETON METHOD
    * Public static method to get the single instance of Player */
    public static Player getInstance() {
        if (instance == null) {
            synchronized (Player.class) {
                if (instance == null) {
                    instance = new Player();
                }
            }
        }
        return instance;
    }

    //get player name
    public String getPlayerName() {
        return name;
    }
    public Weapon getWeapon() {
        return weapon; }

    //set player name
    public void setPlayerName(String name) {
        if (name == null || name.isEmpty() || name.trim().isEmpty()) {
            throw new NullPointerException();
        } else {
            this.name = name;
        }
    }

    //get player image
    public ImageView getImage() {
        return image;
    }
    //set player image
    public void setImage(ImageView image) {
        this.image = image; }

    // set player health
    public void setHealth(int health) {
        this.health = health; }

    // get player health
    public int getHealthLoss() {
        return this.healthLoss;
    }

    //get selected image
    public int getSelectedImage() {
        return selectedImage; }

    //set selected image
    public void setSelectedImage(int selectedImage) {
        this.selectedImage = selectedImage;
    }

    //get score
    public int getScore() {
        return score; }

    //set score
    public void setScore(int score) {
        if (score < 0) {
            this.score = 0;
        } else {
            this.score = score;
        }
    }

    //get health
    public int getHealth() {
        return health;
    }

    //get difficulty
    public String getDifficulty() {
        return difficulty;
    }

    //set difficulty
    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
        switch (difficulty) {
        case "easy":
            this.health = 100;
            this.healthLoss = 2;
            break;
        case "medium":
            this.health = 75;
            this.healthLoss = 5;
            break;
        case "hard":
            this.health = 50;
            this.healthLoss = 10;
            break;
        default:
            this.health = 100;
        }
    }
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }


    // get player size
    public int getSize() {
        return size;
    }

    // set player size
    public void setSize(int size) {
        this.size = size;
    }

    // collision observers
    public void registerCollisionObserver(EnemyCollisionObserver observer) {
        collisionObservers.add(observer);
    }

    public void unregisterCollisionObserver(EnemyCollisionObserver observer) {
        collisionObservers.remove(observer);
    }
    public void notifyCollisionObservers(Enemy enemy) {
        for (EnemyCollisionObserver observer : collisionObservers) {
            observer.onCollision(this, enemy);
        }
    }

    public void setCollided(boolean collided) {
        this.collided = collided;
    }
    public boolean getCollided() {
        return this.collided;
    }

}