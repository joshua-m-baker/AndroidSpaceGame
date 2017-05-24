package com.mygdx.game;

import com.mygdx.game.Weapons.MachineGun;

/**
 * Created by Joshua on 5/21/2017.
 */

public class AlienShip extends Ship{

    private static final String imagePath = "enemyship1.png";
    private static final int startingHealth = 1;

    private float speed;
    private float xDir;

    public AlienShip(float WORLD_WIDTH, float WORLD_HEIGHT){
        super(WORLD_WIDTH, WORLD_HEIGHT, imagePath, new MachineGun(), startingHealth);
        pos.set(0, WORLD_HEIGHT - getHeight());
        speed = 50;
        xDir = 1;

    }

    public void move(float delta){

        float nextPos = xDir * speed * delta + getX();

        if (nextPos <= 0){
            nextPos = 0;
            changeXDir();
        }

        if (nextPos + getWidth() >= WORLD_WIDTH){
            nextPos = WORLD_WIDTH - getWidth();
            changeXDir();
        }

        setX(nextPos);

    }

    public void changeXDir(){

        xDir *= -1;

    }

    @Override
    public void hit(float damage) {
        //TODO
    }

}
