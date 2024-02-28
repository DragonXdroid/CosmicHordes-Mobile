package com.mygdx.game.Ships;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.GameScreen;
import com.mygdx.game.Laser;

import java.util.Iterator;
import java.util.LinkedList;

public abstract class SpaceShip {

    //stats
    private float movement; //world units per second
    private int sheild;
    private String alias;
    private String color;
    private String shipType;
    private float laserCoolDownTimer;
    private Rectangle hitBox;
    private LinkedList<Laser> lasers = new LinkedList<>();

    //position & dimension
    private float xPosition;
    private float yPosition;
    private float width;
    private float height;

    // graphics
    private TextureRegion shipTexture;
    private TextureRegion shieldTexture;


    public SpaceShip(String alias, String shipType, String color, float xPosition, float yPosition){

        this.alias = alias;
        this.color = color;
        this.shipType = shipType;
        this.movement = 5;
        this.sheild = 50;
        this.width = 10;
        this.height = 10;
        this.laserCoolDownTimer = 0.5f;
        this.xPosition = xPosition - width/2;
        this.yPosition = yPosition - height/2;
        this.hitBox = new Rectangle(xPosition, yPosition, width, height);
        this.shipTexture = GameScreen.textureAtlas.findRegion(alias+shipType+color);

        setShieldTexture(GameScreen.textureAtlas.findRegion(alias+"Shield"));
    }

    public void setShieldTexture(TextureRegion shieldTexture) {
        this.shieldTexture = shieldTexture;
        if (alias.equals("Enemy")){
            shieldTexture.flip(false,true);
        }
    }

    public boolean intersects(Rectangle rectangle){
        return hitBox.overlaps(rectangle);
    }


    public void update(float delta){
        if (laserCoolDownTimer > 0){
            laserCoolDownTimer -= delta;
        }
        hitBox.set(xPosition,yPosition,width,height);

        Iterator<Laser> iterator = lasers.iterator();
        while (iterator.hasNext()) {
            Laser laser = iterator.next();
            if (laser.getStatus().equals("Dead")) {
                iterator.remove(); // Remove the laser using the iterator
            }
        }

    }

    public abstract void fireLasers();
    public abstract void draw(Batch batch);
    public void hit(Laser laser) {
    }

    public float getMovement() {
        return movement;
    }

    public int getSheild() {
        return sheild;
    }

    public String getAlias() {
        return alias;
    }

    public String getColor() {
        return color;
    }

    public String getShipType() {
        return shipType;
    }

    public float getLaserCoolDownTimer() {
        return laserCoolDownTimer;
    }

    public void setLaserCoolDownTimer(float laserCoolDownTimer) {
        this.laserCoolDownTimer = laserCoolDownTimer;
    }

    public LinkedList<Laser> getLasers() {
        return lasers;
    }

    public float getxPosition() {
        return xPosition;
    }

    public float getyPosition() {
        return yPosition;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

    public TextureRegion getShipTexture() {
        return shipTexture;
    }

    public TextureRegion getShieldTexture() {
        return shieldTexture;
    }


}