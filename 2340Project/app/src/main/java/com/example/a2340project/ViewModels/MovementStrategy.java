package com.example.a2340project.ViewModels;
import android.graphics.Bitmap;

/* MOVEMENT STRATEGY */
public interface MovementStrategy {
    void move(Bitmap bitmapChoice);
    int getPlayerX();
    int getPlayerY();

}