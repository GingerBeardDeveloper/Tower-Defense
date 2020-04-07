package com.gamecodeschool.towerdefense;

import android.graphics.RectF;
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
import java.util.List;


class TowerDefenseGame extends SurfaceView implements Runnable {

    // Objects for the game loop/thread
    private Thread mThread = null;
    private long mNextFrameTime;

    private int lives, gold;
    private Map gameMap;

    // Attributes for pixels of the game
    Context context;
    int blockSize;
    private int mNumBlocksHigh;
    private final int NUM_BLOCKS_WIDE = 40;

    // Objects for drawing
    private Canvas mCanvas;
    private SurfaceHolder mSurfaceHolder;
    private Paint mPaint;


    // Attributes to deal with start/pause/UI
    private UserInterface mUserInterface;
    private boolean mPlaying;
    private boolean mPaused;

    // List of GameObjects
    private int currentWaveNumber;
    private List<Tower> listOfTowers = new ArrayList<Tower>();
    private ArrayList<ArrayList<Enemy>> waveOfEnemies = new ArrayList<ArrayList<Enemy>>();




    public TowerDefenseGame(Context context, Point size) {
        super(context);
        this.context = context;

        //Deals with the pixels for our mobile application
        // Work out how many pixels each block is
        blockSize = size.x / NUM_BLOCKS_WIDE;
        // How many blocks of the same size will fit into the height
        mNumBlocksHigh = size.y / blockSize;

        //Builds the UI according to size of screen...etc
        //mUserInterface = new UserInterface(context, size);

        //Builds the waveOfEnemiesList. Looks something like:
        buildWaveOfEnemiesList();

        // TODO: Add Sound Strategy Later

        // TODO: Insert Game Objects Initialization Here
        gameMap = new HardMap(mCanvas);

        mUserInterface = new UserInterface();


        // Initialize the drawing objects for the visuals of the game
        mSurfaceHolder = getHolder();
        mPaint = new Paint();

    }

    /* Builds the waveOfEnemiesList. Looks something like:
    Wave 1: Basic Alien, Basic Alien, Basic Alien, Basic Alien, Basic Alien
    Wave 2: Basic Alien, Basic Alien, Mid-Grade Alien, Basic Alien, Basic Alien
    etc. */
    private void buildWaveOfEnemiesList() {
        Point start = new Point(0, 0);
        ArrayList<Enemy> waveOne = new ArrayList<Enemy>();
        waveOne.add(new BasicAlien(start));
        waveOne.add(new BasicAlien(start));
        waveOne.add(new BasicAlien(start));

        this.waveOfEnemies.add(waveOne);
    }

    // Called to start a new game
    public void newGame() {
        // TODO: Reset the number of lives that the user has
        lives = 10;

        // TODO: Set the user off with a specific amount of gold
        gold = 500;

        // TODO: Reset the whole canvas


        // Forces an update to be triggered
        mNextFrameTime = System.currentTimeMillis();
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

    // Purpose of this method is to move all of the movable objects in the game
    // After moving all objects, checks to see if gold is earned, or if specific events occur
    public void update() {

        // TODO: Make all enemies move while towers attack
        for(Enemy enemy: waveOfEnemies.get(currentWaveNumber)) {
            enemy.move();
        }

        for(Tower tower: listOfTowers) {
            tower.attack();
        }

        // TODO: If enemy reached the end of the static path, decrement userLives


        // TODO: If we ran out of Lives, then the user loses. End Game

    }

    // Do all the drawing (interface) for the application
    public void draw() {
        // Lock the canvas, do a drawing, and at the end, present it
        if (mSurfaceHolder.getSurface().isValid()) {
            mCanvas = mSurfaceHolder.lockCanvas();

            // TODO: Draw the Path
            //Path path = new Path(context, mCanvas, mPaint);
            //path.drawPath();
            gameMap.draw(mCanvas);


            // TODO: Make the background space-themed
            // Fill the screen with a color
            mCanvas.drawColor(Color.argb(50, 26, 128, 100));
            // Set the size and color of the mPaint for the text
            mPaint.setColor(Color.argb(255, 255, 255, 255));
            mPaint.setTextSize(120);

            // TODO: Draw the number of Lives left, the score
            mUserInterface.draw(mCanvas, mPaint, lives, gold);

            // TODO: Draw the User Interface Bar


            // TODO: Draw every tower. (Towers are to be stored in an ArrayList<Tower> . This forloop utilizes polymorphism to print all)
          /*  for(Tower t: listOfTowers) {
                t.draw(mCanvas, mPaint);
            }

            // TODO: Draw the enemies
            for(Enemy enemy: waveOfEnemies.get(currentWaveNumber)) {
                enemy.draw(mCanvas, mPaint);
            }
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