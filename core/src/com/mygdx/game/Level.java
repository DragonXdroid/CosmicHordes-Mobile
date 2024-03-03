package com.mygdx.game;

import java.util.ArrayList;
import java.util.List;

public class Level {

    private final EntityManager entityManager;
    private Wave currentWave;

    List<Wave> waves = new ArrayList<>();

    public Level(EntityManager entityManager) {
        this.entityManager = entityManager;

        waves.add(new Wave(entityManager,1));
        waves.add(new Wave(entityManager,2));
        waves.add(new Wave(entityManager,3));



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
