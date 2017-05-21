package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;

/**
 * Created by Joshua on 5/21/2017.
 */

public class Bullet {

    private final String imageName = "bullet.png";

    private Sprite sprite;

    private float speed;

    public Bullet(float x, float y){

        sprite = new Sprite(new Texture(imageName));

        sprite.setPosition(x, y);

        speed = 50;

    }

    public void move(float delta){

        sprite.setY(sprite.getY() + speed * delta);

    }

    public void draw(Batch batch){

        sprite.draw(batch);

    }

}
