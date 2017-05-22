package com.mygdx.game.Weapons;

import com.mygdx.game.Weapons.Bullet;
import java.util.ArrayList;

/**
 * Created by Joshua on 5/21/2017.
 */

public class MachineGun extends Weapon{

    Burst burst;

    private int leftGunX;
    private int leftGunY;
    private int rightGunX;
    private int rightGunY;

    public MachineGun(){
        burst = new Burst(2f, .5f, 3);

        leftGunX = 3;
        leftGunY = 15;
        rightGunX = 19;
        rightGunY = 15;

    }

    @Override
    public ArrayList<Projectile> fire(float delta, float x, float y){
        ArrayList<Projectile> shots = new ArrayList<Projectile>();
        if(burst.shouldFire(delta)==true){

            shots.add(new Bullet(x + leftGunX, y + leftGunY));
            shots.add(new Bullet(x + rightGunX, y + rightGunY));
            return shots;

        }else return null;
    }
}
