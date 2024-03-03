package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.mygdx.game.Screens.GameScreen;

public class CosmicHordesMobile extends Game {
	GameScreen gameScreen;

	public static final float WORLD_WIDTH = 72;
	public static final float WORLD_HEIGHT = 128;
	
	@Override
	public void create () {
		gameScreen = new GameScreen( );
		this.setScreen(gameScreen);
	}

	@Override
	public void render() {
		super.render();
	}

	@Override
	public void resize(int width, int height) {
		gameScreen.resize(width, height);
	}

	@Override
	public void dispose() {
		gameScreen.dispose();
	}
}
