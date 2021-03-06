package com.gamecodeschool.towerdefense;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;

import java.util.ArrayList;

class ShotGunTower extends Tower {

    public ShotGunTower(Point location) {
        super(location);
        this.damage = 25;
        this.range = 20;
        this.speed = 4;
    }

    @Override
    void draw(Canvas mCanvas, Paint mPaint) {
        mPaint.setColor(Color.GRAY);
        mCanvas.drawRect(location.x - 40, location.y - 40, location.x + 40, location.y + 40, mPaint);
    }

    @Override
    ArrayList<Bullet> shoot(double heading) {
        ArrayList<Bullet> bulletsToShoot = new ArrayList<Bullet>();
        bulletsToShoot.add(new SGBullets(location, heading, damage, speed));
        bulletsToShoot.add(new SGBullets(location, heading + 10, damage, speed));
        //bulletsToShoot.add(new SGBullets(location, heading + 5, damage, speed));
        bulletsToShoot.add(new SGBullets(location, heading - 10, damage, speed));
        //bulletsToShoot.add(new SGBullets(location, heading - 5, damage, speed));
        return bulletsToShoot;
    }
}
