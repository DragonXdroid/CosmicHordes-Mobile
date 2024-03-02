package com.mygdx.game;

import com.mygdx.game.Ships.SpaceShip;

import java.util.ArrayList;
import java.util.List;

public class Wave {

    List<SpaceShip> enemies = new ArrayList<>();
    SpaceShipManager spaceShipManager;
    private int waveNumber;
    private String waveStatus;

    public Wave(SpaceShipManager spaceShipManager, int waveNumber) {
        this.waveNumber = waveNumber;
        this.spaceShipManager = spaceShipManager;
        waveStatus = "Loading";
        load();
        waveStatus = "Ready";
    }

    public void load (){
        enemies.add(spaceShipManager.makeSpaceShip("Enemy","Sparrow","Red",GameScreen.WORLD_WIDTH*(1f/5f), GameScreen.WORLD_HEIGHT * 1.25f));
        enemies.add(spaceShipManager.makeSpaceShip("Enemy","Sparrow","Red",GameScreen.WORLD_WIDTH*(3f/6f), GameScreen.WORLD_HEIGHT * 1.25f));
        enemies.add(spaceShipManager.makeSpaceShip("Enemy","Sparrow","Red",GameScreen.WORLD_WIDTH*(4f/5f), GameScreen.WORLD_HEIGHT * 1.25f));
    }

    public void begin(){
        for (SpaceShip spaceShip : enemies){
            spaceShipManager.addSpaceShip(spaceShip);
        }
    }

    public void update (float delta){

        if (spaceShipManager.getEnemySpaceShips().size() == 0){
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
