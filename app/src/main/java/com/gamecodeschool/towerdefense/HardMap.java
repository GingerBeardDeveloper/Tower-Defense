package com.gamecodeschool.towerdefense;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;

import java.util.ArrayList;
import java.util.List;

public class HardMap extends Map {

    Paint pathPaint;

    public HardMap(Canvas mCanvas) {
        pathPaint = new Paint();
        pathPaint.setColor(Color.GRAY);
    }

    public void draw(Canvas mCanvas) {
        mCanvas.drawRect(0, (float) (mCanvas.getHeight() / 2 + 40),
                (float) (mCanvas.getWidth() * 0.8), (float) (mCanvas.getHeight() / 2 - 40), pathPaint);
    }

}
