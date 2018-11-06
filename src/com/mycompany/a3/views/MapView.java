package com.mycompany.a3.views;

import java.util.Observable;
import java.util.Observer;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Container;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.Border;
import com.mycompany.a3.IGameWorld;
import com.mycompany.a3.IIterator;

/**
 * Author: Nicholas Dubble
 * File: MapView.java
 *
 * CSC-133
 * Doan Nguyen
 * Fall 2018
 */
public class MapView extends Container implements Observer 
{
	
	public MapView()
	{

		/* Make container for points view */
		Container mapContainer = new Container();
		mapContainer.setLayout(new FlowLayout());
		mapContainer.getAllStyles().setBgTransparency(255);
		mapContainer.getAllStyles().setBgColor(ColorUtil.LTGRAY);
		this.getAllStyles().setBorder(Border.createLineBorder(4,ColorUtil.BLACK));

		
		/* Add myContainer add to this component */
		this.add(mapContainer);
	}
	
	@Override
	public void update(Observable observable, Object arg) 
	{
		IGameWorld gw = (IGameWorld) arg;
		
		System.out.println("--------------------MAP----------------------------------------------");
		gw.printMap();
		System.out.println("---------------------------------------------------------------------");
		this.repaint();
	}
}
