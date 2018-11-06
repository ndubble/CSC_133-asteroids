package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

/**
 * Author: Nicholas Dubble
 * File: RemoveMissilePSCommand.java
 *
 * CSC-133
 * Doan Nguyen
 * Fall 2018
 */
public class RemoveMissilePSCommand extends Command 
{
	private GameWorld gw; // Reference to a Game World
	
	public RemoveMissilePSCommand(GameWorld gw)
	{
		super("Remove Missile and PS");
		this.gw = gw;
	}
	
	public void actionPerformed(ActionEvent e)
	{
		 if (e.getKeyEvent() != -1) {
			 gw.removeMissilePS();
		 }
	}
}
