package com.mycompany.a3;

import com.mycompany.a3.GameObject;


/**
 * Author: Nicholas Dubble
 * File: MoveableObject.java
 *
 * CSC-133
 * Doan Nguyen
 * Fall 2018
 */
public abstract class MoveableObject extends GameObject implements IMoveable
{
	private int speed;
	private int direction;
	protected final static int SPEEDMAX = 10;
	protected final static int DIRECTIONMAX = 359;
	private boolean kill; // flag that is set if this object needs to be removed because of a collision

	public MoveableObject(int gameWorldHeight, int gameWorldWidth) 
	{
		super(gameWorldHeight,gameWorldWidth);
		speed = R.nextInt(SPEEDMAX + 1);
		direction = R.nextInt(DIRECTIONMAX + 1);
		kill = false;
	}
	
	public boolean getKillStatus()
	{
		return kill;
	}
	
	public void setKillStatus()
	{
		kill = true;
	}
	
	public int getSpeed()
	{
		return speed;
	}
	
	public int getDirection()
	{
		return direction;
	}
	
	public int getMaxSpeed()
	{
		return SPEEDMAX;
	}
	
	public void setSpeed(int x)
	{
		speed = x;
	}
	
	public void setDirection(int x)
	{
		direction = x;
	}
	
	public String toString()
	{
		return super.toString() + "speed=" + this.getSpeed() + " dir=" + this.getDirection() + " ";
	}
	
	@Override
	public void move(int elapsedMillisecs, GameWorld gw)
	{
		if (this instanceof NonPlayerShip) // if moveable object that is to be moved is a NonPlayerShip
		{
			// Calculate whether or not a PlayerShip is in range to be fired upon
			NonPlayerShip nps = (NonPlayerShip) this;
			int range = 50;
			int xPoints[] = new int[range];
			int yPoints[] = new int[range];
			xPoints[0] = (int)this.getLocation().getX();
			yPoints[0] = (int)this.getLocation().getY();
			double dist = 40 * ((double)elapsedMillisecs/50);
			double angle = Math.toRadians(90 + nps.getMissileLauncher().getDirection());
			double deltaX = Math.cos(angle) * dist;
			double deltaY = Math.sin(angle) * dist;
			// create a series of points in the direction the of NPS' missile launcher
			for(int i = 1; i < range ; i++)
			{			
				xPoints[i] = xPoints[i-1] + (int)deltaX;
				yPoints[i] = yPoints[i-1] + (int)deltaY;
			}
			IIterator iterator = gw.getIterator();
			while(iterator.hasNext()) // find the PlayerShip object
			{
				Object obj = iterator.getNext();
				boolean inRange = false;
				if (obj instanceof PlayerShip)
				{
					PlayerShip ps = (PlayerShip) obj;
					int psX = (int)ps.getLocation().getX();
					int psY = (int)ps.getLocation().getY();
					int psSize = ps.getSize();
					// for the points 20 to (range-1) that were calculated
					for(int i = 20; i < range && !inRange; i++)
					{
						// check if any of them are in range of the PlayerShip
						if ((Math.abs(psX+psSize/2-xPoints[i])<= psSize/2 &&
								(Math.abs(psY+psSize/2-yPoints[i])<= psSize/2))||
								(Math.abs(psX-psSize/2-xPoints[i])<=psSize/2 &&
								Math.abs(psY-psSize/2-yPoints[i])<= psSize/2))
						{
							// launch NPS missile if any of the points are in range
							gw.launchNPSMissile(nps);
							// and get out of the loop
							inRange = true;
						}
					}
				}
			}
			
		}
		
		// calculate where the moveable object should move to
		double dist = this.getSpeed() * ((double)elapsedMillisecs/50);
		double angle = Math.toRadians(90 + this.getDirection());
		double deltaX = Math.cos(angle) * dist;
		double deltaY = Math.sin(angle) * dist;
		double x = this.getX() + deltaX;
		double y = this.getY() + deltaY;
		this.setX(x);
		this.setY(y);
	}
	
	// checks whether or not this moveable object has collided with a wall, and bounces it off if it does
	public void collidesWithWall(int height, int base) 
	{
		int x = (int) this.getLocation().getX();
		int y = (int) this.getLocation().getY();
		int worldHeight = this.getWorldHeight();
		int worldWidth = this.getWorldWidth();
		int currentDirection = this.getDirection();
		// Collides with south boundary
		if((y + height/2)>=worldHeight)
		{
			if(currentDirection < 360 && currentDirection > 270)
				this.setDirection(180 + (360-this.getDirection()));
			else if ((currentDirection >= 0 && currentDirection < 90))
				this.setDirection(180 - this.getDirection());
		}
		// Collides with west boundary
		if((x - base/2)<=0)
		{
			if(currentDirection < 90 && currentDirection > 0)
				this.setDirection(270 + (90 - this.getDirection()));
			else if ((currentDirection)>= 90 && currentDirection < 180)
				this.setDirection(270 - (this.getDirection() - 90));	
		}
		// Collides with north boundary
		if((y- height/2)<=0)
		{
			if(currentDirection < 180 && currentDirection > 90)
				this.setDirection(0 + (180 - this.getDirection()));
			else if ((currentDirection)>= 180 && currentDirection < 270)
			{
				if (currentDirection > 180)
					this.setDirection(360 - (this.getDirection() - 180));
				else 
					this.setDirection(0);
			}
		}
		
		// Collides with east boundary
		if((x + base/2)>= worldWidth)
		{
			if(currentDirection < 270 && currentDirection > 180)
				this.setDirection(90 + (270 - this.getDirection()));
			else if ((currentDirection)>= 270 && currentDirection < 360)
				this.setDirection(90 - (this.getDirection() - 270));
		}
	}
		
}