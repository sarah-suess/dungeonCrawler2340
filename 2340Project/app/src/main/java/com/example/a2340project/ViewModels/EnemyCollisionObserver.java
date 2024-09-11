package com.example.a2340project.ViewModels;

import com.example.a2340project.Models.Enemy;
import com.example.a2340project.Models.Player;
// this is the enemy collision observer class
public interface EnemyCollisionObserver {
    void onCollision(Player player, Enemy enemy);
}
