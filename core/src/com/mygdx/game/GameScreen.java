package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Weapons.Projectile;

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
    private ArrayList<Enemy> enemies;
    private ArrayList<Projectile> playerProjectiles;


    public GameScreen(SpaceGame game, SpriteBatch batch){

        this.game = game;
        this.batch = batch;

        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();

        playerProjectiles = new ArrayList<Projectile>();
        enemies = new ArrayList<Enemy>();
        ship = new PlayerShip(WORLD_WIDTH, WORLD_HEIGHT * (h/w));
        enemies.add(new AlienShip(WORLD_WIDTH, WORLD_HEIGHT * (h/w)));


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
        for(Enemy e: enemies){
            e.draw(batch);
        }
        for(Projectile p: playerProjectiles){
            p.draw(batch);
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
            playerProjectiles.addAll(ship.fire(delta));
        }
        catch(java.lang.NullPointerException e){}

        for(Enemy e: enemies){
            e.move(delta);
        }

        for(Projectile p: playerProjectiles){
            p.move(delta);
        }

        checkCollisions();
        if(playerProjectiles.size() > 30){
            System.out.println("Lots of bullets");
        }
    }

    public void checkCollisions(){
        checkPlayerProjectileCollisions();
    }

    private void checkPlayerProjectileCollisions() {

        ArrayList<Projectile> projectilesToRemove = new ArrayList<Projectile>();
        ArrayList<Enemy> enemiesToRemove = new ArrayList<Enemy>();

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
