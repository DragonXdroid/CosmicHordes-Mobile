package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.Screens.GameScreen;

public class Background {

    private TextureRegion region;

    private static final float backgroundMaxScrollingSpeed = CosmicHordesMobile.WORLD_HEIGHT / 4;
    private float scrollDividant;

    private float offset;

    public Background(TextureRegion region, float scrollDividant) {
        this.region = region;
        this.scrollDividant = scrollDividant;
    }

    public void update(float delta) {
            offset += delta * backgroundMaxScrollingSpeed / scrollDividant ;
            if (offset > CosmicHordesMobile.WORLD_HEIGHT) {
                offset = 0;
            }
    }
    public void draw(Batch batch) {
        batch.draw(region, 0, -offset,  CosmicHordesMobile.WORLD_WIDTH,  CosmicHordesMobile.WORLD_HEIGHT);
        batch.draw(region, 0, -offset +  CosmicHordesMobile.WORLD_HEIGHT,  CosmicHordesMobile.WORLD_WIDTH, CosmicHordesMobile.WORLD_HEIGHT);
    }
}