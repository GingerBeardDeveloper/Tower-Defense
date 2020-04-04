package com.gamecodeschool.towerdefense;

import android.view.SurfaceView;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.os.Build;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import java.util.ArrayList;



class TowerDefenseGame extends SurfaceView implements Runnable {

    // Objects for the game loop/thread
    private Thread mThread = null;
    private long mNextFrameTime;

    // Attributes for pixels of the game
    Context context;
    int blockSize;
    private int mNumBlocksHigh;
    private final int NUM_BLOCKS_WIDE = 40;

    // Objects for drawing
    private Canvas mCanvas;
    private SurfaceHolder mSurfaceHolder;
    private Paint mPaint;


    // Attributes to deal with start/pause
    private boolean mPlaying;
    private boolean mPaused;


    public TowerDefenseGame(Context context, Point size) {
        super(context);
        this.context = context;

        //Deals with the pixels for our mobile application
        // Work out how many pixels each block is
        blockSize = size.x / NUM_BLOCKS_WIDE;
        // How many blocks of the same size will fit into the height
        mNumBlocksHigh = size.y / blockSize;

        // TODO: Add Sound Strategy Later

        // TODO: Insert Game Objects Initialization Here


        // Initialize the drawing objects for the visuals of the game
        mSurfaceHolder = getHolder();
        mPaint = new Paint();

    }

    // Called to start a new game
    public void newGame() {


    }


    // Handles the game loop
    @Override
    public void run() {
        /*while (mPlaying) {
            if(!mPaused) {
                // Update 10 times a second
                if (updateRequired()) {
                    update();
                }
            }
            draw();
        }*/
        while (mPlaying) {
            draw();
        }
    }

    // Check to see if it is time for an update
    public boolean updateRequired() {
        // Run at 10 frames per second
        final long TARGET_FPS = 10;
        // There are 1000 milliseconds in a second
        final long MILLIS_PER_SECOND = 1000;

        // Are we due to update the frame...Tenth of a second has passed
        if (mNextFrameTime <= System.currentTimeMillis()) {
            // Setup when the next update will be triggered
            mNextFrameTime = System.currentTimeMillis()
                             + MILLIS_PER_SECOND / TARGET_FPS;

            // Return true so that the update and draw methods execute
            return true;
        }
        return false;
    }

    // Purpose of this method is to make all of the movable objects move
    // After moving all objects, checks to see if gold is earned, or if specific events occur
    public void update() {

        // TODO: Make all enemies move

        // TODO: If enemy reached the end, lower amt of userLives by 1



        // TODO: If we ran out of Lives, then the user loses. End Game


    }

    // Do all the drawing (interface) for the application
    public void draw() {
        // Lock the canvas, do a drawing, and at the end, present it
        if (mSurfaceHolder.getSurface().isValid()) {
            mCanvas = mSurfaceHolder.lockCanvas();

            // Fill the screen with a color
            mCanvas.drawColor(Color.argb(255, 26, 128, 182));

            // Set the size and color of the mPaint for the text
            mPaint.setColor(Color.argb(255, 255, 255, 255));
            mPaint.setTextSize(120);

            // TODO: Draw the number of Lives left, the score
            //mCanvas.drawText("" + mScore, 20, 120, mPaint);

            // TODO: Draw the enemies
           /*
            mApples.get(0).draw(mCanvas, mPaint);
            for (int i = 0; i < mApples.size(); i++) {
                mApples.get(i).draw(mCanvas, mPaint);
            }
            mSnake.draw(mCanvas, mPaint);
            */

            // TODO: Draw the text for when the game is paused


            // Unlock the mCanvas and reveal the graphics for this frame
            mSurfaceHolder.unlockCanvasAndPost(mCanvas);
        }
    }

   /* @Override
    public boolean onTouchEvent(MotionEvent motionEvent) {

        return true;
    }
    */

    // Stop the thread
    public void pause() {
        mPlaying = false;
        try {
            mThread.join();
        } catch (InterruptedException e) {
            // Error
        }
    }


    // Start the thread
    public void resume() {
        mPlaying = true;
        mThread = new Thread(this);
        mThread.start();
    }
}