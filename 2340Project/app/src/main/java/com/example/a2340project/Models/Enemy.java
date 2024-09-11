package com.example.a2340project.Models;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

//this abstract class defines an enemy
//four concrete types of enemies extend this class

public abstract class Enemy {
    // Declare variables
    private int speed;
    private int size;
    private Bitmap sprite;
    private int x;
    private int y;
    private boolean alive = true;

    // Create method to draw enemy on bitmap
    public void drawEnemy(Canvas canvas, Paint paint) {
        canvas.drawBitmap(sprite, x, y, paint);
    }

    // Create getter for speed
    public int getSpeed() {
        return speed;
    }

    // Create getter for size
    public int getSize() {
        return size;
    }

    // Create setter for size
    public void setSize(int size) {
        this.size = size;
    }

    // Create setter for speed
    public void setSpeed(int speed) {
        this.speed = speed;
    }

    // Create setter for sprite
    public void setSprite(Bitmap sprite) {
        this.sprite = sprite;
    }

    // Create abstract class to erase sprite
    // Called when enemy dies
    public abstract void eraseSprite();

    // Create getter for X position
    public int getX() {
        return x;
    }

    // Create getter for Y position
    public int getY() {
        return y;
    }

    // Create setter for X position
    public void setX(int x) {
        this.x = x;
    }

    // Create setter for Y position
    public void setY(int y) {
        this.y = y;
    }

    // Create setter for sprite
    abstract void setSprite(Resources res);

    // Create method to move
    public void move(Bitmap bitmapchoice) {
        x += speed;
    }

    // Create method to change direction
    public abstract void changeDirection(boolean b);

    // Create method to check if sprite is alive
    public boolean isAlive() {
        return alive; }

    // Create setter for alive state
    public void setAlive(boolean alive) {
        this.alive = alive; }

    // Create method to destroy sprite
    public void destroy() {
        alive = false; }


}
