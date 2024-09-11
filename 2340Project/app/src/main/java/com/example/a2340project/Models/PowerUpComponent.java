package com.example.a2340project.Models;

import android.graphics.Canvas;
import android.graphics.Paint;

// Component Interface
public interface PowerUpComponent {
    void applyPowerUp(Player player);
    void drawPowerUp(Canvas canvas, Paint paint);
    int getSize();
    void setSize(int size);
    int getX();
    void setX(int x);
    int getY();
    void setY(int y);

    String description();
}

