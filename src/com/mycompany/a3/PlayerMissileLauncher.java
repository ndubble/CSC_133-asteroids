package com.mycompany.a3;

import com.codename1.charts.util.ColorUtil;

/**
 * Author: Nicholas Dubble
 * File: PlayerMissileLauncher.java
 *
 * CSC-133
 * Doan Nguyen
 * Fall 2018
 */
public class PlayerMissileLauncher extends MoveableObject implements ISteerable {

	public PlayerMissileLauncher(double x, double y,int speed, int gameWorldHeight, int gameWorldWidth) 
	{
		super(gameWorldHeight, gameWorldWidth);
		this.setX(x);
		this.setY(y);
		this.setSpeed(speed);
		this.setDirection(0);
		this.setColor(ColorUtil.BLACK);
	}

	public String toString()
	{
		return "Player MissileLauncher:" + super.toString();
	}
	
	@Override
	public void turn()
	{
	}

}
