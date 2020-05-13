package com.gamecodeschool.towerdefense;

import android.content.res.AssetManager;

// Strategy that deals with preparing the proper sound settings
public interface SoundStrategy {
    public void prepareSounds(AssetManager assetManager);
    public void playEatAppleSound();
    public void playDeathSound();
}
