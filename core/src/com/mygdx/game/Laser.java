package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.Screens.GameScreen;

public class Laser {

    //stats
    private String alias;
    private String status;
    private float damage;
    private float movementSpeed;

    //position and dimensions
    private Rectangle hitBox;
    TextureRegion textureRegion;

    public Laser(String alias, String color, float xPosition, float yPosition) {
        this.alias = alias;
        this.movementSpeed = alias.equals("Player") ? 45 : -45;
        this.textureRegion = GameScreen.textureAtlas.findRegion("Laser" + color);
        this.status = "Alive";
        this.hitBox = new Rectangle(xPosition - 0.4f/2, yPosition, 0.4f, 4);
        this.damage = 10;
    }

    public void update(float delta) {
        hitBox.y += movementSpeed * delta;
    }

    public void draw(Batch batch){
        batch.draw(textureRegion, hitBox.getX() - hitBox.getWidth()/2, hitBox.getY(), hitBox.getWidth(), hitBox.height);
    }

    public Rectangle getHitBox(){
        return hitBox;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public float getDamage() {
        return damage;
    }
}
