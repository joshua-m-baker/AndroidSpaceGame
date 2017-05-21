package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by Joshua on 5/21/2017.
 */

public class AlienShip {

    private final String imagePath = "enemyship1.png";

    //private final int screenWidth;
    //private final int screenHeight;

    private final float WORLD_WIDTH;
    private final float WORLD_HEIGHT;

    private Sprite sprite;

    private float speed;
    private float xDir;

    public AlienShip(float WORLD_WIDTH, float WORLD_HEIGHT){

        this.WORLD_HEIGHT = WORLD_HEIGHT;
        this.WORLD_WIDTH = WORLD_WIDTH;

        speed = 50;
        xDir = 1;

        sprite = new Sprite(new Texture(imagePath));

        sprite.setPosition(0,WORLD_HEIGHT - sprite.getHeight());

        //sprite.setPosition(WORLD_WIDTH/2 - sprite.getWidth(), WORLD_HEIGHT - 10);
    }

    public void move(float delta){

        float nextPos = xDir * speed * delta + sprite.getX();

        if (nextPos <= 0){
            nextPos = 0;
            changeXDir();
        }

        if (nextPos + sprite.getWidth() >= WORLD_WIDTH){
            nextPos = WORLD_WIDTH - sprite.getWidth();
            changeXDir();
        }

        sprite.setX(nextPos);

    }

    public void changeXDir(){
        xDir *= -1;
    }


    public void draw(Batch batch){

        sprite.draw(batch);

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
}
