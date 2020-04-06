package com.gamecodeschool.towerdefense;

// The most basic enemy in the 'Invasion Earth' Model

import android.graphics.Point;

public class BasicAlien extends Enemy {

    public BasicAlien(Point location) {
        super(location);
        this.hp = 100;
        this.speed = 5;
        this.alive = true;
    }

    public void move() {
        super.move();
    }
}
