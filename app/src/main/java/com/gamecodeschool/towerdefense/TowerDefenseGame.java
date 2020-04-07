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
    private Grid grid;
    private Canvas mCanvas;
    private SurfaceHolder mSurfaceHolder;
    private Paint mPaint;


    // Attributes to deal with start/pause/UI
    private UserInterface mUserInterface;
    private boolean mStarted;
    private boolean mPlaying;
    private boolean mPaused;
    private boolean buildingMGTower;

    // List of GameObjects
    private int currentWaveNumber;
    private List<Tower> listOfTowers = new ArrayList<Tower>();
    private ArrayList<Enemy> currentWaveOfEnemies = new ArrayList<Enemy>();

    //private List<Tower> listOfTowers;
    private ArrayList<ArrayList<Enemy>> waveOfEnemies = new ArrayList<ArrayList<Enemy>>();




    public TowerDefenseGame(Context context, Point size) {
        super(context);
        this.context = context;

        this.grid = new Grid(size);


        // TODO: Add Sound Strategy Later

        // TODO: Insert Game Objects Initialization Here
        this.gameMap = new HardMap(mCanvas);

        mUserInterface = new UserInterface();

        listOfTowers = new ArrayList<Tower>();



        // Initialize the drawing objects for the visuals of the game
        mSurfaceHolder = getHolder();
        mPaint = new Paint();

    }

    /* Builds the waveOfEnemiesList. Looks something like:
    Wave 1: Basic Alien, Basic Alien, Basic Alien, Basic Alien, Basic Alien
    Wave 2: Basic Alien, Basic Alien, Mid-Grade Alien, Basic Alien, Basic Alien
    etc. */
    private void getCurrentWaveOfEnemies() {
        this.currentWaveOfEnemies = gameMap.getCurrentWaveOfEnemies(currentWaveNumber);
    }

    // Called to start a new game
    public void newGame() {
        mStarted = true;
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
        while (mPlaying) {
            this.currentWaveOfEnemies = gameMap.getCurrentWaveOfEnemies(currentWaveNumber);
            if(!mPaused) {
                // Update 10 times a second
                if (updateRequired()) {
                    update();
                }
            }
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
        for(Enemy enemy: currentWaveOfEnemies) {
            enemy.move();
        }

        for(Tower tower: listOfTowers) {
            tower.attack();
        }

        // TODO: If enemy reached the end of the static path, decrement userLives


        // TODO: If we ran out of Lives, then the user loses. End Game
        mStarted = false;

    }

    // Do all the drawing (interface) for the application
    public void draw() {
        // Lock the canvas, do a drawing, and at the end, present it
        if (mSurfaceHolder.getSurface().isValid()) {
            mCanvas = mSurfaceHolder.lockCanvas();

            // TODO: Draw the Grid
            grid.draw(mCanvas, mPaint);

            // TODO: Draw the Path
            gameMap.draw(mCanvas);

            // TODO: Make the background space-themed
            // Fill the screen with a color
            mCanvas.drawColor(Color.argb(50, 26, 128, 100));
            // Set the size and color of the mPaint for the text
            mPaint.setColor(Color.argb(255, 255, 255, 255));
            mPaint.setTextSize(120);

            // Draw the UI with number of Lives left, pause button
            mUserInterface.draw(mCanvas, mPaint, lives, gold, mPlaying);


            /*
            // TODO: Draw every tower. (Towers are to be stored in an ArrayList<Tower> . This forloop utilizes polymorphism to print all)
            for(Tower t: listOfTowers) {
                t.draw(mCanvas, mPaint);
            */
            int waveNumber = 1; // Will make this a variable later

            // TODO: Draw every tower. (Towers are to be stored in an ArrayList<Tower> . This for loop utilizes polymorphism to print all)
            for(Tower t: listOfTowers) {
                t.draw(mCanvas, mPaint);
            }

            /*
            // TODO: Draw the enemies
            for(Enemy enemy: currentWaveOfEnemies) {
                enemy.draw(mCanvas, mPaint);
            }

            }*/
            // TODO: Draw the text for when the game is paused


            // Unlock the mCanvas and reveal the graphics for this frame
            mSurfaceHolder.unlockCanvasAndPost(mCanvas);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent motionEvent) {
        float x = motionEvent.getX();
        float y = motionEvent.getY();

        // Check if pause button was pressed
        if ((x > (mCanvas.getWidth() * 0.82) && x < (mCanvas.getWidth() * 0.98)) && (y > (mCanvas.getHeight() * 0.86) && y < (mCanvas.getHeight() * 0.98))) {
            if (!mStarted) {
                newGame();
                mPlaying = true;
            } else {
                if (mPlaying) {
                    pause();
                    mPlaying = false;
                } else if (!mPlaying) {
                    resume();
                    mPlaying = true;
                }
            }
        // Check if Build tower button is pressed
        } else if ((x > (mCanvas.getWidth() * 0.82) && x < (mCanvas.getWidth() * 0.98)) && (y > (mCanvas.getHeight() * 0.72) && y < (mCanvas.getHeight() * 0.84))) {
            if (buildingMGTower) {
                buildingMGTower = false;
                System.out.println("Cancelled building tower");
            } else {
                buildingMGTower = true;
                System.out.println("Building tower");
            }
        }

        if (buildingMGTower) {
            if (x < mCanvas.getWidth() * 0.82) {
                // if building tower and tapping in green area of map, tower is created
                if (y < ((mCanvas.getHeight() / 2.0) - 40) || y > ((mCanvas.getHeight() / 2.0) + 40)) {
                    // Build new tower
                    listOfTowers.add(new MachineGunTower(new Point((int) x, (int) y)));
                    System.out.println("Tower built");
                    buildingMGTower = false;
                }
            }
        }

        return true;
    }

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