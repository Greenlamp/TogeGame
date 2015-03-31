package com.ci.gameworld;

import java.util.List;

import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenEquations;
import aurelienribon.tweenengine.TweenManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.ci.TweenAccessors.Value;
import com.ci.TweenAccessors.ValueAccessor;
import com.ci.gameobjects.MyClickListener;

public class GameRenderer {

	private GameWorld myWorld;
	private OrthographicCamera cam;
	private ShapeRenderer shapeRenderer;
	private SpriteBatch batcher;
	
	float screenWidth, screenHeight;
	Table table;
	Skin skin;
	float scale;
	int cpt = 0;

	// Tween stuff
	private TweenManager manager;
	private Value alpha = new Value();

	public GameRenderer(GameWorld world, int gameHeight, int midPointY) {
		myWorld = world;
		screenWidth = Gdx.graphics.getWidth();
		screenHeight = Gdx.graphics.getHeight();
		Gdx.app.log("ZBGame", "screenWidth: "+ screenWidth);
		Gdx.app.log("ZBGame", "screenHeight: " + screenHeight);

		cam = new OrthographicCamera();
		cam.setToOrtho(true, 136, gameHeight);

		batcher = new SpriteBatch();
		batcher.setProjectionMatrix(cam.combined);
		shapeRenderer = new ShapeRenderer();
		shapeRenderer.setProjectionMatrix(cam.combined);
		
		Gdx.input.setInputProcessor(myWorld.getStage());
		initGameObjects();
		initAssets();
		setupTweens();

	}

	private void setupTweens() {
		Tween.registerAccessor(Value.class, new ValueAccessor());
		manager = new TweenManager();
		Tween.to(alpha, -1, .5f).target(0).ease(TweenEquations.easeOutQuad)
				.start(manager);
	}

	private void initGameObjects() {
	}

	private void initAssets() {
		skin = new Skin(Gdx.files.internal("data/uiskin.json"));
		
		scale = 1;
		Table table = new Table();
		myWorld.getStage().clear();
		myWorld.getStage().addActor(table);
		
		setStory(table, myWorld.getChoiceManager().getStory());
		setChoices(table, myWorld.getChoiceManager().getChoices());
		
		table.setPosition(50, screenHeight - table.getPrefHeight() - 100);

		table.pack();
	}

	private void setChoices(Table table, List<String> choices) {
		for(String choice: choices){
			TextButton bouton = new TextButton(choice,skin);
			bouton.getLabel().setFontScale(2.0f);
			bouton.setWidth(screenWidth);
			bouton.getLabel().setWrap(true);
			table.add(bouton).minWidth(screenWidth - 100).minHeight(110 * scale).fill();
			table.row();
			
			MyClickListener mcl = new MyClickListener(myWorld, choices.indexOf(choice));
			bouton.addListener(mcl);
		}
		table.row();
		TextButton bouton = new TextButton("Recommencer",skin);
		bouton.getLabel().setFontScale(2.0f);
		bouton.setWidth(screenWidth);
		bouton.getLabel().setWrap(true);
		table.add(bouton).minWidth(screenWidth - 100).minHeight(110 * scale).fill();
		
		MyClickListener mcl = new MyClickListener(myWorld, -1);
		bouton.addListener(mcl);
	}

	private void setStory(Table table, String story) {
		Label label = new Label(story, skin);
		label.setFontScale(2.0f);
		label.setWrap(true);
		label.setWidth(screenWidth);
		label.setAlignment(Align.bottom | Align.left);
		table.add(label).minWidth(screenWidth - 100).minHeight(110 * scale).fill();
		table.row();
	}

	public void render(float delta, float runTime) {

		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		if (myWorld.isRunning()) {
			initAssets();
			
			myWorld.getStage().act(delta);
			myWorld.getStage().draw();
		}
		
		drawTransition(delta);

		
		//272
		/*CharSequence str = "Width: " + String.valueOf(screenWidth);
		SpriteBatch spriteBatch = new SpriteBatch();
		BitmapFont font = new BitmapFont();

		spriteBatch.begin();
		font.draw(spriteBatch, str, 0, screenHeight);
		str = "Height: " + String.valueOf(screenHeight);
		font.draw(spriteBatch, str, 100, screenHeight);
		spriteBatch.end();*/

	}

	private void drawTransition(float delta) {
		if (alpha.getValue() > 0) {
			manager.update(delta);
			Gdx.gl.glEnable(GL20.GL_BLEND);
			Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
			shapeRenderer.begin(ShapeType.Filled);
			shapeRenderer.setColor(1, 1, 1, alpha.getValue());
			shapeRenderer.rect(0, 0, 136, 300);
			shapeRenderer.end();
			Gdx.gl.glDisable(GL20.GL_BLEND);

		}
	}
}
