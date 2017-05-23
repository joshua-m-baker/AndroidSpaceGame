package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.Weapons.Bullet;
import com.mygdx.game.Weapons.Projectile;
import com.mygdx.game.Weapons.Weapon;

import java.util.ArrayList;

/**
 * Created by Joshua on 5/22/2017.
 */

public abstract class Ship {

    protected final float WORLD_WIDTH;
    protected final float WORLD_HEIGHT;

    protected Sprite sprite;

    protected int health;

    protected Weapon weapon;

    public Ship(float WORLD_WIDTH, float WORLD_HEIGHT, String imagePath, Weapon weapon, int health){

        this.WORLD_HEIGHT =  WORLD_HEIGHT;
        this.WORLD_WIDTH = WORLD_WIDTH;

        this.sprite = new Sprite(new Texture(imagePath));
        this.weapon = weapon;
        this.health = health;

    }

    public void draw(Batch batch){

        sprite.draw(batch);

    }

    public ArrayList<Projectile> fire(float delta){

        return weapon.fire(delta, sprite.getX(), sprite.getY());

    }

    public void setX(int x){
        sprite.setX(x);
    }

    public void setY(int y){
        sprite.setY(y);
    }

    public float getLeft(){ return sprite.getX(); }

    public float getRight(){ return sprite.getX() + sprite.getWidth(); }

    public float getX(){
        return sprite.getX();
    }

    public float getY(){
        return sprite.getY();
    }

    public int getHealth(){
        return health;
    }

    public Rectangle getRect(){
        return sprite.getBoundingRectangle();
    }


    public abstract void hit(float damage);

}
