package com.gamecodeschool.towerdefense;

// The most basic enemy in the 'Invasion Earth' Model

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.RectF;

public class BasicAlien extends Enemy {
    RectF mRect;


    public BasicAlien(Point location, Context context) {
        super(location);
        this.hp = 100;
        this.speed = 50;
        this.alive = true;
        this.heading = 0.0;
        mRect = new RectF(location.x - 100, location.y , location.x + 100, location.y);
        Bitmap basicAlien = BitmapFactory.decodeResource(context.getResources(), R.drawable.basicalien);
        Bitmap resized = Bitmap.createScaledBitmap(basicAlien, (int)(basicAlien.getWidth()*.3), (int)(basicAlien.getHeight()*.3), true);
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
        //mPaint.setColor(Color.RED);
        mRect = new RectF(location.x-50, location.y-70, location.x + 50, location.y + 70);
        //mCanvas.drawRect(mRect, mPaint);
        mCanvas.drawBitmap(this.mBitmap, this.mRect.left, this.mRect.top, mPaint);
    }
}
