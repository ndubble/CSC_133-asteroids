package com.mycompany.a3;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point;
import com.codename1.ui.geom.Point2D;

/**
 * Author: Nicholas Dubble
 * File: PlayerMissileLauncher.java
 *
 * CSC-133
 * Doan Nguyen
 * Fall 2018
 */
public class PlayerMissileLauncher extends MoveableObject implements ISteerable {

	private final int LENGTH = 90;
	private final int TURNAMOUNT = 20;
	private Point2D missileSpawnLocation;
	
	
	public PlayerMissileLauncher(double x, double y,int speed) 
	{
		super((int)x, (int)y);
		this.setSize(LENGTH);
		this.setX(x);
		this.setY(y);
		this.setSpeed(speed);
		this.setDirection(0);
		this.setColor(ColorUtil.BLACK);
		missileSpawnLocation = new Point2D(this.getX() + (LENGTH* Math.cos(Math.toRadians(this.getDirection() + 90.0))),
				this.getY() + LENGTH* Math.sin(Math.toRadians(this.getDirection() + 90.0)));
	}

	public void setLocation(Point2D location)
	{
		super.setLocation(location);
		// update where missiles spawn
		missileSpawnLocation.setX((this.getX() + (LENGTH* Math.cos(Math.toRadians(this.getDirection() + 90.0)))));
		missileSpawnLocation.setY(this.getY() + LENGTH* Math.sin(Math.toRadians(this.getDirection() + 90.0)));
	}
	
	public Point2D getMissleSpawnLocation()
	{
		return missileSpawnLocation;
	}
	
	public String toString()
	{
		return "Player MissileLauncher:" + super.toString();
	}
	

	/* (non-Javadoc)
	 * @see com.mycompany.a3.IDrawable#draw(com.codename1.ui.Graphics, com.codename1.ui.geom.Point)
	 */
	@Override
	public void draw(Graphics g, Point pCmpRelPrnt) 
	{
		g.setColor(this.getColor());
		int x = (int) (LENGTH* Math.cos(Math.toRadians(this.getDirection() + 90.0)));
		int y =  (int) (LENGTH* Math.sin(Math.toRadians(this.getDirection() + 90.0)));
		g.drawLine(pCmpRelPrnt.getX() + (int) this.getLocation().getX(), pCmpRelPrnt.getY() + (int) this.getLocation().getY(),
				pCmpRelPrnt.getX() + (int) this.getLocation().getX() + x,pCmpRelPrnt.getY() + (int) this.getLocation().getY() + y);		
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
		missileSpawnLocation.setX(this.getX() + (LENGTH* Math.cos(Math.toRadians(this.getDirection() + 90.0))));
		missileSpawnLocation.setY(this.getY() + LENGTH* Math.sin(Math.toRadians(this.getDirection() + 90.0)));
		
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
		missileSpawnLocation.setX(this.getX() + (LENGTH* Math.cos(Math.toRadians(this.getDirection() + 90.0))));
		missileSpawnLocation.setY(this.getY() + LENGTH* Math.sin(Math.toRadians(this.getDirection() + 90.0)));
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
