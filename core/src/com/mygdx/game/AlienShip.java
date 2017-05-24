package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.CatmullRomSpline;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Weapons.MachineGun;

/**
 * Created by Joshua on 5/21/2017.
 */

public class AlienShip extends Ship{

    private static final String imagePath = "enemyship1.png";
    private static final int startingHealth = 1;

    private float speed;
    private float xDir;

    private float current;

    private CatmullRomSpline<Vector2> spline;
    private Vector2 out;

    private Vector2[] points;
    private int counter;

    public AlienShip(float WORLD_WIDTH, float WORLD_HEIGHT, Vector2[] splinePoints){
        super(WORLD_WIDTH, WORLD_HEIGHT, imagePath, new MachineGun(), startingHealth);
        pos.set(0, WORLD_HEIGHT - getHeight());
        speed = 10f;
        xDir = 1;

       spline = new CatmullRomSpline<Vector2>(splinePoints, false);
        int k = 100; //increase k for more fidelity to the spline
        points = new Vector2[k];
        CatmullRomSpline<Vector2> myCatmull = new CatmullRomSpline<Vector2>(splinePoints, true);
    }

    public void move(float delta){

        out = new Vector2();

        spline.derivativeAt(out, current);
        setAngle(out.angle());

        current += (delta*speed)/out.len();

        if(current >= 1) current -= 1;

        spline.valueAt(out, current);
        pos.set(out);

    }

    public void changeXDir(){

        xDir *= -1;

    }

    @Override
    public void hit(float damage) {
        //TODO
    }

}
