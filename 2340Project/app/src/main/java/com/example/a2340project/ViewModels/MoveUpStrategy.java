package com.example.a2340project.ViewModels;

import android.graphics.Color;
import android.graphics.Bitmap;

/* MOVE UP STRATEGY */
public class MoveUpStrategy implements MovementStrategy {
    private int playerX;
    private int playerY;
    private PlayerPositionSubject positionSubject;

    public MoveUpStrategy(int playerX, int playerY, PlayerPositionSubject positionSubject) {
        this.playerX = playerX;
        this.playerY = playerY;
        this.positionSubject = positionSubject;
    }

    @Override
    public void move(Bitmap bitmapChoice) {
        if ((playerY - 10) <= bitmapChoice.getHeight() && (playerY - 10) >= 0) {
            if (bitmapChoice.getPixel(playerX, playerY - 10) != Color.BLACK) {
                playerY -= 10; // Move the player 10 units up
                positionSubject.notifyObservers(playerX, playerY);
            }
        }
    }
    public int getPlayerX() {
        return playerX;
    }
    public int getPlayerY() {
        return playerY;
    }
}
