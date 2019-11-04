package com.game.entities.objects;

import com.game.functions.Character;
import com.game.functions.Location;

public class Bush extends Character {

	public Bush(int health, boolean win, int row, int col) {
		super(8, health, win, row, col);
		
	}

	public Location move(String direction) {
		
		return null;
	}

	public String iAm() {
		
		return "bush";
	}

	public String iRespond(String toWhatIsSaid) {
		
		return null;
	}

}