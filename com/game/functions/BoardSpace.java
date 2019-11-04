package com.game.functions;

public class BoardSpace {
	
	private boolean occupied;
	private Character entity;
	
	public BoardSpace() {
		occupied = false;
		entity = null;
	}
	
//------Gets------------------------------------------------------------------------------------------
	
	public boolean isOccupied() {
		return occupied;
	}
	
	public Character getEntity() {
		return entity;
	}
	
	public int getEntityID() {
		return entity.getID();
	}
	
//------Sets-------------------------------------------------------------------------------------------
	
	public void setOccupied(boolean bool) {
		this.occupied = bool;
	}
	
	public void setEntity(Character entity) {
		this.entity = entity;
	}

}