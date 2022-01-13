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
                player.stopPlayer();
                System.out.println("Player is not jumping");
            }
        }
        System.out.println("FA = " + fa.getBody().getUserData().toString());
        System.out.println("FB = " + fb.getBody().getUserData().toString());

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
            player.setJumping(fa.getBody().getUserData() != "walls" && fb.getBody().getUserData() != "walls");
        }
        System.out.println("FA = " + fa.getBody().getUserData().toString());
        System.out.println("FB = " + fb.getBody().getUserData().toString());
    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}
