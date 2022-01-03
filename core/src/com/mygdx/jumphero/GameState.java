package com.mygdx.jumphero;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.jumphero.managers.GameStateManager;
import com.mygdx.jumphero.managers.State;
import com.mygdx.jumphero.renderers.OrthogonalTiledMapRendererBleeding;

import static com.mygdx.jumphero.util.Constants.PPM;

public class GameState extends State {

    private final World world;
    private final OrthogonalTiledMapRendererBleeding tiledMapRenderer;
    private final TiledMap tiledMap;

    public GameState(GameStateManager gsm) {
        super(gsm);
        world = new World(new Vector2(0, -9.8f), true);

        tiledMap = new TmxMapLoader().load("core/assets/Map4.tmx");
        tiledMapRenderer = new OrthogonalTiledMapRendererBleeding(tiledMap, 1f);
    }

    @Override
    public void handleInput() {

    }

    @Override
    public void update(float dt) {
        world.step(dt, 6, 2);
    }

    @Override
    public void render(SpriteBatch sb) {
        ScreenUtils.clear(0f, 0f, 0f, 1f);
        JumpHero.viewport.apply();
        tiledMapRenderer.setView( (OrthographicCamera) JumpHero.viewport.getCamera());
        tiledMapRenderer.render();
    }

    @Override
    public void dispose() {

    }

}
