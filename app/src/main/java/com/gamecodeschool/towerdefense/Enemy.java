package com.gamecodeschool.towerdefense;

import android.graphics.Point;

abstract class Enemy extends MoveableObject {

    protected double hp;
    protected boolean alive;
    protected int speed;
    // private double armor: may be implemented at a later stage in development

    // Enemies typically spawn at the beginning of the path
    public Enemy(Point location) {
        super(location);
    }

    /**
     *
     * @param damageTaken determined by the source of the damage
     */
    public void takeDamage(double damageTaken) {
        hp -= damageTaken;
        if (hp <= 0) {
            alive = false;
        }
    }

    /**
     *
     * @param healthRestored determined by either the enemy or area of effect from healer class
     */
    /*
    public void heal(double healthRestored) {
        hp += healthRestored;
    }
    */
    public boolean isAlive() {
        return alive;
    }
}
