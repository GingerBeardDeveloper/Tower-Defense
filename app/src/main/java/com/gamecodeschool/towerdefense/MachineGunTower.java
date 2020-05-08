package com.gamecodeschool.towerdefense;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.content.Context;

import java.util.ArrayList;

public class MachineGunTower extends Tower {

    ArrayList<MachineGunBullets> bullets = new ArrayList<MachineGunBullets>();
    Context context;

    public MachineGunTower(Point location) {
        super(location);
        this.damage = 50;
        this.range = 20;
        this.speed = 20;
    }

    @Override
    void draw(Canvas mCanvas, Paint mPaint) {
        // mCanvas.drawBitmap(mBitmap, location.x * mSize, location.y * mSize, mPaint);
        mPaint.setColor(Color.CYAN);
        mCanvas.drawRect(location.x - 40, location.y - 40, location.x + 40, location.y + 40, mPaint);
    }


    public void spawnBullet(ArrayList<Projectile> listOfBullets) {
       listOfBullets.add(new MachineGunBullets(location, damage, range, speed));
    }

}
