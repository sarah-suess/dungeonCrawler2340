package com.example.a2340project.Models;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.a2340project.R;

// this class defines tresure, a type of base powerup

public class Treasure extends BasePowerUp {

    public Treasure(Resources res) {
        setSize(60);
        setX(150);
        setY(590);
        createSprite(res);
    }

    //displays treasure of appropriate size on the bitmap
    public void createSprite(Resources res) {
        Bitmap playerBitmap = BitmapFactory.decodeResource(res, R.drawable.treasure);
        this.setSprite(Bitmap.createScaledBitmap(playerBitmap, getSize(), getSize(), false));
    }
}
