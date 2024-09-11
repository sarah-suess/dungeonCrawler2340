package com.example.a2340project;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.CountDownTimer;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

import com.example.a2340project.Models.BasePowerUp;
import com.example.a2340project.Models.DragonEnemy;
import com.example.a2340project.Models.Enemy;
import com.example.a2340project.Models.EnemyFactory;
import com.example.a2340project.Models.GameEnemyFactory;
import com.example.a2340project.Models.GameWeaponFactory;
import com.example.a2340project.Models.MushroomDecorator;
import com.example.a2340project.Models.Player;
import com.example.a2340project.Models.PowerUpDecorator;
import com.example.a2340project.Models.Sword;
import com.example.a2340project.Models.WeaponFactory;
import com.example.a2340project.ViewModels.MoveDownStrategy;
import com.example.a2340project.ViewModels.MoveLeftStrategy;
import com.example.a2340project.ViewModels.MoveRightStrategy;
import com.example.a2340project.ViewModels.MoveUpStrategy;
import com.example.a2340project.ViewModels.PlayerPositionSubject;
import com.example.a2340project.Views.GameScreen;
import com.example.a2340project.Models.GreenEnemy;
import com.example.a2340project.Models.GhostEnemy;
import com.example.a2340project.Models.GreyEnemy;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        assertEquals("com.example.a2340project", appContext.getPackageName());
    }@Test
    public void testMoveUpStrategy() {
        // Create a test scenario where the player should move up.
        // Initialize the player's position and movement strategy.
        int initialX = 20;
        int initialY = 20;
        PlayerPositionSubject ps = new PlayerPositionSubject();
        MoveUpStrategy moveUpStrategy = new MoveUpStrategy(initialX, initialY, ps);
        Bitmap collisionMap = Bitmap.createBitmap(800,800, Bitmap.Config.RGB_565);
        collisionMap.eraseColor(Color.WHITE);

        // Execute the movement strategy.
        moveUpStrategy.move(collisionMap); // Assuming "0" is the key code for moving up

        // Assert that the player's position has moved up as expected.
        assertEquals(initialX, moveUpStrategy.getPlayerX()); // X-coordinate should remain unchanged.
        assertEquals(initialY - 10,moveUpStrategy.getPlayerY());
    }


    @Test
    public void testMoveDownStrategy() {
        // Create a test scenario where the player should move up.
        // Initialize the player's position and movement strategy.
        int initialX = 20;
        int initialY = 20;
        PlayerPositionSubject ps = new PlayerPositionSubject();
        MoveDownStrategy moveDownStrategy = new MoveDownStrategy(initialX, initialY, ps);
        Bitmap collisionMap = Bitmap.createBitmap(800,800, Bitmap.Config.RGB_565);
        collisionMap.eraseColor(Color.WHITE);
        
        // Execute the movement strategy.
        moveDownStrategy.move(collisionMap); // Assuming "0" is the key code for moving up

        // Assert that the player's position has moved up as expected.
        assertEquals(initialX, moveDownStrategy.getPlayerX()); // X-coordinate should remain unchanged.
        assertEquals(initialY + 10,moveDownStrategy.getPlayerY());
    }

    @Test
    /* Right Movement Strategy Test */
    public void testMoveRightStrategy() {
        int initialX = 20;
        int initialY = 20;
        PlayerPositionSubject ps = new PlayerPositionSubject();
        MoveRightStrategy moveRightStrategy = new MoveRightStrategy(initialX, initialY, ps); // set variable to test
        Bitmap collisionMap = Bitmap.createBitmap(800,800, Bitmap.Config.RGB_565);
        collisionMap.eraseColor(Color.WHITE);
        moveRightStrategy.move(collisionMap); // Movement strategy executed
        assertEquals(initialY, moveRightStrategy.getPlayerY()); // Y-coordinate is unchanged
        assertEquals(initialX + 10,moveRightStrategy.getPlayerX()); // X-coordinate increases by 10 (one movement right)
    }
    @Test
    /* Left Movement Strategy Test */
    public void testMoveLeftStrategy() {
        int initialX = 20;
        int initialY = 20;
        PlayerPositionSubject ps = new PlayerPositionSubject();
        MoveLeftStrategy moveLeftStrategy = new MoveLeftStrategy(initialX, initialY, ps); // set variable to test
        Bitmap collisionMap = Bitmap.createBitmap(800,800, Bitmap.Config.RGB_565);
        collisionMap.eraseColor(Color.WHITE);
        moveLeftStrategy.move(collisionMap); // Movement strategy executed
        assertEquals(initialY, moveLeftStrategy.getPlayerY()); // Y-coordinate is unchanged
        assertEquals(initialX - 10,moveLeftStrategy.getPlayerX()); // X-coordinate decreases by 10 (one movement left)
    }

    @Test
    public void testWallCollisionLeft() {

        Bitmap collisionMap = Bitmap.createBitmap(800,800, Bitmap.Config.RGB_565);
        int initialX = 20;
        int initialY = 20;

        PlayerPositionSubject ps = new PlayerPositionSubject();
        MoveLeftStrategy moveLeftStrategy = new MoveLeftStrategy(initialX, initialY, ps);
        collisionMap.eraseColor(Color.WHITE);
        collisionMap.setPixel(initialX - 10, initialY, Color.BLACK);
        moveLeftStrategy.move(collisionMap);
        assertEquals(initialY, moveLeftStrategy.getPlayerY()); // Y-coordinate is unchanged
        assertEquals(initialX,moveLeftStrategy.getPlayerX());

    }

    @Test
    public void testStayWithinBoundariesLeft() {

        Bitmap collisionMap = Bitmap.createBitmap(800,800, Bitmap.Config.RGB_565);
        int initialX = 5;
        int initialY = 5;

        PlayerPositionSubject ps = new PlayerPositionSubject();
        MoveLeftStrategy moveLeftStrategy = new MoveLeftStrategy(initialX, initialY, ps);
        collisionMap.eraseColor(Color.WHITE);
        moveLeftStrategy.move(collisionMap);
        assertEquals(initialY, moveLeftStrategy.getPlayerY()); // Y-coordinate is unchanged
        assertEquals(initialX,moveLeftStrategy.getPlayerX());

    }
    @Test
    public void testStayWithinBoundariesRight() {

        Bitmap collisionMap = Bitmap.createBitmap(800,800, Bitmap.Config.RGB_565);
        int initialX = 795;
        int initialY = 795;

        PlayerPositionSubject ps = new PlayerPositionSubject();
        MoveRightStrategy moveRightStrategy = new MoveRightStrategy(initialX, initialY, ps);
        collisionMap.eraseColor(Color.WHITE);
        moveRightStrategy.move(collisionMap);
        assertEquals(initialY, moveRightStrategy.getPlayerY());
        assertEquals(initialX,moveRightStrategy.getPlayerX());

    }
    @Test
    public void testStayWithinBoundariesUp() {

        Bitmap collisionMap = Bitmap.createBitmap(800,800, Bitmap.Config.RGB_565);
        int initialX = 5;
        int initialY = 5;

        PlayerPositionSubject ps = new PlayerPositionSubject();
        MoveUpStrategy moveUpStrategy = new MoveUpStrategy(initialX, initialY, ps);
        collisionMap.eraseColor(Color.WHITE);
        moveUpStrategy.move(collisionMap);
        assertEquals(initialY, moveUpStrategy.getPlayerY());
        assertEquals(initialX,moveUpStrategy.getPlayerX());

    }
    @Test
    public void testStayWithinBoundariesDown() {

        Bitmap collisionMap = Bitmap.createBitmap(800,800, Bitmap.Config.RGB_565);
        int initialX = 795;
        int initialY = 795;

        PlayerPositionSubject ps = new PlayerPositionSubject();
        MoveDownStrategy moveDownStrategy = new MoveDownStrategy(initialX, initialY, ps);
        collisionMap.eraseColor(Color.WHITE);
        moveDownStrategy.move(collisionMap);
        assertEquals(initialY, moveDownStrategy.getPlayerY());
        assertEquals(initialX,moveDownStrategy.getPlayerX());

    }

    @Test
    public void testWallCollisionRight() {

        Bitmap collisionMap = Bitmap.createBitmap(800,800, Bitmap.Config.RGB_565);
        int initialX = 20;
        int initialY = 20;

        PlayerPositionSubject ps = new PlayerPositionSubject();
        MoveRightStrategy moveRightStrategy = new MoveRightStrategy(initialX, initialY, ps);
        collisionMap.eraseColor(Color.WHITE);
        collisionMap.setPixel(initialX + 10, initialY, Color.BLACK);
        moveRightStrategy.move(collisionMap);
        assertEquals(initialY, moveRightStrategy.getPlayerY()); // Y-coordinate not changed
        assertEquals(initialX,moveRightStrategy.getPlayerX());

    }

    @Test
    public void testWallCollisionUp() {

        Bitmap collisionMap = Bitmap.createBitmap(800, 800, Bitmap.Config.RGB_565);
        int initialX = 20;
        int initialY = 20;

        PlayerPositionSubject ps = new PlayerPositionSubject();
        MoveUpStrategy moveUpStrategy = new MoveUpStrategy(initialX, initialY, ps);
        collisionMap.eraseColor(Color.WHITE);
        collisionMap.setPixel(initialX, initialY - 10, Color.BLACK);
        moveUpStrategy.move(collisionMap);
        assertEquals(initialY, moveUpStrategy.getPlayerY());
        assertEquals(initialX, moveUpStrategy.getPlayerX());
    }
    @Test
    public void testWallCollisionDown() {

        Bitmap collisionMap = Bitmap.createBitmap(800, 800, Bitmap.Config.RGB_565);
        int initialX = 20;
        int initialY = 20;

        PlayerPositionSubject ps = new PlayerPositionSubject();
        MoveDownStrategy moveDownStrategy = new MoveDownStrategy(initialX, initialY, ps);
        collisionMap.eraseColor(Color.WHITE);
        collisionMap.setPixel(initialX, initialY + 10, Color.BLACK);
        moveDownStrategy.move(collisionMap);
        assertEquals(initialY, moveDownStrategy.getPlayerY());
        assertEquals(initialX, moveDownStrategy.getPlayerX());
    }

    @Test
    public void ghostEnemyStaysInBoundsUp() {
        Bitmap collisionMap = Bitmap.createBitmap(800, 800, Bitmap.Config.RGB_565);

        EnemyFactory enemyFactory = new GameEnemyFactory();
        Enemy enemy1 = enemyFactory.createEnemy("ghost");
        enemy1.setY(2);
        enemy1.move(collisionMap);
        assertEquals(enemy1.getY(), 2);
    }

    @Test
    public void ghostEnemyStaysInBoundsDown() {
        Bitmap collisionMap = Bitmap.createBitmap(800, 800, Bitmap.Config.RGB_565);

        EnemyFactory enemyFactory = new GameEnemyFactory();
        Enemy enemy1 = enemyFactory.createEnemy("ghost");
        enemy1.setY(798);
        enemy1.changeDirection(false);
        enemy1.move(collisionMap);
        assertEquals(enemy1.getY(), 798);
    }
    @Test
    public void dragonEnemyStaysInBoundsLeft() {
        Bitmap collisionMap = Bitmap.createBitmap(800, 800, Bitmap.Config.RGB_565);

        EnemyFactory enemyFactory = new GameEnemyFactory();
        Enemy enemy1 = enemyFactory.createEnemy("dragon");
        enemy1.setX(2);
        enemy1.move(collisionMap);
        assertEquals(enemy1.getX(), 2);
    }
    @Test
    public void dragonEnemyStaysInBoundsRight() {
        Bitmap collisionMap = Bitmap.createBitmap(800, 800, Bitmap.Config.RGB_565);

        EnemyFactory enemyFactory = new GameEnemyFactory();
        Enemy enemy1 = enemyFactory.createEnemy("dragon");
        enemy1.setX(798);
        enemy1.changeDirection(false);
        enemy1.move(collisionMap);
        assertEquals(enemy1.getX(), 798);
    }

    // uses the collision logic utilized in our hasCollidedWithEnemy function
    // checks that it returns true if the player and the enemy are in the same coordinate location/overlapping
    @Test
    public void checkCollisions() {
        int playerY = 50;
        int playerX = 50;
        EnemyFactory enemyFactory = new GameEnemyFactory();
        DragonEnemy enemy1 = new DragonEnemy();
        enemy1.setX(50);
        enemy1.setY(50);
        assertEquals(true, playerY < enemy1.getY() + 20 && playerY + 20 > enemy1.getY() && playerX < enemy1.getX() + 20 && playerX + 20 > enemy1.getX());
    }
    public void greyEnemyStaysInBoundsUp() {
        Bitmap collisionMap = Bitmap.createBitmap(800, 800, Bitmap.Config.RGB_565);

        EnemyFactory enemyFactory = new GameEnemyFactory();
        Enemy enemy1 = enemyFactory.createEnemy("grey");
        enemy1.setY(2);
        enemy1.move(collisionMap);
        assertEquals(enemy1.getY(), 2);
    }

    @Test
    public void greyEnemyStaysInBoundsDown() {
        Bitmap collisionMap = Bitmap.createBitmap(800, 800, Bitmap.Config.RGB_565);

        EnemyFactory enemyFactory = new GameEnemyFactory();
        Enemy enemy1 = enemyFactory.createEnemy("grey");
        enemy1.setY(798);
        enemy1.changeDirection(false);
        enemy1.move(collisionMap);
        assertEquals(enemy1.getY(), 798);
    }

    @Test
    public void greenEnemyStaysInBoundsLeft() {
        Bitmap collisionMap = Bitmap.createBitmap(800, 800, Bitmap.Config.RGB_565);

        EnemyFactory enemyFactory = new GameEnemyFactory();
        Enemy enemy1 = enemyFactory.createEnemy("green");
        enemy1.setX(2);
        enemy1.move(collisionMap);
        assertEquals(enemy1.getX(), 2);
    }
    @Test
    public void greenEnemyStaysInBoundsRight() {
        Bitmap collisionMap = Bitmap.createBitmap(800, 800, Bitmap.Config.RGB_565);

        EnemyFactory enemyFactory = new GameEnemyFactory();
        Enemy enemy1 = enemyFactory.createEnemy("green");
        enemy1.setX(798);
        enemy1.changeDirection(false);
        enemy1.move(collisionMap);
        assertEquals(enemy1.getX(), 798);
    }
    // testPowerUpCollected checks to make sure that the collected method for
    // power ups is functioning properly
    @Test
    public void dragonEnemyDiesAfterCollision() {
        int playerY = 50;
        int playerX = 50;

        DragonEnemy enemy = new DragonEnemy();
        enemy.setX(50);
        enemy.setY(50);

        Sword sword = new Sword();
        sword.setVisible(true);

        if (playerY < enemy.getY() + 20 && playerY + 20 > enemy.getY() && playerX < enemy.getX() + 20 && playerX + 20 > enemy.getX()) {
            enemy.eraseSprite();
        }
        assertEquals(false, enemy.isAlive());
    }
    // testBasePowerUpDescription checks the functionality of the description method for mushroom
    // to make sure the correct description shown
    @Test
    public void testBasePowerUpDescription() {
        BasePowerUp basePowerUp = new BasePowerUp() {
            @Override
            public void applyPowerUp(Player player) {
            }


            @Override
            public void createSprite(Resources resources) {
            }
        };
        PowerUpDecorator mushroomDecorator = new MushroomDecorator(basePowerUp);
        assertEquals("Collected powerups: mushroom", mushroomDecorator.description());
    }

    @Test
    public void greenEnemyDiesAfterCollision() {
        int playerY = 50;
        int playerX = 50;

        GreenEnemy enemy = new GreenEnemy();
        enemy.setX(50);
        enemy.setY(50);


        Sword sword = new Sword();
        sword.setVisible(true);

        if (playerY < enemy.getY() + 20 && playerY + 20 > enemy.getY() && playerX < enemy.getX() + 20 && playerX + 20 > enemy.getX()) {
            enemy.eraseSprite();
        }
        assertEquals(false, enemy.isAlive());
    }

    @Test
    public void ghostEnemyDiesAfterCollision() {
        int playerY = 50;
        int playerX = 50;

        GhostEnemy enemy = new GhostEnemy();
        enemy.setX(50);
        enemy.setY(50);

        Sword sword = new Sword();
        sword.setVisible(true);

        if (playerY < enemy.getY() + 20 && playerY + 20 > enemy.getY() && playerX < enemy.getX() + 20 && playerX + 20 > enemy.getX()) {
            enemy.eraseSprite();
        }
        assertEquals(false, enemy.isAlive());
    }

    @Test
    public void greyEnemyDiesAfterCollision() {
        int playerY = 50;
        int playerX = 50;

        GreyEnemy enemy = new GreyEnemy();
        enemy.setX(50);
        enemy.setY(50);

        Sword sword = new Sword();
        sword.setVisible(true);

        if (playerY < enemy.getY() + 20 && playerY + 20 > enemy.getY() && playerX < enemy.getX() + 20 && playerX + 20 > enemy.getX()) {
            enemy.eraseSprite();
        }
        assertEquals(false, enemy.isAlive());
    }


}