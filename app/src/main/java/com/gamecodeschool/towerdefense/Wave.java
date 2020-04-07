package com.gamecodeschool.towerdefense;

public class Wave {
    private int numAliens;
    private double spawnDelay;

    public Wave(int numberOfAliens, double delayBetweenAliens) {
        numAliens = numberOfAliens;
        spawnDelay = delayBetweenAliens;
    }

    public double getSpawnDelay() {
        return spawnDelay;
    }

    public int getNumAliens() {
        return numAliens;
    }
}
