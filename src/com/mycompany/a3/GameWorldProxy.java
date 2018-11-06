package com.mycompany.a3;

import java.util.Observable;

/**
 * Author: Nicholas Dubble
 * File: GameWorldProxy.java
 *
 * CSC-133
 * Doan Nguyen
 * Fall 2018
 */
public class GameWorldProxy extends Observable implements IGameWorld 
{
	// code here to accept and hold a GameWorld, provide implementations
	// of all the public methods in a GameWorld, forward allowed
	// calls to the actual GameWorld, and reject calls to methods
	// which the outside should not be able to access in the GameWorld.
	
	private GameWorld realGameWorld;
	
	public GameWorldProxy(GameWorld gw)
	{
		realGameWorld = gw;
	}
	
	public IIterator getIterator() 
	{
		return realGameWorld.getIterator();	
	}
	
	public int getPlayerScore() 
	{
		return realGameWorld.getPlayerScore();
	}

	public int getPlayerLives() 
	{
		return realGameWorld.getPlayerLives();
	}

	public int getMissileCount() 
	{
		return realGameWorld.getMissileCount();
	}

	public int getElapsedTime()
	{
		return realGameWorld.getElapsedTme();
	}
	
	public boolean getSound()
	{
		return realGameWorld.getSound();
	}
	
	public void printMap()
	{
		realGameWorld.printMap();
	}


	public boolean setPlayerLives(int x) {
		return false;
	}

	
	public boolean setPlayerScore(int x) {
		
		return false;
	}

	
	public boolean setSound() {
		
		return false;
	}

	
	public boolean incrementGameTime() {
		
		return false;
	}

	
	public boolean addAsteroid() {
		
		return false;
	}

	
	public boolean addNPS() {
		
		return false;
	}

	
	public boolean addStation() {
		
		return false;
	}

	
	public boolean addPS() {
		
		return false;
	}

	
	public boolean increaseSpeed() {
		
		return false;
	}

	
	public boolean decreaseSpeed() {
		
		return false;
	}

	
	public boolean turnLeft() {
		
		return false;
	}

	
	public boolean turnRight() {
		
		return false;
	}

	
	public boolean turnPSMLLeft() {
		
		return false;
	}

	
	public boolean turnPSMLRight() {
		
		return false;
	}

	
	public boolean firePSMissile() {
		
		return false;
	}

	
	public boolean launchNPSMissile() {
		
		return false;
	}

	
	public boolean jump() {
		
		return false;
	}

	
	public boolean newSupply() {
		
		return false;
	}

	
	public boolean removeMissileAsteroid() {
		
		return false;
	}

	
	public boolean removeMissileNPS() {
		
		return false;
	}

	
	public boolean removeMissilePS() {
		
		return false;
	}

	
	public boolean removePSAsteroid() {
		
		return false;
	}

	
	public boolean removeNpsPs() {
		
		return false;
	}

	
	public boolean removeTwoAsteroids() {
		
		return false;
	}

	
	public boolean removeAsteroidNPS() {
		
		return false;
	}

	
	public boolean tick() {
		
		return false;
	}
}
