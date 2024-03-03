package com.mygdx.game.Ships;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.Screens.GameScreen;
import com.mygdx.game.Laser;

import java.util.Iterator;
import java.util.LinkedList;

public abstract class SpaceShip {

    private float speed; //world units per second
    private float sheild;
    private String alias;
    private String color;
    private String shipType;
    private float laserCoolDownTimer;
    private float health;
    private LinkedList<Laser> lasers = new LinkedList<>();

    //position & dimension
    private Rectangle hitBox;

    // graphics
    private TextureRegion shipTexture;
    private TextureRegion shieldTexture;


    public SpaceShip(String alias, String shipType, String color, float xPosition, float yPosition,
                     float speed, float shield, float width, float height, float laserCoolDownTimer,
                     float health){

        this.alias = alias;
        this.health = health;
        this.color = color;
        this.shipType = shipType;
        this.speed = speed;
        this.sheild = shield;
        this.hitBox = new Rectangle(xPosition - width/2, yPosition - height/2, width, height);
        this.laserCoolDownTimer = laserCoolDownTimer;
        this.shipTexture = GameScreen.textureAtlas.findRegion(alias+shipType+color);

        setShieldTexture(GameScreen.textureAtlas.findRegion(alias+"Shield"));
    }

    public void translate(float xChange, float yChange){
        hitBox.setPosition(hitBox.x + xChange, hitBox.y + yChange);
    }



    public boolean intersects(Rectangle rectangle){
        return hitBox.overlaps(rectangle);
    }

    public abstract void shipBehavior(float delta);


    public void update(float delta){

        if (alias.equals("Enemy")){
            shipBehavior(delta);
        }

        if (laserCoolDownTimer > 0){
            laserCoolDownTimer -= delta;
        }

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
        if (sheild >= 0){
            sheild -= laser.getDamage();
        } else {
            health -= laser.getDamage();
        }
        if (alias.equals("Enemy")){
            System.out.println(health);
        }

    }

    public void setShieldTexture(TextureRegion shieldTexture) {
        this.shieldTexture = shieldTexture;
        if (alias.equals("Enemy")){
            shieldTexture.flip(false,true);
        }
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public float getSpeed() {
        return speed;
    }

    public float getSheild() {
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

    public TextureRegion getShipTexture() {
        return shipTexture;
    }

    public TextureRegion getShieldTexture() {
        return shieldTexture;
    }

    public Rectangle getHitBox() {
        return hitBox;
    }

    public float getHealth() {
        return health;
    }

    public void setHealth(float health) {
        this.health = health;
    }
}