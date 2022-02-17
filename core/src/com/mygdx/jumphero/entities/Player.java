package com.mygdx.jumphero.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.mygdx.jumphero.util.PlayerAnimations;

import static com.mygdx.jumphero.util.Constants.PPM;

public class Player extends Sprite {

    private World world;
    private Body player;
    public float velocity = 0;
    public boolean isMoving = false;
    private boolean facingRight;
    private boolean isJumping;

    private PlayerAnimations playerAnimations;

    private float stateTime;

    public Player(World world) {
        this.world = world;
        this.stateTime = 0f;
        player = createPlayer();
        facingRight = true;

        this.playerAnimations = new PlayerAnimations();
    }

    @Override
    public void draw(Batch batch) {
        update(Gdx.graphics.getDeltaTime());

        this.stateTime += Gdx.graphics.getDeltaTime();

        TextureRegion temp = playerAnimations.getTexureFrame(stateTime, facingRight, isMoving);
        batch.draw(temp, getBodyX(), getBodyY(), temp.getRegionWidth() / PPM, temp.getRegionHeight() / PPM);

    }

    public Body createPlayer() {
        Body pbody;
        BodyDef bdef = new BodyDef();
        bdef.type = BodyDef.BodyType.DynamicBody;
        bdef.position.set(70.0f / PPM, 80f / PPM);
        bdef.fixedRotation = true;

        pbody = this.world.createBody(bdef);
        pbody.setUserData(this);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox((16f / 2f) / PPM, (16f / 2f) / PPM);
        FixtureDef fdef = new FixtureDef();
        fdef.shape = shape;
        fdef.friction = 100f;

        pbody.createFixture(fdef);
        shape.dispose();
        return pbody;
    }

    public void movePlayer(float impulse) {
        player.applyLinearImpulse(impulse, 0, getBodyX(), getBodyY(), true);
    }
    public void move() {
        player.applyLinearImpulse(velocity, 0, getBodyX(), getBodyY(), true);
    }

    public void jump(float x, float y) {
        if (facingRight && !isJumping()) {
            player.applyLinearImpulse(x, y, getBodyX(), getBodyY(), true);
        } else if (!isJumping){
            player.applyLinearImpulse(x * -1, y, getBodyX(), getBodyY(), true);
        }
    }

    public void slopeLeftGlide() {
        player.setLinearVelocity(4.0f, 0f);
    }

    public void slopeRightGlide() {
        player.setLinearVelocity(-4.0f, 0f);
    }

    public void update(float dt) {
        if (isMoving) {
            move();
        }
    }

    public boolean isJumping() {
        return isJumping;
    }

    public boolean isFacingRight() {
        return facingRight;
    }

    public Body getPlayerBody() {
        return player;
    }

    public void setJumping(boolean jumping) {
        isJumping = jumping;
    }

    public void setFacingRight(boolean facingRight) {
        this.facingRight = facingRight;
    }

    public float getBodyX() {
        return player.getPosition().x - 32 / PPM;
    }

    public float getBodyY() {
        return player.getPosition().y - 25 / PPM;
    }

    public float getActualBodyY() {
        return player.getPosition().y - 8 / PPM;
    }
    public float getActualBodyX() {
        return player.getPosition().x - 8 / PPM;
    }

}
