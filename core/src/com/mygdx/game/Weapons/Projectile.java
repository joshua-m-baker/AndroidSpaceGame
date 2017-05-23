package com.mygdx.game.Weapons;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;

/**
 * Created by Joshua on 5/21/2017.
 */

public abstract class Projectile{

    private Sprite sprite;

    protected float speed;
    protected int damage;

    protected boolean alive;

    public Projectile(float x, float y, String imageName){

        sprite = new Sprite(new Texture(imageName));

        sprite.setPosition(x, y);
        alive = true;
    }

    public void move(float delta){

        sprite.setY(sprite.getY() + speed * delta);

    }

    public void draw(Batch batch){

        sprite.draw(batch);

    }

    public int getDamage(){
        return damage;
    }

    public float getY(){
        return sprite.getY();
    }

    public Rectangle getRect(){
        return sprite.getBoundingRectangle();
    }

}
