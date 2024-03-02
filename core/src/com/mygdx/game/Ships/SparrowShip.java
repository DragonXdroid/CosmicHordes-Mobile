package com.mygdx.game.Ships;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.GameScreen;
import com.mygdx.game.Laser;

import java.util.LinkedList;
import java.util.Random;

public class SparrowShip extends SpaceShip{

    private Vector2 directionVector;
    private float moveTimer;
    private float moveTimeLimit;
    private float fireLaserTimer;
    private float waitTimeLimit;

    public SparrowShip(String alias, String shipType, String color, float xPosition, float yPosition) {
        super(alias, shipType, color, xPosition, yPosition, 10,10,10,10,1f,20);

        directionVector = new Vector2(0, -1);
        moveTimeLimit = 0.75f;
        waitTimeLimit = 1f;
        moveTimer = moveTimeLimit;
        fireLaserTimer = 2;

    }

    @Override
    public void shipBehavior(float delta) {

        fireLaserTimer -= delta;

        if (fireLaserTimer <= 0){
            fireLasers();
            fireLaserTimer = 4;
        }


        float xChange = 0;
        float yChange = -getSpeed() * delta;
        translate(xChange,yChange);

        if (getHitBox().getX() < 0) {
             getHitBox().setX(0);

        } else if (getHitBox().getX() + getHitBox().getWidth() > GameScreen.WORLD_WIDTH) {
             getHitBox().setX(GameScreen.WORLD_WIDTH - getHitBox().getWidth());
        }

    }

    private void randomizeDirectionVector(){
        Random random = new Random();

        double angle = random.nextDouble(0,Math.PI*2);

        directionVector.x = (float) Math.sin(angle);
        directionVector.y = (float) Math.cos(angle);
    }

    @Override
    public void fireLasers() {
        LinkedList<Laser> lasers = getLasers();

        if (getLaserCoolDownTimer() <= 0){
            lasers.add(new Laser(getAlias(), getColor(),
                    getHitBox().getX() + ( getHitBox().getWidth()*0.3f ),
                    getHitBox().getY() + (getHitBox().getHeight()*0.26f)
            ));

            lasers.add(new Laser(getAlias(), getColor(),
                    getHitBox().getX() + ( getHitBox().getWidth()*0.7f ),
                    getHitBox().getY() + (getHitBox().getHeight()*0.26f)
            ));

            setLaserCoolDownTimer(0.5f);

        }
    }

    @Override
    public void draw(Batch batch){
        batch.draw(getShipTexture(), getHitBox().getX(), getHitBox().getY(), getHitBox().getWidth(), getHitBox().getHeight());
        if (getSheild() > 0) {
            float positionOffset = getAlias().equals("Player") ? 2 : -2;
            batch.draw(
                    getShieldTexture(),
                    getHitBox().getX()+0.5f,
                    getHitBox().getY() + positionOffset,
                    getHitBox().getWidth() -1,
                    getHitBox().getHeight()
            );
        }
    }
}
