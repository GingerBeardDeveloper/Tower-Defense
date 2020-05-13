package com.gamecodeschool.towerdefense;

// Allows our game to support different versions of android
public class SoundContext {
    private SoundStrategy strategy;

    public void setSoundStrategy(SoundStrategy strategy) {
        this.strategy = strategy;
    }

    public SoundStrategy getStrategy() {
        return strategy;
    }
}
