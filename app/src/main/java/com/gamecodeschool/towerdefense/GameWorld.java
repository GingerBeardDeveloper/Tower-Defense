package com.gamecodeschool.towerdefense;

import java.util.ArrayList;

class GameWorld {
    // Array lists of GameObjects
    public ArrayList<Tower> towerArrayList;
    public ArrayList<Enemy> enemyArrayList;
    public ArrayList<Bullet> bulletArrayList;

    public GameWorld() {
        towerArrayList = new ArrayList<Tower>();
        enemyArrayList = new ArrayList<Enemy>();
        bulletArrayList = new ArrayList<Bullet>();
    }

}
