package com.mygdx.jumphero.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.jumphero.JumpHero;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.height = JumpHero.D_HEIGHT;
		config.width = JumpHero.D_WIDTH;
		//config.fullscreen = true;
		new LwjglApplication(new JumpHero(), config);
	}
}
