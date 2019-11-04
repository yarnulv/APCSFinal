package com.game.entities.objects;

import com.game.functions.Character;
import com.game.functions.Location;

public class Tree extends Character {

	public Tree(int health, boolean win, int row, int col) {
		super(1, health, win, row, col);
		super.setDiscovered(true);
	}

	public Location move(String direction) {
		
		return null;
	}

	public String iAm() {
		
		return "tree";
	}

	public String iRespond(String toWhatIsSaid) {
		
		return null;
	}
	
}