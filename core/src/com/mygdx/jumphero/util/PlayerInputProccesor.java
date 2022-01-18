package com.mygdx.jumphero.util;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.mygdx.jumphero.entities.Player;

public class PlayerInputProccesor implements InputProcessor {

    private Player player;
    private Long snapTime;
    private float timeElapsed;

    public PlayerInputProccesor(Player player) {
        this.player = player;
    }

    @Override
    public boolean keyDown(int keycode) {
        if (keycode == Input.Keys.SPACE) {
            snapTime = System.currentTimeMillis();
            return true;
        }
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        if (keycode == Input.Keys.SPACE) {
            //<
            //>
            timeElapsed = (float) (System.currentTimeMillis() - snapTime) / 1000f;
            if (timeElapsed < 0.5f) {
                player.jump(5f, 9f);
            } else if (timeElapsed > 0.5f && timeElapsed < 1f) {
                player.jump(6f, 12f);
            } else if(timeElapsed > 1) {
                player.jump(7f, 15f);
            }

            System.out.println(timeElapsed);
            return true;
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
