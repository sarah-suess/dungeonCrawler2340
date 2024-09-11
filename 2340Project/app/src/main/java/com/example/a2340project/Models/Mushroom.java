package com.example.a2340project.Models;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.a2340project.R;

public class Mushroom extends BasePowerUp {

    public Mushroom(Resources res) {
        setSize(60);
        setX(100);
        setY(180);

    }

    public void createSprite(Resources res) {
        Bitmap playerBitmap = BitmapFactory.decodeResource(res, R.drawable.mushroom);
        this.setSprite(Bitmap.createScaledBitmap(playerBitmap, getSize(), getSize(), false));
    }
}
