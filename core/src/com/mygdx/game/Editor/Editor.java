package com.mygdx.game.Editor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.game.SpaceGame;

/**
 * Created by Joshua on 5/24/2017.
 */

public class Editor extends ScreenAdapter{

    public static int WORLD_WIDTH = 640;
    public static int WORLD_HEIGHT = 360;

    private final int bufferSize = 50;
    private final int leftBuffer = 10;

    SpriteBatch batch;

    //SplineWindow splineWindow;

    private OrthographicCamera camera;

    private BitmapFont font;
    private TextButton.TextButtonStyle style;
    private TextButton splineButton;

    private ShapeRenderer shapeRenderer;
    private final int border = 20;
    private final int windowHeight = 180 + border;
    private final int windowWidth = 320 + border;

    private Rectangle buttonRect = new Rectangle(windowWidth + leftBuffer, leftBuffer, 50, 50);

    public Editor(SpriteBatch batch){
        Gdx.graphics.setWindowedMode(WORLD_WIDTH, WORLD_HEIGHT);
        this.batch = batch;

        font = new BitmapFont();
        style = new TextButton.TextButtonStyle();
        style.font = font;
        style.fontColor = Color.BLACK;
        //style.fontColor(0, 0 ,0, 1);

        splineButton = new TextButton("Draw", style);
        splineButton.setPosition(windowWidth, 0);

        splineButton.addListener(new ClickListener(){
            public void clicked(InputEvent event, float x, float y){
                System.out.println("Clicked Button");
            }
        });

        shapeRenderer = new ShapeRenderer();
        camera = new OrthographicCamera(WORLD_WIDTH, WORLD_HEIGHT);
        camera.position.set(camera.viewportWidth/2f, camera.viewportHeight/2f, 0);
        camera.update();

        Gdx.input.setInputProcessor(new inputListener());
    }

    private class inputListener extends InputAdapter {

        @Override
        public boolean touchDown(int screenX, int screenY, int pointer, int button) {
            int x = screenX - 20;
            int y = screenY - 20;
            System.out.println(x + ", " + y);
//            if(splineWindow.getRect().contains(screenX, screenY)){
//                System.out.println("clicked");
//            }
         return true;
        }
    }
    @Override
    public void render(float delta){
        Gdx.gl.glClearColor(177/255.0f, 177/255.0f, 177/255.0f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(1,1,1,1);
        shapeRenderer.rect(0, WORLD_HEIGHT - windowHeight, windowWidth, windowHeight);
        shapeRenderer.end();

        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(0,0,0,1);
        shapeRenderer.rect(border, WORLD_HEIGHT - windowHeight + border, windowWidth-(2*border), windowHeight-(2*border));
        shapeRenderer.end();

       // splineWindow.draw(batch, camera);
    }

}
