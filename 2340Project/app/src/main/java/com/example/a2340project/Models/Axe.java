package com.example.a2340project.Models;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.a2340project.R;
/* AXE EXTENDS WEAPON */
public class Axe extends Weapon {
    /* CONSTRUCTOR */
    public Axe(Resources res) {
        this.setSize(50);
        setX(280);
        setY(60);
        setSprite(res);
    }
    /* CONSTRUCTOR */
    public Axe() {
        this.setSize(100);
        setX(280);
        setY(60);
    }
    /* SPRITE SETTER */
    void setSprite(Resources res) {
        Bitmap playerBitmap = BitmapFactory.decodeResource(res, R.drawable.axe);
        this.setSprite(Bitmap.createScaledBitmap(playerBitmap, this.getSize(), this.getSize(),
                false));
    }
    /* WEAPON ERASER */
    public void eraseWeapon() {
        setVisible(false);
        this.setSprite(Bitmap.createBitmap(this.getSize(), this.getSize(),
                Bitmap.Config.ARGB_8888));
    }
}
