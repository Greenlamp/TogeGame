package com.ci.gameworld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.ci.gameobjects.ChoiceManager;
import com.ci.gameobjects.Page;


public class GameWorld {
    private float runTime = 0;
    private int midPointY;
    ChoiceManager choiceManager;

    private GameState currentState;
	
	Stage stage;

    public enum GameState {
        RUNNING, GAMEOVER
    }

    public GameWorld(int midPointY) {
		stage = new Stage(new ScreenViewport());
        currentState = GameState.RUNNING;
        this.midPointY = midPointY;
        choiceManager = new ChoiceManager(this);
    }
    
    public ChoiceManager getChoiceManager(){
    	return this.choiceManager;
    }

    public void update(float delta) {
        runTime += delta;

        switch (currentState) {
	        case RUNNING:
	            updateRunning(delta);
	            break;
	        default:
	            break;
        }

    }
	
	public Stage getStage(){
		return this.stage;
	}

    public void updateRunning(float delta) {
    }

    public int getMidPointY() {
        return midPointY;
    }

    public void start() {
        currentState = GameState.RUNNING;
    }

    public boolean isGameOver() {
        return currentState == GameState.GAMEOVER;
    }

    public boolean isRunning() {
        return currentState == GameState.RUNNING;
    }
	
	public void selected(int selection){
		this.choiceManager.changePage(selection);
	}
}
