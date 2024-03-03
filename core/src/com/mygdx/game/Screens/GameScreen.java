package com.mygdx.game.Screens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.BackgroundManager;
import com.mygdx.game.CosmicHordesMobile;
import com.mygdx.game.EntityManager;
import com.mygdx.game.Level;
import com.mygdx.game.Ships.SpaceShipInputHandler;
import com.mygdx.game.Ships.SpaceShip;

public class GameScreen implements Screen {
    public static final TextureAtlas textureAtlas = new TextureAtlas("SpaceShipAtlas.atlas");

    //screen
    private Camera camera;
    private Viewport viewport;

    //graphics
    private SpriteBatch batch;
    private BackgroundManager backgroundManager;

    //spaceship
    private EntityManager entityManager;
    private SpaceShip playerShip;
    private SpaceShipInputHandler spaceShipInputHandler;

    // Level
    public Level level;


    public GameScreen (){
        camera = new OrthographicCamera();
        viewport = new StretchViewport(CosmicHordesMobile.WORLD_WIDTH, CosmicHordesMobile.WORLD_HEIGHT, camera);

        backgroundManager = new BackgroundManager();
        entityManager = new EntityManager();

        playerShip = entityManager.makeSpaceShip("Player","Normal","Blue", CosmicHordesMobile.WORLD_WIDTH/2, CosmicHordesMobile.WORLD_WIDTH/4);
        entityManager.addToCurrentSpaceShips(playerShip);
        spaceShipInputHandler = new SpaceShipInputHandler(viewport, playerShip);

        level = new Level(entityManager);


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
        entityManager.draw(batch);
        entityManager.update(delta);
        level.update(delta);

        spaceShipInputHandler.update(delta);

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
