package com.mygdx.game;

import com.mygdx.game.Weapons.MachineGun;

/**
 * Created by Joshua on 5/19/2017.
 */

public class PlayerShip extends Ship {

    private static int startingHealth = 5;

    private static float secondsToCrossScreen = 4.2f;
    private float speed;

    private enum DIRECTION{LEFT, RIGHT, FORWARD};
    private DIRECTION dir;

    private static final String imagePath = "playership.png";

    public PlayerShip(float WORLD_WIDTH, float WORLD_HEIGHT){
        super(WORLD_WIDTH, WORLD_HEIGHT, imagePath, new MachineGun(), startingHealth);

        speed = WORLD_WIDTH/secondsToCrossScreen;

        //set the starting location in the center, up off the bottom by a small margin
        pos.set(WORLD_WIDTH/2 - getWidth(), WORLD_HEIGHT/100);

        weapon = new MachineGun();
        dir = DIRECTION.FORWARD;
    }

    public void goingLeft(){
        dir = DIRECTION.LEFT;
    }
    public void goingRight(){
        dir = DIRECTION.RIGHT;
    }
    public void goingForward(){
        dir = DIRECTION.FORWARD;
    }

    public void move(float delta){

        switch (dir){
            case FORWARD:
                break;
            case LEFT:
                moveLeft(delta);
                break;
            case RIGHT:
                moveRight(delta);
                break;
        }

    }

    private void moveLeft(float delta){

        float nextPos = getLeft() - speed * delta;

        if (nextPos <= 0) nextPos = 0;

        setX(nextPos);
    }

    private void moveRight(float delta){

        float nextPos = getLeft() + speed * delta;
        float nextRight = nextPos + getWidth();

        if (nextRight >= WORLD_WIDTH){
            nextPos = WORLD_WIDTH - getWidth();
        }
        setX(nextPos);
    }

    @Override
    public void hit(float damage) {
        //TODO
    }
}
