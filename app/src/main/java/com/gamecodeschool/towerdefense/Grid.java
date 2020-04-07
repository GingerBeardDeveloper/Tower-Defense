package com.gamecodeschool.towerdefense;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.view.Display;

public class Grid {

    private Point size;
    private int numberHorizontalPixels;
    private int numberVerticalPixels;
    private int gridWidth = 20;
    private int blockSize;
    private int gridHeight;

    public Grid(Point size) {
        this.size = size;
        numberHorizontalPixels = size.x;
        numberVerticalPixels = size.y;
        blockSize = numberHorizontalPixels / gridWidth;
        gridHeight = numberVerticalPixels / blockSize;
    }

    public void draw(Canvas canvas, Paint paint) {
        paint.setColor(Color.argb(255, 0, 0, 0));

        // Draw the vertical lines of the grid
        for(int i = 0; i < gridWidth; i++){
            canvas.drawLine(blockSize * i, 0,
                    blockSize * i, numberVerticalPixels,
                    paint);
        }

        // Draw the horizontal lines of the grid
        for(int i = 0; i < gridHeight; i++){
            canvas.drawLine(0, blockSize * i,
                    numberHorizontalPixels, blockSize * i,
                    paint);
        }
    }
}
