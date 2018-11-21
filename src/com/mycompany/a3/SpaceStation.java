package com.mycompany.a3;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point;

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
	private final static int RADIUS = 55;
	
	public SpaceStation(int gameWorldHeight, int gameWorldWidth) 
	{
		super(gameWorldHeight, gameWorldWidth);
		blinkRate = R.nextInt(BLINKRATEMAX + 1);
		this.setLightOn(false);
		this.setSize(RADIUS*2);
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

	/* (non-Javadoc)
	 * @see com.mycompany.a3.IDrawable#draw(com.codename1.ui.Graphics, com.codename1.ui.geom.Point)
	 */
	@Override
	public void draw(Graphics g, Point pCmpRelPrnt) 
	{
		g.setColor(this.getColor());
		int x,y;
		x = pCmpRelPrnt.getX() + (int) this.getLocation().getX();
		y = pCmpRelPrnt.getY() + (int)this.getLocation().getY();
		if (lightOn)
			g.fillArc(x,y,2*RADIUS,2*RADIUS,0,360);
		else
			g.drawArc(x,y,2*RADIUS,2*RADIUS,0,360);
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
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see com.mycompany.a3.ICollider#collidesWith(com.mycompany.a3.ICollider)
	 */

}
