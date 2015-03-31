package com.ci.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Timer;
import com.ci.gameworld.GameRenderer;
import com.ci.gameworld.GameWorld;
import com.ci.zbHelpers.AssetLoader;
import com.ci.zbHelpers.InputHandler;
import com.sun.jmx.snmp.tasks.Task;

public class GameScreen implements Screen{

	private GameWorld world;
	private GameRenderer renderer;
	private float runTime;

	// This is the constructor, not the class declaration
	public GameScreen() {

		float screenWidth = Gdx.graphics.getWidth();
		float screenHeight = Gdx.graphics.getHeight();
		float gameWidth = 136;
		float gameHeight = screenHeight / (screenWidth / gameWidth);
		int midPointY = (int) (gameHeight / 2);

		world = new GameWorld(midPointY);
		Gdx.input.setInputProcessor(new InputHandler(world));
		renderer = new GameRenderer(world, (int) gameHeight, midPointY);
		AssetLoader.route.play();
		AssetLoader.route.setLooping(true);
	}

	@Override
	public void render(float delta) {
		runTime += delta;
		world.update(delta);
		renderer.render(delta, runTime);
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void show() {
	}

	@Override
	public void hide() {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}

	@Override
	public void dispose() {
		world.getStage().dispose();
		AssetLoader.music.dispose();
		
	}
}
