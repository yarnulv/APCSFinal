package com.game.entities.NPCs;

import com.game.functions.Character;
import com.game.functions.Location;

public class Pig extends Character {

	public Pig(int health, boolean win, int row, int col) {
		super(5, health, win, row, col);
		
	}

	public Location move(String direction) {
		
		return null;
	}

	public String iAm() {
		
		return "pig";
	}

	public String iRespond(String toWhatIsSaid) {
		
		return null;
	}
	
}