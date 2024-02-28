package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.Ships.SpaceShip;

public class GameScreen implements Screen {
    public static final TextureAtlas textureAtlas = new TextureAtlas("SpaceShipAtlas.atlas");

    //screen
    private Camera camera;
    private Viewport viewport;

    //graphics
    private SpriteBatch batch;
    private BackgroundManager backgroundManager;
    private SpaceShipManager spaceShipManager;
    private SpaceShip playerShip;


    //world parameters
    public static final float WORLD_WIDTH = 72;
    public static final float WORLD_HEIGHT = 128;


    public GameScreen (){
        camera = new OrthographicCamera();
        viewport = new StretchViewport(WORLD_WIDTH, WORLD_HEIGHT, camera);

        //texture atlas
        backgroundManager = new BackgroundManager();
        spaceShipManager = new SpaceShipManager();

        playerShip = spaceShipManager.makeSpaceShip("Player","Normal","Blue");
        spaceShipManager.makeSpaceShip("Enemy","Sparrow","Red");


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
        spaceShipManager.draw(batch);
        spaceShipManager.update(delta);

        input(delta);

        batch.end();
    }

    private void input(float delta){


        if (Gdx.input.isKeyPressed(Input.Keys.D) && playerShip.getHitBox().getX() < WORLD_WIDTH - playerShip.getHitBox().width) {
            playerShip.translate(playerShip.getMovement() * delta , 0f);
        }

        if (Gdx.input.isKeyPressed(Input.Keys.A) && playerShip.getHitBox().getX() > 0) {
            playerShip.translate(-playerShip.getMovement() * delta , 0f);
        }

        if (Gdx.input.isKeyPressed(Input.Keys.W) && playerShip.getHitBox().getY() < WORLD_HEIGHT/2 - playerShip.getHitBox().height) {
            playerShip.translate(0f, playerShip.getMovement() * delta);
        }

        if (Gdx.input.isKeyPressed(Input.Keys.S) && playerShip.getHitBox().getY() > 0) {
            playerShip.translate(0f, -playerShip.getMovement() * delta);
        }

        if (Gdx.input.isTouched()){
            playerShip.fireLasers();
        }


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
