package com.mygdx.game;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class GameScreen implements Screen {
    public static final TextureAtlas textureAtlas = new TextureAtlas("SpaceShipAtlas.atlas");

    //screen
    private Camera camera;
    private Viewport viewport;

    //graphics
    private SpriteBatch batch;
    private BackgroundManager backgroundManager;
    private SpaceShips spaceShips;


    //world parameters
    public static final float WORLD_WIDTH = 72;
    public static final float WORLD_HEIGHT = 128;


    public GameScreen (){
        camera = new OrthographicCamera();
        viewport = new StretchViewport(WORLD_WIDTH, WORLD_HEIGHT, camera);

        //texture atlas
        backgroundManager = new BackgroundManager();
        spaceShips = new SpaceShips();


        batch = new SpriteBatch();
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        batch.begin();

        //scrolling background
        backgroundManager.update(delta);
        backgroundManager.draw(batch);

        //spaceships
        spaceShips.draw(batch);
        spaceShips.update(delta);

        batch.end();
    }


    @Override
    public void resize(int width, int height) {
        viewport.update(width,height,true);
        batch.setProjectionMatrix(camera.combined);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        batch.dispose();
        textureAtlas.dispose();

    }
}
