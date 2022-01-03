package com.mygdx.jumphero.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.jumphero.JumpHero;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.height = 1920;
		config.width = 1080;
		config.fullscreen = true;
		new LwjglApplication(new JumpHero(), config);
	}
}
