package com.gamecodeschool.towerdefense;

import android.util.Log;
import android.view.SurfaceView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.os.Build;
import android.view.MotionEvent;
import android.view.SurfaceHolder;

import androidx.annotation.RequiresApi;

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
    private boolean waveCleared;
    private boolean buildingMGTower;
    private boolean buildingSGTower;
    private boolean buildingSTower;

    // List of GameObjects
    private GameWorld gameWorld;

    public TowerDefenseGame(Context context, Point size) {
        super(context);
        this.context = context;
        this.size = size;
        this.grid = new Grid(size);
        this.waveNumber = 1;
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
        getNextWave();
        // Initialize the drawing objects for the visuals of the game
        mSurfaceHolder = getHolder();
        mPaint = new Paint();
    }

    // Called to start a new game
    public void newGame() {
        mStarted = true;
        mPaused = false;
        // TODO: Reset the number of lives and gold that the user has
        lives = 10;
        gold = 500;
        waveNumber = 0;

        // TODO: Reset the whole canvas
        if(gameOver) {
            gameWorld.bulletArrayList.clear();
            gameWorld.towerArrayList.clear();
            gameOver = false;
        }
        getNextWave();

        // Forces an update to be triggered
        mNextFrameTime = System.currentTimeMillis();
    }

    private void getNextWave() {
        WaveGenerator waveGenerator = new WaveGenerator(size, context);
        this.gameWorld.enemyArrayList = waveGenerator.getRandomWave();
    }

    // Handles the game loop
    @RequiresApi(api = Build.VERSION_CODES.N)
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

    // Purpose of this method is to move all of the movable objects in the game.
    // The 'visual' of each thing is not drawn here. Just the underlying features
    // After moving all objects, checks to see if gold is earned, or if specific events occur
    @RequiresApi(api = Build.VERSION_CODES.N)
    public void update() {
        counter++;

        collisionCheck();
        // Remove dead enemies and bullets that have collided
        gameWorld.enemyArrayList.removeIf(enemy -> !enemy.isAlive());
        gameWorld.bulletArrayList.removeIf(bullet -> bullet.hasCollided());

        // TODO: If enemy reached the end of the static path, decrement userLives
        for(int i = 0; i < gameWorld.enemyArrayList.size(); i++) {
            Enemy e = gameWorld.enemyArrayList.get(i);
            e.move();
            if (e.getLocation().x > 1440) {
                gameWorld.enemyArrayList.remove(e);
                lives--;
            }
        }

        // Check if there are enemies on the screen and shoot if they exist
        shootTowers();

        // Move bullets
        for(Bullet bullet: gameWorld.bulletArrayList) {
            bullet.move();
        }

        // TODO: If the wave is empty, increment it
        if(gameWorld.enemyArrayList.isEmpty() && lives > 0) {
            waveNumber++;
            waveCleared = true;
            mPaused = true;
            getNextWave();
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
            //for (ArrayList<Enemy> list : gameWorld.enemyArrayList) {
                // TODO: Draw the enemies
            for (Enemy enemy : gameWorld.enemyArrayList) {
                enemy.draw(mCanvas, mPaint);
            }

            // Draw the UI with number of Lives left, pause button
            mUserInterface.draw(mCanvas, mPaint, lives, gold, mPaused, gameOver);

            // TODO: Draw the text for when the game is paused
            if (!mStarted) {
                mCanvas.drawText("To start game, press PLAY", (float) (mCanvas.getWidth() * 0.3), (float) (mCanvas.getHeight() * 0.5), mPaint);
            }

            if (mPaused && mStarted && !gameOver && !waveCleared) {
                mCanvas.drawText("Currently Paused", (float) (mCanvas.getWidth() * 0.3), (float) (mCanvas.getHeight() * 0.5), mPaint);
            }

            if (buildingMGTower || buildingSGTower || buildingSTower) {
                mCanvas.drawText("Please place tower", (float) (mCanvas.getWidth() * 0.3), (float) (mCanvas.getHeight() * 0.9), mPaint);
            }

            if (waveCleared) {
                mCanvas.drawText("Wave Completed! Prepare for the next wave! ", (float) (mCanvas.getWidth() * 0.3), (float) (mCanvas.getHeight() * 0.5), mPaint);
            }

            // Unlock the mCanvas and reveal the graphics for this frame
            mSurfaceHolder.unlockCanvasAndPost(mCanvas);
        }
    }


    @Override
    public boolean onTouchEvent(MotionEvent motionEvent) {
        float x = motionEvent.getX();
        float y = motionEvent.getY();

        if(gameOver) {
            newGame();
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
        } else if (buildMGTowerButton(x, y)) {
            if (buildingMGTower) {
                buildingMGTower = false;
            } else {
                buildingMGTower = true;
            }
        } else if (buildSGTowerButton(x, y)) {
            if (buildingSGTower) {
                buildingSGTower = false;
            } else {
                buildingSGTower = true;
            }
        } else if (buildSTowerButton(x, y)) {
            if (buildingSTower) {
                buildingSTower = false;
            } else {
                buildingSTower = true;
            }
        } else if(waveCleared) {
            waveCleared = false;
            mPaused = false;
        }


        if (x < mCanvas.getWidth() * 0.82) {
            // if building tower and tapping in green area of map, tower is created
            if (y < ((mCanvas.getHeight() / 2.0) - 40) || y > ((mCanvas.getHeight() / 2.0) + 40)) {
                if (buildingMGTower) {
                    // Build new MGTower
                    if (gold >= 200) {
                        gameWorld.towerArrayList.add(new MachineGunTower(new Point((int) x, (int) y)));
                        gold -= 200;
                    }
                    buildingMGTower = false;
                } else if (buildingSGTower) {
                    // Build new SGTower
                    if (gold >= 150) {
                        gameWorld.towerArrayList.add(new ShotGunTower(new Point((int) x, (int) y)));
                        gold -= 150;
                    }
                    buildingSGTower = false;
                } else if (buildingSTower) {
                    // Build new SniperTower
                    if (gold >= 100) {
                        gameWorld.towerArrayList.add(new SniperTower(new Point((int) x, (int) y)));
                        gold -= 100;
                    }
                    buildingSTower = false;
                }

            }
        }
        return true;
    }

    private boolean buildMGTowerButton(float x, float y) {
        if((x > (mCanvas.getWidth() * 0.82) && x < (mCanvas.getWidth() * 0.98))
                && (y > (mCanvas.getHeight() * 0.72) && y < (mCanvas.getHeight() * 0.84))) {
            return true;
        }
        return false;
    }

    private boolean buildSGTowerButton(float x, float y) {
        if ((x > (mCanvas.getWidth() * 0.82) && x < (mCanvas.getWidth() * 0.98)) &&
                (y > (mCanvas.getHeight() * 0.58) && y < (mCanvas.getHeight() * 0.70))) {
            return true;
        }
        return false;
    }

    private boolean buildSTowerButton(float x, float y) {
        if ((x > (mCanvas.getWidth() * 0.82) && x < (mCanvas.getWidth() * 0.98)) &&
                (y > (mCanvas.getHeight() * 0.44) && y < (mCanvas.getHeight() * 0.56))) {
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
                if ((tower instanceof MachineGunTower) && (counter % 15 == 0)) {
                    double heading = Math.toDegrees(Math.atan2(enemyLocation.y - tower.location.y, enemyLocation.x - tower.location.x));
                    gameWorld.bulletArrayList.addAll(tower.shoot(heading));
                } else if ((tower instanceof ShotGunTower) && (counter % 30 == 0)) {
                    double heading = Math.toDegrees(Math.atan2(enemyLocation.y - tower.location.y, enemyLocation.x - tower.location.x));
                    gameWorld.bulletArrayList.addAll(tower.shoot(heading));
                } else if ((tower instanceof SniperTower) && (counter % 60 == 0)) {
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

    private void collisionCheck() {
        for (Bullet bullet : gameWorld.bulletArrayList) {
            for (Enemy enemy : gameWorld.enemyArrayList) {
                if (distance(bullet.location, enemy.location) < 20) {
                    enemy.takeDamage(bullet.getDamage());
                    bullet.collide();
                }
            }
        }
    }

    private double distance(Point a, Point b) {
        double distance = Math.sqrt(Math.pow((a.x - b.x), 2) + Math.pow((a.y - b.y), 2));
        //System.out.println(distance);
        return distance;
    }

}