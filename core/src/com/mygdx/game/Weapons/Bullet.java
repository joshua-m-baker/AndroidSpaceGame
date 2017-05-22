package com.mygdx.game.Weapons;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;

/**
 * Created by Joshua on 5/21/2017.
 */

public class Bullet extends Projectile{

    private static final String imageName = "bullet.png";

    public Bullet(float x, float y){
        super(x, y, imageName);
        speed = 50;
        damage = 1;
    }


}
