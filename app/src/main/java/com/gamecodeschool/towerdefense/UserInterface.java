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

    public void draw(Canvas mCanvas, Paint mPaint) {
        // TODO: create ui box on right side of screen
        height = mCanvas.getHeight();
        width = mCanvas.getWidth();
        // Draw rectangle on right 20% of screen for UI
        mCanvas.drawRect(width, height, (float) (width * 0.8), 0, mPaint);
        mPaint.setTextSize(40);
        mPaint.setColor(Color.BLACK);
        mCanvas.drawText("Lives: 5" , (float) (width * 0.82), (float) (height * 0.05), mPaint);
        mCanvas.drawText("Gold: 500 pieces", (float) (width * 0.82), (float) (height * 0.15), mPaint);
    }
}
