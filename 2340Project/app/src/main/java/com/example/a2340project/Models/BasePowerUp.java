package com.example.a2340project.Models;


import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

// Concrete Component
public abstract class BasePowerUp implements PowerUpComponent {
    private Bitmap sprite;
    private int size;
    private int x;
    private int y;
    private boolean collected = false;

    @Override
    public void applyPowerUp(Player player) {
        // Base power-up behavior
    }
    
    @Override
    public void drawPowerUp(Canvas canvas, Paint paint) {
        canvas.drawBitmap(sprite, x, y, paint);
    }

    // getter for size
    @Override
    public int getSize() {
        return size;
    }

    // setter for size
    @Override
    public void setSize(int size) {
        this.size = size;
    }

    // getter for x pos of powerup
    @Override
    public int getX() {
        return x;
    }

    // setter for x pos of powerup
    @Override
    public void setX(int x) {
        this.x = x;
    }

    // getter for y pos of powerup
    @Override
    public int getY() {
        return y;
    }

    // setter for y pos of powerup
    @Override
    public void setY(int y) {
        this.y = y;
    }

    // creates sprite
    public abstract void createSprite(Resources res);

    // setter for sprite
    public void setSprite(Bitmap sprite) {
        this.sprite = sprite;
    }

    // whether the basePowerUp is collected
    public boolean isCollected() {
        return collected;
    }

    // setter for collected
    public void setCollected(boolean collected) {
        this.collected = collected;
    }

    // string description of power up
    @Override
    public String description() {
        return "Collected powerups: ";
    }
}
