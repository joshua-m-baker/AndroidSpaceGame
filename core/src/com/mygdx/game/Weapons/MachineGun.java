package com.mygdx.game.Weapons;

import com.mygdx.game.Weapons.Bullet;
import java.util.ArrayList;

/**
 * Created by Joshua on 5/21/2017.
 */

public class MachineGun extends Weapon{

    Burst burst;

    private final int leftGunX = 3;
    private final int leftGunY = 15;
    private final int rightGunX = 19;
    private final int rightGunY = 15;

    public MachineGun(){
        burst = new Burst(1.5f, .35f, 3);
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
