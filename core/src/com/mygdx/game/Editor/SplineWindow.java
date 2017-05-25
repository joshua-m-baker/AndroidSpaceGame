package com.mygdx.game.Editor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.CatmullRomSpline;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.mygdx.game.SpaceGame;

import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

/**
 * Created by Joshua on 5/24/2017.
 */

public class SplineWindow extends ScreenAdapter{

    static final int WIDTH = 320;
    static final int HEIGHT = 180;

    private SpaceGame game;
    private SpriteBatch batch;

    private Sprite sprite;

    private ArrayList<Vector2> inputPoints;
    private Vector2[] outputPoints;

    private CatmullRomSpline<Vector2> spline;

    private OrthographicCamera camera;

    float h;
    private ShapeRenderer shapeRenderer;
    private Rectangle rect;


    public SplineWindow(SpaceGame game, SpriteBatch batch){

        this.game = game;
        this.batch = batch;

        float w = Gdx.graphics.getWidth();
        h = Gdx.graphics.getHeight();

        spline = null;
        shapeRenderer = new ShapeRenderer();
        sprite = new Sprite(new Texture("black.png"));

        inputPoints = new ArrayList<Vector2>();
        outputPoints = null;

        camera = new OrthographicCamera(WIDTH, HEIGHT);
        camera.position.set(camera.viewportWidth/2f, camera.viewportHeight/2f, 0);
        camera.update();

        Gdx.input.setInputProcessor(new inputListener());
    }

    private class inputListener extends InputAdapter {

        public boolean touchDown(int screenX, int screenY, int pointer, int button) {
            if (screenX <= 20 && screenY <= 20) {
                if(inputPoints.size()>=4) {
                    System.out.println(inputPoints);
                    Vector2[] splinePoints = inputPoints.toArray(new Vector2[inputPoints.size()]);
                    //spline = new CatmullRomSpline<Vector2>(splinePoints, true);

                /*members*/
                    int k = 10000; //increase k for more fidelity to the spline
                    outputPoints = new Vector2[k];
/*init()*/
                    CatmullRomSpline<Vector2> myCatmull = new CatmullRomSpline<Vector2>(splinePoints, false);
                    for (int i = 0; i < k; ++i) {
                        outputPoints[i] = new Vector2();
                        myCatmull.valueAt(outputPoints[i], ((float) i) / ((float) k - 1));
                    }
                }
            } else if(screenX >= WIDTH - 20 && screenY <=  20){
                inputPoints = new ArrayList<Vector2>();
                outputPoints = null;
            }
            else {
                inputPoints.add(new Vector2(screenX, h - screenY));
                //System.out.println(inputPoints);
                return true;

            }
            return true;
        }
    }


    @Override
    public void render(float delta){
        Gdx.gl.glClearColor(255, 255, 255, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();
        batch.setProjectionMatrix(camera.combined);


        shapeRenderer.setProjectionMatrix(camera.combined);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(0,0,0,1);
        shapeRenderer.rect(0, HEIGHT - 20, 20, 20);
        shapeRenderer.rect(WIDTH-20, HEIGHT-20, 20, 20);
        shapeRenderer.end();

        batch.begin();
        for(int i = 0; i < inputPoints.size(); i++){
            sprite.setPosition(inputPoints.get(i).x, inputPoints.get(i).y);
            sprite.draw(batch);
        }
        if(outputPoints != null) {
            for (int i = 0; i < outputPoints.length; i++) {
                sprite.setPosition(outputPoints[i].x, outputPoints[i].y);
                sprite.draw(batch);
            }
        }
        batch.end();
    }

}
