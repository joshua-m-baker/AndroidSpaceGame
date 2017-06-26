package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Weapons.Projectile;

import java.util.ArrayList;


/**
 * Created by Joshua on 5/21/2017.
 */

public class GameScreen extends ScreenAdapter {

    static final int WORLD_WIDTH = 320;
    static final float WORLD_HEIGHT = 180;

    private SpaceGame game;
    private SpriteBatch batch;

    private OrthographicCamera camera;

    private PlayerShip playerShip;
    private ArrayList<Ship> enemies;
    private ArrayList<Projectile> playerProjectiles;


    public GameScreen(SpaceGame game, SpriteBatch batch){

        System.out.println("running");

        this.game = game;
        this.batch = batch;

        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();

        playerProjectiles = new ArrayList<Projectile>();
        enemies = new ArrayList<Ship>();
        playerShip = new PlayerShip(WORLD_WIDTH, WORLD_HEIGHT);


        //[(6.0,135.0), (20.0,132.0), (102.0,122.0), (142.0,113.0), (174.0,101.0),
        // (193.0,66.0), (162.0,45.0), (123.0,50.0), (113.0,74.0), (126.0,102.0),
        // (187.0,126.0), (295.0,137.0), (309.0,137.0)]

        Vector2[] splinePoints = {new Vector2(30, 25), new Vector2(60, 50),
        new Vector2(100, 60), new Vector2(120, 80), new Vector2(180, 85),
                new Vector2(180, 90), new Vector2(210, 100)};

        enemies.add(new AlienShip(WORLD_WIDTH, WORLD_HEIGHT, splinePoints));


        camera = new OrthographicCamera(WORLD_WIDTH, WORLD_HEIGHT);
        camera.position.set(camera.viewportWidth/2f, camera.viewportHeight/2f, 0);
        camera.update();

    }

    @Override
    public void render(float delta){

        update(delta);
        draw();

    }

    public void draw() {

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        playerShip.draw(batch);
        for (Ship e : enemies) {
            e.draw(batch);
        }

        for (Projectile p : playerProjectiles) {
            p.draw(batch);
        }
        batch.end();
    }

    public void handleInput(float delta) {
        if (Gdx.input.isTouched()) {

            int screenX = Gdx.input.getX();

            if (screenX >= Gdx.graphics.getWidth() / 2) {
                playerShip.goingRight();
            } else if (screenX < Gdx.graphics.getWidth() / 2) {
                playerShip.goingLeft();
            }
        } else if (Gdx.input.isKeyPressed(Input.Keys.ANY_KEY)) {
            if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
                playerShip.goingLeft();
            } else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
                playerShip.goingRight();
            }

        } else{
            playerShip.goingForward();
        }

    }

    public void update(float delta){
        handleInput(delta);
        //If an arraylist was returned, add it to our main bullet list



        try{
            playerProjectiles.addAll(playerShip.fire(delta));
        }
        catch(java.lang.NullPointerException e){}

        playerShip.move(delta);

        for(Ship e: enemies){
            e.move(delta);
        }

        for(Projectile p: playerProjectiles){
            p.move(delta);
        }

        checkCollisions();

    }

    public void checkCollisions(){
        checkPlayerProjectileCollisions();
    }

    private void checkPlayerProjectileCollisions() {

        ArrayList<Projectile> projectilesToRemove = new ArrayList<Projectile>();
        ArrayList<Ship> enemiesToRemove = new ArrayList<Ship>();

        for (int i = 0; i < playerProjectiles.size(); i++) {
            if (playerProjectiles.get(i).getY() > WORLD_HEIGHT) {
                projectilesToRemove.add(playerProjectiles.get(i));
            } else {

                for (int j = 0; j < enemies.size(); j++) {
                    if (playerProjectiles.get(i).getRect().overlaps(enemies.get(j).getRect())) {
                        projectilesToRemove.add(playerProjectiles.get(i));
                        enemiesToRemove.add(enemies.get(j));
                    }
                }
            }
        }

        playerProjectiles.removeAll(projectilesToRemove);
        enemies.removeAll(enemiesToRemove);

    }

    private void checkEnemyProjectileCollisions(){

    }

}
