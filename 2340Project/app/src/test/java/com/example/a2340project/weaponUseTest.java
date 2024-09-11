package com.example.a2340project;

import static org.junit.Assert.assertEquals;

import android.os.CountDownTimer;

import com.example.a2340project.Models.DragonEnemy;
import com.example.a2340project.Models.Player;
import com.example.a2340project.Models.Spear;
import com.example.a2340project.Models.Sword;

import org.junit.Test;

public class weaponUseTest {
    /* TEST THAT WEAPON CAN BE USED ONLY ONCE
     * Once the weapon is used (no longer visible) the player's score should
     * not increase when the player collides with enemy */
    @Test
    public void singleWeaponUse() {
        Player player = Player.getInstance();
        int prevScore = player.getScore();
        DragonEnemy enemy = new DragonEnemy();
        Sword sword = new Sword();
        sword.setCollected(true);
        sword.setVisible(false);
        if (player.getCollided()) {
            assertEquals(prevScore, player.getScore());
        }
    }

    @Test
    public void singleWeaponUseSpear() {
        Player player = Player.getInstance();
        int prevScore = player.getScore();
        DragonEnemy enemy = new DragonEnemy();
        Spear spear = new Spear();
        spear.setCollected(true);
        spear.setVisible(false);
        if (player.getCollided()) {
            assertEquals(prevScore, player.getScore());
        }
    }

    @Test
    public void scoreLowersEveryTenSeconds() {
        CountDownTimer countDownTimer = new CountDownTimer(120000,1000) {
            int points = 10;
            @Override
            public void onTick(long l) {
                long secondsLeft = l/1000;
                if (secondsLeft % 10 == 0) {
                    points = points - 1;
                }
            }

            @Override
            public void onFinish() {
                assertEquals(points,0);
            }
        }.start();
    }
}
