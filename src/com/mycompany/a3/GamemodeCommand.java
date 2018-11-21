/**
 * 
 */
package com.mycompany.a3;

import java.util.Iterator;
import java.util.Vector;

import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Form;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.util.UITimer;
import com.mycompany.a3.Game;

/**
 * Author: Nicholas Dubble
 * File: GamemodeCommand.java
 *
 * CSC-133
 * Doan Nguyen
 * Fall 2018
 */
public class GamemodeCommand extends Command 
{
	private GameWorld gw; // Reference to a Game World
	private UITimer timer;
	private Form game;
	private ControlPanelButton gameModeSelectButton;
	private Vector<ControlPanelButton> controlPanelButtons;
	private Vector<Command> commands;
	
	public GamemodeCommand(GameWorld gw, UITimer t, Form g, ControlPanelButton button, Vector<ControlPanelButton> controlPanel, Vector<Command> comm)
	{
		super("Pause");
		this.gw = gw;
		timer = t;
		game = g;
		gameModeSelectButton = button;
		controlPanelButtons = controlPanel;
		commands = comm;
	}
	
	public void actionPerformed(ActionEvent e)
	{
		 if (e.getKeyEvent() != -1) 
		 {
			 gw.toggleGamePlayMode();
			 if (gw.getGamePlayModeStatus() == true) // if just switched to "play" mode
			 {
				 gameModeSelectButton.setText("Pause");
				 if (gw.getSound() == true)
					 gw.toggleBackgroundSound();
				 for(int i = 0; i < controlPanelButtons.size() && i < commands.size(); i++)
				 {
					 if (i == controlPanelButtons.size() - 1)
					 {
						 controlPanelButtons.elementAt(i).setEnabled(false); // disable refuel during play mode
						 commands.elementAt(i).setEnabled(true);
					 }
					 else
					 {
						 controlPanelButtons.elementAt(i).setEnabled(true);
						 commands.elementAt(i).setEnabled(true);
					 }
				 }
				 
				game.addKeyListener('a',commands.elementAt(0));				
				game.addKeyListener('y',commands.elementAt(1));					
				game.addKeyListener('b',commands.elementAt(2));												
				game.addKeyListener(-91,commands.elementAt(3));						
				game.addKeyListener(-92,commands.elementAt(4));						
				game.addKeyListener(-93,commands.elementAt(5));							
				game.addKeyListener(-94,commands.elementAt(6));					
				game.addKeyListener(44,commands.elementAt(7));		
				game.addKeyListener(46,commands.elementAt(8));						
				game.addKeyListener(-90,commands.elementAt(9));			
				game.addKeyListener('j',commands.elementAt(10));	
				
				IIterator iterator = gw.getIterator();
				while(iterator.hasNext()) // unselect any currently selected objects if there are any
				{
					Object obj = iterator.getNext();
					if (obj instanceof ISelectable)
					{
						ISelectable selectableObj = (ISelectable) obj;
						selectableObj.setSelected(false);
					}
				}
				
				timer.schedule(gw.getTimerRate(), true, game);
			 }
			 else
			 {
				gameModeSelectButton.setText("Play");
				if (gw.getSound() == true)
					gw.toggleBackgroundSound();
				 
				for(int i = 0; i < controlPanelButtons.size() && i < commands.size(); i++) // disable all gameplay commands/buttons
				{
					 if (i == controlPanelButtons.size() - 1)
					 {
						 controlPanelButtons.elementAt(i).setEnabled(true); // enable refuel during pause mode
						 commands.elementAt(i).setEnabled(true);
					 }
					 else
					 {
						 controlPanelButtons.elementAt(i).setEnabled(false);
						 commands.elementAt(i).setEnabled(false);
					 }
				}

				game.removeKeyListener('a',commands.elementAt(0));				
				game.removeKeyListener('y',commands.elementAt(1));					
				game.removeKeyListener('b',commands.elementAt(2));												
				game.removeKeyListener(-91,commands.elementAt(3));						
				game.removeKeyListener(-92,commands.elementAt(4));						
				game.removeKeyListener(-93,commands.elementAt(5));							
				game.removeKeyListener(-94,commands.elementAt(6));					
				game.removeKeyListener(44,commands.elementAt(7));		
				game.removeKeyListener(46,commands.elementAt(8));						
				game.removeKeyListener(-90,commands.elementAt(9));				
				game.removeKeyListener('j',commands.elementAt(10));
				
				timer.cancel();
			 }
		
		 }
	}
}
