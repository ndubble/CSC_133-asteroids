package com.mycompany.a3;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Border;
import com.mycompany.a3.views.MapView;
import com.mycompany.a3.views.PointsView;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;
import java.lang.String;
import com.codename1.ui.Toolbar;

/**
 * Author: Nicholas Dubble
 * File: Game.java
 *
 * CSC-133
 * Doan Nguyen
 * Fall 2018
 */
public class Game extends Form 
{
	private GameWorld gw;
	private MapView mv;
	private PointsView pv;
	
	public Game()
	{
		//Initialize Views and GameWorld
		mv = new MapView();
		gw = new GameWorld();
		pv = new PointsView(gw);
		//Register observers (MapView and PointsView) with the observable(GameWorld)
		gw.addObserver(mv);
		gw.addObserver(pv);
				
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
		ControlPanelButton addPS = new ControlPanelButton("Add Player Ship");
		ControlPanelButton increaseSpeed = new ControlPanelButton("Increase Player Speed");
		ControlPanelButton decreaseSpeed = new ControlPanelButton("Decrease Player Speed");
		ControlPanelButton turnLeft = new ControlPanelButton("Turn Player Ship Left");
		ControlPanelButton turnRight = new ControlPanelButton("Turn Player Ship Right");
		ControlPanelButton turnPSMLLeft = new ControlPanelButton("Turn Player Ship ML Left");
		ControlPanelButton turnPSMLRight = new ControlPanelButton("Turn Player Ship ML Right");
		ControlPanelButton firePSMissile = new ControlPanelButton("Fire Player Ship Missile");
		ControlPanelButton launchNPSMissile = new ControlPanelButton("Launch Non-Player Ship Missile");
		ControlPanelButton jump = new ControlPanelButton("Jump Through Hyperspace");
		ControlPanelButton newSupply = new ControlPanelButton("Load Player Ship with Missile Supply");
		ControlPanelButton removeMissileAsteroid = new ControlPanelButton("Destroy Player Missile and Asteroid");
		ControlPanelButton removeMissileNPS = new ControlPanelButton("Destroy Player Missile and Non-Player Ship");
		ControlPanelButton removeMissilePS = new ControlPanelButton("Destroy Non-Player Ship Missile and Player Ship");
		ControlPanelButton removePSAsteroid = new ControlPanelButton("Destroy Player Ship and Asteroid");
		ControlPanelButton removeNPSPS = new ControlPanelButton("Destroy Non-Player Ship and Player Ship");
		ControlPanelButton removeTwoAsteroids = new ControlPanelButton("Destroy Two Asteroids");
		ControlPanelButton removeAsteroidNPS = new ControlPanelButton("Destroy Asteroid and Non-Player Ship");
		ControlPanelButton tick = new ControlPanelButton("Tick (increase game time)");
		ControlPanelButton quit = new ControlPanelButton("Quit");
			
		
		//Declare all the needed commands for the buttons, keys, and Commands side menu
		AddAsteroidCommand myAddAsteroid = new AddAsteroidCommand(gw);
		addAsteroid.setCommand(myAddAsteroid);
		
		AddNPSCommand myAddNPS = new AddNPSCommand(gw);
		addNPS.setCommand(myAddNPS);
		
		AddStationCommand myAddStation = new AddStationCommand(gw);
		addStation.setCommand(myAddStation);
		
		AddPSCommand myAddPS = new AddPSCommand(gw);
		addPS.setCommand(myAddPS);
		
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
		
		LaunchNPSMissileCommand myLaunchNPSMissile = new LaunchNPSMissileCommand(gw);
		launchNPSMissile.setCommand(myLaunchNPSMissile);
		
		JumpCommand myJump = new JumpCommand(gw);
		jump.setCommand(myJump);
		
		NewSupplyCommand myNewSupply = new NewSupplyCommand(gw);
		newSupply.setCommand(myNewSupply);
		
		RemoveMissileAsteroidCommand myRemoveMissileAsteroid = new RemoveMissileAsteroidCommand(gw);
		removeMissileAsteroid.setCommand(myRemoveMissileAsteroid);
		
		RemoveMissileNPSCommand myRemoveMissileNPS = new RemoveMissileNPSCommand(gw);
		removeMissileNPS.setCommand(myRemoveMissileNPS);
		
		RemoveMissilePSCommand myRemoveMissilePS = new RemoveMissilePSCommand(gw);
		removeMissilePS.setCommand(myRemoveMissilePS);
		
		RemovePSAsteroidCommand myRemovePSAsteroid = new RemovePSAsteroidCommand(gw);
		removePSAsteroid.setCommand(myRemovePSAsteroid);
		
		RemoveNPSPSCommand myRemoveNPSPS = new RemoveNPSPSCommand(gw);
		removeNPSPS.setCommand(myRemoveNPSPS);
		
		RemoveTwoAsteroidsCommand myRemoveTwoAsteroids = new RemoveTwoAsteroidsCommand(gw);
		removeTwoAsteroids.setCommand(myRemoveTwoAsteroids);
		
		RemoveAsteroidNPSCommand myRemoveAsteroidNPS = new RemoveAsteroidNPSCommand(gw);
		removeAsteroidNPS.setCommand(myRemoveAsteroidNPS);
		
		TickCommand myTick= new TickCommand(gw);
		tick.setCommand(myTick);
		
		QuitCommand myQuit= new QuitCommand();
		quit.setCommand(myQuit);
		
		
		// Bind the keys to the commands, add the button to the control panel, and add command to side menu if necessary
		addKeyListener('a',myAddAsteroid);
		controlPanel.add(addAsteroid);
		toolbar.addCommandToLeftSideMenu(myAddAsteroid);
		
		addKeyListener('y',myAddNPS);
		controlPanel.add(addNPS);
		
		addKeyListener('b',myAddStation);
		controlPanel.add(addStation);
		toolbar.addCommandToLeftSideMenu(myAddStation);
		
		addKeyListener('s',myAddPS);
		controlPanel.add(addPS);
		toolbar.addCommandToLeftSideMenu(myAddPS);
		
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
		
		addKeyListener('L',myLaunchNPSMissile);
		controlPanel.add(launchNPSMissile);
		
		addKeyListener('j',myJump);
		controlPanel.add(jump);
		
		addKeyListener('n',myNewSupply);
		controlPanel.add(newSupply);
		toolbar.addCommandToLeftSideMenu(myNewSupply);
		
		addKeyListener('k',myRemoveMissileAsteroid);
		controlPanel.add(removeMissileAsteroid);
		toolbar.addCommandToLeftSideMenu(myRemoveMissileAsteroid);
		
		addKeyListener('e',myRemoveMissileNPS);
		controlPanel.add(removeMissileNPS);
		
		addKeyListener('E',myRemoveMissilePS);
		controlPanel.add(removeMissilePS);
		
		addKeyListener('c',myRemovePSAsteroid);
		controlPanel.add(removePSAsteroid);
		toolbar.addCommandToLeftSideMenu(myRemovePSAsteroid);
		
		addKeyListener('h',myRemoveNPSPS);
		controlPanel.add(removeNPSPS);
		
		addKeyListener('x',myRemoveTwoAsteroids);
		controlPanel.add(removeTwoAsteroids);
		toolbar.addCommandToLeftSideMenu(myRemoveTwoAsteroids);
		
		addKeyListener('i',myRemoveAsteroidNPS);
		controlPanel.add(removeAsteroidNPS);
		
		addKeyListener('t',myTick);
		controlPanel.add(tick);
		toolbar.addCommandToLeftSideMenu(myTick);
		
		addKeyListener('z',myQuit);
		controlPanel.add(quit);
		toolbar.addCommandToLeftSideMenu(myQuit);
		
		// Create command called Commandsthat will be used for the title of the Commands side menu and
		Command commandsTitle = new Command("Commands");
		toolbar.addCommandToLeftBar(commandsTitle);
		
		// Add the controlPanel container to the west border, PointsViews container to the north border, and MapsView container to the center
		add(BorderLayout.WEST,controlPanel);
		add(BorderLayout.NORTH,pv);
		add(BorderLayout.CENTER,mv);
		
		this.show();
		
		// Update the size of the game world based on the values of MapViews' getHeight() and getWidth() , which will have values by this point
		gw.init(mv.getHeight(),mv.getWidth());
	}
}