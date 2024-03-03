package com.mygdx.game.Utils;

import com.badlogic.gdx.graphics.Texture;

public class TextureRegion  {

    public static com.badlogic.gdx.graphics.g2d.TextureRegion[] split (Texture texture, int totalFrames , int tileWidth, int tileHeight) {
        com.badlogic.gdx.graphics.g2d.TextureRegion[][] textureRegion2D = com.badlogic.gdx.graphics.g2d.TextureRegion.split(texture, tileWidth, tileHeight);
        com.badlogic.gdx.graphics.g2d.TextureRegion[] textureRegion1D = new com.badlogic.gdx.graphics.g2d.TextureRegion[totalFrames];
        int index = 0;
        for (int i = 0; i < 4; i++){
            for (int j = 0; j < 4; j++){
                textureRegion1D[index++] = textureRegion2D[i][j];
            }
        }
        return textureRegion1D;
    }
}
