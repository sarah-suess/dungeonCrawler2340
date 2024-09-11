package com.example.a2340project.Models;

import android.content.res.Resources;

public class GameWeaponFactory extends WeaponFactory {
    // Override createWeapon method with type and resource
    @Override
    public Weapon createWeapon(String type, Resources res) {
        if (type.equals("sword")) {
            return new Sword(res);
        } else if (type.equals("axe")) {
            return new Axe(res);
        } else if (type.equals("spear")) {
            return new Spear(res);
        } else {
            return null;
        }
    }

    // Override createWeapon method with type 
    @Override
    public Weapon createWeapon(String type) {
        if (type.equals("sword")) {
            return new Sword();
        } else if (type.equals("axe")) {
            return new Axe();
        } else if (type.equals("spear")) {
            return new Spear();
        } else {
            return null;
        }
    }
}
