package com.gamecodeschool.towerdefense;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;

abstract class GameObject {

    protected android.graphics.Point location;
    protected int mSize;
    protected Bitmap mBitmap;

    public GameObject(Point location) {
        this.location = location;
    }

    abstract void draw(Canvas mCanvas, Paint mPaint);

    public float getSize() {
        return mSize;
    }

    public Point getLocation() {
        return location;
    }

    void setLocation(Point newLocation) {
        this.location = newLocation;
    }
}
