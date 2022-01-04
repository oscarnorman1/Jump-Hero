package com.mygdx.jumphero;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.jumphero.managers.GameStateManager;
import com.mygdx.jumphero.managers.State;
import com.mygdx.jumphero.renderers.OrthogonalTiledMapRendererBleeding;
import com.mygdx.jumphero.util.TiledObjectUtil;

import static com.mygdx.jumphero.util.Constants.PPM;

public class GameState extends State {

    private final World world;
    private final OrthogonalTiledMapRendererBleeding tiledMapRenderer;
    private final TiledMap tiledMap;
    private Box2DDebugRenderer b2dr;

    public GameState(GameStateManager gsm) {
        super(gsm);
        world = new World(new Vector2(0, -9.8f), true);
        b2dr = new Box2DDebugRenderer();
        //b2dr.setDrawBodies(false);

        cam.setToOrtho(false, JumpHero.D_WIDTH, JumpHero.D_HEIGHT);

        tiledMap = new TmxMapLoader().load("core/assets/Map4.tmx");
        tiledMapRenderer = new OrthogonalTiledMapRendererBleeding(tiledMap, 1f);

        TiledObjectUtil.parseTiledObjectLayer(world, tiledMap.getLayers().get("collision-layer").getObjects());
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
        Gdx.gl.glClearColor(0f, 0f, 0f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        JumpHero.viewport.apply();
        tiledMapRenderer.setView( (OrthographicCamera) JumpHero.viewport.getCamera());
        tiledMapRenderer.render();
        b2dr.render(world, cam.combined);

        if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
            dispose();
            Gdx.app.exit();
        }
    }

    @Override
    public void dispose() {
        tiledMapRenderer.dispose();
        b2dr.dispose();
        world.dispose();
    }

}
