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
    private Texture texture;
    private Body player;
    private boolean facingRight;
    private boolean isJumping;

    private Animation<TextureRegion> runRightAnimation; // Must declare frame type (TextureRegion)
    private Animation<TextureRegion> runLeftAnimation; // Must declare frame type (TextureRegion)
    private Texture animationSheet;

    private PlayerAnimations playerAnimations;

    private float stateTime;

    public Player(World world) {
        this.world = world;
        this.texture = new Texture("Player.jpg");
        player = createPlayer();
        setTexture(texture);
        facingRight = true;

        this.playerAnimations = new PlayerAnimations();
        this.animationSheet = new Texture(Gdx.files.internal("player_animation_sheet.png"));

        TextureRegion[][] tmp = TextureRegion.split(animationSheet,
                animationSheet.getWidth() / 8,
                animationSheet.getHeight() / 8);

        TextureRegion[] runRightFrames = new TextureRegion[8];
        TextureRegion[] runLeftFrames = new TextureRegion[8];
        int rightIndex = 0;
        int leftIndex = 0;
            for (int i = 0; i < 8; i++) {
                runRightFrames[rightIndex++] = tmp[1][i];
            }
            for (int i = 0; i < 8; i++) {
                runLeftFrames[leftIndex++] = tmp[1][i];
            }
            for (int i = 0; i < 8; i++) {
                runLeftFrames[i].flip(true, false);
            }
            for (int i = 0; i < 8; i++) {
                runRightFrames[i].flip(false, false);
            }

        this.runRightAnimation = new Animation<TextureRegion>(0.1f, runRightFrames);
        this.runLeftAnimation = new Animation<TextureRegion>(0.1f, runLeftFrames);
        this.stateTime = 0f;

        // >
        // <

    }

    @Override
    public void draw(Batch batch) {
        update(Gdx.graphics.getDeltaTime());

        this.stateTime += Gdx.graphics.getDeltaTime();

/*        TextureRegion currentRunRightFrame = runRightAnimation.getKeyFrame(stateTime, true);
        TextureRegion currentRunLeftFrame = runLeftAnimation.getKeyFrame(stateTime, true);*/


/*        if (isMoving && isFacingRight() && !isJumping) {
            batch.draw(currentRunRightFrame, getBodyX(), getBodyY(), currentRunRightFrame.getRegionWidth() / PPM, currentRunRightFrame.getRegionHeight() / PPM);
        } else if(isMoving && !isFacingRight() && !isJumping) {
            batch.draw(currentRunLeftFrame, getBodyX(), getBodyY(), currentRunLeftFrame.getRegionWidth() / PPM, currentRunLeftFrame.getRegionHeight() / PPM);
        }*/
        TextureRegion temp = playerAnimations.getTexureFrame(stateTime, facingRight, isMoving);
        batch.draw(temp, getBodyX(), getBodyY(), temp.getRegionWidth() / PPM, temp.getRegionHeight() / PPM);

    }

    public Body createPlayer() {
        Body pbody;
        BodyDef bdef = new BodyDef();
        bdef.type = BodyDef.BodyType.DynamicBody;
        bdef.position.set(70.0f / PPM, 80f / PPM);
        /*bdef.position.set(100.0f / PPM, 25.0f / PPM);*/
        // JumpHero.D_HEIGHT - (JumpHero.D_HEIGHT - playerWidth - playerWidth / 2f)
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


    public float velocity = 0;
    public boolean isMoving = false;
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

    @Override
    public Texture getTexture() {
        return texture;
    }

    public float getBodyX() {
        return player.getPosition().x - 32 / PPM;
    }

    public float getBodyY() {
        return player.getPosition().y - 25 / PPM;
    }
}
