package com.game.entities.objects;
import com.game.functions.Character;
import com.game.functions.Location;

public class GodApple extends Character {

	public GodApple(int health, boolean win, int row, int col) {
		super(9, health, win, row, col);
	}

	public Location move(String direction) {
		return null;
	}

	public String iAm() {
		return "God Apple";
	}

	public String iRespond(String toWhatIsSaid) {
		return null;
	}

}