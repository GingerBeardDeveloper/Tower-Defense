package com.gamecodeschool.towerdefense;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;

abstract class MoveableObject extends GameObject {

    // Heading is a number between 0 and 359 to represent the heading in degrees
    protected double heading;
    protected double speed;

    public MoveableObject(Point location) {
        super(location);
    };

    /**
     * Moves the MoveableObject by updating the location using
     * the heading and speed to calculate the new point
     */
    abstract void move();

    void draw(Canvas canvas, Paint paint) {
        canvas.drawBitmap(mBitmap, location.x * mSize, location.y * mSize, paint);
    }
    /**
     * Rotates the heading of the MoveableObject
     * by adding to rotate left and subtracting to rotate right
     * value of heading
     */
    public void changeHeading(double amountToRotate) {
        heading = (heading + amountToRotate) % 360;
    }
}
