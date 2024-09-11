package com.example.a2340project.Models;

import android.content.res.Resources;

public class GameEnemyFactory extends EnemyFactory {

    // Override create enemy with type and resource
    @Override
    public Enemy createEnemy(String type, Resources res) {
        if (type.equals("dragon")) {
            return new DragonEnemy(res);
        } else if (type.equals("ghost")) {
            return new GhostEnemy(res);
        } else if (type.equals("green")) {
            return new GreenEnemy(res);
        } else if (type.equals("grey")) {
            return new GreyEnemy(res);
        } else {
            return null;
        }
    }

    // Override create enemy with type
    @Override
    public Enemy createEnemy(String type) {
        if (type.equals("dragon")) {
            return new DragonEnemy();
        } else if (type.equals("ghost")) {
            return new GhostEnemy();
        } else if (type.equals("green")) {
            return new GreenEnemy();
        } else if (type.equals("grey")) {
            return new GreyEnemy();
        } else {
            return null;
        }
    }
}
