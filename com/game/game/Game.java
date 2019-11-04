package com.game.game;

import java.io.IOException;

import java.util.ArrayList;
import java.util.Scanner;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import com.game.entities.NPCs.*;
import com.game.entities.objects.*;
import com.game.entities.playables.Player;
import com.game.functions.Character;
import com.game.functions.Command;
import com.game.functions.Location;
import com.game.gui.Audio;
import com.game.functions.Board;

public class Game {
	
	public static int boardSizeX = 10;
	public static int boardSizeY = 10;
	public static String toSay = "";
	
	public static Board board;
	
	private boolean[][] isUsed;
	
	private boolean running;
	private ArrayList<Character> entityList;
	private Audio audio;
	private Scanner in;
	
	private Player player1;
	private Pig pig1;
	private Chest chest1;
	private EnderDragon dragon;
	private Guardian guard;
	
	private Sword sword;
	private Apple apple;
	
	public Game() {
		running = false;
		entityList = new ArrayList<Character>();
		board = new Board(Game.boardSizeX, Game.boardSizeY);
		in = new Scanner(System.in);
		isUsed = new boolean[board.getBoard().length][board.getBoard().length];
		try {
			audio = new Audio("/theslap.wav");
		} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
			System.out.print("Error: Failed to load song");
			e.printStackTrace();
			System.exit(0);
		}
		
		player1 = new Player(100, false, 0, 0);
		pig1 = new Pig(20, false, 0, 0);
		chest1 = new Chest(10, false, 0, 0);
		
		dragon = new EnderDragon(500, true, 0, 0);
		guard = new Guardian(150, false, 0, 0);
		dragon.setDiscovered(true);
		guard.setDiscovered(true);
		
		add(player1); //Adds a Character(Entity) to ArrayList to be used in board.updateBoard(entityList);
		add(pig1);
		add(chest1);
		
		//NOTE: I messed up and the coordinates are backwards. y,x not x,y
		sword = new Sword(99999, false, 0, 0);
		apple = new Apple(99999, false, 0, 0);
		
		for (int i = 0; i < 10; i++) {
			Tree tree = new Tree(30, false, 0, 0);
			add(tree);
		}
		
		for (int i = 0; i < 5; i++) {
			Bush bush = new Bush(10, false, 0, 0);
			add(bush);
		}
		
		for (int i = 0; i < entityList.size(); i++) {
			int y = (int)(Math.random()*Game.boardSizeX), x = (int)(Math.random()*Game.boardSizeY);
			if (!isUsed[y][x]) {
				entityList.get(i).setLocation(y, x);
				isUsed[y][x] = true;
			} else
				 i--;
				
		}
		
		chest1.addToChest(sword);
		chest1.addToChest(apple);
	}
	
	
	public Player getPlayer() {
		return this.player1;
	}
	
	//Print for debug purposes right now
	public void printBoard() {
		
		for (int i = 0; i < 20; i++)
			System.out.println();
		
		for (int r = 0; r < board.getBoard().length; r++) {
			for (int c = 0; c < board.getBoard()[r].length; c++) {
				if (board.getBoard()[r][c].getEntity() == null)
					System.out.print(0 + " ");
				else {
					if (board.getBoard()[r][c].getEntity().isDiscovered()) {
						switch (board.getBoard()[r][c].getEntityID()) {
						case 0:
							System.out.print("@ ");
							break;
						case 1:
							System.out.print("T ");
							break;
						case 2:
							System.out.print(" ");
							break;
						case 3:
							System.out.print("C ");
							break;
						case 4:
							System.out.print("Error: Sword ");
							break;
						case 5:
							System.out.print("P ");
							break;
						case 6:
							System.out.print("G ");
							break;
						case 7:
							System.out.print("D ");
							break;
						case 8:
							System.out.print("B ");
							break;
						case 9:
							System.out.print("A ");
							break;
						}
					} else
						System.out.print("? ");
				}
			}
			System.out.println();
		}
	}
	
	//add(entity) is so much better than entityList.add(entity); every time
	public void add(Character entity) {
		entityList.add(entity);
	}
	
	//The silly other move method was annoying to work with. So this happened
	public void move(Player entity, String direction) { //
		Location moveTo = entity.move(direction);
		if (moveTo == null)
			moveTo = entity.getLocation();
		
		entity.setLocation(moveTo.getRow(), moveTo.getCol());
	}
	
	public void discover(Player entity, String direction) {
		Location loc = entity.discover(direction);
		if (loc == null)
			return;
		
		board.getBoard()[loc.getRow()][loc.getCol()].getEntity().setDiscovered(true);
		toSay = "You've found a " + board.getBoard()[loc.getRow()][loc.getCol()].getEntity().iAm();
	}
	
	public void slap(Player entity, String direction) {
		Location loc = entity.slap(direction);
		if (loc == null)
			return;
		
		try {
			audio = new Audio("/theslap.wav");
		} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
			System.out.print("Error: Failed to load song");
			e.printStackTrace();
			System.exit(0);
		}
		
		audio.play();
	}
	
	public void open(Player entity,  String direction) {
		Location loc = entity.open(direction);
		if (loc == null)
			return;
		
		System.out.print("Contents: ");
		for (int i = 0; i < chest1.getContents().size(); i++) {
			System.out.print(chest1.getContents().get(i).iAm() + " ");
		}
		System.out.println("\n(take/close)");
		
		String answer;
		while (true) {
			answer = in.nextLine();
			if (answer.equalsIgnoreCase("close"))
				break;
			else if (answer.contentEquals("take")) {
				for (int i = chest1.getContents().size()-1; i >= 0; i--) {
					entity.addToBag(chest1.getContents().get(i));
					chest1.getContents().remove(i);
				}
				System.out.println("You put everything in your bag.");
				
				while (true) {
					int y = (int)(Math.random()*(Game.boardSizeX-2)+1), x = (int)(Math.random()*(Game.boardSizeY-2)+1);
					if (y == player1.getLocation().getCol() && x == player1.getLocation().getRow())
						continue;
					if (!isUsed[y][x]) {
						add(dragon);
						dragon.setLocation(y, x);
						isUsed[y][x] = true;
						
						for (int i = entityList.size()-1; i >= 0; i--) {
							if (entityList.get(i).getLocation().getRow() == y-1 && entityList.get(i).getLocation().getCol() == x)
								entityList.remove(i);
							if (entityList.get(i).getLocation().getRow() == y+1 && entityList.get(i).getLocation().getCol() == x)
								entityList.remove(i);
							if (entityList.get(i).getLocation().getRow() == y && entityList.get(i).getLocation().getCol() == x-1)
								entityList.remove(i);
							if (entityList.get(i).getLocation().getRow() == y && entityList.get(i).getLocation().getCol() == x+1)
								entityList.remove(i);
							if (entityList.get(i).getLocation().getRow() == y-1 && entityList.get(i).getLocation().getCol() == x-1)
								entityList.remove(i);
							if (entityList.get(i).getLocation().getRow() == y-1 && entityList.get(i).getLocation().getCol() == x+1)
								entityList.remove(i);
							if (entityList.get(i).getLocation().getRow() == y+1 && entityList.get(i).getLocation().getCol() == x-1)
								entityList.remove(i);
							if (entityList.get(i).getLocation().getRow() == y+1 && entityList.get(i).getLocation().getCol() == x+1)
								entityList.remove(i);
						}
						
						guard = new Guardian(100, false, y-1, x);
						guard.setDiscovered(true);
						add(guard);
						guard = new Guardian(100, false, y+1, x);
						guard.setDiscovered(true);
						add(guard);
						guard = new Guardian(100, false, y, x-1);
						guard.setDiscovered(true);
						add(guard);
						guard = new Guardian(100, false,y, x+1);
						guard.setDiscovered(true);
						add(guard);
						guard = new Guardian(100, false, y-1, x-1);
						guard.setDiscovered(true);
						add(guard);
						guard = new Guardian(100, false, y-1, x+1);
						guard.setDiscovered(true);
						add(guard);
						guard = new Guardian(100, false, y+1, x-1);
						guard.setDiscovered(true);
						add(guard);
						guard = new Guardian(100, false, y+1, x+1);
						guard.setDiscovered(true);
						add(guard);
						break;
					}
				}
			}
		}
	}
	
	public void attack(Player entity, String direction) {
		Location loc = entity.attack(direction);
		if (loc == null)
			return;
		
		board.getBoard()[loc.getRow()][loc.getCol()].getEntity().setHealth(board.getBoard()[loc.getRow()][loc.getCol()].getEntity().getHealth()-(int)(Math.random()*21));
		int rand;
		switch (board.getBoard()[loc.getRow()][loc.getCol()].getEntityID()) {
		case 1:
			
			break;
		case 2:
			
			break;
		case 3:
			
			break;
		case 4:
			
			break;
		case 5:
			if (board.getBoard()[loc.getRow()][loc.getCol()].getEntity().getHealth() > 0)
				entity.setHealth(entity.getHealth()-1);
			toSay += "The Pig has chomped you!";
			break;
		case 6:
			rand = (int)(Math.random()*10+1);
			if (rand >= 1 && rand <= 3 && board.getBoard()[loc.getRow()][loc.getCol()].getEntity().getHealth() > 0) {
				entity.setHealth(entity.getHealth()-(int)(Math.random()*5+5));
				toSay += "Guardian has attackith!";
			}
			break;
		case 7:
			rand = (int)(Math.random()*100+1);
			if (rand >= 1 && rand <= 35 && board.getBoard()[loc.getRow()][loc.getCol()].getEntity().getHealth() > 0) {
				entity.setHealth(entity.getHealth()-(int)(Math.random()*16+5));
				toSay += "Ender Dragon has attackith!";
			}
			break;
		case 8:
			
			break;
		case 9:
			
			break;
		}
		
		toSay += "\nYour Health: " + entity.getHealth() + "\nEnemy Health: " + board.getBoard()[loc.getRow()][loc.getCol()].getEntity().getHealth();
	}
	
	public void pickUp(Player entity, String direction) {
		Location loc = entity.pickUp(direction);
		if (loc == null)
			return;
		
		entity.addToBag(board.getBoard()[loc.getRow()][loc.getCol()].getEntity());
		
		for (int i = entityList.size()-1; i >= 0; i--) {
			if (entityList.get(i).getLocation().getRow() == loc.getRow() && entityList.get(i).getLocation().getCol() == loc.getCol() && entityList.get(i).getID() == 9) {
				System.out.println("Removing apple");
				entityList.remove(i);
			}
		}
		
	}
	
	public void printResponse() {
		if (!toSay.equalsIgnoreCase("")) {
			System.out.println(toSay);
			toSay = "";
		}
	}
	
	public void run (Game game) {
		Command command = new Command();
		running = true;
		while (running) {
			board.updateBoard(entityList); //Every iteration, the board is updated
			printBoard();
			command.lookAround(game, board);
			command.printList();
			printResponse();
//			System.out.println(player1.getLocation().getRow() + ", " + player1.getLocation().getCol()); // Coordinates
			
			if (dragon.getHealth() <= 0)
				break;
			
			command.doCmd(game);
			if (audio.getClip().isRunning())
				audio.stop();

			running = false; //Debug
		}
		System.out.println("You win!");
	}
	
	public static void main(String args[]) {
		Game game = new Game(); //Create the game :)
		
		System.out.println("Welcome to the exclusive, limited edition, \nAPCS Final RPG game thing!");
		System.out.println("\nYOU ARE THE '@' SYMBOL");
		System.out.println("To move around, simply type \"up\", \"down\", \"left\", or \"right\"\nwithout quotations!");
		System.out.println("\nYou can view your inventory by typing \"seeBag\"\nWhen you aquire a God Apple, type \"eatApple\" to COnSumE!");
		System.out.println("During the game, type \"exit\", without quotations, to exit!");
		System.out.println("\nAll other commands will be provided for you!");
		System.out.println("\nFor a little bonus go find the pig :)");
		System.out.println("Press Enter to continue...");
		game.in.nextLine();
		
		for (int i = 0; i < 40; i++)
			System.out.println();
		
		System.out.println("Find the chest and take it's contents to spawn the dragon!");
		System.out.println("\nDefeat the dragon to win the game!");
		System.out.println("\nYou can heal with god apples which have a 20% \nchance of dropping from a tree");
		System.out.println("\nWhen you attack certain Characters, they have\na chance of dealing damage to you");
		System.out.println("\nAlmost everything on the board needs to be discovered");
		System.out.println("The only objects that are already are the player and trees");
		System.out.println("Press Enter to continue...");
		
		game.in.nextLine();
		
		game.run(game); //Run the game :))
		
	}
	
}