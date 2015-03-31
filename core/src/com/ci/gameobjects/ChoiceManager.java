package com.ci.gameobjects;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.ci.gameworld.GameWorld;
import com.ci.zbHelpers.AssetLoader;

public class ChoiceManager {
	GameWorld gameWorld;
	Page currentPage;
	Map<Integer, Page> pages;
	
	public ChoiceManager(GameWorld gameWorld){
		this.gameWorld = gameWorld;
		pages = new HashMap<Integer, Page>();
		try {
			initPages();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		currentPage = pages.get(1);
	}
	
	public void initPages() throws IOException{
		FileHandle scenario = AssetLoader.scenario;
		BufferedReader reader = new BufferedReader(scenario.reader());
		//List<String> lines = new ArrayList<String>();
		String line = reader.readLine();
		int cpt = 1;
		while(line != null) {
			Gdx.app.log("ZBGame", "cpt: " + cpt);
			String[] split = line.split("#");
			process(split, cpt);
			cpt++;
			line = reader.readLine();
		}
	}
	
	private void process(String[] line, int cpt) {
		String story = line[0];
		String link1 = line[1];
		String link2 = line[2];
		String link3 = line[3];
		String choice1 = line[4];
		String choice2 = line[5];
		String choice3 = line[6];
		
		Page page = new Page(story, choice1, choice2, choice3, link1, link2, link3);
		pages.put(cpt, page);
	}

	public void changePage(int selection){
		if(selection == -1){
			currentPage = pages.get(1);
		}else{
			Gdx.app.log("ZBGame", "selection: " + selection);
			int link = currentPage.getLink(selection);
			Gdx.app.log("ZBGame", "link: " + link);
			this.currentPage = pages.get(link);
			Gdx.app.log("ZBGame", "currentPage: " + currentPage.getStory());
		}
	}
	
	public String getStory(){
		return this.currentPage.getStory();
	}
	
	public String getChoice(int position){
		return this.currentPage.getChoice(position);
	}
	
	public List<String> getChoices(){
		return this.currentPage.getChoices();
	}
}
