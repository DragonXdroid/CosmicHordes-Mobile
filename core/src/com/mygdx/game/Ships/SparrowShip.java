package com.mygdx.game.Ships;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.mygdx.game.Laser;

import java.util.LinkedList;

public class SparrowShip extends SpaceShip{

    public SparrowShip(String alias, String shipType, String color, float xPosition, float yPosition) {
        super(alias, shipType, color, xPosition, yPosition);
    }

    @Override
    public void fireLasers() {
        LinkedList<Laser> lasers = getLasers();

        if (getLaserCoolDownTimer() <= 0){
            lasers.add(new Laser(getAlias(), getColor(),getxPosition() + ( getWidth()*0.3f ), getyPosition()+ (getHeight()*0.26f) ));
            lasers.add(new Laser(getAlias(), getColor(),getxPosition() + ( getWidth()*0.7f ), getyPosition()+ (getHeight()*0.26f) ));
            setLaserCoolDownTimer(0.5f);

        }


    }

    @Override
    public void draw(Batch batch){
        batch.draw(getShipTexture(), getxPosition(), getyPosition(),getWidth(), getHeight());
        if (getSheild() > 0) {
            float positionOffset = getAlias().equals("Player") ? 2 : -2;
            batch.draw(getShieldTexture(), getxPosition()+0.5f, getyPosition() + positionOffset, getWidth() - 1, getHeight());
        }
    }
}
