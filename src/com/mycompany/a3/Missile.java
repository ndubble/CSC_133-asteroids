package com.mycompany.a3;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point;
import com.codename1.ui.geom.Point2D;

/**
 * Author: Nicholas Dubble
 * File: Missile.java
 *
 * CSC-133
 * Doan Nguyen
 * Fall 2018
 */
public class Missile extends MoveableObject implements ISelectable
{
	private int fuelLevel;
	private final int MISSILESPEED = 7;
	private final int INITIALFUEL = 225; // 50hz * 4.5 seconds
	private Boolean isPlayerMissile;
	private final static int LENGTH = 30;
	private Point topLeft, topRight, bottomLeft, bottomRight;
	private boolean isSelectedStatus;
	
	public Missile(Point2D loc,int direction, int speedOfShip, Boolean playerMissile, int gameWorldHeight, int gameWorldWidth, int color)
	{
		super(gameWorldHeight, gameWorldWidth);
		this.setDirection(direction);
		this.setSpeed(speedOfShip + MISSILESPEED);
		this.setColor(color);
		this.setLocation(loc);
		this.setSize(LENGTH);
		fuelLevel = INITIALFUEL;
		if (playerMissile)
			isPlayerMissile = true;
		else
			isPlayerMissile = false;
		
		topLeft = new Point (-LENGTH/2, LENGTH/2);
		topRight = new Point (LENGTH/2, LENGTH/2);
		bottomLeft = new Point (-(LENGTH/2),-(LENGTH)/2);
		bottomRight = new Point ((LENGTH/2),-(LENGTH/2));
		isSelectedStatus = false;
	}
	
	public int getInitialFuel()
	{
		return INITIALFUEL;
	}
	
	public int getFuelLevel()
	{
		return fuelLevel;
	}
	
	public void setFuelLevel(int x)
	{
		fuelLevel = x;
	}
	
	public Boolean getIsPlayerMissile()
	{
		return isPlayerMissile;
	}
	
	public String toString()
	{
		return "Missile: " + super.toString() + "fuel level: " + fuelLevel;
	}

	@Override
	public void draw(Graphics g, Point pCmpRelPrnt) 
	{
		if (isSelected())
			g.setColor(ColorUtil.YELLOW);
		else
			g.setColor(this.getColor());
			
		int xPoints[] = new int[4];
		xPoints[0] = pCmpRelPrnt.getX() + (int)this.getLocation().getX() + topLeft.getX();
		xPoints[1] = pCmpRelPrnt.getX() + (int)this.getLocation().getX() + bottomLeft.getX();
		xPoints[2] = pCmpRelPrnt.getX() + (int)this.getLocation().getX() + bottomRight.getX();
		xPoints[3] = pCmpRelPrnt.getX() + (int)this.getLocation().getX() + topRight.getX();
		int yPoints[] = new int[4];
		yPoints[0] = pCmpRelPrnt.getY() + (int)this.getLocation().getY() + topLeft.getY();
		yPoints[1] = pCmpRelPrnt.getY() + (int)this.getLocation().getY() + bottomLeft.getY();
		yPoints[2] = pCmpRelPrnt.getY() + (int)this.getLocation().getY() + bottomRight.getY();
		yPoints[3] = pCmpRelPrnt.getY() + (int)this.getLocation().getY() + topRight.getY();
		
		// draw a square
		g.drawLine(xPoints[0], yPoints[0], xPoints[1], yPoints[1]);	
		g.drawLine(xPoints[1], yPoints[1], xPoints[2], yPoints[2]);
		g.drawLine(xPoints[2], yPoints[2], xPoints[3], yPoints[3]);
		g.drawLine(xPoints[3], yPoints[3], xPoints[0], yPoints[0]);
		g.fillPolygon(xPoints, yPoints, 4);
		
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
			if ((missObj.getIsPlayerMissile() == true && this.getIsPlayerMissile() == true) ||
				 missObj.getIsPlayerMissile() == false && this.getIsPlayerMissile() == false)
			{
				return false;
			}
		}
		if ((this.getIsPlayerMissile() == true && !(otherObject instanceof PlayerShip) && !(otherObject instanceof SpaceStation)) ||
			 (this.getIsPlayerMissile() == false && !(otherObject instanceof NonPlayerShip) && !(otherObject instanceof SpaceStation)))
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
		if (otherObject instanceof NonPlayerShip && this.getIsPlayerMissile() == true)
		{
			NonPlayerShip nps = (NonPlayerShip) otherObject;
			nps.setDestroyedByPS();
		}
		else if (otherObject instanceof Asteroid)
		{
			Asteroid ast = (Asteroid) otherObject;
			if (this.getIsPlayerMissile() == true)
				ast.setDestroyedByPS();
			else
				ast.setDestroyedByNPS();
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
		this.collidesWithWall(LENGTH, LENGTH);
		
	}

	/* (non-Javadoc)
	 * @see com.mycompany.a3.ISelectable#setSelected(boolean)
	 */
	@Override
	public void setSelected(boolean yesNo) 
	{
		isSelectedStatus = yesNo;
	}

	/* (non-Javadoc)
	 * @see com.mycompany.a3.ISelectable#isSelected()
	 */
	@Override
	public boolean isSelected() 
	{
		return isSelectedStatus;
	}

	/* (non-Javadoc)
	 * @see com.mycompany.a3.ISelectable#contains(com.codename1.ui.geom.Point, com.codename1.ui.geom.Point)
	 */
	@Override
	public boolean contains(Point pPtrRelPrnt, Point pCmpRelPrnt) 
	{
		boolean containsPtr = false;
		int ptrX = pPtrRelPrnt.getX();
		int ptrY = pPtrRelPrnt.getY();
		int topLeftX = pCmpRelPrnt.getX() + (int)this.getLocation().getX() + topLeft.getX();
		int topRightX = pCmpRelPrnt.getX() + (int)this.getLocation().getX() + topRight.getX();
		int topLeftY = pCmpRelPrnt.getY() + (int)this.getLocation().getY() + topLeft.getY();
		int bottomLeftY = pCmpRelPrnt.getY() + (int)this.getLocation().getY() + bottomLeft.getY();
		if (ptrX>= topLeftX && ptrX<= topRightX &&
				ptrY<= topLeftY && ptrY>= bottomLeftY)
		{
			containsPtr = true;
			//System.out.println("inside!");
		}
		
		return containsPtr;
	}

	/* (non-Javadoc)
	 * @see com.mycompany.a3.ICollider#collidesWithWall(com.mycompany.a3.ICollider)
	 */
	/*@Override
	public void collidesWithWall() {
		// TODO Auto-generated method stub
	}*/
}
