package com.gamecodeschool.towerdefense;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;

import java.util.List;

abstract class Tower extends GameObject {

    // Durability may or may not be used, if enemies shoot back at the tower, it would take damage to durability
    private int durability;
    protected double damage;
    protected double range;
    protected double speed;
    protected List<Projectile> listOfProjectiles;

    public Tower(Point location) {
        super(location);
    }

    // Inherits draw function
    abstract void draw(Canvas canvas, Paint paint);

    abstract void attack(Canvas mCanvas, Paint mPaint);
}
