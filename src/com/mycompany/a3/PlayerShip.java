package com.mycompany.a3;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.geom.Point2D;

/**
 * Author: Nicholas Dubble
 * File: PlayerShip.java
 *
 * CSC-133
 * Doan Nguyen
 * Fall 2018
 */
public class PlayerShip extends MoveableObject implements ISteerable
{
	private int missileCount;
	private PlayerMissileLauncher missileLauncher;
	private final int MAXMISSILES = 10;
	private Point2D initialSpawn;
	
	public PlayerShip(int gameWorldHeight, int gameWorldWidth)
	{
		super(gameWorldHeight,gameWorldWidth);
		initialSpawn = new Point2D(gameWorldHeight/2,gameWorldWidth/2);
		this.setLocation(initialSpawn);
		this.setColor(ColorUtil.GREEN);
		this.setSpeed(0);
		this.setDirection(0);
		missileLauncher = new PlayerMissileLauncher(this.getX(), this.getY(),this.getSpeed(), gameWorldHeight, gameWorldWidth);
		missileCount = MAXMISSILES;
	}
	
	public PlayerMissileLauncher getMissileLauncher()
	{
		return missileLauncher;
	}
	
	public int getMissicleCount()
	{
		return missileCount;
	}
	
	public Point2D getINITIALSPAWN()
	{
		return initialSpawn;
	}
	
	public int getMAXMISSILES()
	{
		return MAXMISSILES;
	}
	
	public void setMissileCount(int x)
	{
		missileCount = x;
	}
	
	public String toString()
	{
		String playerShip = "PlayerShip: " + super.toString() + "missiles=" + this.getMissicleCount() + "\n";
		String playerMissileLauncher = this.getMissileLauncher().toString();
		return playerShip + playerMissileLauncher;
				
	}
	
	@Override
	public void turn()
	{
	}

}
