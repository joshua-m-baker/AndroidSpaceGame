package com.mygdx.game.Weapons;

import java.util.ArrayList;

/**
 * Created by Joshua on 5/21/2017.
 */

public abstract class Weapon<T> {

    private Projectile projectileType;

    protected ArrayList<int[]> gunLocations;

    public abstract ArrayList<Projectile> fire(float delta, float x, float y);

}
