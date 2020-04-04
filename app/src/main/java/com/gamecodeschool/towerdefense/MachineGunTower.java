package com.gamecodeschool.towerdefense;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;

public class MachineGunTower extends Tower {

    public MachineGunTower(Point location) {
        super(location);
        this.damage = 50;
        this.range = 20;
        this.speed = 20;
    }

    void draw(Canvas canvas, Paint paint) {
        canvas.drawBitmap(mBitmap,
                location.x * mSize, location.y * mSize, paint);
    }

    // Send a new projectile in the air
    void attack() {
        Projectile projectile = new Projectile(location, damage, range, speed);
        this.listOfProjectiles.add(projectile);
        updateProjectile();
    }

    private void updateProjectile() {
        for(Projectile projectile: listOfProjectiles) {
            projectile.move();
        }
    }
}
