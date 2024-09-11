package com.example.a2340project.ViewModels;

import android.graphics.Color;
import android.graphics.Bitmap;

/* MOVE RIGHT STRATEGY */
public class MoveRightStrategy implements MovementStrategy {
    private int playerX;
    private int playerY;
    private PlayerPositionSubject positionSubject;

    public MoveRightStrategy(int playerX, int playerY, PlayerPositionSubject positionSubject) {
        this.playerX = playerX;
        this.playerY = playerY;
        this.positionSubject = positionSubject;
    }

    // this is the move function for the move right strategy
    @Override
    public void move(Bitmap bitmapChoice) {
        if ((playerX + 10) >= 0 && (playerX + 10) <= bitmapChoice.getWidth()) {
            if (bitmapChoice.getPixel(playerX + 10, playerY) != Color.BLACK) {
                playerX += 10; // Move the player 10 units to the right
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
