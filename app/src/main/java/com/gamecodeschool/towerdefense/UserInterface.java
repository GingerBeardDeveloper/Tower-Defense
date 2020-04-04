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

    private Context context;
    private Point screen;

    public UserInterface(Context context, Point size) {
        this.context = context;
        this.screen = size;
    }

    public void draw(Canvas mCanvas, Paint mPaint) {
        // mPaint.setColor(100);
        RectF UIBar = new RectF(100,100,100,100);
        mPaint.setColor(Color.BLUE);
        mCanvas.drawRect(UIBar, mPaint);
    }
}
