package com.mygdx.jumphero.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.mygdx.jumphero.entities.Player;
import com.mygdx.jumphero.managers.GameStateManager;

public class PlayerInputProccesor implements InputProcessor {

    private Player player;
    private GameStateManager gsm;

    private Long snapTime;
    private float timeElapsed;

    public PlayerInputProccesor(Player player, GameStateManager gsm) {
        this.player = player;
        this.gsm = gsm;
    }

    @Override
    public boolean keyDown(int keycode) {
        if (keycode == Input.Keys.SPACE) {
            snapTime = System.currentTimeMillis();
            return true;
        }

        if (keycode == Input.Keys.ESCAPE) {
            //dispose();
            Gdx.app.exit();
        }

        if (keycode == Input.Keys.RIGHT) {
            if (!player.isJumping()) {
                player.setFacingRight(true);
                /*player.movePlayer(10f, 0f);*/
                player.isMoving = true;
                player.velocity = 3;
            }
        }

        if (keycode == Input.Keys.LEFT) {
            if (!player.isJumping()) {
                player.setFacingRight(false);
                /*player.movePlayer(-10f, 0.f);*/
                player.isMoving = true;
                player.velocity = -3;
            }
        }

        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        if (keycode == Input.Keys.SPACE) {

            timeElapsed = (float) (System.currentTimeMillis() - snapTime) / 1000f;
            if (timeElapsed < 0.5f) {
                player.jump(3f, 4f);
            } else if (timeElapsed > 0.5f && timeElapsed < 1f) {
                player.jump(4f, 6f);
            } else if(timeElapsed > 1) {
                player.jump(5f, 8f);
            }

            System.out.println(timeElapsed);
            return true;
        }

        if (keycode == Input.Keys.RIGHT) {
            if (!player.isJumping()) {
                player.setFacingRight(true);
                /*player.movePlayer(10f, 0f);*/
                player.isMoving = false;
                player.velocity = 0;
            }
        }

        if (keycode == Input.Keys.LEFT) {
            if (!player.isJumping()) {
                player.setFacingRight(false);
                /*player.movePlayer(-10f, 0.f);*/
                player.isMoving = false;
                player.velocity = 0;
            }
        }

        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        return false;
    }
}
