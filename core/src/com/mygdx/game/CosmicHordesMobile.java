package com.mygdx.game;

import com.badlogic.gdx.Game;

public class CosmicHordesMobile extends Game {
	GameScreen gameScreen;
	
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
