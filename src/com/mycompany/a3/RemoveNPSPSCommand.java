package com.mycompany.a3;

import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.Command;

/**
 * Author: Nicholas Dubble
 * File: RemoveNPSPSCommand.java
 *
 * CSC-133
 * Doan Nguyen
 * Fall 2018
 */
public class RemoveNPSPSCommand extends Command 
{
	private GameWorld gw; // Reference to a Game World
	
	public RemoveNPSPSCommand(GameWorld gw)
	{
		super("Remove Non-Player Ship and Player Ship");
		this.gw = gw;
	}
	
	public void actionPerformed(ActionEvent e)
	{
		 if (e.getKeyEvent() != -1) {
			 gw.removeNpsPs();
		 }
	}
}
