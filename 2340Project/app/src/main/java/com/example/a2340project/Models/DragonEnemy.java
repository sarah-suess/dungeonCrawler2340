package com.example.a2340project.Models;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.util.Log;

import com.example.a2340project.R;
//this class defines an dragon enemy
public class DragonEnemy extends Enemy {
    //Player player = Player.getInstance();
    //private PlayerPositionObserver positionObserver;
    private boolean movingLeft = true;
    private int newX;
    public DragonEnemy(Resources res) {
        this.setSpeed(2);
        this.setSize(50);
        setX(500);
        setY(550);
        setSprite(res);
        //this.positionObserver= positionObserver;
    }

    //constructor
    public DragonEnemy() {
        this.setSpeed(2);
        this.setSize(50);
        setX(500);
        setY(550);
    }

    // Create function to set the dragon sprite using bitmapfactory
    void setSprite(Resources res) {
        Bitmap playerBitmap = BitmapFactory.decodeResource(res, R.drawable.dragon);
        this.setSprite(Bitmap.createScaledBitmap(playerBitmap, this.getSize(), this.getSize(),
                false));
    }

    // Create function to delete sprite from the bitmap and set the setAlive state to false
    // necessary when the enemy dies
    public void eraseSprite() {
        setAlive(false);
        this.setSprite(Bitmap.createBitmap(this.getSize(), this.getSize(),
                Bitmap.Config.ARGB_8888));
        Log.d("enemy", "erased");
    }

    // Create function to move the dragon sprite using current position and speed
    public void move(Bitmap bitmap) {
        if (movingLeft) {
            //move left
            newX = getX() - this.getSpeed();

            // Check if enemy is within bounds
            if ((newX > 0) && (bitmap.getPixel(newX, getY()) != Color.BLACK)) {
                setX(newX);
            } else {
                movingLeft = false;
            }
        } else {
            //move down
            int newX = getX() + this.getSpeed();

            // Check if enemy is within bounds
            if ((newX + this.getSize() < bitmap.getWidth())
                    && (bitmap.getPixel(newX, getY()) != Color.BLACK)) {
                setX(newX);
            } else {
                movingLeft = true;
            }
        }
    }

    // Create override function to change the direction of the dragon sprite
    @Override
    public void changeDirection(boolean b) {
        this.movingLeft = b;
    }
}
