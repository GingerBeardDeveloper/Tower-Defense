package com.gamecodeschool.towerdefense;

import android.graphics.Point;

abstract class Projectile extends MoveableObject {

    private double damage;
    private double range;

    /**
     * Adding functionality to the move function specific to Projectiles
     * they have a range of how far they can move, if range <= 0,
     * the Projectile will be removed from the GameWorld
     */
    @Override
    public void move() {
        super.move();
        range -= speed;
    }
}
