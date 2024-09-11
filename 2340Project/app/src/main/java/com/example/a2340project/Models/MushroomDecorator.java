package com.example.a2340project.Models;

//concrete decorator
public class MushroomDecorator extends PowerUpDecorator {

    public MushroomDecorator(BasePowerUp decoratedPowerUp) {
        setDecoratedPowerUp(decoratedPowerUp);
        setSize(60);
    }

    @Override
    public void applyPowerUp(Player player) {
        getDecoratedPowerUp().applyPowerUp(player);
        player.setSize(player.getSize() + 50);
    }

    @Override
    public String description() {
        return getDecoratedPowerUp().description() + "mushroom";
    }


}
