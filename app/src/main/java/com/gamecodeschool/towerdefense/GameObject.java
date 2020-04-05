package com.gamecodeschool.towerdefense;

import android.graphics.Point;

abstract class GameObject {

    private float size;
    private Point location;

    public GameObject(Point initialLocation, float initialSize) {
        size = initialSize;
        location = initialLocation;
    }


    public float getSize() {
        return size;
    }

    public Point getLocation() {
        return location;
    }

    void setLocation(Point newLocation) {
        this.location = newLocation;
    }
}
