package com.example.a2340project.Models;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.a2340project.R;
//defines a ghost enemy, a type of enemy
public class GhostEnemy extends Enemy {
    private boolean movingUp = true;

    // ghost enemy constructor with resources
    public GhostEnemy(Resources res) {
        this.setSpeed(4);
        this.setSize(75);
        setX(480);
        setY(250);
        setSprite(res);
    }

    // ghost enemy constructor with resources
    public GhostEnemy() {
        this.setSpeed(4);
        this.setSize(75);
        setX(480);
        setY(250);
    }

    // set ghost enemy sprite 
    void setSprite(Resources res) {
        Bitmap playerBitmap = BitmapFactory.decodeResource(res, R.drawable.ghost);
        this.setSprite(Bitmap.createScaledBitmap(playerBitmap, this.getSize(), this.getSize(),
                false));
    }

    // erase ghost enemy sprite
    // called when player attacks enemy with weapon and enemy dies
    public void eraseSprite() {
        setAlive(false);
        this.setSprite(Bitmap.createBitmap(this.getSize(), this.getSize(),
                Bitmap.Config.ARGB_8888));
    }

    // move method for ghost enemy
    public void move(Bitmap bitmap) {
        if (movingUp) {
            //move up
            int newY = getY() - getSpeed();

            // Check if enemy is within bounds
            if (newY > 0) {
                setY(newY);
            } else {
                movingUp = false;
            }
        } else {
            //move down
            int newY = getY() + getSpeed();

            // Check if enemy is within bounds
            if (newY + getSize() < bitmap.getHeight()) {
                setY(newY);
            } else {
                movingUp = true;
            }
        }
    }

    // override method for ghost to change direction
    @Override
    public void changeDirection(boolean b) {
        this.movingUp = b;
    }
}
