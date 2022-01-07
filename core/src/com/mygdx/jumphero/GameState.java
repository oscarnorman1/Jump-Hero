package com.mygdx.jumphero;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.Array;
import com.mygdx.jumphero.entities.Player;
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
    private Player player;
    private Array<Body> tmpBodies = new Array<Body>();

    public GameState(GameStateManager gsm) {
        super(gsm);
        world = new World(new Vector2(0, -9.8f), false);
        b2dr = new Box2DDebugRenderer();
        //b2dr.setDrawBodies(false);
        player = new Player(world);

        cam.setToOrtho(false, JumpHero.D_WIDTH / PPM, JumpHero.D_HEIGHT / PPM);

        tiledMap = new TmxMapLoader().load("core/assets/Map4.tmx");
        tiledMapRenderer = new OrthogonalTiledMapRendererBleeding(tiledMap, 1f / PPM);

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
        sb.setProjectionMatrix(cam.combined);

        sb.begin();
        world.getBodies(tmpBodies);
        for (Body body : tmpBodies)
            if (body.getUserData() instanceof Player) {
                Player player = (Player) body.getUserData();
                player.setPosition(player.getX(), player.getY());
                player.draw(sb);
            }
        sb.end();

        if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
            dispose();
            Gdx.app.exit();
        }

        if (Gdx.input.isKeyPressed((Input.Keys.RIGHT))) {
            player.movePlayer(10.0f, 0);
        }

    }

    @Override
    public void dispose() {
        tiledMapRenderer.dispose();
        b2dr.dispose();
        world.dispose();
    }

}
