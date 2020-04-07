package com.gamecodeschool.towerdefense;

// The most basic enemy in the 'Invasion Earth' Model

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;

public class BasicAlien extends Enemy {

    public BasicAlien(Point location) {
        super(location);
        this.hp = 100;
        this.speed = 20;
        this.alive = true;
        this.heading = 0.0;
    }

    public void move() {
        super.move();
    }

    @Override
    void draw(Canvas mCanvas, Paint mPaint) {
        mPaint.setColor(Color.RED);
        mCanvas.drawRect(location.x - 20, location.y - 20, location.x + 20, location.y + 20, mPaint);
    }
}
