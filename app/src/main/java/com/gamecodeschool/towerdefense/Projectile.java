package com.gamecodeschool.towerdefense;

import android.graphics.Point;

class Projectile extends MoveableObject {

    private double damage;
    private double range;
    private double speed;
    private double mSize;

    // The projectile gets its damage, range, and speed from the tower that instantiates it
    public Projectile(Point location, double damage, double range, double speed, int mSize) {
        super(location);
        this.damage = damage;
        this.range = range;
        this.speed = speed;
        this.mSize = mSize;
        changeHeading(90.0);
    }

    /**
     * Adding functionality to the move function specific to Projectiles
     * they have a range of how far they can move, if range <= 0,
     * the Projectile will be removed from the GameWorld
     */
    @Override
    public void move() {
        // If enemy is in range, then attack them
        double dx, dy;
        dx = getLocation().x + (Math.cos(heading) * speed);
        dy = getLocation().y + (Math.sin(heading) * speed);
        setLocation(new Point((int) dx, (int) dy));        // Detect contact with enemy unit
        //range -= speed;
    }

    // If the projectile is within range of an enemy, then reduce the hp of the enemy
    private boolean contactEnemy() {

        return false;
    }

}
