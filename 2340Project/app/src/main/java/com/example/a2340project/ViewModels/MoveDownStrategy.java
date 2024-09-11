package com.example.a2340project.ViewModels;

import android.graphics.Color;
import android.graphics.Bitmap;

/* MOVE DOWN STRATEGY */
public class MoveDownStrategy implements MovementStrategy {
    private int playerX;
    private int playerY;
    private PlayerPositionSubject positionSubject;

    // this is the move function for the move down strategy
    public MoveDownStrategy(int playerX, int playerY, PlayerPositionSubject positionSubject) {
        this.playerX = playerX;
        this.playerY = playerY;
        this.positionSubject = positionSubject;
    }

    @Override
    public void move(Bitmap bitmapChoice) {
        if ((playerY + 10) >= 0 && (playerY + 10) <= bitmapChoice.getHeight()) {
            if (bitmapChoice.getPixel(playerX, playerY + 10) != Color.BLACK) {
                playerY += 10; // Move the player 10 units down
                // Notify the subject that the player's position has changed
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
