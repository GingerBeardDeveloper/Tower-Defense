package com.gamecodeschool.towerdefense;

import android.graphics.Point;
import android.graphics.RectF;

class Projectile extends MoveableObject {

    private double damage;
    private double speed;
    private double mSize;
    private RectF rect;

    // The projectile gets its damage, range, and speed from the tower that instantiates it
    public Projectile(Point location, double damage, double speed) {
        super(location);
        this.damage = damage;
        this.speed = speed;
        this.heading = 0;
        changeHeading(90.0);
    }

    /**
     * Adding functionality to the move function specific to Projectiles
     * they have a range of how far they can move, if range <= 0,
     * the Projectile will be removed from the GameWorld
     */
    @Override
    public void move() {
        double dx, dy;
        dx = getLocation().x + (Math.cos(heading) * speed);
        dy = getLocation().y + (Math.sin(heading) * speed);
        setLocation(new Point((int) dx, (int) dy));
    }

    public RectF getRect() { return this.rect; }
}
