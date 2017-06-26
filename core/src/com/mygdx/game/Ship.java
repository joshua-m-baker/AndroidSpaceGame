package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Weapons.Projectile;
import com.mygdx.game.Weapons.Weapon;

import java.util.ArrayList;

/**
 * Created by Joshua on 5/22/2017.
 */

public abstract class Ship {

    protected final float WORLD_WIDTH;
    protected final float WORLD_HEIGHT;

    private Sprite sprite;
    private float width;
    private float height;

    protected int health;

    protected Weapon weapon;

    protected Vector2 pos;

    public Ship(float WORLD_WIDTH, float WORLD_HEIGHT, String imagePath, Weapon weapon, int health){

        this.WORLD_HEIGHT =  WORLD_HEIGHT;
        this.WORLD_WIDTH = WORLD_WIDTH;

        System.out.println(WORLD_HEIGHT);

        this.sprite = new Sprite(new Texture(imagePath));
        width = sprite.getWidth();
        height = sprite.getHeight();

        this.weapon = weapon;
        this.health = health;

        pos = new Vector2();

    }

    public void draw(Batch batch){

        sprite.setPosition(pos.x, pos.y);
        sprite.draw(batch);

        //batch.draw(sprite, pos.x, pos.y);

    }

    public ArrayList<Projectile> fire(float delta){

        return weapon.fire(delta, getX(), getY());

    }

    public void setX(float x){
        pos.x = x;
    }

    public void setY(float y){
        pos.y = y;
    }

    public void setAngle(float degrees){
        sprite.setRotation(degrees);
    }

    public float getLeft(){ return pos.x; }

    public float getRight(){ return pos.x + getWidth(); }

    public float getX(){
        return pos.x;
    }

    public float getY(){
        return pos.y;
    }

    public int getHealth(){
        return health;
    }

    public Rectangle getRect(){

        sprite.setPosition(pos.x, pos.y);
        return sprite.getBoundingRectangle();
    }

    public float getWidth(){
        return width;
    }

    public float getHeight(){
        return height;
    }

    public abstract void hit(float damage);

    public abstract void move(float delta);

}
