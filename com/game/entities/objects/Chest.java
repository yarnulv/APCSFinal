package com.game.entities.objects;

import java.util.ArrayList;

import com.game.functions.Character;
import com.game.functions.Location;

public class Chest extends Character {
	
	private ArrayList<Character> contents;

	public Chest(int health, boolean win, int row, int col) {
		super(3, health, win, row, col);
		
		contents = new ArrayList<Character>();
	}
	
	public void addToChest(Character entity) {
		contents.add(entity);
	}
	
	public ArrayList<Character> getContents() {
		return contents;
	}

	public Location move(String direction) {
		return null;
	}

	public String iAm() {
		
		return "chest";
	}

	public String iRespond(String toWhatIsSaid) {
		
		return null;
	}
	
}