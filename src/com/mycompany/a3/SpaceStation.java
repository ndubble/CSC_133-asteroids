package com.mycompany.a3;

import com.codename1.charts.util.ColorUtil;

/**
 * Author: Nicholas Dubble
 * File: SpaceStation.java
 *
 * CSC-133
 * Doan Nguyen
 * Fall 2018
 */
public class SpaceStation extends FixableObject 
{
	private int blinkRate;
	private final int BLINKRATEMAX = 4;
	private Boolean lightOn;
	
	public SpaceStation(int gameWorldHeight, int gameWorldWidth) 
	{
		super(gameWorldHeight, gameWorldWidth);
		blinkRate = R.nextInt(BLINKRATEMAX + 1);
		this.setLightOn(false);
		this.setColor(ColorUtil.CYAN);
	}
	
	public int getBlinkRate()
	{
		return blinkRate;
	}
	
	public Boolean getLightOn()
	{
		return lightOn;
	}
	
	public void setLightOn(Boolean x)
	{
		lightOn = x;
	}
	
	public String toString()
	{
		String light;
		if (lightOn)
			light = "on";
		else
			light = "off";
		return "Station: " + super.toString() + "blink rate=" + blinkRate + " light: " + light;
	}

}
