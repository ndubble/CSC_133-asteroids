package com.mycompany.a3;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.util.UITimer;
import com.mycompany.a3.views.MapView;
import com.mycompany.a3.views.PointsView;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;

import java.lang.String;
import java.util.Random;
import java.util.Vector;

import com.codename1.ui.Toolbar;

/**
 * Author: Nicholas Dubble
 * File: Game.java
 *
 * CSC-133
 * Doan Nguyen
 * Fall 2018
 */
public class Game extends Form implements Runnable
{
	private GameWorld gw;
	private MapView mv;
	private PointsView pv;
	private int refreshRate = 10;
	UITimer timer;
	private Vector<ControlPanelButton> controlPanelButtons; // passed to GamemodeCommand instance so it can disable buttons as needed
	private Vector<Command> commands; // passed to GameModeCommand instance so it can disable commands as needed
	
	public Game()
	{
		//Initialize Views and GameWorld
		mv = new MapView();
		gw = new GameWorld();
		pv = new PointsView(gw);
		
		controlPanelButtons = new Vector<ControlPanelButton>();
		commands = new Vector<Command>();
		
		//Register observers (MapView and PointsView) with the observable(GameWorld)
		gw.addObserver(mv);
		gw.addObserver(pv);
				
		timer = new UITimer (this);
		timer.schedule(refreshRate, true, this);
		
		//Use BorderLayout for the Form
		setLayout(new BorderLayout());
		//Create toolbar that will have two side menus: Commands and File
		Toolbar toolbar = new Toolbar();
		this.setToolbar(toolbar);
		
		//Command that will be used for the name of the File menu
		Command fileTitle = new Command("File");
		toolbar.addCommandToRightBar(fileTitle);
		
		// Create Checkbox that will indicate whether or not sound is currently disabled
		CheckBox checkSoundItem = new CheckBox("");
		CheckBoxSoundCommand checkBoxSoundCommand = new CheckBoxSoundCommand(gw);
		checkSoundItem.setCommand(checkBoxSoundCommand);
		//set the style for the check box
		checkSoundItem.getAllStyles().setBgTransparency(255);
		checkSoundItem.getAllStyles().setBgColor(ColorUtil.WHITE);
		checkSoundItem.setText("Disable Sound");
		checkSoundItem.setShiftText(40);
		
		//Create commands for the File side menu
		Command newCommand = new Command("New"){
			public void actionPerformed(ActionEvent ev) {
				System.out.println("New Command invoked");
			}
			};
		Command save = new Command("Save"){
			public void actionPerformed(ActionEvent ev) {
				System.out.println("Save Command invoked");
			}
			};
		Command undo = new Command("Undo"){
			public void actionPerformed(ActionEvent ev) {
				System.out.println("Undo Command invoked");
			}
			};
		
		Command about = new Command("About") {
			public void actionPerformed(ActionEvent ev) {
			System.out.println("About Command invoked");
			String Message = "Author: Nicholas Dubble\nCourse: CSC 133\nSemester: Fall 2018";
			Dialog.show("About", Message, "Ok", null);
			}
			};
		
		// Add the commands to the File Menu
		toolbar.addCommandToRightSideMenu(newCommand);
		toolbar.addCommandToRightSideMenu(save);
		toolbar.addCommandToRightSideMenu(undo);	
		toolbar.addComponentToRightSideMenu(checkSoundItem);
		toolbar.addCommandToRightSideMenu(about);	
		toolbar.addCommandToRightSideMenu(new QuitCommand());
		
		// Create the container called controlPanel which will hold the on-screen buttons for most of the commands
		Container controlPanel = new Container();
		controlPanel.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
		controlPanel.getAllStyles().setBorder(Border.createLineBorder(4,ColorUtil.YELLOW));
		
		Label commandsTextLabel = new Label("Commands:");
		controlPanel.add(commandsTextLabel);
		
		
		//Add the controlPanel buttons that will be on the west border
		ControlPanelButton addAsteroid = new ControlPanelButton ("Add Asteroid");
		ControlPanelButton addNPS = new ControlPanelButton("Add Non-Player Ship");
		ControlPanelButton addStation = new ControlPanelButton("Add Station");
		ControlPanelButton increaseSpeed = new ControlPanelButton("Increase Player Speed");
		ControlPanelButton decreaseSpeed = new ControlPanelButton("Decrease Player Speed");
		ControlPanelButton turnLeft = new ControlPanelButton("Turn Player Ship Left");
		ControlPanelButton turnRight = new ControlPanelButton("Turn Player Ship Right");
		ControlPanelButton turnPSMLLeft = new ControlPanelButton("Turn Player Ship ML Left");
		ControlPanelButton turnPSMLRight = new ControlPanelButton("Turn Player Ship ML Right");
		ControlPanelButton firePSMissile = new ControlPanelButton("Fire Player Ship Missile");
		ControlPanelButton jump = new ControlPanelButton("Jump Through Hyperspace");
		ControlPanelButton refuel = new ControlPanelButton("Refuel");
		ControlPanelButton gamemodeSelect = new ControlPanelButton("Pause"); // is passed to GamemodeCommand instance so it...
																			 // ... can change button text depending on what gamemode...
																			 // ... is active
		ControlPanelButton quit = new ControlPanelButton("Quit");
		
		// add buttons to the vector that will be passed to GamemodeCommand instance
		controlPanelButtons.add(increaseSpeed);
		controlPanelButtons.add(decreaseSpeed);
		controlPanelButtons.add(turnLeft);
		controlPanelButtons.add(turnRight);
		controlPanelButtons.add(turnPSMLLeft);
		controlPanelButtons.add(turnPSMLRight);
		controlPanelButtons.add(firePSMissile);
		controlPanelButtons.add(jump);
		controlPanelButtons.add(refuel);
		
		//Declare all the needed commands for the buttons, keys, and Commands side menu
		AddAsteroidCommand myAddAsteroid = new AddAsteroidCommand(gw);
		addAsteroid.setCommand(myAddAsteroid);
		
		AddNPSCommand myAddNPS = new AddNPSCommand(gw);
		addNPS.setCommand(myAddNPS);
		
		AddStationCommand myAddStation = new AddStationCommand(gw);
		addStation.setCommand(myAddStation);
		
		IncreaseSpeedCommand myIncreaseSpeed = new IncreaseSpeedCommand(gw);
		increaseSpeed.setCommand(myIncreaseSpeed);
		
		DecreaseSpeedCommand myDecreaseSpeed = new DecreaseSpeedCommand(gw);
		decreaseSpeed.setCommand(myDecreaseSpeed);
		
		TurnLeftCommand myTurnLeft = new TurnLeftCommand(gw);
		turnLeft.setCommand(myTurnLeft);
		
		TurnRightCommand myTurnRight = new TurnRightCommand(gw);
		turnRight.setCommand(myTurnRight);
		
		TurnPSMLLeftCommand myTurnPSMLLeft = new TurnPSMLLeftCommand(gw);
		turnPSMLLeft.setCommand(myTurnPSMLLeft);
		
		TurnPSMLRightCommand myTurnPSMLRight = new TurnPSMLRightCommand(gw);
		turnPSMLRight.setCommand(myTurnPSMLRight);
		
		FirePSMissileCommand myFirePSMissile = new FirePSMissileCommand(gw);
		firePSMissile.setCommand(myFirePSMissile);
		
		JumpCommand myJump = new JumpCommand(gw);
		jump.setCommand(myJump);
		
		RefuelCommand myRefuel = new RefuelCommand(gw);
		refuel.setCommand(myRefuel);
		
		QuitCommand myQuit= new QuitCommand();
		quit.setCommand(myQuit);
		
		// add commands to vector that will be passed to GamemodeCommand instance
		commands.add(myIncreaseSpeed);
		commands.add(myDecreaseSpeed);
		commands.add(myTurnLeft);
		commands.add(myTurnRight);
		commands.add(myTurnPSMLLeft);
		commands.add(myTurnPSMLRight);
		commands.add(myFirePSMissile);
		commands.add(myJump);
		commands.add(myRefuel);
		
		GamemodeCommand myGamemodeCommand = new GamemodeCommand(gw,timer,this, gamemodeSelect, controlPanelButtons, commands);
		gamemodeSelect.setCommand(myGamemodeCommand);
		
		// Bind the keys to the commands, add the button to the control panel, and add command to side menu if necessary
		addKeyListener('a',myAddAsteroid);
		controlPanel.add(addAsteroid);
		toolbar.addCommandToLeftSideMenu(myAddAsteroid);
		
		addKeyListener('y',myAddNPS);
		controlPanel.add(addNPS);
		
		addKeyListener('b',myAddStation);
		controlPanel.add(addStation);
		toolbar.addCommandToLeftSideMenu(myAddStation);
		
		addKeyListener(-91,myIncreaseSpeed);
		controlPanel.add(increaseSpeed);
		
		addKeyListener(-92,myDecreaseSpeed);
		controlPanel.add(decreaseSpeed);
		
		addKeyListener(-93,myTurnLeft);
		controlPanel.add(turnLeft);
		
		addKeyListener(-94,myTurnRight);
		controlPanel.add(turnRight);
		
		addKeyListener(44,myTurnPSMLLeft);
		controlPanel.add(turnPSMLLeft);
		
		addKeyListener(46,myTurnPSMLRight);
		controlPanel.add(turnPSMLRight);
		
		addKeyListener(-90,myFirePSMissile);
		controlPanel.add(firePSMissile);
		
		addKeyListener('j',myJump);
		controlPanel.add(jump);
		
		controlPanel.add(refuel);
		
		controlPanel.add(gamemodeSelect);
		
		addKeyListener('z',myQuit);
		controlPanel.add(quit);
		toolbar.addCommandToLeftSideMenu(myQuit);
		
		
		
		// Create command called Commands that will be used for the title of the Commands side menu and
		Command commandsTitle = new Command("Commands");
		toolbar.addCommandToLeftBar(commandsTitle);
		
		// Add the controlPanel container to the west border, PointsViews container to the north border, and MapsView container to the center
		add(BorderLayout.WEST,controlPanel);
		add(BorderLayout.NORTH,pv);
		add(BorderLayout.CENTER,mv);
		
		this.show();
		
		// Update the size of the game world based on the values of MapViews' getHeight() and getWidth() , which will have values by this point
		gw.init(mv.getHeight(),mv.getWidth(),refreshRate);
	}

	public Vector<ControlPanelButton> getControlPanelButtons()
	{
		return controlPanelButtons;
	}
	
	public Vector<Command> getCommands()
	{
		return commands;
	}
	
	// generate random integer between min and max inclusive
	public static int genRandInt(int min, int max)
	{
		Random r = new Random();
		int x = r.nextInt((max-min) + 1) + min;
		return x;
	}
	
	@Override
	public void run() 
	{
		if (!gw.getGameOverStatus())
		{
			int roll = this.genRandInt(1, 900); 
			if (roll>=1 && roll<=3) // generate NonPlayerShips throughout the game
				gw.addNPS();
			gw.tick();
		}
		else
		{
			timer.cancel();
			if (Dialog.show("Game Over!", "Final Score: " + gw.getPlayerScore() + "\nPlay again?", "Quit", "Yes")) 
				System.exit(0);
			else
			{
				gw.resetWorld();
				timer.schedule(refreshRate,true,this);
			}
		}
	}
}