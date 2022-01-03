package com.mygdx.jumphero;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class Game extends ApplicationAdapter {

	@Override
	public void create() {

	}

	@Override
	public void render() {


		if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
			System.exit(0);
			dispose();
		}
	}

	@Override
	public void dispose() {

	}
}
