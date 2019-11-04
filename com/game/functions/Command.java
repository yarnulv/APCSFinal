package com.game.functions;

import java.util.ArrayList;
import java.util.Scanner;

import com.game.game.Game;

public class Command {
	
	private ArrayList<Character> aroundList;
	
	public Command() {
		
	}
	
	public void lookAround(Game game, Board board) {
		aroundList = new ArrayList<Character>();
		if (game.getPlayer().getLocation().getRow() != 0 && board.getBoard()[game.getPlayer().getLocation().getRow()-1][game.getPlayer().getLocation().getCol()].isOccupied()) {
			aroundList.add(board.getBoard()[game.getPlayer().getLocation().getRow()-1][game.getPlayer().getLocation().getCol()].getEntity());
		} else aroundList.add(null);
		if (game.getPlayer().getLocation().getRow() != Game.boardSizeY-1 && board.getBoard()[game.getPlayer().getLocation().getRow()+1][game.getPlayer().getLocation().getCol()].isOccupied()) {
			aroundList.add(board.getBoard()[game.getPlayer().getLocation().getRow()+1][game.getPlayer().getLocation().getCol()].getEntity());
		} else aroundList.add(null);
		if (game.getPlayer().getLocation().getCol() != 0 && board.getBoard()[game.getPlayer().getLocation().getRow()][game.getPlayer().getLocation().getCol()-1].isOccupied()) {
			aroundList.add(board.getBoard()[game.getPlayer().getLocation().getRow()][game.getPlayer().getLocation().getCol()-1].getEntity());
		} else aroundList.add(null);
		if (game.getPlayer().getLocation().getCol() != Game.boardSizeX-1 && board.getBoard()[game.getPlayer().getLocation().getRow()][game.getPlayer().getLocation().getCol()+1].isOccupied()) {
			aroundList.add(board.getBoard()[game.getPlayer().getLocation().getRow()][game.getPlayer().getLocation().getCol()+1].getEntity());
		} else aroundList.add(null);
	}
	
	public String getCmd() {
		@SuppressWarnings("resource")
		Scanner in = new Scanner(System.in);
		String cmd;
		
		cmd = in.nextLine();
		
		return cmd;
	}
	
	public void doCmd(Game game) {
		while (true) {
			String cmd = getCmd();
			int index = cmd.indexOf('(');
			if (index != -1 && cmd.charAt(cmd.length()-1) == ')') {
				if (cmd.substring(0, index).equalsIgnoreCase("discover")
						&& (cmd.substring(index+1, cmd.length()-1).equalsIgnoreCase("up")
							|| cmd.substring(index+1, cmd.length()-1).equalsIgnoreCase("down")
							|| cmd.substring(index+1, cmd.length()-1).equalsIgnoreCase("left")
							|| cmd.substring(index+1, cmd.length()-1).equalsIgnoreCase("right"))) {
					game.discover(game.getPlayer(), cmd.substring(index+1, cmd.length()-1));
					break;
				}else if (cmd.substring(0, index).equalsIgnoreCase("slap")
						&& (cmd.substring(index+1, cmd.length()-1).equalsIgnoreCase("up")
								|| cmd.substring(index+1, cmd.length()-1).equalsIgnoreCase("down")
								|| cmd.substring(index+1, cmd.length()-1).equalsIgnoreCase("left")
								|| cmd.substring(index+1, cmd.length()-1).equalsIgnoreCase("right"))) {
						game.slap(game.getPlayer(), cmd.substring(index+1, cmd.length()-1));
						break;
				} else if (cmd.substring(0, index).equalsIgnoreCase("open")
						&& (cmd.substring(index+1, cmd.length()-1).equalsIgnoreCase("up")
								|| cmd.substring(index+1, cmd.length()-1).equalsIgnoreCase("down")
								|| cmd.substring(index+1, cmd.length()-1).equalsIgnoreCase("left")
								|| cmd.substring(index+1, cmd.length()-1).equalsIgnoreCase("right"))) {
						game.open(game.getPlayer(), cmd.substring(index+1, cmd.length()-1));
						break;
				} else if (cmd.substring(0, index).equalsIgnoreCase("attack")
						&& (cmd.substring(index+1, cmd.length()-1).equalsIgnoreCase("up")
								|| cmd.substring(index+1, cmd.length()-1).equalsIgnoreCase("down")
								|| cmd.substring(index+1, cmd.length()-1).equalsIgnoreCase("left")
								|| cmd.substring(index+1, cmd.length()-1).equalsIgnoreCase("right"))) {
						game.attack(game.getPlayer(), cmd.substring(index+1, cmd.length()-1));
						break;
				} else if (cmd.substring(0, index).equalsIgnoreCase("pickUp")
						&& (cmd.substring(index+1, cmd.length()-1).equalsIgnoreCase("up")
								|| cmd.substring(index+1, cmd.length()-1).equalsIgnoreCase("down")
								|| cmd.substring(index+1, cmd.length()-1).equalsIgnoreCase("left")
								|| cmd.substring(index+1, cmd.length()-1).equalsIgnoreCase("right"))) {
						game.pickUp(game.getPlayer(), cmd.substring(index+1, cmd.length()-1));
						break;
				}
					System.out.println("Error: Invalid command");
			}  else if(cmd.equalsIgnoreCase("up") || cmd.equalsIgnoreCase("down") || cmd.equalsIgnoreCase("left") || cmd.equalsIgnoreCase("right")) {
				game.move(game.getPlayer(), cmd);
				break;
			} else if (cmd.equalsIgnoreCase("seeBag")) {
				game.getPlayer().printBag();
			} else if (cmd.equalsIgnoreCase("eatApple")) {
				ArrayList<Character> list = new ArrayList<Character>();
				list = game.getPlayer().getBag();
				
				for (int i = 0; i < list.size(); i++) {
					if (list.get(i).getID() == 9) {
						list.remove(i);
						game.getPlayer().setHealth(game.getPlayer().getHealth()+50);
						Game.toSay += "\nYou have healed 50 health!";
						Game.toSay += "\nHealth: " + game.getPlayer().getHealth();
						return;
					}
				}
				
				System.out.println("You don't have a God Apple to eat");
				
			}else if (cmd.equalsIgnoreCase("exit"))
				System.exit(0);
			else
				System.out.println("Error: Invalid command");
		}
		
	}
	
	public void printList() {
		System.out.print("direction");
		for (int i = 0; i < aroundList.size(); i++) {
			if (aroundList.get(i) != null && !aroundList.get(i).isDiscovered()) {
				switch (i) {
				case 0:
					System.out.print(", discover(up)");
					break;
				case 1:
					System.out.print(", discover(down)");
					break;
				case 2:
					System.out.print(", discover(left)");
					break;
				case 3:
					System.out.print(", discover(right)");
					break;
				}
			}
			
			if (aroundList.get(i) != null && aroundList.get(i).getID() == 5 && aroundList.get(i).isDiscovered()) {
				switch (i) {
				case 0:
					System.out.print(", slap(up)");
					break;
				case 1:
					System.out.print(", slap(down)");
					break;
				case 2:
					System.out.print(", slap(left)");
					break;
				case 3:
					System.out.print(", slap(right)");
					break;
				}
			}
			
			if (aroundList.get(i) != null && aroundList.get(i).getID() == 9 && aroundList.get(i).isDiscovered()) {
				switch (i) {
				case 0:
					System.out.print(", pickUp(up)");
					break;
				case 1:
					System.out.print(", pickUp(down)");
					break;
				case 2:
					System.out.print(", pickUp(left)");
					break;
				case 3:
					System.out.print(", pickUp(right)");
					break;
				}
			}
			
			if (aroundList.get(i) != null && aroundList.get(i).getID() == 3 && aroundList.get(i).isDiscovered()) {
				switch (i) {
				case 0:
					System.out.print(", open(up)");
					break;
				case 1:
					System.out.print(", open(down)");
					break;
				case 2:
					System.out.print(", open(left)");
					break;
				case 3:
					System.out.print(", open(right)");
					break;
				}
			}
			
			if (aroundList.get(i) != null && aroundList.get(i).isDiscovered()) {
				switch (i) {
				case 0:
					System.out.print(", attack(up)");
					break;
				case 1:
					System.out.print(", attack(down)");
					break;
				case 2:
					System.out.print(", attack(left)");
					break;
				case 3:
					System.out.print(", attack(right)");
					break;
				}
			}
		}
		System.out.println();
	}

}