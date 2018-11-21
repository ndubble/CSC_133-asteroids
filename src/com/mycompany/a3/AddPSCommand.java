package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

/**
 * Author: Nicholas Dubble
 * File: AddPSCommand.java
 *
 * CSC-133
 * Doan Nguyen
 * Fall 2018
 */
public class AddPSCommand extends Command 
{
	private GameWorld gw; // Reference to a Game World
	
	public AddPSCommand(GameWorld gw)
	{
		super("Add Player Ship");
		this.gw = gw;
	}
	
	public void actionPerformed(ActionEvent e)
	{
		 if (e.getKeyEvent() != -1) 
		 {
			 if (gw.getGamePlayModeStatus() == true) // if game is currently running
				 gw.addPS();
		 }
	}
}
