package com.gamecodeschool.towerdefense;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;

import java.util.ArrayList;

public class MachineGunTower extends Tower {

    ArrayList<Projectile> projectiles = new ArrayList<Projectile>();

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

    // Send a new projectile in the air
    void attack(Canvas mCanvas, Paint mPaint) {
      /*  Projectile projectile = new Projectile(this.location, this.damage,this.range, this.speed, 5);
        projectiles.add(projectile);

        if(!projectiles.isEmpty()) {
            for(Projectile p: projectiles) {
                p.move();
                p.draw(mCanvas, mPaint);
            }
        }
        */
    }
}
