package com.mycompany.a3;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point;
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
	private final static int PLAYERSHIPSIZE = 100;
	private Point top, bottomLeft, bottomRight;
	private final int TURNAMOUNT = 20;
	
	public PlayerShip(int gameWorldHeight, int gameWorldWidth)
	{
		super(gameWorldHeight,gameWorldWidth);
		initialSpawn = new Point2D(gameWorldWidth/2,gameWorldHeight/2);
		this.setSize(PLAYERSHIPSIZE);
		this.setLocation(initialSpawn);
		this.setColor(ColorUtil.GREEN);
		this.setSpeed(0);
		this.setDirection(0);
		missileLauncher = new PlayerMissileLauncher(this.getX(), this.getY(),this.getSpeed());
		missileCount = MAXMISSILES;
		top = new Point (0, PLAYERSHIPSIZE/2);
		bottomLeft = new Point (-(PLAYERSHIPSIZE/2),-(PLAYERSHIPSIZE/2));
		bottomRight = new Point ((PLAYERSHIPSIZE/2),-(PLAYERSHIPSIZE/2));
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
	
	public void draw(Graphics g, Point pCmpRelPrnt)
	{
		//draw a triangle
		g.setColor(this.getColor());
		g.drawLine(pCmpRelPrnt.getX() + (int)this.getLocation().getX() + top.getX(), 
				pCmpRelPrnt.getY() + (int)this.getLocation().getY() + top.getY(), 
				pCmpRelPrnt.getX() + (int)this.getLocation().getX() + bottomLeft.getX(), 
				pCmpRelPrnt.getY() + (int)this.getLocation().getY() + bottomLeft.getY());
		
		g.drawLine(pCmpRelPrnt.getX() + (int)this.getLocation().getX() + bottomLeft.getX(), 
				pCmpRelPrnt.getY() + (int)this.getLocation().getY() + bottomLeft.getY(), 
				pCmpRelPrnt.getX() + (int)this.getLocation().getX() + bottomRight.getX(), 
				pCmpRelPrnt.getY() + (int)this.getLocation().getY() + bottomRight.getY());
		
		g.drawLine(pCmpRelPrnt.getX() + (int)this.getLocation().getX() + bottomRight.getX(), 
				pCmpRelPrnt.getY() + (int)this.getLocation().getY() + bottomRight.getY(), 
				pCmpRelPrnt.getX() + (int)this.getLocation().getX() + top.getX(), 
				pCmpRelPrnt.getY() + (int)this.getLocation().getY() + top.getY());
	}
	
	/* (non-Javadoc)
	 * @see com.mycompany.a3.ISteerable#turnLeft()
	 */
	@Override
	public void turnLeft() {
		int currentDirection = this.getDirection();
		if (currentDirection-TURNAMOUNT>=0)
			this.setDirection(currentDirection - TURNAMOUNT);
		else
			this.setDirection(360 + (currentDirection - TURNAMOUNT));
		
	}

	/* (non-Javadoc)
	 * @see com.mycompany.a3.ISteerable#turnRight()
	 */
	@Override
	public void turnRight() {
		int currentDirection = this.getDirection();
		if (currentDirection+TURNAMOUNT<=359)
			this.setDirection(currentDirection + TURNAMOUNT);
		else
			this.setDirection(Math.abs(360 - (currentDirection+TURNAMOUNT)));
	}

	/* (non-Javadoc)
	 * @see com.mycompany.a3.ICollider#collidesWith(com.mycompany.a3.ICollider)
	 */
	@Override
	public boolean collidesWith(ICollider otherObject) 
	{
		boolean result = false;
		if (otherObject instanceof Missile)
		{
			Missile missObj = (Missile) otherObject;
			if (missObj.getIsPlayerMissile() == true)
				return false;
		}
		GameObject otherObj = (GameObject) otherObject;
		int dx,dy;
		if (otherObject instanceof SpaceStation)
		{
			// find dist between centers ( use square, to avoid taking roots)
			dx = (int)this.getX() - ((int)otherObj.getX() + (int)otherObj.getSize()/2);
			dy = (int)this.getY() - ((int)otherObj.getY() + (int)otherObj.getSize()/2);
		}
		else
		{
			// find dist between centers ( use square, to avoid taking roots)
			dx = (int)this.getX() - (int)otherObj.getX();
			dy = (int)this.getY() - (int)otherObj.getY();
		}
		int distBetweenCentersSqr = (dx*dx + dy*dy);
		//find square of sum of radii
		int thisRadius = this.getSize()/2;
		int otherRadius = otherObj.getSize()/2;
		int radiiSqr = (thisRadius*thisRadius + 2*thisRadius*otherRadius + otherRadius*otherRadius);
		if (distBetweenCentersSqr <= radiiSqr)
		{
			result = true;
			if (!(otherObject instanceof SpaceStation)) // we want to be able to check if it collides again with this SpaceStation in the future
			{
				this.getCollideWithList().add(otherObj);
				otherObj.getCollideWithList().add(this);
			}
		}
		return result;
	}

	/* (non-Javadoc)
	 * @see com.mycompany.a3.ICollider#handleCollision(com.mycompany.a3.ICollider)
	 */
	@Override
	public void handleCollision(ICollider otherObject) 
	{
		if (otherObject instanceof SpaceStation)
		{
			this.setMissileCount(MAXMISSILES);
		}
		else
		{
			this.setKillStatus();
			MoveableObject otherObj = (MoveableObject) otherObject;
			otherObj.setKillStatus();
		}
	}

	/* (non-Javadoc)
	 * @see com.mycompany.a3.ICollider#collidesWithWall()
	 */
	@Override
	public void collidesWithWall() {
		this.collidesWithWall(PLAYERSHIPSIZE, PLAYERSHIPSIZE);
		
	}

}
