package com.mycompany.a3;

import com.codename1.ui.geom.Point2D;
import com.codename1.charts.util.ColorUtil;
import java.util.Random;

/**
 * Author: Nicholas Dubble
 * File: GameObject.java
 *
 * CSC-133
 * Doan Nguyen
 * Fall 2018
 */
public abstract class GameObject 
{
	private Point2D location;
	protected static final java.util.Random R = new java.util.Random(); // Random class methods used by child classes
	private int color;
	
	public GameObject(int gameWorldHeight, int gameWorldWidthwidth)
	{
		
		location = new Point2D( R.nextInt((int)gameWorldHeight+1),R.nextInt((int)gameWorldWidthwidth)+1); // default value
		color = ColorUtil.rgb(0,0,0);	// default value
	}
	
	public double getX()
	{
		return location.getX();
	}
	
	public double getY()
	{	
		return location.getY();
	}
	
	public Point2D getLocation()
	{
		return location;
	}
	
	public int getColor()
	{
		return color;
	}
	
	public void setX(double x)
	{
		location.setX(x);
	}
	
	public void setY(double y)
	{
		location.setY(y);
	}
	
	public void setLocation(Point2D loc)
	{
		location.setX(loc.getX());
		location.setY(loc.getY());
	}
	
	public void setColor(int c)
	{
		color = c;
	}
	
	public double rnd1Dec(double x) // round doubles to 1 decimal
	{
		return Math.round(x*10)/10;
	}
	
	public String toString()
	{
		return "loc=" + rnd1Dec(this.getX()) + "," + rnd1Dec(this.getY()) + " color=[" + ColorUtil.red(color) + ","
				+ ColorUtil.green(color) + "," + ColorUtil.blue(color)  + "] ";
	}
}