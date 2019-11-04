package com.game.entities.playables;

import java.util.ArrayList;

import com.game.functions.Character;
import com.game.functions.Location;
import com.game.game.Game;

public class Player extends Character {
	
	private ArrayList<Character> bag;
	
	public Player(int health, boolean win, int row, int col) {
		super(0, health, win, row, col);
		setDiscovered(true);
		bag = new ArrayList<Character>();
	}

	public Location move(String direction) {
		if (direction.equalsIgnoreCase("up"))
			
			if (this.getLocation().getRow() == 0 || Game.board.getBoard()[this.getLocation().getRow()-1][this.getLocation().getCol()].isOccupied())
				return null;
			else
				return new Location(this.getLocation().getRow()-1, this.getLocation().getCol());
		
		else if (direction.equalsIgnoreCase("down"))
			
			if (this.getLocation().getRow() == Game.boardSizeY-1 || Game.board.getBoard()[this.getLocation().getRow()+1][this.getLocation().getCol()].isOccupied())
				return null;
			else
				return new Location(this.getLocation().getRow()+1, this.getLocation().getCol());
		
		else if (direction.equalsIgnoreCase("left"))
			
			if (this.getLocation().getCol() == 0 || Game.board.getBoard()[this.getLocation().getRow()][this.getLocation().getCol()-1].isOccupied())
				return null;
			else
				return new Location(this.getLocation().getRow(), this.getLocation().getCol()-1);
		
		else if (direction.equalsIgnoreCase("right"))
			
			if (this.getLocation().getCol() == Game.boardSizeX-1 || Game.board.getBoard()[this.getLocation().getRow()][this.getLocation().getCol()+1].isOccupied())
				return null;
			else
				return new Location(this.getLocation().getRow(), this.getLocation().getCol()+1);
		
		else {
			return null;
		}
	}
	
	public Location discover(String direction) {
		if (direction.equalsIgnoreCase("up"))
			
			if (this.getLocation().getRow() == 0 || (Game.board.getBoard()[this.getLocation().getRow()-1][this.getLocation().getCol()].getEntity() == null ? true : Game.board.getBoard()[this.getLocation().getRow()-1][this.getLocation().getCol()].getEntity().isDiscovered()))
				return null;
			else
				return new Location(this.getLocation().getRow()-1, this.getLocation().getCol());
		
		else if (direction.equalsIgnoreCase("down"))
			
			if (this.getLocation().getRow() == Game.boardSizeY-1 || (Game.board.getBoard()[this.getLocation().getRow()+1][this.getLocation().getCol()].getEntity() == null ? true : Game.board.getBoard()[this.getLocation().getRow()+1][this.getLocation().getCol()].getEntity().isDiscovered()))
				return null;
			else
				return new Location(this.getLocation().getRow()+1, this.getLocation().getCol());
		
		else if (direction.equalsIgnoreCase("left"))
			
			if (this.getLocation().getCol() == 0 || (Game.board.getBoard()[this.getLocation().getRow()][this.getLocation().getCol()-1].getEntity() == null ? true : Game.board.getBoard()[this.getLocation().getRow()][this.getLocation().getCol()-1].getEntity().isDiscovered()))
				return null;
			else
				return new Location(this.getLocation().getRow(), this.getLocation().getCol()-1);
		
		else if (direction.equalsIgnoreCase("right"))
			
			if (this.getLocation().getCol() == Game.boardSizeX-1 ||  (Game.board.getBoard()[this.getLocation().getRow()][this.getLocation().getCol()+1].getEntity() == null ? true : Game.board.getBoard()[this.getLocation().getRow()][this.getLocation().getCol()+1].getEntity().isDiscovered()))
				return null;
			else
				return new Location(this.getLocation().getRow(), this.getLocation().getCol()+1);
		
		else {
			return null;
		}
	}
	
	public Location slap(String direction) {
		if (direction.equalsIgnoreCase("up"))
			
			if (this.getLocation().getRow() == 0 || (Game.board.getBoard()[this.getLocation().getRow()-1][this.getLocation().getCol()].getEntity() == null ? true : Game.board.getBoard()[this.getLocation().getRow()-1][this.getLocation().getCol()].getEntityID() != 5))
				return null;
			else
				return new Location(this.getLocation().getRow()-1, this.getLocation().getCol());
		
		else if (direction.equalsIgnoreCase("down"))
			
			if (this.getLocation().getRow() == Game.boardSizeY-1 || (Game.board.getBoard()[this.getLocation().getRow()+1][this.getLocation().getCol()].getEntity() == null ? true : Game.board.getBoard()[this.getLocation().getRow()+1][this.getLocation().getCol()].getEntityID() != 5))
				return null;
			else
				return new Location(this.getLocation().getRow()+1, this.getLocation().getCol());
		
		else if (direction.equalsIgnoreCase("left"))
			
			if (this.getLocation().getCol() == 0 || (Game.board.getBoard()[this.getLocation().getRow()][this.getLocation().getCol()-1].getEntity() == null ? true : Game.board.getBoard()[this.getLocation().getRow()][this.getLocation().getCol()-1].getEntityID() != 5))
				return null;
			else
				return new Location(this.getLocation().getRow(), this.getLocation().getCol()-1);
		
		else if (direction.equalsIgnoreCase("right"))
			
			if (this.getLocation().getCol() == Game.boardSizeX-1 || (Game.board.getBoard()[this.getLocation().getRow()][this.getLocation().getCol()+1].getEntity() == null ? true : Game.board.getBoard()[this.getLocation().getRow()][this.getLocation().getCol()+1].getEntityID() != 5))
				return null;
			else
				return new Location(this.getLocation().getRow(), this.getLocation().getCol()+1);
		
		else {
			return null;
		}
	}
	
	public Location open(String direction) {
		if (direction.equalsIgnoreCase("up"))
			
			if (this.getLocation().getRow() == 0 || (Game.board.getBoard()[this.getLocation().getRow()-1][this.getLocation().getCol()].getEntity() == null ? true : Game.board.getBoard()[this.getLocation().getRow()-1][this.getLocation().getCol()].getEntityID() != 3))
				return null;
			else
				return new Location(this.getLocation().getRow()-1, this.getLocation().getCol());
		
		else if (direction.equalsIgnoreCase("down"))
			
			if (this.getLocation().getRow() == Game.boardSizeY-1 || (Game.board.getBoard()[this.getLocation().getRow()+1][this.getLocation().getCol()].getEntity() == null ? true : Game.board.getBoard()[this.getLocation().getRow()+1][this.getLocation().getCol()].getEntityID() != 3))
				return null;
			else
				return new Location(this.getLocation().getRow()+1, this.getLocation().getCol());
		
		else if (direction.equalsIgnoreCase("left"))
			
			if (this.getLocation().getCol() == 0 || (Game.board.getBoard()[this.getLocation().getRow()][this.getLocation().getCol()-1].getEntity() == null ? true : Game.board.getBoard()[this.getLocation().getRow()][this.getLocation().getCol()-1].getEntityID() != 3))
				return null;
			else
				return new Location(this.getLocation().getRow(), this.getLocation().getCol()-1);
		
		else if (direction.equalsIgnoreCase("right"))
			
			if (this.getLocation().getCol() == Game.boardSizeX-1 || (Game.board.getBoard()[this.getLocation().getRow()][this.getLocation().getCol()+1].getEntity() == null ? true : Game.board.getBoard()[this.getLocation().getRow()][this.getLocation().getCol()+1].getEntityID() != 3))
				return null;
			else
				return new Location(this.getLocation().getRow(), this.getLocation().getCol()+1);
		
		else {
			return null;
		}
	}
	
	public Location attack(String direction) {
		if (direction.equalsIgnoreCase("up"))
			
			if (this.getLocation().getRow() == 0 || Game.board.getBoard()[this.getLocation().getRow()-1][this.getLocation().getCol()].getEntity() == null)
				return null;
			else
				return new Location(this.getLocation().getRow()-1, this.getLocation().getCol());
		
		else if (direction.equalsIgnoreCase("down"))
			
			if (this.getLocation().getRow() == Game.boardSizeY-1 || Game.board.getBoard()[this.getLocation().getRow()+1][this.getLocation().getCol()].getEntity() == null)
				return null;
			else
				return new Location(this.getLocation().getRow()+1, this.getLocation().getCol());
		
		else if (direction.equalsIgnoreCase("left"))
			
			if (this.getLocation().getCol() == 0 || Game.board.getBoard()[this.getLocation().getRow()][this.getLocation().getCol()-1].getEntity() == null)
				return null;
			else
				return new Location(this.getLocation().getRow(), this.getLocation().getCol()-1);
		
		else if (direction.equalsIgnoreCase("right"))
			
			if (this.getLocation().getCol() == Game.boardSizeX-1 || Game.board.getBoard()[this.getLocation().getRow()][this.getLocation().getCol()+1].getEntity() == null)
				return null;
			else
				return new Location(this.getLocation().getRow(), this.getLocation().getCol()+1);
		
		else {
			return null;
		}
	}
	
	public Location pickUp(String direction) {
		if (direction.equalsIgnoreCase("up"))
			
			if (this.getLocation().getRow() == 0 || Game.board.getBoard()[this.getLocation().getRow()-1][this.getLocation().getCol()].getEntity() == null)
				return null;
			else
				return new Location(this.getLocation().getRow()-1, this.getLocation().getCol());
		
		else if (direction.equalsIgnoreCase("down"))
			
			if (this.getLocation().getRow() == Game.boardSizeY-1 || Game.board.getBoard()[this.getLocation().getRow()+1][this.getLocation().getCol()].getEntity() == null)
				return null;
			else
				return new Location(this.getLocation().getRow()+1, this.getLocation().getCol());
		
		else if (direction.equalsIgnoreCase("left"))
			
			if (this.getLocation().getCol() == 0 || Game.board.getBoard()[this.getLocation().getRow()][this.getLocation().getCol()-1].getEntity() == null)
				return null;
			else
				return new Location(this.getLocation().getRow(), this.getLocation().getCol()-1);
		
		else if (direction.equalsIgnoreCase("right"))
			
			if (this.getLocation().getCol() == Game.boardSizeX-1 || Game.board.getBoard()[this.getLocation().getRow()][this.getLocation().getCol()+1].getEntity() == null)
				return null;
			else
				return new Location(this.getLocation().getRow(), this.getLocation().getCol()+1);
		
		else {
			return null;
		}
	}
	
	public void addToBag(Character entity) {
		bag.add(entity);
	}
	
	public ArrayList<Character> getBag() {
		return bag;
	}
	
	public void printBag() {
		System.out.print("Bag: ");
		for (int i = 0; i < bag.size(); i++) {
			System.out.print(bag.get(i).iAm() + ", ");
		}
		System.out.println();
	}

	public String iAm() {
		
		return "Endergirl1400";
	}

	public String iRespond(String toWhatIsSaid) {
		
		return null;
	}

}