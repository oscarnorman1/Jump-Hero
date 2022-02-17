package com.mygdx.jumphero.renderers;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.jumphero.entities.Player;
import com.mygdx.jumphero.util.TiledObjectUtil;

import static com.mygdx.jumphero.util.Constants.*;

public class StageRenderer {

    private OrthogonalTiledMapRendererBleeding tiledMapRenderer;
    private TiledMap tiledMap;
    private OrthographicCamera camera;
    private Player player;

    public StageRenderer(World world, OrthographicCamera camera, Player player) {
        this.player = player;
        this.camera = camera;
        this.tiledMap = new TmxMapLoader().load("core/assets/fullmap.tmx");
        this.tiledMapRenderer = new OrthogonalTiledMapRendererBleeding(tiledMap, 1f / PPM);

        TiledObjectUtil.parseTiledObjectLayer(world, tiledMap.getLayers().get("platform").getObjects(), "platform");
        TiledObjectUtil.parseTiledObjectLayer(world, tiledMap.getLayers().get("walls").getObjects(), "walls");
        TiledObjectUtil.parseTiledObjectLayer(world, tiledMap.getLayers().get("slope-left").getObjects(), "slope-left");
        TiledObjectUtil.parseTiledObjectLayer(world, tiledMap.getLayers().get("slope-right").getObjects(), "slope-right");
    }

    public void render() {
        if (player.getActualBodyY() > 0 && player.getActualBodyY() < D_HEIGHT / PPM) {
            camera.position.y = (D_HEIGHT / PPM) / 2;
            System.out.println("fdsa");
            camera.update();
        }
        if (player.getActualBodyY() > D_HEIGHT / PPM) {
            camera.position.y = D_HEIGHT / PPM / 2 + ((D_HEIGHT * 2f) / PPM) / 2;
            System.out.println("asdf");
            camera.update();
        }

        this.tiledMapRenderer.setView(camera);
        tiledMapRenderer.render();
    }

}
