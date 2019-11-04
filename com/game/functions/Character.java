package com.game.functions;

public abstract class Character
{
	private final int id;
	
    private int health;
    private boolean win;
    private boolean discovered;
    private Location loc;
    
    public Character(int id, int health, boolean win, int row, int col)
    {
    	this.id = id;
        this.health = health;
        this.win = win;
        this.discovered = false;
        
        loc = new Location(row, col);
    }
    /*changes loc to where the character is moving in the direction indicated*/
    public abstract Location move(String direction);
	
    /*returns a string of the character*/
    public abstract String iAm();
	
    /*chat bot for interaction with the player*/
    public abstract String iRespond(String toWhatIsSaid);
    
    public int getID() {
    	return id;
    }
	
    public int getHealth()
    {
        return health;
    }
    
    public void setHealth(int health)
    {
        this.health=health;
    }
    
    public boolean getWin()
    {
        return win;
        
    }
    
    public boolean isDiscovered() {
    	return discovered;
    }
    
    public void setDiscovered(boolean bool) {
    	discovered = bool;
    }
    
    public Location getLocation()
    {
        return loc;
        
    }
    
    public void setLocation(int row, int col)
    {
        loc = new Location(row,col);
        
    }
}