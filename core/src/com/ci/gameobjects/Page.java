package com.ci.gameobjects;

import java.util.LinkedList;
import java.util.List;

public class Page {
	private String story;
	List<String> choices;
	List<Integer> links;
	
	public Page(String story, String choice1, String choice2, String choice3, int link1, int link2, int link3){
		this.setStory(story);
		
		this.choices = new LinkedList<String>();
		this.choices.add(choice1);
		this.choices.add(choice2);
		this.choices.add(choice3);
		
		this.links = new LinkedList<Integer>();
		this.links.add(link1);
		this.links.add(link2);
		this.links.add(link3);
	}
	
	public Page(String story, String choice1, String choice2, String choice3, String link1, String link2, String link3){
		this.setStory(story);
		
		this.choices = new LinkedList<String>();
		if(choice1 != null && !choice1.equals("") && !choice1.equals("None")) this.choices.add(choice1);
		if(choice2 != null && !choice2.equals("") && !choice2.equals("None")) this.choices.add(choice2);
		if(choice3 != null && !choice3.equals("") && !choice3.equals("None")) this.choices.add(choice3);
		
		this.links = new LinkedList<Integer>();
		if(link1 != null && !link1.equals("") && !link1.equals("None")) this.links.add(Integer.parseInt(link1));
		if(link2 != null && !link2.equals("") && !link2.equals("None")) this.links.add(Integer.parseInt(link2));
		if(link3 != null && !link3.equals("") && !link3.equals("None")) this.links.add(Integer.parseInt(link3));
	}

	String getStory() {
		return story;
	}

	void setStory(String story) {
		this.story = story;
	}
	
	public String getChoice(int position){
		return this.choices.get(position);
	}
	
	public List<String> getChoices(){
		return this.choices;
	}
	
	public int getLink(int position){
		return this.links.get(position);
	}
}
