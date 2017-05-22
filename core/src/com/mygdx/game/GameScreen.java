package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;


/**
 * Created by Joshua on 5/21/2017.
 */

public class GameScreen extends ScreenAdapter {

    static final int WORLD_WIDTH = 300;
    static final int WORLD_HEIGHT = 300;

    private SpaceGame game;
    private SpriteBatch batch;

    OrthographicCamera camera;

    private PlayerShip ship;
    private AlienShip enemy;
    private ArrayList<com.mygdx.game.Weapons.Bullet> bullets;

    public GameScreen(SpaceGame game, SpriteBatch batch){

        this.game = game;
        this.batch = batch;

        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();

        ship = new PlayerShip(WORLD_WIDTH, WORLD_HEIGHT);
        enemy = new AlienShip(WORLD_WIDTH, WORLD_HEIGHT * (h/w));
        bullets = new ArrayList<com.mygdx.game.Weapons.Bullet>();

        camera = new OrthographicCamera(WORLD_WIDTH, WORLD_HEIGHT * (h/w));
        camera.position.set(camera.viewportWidth/2f, camera.viewportHeight/2f, 0);
        camera.update();

    }

    @Override
    public void render(float delta){

        update(delta);
        draw();

    }

    public void draw(){

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();
        batch.setProjectionMatrix(camera.combined);

        batch.begin();
        ship.draw(batch);
        enemy.draw(batch);
        for(com.mygdx.game.Weapons.Bullet b: bullets){
            b.draw(batch);
        }
        batch.end();
    }

    public void handleInput(float delta) {
        if (Gdx.input.isTouched()) {

            int screenX = Gdx.input.getX();

            if (screenX >= Gdx.graphics.getWidth() / 2) {
                ship.moveRight(delta);
            } else if (screenX < Gdx.graphics.getWidth() / 2) {
                ship.moveLeft(delta);
            }
        }

        else if(Gdx.input.isKeyPressed(Input.Keys.LEFT)){
            ship.moveLeft(delta);
        }

        else if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
            ship.moveRight(delta);
        }
    }

    public void update(float delta){
        handleInput(delta);
        //If an arraylist was returned, add it to our main bullet list
        try{
            bullets.addAll(ship.fire(delta));
        }
        catch(java.lang.NullPointerException e){

        }
        enemy.move(delta);
        for(com.mygdx.game.Weapons.Bullet b: bullets){
            b.move(delta);
        }
    }

}
