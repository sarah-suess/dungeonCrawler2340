package com.example.a2340project.Models;

import android.content.res.Resources;
import android.graphics.Bitmap;

// Base Decorator
public abstract class PowerUpDecorator extends BasePowerUp {
    private BasePowerUp decoratedPowerUp;

    public void setDecoratedPowerUp(BasePowerUp decoratedPowerUp) {
        this.decoratedPowerUp = decoratedPowerUp;
    }
    public BasePowerUp getDecoratedPowerUp() {
        return decoratedPowerUp;
    }

    @Override
    public abstract void applyPowerUp(Player player);

    @Override
    public int getSize() {
        return decoratedPowerUp.getSize();
    }
    @Override
    public void setSize(int size) {
        decoratedPowerUp.setSize(size);
    }

    @Override
    public int getX() {
        return decoratedPowerUp.getX();
    }

    @Override
    public void setX(int x) {
        decoratedPowerUp.setX(x);
    }

    @Override
    public int getY() {
        return decoratedPowerUp.getY();
    }

    @Override
    public void setY(int y) {
        decoratedPowerUp.setY(y);
    }

    @Override
    public void setSprite(Bitmap sprite) {
        decoratedPowerUp.setSprite(sprite);
    }

    @Override
    public void createSprite(Resources res) {
        decoratedPowerUp.createSprite(res);
    }

    public abstract String description();
}
