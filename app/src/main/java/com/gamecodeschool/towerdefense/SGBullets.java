package com.gamecodeschool.towerdefense;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;

public class SGBullets extends Bullet {

    public SGBullets(Point location, double heading, double damage, double speed) {
        super(location, damage, speed);
        this.heading = heading;
    }

    void draw(Canvas mCanvas, Paint mPaint) {
        mPaint.setColor(Color.RED);
        mCanvas.drawCircle(location.x, location.y, 6, mPaint);
        mPaint.setColor(Color.GRAY);
        mCanvas.drawCircle(location.x, location.y, 5, mPaint);
    }

}
