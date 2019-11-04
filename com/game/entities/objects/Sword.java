package com.game.entities.objects;

import com.game.functions.Character;
import com.game.functions.Location;

public class Sword extends Character {

	public Sword(int health, boolean win, int row, int col) {
		super(4, health, win, row, col);
		
	}

	public Location move(String direction) {
		
		return null;
	}

	public String iAm() {
		
		return "sword";
	}

	public String iRespond(String toWhatIsSaid) {
		
		return null;
	}
	
}