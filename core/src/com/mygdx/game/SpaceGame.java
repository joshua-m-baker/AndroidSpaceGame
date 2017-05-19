package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public class SpaceGame extends ApplicationAdapter {
    SpriteBatch batch;
    Sprite ship;
    TextureAtlas spriteSheet;

    @Override
    public void create () {
        batch = new SpriteBatch();
        //ship = new Texture("ship.png");
        spriteSheet = new TextureAtlas("spritesheet.txt");
        ship = spriteSheet.createSprite("playership");
        //spriteSheet = new TextureAtlas("simpleshipsheet.txt");
        //ship = spriteSheet.createSprite("ship");
        ship.setPosition((Gdx.graphics.getWidth()/2-ship.getWidth()/2), 150);
        ship.setScale(9);
    }

    @Override
    public void render () {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        if (Gdx.input.isTouched()){

            int screenX = Gdx.input.getX();

            if(screenX >= Gdx.graphics.getWidth()/2){
                float pos = ship.getX() + 10;
                if (ship.getX() + ship.getWidth()/2 * ship.getScaleX() >= Gdx.graphics.getWidth()){
                    pos = Gdx.graphics.getWidth() - ship.getWidth()/2 * ship.getScaleX();
                }
                ship.setX(pos);
            }

            else if (screenX < Gdx.graphics.getWidth()/2){
                float pos = ship.getX() - 10;
                //System.out.println(ship.getWidth() * ship.getScaleX());
                if (pos <= ship.getWidth()/2 * ship.getScaleX()){
                    pos = ship.getWidth()/2 * ship.getScaleX();
                }
                ship.setX(pos);
            }
        }

        batch.begin();
        ship.draw(batch);
        //batch.draw(ship, (Gdx.graphics.getWidth()/2)-(ship.getWidth()/2), 0, 100, 100);
        //batch.draw(ship, (Gdx.graphics.getWidth()/2)-(ship.getWidth()/2), 0);
        batch.end();
    }

    @Override
    public void dispose () {
        batch.dispose();
        //ship.dispose();
    }
}
