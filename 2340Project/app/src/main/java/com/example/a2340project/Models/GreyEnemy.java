package com.example.a2340project.Models;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;

import com.example.a2340project.R;

public class GreyEnemy extends Enemy {
    private boolean movingUp = true;

    // grey enemy constructor with resources
    public GreyEnemy(Resources res) {
        this.setSpeed(1);
        this.setSize(75);
        setX(50);
        setY(150);
        setSprite(res);
    }
    // grey enemy constructor
    public GreyEnemy() {
        this.setSpeed(1);
        this.setSize(75);
        setX(50);
        setY(150);
    }

    // set grey enemy sprite
    void setSprite(Resources res) {
        Bitmap playerBitmap = BitmapFactory.decodeResource(res, R.drawable.greyenemy);
        this.setSprite(Bitmap.createScaledBitmap(playerBitmap, getSize(), getSize(), false));
    }

    // erase grey enemy sprite
    public void eraseSprite() {
        setAlive(false);
        this.setSprite(Bitmap.createBitmap(this.getSize(), this.getSize(),
                Bitmap.Config.ARGB_8888));
    }

    // move method for grey enemy
    public void move(Bitmap bitmap) {
        if (movingUp) {
            //calculate new y position if player moves up
            int newY = getY() - getSpeed();

            // Check if enemy is within bounds
            if ((newY > 0) && (bitmap.getPixel(getX(), newY) != Color.BLACK)) {
                setY(newY);
            } else {
                movingUp = false;
            }
        } else {
            //move down
            int newY = getY() + getSpeed();

            // Check if enemy is within bounds
            if ((newY + getSize() < bitmap.getHeight())
                    && (bitmap.getPixel(getX(), newY) != Color.BLACK))  {
                setY(newY);
            } else {
                movingUp = true;
            }
        }
    }

    // change direction method for grey enemy
    @Override
    public void changeDirection(boolean b) {
        this.movingUp = b;
    }
}