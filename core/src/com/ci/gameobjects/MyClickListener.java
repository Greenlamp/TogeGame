package com.ci.gameobjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.ci.gameworld.GameWorld;

public class MyClickListener extends ClickListener{
	
	int selection;
	GameWorld myWorld;
	
	public MyClickListener(GameWorld myWorld, int selection){
		super();
		this.selection = selection;
		this.myWorld = myWorld;
	}
	public boolean touchDown(InputEvent event, float x, float y, int pointer, int button)
	{
		myWorld.selected(selection);
		return false;
	}
}
