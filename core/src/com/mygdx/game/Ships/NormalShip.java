package com.mygdx.game.Ships;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.mygdx.game.Laser;

import java.util.LinkedList;

public class NormalShip extends SpaceShip{
    public NormalShip(String alias, String shipType, String color, float xPosition, float yPosition) {
        super(alias, shipType, color, xPosition, yPosition,25,50,10,10,0.5f,100);

    }

    @Override
    public void shipBehavior(float delta) {

    }

    @Override
    public void fireLasers() {
        LinkedList<Laser> lasers = getLasers();

        if (getLaserCoolDownTimer() <= 0){


            if (getLaserCoolDownTimer() <= 0){
                lasers.add(new Laser(getAlias(), getColor(),
                        getHitBox().getX() + ( getHitBox().getWidth()*0.22f ),
                        getHitBox().getY() + (getHitBox().getHeight()*0.4f)
                ));

                lasers.add(new Laser(getAlias(), getColor(),
                        getHitBox().getX() + ( getHitBox().getWidth()*0.78f ),
                        getHitBox().getY() + (getHitBox().getHeight()*0.4f)
                ));

                setLaserCoolDownTimer(0.5f);

            }
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
                    getHitBox().getX(),
                    getHitBox().getY() + positionOffset,
                    getHitBox().getWidth(),
                    getHitBox().getHeight()
            );
        }
    }
}
