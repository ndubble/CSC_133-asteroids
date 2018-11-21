package com.mycompany.a3.views;

import java.util.Observable;
import java.util.Observer;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Container;
import com.codename1.ui.Graphics;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.geom.Point;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.Border;
import com.mycompany.a3.IGameWorld;
import com.mycompany.a3.IIterator;
import com.mycompany.a3.ISelectable;
import com.mycompany.a3.NonPlayerShip;
import com.mycompany.a3.PlayerShip;
import com.mycompany.a3.GameObject;

/**
 * Author: Nicholas Dubble
 * File: MapView.java
 *
 * CSC-133
 * Doan Nguyen
 * Fall 2018
 */
public class MapView extends Container implements Observer, ActionListener
{
	private IGameWorld gw;
	Point pCmpRelPrnt;
	
	public MapView()
	{

		/* Make container for points view */
		Container mapContainer = new Container();
		mapContainer.setLayout(new FlowLayout());
		mapContainer.getAllStyles().setBgTransparency(255);
		mapContainer.getAllStyles().setBgColor(ColorUtil.LTGRAY);
		this.getAllStyles().setBorder(Border.createLineBorder(4,ColorUtil.BLACK));
		this.addPointerPressedListener(this);
		
		/* Add myContainer add to this component */
		this.add(mapContainer);
	}
	
	@Override
	public void update(Observable observable, Object arg) 
	{
		gw = (IGameWorld) arg;
		
		/*System.out.println("--------------------MAP----------------------------------------------");
		gw.printMap();
		System.out.println("---------------------------------------------------------------------");*/
		this.repaint();
	}

	@Override
	public void paint(Graphics g)
	{
		super.paint(g);
		
		pCmpRelPrnt = new Point (this.getX(), getY());
		
		IIterator iterator = gw.getIterator();
		
		for (int i = 0; i < iterator.size(); i++)
		{
			GameObject x = (GameObject) iterator.elementAt(i);
			x.draw(g, pCmpRelPrnt);
			if( x instanceof PlayerShip) // need to check if this is a ship; need to draw its missile launcher, which isn't in the collection
			{
				((PlayerShip) x).getMissileLauncher().draw(g, pCmpRelPrnt);
			}
			else if (x instanceof NonPlayerShip)
			{
				((NonPlayerShip) x).getMissileLauncher().draw(g, pCmpRelPrnt);
			}
		}	
	}
	
	public void actionPerformed(ActionEvent evt)
	{
		int x = evt.getX() - getParent().getAbsoluteX();
		int y = evt.getY() - getParent().getAbsoluteY();
		Point pPtrRelPtr = new Point(x,y);
		
		if (gw.getGamePlayModeStatus() == false) // if game is paused
		{
			// (1) check if any Asteroids or missiles have been selected
			System.out.println("Clicked from west region --> x and y: " + x + " " + y);
			IIterator iterator = gw.getIterator();
			while(iterator.hasNext())
			{
				Object obj = iterator.getNext();
				if (obj instanceof ISelectable)
				{
					ISelectable selectableObj = (ISelectable) obj;
					if(selectableObj.contains(pPtrRelPtr,pCmpRelPrnt))
						selectableObj.setSelected(true);
					else
						selectableObj.setSelected(false);	
				}
			}
			//		(1.1) if any are selected, mark that they have been selected
			// (2) if any were selected, draw them with extra stuff
			// (3) redraw objects normally if they were unselected
		}
		
		this.repaint();
		
	}
}
