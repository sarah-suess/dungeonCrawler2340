package com.example.a2340project.Models;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.a2340project.R;

// defines a spear class
public class Spear extends Weapon {
    public Spear(Resources res) {
        this.setSize(50);
        setX(600);
        setY(450);
        setSprite(res);
    }
    public Spear() {
        this.setSize(100);
        setX(600);
        setY(450);
    }

    //creates a spear of appropriate size on bitmap
    void setSprite(Resources res) {
        Bitmap playerBitmap = BitmapFactory.decodeResource(res, R.drawable.spear);
        this.setSprite(Bitmap.createScaledBitmap(playerBitmap, this.getSize(), this.getSize(),
                false));
    }

    //erased when used to defeat enemy
    public void eraseWeapon() {
        setVisible(false);
        this.setSprite(Bitmap.createBitmap(this.getSize(), this.getSize(),
                Bitmap.Config.ARGB_8888));
    }
}
