package com.example.a2340project.Models;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.a2340project.R;
/* SWORD EXTENDS WEAPON */
// sword is a type of weapon as well as used for testing purposes
public class Sword extends Weapon {
    /* CONSTRUCTOR */
    public Sword(Resources res) {
        this.setSize(50);
        setX(200);
        setY(250);
        setSprite(res);
    }
    /* CONSTRUCTOR */
    public Sword() {
        this.setSize(100);
        setX(200);
        setY(200);
    }
    /* SPRITE SETTER */
    void setSprite(Resources res) {
        Bitmap playerBitmap = BitmapFactory.decodeResource(res, R.drawable.sword);
        this.setSprite(Bitmap.createScaledBitmap(playerBitmap, this.getSize(), this.getSize(),
                false));
        //Log.i("draw Sword", "draw Sword");
    }
    /* WEAPON ERASER */
    // weapons are erased when used
    public void eraseWeapon() {
        setVisible(false);
        this.setSprite(Bitmap.createBitmap(this.getSize(), this.getSize(),
                Bitmap.Config.ARGB_8888));
    }
}
