package com.gamecodeschool.towerdefense;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.RectF;

public class MachineGunBullets extends Projectile {

    private RectF mRect;

    public MachineGunBullets(Point location, double damage, double range, double speed) {
        super(location, damage, range, speed);
        mRect = new RectF(location.x, location.y, location.x+10, location.y+10);
    }

    public RectF getRect() { return mRect; }

    void draw(Canvas mCanvas, Paint mPaint) {
        //mPaint.setColor(Color.RED);
        mRect = new RectF(location.x - 20, location.y-10, location.x + 10, location.y);
        //mCanvas.drawRect(mRect, mPaint);
        mCanvas.drawBitmap(this.mBitmap, this.mRect.left, this.mRect.top, mPaint);
    }

}
