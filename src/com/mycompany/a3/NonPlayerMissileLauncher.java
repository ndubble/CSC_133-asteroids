package com.mycompany.a3;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point;
import com.codename1.ui.geom.Point2D;

/**
 * Author: Nicholas Dubble
 * File: NonPlayerMissileLauncher.java
 *
 * CSC-133
 * Doan Nguyen
 * Fall 2018
 */
public class NonPlayerMissileLauncher extends MoveableObject 
{
	private final int LENGTH = 40;
	
	public NonPlayerMissileLauncher(Point2D loc, int speed, int direction, int gameWorldHeight, int gameWorldWidth) 
	{
		super(gameWorldHeight,gameWorldWidth);
		this.setLocation(loc);
		this.setSpeed(speed);
		this.setDirection(direction);	
		this.setColor(ColorUtil.BLACK);
		this.setSize(LENGTH);
	}

	
	public String toString()
	{
		return "NonPlayerMissileLauncher: " + super.toString();
	}


	/* (non-Javadoc)
	 * @see com.mycompany.a3.IDrawable#draw(com.codename1.ui.Graphics, com.codename1.ui.geom.Point)
	 */
	@Override
	public void draw(Graphics g, Point pCmpRelPrnt) 
	{
		g.setColor(this.getColor());
		int x = (int) (LENGTH* Math.cos(Math.toRadians(this.getDirection() + 90.0)));
		int y = (int) (LENGTH* Math.sin(Math.toRadians(this.getDirection() + 90.0)));
		g.drawLine(pCmpRelPrnt.getX() + (int) this.getLocation().getX(), pCmpRelPrnt.getY() + (int) this.getLocation().getY(),
				pCmpRelPrnt.getX() + (int) this.getLocation().getX() + x,pCmpRelPrnt.getY() + (int) this.getLocation().getY() + y);	
	}


	/* (non-Javadoc)
	 * @see com.mycompany.a3.ICollider#collidesWith(com.mycompany.a3.ICollider)
	 */
	@Override
	public boolean collidesWith(ICollider otherObject) {
		// TODO Auto-generated method stub
		return false;
	}


	/* (non-Javadoc)
	 * @see com.mycompany.a3.ICollider#handleCollision(com.mycompany.a3.ICollider)
	 */
	@Override
	public void handleCollision(ICollider otherObject) {
		// TODO Auto-generated method stub
		
	}


	/* (non-Javadoc)
	 * @see com.mycompany.a3.ICollider#collidesWithWall()
	 */
	@Override
	public void collidesWithWall() {
		this.collidesWithWall(0, 0);
		
	}


	/* (non-Javadoc)
	 * @see com.mycompany.a3.ICollider#collidesWithWall(com.mycompany.a3.ICollider)
	 */
	/*@Override
	public void collidesWithWall() {
		// TODO Auto-generated method stub
	}*/
}
