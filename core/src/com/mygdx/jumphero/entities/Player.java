package com.mygdx.jumphero.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

import static com.mygdx.jumphero.util.Constants.PPM;

public class Player extends Sprite {

    private World world;
    private Texture texture;
    private Body player;
    private boolean facingRight;
    private boolean isJumping;

    private Animation<TextureRegion> walkAnimation; // Must declare frame type (TextureRegion)
    private Texture walkSheet;

    private float stateTime;

    public Player(World world) {
        this.world = world;
        this.texture = new Texture("Player.jpg");
        player = createPlayer();
        setTexture(texture);
        facingRight = true;

        this.walkSheet = new Texture(Gdx.files.internal("player_animation_sheet.png"));

        TextureRegion[][] tmp = TextureRegion.split(walkSheet,
                walkSheet.getWidth() / 8,
                walkSheet.getHeight() / 8);

        TextureRegion[] walkFrames = new TextureRegion[8];
        int index = 0;
            for (int j = 0; j < 8; j++) {
                System.out.println(j);
                walkFrames[index++] = tmp[1][j];
            }

        this.walkAnimation = new Animation<TextureRegion>(0.25f, walkFrames);
        this.stateTime = 0f;

        // >
        // <

    }

    @Override
    public void draw(Batch batch) {
        update(Gdx.graphics.getDeltaTime());

        this.stateTime += Gdx.graphics.getDeltaTime();
        TextureRegion currentFrame = walkAnimation.getKeyFrame(stateTime, true);
        batch.draw(currentFrame, getX(), getY(), 1f, 1f);
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
        player.applyLinearImpulse(impulse, 0, getX(), getY(), true);
    }


    public float velocity = 0;
    public boolean isMoving = false;
    public void move() {
        player.applyLinearImpulse(velocity, 0, getX(), getY(), true);
    }

    public void jump(float x, float y) {
        if (facingRight && !isJumping()) {
            player.applyLinearImpulse(x, y, getX(), getY(), true);
        } else if (!isJumping){
            player.applyLinearImpulse(x * -1, y, getX(), getY(), true);
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

    public float getX() {
        return player.getPosition().x - 8 / PPM;
    }

    public float getY() {
        return player.getPosition().y - 8 / PPM;
    }
}
