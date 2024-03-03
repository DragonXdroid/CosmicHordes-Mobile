package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.mygdx.game.Screens.GameScreen;

import java.util.ArrayList;
import java.util.List;

public class BackgroundManager {
    private List<Background> backgrounds = new ArrayList<>();

    public BackgroundManager() {
        backgrounds.add(new Background(GameScreen.textureAtlas.findRegion("Starscape0"), 8));
        backgrounds.add(new Background(GameScreen.textureAtlas.findRegion("Starscape1"), 4));
        backgrounds.add(new Background(GameScreen.textureAtlas.findRegion("Starscape2"), 2));
        backgrounds.add(new Background(GameScreen.textureAtlas.findRegion("Starscape3"), 1));
    }

    public void update(float delta) {
        for (Background background : backgrounds) {
            background.update(delta);
        }
    }

    public void draw(Batch batch) {
        for (Background background : backgrounds){
            background.draw(batch);
        }
    }
}