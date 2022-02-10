package com.mygdx.jumphero.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class PlayerAnimations {

    private Animation<TextureRegion> runRightAnimation;
    private Animation<TextureRegion> runLeftAnimation;
    private Animation<TextureRegion> idleRightAnimation;
    private Animation<TextureRegion> idleLeftAnimation;
    private Texture animationSheet;

    public PlayerAnimations() {
        this.animationSheet = new Texture(Gdx.files.internal("player_animation_sheet.png"));

        loadRunningLeftAnimation();
        loadRunningRightAnimation();
        loadIdleRightAnimation();
        loadIdleLeftAnimation();

    }

    public void loadRunningLeftAnimation() {
        TextureRegion[] runLeftFrames = new TextureRegion[8];

        System.arraycopy(getTextureRegionFromSheet()[1], 0, runLeftFrames, 0, 8);
        for (int i = 0; i < 8; i++) {
            runLeftFrames[i].flip(true, false);
        }

        this.runLeftAnimation = new Animation<TextureRegion>(0.1f, runLeftFrames);
    }

    public void loadRunningRightAnimation() {
        TextureRegion[] runRightFrames = new TextureRegion[8];

        System.arraycopy(getTextureRegionFromSheet()[1], 0, runRightFrames, 0, 8);

        this.runRightAnimation = new Animation<TextureRegion>(0.1f, runRightFrames);
    }

    public void loadIdleRightAnimation() {
        TextureRegion[] idleFrames = new TextureRegion[5];

        System.arraycopy(getTextureRegionFromSheet()[0], 0, idleFrames, 0 , 5);

        this.idleRightAnimation = new Animation<TextureRegion>(0.1f, idleFrames);
    }
    public void loadIdleLeftAnimation() {
        TextureRegion[] idleFrames = new TextureRegion[5];

        System.arraycopy(getTextureRegionFromSheet()[0], 0, idleFrames, 0 , 5);
        for (int i = 0; i < 5; i++) {
            idleFrames[i].flip(true, false);
        }

        this.idleLeftAnimation = new Animation<TextureRegion>(0.1f, idleFrames);
    }

    public TextureRegion getTexureFrame(float stateTime, boolean isFacingRight, boolean isMoving) {
        if (isFacingRight && isMoving) {
            return runRightAnimation.getKeyFrame(stateTime, true);
        } else if (!isFacingRight && isMoving) {
            return runLeftAnimation.getKeyFrame(stateTime, true);
        } else if (isFacingRight){
            return idleRightAnimation.getKeyFrame(stateTime, true);
        } else {
            return idleLeftAnimation.getKeyFrame(stateTime, true);
        }
    }

    public TextureRegion[][] getTextureRegionFromSheet() {
        return TextureRegion.split(animationSheet,
                animationSheet.getWidth() / 8,
                animationSheet.getHeight() / 8);
    }

}
