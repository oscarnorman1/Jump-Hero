package com.mygdx.jumphero.managers;

import com.badlogic.gdx.physics.box2d.*;
import com.mygdx.jumphero.entities.Player;
import com.mygdx.jumphero.states.GameState;

public class B2dContactListener implements ContactListener {

    private GameState gameState;


    public B2dContactListener(GameState gameState) {
        this.gameState = gameState;
    }

    @Override
    public void beginContact(Contact contact) {
        Fixture fa = contact.getFixtureA();
        Fixture fb = contact.getFixtureB();

        if (fa == null || fb == null)
            return;
        if (fa.getBody().getUserData() == null || fa.getBody().getUserData() == null)
            return;

        Object udA = fa.getBody().getUserData();
        Object udB = fb.getBody().getUserData();

        Player player = null;

        if (udA instanceof Player) {
            player = (Player) udA;
        }

        if (udB instanceof Player) {
            player = (Player) udB;
        }

        if (player != null) {
            if (fa.getBody().getUserData() == "platform" || fb.getBody().getUserData() == "platform") {
                player.setJumping(false);
                player.getPlayerBody().setLinearDamping(20f);
                System.out.println("touching platform");
            } else if (fa.getBody().getUserData() != "walls" && fb.getBody().getUserData() != "platform"
                    || fa.getBody().getUserData() != "platform" && fb.getBody().getUserData() != "walls") {

                player.setJumping(false);
            }
        }
/*        System.out.println("FA = " + fa.getBody().getUserData().toString());
        System.out.println("FB = " + fb.getBody().getUserData().toString());*/

    }

    @Override
    public void endContact(Contact contact) {
        Fixture fa = contact.getFixtureA();
        Fixture fb = contact.getFixtureB();

        if (fa == null || fb == null)
            return;
        if (fa.getBody().getUserData() == null || fa.getBody().getUserData() == null)
            return;

        Object udA = fa.getBody().getUserData();
        Object udB = fb.getBody().getUserData();

        Player player = null;

        if (udA instanceof Player) {
            player = (Player) udA;
        }

        if (udB instanceof Player) {
            player = (Player) udB;
        }

        if (player != null) {
            player.isMoving = false;
            if (fa.getBody().getUserData() != "walls" && fb.getBody().getUserData() != "walls") {
                player.setJumping(true);
                player.getPlayerBody().setLinearDamping(0);
                System.out.println("touching walls");
            }
        }

/*        System.out.println("FA = " + fa.getBody().getUserData().toString());
        System.out.println("FB = " + fb.getBody().getUserData().toString());*/
    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}
