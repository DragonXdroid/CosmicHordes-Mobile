package com.mygdx.game;

import java.util.ArrayList;
import java.util.List;

public class Level {

    private final SpaceShipManager spaceShipManager;
    private Wave currentWave;

    List<Wave> waves = new ArrayList<>();

    public Level(SpaceShipManager spaceShipManager ) {
        this.spaceShipManager = spaceShipManager;

        waves.add(new Wave(spaceShipManager,1));
        waves.add(new Wave(spaceShipManager,2));
        waves.add(new Wave(spaceShipManager,3));



    }

    public void update(float delta){

            if (currentWave == null){
                currentWave = waves.remove(0);
            }
            if (currentWave.getWaveStatus().equals("Ready")){
                currentWave.begin();
                System.out.println("begin");
                currentWave.setWaveStatus("Proceeding");
            }
            if (currentWave.getWaveStatus().equals("Proceeding")){
                currentWave.update(delta);
            }
            if (currentWave.getWaveStatus().equals("Completed") ){
                System.out.println("completed");
                if (waves.size() != 0){
                    currentWave = waves.remove(0);
                }
            }

    }
}
