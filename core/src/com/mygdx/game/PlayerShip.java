package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;

import java.util.ArrayList;

/**
 * Created by Joshua on 5/19/2017.
 */

public class PlayerShip {

    private final String imagePath = "playership.png";

    //private final int screenWidth;
    //private final int screenHeight;

    private final float WORLD_WIDTH;
    private final float WORLD_HEIGHT;

    private Sprite sprite;

    private float speed;

    public boolean hasFired;
    public float fireDelay;

    private float fireTimer;

    private int leftGunX;
    private int leftGunY;
    private int rightGunX;
    private int rightGunY;

    //private int xPos;
    //private int yPos;



    public PlayerShip(float WORLD_WIDTH, float WORLD_HEIGHT){

        this.WORLD_HEIGHT = WORLD_HEIGHT;
        this.WORLD_WIDTH = WORLD_WIDTH;


        //number of seconds to cross screen
        float seconds = 3;

        speed = WORLD_WIDTH/seconds;

        hasFired = false;
        fireDelay = 1;
        fireTimer = 0;

        leftGunX = 3;
        leftGunY = 15;

        rightGunX = 19;
        rightGunY = 15;

        sprite = new Sprite(new Texture(imagePath));

        sprite.setPosition(WORLD_WIDTH/2 - sprite.getWidth(), 3);
    }

    public void moveLeft(float delta){

        float nextPos = getLeft() - speed * delta;

        if (nextPos <= 0) nextPos = 0;

        sprite.setX(nextPos);
    }

    public void moveRight(float delta){

        float nextPos = getLeft() + speed * delta;
        float nextRight = nextPos + sprite.getWidth();

        if (nextRight >= WORLD_WIDTH){
            nextPos = WORLD_WIDTH - sprite.getWidth();
        }

        sprite.setX(nextPos);
    }

    public void draw(Batch batch){

        sprite.draw(batch);

    }

    public ArrayList<Bullet> fire(float delta){

        ArrayList<Bullet> bullets = new ArrayList<Bullet>();

        fireTimer += delta;

        if (fireTimer >= fireDelay){
            fireTimer -= fireDelay;
            bullets.add(new Bullet(sprite.getX() + leftGunX, sprite.getY() + leftGunY));
            bullets.add(new Bullet(sprite.getX() + rightGunX, sprite.getY() + rightGunY));
            return bullets;
        }

        return null;

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
