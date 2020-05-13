package com.gamecodeschool.towerdefense;

import android.graphics.Canvas;
import android.graphics.Point;

import java.util.ArrayList;

abstract class Map {

    ArrayList<Point> path;
    Point start, end;
    int heading;

    abstract void draw(Canvas mCanvas);

    public abstract ArrayList<Enemy> getCurrentWaveOfEnemies(int waveNumber);
}
