package com.mygdx.jumphero.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.jumphero.JumpHero;

import static com.mygdx.jumphero.util.Constants.D_HEIGHT;
import static com.mygdx.jumphero.util.Constants.D_WIDTH;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.height = D_HEIGHT;
		config.width = D_WIDTH;
		config.foregroundFPS = 60;
		config.backgroundFPS = 60;
		config.fullscreen = true;
		new LwjglApplication(new JumpHero(), config);
	}
}
