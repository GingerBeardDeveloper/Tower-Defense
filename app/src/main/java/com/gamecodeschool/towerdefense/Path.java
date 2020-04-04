package com.gamecodeschool.towerdefense;

// This is the static path that all enemies will follow
// It will get the canvas, and paint, and will draw a path onto it

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;

public class Path {

    private Context context;
    private Canvas canvas;
    private Paint paint;

    public Path(Context context, Canvas mCanvas, Paint mPaint) {
        this.context = context;
        this.canvas = mCanvas;
        this.paint = mPaint;
    }

    // Uses the canvas and paint to draw a static path onto the screen
    public void drawPath() {
        paint.setColor(Color.BLACK);

        canvas.drawText("INSERT PATH", 150, 700, paint);
    }

}
