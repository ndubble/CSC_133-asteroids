package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

/**
 * Author: Nicholas Dubble
 * File: TurnLeftCommand.java
 *
 * CSC-133
 * Doan Nguyen
 * Fall 2018
 */
public class TurnLeftCommand extends Command 
{
	private GameWorld gw; // Reference to a Game World
	
	public TurnLeftCommand(GameWorld gw)
	{
		super("Turn Player Ship Left");
		this.gw = gw;
	}
	
	public void actionPerformed(ActionEvent e)
	{
		 if (e.getKeyEvent() != -1) {
			 gw.turnLeft();
		 }
	}
}
