package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
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
    private final float TOUCH_MOVEMENT_THRESHOLD = 0.5f;

    //world parameters
    public static final float WORLD_WIDTH = 72;
    public static final float WORLD_HEIGHT = 128;

    // Level
    public Level level;


    public GameScreen (){
        camera = new OrthographicCamera();
        viewport = new StretchViewport(WORLD_WIDTH, WORLD_HEIGHT, camera);

        backgroundManager = new BackgroundManager();
        spaceShipManager = new SpaceShipManager();

        playerShip = spaceShipManager.makeSpaceShip("Player","Normal","Blue", WORLD_WIDTH/2, WORLD_WIDTH/4);
        spaceShipManager.addSpaceShip(playerShip);

        level = new Level(spaceShipManager);


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
        level.update(delta);

        input(delta);

        batch.end();
    }

    private void input(float delta){

        float xChange = 0;
        float yChange = 0;

        if (Gdx.input.isKeyPressed(Input.Keys.D) || Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            xChange += 1; // Right movement
        }

        if (Gdx.input.isKeyPressed(Input.Keys.A) || Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            xChange -= 1; // Left movement
        }

        if (Gdx.input.isKeyPressed(Input.Keys.W) || Gdx.input.isKeyPressed(Input.Keys.UP)) {
            yChange += 1; // Up movement
        }

        if (Gdx.input.isKeyPressed(Input.Keys.S) || Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            yChange -= 1; // Down movement
        }
        if (Gdx.input.isButtonPressed(Input.Buttons.LEFT)){
            playerShip.fireLasers();
        }


        // Normalize the vector
        if (xChange != 0 || yChange != 0) {
            float length = (float) Math.sqrt(xChange * xChange + yChange * yChange);
            xChange /= length;
            yChange /= length;
        }

//        if (Gdx.input.isTouched()){
//
//            float xTouchPosition = Gdx.input.getX();
//            float yTouchPosition = Gdx.input.getY();
//
//            Vector2 touchPosition = new Vector2(xTouchPosition, yTouchPosition);
//            touchPosition = viewport.unproject(touchPosition);
//
//            Vector2 playerShipCenter = new Vector2(
//                    playerShip.getHitBox().getX() + playerShip.getHitBox().getWidth()/2,
//                    playerShip.getHitBox().getY() + playerShip.getHitBox().getHeight()/2
//            );
//
//            float touchDistance = touchPosition.dst(playerShipCenter);
//
//            if (touchDistance > TOUCH_MOVEMENT_THRESHOLD){
//                float xTouchDifference = touchPosition.x - playerShipCenter.x;
//                float yTouchDifference = touchPosition.y - playerShipCenter.y;
//
//                xChange = xTouchDifference / touchDistance;
//                yChange = yTouchDifference / touchDistance;
//
//            }
//        }

        playerShip.translate(xChange * playerShip.getSpeed() * delta, yChange * playerShip.getSpeed() * delta);

        // Limit ship movement to within the screen boundaries
        if (playerShip.getHitBox().getX() < 0) {
            playerShip.getHitBox().setX(0);

        } else if (playerShip.getHitBox().getX() + playerShip.getHitBox().getWidth() > WORLD_WIDTH) {
            playerShip.getHitBox().setX(WORLD_WIDTH - playerShip.getHitBox().getWidth());
        }

        if (playerShip.getHitBox().getY() < 0) {
            playerShip.getHitBox().setY(0);

        } else if (playerShip.getHitBox().getY() + playerShip.getHitBox().getHeight() > WORLD_HEIGHT/2) {
            playerShip.getHitBox().setY(WORLD_HEIGHT/2 - playerShip.getHitBox().getHeight());
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
