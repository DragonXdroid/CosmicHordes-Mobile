package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

public class Explosion {

    private Animation<TextureRegion> explosionAnimation;
    private float explosionTimer;

    private Rectangle boundingBox;

    public Explosion(Texture texture, float animationTime, com.badlogic.gdx.math.Rectangle boundingBox) {
        this.boundingBox = boundingBox;

        TextureRegion[][] textureRegion2D = TextureRegion.split(texture, 64, 64);
        TextureRegion[] textureRegion1D = new TextureRegion[16];
        int index = 0;
        for (int i = 0; i < 4; i++){
            for (int j = 0; j < 4; j++){
                textureRegion1D[index++] = textureRegion2D[i][j];
            }
        }
        explosionAnimation = new Animation<TextureRegion>(animationTime/16, textureRegion1D);
        explosionTimer = 0;
    }

    public void update(float delta){
        explosionTimer += delta;
    }

    public  void draw(Batch batch){
        batch.draw(explosionAnimation.getKeyFrame(explosionTimer),
                boundingBox.x, boundingBox.y,
                boundingBox.width, boundingBox.height);
    }

    public boolean isAnimationFinished (){
        return explosionAnimation.isAnimationFinished(explosionTimer);
    }

}
