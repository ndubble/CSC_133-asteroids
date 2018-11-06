package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

/**
 * Author: Nicholas Dubble
 * File: FirePSMissileCommand.java
 *
 * CSC-133
 * Doan Nguyen
 * Fall 2018
 */
public class FirePSMissileCommand extends Command 
{
	private GameWorld gw; // Reference to a Game World
	
	public FirePSMissileCommand(GameWorld gw)
	{
		super("Fire Player Ship Missile");
		this.gw = gw;
	}
	
	public void actionPerformed(ActionEvent e)
	{
		 if (e.getKeyEvent() != -1) {
			 gw.firePSMissile();
		 }
	}
}
