package com.example.a2340project.Models;

//concrete decorator
public class TreasureDecorator extends PowerUpDecorator {

    public TreasureDecorator(BasePowerUp decoratedPowerUp) {
        setDecoratedPowerUp(decoratedPowerUp);
        setSize(60);
    }

    @Override
    public void applyPowerUp(Player player) {
        getDecoratedPowerUp().applyPowerUp(player);
        player.setScore(player.getScore() + 5);
    }

    @Override
    public String description() {
        return getDecoratedPowerUp().description() + ", treasure";
    }
}
