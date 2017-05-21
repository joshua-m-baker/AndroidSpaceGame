package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;

/**
 * Created by Joshua on 5/19/2017.
 */

public class PlayerShip {

    private final String imagePath = "playership.png";

    //private final int screenWidth;
    //private final int screenHeight;

    private final int WORLD_WIDTH;
    private final int WORLD_HEIGHT;

    private Sprite sprite;

    private float speed;

    //private int xPos;
    //private int yPos;



    public PlayerShip(int WORLD_WIDTH, int WORLD_HEIGHT){

        //this.screenWidth = screenWidth;
        //this.screenHeight = screenHeight;

        //System.out.println(screenHeight);

        //int targetSize = screenHeight/9;

        this.WORLD_HEIGHT = WORLD_HEIGHT;
        this.WORLD_WIDTH = WORLD_WIDTH;

        speed = 2;

        sprite = new Sprite(new Texture(imagePath));

        //sprite.setScale(targetSize);
        //sprite.setSize(targetSize, targetSize);
        //sprite.setX(screenWidth/2);
        sprite.setPosition(0,0);
    }

    public void moveLeft(){

        float nextPos = getLeft() - speed;

        if (nextPos <= 0) nextPos = 0;

        sprite.setX(nextPos);
    }

    public void moveRight(){

        float nextPos = getLeft() + speed;
        float nextRight = getRight() + speed;

        System.out.println("World Width: " + WORLD_WIDTH);
        System.out.println("Sprite WidthL: " + sprite.getWidth());
        System.out.println("Sprite left: " + getLeft());

        if (nextRight >= WORLD_WIDTH){
            nextPos = WORLD_WIDTH - sprite.getWidth();
        }

        sprite.setX(nextPos);
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
