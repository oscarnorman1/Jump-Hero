package com.mygdx.jumphero;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.jumphero.managers.GameStateManager;
import com.mygdx.jumphero.states.GameState;

import static com.mygdx.jumphero.util.Constants.PPM;

public class JumpHero extends ApplicationAdapter {

	public static final int D_WIDTH = 560;
	public static final int D_HEIGHT = 320;

	public static Viewport viewport;

	private GameStateManager gsm;
	private SpriteBatch sb;

	@Override
	public void create() {
		viewport = new FitViewport(D_WIDTH / PPM, D_HEIGHT / PPM);
		gsm = new GameStateManager();
		sb = new SpriteBatch();
		gsm.push(new GameState(gsm));
	}

	@Override
	public void render() {
		gsm.render(sb);
		gsm.update(Gdx.graphics.getDeltaTime());
	}

	@Override
	public void dispose() {

	}

	@Override
	public void resize(int width, int height) {
		viewport.update(width, height, true);
	}
}
