package com.gamecodeschool.towerdefense;

import android.content.Context;
import android.graphics.Point;
import android.location.Location;

import java.util.ArrayList;
import java.util.List;

public class Wave {

    private Context context;
    private Point location;

    // List of list of enemies:
    //Ex:
        //1: Basic Alien, Basic Alien, Basic Alien, Basic Alien, Basic Alien
        //2: Basic Alien, Basic Alien, Basic Alien, Basic Alien, Intermediate Alien
    ArrayList<ArrayList<Enemy>> listOfEnemies = new ArrayList<ArrayList<Enemy>>();

    public Wave(Point location, Context context) {
        this.location = location;
        this.context = context;
        setupWaves();
    }

    public ArrayList<Enemy> getEnemies(int waveNumber) {
        return listOfEnemies.get(waveNumber);
    }

    private void setupWaves() {
        Point start = new Point(0, location.y/2);
        ArrayList<Enemy> waveOne = new ArrayList<Enemy>();
            waveOne.add(new BasicAlien(start, context));
            waveOne.add(new BasicAlien(start, context));
            waveOne.add(new BasicAlien(start, context));
            waveOne.add(new BasicAlien(start, context));
            waveOne.add(new BasicAlien(start, context));
        for(int i = 1; i < waveOne.size(); i++) {
            Enemy e = waveOne.get(i);
            e.setLocation(new Point(e.getLocation().x-(i*100), e.getLocation().y));
        }
        /*ArrayList<Enemy> waveTwo = new ArrayList<Enemy>();
            waveTwo.add(basicAlien);
            waveTwo.add(basicAlien);
            waveTwo.add(basicAlien);
            waveTwo.add(basicAlien);
            waveTwo.add(intermediateAlien);

         */
        listOfEnemies.add(waveOne);
        //listOfEnemies.add(waveTwo);
    }
}
