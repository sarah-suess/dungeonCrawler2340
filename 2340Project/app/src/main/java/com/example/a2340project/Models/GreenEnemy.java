package com.example.a2340project.Models;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;

import com.example.a2340project.R;

//defines a green enemy
public class GreenEnemy extends Enemy {
    private boolean movingLeft = true;

    // green enemy constructor with resources
    public GreenEnemy(Resources res) {
        this.setSpeed(3);
        this.setSize(85);
        setX(330);
        setY(450);
        setSprite(res);
    }

    // green enemy constructor
    public GreenEnemy() {
        this.setSpeed(3);
        this.setSize(85);
        setX(330);
        setY(450);
    }


    // set green enemy sprite
    void setSprite(Resources res) {
        Bitmap playerBitmap = BitmapFactory.decodeResource(res, R.drawable.greenenemy);
        this.setSprite(Bitmap.createScaledBitmap(playerBitmap, getSize(), getSize(), false));
    }

    // erase green enemy sprite, used when enemy dies after collision with player
    public void eraseSprite() {
        setAlive(false);
        this.setSprite(Bitmap.createBitmap(this.getSize(), this.getSize(),
                Bitmap.Config.ARGB_8888));
    }

    // green enemy move function
    public void move(Bitmap bitmap) {
        if (movingLeft) {
            //move left
            int newX = getX() - getSpeed();

            // Check if enemy is within bounds
            if ((newX > 0) && (bitmap.getPixel(newX, getY()) != Color.BLACK)) {
                setX(newX);
            } else {
                movingLeft = false;
            }
        } else {
            //move down
            int newX = getX() + getSpeed();

            // Check if enemy is within bounds
            if ((newX + getSize() < bitmap.getWidth())
                    && (bitmap.getPixel(newX, getY()) != Color.BLACK)) {
                setX(newX);
            } else {
                movingLeft = true;
            }
        }
    }

    // green enemy change direction function
    @Override
    public void changeDirection(boolean b) {
        this.movingLeft = b;
    }
}
