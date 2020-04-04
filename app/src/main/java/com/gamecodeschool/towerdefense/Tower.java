package com.gamecodeschool.towerdefense;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;

abstract class Tower extends GameObject {

    // Durability may or may not be used, if enemies shoot back at the tower, it would take damage to durability
    private int durability;
    protected double damage;

    public Tower(Point location) {
        super(location);
    }

    abstract void draw(Canvas canvas, Paint paint);
    // Inherits draw function
}
