//package com.example.a2340project.Models;
//
//import android.content.res.Resources;
//
//public class GamePowerUpDecorator extends PowerUpDecorator {
//
//    @Override
//    public PowerUp createPowerup(String type, Resources res) {
//        if (type.equals("mushroom")) {
//            return new Mushroom(res);
//        } else if (type.equals("treasure")) {
//            return new Treasure(res);
//        } else if (type.equals("rainbow")) {
//            return new Rainbow(res);
//        } else {
//            return null;
//        }
//    }
//
//    @Override
//    public PowerUp createPowerup(String type) {
//        if (type.equals("mushroom")) {
//            return new Mushroom();
//        } else if (type.equals("treasure")) {
//            return new Treasure();
//        } else if (type.equals("rainbow")) {
//            return new Rainbow();
//        } else {
//            return null;
//        }
//    }
//}
