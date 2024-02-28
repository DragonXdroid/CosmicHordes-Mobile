package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Background {

    private TextureRegion region;

    private static final float backgroundMaxScrollingSpeed = GameScreen.WORLD_HEIGHT / 4;
    private float scrollDividant;

    private float offset;

    public Background(TextureRegion region, float scrollDividant) {
        this.region = region;
        this.scrollDividant = scrollDividant;
    }

    public void update(float delta) {
            offset += delta * backgroundMaxScrollingSpeed / scrollDividant ;
            if (offset > GameScreen.WORLD_HEIGHT) {
                offset = 0;
            }
    }
    public void draw(Batch batch) {
        batch.draw(region, 0, -offset,  GameScreen.WORLD_WIDTH,  GameScreen.WORLD_HEIGHT);
        batch.draw(region, 0, -offset +  GameScreen.WORLD_HEIGHT,  GameScreen.WORLD_WIDTH,  GameScreen.WORLD_HEIGHT);
    }
}