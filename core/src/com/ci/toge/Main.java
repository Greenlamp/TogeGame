package com.ci.toge;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.ci.screens.GameScreen;
import com.ci.zbHelpers.AssetLoader;

public class Main extends Game {
	
	@Override
	public void create () {
		Gdx.app.log("ZBGame", "created");
		AssetLoader.load();
		setScreen(new GameScreen());
	}

    @Override
    public void dispose() {
        super.dispose();
        AssetLoader.dispose();
    }
}
