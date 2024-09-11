package com.example.a2340project.Models;

//concrete decorator
public class RainbowDecorator extends PowerUpDecorator {

    public RainbowDecorator(BasePowerUp decoratedPowerUp) {
        setDecoratedPowerUp(decoratedPowerUp);
        setSize(50);
    }

    @Override
    public void applyPowerUp(Player player) {
        getDecoratedPowerUp().applyPowerUp(player);
        player.setHealth(100);
    }

    @Override
    public String description() {
        return getDecoratedPowerUp().description() + ", rainbow";
    }
}
