package com.example.a2340project;

import static org.junit.Assert.assertEquals;

import com.example.a2340project.Models.Player;

import org.junit.Test;

public class difficultyHealthTest {
    /* Unit test for easy difficulty mode.
    Checks if when easy is selected the health is properly set to 100.
    Implemented by Maeci */
    @Test
    public void healthIs100() {
        Player player = Player.getInstance();

        if (player.getDifficulty() == "easy") {
            assertEquals(player.getHealth(), 100);
        }
    }
    /* Unit test for medium difficulty mode.
    Checks if when medium is selected the health is properly set to 75.
    Implemented by Maeci */
    @Test
    public void healthIs75() {
        Player player = Player.getInstance();

        if (player.getDifficulty() == "medium") {
            assertEquals(player.getHealth(), 75);
        }
    }
    /* Unit test for hard difficulty mode.
    Checks if when hard is selected the health is properly set to 50.
    Implemented by Arina */
    @Test
    public void healthIs50() {
        Player player = Player.getInstance();

        if (player.getDifficulty() == "hard") {
            assertEquals(player.getHealth(), 50);
        }
    }
}
