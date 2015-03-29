package com.ci.zbHelpers;

import java.io.File;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AssetLoader {
    private static Preferences prefs;
    public static FileHandle  scenario;
    
    public static void load() {
    	prefs = Gdx.app.getPreferences("ZombieBird");
    	if (!prefs.contains("highScore")) {
    	    prefs.putInteger("highScore", 0);
    	}
    	scenario = Gdx.files.internal("data/scenario.txt");
    }
    
 // Receives an integer and maps it to the String highScore in prefs
    public static void setHighScore(int val) {
        prefs.putInteger("highScore", val);
        prefs.flush();
    }

    // Retrieves the current high score
    public static int getHighScore() {
        return prefs.getInteger("highScore");
    }

    public static void dispose() {
    }
}
