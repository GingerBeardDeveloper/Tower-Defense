package com.gamecodeschool.towerdefense;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;

public class BasicTower extends Tower {

    public BasicTower(Point location) {
        super(location);
        this.damage = 50;
    }

    void draw(Canvas canvas, Paint paint) {
        canvas.drawBitmap(mBitmap,
                location.x * mSize, location.y * mSize, paint);
    }
}
