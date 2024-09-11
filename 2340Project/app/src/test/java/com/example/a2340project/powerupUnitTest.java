package com.example.a2340project;

import static org.junit.Assert.assertEquals;

import android.content.res.Resources;

import com.example.a2340project.Models.BasePowerUp;
import com.example.a2340project.Models.DragonEnemy;
import com.example.a2340project.Models.Mushroom;
import com.example.a2340project.Models.MushroomDecorator;
import com.example.a2340project.Models.Player;
import com.example.a2340project.Models.RainbowDecorator;
import com.example.a2340project.Models.Sword;
import com.example.a2340project.Models.TreasureDecorator;

import org.junit.Test;

public class powerupUnitTest {

    @Test
    public void mushroomIncreasesSize() {
        Player player = Player.getInstance();
        int initialSize = player.getSize();
        BasePowerUp mushroom = new MushroomDecorator(new Mushroom(Resources.getSystem()));
        mushroom.applyPowerUp(player);
        assertEquals(player.getSize(), initialSize + 50);
    }

    @Test
    public void correctPowerupDescription() {
        BasePowerUp powerup = new MushroomDecorator(new Mushroom(Resources.getSystem()));
        powerup = new TreasureDecorator(powerup);
        powerup = new RainbowDecorator(powerup);

        assertEquals(powerup.description(), "Collected powerups: mushroom, treasure, rainbow");

    }
}
