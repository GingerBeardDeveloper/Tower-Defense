package com.gamecodeschool.towerdefense;

abstract class Enemy extends MoveableObject {

    private double hp;
    // Armor should be a value between 0 and 1 that reduces damage from sources
    private double armor;
    // used for a quick check to tell if enemy should be removed from the GameWorld
    private boolean alive;

    /**
     *
     * @param damageTaken determined by the source of the damage
     */
    public void takeDamage(double damageTaken) {
        hp -= damageTaken - (damageTaken * armor);
        if (hp <= 0) {
            alive = false;
        }
    }

    /**
     *
     * @param healthRestored determined by either the enemy or area of effect from healer class
     */
    public void heal(double healthRestored) {
        hp += healthRestored;
    }

    public boolean isAlive() {
        return alive;
    }
}
