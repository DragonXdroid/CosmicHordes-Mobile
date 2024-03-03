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
    Texture explosionTexture = new Texture("explosion.png");

    public Explosion(float animationTime, com.badlogic.gdx.math.Rectangle boundingBox) {
        this.boundingBox = boundingBox;
        explosionAnimation = new Animation<TextureRegion>(animationTime/16, com.mygdx.game.Utils.TextureRegion.split(explosionTexture,16,64,64));
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
