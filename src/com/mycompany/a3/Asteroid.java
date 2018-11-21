package com.mycompany.a3;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point;

/**
 * Author: Nicholas Dubble
 * File: Asteroid.java
 *
 * CSC-133
 * Doan Nguyen
 * Fall 2018
 */
public class Asteroid extends MoveableObject implements ISelectable
{
	private int size;
	private final int SIZEMIN = 55;
	private final int SIZEMAX = 80;
	private Point topLeft, topRight, bottomLeft, bottomRight;
	private boolean destroyedByPS;
	private boolean destroyedByNPS;
	private boolean isSelectedStatus;
	
	public Asteroid(int gameWorldHeight, int gameWorldWidth) 
	{
		super(gameWorldHeight,gameWorldWidth);
		System.out.println(this.getLocation());
		/* size = random value between 40 and 80*/
		size = R.nextInt(SIZEMAX-SIZEMIN+1) + SIZEMIN;
		this.setSize(size);
		this.setColor(ColorUtil.GRAY);
		topLeft = new Point (-size/2, size/2);
		topRight = new Point (size/2, size/2);
		bottomLeft = new Point (-(size/2),-(size)/2);
		bottomRight = new Point ((size/2),-(size/2));
		destroyedByPS = false;
		destroyedByNPS = false;
		isSelectedStatus = false;
	}
	
	public boolean getDestroyedByPS()
	{
		return destroyedByPS;
	}
	
	public void setDestroyedByPS()
	{
		destroyedByPS = true;
	}
	
	public boolean getDestroyedByNPS()
	{
		return destroyedByNPS;
	}
	
	public void setDestroyedByNPS()
	{
		destroyedByNPS = true;
	}
	
	public int getSize()
	{
		return size;
	}
	
	public String toString()
	{
		
		return "Asteroid: " + super.toString() + "size=" + size;
	}

	/* (non-Javadoc)
	 * @see com.mycompany.a3.IDrawable#draw(com.codename1.ui.Graphics, com.codename1.ui.geom.Point)
	 */
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
		
		g.drawLine(xPoints[0], yPoints[0], xPoints[1], yPoints[1]);	
		g.drawLine(xPoints[1], yPoints[1], xPoints[2], yPoints[2]);
		g.drawLine(xPoints[2], yPoints[2], xPoints[3], yPoints[3]);
		g.drawLine(xPoints[3], yPoints[3], xPoints[0], yPoints[0]);
		
	}

	/* (non-Javadoc)
	 * @see com.mycompany.a3.ICollider#collidesWith(com.mycompany.a3.ICollider)
	 */
	@Override
	public boolean collidesWith(ICollider otherObject) 
	{
		boolean result = false;
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
			
		return result;
	}

	/* (non-Javadoc)
	 * @see com.mycompany.a3.ICollider#handleCollision(com.mycompany.a3.ICollider)
	 */
	@Override
	public void handleCollision(ICollider otherObject) 
	{
		if(otherObject instanceof Missile)
		{
			Missile missObj = (Missile) otherObject;
			if (missObj.getIsPlayerMissile() == true)
				this.setDestroyedByPS();
			else
				this.setDestroyedByNPS();
		}
		if (!(otherObject instanceof SpaceStation))
		{
			this.setKillStatus();
			MoveableObject otherObj = (MoveableObject) otherObject;
			otherObj.setKillStatus();
		}
	}

	@Override
	public void collidesWithWall()
	{
		this.collidesWithWall(size, size);
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
}
