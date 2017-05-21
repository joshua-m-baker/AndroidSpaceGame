package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class SpaceGame extends Game {
    SpriteBatch batch;

    //Sprite ship;
    //TextureAtlas spriteSheet;

    @Override
    public void create () {

        batch = new SpriteBatch();
        setScreen(new GameScreen(this, batch));

    }

    @Override
    public void render(){
        super.render();
    }

}
