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
        System.out.println(camera.position.y);

        TiledObjectUtil.parseTiledObjectLayer(world, tiledMap.getLayers().get("platform").getObjects(), "platform");
        TiledObjectUtil.parseTiledObjectLayer(world, tiledMap.getLayers().get("walls").getObjects(), "walls");
    }

    public void render() {
        if (player.getY() > 0 && player.getY() < D_HEIGHT / PPM) {
            camera.position.y = (D_HEIGHT / PPM) / 2;
            camera.update();
        }
        if (player.getY() > D_HEIGHT / PPM) {
            camera.position.y = D_HEIGHT / PPM / 2 + ((D_HEIGHT * 2f) / PPM) / 2;
            camera.update();
        }


        this.tiledMapRenderer.setView(camera);
        //tiledMapRenderer.render();
    }

}
