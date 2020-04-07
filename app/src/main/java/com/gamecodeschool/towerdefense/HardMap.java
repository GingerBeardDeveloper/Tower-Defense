package com.gamecodeschool.towerdefense;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;

import java.util.ArrayList;

public class HardMap extends Map {

    ArrayList<Wave> alienWaves;
    Paint pathPaint;

    public HardMap(Canvas mCanvas) {
        //start = new Point(mCanvas.getWidth(), mCanvas.getHeight() / 2);
        //end = new Point((int) (mCanvas.getWidth() * 0.2),mCanvas.getHeight() / 2);
        pathPaint = new Paint();
        pathPaint.setColor(Color.GRAY);
        alienWaves = new ArrayList<Wave>();
    }

    public void draw(Canvas mCanvas) {
        mCanvas.drawRect(0, (float) (mCanvas.getHeight() / 2 + 40), (float) (mCanvas.getWidth() * 0.8), (float) (mCanvas.getHeight() / 2 - 40), pathPaint);

    }

    private void setupWaves(int numWaves) {
        for (int i = 0; i < numWaves; i++) {
            alienWaves.add(new Wave(5, 2));
        }
    }
}
