package com.mycompany.a3;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.geom.Point2D;

/**
 * Author: Nicholas Dubble
 * File: NonPlayerMissileLauncher.java
 *
 * CSC-133
 * Doan Nguyen
 * Fall 2018
 */
public class NonPlayerMissileLauncher extends MoveableObject 
{

	public NonPlayerMissileLauncher(Point2D loc, int speed, int direction, int gameWorldHeight, int gameWorldWidth) 
	{
		super(gameWorldHeight,gameWorldWidth);
		this.setLocation(loc);
		this.setSpeed(speed);
		this.setDirection(direction);	
		this.setColor(ColorUtil.LTGRAY);
	}

	
	public String toString()
	{
		return "NonPlayerMissileLauncher: " + super.toString();
	}
}
