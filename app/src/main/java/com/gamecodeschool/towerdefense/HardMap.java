package com.gamecodeschool.towerdefense;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;

import java.util.ArrayList;
import java.util.List;

public class HardMap extends Map {

    //ArrayList<Wave> alienWaves;
    Paint pathPaint;
    private ArrayList<ArrayList<Enemy>> listOfEnemies = new ArrayList<ArrayList<Enemy>>();
    Point startOfPath;
    Point endOfPath;


    public HardMap(Canvas mCanvas) {
        //start = new Point(mCanvas.getWidth(), mCanvas.getHeight() / 2);
        //end = new Point((int) (mCanvas.getWidth() * 0.2),mCanvas.getHeight() / 2);
        pathPaint = new Paint();
        pathPaint.setColor(Color.GRAY);
      //  alienWaves = new ArrayList<Wave>();
      //  setupWaves(1);
        //startOfPath = new Point(0, (int)(mCanvas.getHeight() * 0.5));
        //endOfPath = new Point((int)(mCanvas.getWidth() * 0.8), (int)(mCanvas.getHeight() * 0.5));
    }

    public void draw(Canvas mCanvas) {
        // Rect is left, top, right, bottom
        mCanvas.drawRect(0, (float) (mCanvas.getHeight() / 2 + 40),
                (float) (mCanvas.getWidth() * 0.8), (float) (mCanvas.getHeight() / 2 - 40), pathPaint);
    }

    public ArrayList<Enemy> getCurrentWaveOfEnemies(int waveNumber) {
        return listOfEnemies.get(waveNumber);
    }

    public void setStartOfPath(Canvas mCanvas) {
        this.startOfPath = new Point(0, (int)(mCanvas.getHeight() * 0.5));
    }

    public void setEndOfPath(Canvas mCanvas) {
        this.endOfPath = endOfPath = new Point((int)(mCanvas.getWidth() * 0.8), (int)(mCanvas.getHeight() * 0.5));
    }
    // creates a predefined list of waves of aliens
    /*private void setupWaves(int numWaves) {
        for (int i = 0; i < numWaves; i++) {
            alienWaves.add(new Wave(5, 2));
        }

        for(Wave w: alienWaves) {
            ArrayList<Enemy> list = new ArrayList<Enemy>();

            for(int i = 0; i < w.getNumAliens(); i++) {
                list.add(new BasicAlien(startOfPath));
            }
            this.listOfEnemies.add(list);
        }

        spaceEnemiesApart();
    }*/

    private void spaceEnemiesApart() {
        int counter = 0;
        for(ArrayList<Enemy> wave: listOfEnemies) {
           for(Enemy e: wave) {
               // if they are the first enemy, make their x location == 0
               if(counter == 0) {
                   e.getLocation().x = 0;
                   counter++;
               }
               else {
                   e.getLocation().x = counter++ * -20;
               }
           }
        }
    }

}
