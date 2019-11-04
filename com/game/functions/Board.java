package com.game.functions;

import java.util.ArrayList;

import com.game.entities.objects.GodApple;
import com.game.game.Game;

public class Board {
	
	private BoardSpace[][] board;
	
	//Constructor initializes board
	public Board(int xsize, int ysize) {
		board = new BoardSpace[xsize][ysize];
		
		for (int r = 0; r < board.length; r++) {
			for (int c = 0; c < board[0].length; c++) {
				board[r][c] = new BoardSpace();
			}
		}
	}
	
	//Getter
	public BoardSpace[][] getBoard() {
		return board;
	}
	
	//Resets the board to an array of BoardSpaces with default fields
	private void resetBoard() {
		for (int r = 0; r < board.length; r++) {
			for (int c = 0; c < board[r].length; c++) {
				board[r][c] = new BoardSpace();
			}
		}
	}
	
	//After resetting, the board replaces all entities on screen in their new positions
	public void updateBoard(ArrayList<Character> entityList) {
		resetBoard();
		
		for (int i = entityList.size()-1; i >= 0; i--) {
			if (entityList.get(i).getID() == 0 && entityList.get(i).getHealth() <= 0) {
				Game.toSay += "\n" + entityList.get(i).iAm() + ", you have DIED!!";
				entityList.remove(i);
				System.out.print("GAME OVER");
				System.exit(0);
			} else if (entityList.get(i).getID() == 1 && entityList.get(i).getHealth() <= 0) {
				Game.toSay += "\nYou killed a " + entityList.get(i).iAm();
				Location loc = entityList.get(i).getLocation();
				entityList.remove(i);
				GodApple apple = new GodApple(100, false, loc.getRow(), loc.getCol());
				apple.setDiscovered(true);
				int rand = (int)(Math.random()*5+1);
				if (rand == 1)
					entityList.add(apple);
			} else if (entityList.get(i).getHealth() <= 0) {
				Game.toSay += "\nYou killed a " + entityList.get(i).iAm();
				entityList.remove(i);
			}
		}
		
		for (int i = 0; i < entityList.size(); i++) {
			board[entityList.get(i).getLocation().getRow()][entityList.get(i).getLocation().getCol()].setEntity(entityList.get(i));
			board[entityList.get(i).getLocation().getRow()][entityList.get(i).getLocation().getCol()].setOccupied(true);
		}
	}

}