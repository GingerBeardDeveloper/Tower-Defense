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
    private List<Tower> listOfTowers;
    //private ArrayList<ArrayList<Enemy>> waveOfEnemies = new ArrayList<ArrayList<Enemy>>();
    private ArrayList<Enemy> listOfEnemies;



    public TowerDefenseGame(Context context, Point size) {
        super(context);
        this.context = context;
        this.grid = new Grid(size);
        mPaused = true; //game initially paused
        mStarted = false;

        // TODO: Add Sound Strategy Later

        // TODO: Insert Game Objects Initialization Here
        gameMap = new HardMap(mCanvas);
        lives = 10;
        gold = 500;
        mUserInterface = new UserInterface(lives, gold, mPaused);

        listOfTowers = new ArrayList<Tower>();
        listOfEnemies = new ArrayList<Enemy>();

        //Point start =  new Point(0, (int)(mCanvas.getHeight() * 0.5));
        Point startPosition = new Point(0, (int)(size.y * 0.5));
        listOfEnemies.add(new BasicAlien(startPosition));
        listOfEnemies.add(new BasicAlien(startPosition));


        // Initialize the drawing objects for the visuals of the game
        mSurfaceHolder = getHolder();
        mPaint = new Paint();
    }

    // Called to start a new game
    public void newGame() {
        mStarted = true;
        // TODO: Reset the number of lives and gold that the user has
        lives = 10;
        gold = 500;

        // TODO: Reset the whole canvas

        // Forces an update to be triggered
        mNextFrameTime = System.currentTimeMillis();
    }

    // Handles the game loop
    @Override
    public void run() {
        while (mPlaying) {
            if(!mPaused) {
                // Update 10 times a second
                if (updateRequired()) {
                    System.out.println("Updating");
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

            return true;
        }
        return false;
    }

    // Purpose of this method is to move all of the movable objects in the game
    // After moving all objects, checks to see if gold is earned, or if specific events occur
    public void update() {
        // TODO: Make all enemies move while towers attack
        for(Enemy enemy: listOfEnemies) {
            enemy.move();
        }

       /* for(Tower tower: listOfTowers) {
            tower.attack(mCanvas, mPaint);
        }
*/
        // TODO: If enemy reached the end of the static path, decrement userLives

        // TODO: If we ran out of Lives, then the user loses. End Game
        //mStarted = false;
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
            mUserInterface.draw(mCanvas, mPaint);

            // TODO: Draw every tower. (Towers are to be stored in an ArrayList<Tower> . This for loop utilizes polymorphism to print all)
            for(Tower t: listOfTowers) {
                t.draw(mCanvas, mPaint);
            }

            // TODO: Draw the enemies
            for(Enemy enemy: listOfEnemies) {
                enemy.draw(mCanvas, mPaint);
            }
            // TODO: Draw the text for when the game is paused
            if(!mPlaying) {
                mCanvas.drawText("Currently Paused", (float)(mCanvas.getWidth() * 0.3), (float)(mCanvas.getHeight() * 0.5), mPaint);
            }

            // Unlock the mCanvas and reveal the graphics for this frame
            mSurfaceHolder.unlockCanvasAndPost(mCanvas);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent motionEvent) {
        float x = motionEvent.getX();
        float y = motionEvent.getY();

        // Check if pause button was pressed
        if (playButtonPressed(x, y)) {
            if (!mStarted) {
                newGame();
                mPlaying = true;
            }
            else {
                // if it's not paused, pause it, and set the pause var true
                if (!mPaused) {
                    pause();
                    mPaused = true;
                    System.out.println("Game currently Paused");
                } else {
                    resume();
                    mPaused = false;
                    System.out.println("Game currently Playing");
                }
            }
        // Check if Build tower button is pressed
        }
        else if (buildTowerButton(x, y)) {
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

    private boolean buildTowerButton(float x, float y) {
        if((x > (mCanvas.getWidth() * 0.82)
                && x < (mCanvas.getWidth() * 0.98))
                && (y > (mCanvas.getHeight() * 0.72)
                && y < (mCanvas.getHeight() * 0.84))) {
            return true;
        }
        return false;
    }

    private boolean playButtonPressed(float x, float y) {
        if ((x > (mCanvas.getWidth() * 0.82)
                && x < (mCanvas.getWidth() * 0.98))
                && (y > (mCanvas.getHeight() * 0.86)
                && y < (mCanvas.getHeight() * 0.98))) {
            return true;
        }
        return false;
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