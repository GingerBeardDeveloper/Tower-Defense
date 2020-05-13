package com.gamecodeschool.towerdefense;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.RectF;

public class AlienSpaceship extends Enemy {

    public AlienSpaceship(Point location, Context context) {
        super(location);
        this.hp = 5000;
        this.speed = 3;
        this.alive = true;
        this.heading = 0.0;
        Bitmap basicAlien = BitmapFactory.decodeResource(context.getResources(), R.drawable.alienspaceship);
        Bitmap resized = Bitmap.createScaledBitmap(basicAlien, (int)(basicAlien.getWidth()*.3), (int)(basicAlien.getHeight()*.3), true);
        mBitmap = resized;
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
        RectF mRect = new RectF(location.x-50, location.y-300, location.x + 50, location.y);
        //mCanvas.drawRect(mRect, mPaint);
        mCanvas.drawBitmap(this.mBitmap, mRect.left, mRect.top, mPaint);
    }
}
