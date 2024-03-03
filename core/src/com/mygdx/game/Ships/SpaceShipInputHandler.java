package com.mygdx.game.Ships;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.CosmicHordesMobile;

public class SpaceShipInputHandler {

    Viewport viewport;
    SpaceShip playerShip;
    private final float TOUCH_MOVEMENT_THRESHOLD = 0.5f;
    private float xChange;
    private float yChange;

    public SpaceShipInputHandler(Viewport viewport, SpaceShip spaceShip){
        setViewport(viewport);
        setPlayerSpaceShip(spaceShip);
    }

    public void update(float delta){
        xChange = 0;
        yChange = 0;

        if (Gdx.app.getType() == Application.ApplicationType.Desktop){
            desktopMovementControls(delta);
        }
        else if (Gdx.app.getType() == Application.ApplicationType.Android){
            androidMovementControls(delta);
        }

        playerShip.translate(xChange * playerShip.getSpeed() * delta, yChange * playerShip.getSpeed() * delta);
        checkPlayerBounds(delta);

    }

    private void desktopMovementControls(float delta){
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
    }

    private void androidMovementControls(float delta){
        if (Gdx.input.isTouched()){

            float xTouchPosition = Gdx.input.getX();
            float yTouchPosition = Gdx.input.getY();

            Vector2 touchPosition = new Vector2(xTouchPosition, yTouchPosition);
            touchPosition = viewport.unproject(touchPosition);

            Vector2 playerShipCenter = new Vector2(
                    playerShip.getHitBox().getX() + playerShip.getHitBox().getWidth()/2,
                    playerShip.getHitBox().getY() + playerShip.getHitBox().getHeight()/2
            );

            float touchDistance = touchPosition.dst(playerShipCenter);

            if (touchDistance > TOUCH_MOVEMENT_THRESHOLD){
                float xTouchDifference = touchPosition.x - playerShipCenter.x;
                float yTouchDifference = touchPosition.y - playerShipCenter.y;

                xChange = xTouchDifference / touchDistance;
                yChange = yTouchDifference / touchDistance;

            }
        }
    }

    private void checkPlayerBounds(float delta){

        // Limit ship movement to within the screen boundaries
        if (playerShip.getHitBox().getX() < 0) {
            playerShip.getHitBox().setX(0);

        } else if (playerShip.getHitBox().getX() + playerShip.getHitBox().getWidth() > CosmicHordesMobile.WORLD_WIDTH) {
            playerShip.getHitBox().setX(CosmicHordesMobile.WORLD_WIDTH - playerShip.getHitBox().getWidth());
        }

        if (playerShip.getHitBox().getY() < 0) {
            playerShip.getHitBox().setY(0);

        } else if (playerShip.getHitBox().getY() + playerShip.getHitBox().getHeight() > CosmicHordesMobile.WORLD_HEIGHT/2) {
            playerShip.getHitBox().setY(CosmicHordesMobile.WORLD_HEIGHT/2 - playerShip.getHitBox().getHeight());
        }
    }


    public void setViewport(Viewport viewport) {
        this.viewport = viewport;
    }

    public void setPlayerSpaceShip(SpaceShip playerSpaceShip) {
        if (!playerSpaceShip.getAlias().equals("Player")){
            throw new IllegalArgumentException("Only ships associated with a player can have an input handler");
        }
        this.playerShip = playerSpaceShip;
    }
}
