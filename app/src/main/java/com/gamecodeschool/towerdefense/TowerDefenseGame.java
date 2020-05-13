package com.gamecodeschool.towerdefense;

import android.graphics.RectF;
import android.util.Log;
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

import static android.util.Log.e;


class TowerDefenseGame extends SurfaceView implements Runnable
{

    // Objects for the game loop/thread
    private Thread mThread = null;
    private long mNextFrameTime;

    private int lives, gold;
    private Map gameMap;
    private int counter;
    // Attributes for pixels of the game
    private Context context;
    private Point size;
    int blockSize;
    private int mNumBlocksHigh;
    private final int NUM_BLOCKS_WIDE = 40;
    private int waveNumber;

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
    private boolean gameOver;
    private boolean buildingMGTower;

    // List of GameObjects
    private GameWorld gameWorld;
    private int currentWaveNumber;



    public TowerDefenseGame(Context context, Point size) {
        super(context);
        this.context = context;
        this.size = size;
        this.grid = new Grid(size);
        mPaused = true;
        mStarted = false;

        // TODO: Add Sound Strategy Later

        // TODO: Insert Game Objects Initialization Here
        gameMap = new HardMap(mCanvas);
        lives = 10;
        gold = 500;
        mUserInterface = new UserInterface(lives, gold, mPaused);

        // Initialize GameWorld to contain ArrayLists of GameObjects
        gameWorld = new GameWorld();
        initializeEnemies();

        //Point start =  new Point(0, (int)(mCanvas.getHeight() * 0.5));
        //Point startPosition = new Point(0, (int)(size.y * 0.5));
        //gameWorld.enemyArrayList.clear();
        //gameWorld.enemyArrayList.add(new BasicAlien(startPosition, context));


        // Initialize the drawing objects for the visuals of the game
        mSurfaceHolder = getHolder();
        mPaint = new Paint();
    }

    // Called to start a new game
    public void newGame() {
        mStarted = true;
        mPaused = false;
        gameOver = false;
        // TODO: Reset the number of lives and gold that the user has
        lives = 10;
        gold = 500;
        waveNumber = 0;

        // TODO: Reset the whole canvas
        //initializeEnemies();

        // Forces an update to be triggered
        mNextFrameTime = System.currentTimeMillis();
    }

    private void initializeEnemies() {

        Wave wave = new Wave(size, context);
        this.gameWorld.enemyArrayList = wave.getEnemies();
    }

    // Handles the game loop
    @Override
    public void run() {
        while (mPlaying) {
            if(!mPaused) {
                // Update 10 times a second
                if (updateRequired()) {
                    //System.out.println("Updating");
                    update();
                }
            }
            draw();
        }
    }

    // Check to see if it is time for an update
    public boolean updateRequired() {
        // Run at 10 frames per second
        final long TARGET_FPS = 60;
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

    // Purpose of this method is to move all of the movable objects in the game. The 'visual' of each thing is not drawn here. Just the underlying features
    // After moving all objects, checks to see if gold is earned, or if specific events occur
    public void update() {
        counter++;
        // TODO: Make all enemies move while towers attack
        // If enemy reached the end of the static path, decrement userLives
        /*for(Enemy enemy: gameWorld.enemyArrayList.get(waveNumber)) {
            enemy.move();
            // After they move, if they reached end of path, decrement lives
            if (enemy.getLocation().x > 1440) {
                gameWorld.enemyArrayList.get(waveNumber).remove(enemy);
                lives--;
            }
        }

        // Check if there are enemies on the screen and shoot if they exist
        shootTowers();

        // Move bullets
        for(Bullet bullet: gameWorld.bulletArrayList) {
            bullet.move();
        }

        // TODO: If enemy reached the end of the static path, decrement userLives
        for (int i = 0; i < gameWorld.enemyArrayList.size(); i++) {
            if (gameWorld.enemyArrayList.get(i).getLocation().x > 1440) {
                gameWorld.enemyArrayList.remove(i);
                lives--;
        }*/
        for(int i = 0; i < gameWorld.enemyArrayList.size(); i++) {
            for(int j = 0; j < gameWorld.enemyArrayList.get(waveNumber).size(); j++) {
                Enemy e = gameWorld.enemyArrayList.get(waveNumber).get(j);
                e.move();
                if (e.getLocation().x > 1440) {
                    gameWorld.enemyArrayList.get(waveNumber).remove(e);
                    lives--;
                }
            }
        }


        // TODO: If we ran out of Lives, then the user loses. End Game
        if(lives == 0) {
            gameOver = true;
        }
    }

    // Do all the drawing (interface) for the application
    public void draw() {
        // Lock the canvas, do a drawing, and at the end, present it
        if (mSurfaceHolder.getSurface().isValid()) {
            mCanvas = mSurfaceHolder.lockCanvas();
            mCanvas.drawColor(Color.WHITE);

            // TODO: Draw the Path
            gameMap.draw(mCanvas);

            // TODO: Make the background space-themed
            // Fill the screen with a color
            mCanvas.drawColor(Color.argb(50, 26, 128, 100));
            // Set the size and color of the mPaint for the text
            mPaint.setColor(Color.argb(255, 255, 255, 255));
            mPaint.setTextSize(120);

            // Draw every tower.
            for (Tower t : gameWorld.towerArrayList) {
                t.draw(mCanvas, mPaint);
            }

            // Draw the bullets
            for (Bullet bullet : gameWorld.bulletArrayList) {
                bullet.draw(mCanvas, mPaint);
            }

            // Draw the enemies
            for (ArrayList<Enemy> list : gameWorld.enemyArrayList) {
                // TODO: Draw the enemies
                for (Enemy enemy : gameWorld.enemyArrayList.get(waveNumber)) {

                    enemy.draw(mCanvas, mPaint);
                }

                // Draw the UI with number of Lives left, pause button
                mUserInterface.draw(mCanvas, mPaint, lives, gold, mPaused, gameOver);

                // TODO: Draw the text for when the game is paused
                if (!mStarted) {
                    mCanvas.drawText("To start game, press PLAY", (float) (mCanvas.getWidth() * 0.3), (float) (mCanvas.getHeight() * 0.5), mPaint);
                }

                if (mPaused && mStarted) {
                    mCanvas.drawText("Currently Paused", (float) (mCanvas.getWidth() * 0.3), (float) (mCanvas.getHeight() * 0.5), mPaint);
                }

                if (buildingMGTower) {
                    mCanvas.drawText("Please place tower", (float) (mCanvas.getWidth() * 0.3), (float) (mCanvas.getHeight() * 0.9), mPaint);
                }

                // Unlock the mCanvas and reveal the graphics for this frame
                mSurfaceHolder.unlockCanvasAndPost(mCanvas);
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent motionEvent) {
        float x = motionEvent.getX();
        float y = motionEvent.getY();

        if(gameOver) {
            mStarted = false;
            mPaused = true;
            lives = 10;
            gameOver = false;
        }
        // button to start game, then acts as a pause/resume button
        else if (playButtonPressed(x, y)) {
            if (!mStarted) {
                newGame();
                mPlaying = true;
            }
            // functions as a pause/resume button
            else {
                if (!mPaused) {
                    mPaused = true;
                } else {
                    mPaused = false;
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
                    gameWorld.towerArrayList.add(new SniperTower(new Point((int) x, (int) y)));
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
            Log.e("Error:", "joining thread");
        }
    }

    // Start the thread
    public void resume() {
        mPlaying = true;
        mThread = new Thread(this);
        mThread.start();
    }

    private void shootTowers() {
        if (!gameWorld.enemyArrayList.isEmpty()) {
            Point enemyLocation = gameWorld.enemyArrayList.get(0).getLocation();
            for (Tower tower: gameWorld.towerArrayList) {
                if (tower instanceof MachineGunTower) {
                    double heading = Math.toDegrees(Math.atan2(enemyLocation.y - tower.location.y, enemyLocation.x - tower.location.x));
                    gameWorld.bulletArrayList.addAll(tower.shoot(heading));
                } else if ((tower instanceof ShotGunTower) && (counter % 3 == 0)) {
                    double heading = Math.toDegrees(Math.atan2(enemyLocation.y - tower.location.y, enemyLocation.x - tower.location.x));
                    gameWorld.bulletArrayList.addAll(tower.shoot(heading));
                } else if ((tower instanceof SniperTower) && (counter % 10 == 0)) {
                    double heading = Math.toDegrees(Math.atan2(enemyLocation.y - tower.location.y, enemyLocation.x - tower.location.x));
                    gameWorld.bulletArrayList.addAll(tower.shoot(heading));
                }
            }
        }
    }

    private void cleanupBullets() {
        for (Bullet bullet : gameWorld.bulletArrayList) {
            if (bullet.location.x < 0 || bullet.location.x > 1440) {
                gameWorld.bulletArrayList.remove(bullet);
            } else if (bullet.location.y < 0 || bullet.location.y > 1000) {
                gameWorld.bulletArrayList.remove(bullet);
            }
        }
    }

}