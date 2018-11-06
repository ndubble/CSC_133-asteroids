package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

/**
 * Author: Nicholas Dubble
 * File: CheckBoxSoundCommand.java
 *
 * CSC-133
 * Doan Nguyen
 * Fall 2018
 */
public class CheckBoxSoundCommand extends Command 
{
	private GameWorld gw; // Reference to a Game World
	
	public CheckBoxSoundCommand(GameWorld gw)
	{
		super("Disable sound: ");
		this.gw = gw;
	}
	
	public void actionPerformed(ActionEvent e)
	{
		System.out.println("ChecBoxSound Command invoked");
		gw.setSound();
	}
}
