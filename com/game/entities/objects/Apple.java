package com.game.entities.objects;

import com.game.functions.Character;
import com.game.functions.Location;

public class Apple extends Character{
	
	public Apple(int health, boolean win, int row, int col) {
		super(2, health, win, row, col);
		
	}

	// Apples don't move silly
	public Location move(String direction) {
		
		return null;
	}

	public String iAm() {
		
		return "apple";
	}

	public String iRespond(String toWhatIsSaid) {
		
		return null;
	}

}