package com.mygdx.game.Ships;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.mygdx.game.Laser;

import java.util.LinkedList;

public class NormalShip extends SpaceShip{
    public NormalShip(String alias, String shipType, String color, float xPosition, float yPosition) {
        super(alias, shipType, color, xPosition, yPosition);
    }

    @Override
    public void fireLasers() {
        LinkedList<Laser> lasers = getLasers();

        if (getLaserCoolDownTimer() <= 0){

            lasers.add(new Laser(getAlias(), getColor(),getxPosition() + ( getWidth()*0.22f ), getyPosition()+ (getHeight()*0.4f) ));
            lasers.add(new Laser(getAlias(), getColor(),getxPosition() + ( getWidth()*0.78f ), getyPosition()+ (getHeight()*0.4f) ));
            setLaserCoolDownTimer(0.5f);

        }
    }

    @Override
    public void draw(Batch batch){
        batch.draw(getShipTexture(), getxPosition(), getyPosition(),getWidth(), getHeight());
        if (getSheild() > 0) {
            float positionOffset = getAlias().equals("Player") ? 2 : -2;
            batch.draw(getShieldTexture(), getxPosition(), getyPosition() + positionOffset, getWidth(), getHeight());
        }
    }
}
