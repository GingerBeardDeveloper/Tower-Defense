package com.gamecodeschool.towerdefense;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.RectF;

// The purpose of this class is a horizontal bar on the bottom of the game
// It will allow the user to place towers, pause, and resume the game
public class UserInterface {

    int height, width;

    public UserInterface() {

    }

    public void draw(Canvas mCanvas, Paint mPaint, int lives, int gold, boolean paused) {
        // TODO: create ui box on right side of screen
        height = mCanvas.getHeight();
        width = mCanvas.getWidth();

        // Draw rectangle on right 20% of screen for UI
        mCanvas.drawRect(width, height, (float) (width * 0.8), 0, mPaint);
        mPaint.setTextSize(40);
        mPaint.setColor(Color.BLACK);

        // List Lives remaining and Gold accumulated
        mCanvas.drawText("Lives: " + lives, (float) (width * 0.82), (float) (height * 0.05), mPaint);
        mCanvas.drawText("Gold: " + gold, (float) (width * 0.82), (float) (height * 0.1), mPaint);


        // Play/Pause Button
        mPaint.setColor(Color.DKGRAY);
        mCanvas.drawRect((float) (width * 0.82), (float) (height * 0.86), (float) (width * 0.98), (float) (height * 0.98), mPaint);
        mPaint.setColor(Color.WHITE);
        mPaint.setTextSize(100);
        if (paused) {
            mCanvas.drawText("Play",(float) (mCanvas.getWidth() * 0.84), (float) (mCanvas.getHeight() * 0.95), mPaint);
        } else {
            mCanvas.drawText("Pause",(float) (mCanvas.getWidth() * 0.84), (float) (mCanvas.getHeight() * 0.95), mPaint);
        }
    }
}
