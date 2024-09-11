package com.example.a2340project.Models;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.a2340project.R;

// this class defines a Rainbow, a type of base power up
public class Rainbow extends BasePowerUp {

    public Rainbow(Resources res) {
        setSize(50);
        setX(400);
        setY(600);
        createSprite(res);
    }

    // creates the sprite on the bitmap of appropriate size
    public void createSprite(Resources res) {
        Bitmap playerBitmap = BitmapFactory.decodeResource(res, R.drawable.rainbow);
        this.setSprite(Bitmap.createScaledBitmap(playerBitmap, getSize(), getSize(), false));
    }
}
