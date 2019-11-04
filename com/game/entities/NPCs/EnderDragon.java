package com.game.entities.NPCs;

import com.game.functions.Character;
import com.game.functions.Location;

public class EnderDragon extends Character {
	
	public EnderDragon(int health, boolean win, int row, int col) {
		super(7, health, win, row, col);
		
	}

	public Location move(String direction) {
		
		return null;
	}

	public String iAm() {
		
		return "Ender Dragon";
	}

	public String iRespond(String toWhatIsSaid) {
		
		return null;
	}

}