package com.example.a2340project.Models;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

/* CLASS DEFINING WEAPON */
public abstract class Weapon {
    /* PRIVATE VARIABLES */
    private int size;
    private Bitmap sprite;
    private int x;
    private int y;
    private boolean collected = false;
    private boolean visible = true;

    /* DRAW WEAPON */
    public void drawWeapon(Canvas canvas, Paint paint) {
        canvas.drawBitmap(sprite, x, y, paint);
    }
    /* SIZE GETTER */
    public int getSize() {
        return size;
    }
    /* SIZE SETTER */
    public void setSize(int size) {
        this.size = size;
    }
    /* SPRITE SETTER */
    public void setSprite(Bitmap sprite) {
        this.sprite = sprite;
    }
    /* POSITION X GETTER */
    public int getX() {
        return x;
    }
    /* POSITION Y GETTER */
    public int getY() {
        return y;
    }
    /* POSITION X SETTER */
    public void setX(int x) {
        this.x = x;
    }
    /* POSITION Y SETTER */
    public void setY(int y) {
        this.y = y;
    }
    /* SPRITE SETTER */
    abstract void setSprite(Resources res);
    /* COLLECTED GETTER */
    public boolean isCollected() {
        return collected; }
    /* COLLECTED SETTER */
    public void setCollected(boolean collected) {
        this.collected = collected; }
    /* VISIBLE GETTER */
    public boolean isVisible() {
        return visible; }
    /* VISIBLE SETTER */
    public void setVisible(boolean visible) {
        this.visible = visible; }
    /* WEAPON ERASER */
    public abstract void eraseWeapon();
}
