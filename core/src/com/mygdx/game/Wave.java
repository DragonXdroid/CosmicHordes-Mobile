package com.mygdx.game;

import com.mygdx.game.Screens.GameScreen;
import com.mygdx.game.Ships.SpaceShip;

import java.util.ArrayList;
import java.util.List;

public class Wave {

    List<SpaceShip> enemies = new ArrayList<>();
    EntityManager entityManager;
    private int waveNumber;
    private String waveStatus;

    public Wave(EntityManager entityManager, int waveNumber) {
        this.waveNumber = waveNumber;
        this.entityManager = entityManager;
        waveStatus = "Loading";
        load();
        waveStatus = "Ready";
    }

    public void load (){
        enemies.add(entityManager.makeSpaceShip("Enemy","Sparrow","Red",
                CosmicHordesMobile.WORLD_WIDTH*(1f/5f), CosmicHordesMobile.WORLD_HEIGHT * 1.25f));
        enemies.add(entityManager.makeSpaceShip("Enemy","Sparrow","Red",
                CosmicHordesMobile.WORLD_WIDTH*(3f/6f), CosmicHordesMobile.WORLD_HEIGHT * 1.25f));
        enemies.add(entityManager.makeSpaceShip("Enemy","Sparrow","Red",
                CosmicHordesMobile.WORLD_WIDTH*(4f/5f), CosmicHordesMobile.WORLD_HEIGHT * 1.25f));
    }

    public void begin(){
        for (SpaceShip spaceShip : enemies){
            entityManager.addToCurrentSpaceShips(spaceShip);
        }
    }

    public void update (float delta){

        if (entityManager.getEnemySpaceShips().size() == 0){
            waveStatus = "Completed";
        }
    }
    public String getWaveStatus() {
        return waveStatus;
    }

    public void setWaveStatus(String waveStatus) {
        this.waveStatus = waveStatus;
    }

    public int getWaveNumber() {
        return waveNumber;
    }
}
