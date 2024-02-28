package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

public class Laser {

    //stats
    private float movementSpeed;

    //position and dimensions
    private float xPosition;
    private float yPosition;
    private float width;
    private float height;
    private String alias;
    private String status;
    private Rectangle hitBox;

    TextureRegion textureRegion;

    public Laser(String alias, String color, float xPosition, float yPosition) {
        this.alias = alias;
        this.movementSpeed = alias.equals("Player") ? 45 : -45;
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.width = 0.4f;
        this.height = 4;
        this.textureRegion = GameScreen.textureAtlas.findRegion("Laser" + color);
        this.status = "Alive";
        this.hitBox = new Rectangle(xPosition, yPosition, width, height);

    }

    public void update(float delta) {
        yPosition += movementSpeed * delta;
        hitBox.set(xPosition, yPosition, width, height);

    }

    public void draw(Batch batch){
        batch.draw(textureRegion, xPosition - width/2, yPosition, width, height);
    }

    public Rectangle getHitBox(){
        return hitBox;
    }

    public float getxPosition() {
        return xPosition;
    }

    public float getyPosition() {
        return yPosition;
    }
    public String getAlias() {
        return alias;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    public void setxPosition(float xPosition) {
        this.xPosition = xPosition;
    }

    public void setyPosition(float yPosition) {
        this.yPosition = yPosition;
    }

    public float getMovementSpeed() {
        return movementSpeed;
    }
}
