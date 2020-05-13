package com.gamecodeschool.towerdefense;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.RectF;

public class SupremeAlien extends Enemy {
    RectF mRect;

    public SupremeAlien(Point location, Context context) {
        super(location);
        this.hp = 2000;
        this.speed = 5;
        this.alive = true;
        this.heading = 0.0;
        Bitmap basicAlien = BitmapFactory.decodeResource(context.getResources(), R.drawable.supremealien);
        Bitmap resized = Bitmap.createScaledBitmap(basicAlien, (int)(basicAlien.getWidth()*.1), (int)(basicAlien.getHeight()*.1), true);
        mBitmap = resized;
    }

    public void move() {
        super.move();
    }

    public void setStartPosition(Point startPosition) {
        setLocation(startPosition);
    }

    public RectF getRect() {
        return this.mRect;
    }

    @Override
    void draw(Canvas mCanvas, Paint mPaint) {
        mRect = new RectF(location.x - 50, location.y - 110, location.x + 50, location.y + 110);
        mCanvas.drawBitmap(this.mBitmap, this.mRect.left, this.mRect.top, mPaint);
    }

}
