package com.mygdx.game.Weapons;

/**
 * Created by Joshua on 5/21/2017.
 */

public class Burst {

    private float fireTimer;

    private float burstDelay; //time between bursts
    private float shotDelay; //time between shots in a burst
    private int burstSize; //number of shots in a burst

    private int burstCounter;

    public Burst(float burstDelay, float shotDelay, int burstSize){
        this.shotDelay = shotDelay;
        this.burstDelay = burstDelay;
        this.burstSize = burstSize;
        this.burstCounter = 0;
        this.fireTimer = 0;
    }

    public boolean shouldFire(float delta){
        fireTimer += delta;

        if(burstCounter >= burstSize){
            burstCounter = 0;
        }

        if (burstCounter == 0){

            if (fireTimer >= burstDelay){
                fireTimer -= burstDelay;
                burstCounter += 1;
                return true;
            }

        }else{

            if(fireTimer >= shotDelay){
                fireTimer -= shotDelay;
                burstCounter += 1;
                return true;
            }

        }

        return false;
    }

}
