package com.gamecodeschool.towerdefense;

import android.graphics.Point;

class Projectile extends MoveableObject {

    private double damage;
    private double range;
    private double speed;

    // The projectile gets its damage, range, and speed from the tower that instantiates it
    public Projectile(Point location, double damage, double range, double speed) {
        super(location);
        this.damage = damage;
        this.range = range;
        this.speed = speed;
    }

    /**
     * Adding functionality to the move function specific to Projectiles
     * they have a range of how far they can move, if range <= 0,
     * the Projectile will be removed from the GameWorld
     */
    @Override
    public void move() {
        // If enemy is in range, then attack them
        super.move();
        // Detect contact with enemy unit
        range -= speed;
    }

    // If the projectile is within range of an enemy, then reduce the hp of the enemy
    private boolean contactEnemy() {



        return false;
    }

}
