package com.mygdx.jumphero.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

import static com.mygdx.jumphero.util.Constants.PPM;

public class Player extends Sprite {

    private World world;
    private Texture texture;
    private Body player;
    private boolean facingRight;
    private boolean isJumping;
    private Vector2 movement = new Vector2();

    public Player(World world) {
        this.world = world;
        this.texture = new Texture("Player.jpg");
        player = createPlayer();
        setTexture(texture);
        facingRight = true;
    }

    @Override
    public void draw(Batch batch) {
        update(Gdx.graphics.getDeltaTime());
        batch.draw(texture, getX(), getY(), 16.0f / PPM, 16.0f / PPM);
    }

    public Body createPlayer() {
        Body pbody;
        BodyDef bdef = new BodyDef();
        bdef.type = BodyDef.BodyType.DynamicBody;
        bdef.position.set(100.0f / PPM, 25.0f / PPM);
        // JumpHero.D_HEIGHT - (JumpHero.D_HEIGHT - playerWidth - playerWidth / 2f)
        bdef.fixedRotation = true;
        pbody = this.world.createBody(bdef);
        pbody.setUserData(this);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox((16f / 2f) / PPM, (16f / 2f) / PPM);

        pbody.createFixture(shape, 1.0f);
        shape.dispose();
        return pbody;
    }

    public void movePlayer(float x, float y) {
        player.setLinearVelocity(x, y);
    }

    public void stopPlayer() {
        player.setLinearVelocity(0, -9.8f);
    }

    public void jump() {
        if (facingRight) {
            player.setLinearVelocity(3, 10);
        } else {
            player.setLinearVelocity(-3, 10);
        }
        System.out.println("jump");
    }

    public void update(float dt) {

    }

    public boolean isJumping() {
        return isJumping;
    }

    public boolean isFacingRight() {
        return facingRight;
    }

    public void setJumping(boolean jumping) {
        isJumping = jumping;
    }

    public void setFacingRight(boolean facingRight) {
        this.facingRight = facingRight;
    }

    @Override
    public Texture getTexture() {
        return texture;
    }

    public float getX() {
        return player.getPosition().x - 8 / PPM;
    }

    public float getY() {
        return player.getPosition().y - 8 / PPM;
    }
}
