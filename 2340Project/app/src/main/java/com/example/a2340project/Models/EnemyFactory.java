package com.example.a2340project.Models;

import android.content.res.Resources;

public abstract class EnemyFactory {

    // Create method to create enemy with type and resource
    public abstract Enemy createEnemy(String type, Resources res);

    // Create method to create enemy with type
    public abstract Enemy createEnemy(String type);
}
