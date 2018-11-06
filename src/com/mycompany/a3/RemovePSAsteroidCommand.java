package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

/**
 * Author: Nicholas Dubble
 * File: RemovePSAsteroidCommand.java
 *
 * CSC-133
 * Doan Nguyen
 * Fall 2018
 */
public class RemovePSAsteroidCommand extends Command 
{
	private GameWorld gw; // Reference to a Game World
	
	public RemovePSAsteroidCommand(GameWorld gw)
	{
		super("Remove Player Ship and Asteroid");
		this.gw = gw;
	}
	
	public void actionPerformed(ActionEvent e)
	{
		 if (e.getKeyEvent() != -1) {
			 gw.removePSAsteroid();
		 }
	}
}
