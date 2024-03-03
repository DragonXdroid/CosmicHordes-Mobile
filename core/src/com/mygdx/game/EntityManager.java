package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.Ships.NormalShip;
import com.mygdx.game.Ships.SpaceShip;
import com.mygdx.game.Ships.SparrowShip;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class EntityManager {

    private List<SpaceShip> currentSpaceShips = new ArrayList<>();
    private List<Explosion> explosions = new ArrayList<>();
    private SpaceShip playerSpaceShip;

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

    public void addToCurrentSpaceShips(SpaceShip spaceShip){
        if (spaceShip.getAlias().equals("Player")){
            setPlayerSpaceShip(spaceShip);
        }
        currentSpaceShips.add(spaceShip);
    }

    public void update(float delta){
        currentSpaceShipsUpdate(delta);
        laserUpdate(delta);
        explosionUpdate(delta);
        checkCollisions(delta);

    }

    private void currentSpaceShipsUpdate(float delta){
        Iterator<SpaceShip> iterator = currentSpaceShips.iterator();
        while (iterator.hasNext()) {
            SpaceShip spaceShip = iterator.next();
            spaceShip.update(delta);
            if (spaceShip.getHitBox().getY() < -spaceShip.getHitBox().width )  {
                iterator.remove(); // Remove the current spaceShip from the list
            }
            if (spaceShip.getHealth() <= 0){
                explosions.add(new Explosion(0.7f ,new Rectangle(spaceShip.getHitBox())));
                iterator.remove();
            }
        }
    }

    private void laserUpdate(float delta){
        Iterator<SpaceShip> iterator = currentSpaceShips.iterator();
        while (iterator.hasNext()) {
            SpaceShip spaceShip = iterator.next();
            for (Laser laser : spaceShip.getLasers()) {
                laser.update(delta);
            }
        }
    }

    private void checkCollisions(float delta){
        Iterator<SpaceShip> spaceShipIterator = currentSpaceShips.iterator();
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
    }

    private void explosionUpdate(float delta){
        ListIterator<Explosion> explosionListIterator = explosions.listIterator();
        while (explosionListIterator.hasNext()){
            Explosion explosion = explosionListIterator.next();
            explosion.update(delta);
            if (explosion.isAnimationFinished()){
                explosionListIterator.remove();
            }
        }
    }

    public void draw(Batch batch){
        for (SpaceShip spaceShip : currentSpaceShips){
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
        for (SpaceShip spaceShip : currentSpaceShips){
            lasers.addAll(spaceShip.getLasers());
        }
        return lasers;
    }

    public List<SpaceShip> getEnemySpaceShips() {
        List<SpaceShip> list = new ArrayList<>();
        for (SpaceShip spaceShip : currentSpaceShips){
            if (spaceShip.getAlias().equals("Enemy")){
                list.add(spaceShip);
            }
        }
        return list;
    }
}
