package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.mygdx.game.Weapons.MachineGun;
import com.mygdx.game.Weapons.Weapon;

import java.util.ArrayList;

/**
 * Created by Joshua on 5/19/2017.
 */

public class PlayerShip extends Ship {

    private static int startingHealth = 5;

    private static float secondsToCrossScreen = 4.2f;
    private float speed;

    private static final String imagePath = "playership.png";

    public PlayerShip(float WORLD_WIDTH, float WORLD_HEIGHT){
        super(WORLD_WIDTH, WORLD_HEIGHT, imagePath, new MachineGun(), startingHealth);

        speed = WORLD_WIDTH/secondsToCrossScreen;

        sprite = new Sprite(new Texture(imagePath));

        //set the starting location in the center, up off the bottom by a small margin
        sprite.setPosition(WORLD_WIDTH/2 - sprite.getWidth(), WORLD_HEIGHT/100);

        weapon = new MachineGun();
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

    @Override
    public void hit(float damage) {
        //TODO
    }
}
