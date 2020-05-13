package com.gamecodeschool.towerdefense;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;

import java.util.ArrayList;

class SniperTower extends Tower {

    public SniperTower(Point location) {
        super(location);
        this.damage = 100;
        this.range = 20;
        this.speed = 40;
    }

    @Override
    void draw(Canvas mCanvas, Paint mPaint) {
        mPaint.setColor(Color.CYAN);
        mCanvas.drawRect(location.x - 40, location.y - 40, location.x + 40, location.y + 40, mPaint);
    }

    @Override
    ArrayList<Bullet> shoot(double heading) {
        ArrayList<Bullet> bulletsToShoot = new ArrayList<Bullet>();
        bulletsToShoot.add(new MachineGunBullets(location, heading, damage, speed));
        return bulletsToShoot;
    }
}
