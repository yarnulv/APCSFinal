package com.game.entities.NPCs;

import com.game.functions.Character;
import com.game.functions.Location;

public class Guardian extends Character {

	public Guardian(int health, boolean win, int row, int col) {
		super(6, health, win, row, col);
		
	}

	public Location move(String direction) {
		
		return null;
	}

	public String iAm() {
		
		return "Guardian";
	}

	public String iRespond(String toWhatIsSaid) {
		
		return null;
	}

}