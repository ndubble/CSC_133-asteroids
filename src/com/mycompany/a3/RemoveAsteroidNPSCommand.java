package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

/**
 * Author: Nicholas Dubble
 * File: RemoveAsteroidNPSCommand.java
 *
 * CSC-133
 * Doan Nguyen
 * Fall 2018
 */
public class RemoveAsteroidNPSCommand extends Command 
{
	private GameWorld gw; // Reference to a Game World
	
	public RemoveAsteroidNPSCommand(GameWorld gw)
	{
		super("Remove Asteroid and Non-Player Ship");
		this.gw = gw;
	}
	
	public void actionPerformed(ActionEvent e)
	{
		 if (e.getKeyEvent() != -1) {
			 gw.removeAsteroidNPS();
		 }
	}
}
