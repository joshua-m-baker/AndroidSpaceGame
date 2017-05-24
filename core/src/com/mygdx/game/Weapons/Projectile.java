package com.mygdx.game.Weapons;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by Joshua on 5/21/2017.
 */

public abstract class Projectile{

    private Sprite sprite;

    protected float speed;
    protected int damage;

    protected boolean alive;

    protected Vector2 pos;

    public Projectile(float x, float y, String imageName){

        sprite = new Sprite(new Texture(imageName));

        //sprite.setPosition(x, y);
        pos = new Vector2(x, y);
        alive = true;
    }

    public void move(float delta){

        pos.y = getY() + speed * delta;

    }

    public void draw(Batch batch){

        batch.draw(sprite, pos.x, pos.y);
        //sprite.draw(batch);

    }

    public int getDamage(){
        return damage;
    }

    public float getY(){
        return pos.y;
    }

    public Rectangle getRect(){
        sprite.setPosition(pos.x, pos.y);
        return sprite.getBoundingRectangle();
    }

}
