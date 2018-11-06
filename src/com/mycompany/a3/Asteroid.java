package com.mycompany.a3;

import com.codename1.charts.util.ColorUtil;

/**
 * Author: Nicholas Dubble
 * File: Asteroid.java
 *
 * CSC-133
 * Doan Nguyen
 * Fall 2018
 */
public class Asteroid extends MoveableObject 
{
	private int size;
	private final int SIZEMIN = 6;
	private final int SIZEMAX = 30;
	
	public Asteroid(int gameWorldHeight, int gameWorldWidth) 
	{
		super(gameWorldHeight,gameWorldWidth);
		/* size = random value between 6 and 30*/
		size = R.nextInt(SIZEMAX-SIZEMIN+1) + SIZEMIN;
		this.setColor(ColorUtil.GRAY);
	}
	
	public int getSize()
	{
		return size;
	}
	
	public String toString()
	{
		
		return "Asteroid: " + super.toString() + "size=" + size;
	}

}
