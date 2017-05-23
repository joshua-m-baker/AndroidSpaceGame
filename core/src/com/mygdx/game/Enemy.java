package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.Weapons.Projectile;

/**
 * Created by Joshua on 5/21/2017.
 */

public abstract class Enemy {

    protected Sprite sprite;
    protected int health;

    public abstract void move(float delta);
    public void draw(Batch batch){

        sprite.draw(batch);

    }

    public Rectangle getRect(){
        return sprite.getBoundingRectangle();
    }

    public void hit(Projectile p){
        health -= p.getDamage();
        if (health == 0){

        }
    }

    public int getHealth(){
        return health;
    }
}
