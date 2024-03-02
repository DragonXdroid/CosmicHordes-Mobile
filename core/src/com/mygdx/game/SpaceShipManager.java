package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.mygdx.game.Ships.NormalShip;
import com.mygdx.game.Ships.SpaceShip;
import com.mygdx.game.Ships.SparrowShip;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class SpaceShipManager {

    private List<SpaceShip> spaceShips = new ArrayList<>();
    private List<Explosion> explosions = new ArrayList<>();
    private SpaceShip playerSpaceShip;
    private List<Laser> lasers = new ArrayList<>();

    public SpaceShipManager(){

    }

    public SpaceShip makeSpaceShip(String alias, String shipType, String color, float xPosition, float yPosition){
        SpaceShip spaceShip = null;
        // Alias options: Enemy, Player
        // ShipType options: Normal, Sparrow
        // Color options: Normal only has blue option, Sparrow only has red option

        if (shipType.equals("Sparrow")){
            spaceShip =  new SparrowShip( alias, shipType, color, xPosition, yPosition);
        }
        else {
            spaceShip = new NormalShip( alias, shipType, color, xPosition, yPosition);
        }
        return spaceShip;
    }

    public void addSpaceShip(SpaceShip spaceShip){
        if (spaceShip.getAlias().equals("Player")){
            setPlayerSpaceShip(spaceShip);
        }
        spaceShips.add(spaceShip);
    }


    public void update(float delta){

        Iterator<SpaceShip> iterator = spaceShips.iterator();
        while (iterator.hasNext()) {
            SpaceShip spaceShip = iterator.next();
            spaceShip.update(delta);
            if (spaceShip.getHitBox().getY() < -spaceShip.getHitBox().width )  {
                iterator.remove(); // Remove the current spaceShip from the list
            }
            if (spaceShip.getHealth() <= 0){
                iterator.remove();
            }
            for (Laser laser : spaceShip.getLasers()) {
                laser.update(delta);
            }
            lasers.addAll(spaceShip.getLasers());
        }


        Iterator<SpaceShip> spaceShipIterator = spaceShips.iterator();
        while (spaceShipIterator.hasNext()) {
            SpaceShip spaceShip = spaceShipIterator.next();
            for (Laser laser : getLasers()) {
                if (spaceShip.intersects(laser.getHitBox())) {
                    if (!spaceShip.getLasers().contains(laser)) {
                        laser.setStatus("Dead");
                        spaceShip.hit(laser);
                    }
                }
            }
        }

        ListIterator<Explosion> explosionListIterator = explosions.listIterator();
        while (explosionListIterator.hasNext()){
            Explosion explosion = explosionListIterator.next();
            if (explosion.isAnimationFinished()){
                explosionListIterator.remove();
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
        for (Explosion explosion : explosions){
            explosion.draw(batch);
        }
    }

    public void setPlayerSpaceShip(SpaceShip playerSpaceShip) {
        if (this.playerSpaceShip != null || !playerSpaceShip.getAlias().equals("Player")){
            throw new IllegalArgumentException("There is already a spaceShip or the spaceship " +
                    "given is not a player");
        }

        this.playerSpaceShip = playerSpaceShip;
    }

    public List<Laser> getLasers() {
        List<Laser> lasers = new ArrayList<>();
        for (SpaceShip spaceShip : spaceShips){
            lasers.addAll(spaceShip.getLasers());
        }
        return lasers;
    }



    public List<SpaceShip> getEnemySpaceShips() {
        List<SpaceShip> list = new ArrayList<>();
        for (SpaceShip spaceShip : spaceShips){
            if (spaceShip.getAlias().equals("Enemy")){
                list.add(spaceShip);
            }
        }
        return list;
    }
}
