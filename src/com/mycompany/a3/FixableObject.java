package com.mycompany.a3;

/**
 * Author: Nicholas Dubble
 * File: FixableObject.java
 *
 * CSC-133
 * Doan Nguyen
 * Fall 2018
 */
public abstract class FixableObject extends GameObject 
{
	private int id;
	private static int counter = 0;
	
	public FixableObject(int gameWorldHeight, int gameWorldWidth) 
	{
		super(gameWorldHeight,gameWorldWidth);
		id = counter;
		counter++;
	}
	
	public String toString()
	{
		return super.toString();
	}

}