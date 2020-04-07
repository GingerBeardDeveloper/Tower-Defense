package com.gamecodeschool.towerdefense;

import android.graphics.Canvas;
import android.graphics.Point;

public class HardMap extends Map {

    public HardMap(Canvas mCanvas) {
        start = new Point(mCanvas.getWidth(), mCanvas.getHeight() / 2);
        end = new Point((int) (mCanvas.getWidth() * 0.2),mCanvas.getHeight() / 2);
    }
}
