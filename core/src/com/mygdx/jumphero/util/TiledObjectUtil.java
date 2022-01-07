package com.mygdx.jumphero.util;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.objects.PolylineMapObject;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

import static com.mygdx.jumphero.util.Constants.PPM;

public class TiledObjectUtil {
    public static void parseTiledObjectLayer(World world, MapObjects mapObjects) {
        for (MapObject object : mapObjects) {
            Shape shape;
            if (object instanceof PolylineMapObject)
                shape = createPolyLine((PolylineMapObject) object);

            else
                continue;

            Body body;
            BodyDef bdef = new BodyDef();
            bdef.type = BodyDef.BodyType.StaticBody;
            body = world.createBody(bdef);
            body.createFixture(shape, 1.0f);
            shape.dispose();
        }
    }

    public static ChainShape createPolyLine(PolylineMapObject polyLine) {
        float[] vertices = polyLine.getPolyline().getTransformedVertices();
        Vector2[] worldVertices = new Vector2[vertices.length / 2];

        for (int i = 0; i < worldVertices.length; i++) {
            worldVertices[i] = new Vector2(vertices[i * 2] / PPM, vertices[i * 2 + 1] / PPM);
        }
        ChainShape cs = new ChainShape();
        cs.createChain(worldVertices);
        return cs;
    }


}
