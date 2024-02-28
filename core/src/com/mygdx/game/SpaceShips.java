package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.mygdx.game.Ships.NormalShip;
import com.mygdx.game.Ships.SpaceShip;
import com.mygdx.game.Ships.SparrowShip;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class SpaceShips {

    private List<SpaceShip> spaceShips = new ArrayList<>();

    private List<Laser> lasers = new ArrayList<>();


    public SpaceShips(){

        spaceShips.add( makeSpaceShip("Player","Normal","Blue"));
        spaceShips.add( makeSpaceShip("Enemy","Sparrow","Red"));


    }

    public SpaceShip makeSpaceShip(String alias, String shipType, String color){

        // Alias options: Enemy, Player
        // ShipType options: Normal, Sparrow
        // Color options: Normal only has blue option, Sparrow only has red option

        float positionModifier = 1;
        if (alias.equals("Enemy")){
            positionModifier = 3;
        }

        if (shipType.equals("Sparrow")){
            return new SparrowShip( alias, shipType, color, GameScreen.WORLD_WIDTH/2,
                    GameScreen.WORLD_HEIGHT*(positionModifier/4));
        }
        else {
            return new NormalShip( alias, shipType, color, GameScreen.WORLD_WIDTH/2,
                    GameScreen.WORLD_HEIGHT*(positionModifier/4));
        }


    }


    public void update(float delta){

        for (SpaceShip spaceShip : spaceShips) {
            spaceShip.update(delta);
            for (Laser laser : spaceShip.getLasers()) {
                laser.update(delta);
            }
            spaceShip.fireLasers();
            lasers.addAll(spaceShip.getLasers());
        }

        Iterator<SpaceShip> spaceShipIterator = spaceShips.iterator();
        while (spaceShipIterator.hasNext()) {
            SpaceShip spaceShip = spaceShipIterator.next();
            for (Laser laser : lasers) {
                if (spaceShip.intersects(laser.getHitBox())) {
                    if (!spaceShip.getLasers().contains(laser)) {
                        laser.setStatus("Dead");
                        spaceShip.hit(laser);
                    }
                }
            }
        }

    }

    public void draw(Batch batch){
        for (SpaceShip spaceShip : spaceShips){
            for (Laser laser : spaceShip.getLasers()){
                laser.draw(batch);
            }
            spaceShip.draw(batch);

        }
    }



}
