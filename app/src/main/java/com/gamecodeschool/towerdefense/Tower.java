package com.gamecodeschool.towerdefense;

import android.graphics.Point;

abstract class Tower extends GameObject {

    // Durability may or may not be used, if enemies shoot back at the tower, it would take damage to durability
    private int durability;
    private double damage;


    public Tower(Point initialLocation, float initialSize, int initialDurability, double initialDamage) {
        super(initialLocation, initialSize);
        durability = initialDurability;
        damage = initialDamage;
    }

}
