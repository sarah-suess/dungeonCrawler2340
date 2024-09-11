package com.example.a2340project;

import static org.junit.Assert.assertEquals;

import com.example.a2340project.Models.Player;
import com.example.a2340project.Models.Sword;

import org.junit.Test;

public class collisionHealthTest {
    @Test
    public void collisionLose2() {
        Player player = Player.getInstance();
        int prevHealth = player.getHealth();
        if (player.getDifficulty() == "easy") {
            if (player.getCollided())
                assertEquals(player.getHealth(), prevHealth - 2);
        }
    }
    @Test
    public void collisionLose5() {
        Player player = Player.getInstance();
        int prevHealth = player.getHealth();
        if (player.getDifficulty() == "medium") {
            if (player.getCollided())
            assertEquals(player.getHealth(), prevHealth - 5);
        }
    }
    @Test
    public void collisionLose10() {
        Player player = Player.getInstance();
        int prevHealth = player.getHealth();
        if (player.getDifficulty() == "hard") {
            if (player.getCollided())
                assertEquals(player.getHealth(), prevHealth - 10);
        }
    }
}
