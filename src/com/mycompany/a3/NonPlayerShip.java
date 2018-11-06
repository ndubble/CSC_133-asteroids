package com.mycompany.a3;

import com.codename1.charts.util.ColorUtil;

/**
 * Author: Nicholas Dubble
 * File: NonPlayerShip.java
 *
 * CSC-133
 * Doan Nguyen
 * Fall 2018
 */
public class NonPlayerShip extends MoveableObject 
{
	private int size;
	private final static int LARGE = 20;
	private final static int SMALL = 10;
	private final static int MAXMISSILES = 2;
	private NonPlayerMissileLauncher missileLauncher;
	private int missileCount;
	
	public NonPlayerShip(int gameWorldHeight, int gameWorldWidth) 
	{
		super(gameWorldHeight, gameWorldWidth);
		if (R.nextInt(2) == 0)
			size = SMALL;
		else
			size = LARGE;
		missileLauncher = new NonPlayerMissileLauncher(this.getLocation(), this.getSpeed(), this.getDirection(),gameWorldHeight,gameWorldWidth);
		missileCount = 2;
		this.setColor(ColorUtil.MAGENTA);
	}
	
	public int getSize()
	{
		return size;
	}
	
	public int getMissileCount()
	{
		return missileCount;
	}
	
	public NonPlayerMissileLauncher getMissileLauncher()
	{
		return missileLauncher;
	}
	
	public void setMissileCount(int x)
	{
		missileCount = x;
	}
	
	public int getMaxMissiles()
	{
		return MAXMISSILES;
	}
	
	public String toString()
	{
		String NonPlayerShip = "NonPlayerShip: " + super.toString() + "size=" + getSize() + "\n";
		String NPSMissileLauncher = this.getMissileLauncher().toString();
		return NonPlayerShip + NPSMissileLauncher;
	}
	
}
