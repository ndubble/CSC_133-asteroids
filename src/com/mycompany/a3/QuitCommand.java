package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionEvent;

/**
 * Author: Nicholas Dubble
 * File: QuitCommand.java
 *
 * CSC-133
 * Doan Nguyen
 * Fall 2018
 */
public class QuitCommand extends Command 
{
	
	public QuitCommand()
	{
		super("Quit");
	}
	
	public void actionPerformed(ActionEvent e)
	{
		 if (e.getKeyEvent() != -1) {
			System.out.println("Quit Command invoked");
			if (Dialog.show("Confirm", "Do you want to proceed?", "Quit", "Cancel")) 
				System.exit(0);
		 }
	}
}
