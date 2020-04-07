package com.gamecodeschool.towerdefense;

import android.graphics.Point;

class Bullet extends Projectile {

    public Bullet(Point location, double damage, double range, double speed, int mSize) {
        super(location, damage, range, speed, mSize);
    }
}
