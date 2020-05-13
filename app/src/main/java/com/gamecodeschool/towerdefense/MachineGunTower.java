package com.gamecodeschool.towerdefense;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;

import java.util.ArrayList;

public class MachineGunTower extends Tower {

    public MachineGunTower(Point location) {
        super(location);
        this.damage = 20;
        this.range = 20;
        this.speed = 6;
    }

    @Override
    void draw(Canvas mCanvas, Paint mPaint) {
        // mCanvas.drawBitmap(mBitmap, location.x * mSize, location.y * mSize, mPaint);
        mPaint.setColor(Color.DKGRAY);
        mCanvas.drawRect(location.x - 40, location.y - 40, location.x + 40, location.y + 40, mPaint);
    }

    // Send a new projectile in the air
    public ArrayList<Bullet> shoot(double heading) {
        ArrayList<Bullet> bulletsToShoot = new ArrayList<Bullet>();
        bulletsToShoot.add(new MachineGunBullets(location, heading, damage, speed));
        return bulletsToShoot;
    }

}
