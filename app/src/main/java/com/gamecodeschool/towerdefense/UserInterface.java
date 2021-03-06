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
    private int numLives, gold;
    private boolean paused;

    public UserInterface(int lives, int gold, boolean paused) {
        this.numLives = lives;
        this.gold = gold;
        this.paused = paused;
    }

    public void draw(Canvas mCanvas, Paint mPaint, int lives, int gold, boolean mPaused, boolean gameOver, int waveNumber) {
        height = mCanvas.getHeight();
        width = mCanvas.getWidth();


        // Draw rectangle on right 20% of screen for UI
        mPaint.setColor(Color.WHITE);
        mCanvas.drawRect(width, height, (float) (width * 0.8), 0, mPaint);
        mPaint.setTextSize(40);
        mPaint.setColor(Color.BLACK);
        if(lives > 0 && !gameOver) {
            drawLivesAndGold(mCanvas, mPaint, lives, gold, waveNumber);
        }
        else {
            drawNewGameText(mCanvas, mPaint);
        }
        drawPauseButton(mCanvas, mPaint, mPaused);

        // Build basic tower
        drawTowerBuilder(mCanvas, mPaint);

    }

    private void drawNewGameText(Canvas mCanvas, Paint mPaint) {
        mPaint.setColor(Color.DKGRAY);
        //mCanvas.drawRect((float) (width * 0.82), (float) (height * 0.72), (float) (width * 0.98), (float) (height * 0.84), mPaint);
        mPaint.setColor(Color.WHITE);
        mPaint.setTextSize(50);
        mCanvas.drawText("Game Over! Click anywhere to reset!", (float) (mCanvas.getWidth() * 0.3), (float) (mCanvas.getHeight() * 0.8), mPaint);
    }

    public void drawTowerBuilder(Canvas mCanvas, Paint mPaint) {
        // MG Tower Button
        mPaint.setColor(Color.DKGRAY);
        mCanvas.drawRect((float) (width * 0.82), (float) (height * 0.72), (float) (width * 0.98), (float) (height * 0.84), mPaint);
        mPaint.setColor(Color.WHITE);
        mPaint.setTextSize(40);
        mCanvas.drawText("MG Tower", (float) (mCanvas.getWidth() * 0.84), (float) (mCanvas.getHeight() * 0.77), mPaint);
        mCanvas.drawText("200 Gold", (float) (mCanvas.getWidth() * 0.84), (float) (mCanvas.getHeight() * 0.81), mPaint);

        // SG Tower Button
        mPaint.setColor(Color.GRAY);
        mCanvas.drawRect((float) (width * 0.82), (float) (height * 0.58), (float) (width * 0.98), (float) (height * 0.70), mPaint);
        mPaint.setColor(Color.WHITE);
        mPaint.setTextSize(40);
        mCanvas.drawText("SG Tower", (float) (mCanvas.getWidth() * 0.84), (float) (mCanvas.getHeight() * 0.63), mPaint);
        mCanvas.drawText("150 Gold", (float) (mCanvas.getWidth() * 0.84), (float) (mCanvas.getHeight() * 0.67), mPaint);

        // S Tower Button
        mPaint.setColor(Color.LTGRAY);
        mCanvas.drawRect((float) (width * 0.82), (float) (height * 0.44), (float) (width * 0.98), (float) (height * 0.56), mPaint);
        mPaint.setColor(Color.WHITE);
        mPaint.setTextSize(40);
        mCanvas.drawText("Sniper Tower", (float) (mCanvas.getWidth() * 0.84), (float) (mCanvas.getHeight() * 0.49), mPaint);
        mCanvas.drawText("100 Gold", (float) (mCanvas.getWidth() * 0.84), (float) (mCanvas.getHeight() * 0.53), mPaint);
    }

    // Deals with the pause button and the toggle feature
    public void drawPauseButton(Canvas mCanvas, Paint mPaint, boolean mPaused) {
        mPaint.setColor(Color.BLACK);
        mCanvas.drawRect((float) (width * 0.82), (float) (height * 0.86), (float) (width * 0.98), (float) (height * 0.98), mPaint);
        mPaint.setColor(Color.WHITE);
        mPaint.setTextSize(100);
        if (mPaused) {
            mCanvas.drawText("Play", (float) (mCanvas.getWidth() * 0.84), (float) (mCanvas.getHeight() * 0.95), mPaint);
        } else {
            mCanvas.drawText("Pause", (float) (mCanvas.getWidth() * 0.84), (float) (mCanvas.getHeight() * 0.95), mPaint);
        }
    }

    public void drawLivesAndGold(Canvas mCanvas, Paint mPaint, int lives, int gold, int waveNumber) {
        mCanvas.drawText("Lives: " + lives, (float) (width * 0.82), (float) (height * 0.05), mPaint);
        mCanvas.drawText("Gold: " + gold, (float) (width * 0.82), (float) (height * 0.1), mPaint);
        mCanvas.drawText("Wave Number: " + waveNumber, (float) (width * 0.82), (float) (height * 0.15), mPaint);
    }

}
