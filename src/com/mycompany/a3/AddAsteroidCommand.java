package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

/**
 * Author: Nicholas Dubble
 * File: AddAsteroidCommand.java
 *
 * CSC-133
 * Doan Nguyen
 * Fall 2018
 */
public class AddAsteroidCommand extends Command 
{
	private GameWorld gw; // Reference to a Game World
	
	public AddAsteroidCommand(GameWorld gw)
	{
		super("Add Asteroid");
		this.gw = gw;
	}
	
	public void actionPerformed(ActionEvent e)
	{
		 if (e.getKeyEvent() != -1) 
		 {
			 gw.addAsteroid();
		 }
	}
}
