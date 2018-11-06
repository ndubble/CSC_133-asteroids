package com.mycompany.a3;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.geom.Point2D;

/**
 * Author: Nicholas Dubble
 * File: Missile.java
 *
 * CSC-133
 * Doan Nguyen
 * Fall 2018
 */
public class Missile extends MoveableObject 
{
	private int fuelLevel;
	private final int MISSILESPEED = 3;
	private final int INITIALFUEL = 10;
	private Boolean isPlayerMissile;
	
	public Missile(Point2D loc,int direction, int speedOfShip, Boolean playerMissile, int gameWorldHeight, int gameWorldWidth)
	{
		super(gameWorldHeight, gameWorldWidth);
		this.setDirection(direction);
		this.setSpeed(speedOfShip + MISSILESPEED);
		this.setColor(ColorUtil.BLUE);
		this.setLocation(loc);
		fuelLevel = INITIALFUEL;
		if (playerMissile)
			isPlayerMissile = true;
		else
			isPlayerMissile = false;
	}
	
	public int getFuelLevel()
	{
		return fuelLevel;
	}
	
	public void setFuelLevel(int x)
	{
		fuelLevel = x;
	}
	
	public Boolean getIsPlayerMissile()
	{
		return isPlayerMissile;
	}
	
	public String toString()
	{
		return "Missile: " + super.toString() + "fuel level: " + fuelLevel;
	}
}
