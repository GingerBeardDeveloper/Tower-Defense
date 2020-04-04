package com.gamecodeschool.towerdefense;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;

abstract class MoveableObject extends GameObject {

    // Heading is a number between 0 and 359 to represent the heading in degrees
    private double heading;
    protected double speed;

    /**
     * Moves the MoveableObject by updating the location using
     * the heading and speed to calculate the new point
     */
    public void move() {
        double dx, dy;
        dx = getLocation().x + (Math.cos(heading) * speed);
        dy = getLocation().y + (Math.sin(heading) * speed);
        setLocation(new Point((int) dx, (int) dy));
    }

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
