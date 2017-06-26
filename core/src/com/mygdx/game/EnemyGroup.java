package com.mygdx.game;

import java.util.ArrayList;

/**
 * Created by Joshua on 6/24/2017.
 */

public class EnemyGroup {

    ArrayList<AlienShip> ships;
    float delay;

    public void EnemyGroup(float delay){
        this.delay = delay;
    }

    public ArrayList<Ship> makeGroup(){
        //create the ships and return them
        return null;
    }


    public float getDelay(){
        return this.delay;
    }


}
