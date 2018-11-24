package com.mycompany.a3;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point;

/**
 * Author: Nicholas Dubble
 * File: NonPlayerShip.java
 *
 * CSC-133
 * Doan Nguyen
 * Fall 2018
 */
public class NonPlayerShip extends MoveableObject 
{
	protected final static int LARGE = 120;
	protected final static int SMALL = 90;
	private final static int MAXMISSILES = 3;
	private NonPlayerMissileLauncher missileLauncher;
	private int missileCount;
	private Point top, bottomLeft, bottomRight;
	private int size;
	private boolean destroyedByPS;
	private int reloadTimeMillisecs = 2000;
	private int timeElapsedSinceLastShot;
	private int timerRate;
	
	public NonPlayerShip(int gameWorldHeight, int gameWorldWidth, int refreshRate) 
	{
		super(gameWorldHeight, gameWorldWidth);
		if (NonPlayerShip.R.nextInt(2) == 0)
			size = NonPlayerShip.SMALL;
		else
			size = NonPlayerShip.LARGE;
		this.setSize(size);
		missileLauncher = new NonPlayerMissileLauncher(this.getLocation(), this.getSpeed(), this.getDirection(),gameWorldHeight,gameWorldWidth);
		missileCount = 2;
		this.setColor(ColorUtil.MAGENTA);
		top = new Point (0, this.getSize()/2);
		bottomLeft = new Point (-(this.getSize()/2),-(this.getSize()/2));
		bottomRight = new Point ((this.getSize()/2),-(this.getSize()/2));
		destroyedByPS = false;
		timeElapsedSinceLastShot = 0;
		timerRate = refreshRate;
	}
	
	public int getTimeElapsedSinceLastShot()
	{
		return timeElapsedSinceLastShot;
	}
	
	public void addRateToTimeElapsedSinceLastShot()
	{
		timeElapsedSinceLastShot+=timerRate;
	}
	
	public int getMissileCount()
	{
		return missileCount;
	}
	
	public int getReloadTimeMillisecs()
	{
		return reloadTimeMillisecs;
	}
	
	public boolean getDestroyedByPS()
	{
		return destroyedByPS;
	}
	
	public void setDestroyedByPS()
	{
		destroyedByPS = true;
	}
	
	public NonPlayerMissileLauncher getMissileLauncher()
	{
		return missileLauncher;
	}
	
	public void setMissileCount(int x)
	{
		missileCount = x;
	}
	
	public int getMaxMissiles()
	{
		return MAXMISSILES;
	}
	
	public String toString()
	{
		String NonPlayerShip = "NonPlayerShip: " + super.toString() + "size=" + getSize() + "\n";
		String NPSMissileLauncher = this.getMissileLauncher().toString();
		return NonPlayerShip + NPSMissileLauncher;
	}

	/* (non-Javadoc)
	 * @see com.mycompany.a3.IDrawable#draw(com.codename1.ui.Graphics, com.codename1.ui.geom.Point)
	 */
	@Override
	public void draw(Graphics g, Point pCmpRelPrnt) 
	{
		
		// draw a triangle
		g.setColor(this.getColor());
		int xPoints[] = new int[3];
		xPoints[0] = pCmpRelPrnt.getX() + (int)this.getLocation().getX() + top.getX();
		xPoints[1] = pCmpRelPrnt.getX() + (int)this.getLocation().getX() + bottomLeft.getX();
		xPoints[2] = pCmpRelPrnt.getX() + (int)this.getLocation().getX() + bottomRight.getX();
		int yPoints[] = new int[3];
		yPoints[0] = pCmpRelPrnt.getY() + (int)this.getLocation().getY() + top.getY();
		yPoints[1] = pCmpRelPrnt.getY() + (int)this.getLocation().getY() + bottomLeft.getY();
		yPoints[2] = pCmpRelPrnt.getY() + (int)this.getLocation().getY() + bottomRight.getY();
		
		g.drawLine(xPoints[0], yPoints[0], xPoints[1], yPoints[1]);	
		g.drawLine(xPoints[1], yPoints[1], xPoints[2], yPoints[2]);
		g.drawLine(xPoints[2], yPoints[2], xPoints[0], yPoints[0]);
		g.fillPolygon(xPoints, yPoints, 3);
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
			if (missObj.getIsPlayerMissile() == false)
				return false;
		}
		if (!(otherObject instanceof NonPlayerShip) && !(otherObject instanceof SpaceStation)) // assuming NonPlayerShips can't crash into each other
		{
			GameObject otherObj = (GameObject) otherObject;
			// find dist between centers ( use square, to avoid taking roots)
			int dx = (int)this.getX() - (int)otherObj.getX();
			int dy = (int)this.getY() - (int)otherObj.getY();
			int distBetweenCentersSqr = (dx*dx + dy*dy);
			
			//find square of sum of radii
			int thisRadius = this.getSize()/2;
			int otherRadius = otherObj.getSize()/2;
			int radiiSqr = (thisRadius*thisRadius + 2*thisRadius*otherRadius + otherRadius*otherRadius);
			if (distBetweenCentersSqr <= radiiSqr)
			{
				result = true;
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
		if (otherObject instanceof Missile)
		{
			Missile missObj = (Missile) otherObject;
			if (missObj.getIsPlayerMissile() == true)
			{
				this.setDestroyedByPS();
			}
		}
		this.setKillStatus();
		MoveableObject otherObj = (MoveableObject) otherObject;
		otherObj.setKillStatus();
	}

	/* (non-Javadoc)
	 * @see com.mycompany.a3.ICollider#collidesWithWall()
	 */
	@Override
	public void collidesWithWall() {
		this.collidesWithWall(size, size);
		
	}

	/* (non-Javadoc)
	 * @see com.mycompany.a3.ICollider#collidesWithWall(com.mycompany.a3.ICollider)
	 */
	/*@Override
	public void collidesWithWall() {
		// TODO Auto-generated method stub
	}*/
	
}
