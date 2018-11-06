package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

/**
 * Author: Nicholas Dubble
 * File: IncreaseSpeedCommand.java
 *
 * CSC-133
 * Doan Nguyen
 * Fall 2018
 */
public class IncreaseSpeedCommand extends Command 
{
	private GameWorld gw; // Reference to a Game World
	
	public IncreaseSpeedCommand(GameWorld gw)
	{
		super("Increase Player Speed");
		this.gw = gw;
	}
	
	public void actionPerformed(ActionEvent e)
	{
		 if (e.getKeyEvent() != -1) {
			 gw.increaseSpeed();
		 }
	}
}
