package com.mycompany.a3;
import com.codename1.ui.geom.Point2D;
import com.mycompany.a3.GameObject;

import java.util.Random;

/**
 * Author: Nicholas Dubble
 * File: MoveableObject.java
 *
 * CSC-133
 * Doan Nguyen
 * Fall 2018
 */
public abstract class MoveableObject extends GameObject implements IMoveable 
{
	private int speed;
	private int direction;
	protected final static int SPEEDMAX = 10;
	protected final static int DIRECTIONMAX = 359;

	public MoveableObject(int gameWorldHeight, int gameWorldWidth) 
	{
		super(gameWorldHeight,gameWorldWidth);
		speed = R.nextInt(SPEEDMAX + 1);
		direction = R.nextInt(DIRECTIONMAX + 1);
	}
	
	public int getSpeed()
	{
		return speed;
	}
	
	public int getDirection()
	{
		return direction;
	}
	
	public int getMaxSpeed()
	{
		return SPEEDMAX;
	}
	
	public void setSpeed(int x)
	{
		speed = x;
	}
	
	public void setDirection(int x)
	{
		direction = x;
	}
	
	public String toString()
	{
		return super.toString() + "speed=" + this.getSpeed() + " dir=" + this.getDirection() + " ";
	}
	
	@Override
	public void move()
	{
		/* newLocation(x,y) = oldLocation(x,y) + (deltaX, deltaY), where
			deltaX = cos(angle)*speed,
			deltaY = sin(angle)*speed, and
			angle = 90 - heading */
		
		int angle = 90 - this.getDirection();
		double deltaX = Math.cos(angle) * this.getSpeed();
		double deltaY = Math.sin(angle) * this.getSpeed();
		this.setX(deltaX + this.getX());
		this.setY(deltaY + this.getY());
	}
		
}