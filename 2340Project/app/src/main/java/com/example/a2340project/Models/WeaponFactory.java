package com.example.a2340project.Models;

// this class defines a weapon factory, where weapons are created

import android.content.res.Resources;

public abstract class WeaponFactory {
    public abstract Weapon createWeapon(String type, Resources res);

    public abstract Weapon createWeapon(String type);
}
