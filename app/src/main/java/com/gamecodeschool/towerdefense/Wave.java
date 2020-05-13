package com.gamecodeschool.towerdefense;

import android.content.Context;
import android.graphics.Point;
import android.location.Location;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Wave {

    private Context context;
    private Point location;


  //  ArrayList<ArrayList<Enemy>> listOfEnemies = new ArrayList<ArrayList<Enemy>>();

    public Wave(Point location, Context context) {
        this.location = location;
        this.context = context;
 //       setupWaves();
    }

   // public ArrayList<Enemy> getEnemies(int waveNumber) {
     //   return listOfEnemies.get(waveNumber);
    //}

    public ArrayList<Enemy> getRandomWave() {
        Random rand = new Random();
        Point start = new Point(0, location.y/2);
        ArrayList<Enemy> randomWave = new ArrayList<Enemy>();

        for(int i = 0; i < 30; i++) {
            int randomNumber = rand.nextInt(100);
            if(randomNumber <= 70) {
                randomWave.add(new BasicAlien(start, context));
            }
            else if(randomNumber > 70 && randomNumber < 90) {
                randomWave.add(new AlienSpaceship(start, context));
            }
            else {
                randomWave.add(new SupremeAlien(start, context));
            }
        }

        for(int i = 1; i < randomWave.size(); i++) {
            Enemy e = randomWave.get(i);
            e.setLocation(new Point(e.getLocation().x-(i*100), e.getLocation().y));
        }

       return randomWave;
    }
}
