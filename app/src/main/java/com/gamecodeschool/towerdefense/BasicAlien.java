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
        this.speed = 10;
        this.alive = true;
        this.heading = 0.0;
        mRect = new RectF(location.x - 20, location.y , location.x + 20, location.y);
        mBitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.basicalien);
    }

    public BasicAlien(Point startOfPath) {
        super(startOfPath);
    }


    public void move() {
        super.move();
    }

    public void setStartPosition(Point startPosition) {
        setLocation(startPosition);
    }

    @Override
    void draw(Canvas mCanvas, Paint mPaint) {
        //mPaint.setColor(Color.RED);
        mRect = new RectF(location.x - 20, location.y-350, location.x + 20, location.y);
        //mCanvas.drawRect(mRect, mPaint);
        mCanvas.drawBitmap(this.mBitmap, this.mRect.left, this.mRect.top, mPaint);
    }
}
