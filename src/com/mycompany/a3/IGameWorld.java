package com.mycompany.a3;

/**
 * Author: Nicholas Dubble
 * File: IGameWorld.java
 *
 * CSC-133
 * Doan Nguyen
 * Fall 2018
 */
public interface IGameWorld 
{
	// Methods that GameWorldProxy can use
	IIterator getIterator();
	int getPlayerLives();
	int getPlayerScore();
	int getMissileCount();
	int getElapsedTime();
	boolean getSound();
	void printMap();
	
	// Methods that GameWorldProxy cannot utilize
	boolean setPlayerLives(int x);
	boolean setPlayerScore(int x);
	boolean setSound();
	boolean incrementGameTime();
	boolean addAsteroid();
	boolean addNPS();
	boolean addStation();
	boolean addPS();
	boolean increaseSpeed();
	boolean decreaseSpeed();
	boolean turnLeft();
	boolean turnRight();
	boolean turnPSMLLeft();
	boolean turnPSMLRight();
	boolean firePSMissile();
	boolean launchNPSMissile();
	boolean jump();
	boolean newSupply();
	boolean removeMissileAsteroid();
	boolean removeMissileNPS();
	boolean removeMissilePS();
	boolean removePSAsteroid();
	boolean removeNpsPs();
	boolean removeTwoAsteroids();
	boolean removeAsteroidNPS();
	boolean tick();
}
