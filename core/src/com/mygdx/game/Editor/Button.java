package com.mygdx.game.Editor;

import com.badlogic.gdx.math.Rectangle;

/**
 * Created by Joshua on 5/25/2017.
 */

public class Button {

    private Rectangle rect;

    public Button(int x, int y, int width, int height){
        rect = new Rectangle(x, y, width, height);
    }


    public Rectangle getRect() {
        return rect;
    }
}
