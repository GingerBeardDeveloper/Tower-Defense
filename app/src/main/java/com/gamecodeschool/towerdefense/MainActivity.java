package com.gamecodeschool.towerdefense;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Get the pixel dimensions of the screen
        Display display = getWindowManager().getDefaultDisplay();

        // Initialize the result into a Point object
        Point size = new Point();
        display.getSize(size);

        // Create a new instance of the TowerGame class
        mTowerGame = new TowerGame(this, size);

        // Make the towerDefense the view of the Activity
        setContentView(R.layout.activity_main);
    }
}
