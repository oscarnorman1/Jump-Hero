package com.mygdx.jumphero.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.mygdx.jumphero.managers.GameStateManager;
import com.mygdx.jumphero.managers.State;

public class RandomState extends State {

    private GameState gameState;
    private Table table;
    private Label labelOne;
    private Skin font;

    protected RandomState(GameStateManager gsm, GameState gameState) {
        super(gsm);
        this.gameState = gameState;
        table = new Table();
        font = new Skin();
        //labelOne = new Label("Hej");
    }

    @Override
    public void handleInput() {

    }

    @Override
    public void update(float dt) {

    }

    @Override
    public void render(SpriteBatch sb) {
        Gdx.gl.glClearColor(0f, 0f, 0f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        if (Gdx.input.isKeyPressed(Input.Keys.T)) {
            this.gsm.push(this.gameState);
        }
    }

    @Override
    public void dispose() {

    }
}
