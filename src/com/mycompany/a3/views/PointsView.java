package com.mycompany.a3.views;

import java.util.Observable;
import java.util.Observer;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Container;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.a3.GameWorld;
import com.mycompany.a3.IGameWorld;

/**
 * Author: Nicholas Dubble
 * File: PointsView.java
 *
 * CSC-133
 * Doan Nguyen
 * Fall 2018
 */
public class PointsView extends Container implements Observer 
{
	private Label pointsValueLabel;
	private Label missileCountValueLabel;
	private Label elapsedTimeValueLabel;
	private Label soundValueLabel;
	private Label livesValueLabel;
	
	public PointsView(GameWorld gw) 
	{
		// Instantiate text labels
		Label pointsTextLabel = new Label ("Points: ");
		Label missileCountTextLabel = new Label ("Missile Count: ");
		Label elapsedTimeTextLabel = new Label("Elapsed Time: ");
		Label soundTextLabel = new Label("Sound: ");
		Label livesTextLabel = new Label("Lives: ");
		
		// Set color
		 pointsTextLabel.getAllStyles().setFgColor(ColorUtil.BLACK);
		 pointsTextLabel.getAllStyles().setBgTransparency(255);
		 pointsTextLabel.getAllStyles().setBgColor(ColorUtil.WHITE);
		 
		 missileCountTextLabel.getAllStyles().setFgColor(ColorUtil.BLACK);
		 missileCountTextLabel.getAllStyles().setBgTransparency(255);
		 missileCountTextLabel.getAllStyles().setBgColor(ColorUtil.WHITE);
		 
		 elapsedTimeTextLabel.getAllStyles().setFgColor(ColorUtil.BLACK);
		 elapsedTimeTextLabel.getAllStyles().setBgTransparency(255);
		 elapsedTimeTextLabel.getAllStyles().setBgColor(ColorUtil.WHITE);
		 
		 soundTextLabel.getAllStyles().setFgColor(ColorUtil.BLACK);
		 soundTextLabel.getAllStyles().setBgTransparency(255);
		 soundTextLabel.getAllStyles().setBgColor(ColorUtil.WHITE);
		 
		 livesTextLabel.getAllStyles().setFgColor(ColorUtil.BLACK);
		 livesTextLabel.getAllStyles().setBgTransparency(255);
		 livesTextLabel.getAllStyles().setBgColor(ColorUtil.WHITE);
		// Instantiating value labels
		pointsValueLabel = new Label ("XXX");
		missileCountValueLabel = new Label ("XXX");
		elapsedTimeValueLabel= new Label ("XXX");
		soundValueLabel= new Label ("XXX");
		livesValueLabel= new Label ("XXX");
		
		// Set color
		pointsValueLabel.getAllStyles().setFgColor(ColorUtil.BLACK);
		pointsValueLabel.getAllStyles().setBgTransparency(255);
		pointsValueLabel.getAllStyles().setBgColor(ColorUtil.WHITE);
		
		missileCountValueLabel.getAllStyles().setFgColor(ColorUtil.BLACK);
		missileCountValueLabel.getAllStyles().setBgTransparency(255);
		missileCountValueLabel.getAllStyles().setBgColor(ColorUtil.WHITE);
		
		elapsedTimeValueLabel.getAllStyles().setFgColor(ColorUtil.BLACK);
		elapsedTimeValueLabel.getAllStyles().setBgTransparency(255);
		elapsedTimeValueLabel.getAllStyles().setBgColor(ColorUtil.WHITE);
		
		soundValueLabel.getAllStyles().setFgColor(ColorUtil.BLACK);
		soundValueLabel.getAllStyles().setBgTransparency(255);
		soundValueLabel.getAllStyles().setBgColor(ColorUtil.WHITE);
		
		livesValueLabel.getAllStyles().setFgColor(ColorUtil.BLACK);
		livesValueLabel.getAllStyles().setBgTransparency(255);
		livesValueLabel.getAllStyles().setBgColor(ColorUtil.WHITE);
		
		
		// Adding a container with a boxlayout
		Container myContainer = new Container();
		myContainer.setLayout(new BoxLayout(BoxLayout.X_AXIS));
		
		// Adding all labels in order
		myContainer.add(pointsTextLabel);
		myContainer.add(pointsValueLabel);
		
		myContainer.add(missileCountTextLabel);
		myContainer.add(missileCountValueLabel);
		
		myContainer.add(elapsedTimeTextLabel);
		myContainer.add(elapsedTimeValueLabel);
		
		myContainer.add(soundTextLabel);
		myContainer.add(soundValueLabel);
		
		myContainer.add(livesTextLabel);
		myContainer.add(livesValueLabel);	
		
		this.add(myContainer);
	}

	@Override
	public void update(Observable observable, Object arg) 
	{
		// Casting data as a GameWorld
		IGameWorld gw = (IGameWorld) arg;
		
		// Getting player score
		int score = gw.getPlayerScore();
		pointsValueLabel.setText("" + (score > 99 ? "" : "0") + (score > 9 ? "" : "0") + score);
		
		int missileCount = gw.getMissileCount();
		if (missileCount != -1) // If Player Ship Exists
			missileCountValueLabel.setText("" + (missileCount > 99 ? "" : "0") + (missileCount > 9 ? "" : "0") + missileCount);
		
		int elapsedTime = gw.getElapsedTime();
		elapsedTimeValueLabel.setText("" + (elapsedTime > 99 ? "" : "0") + (elapsedTime > 9 ? "" : "0") + elapsedTime);
		
		boolean sound = gw.getSound();
		if(sound == true)
			soundValueLabel.setText("ON");
		else
			soundValueLabel.setText("OFF");
		
		int lives = gw.getPlayerLives();
		livesValueLabel.setText("" + lives);
		
		this.repaint();
	}

}
