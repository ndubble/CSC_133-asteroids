package com.mycompany.a3;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Button;

/**
 * Author: Nicholas Dubble
 * File: ControlPanelButton.java
 *
 * CSC-133
 * Doan Nguyen
 * Fall 2018
 */
public class ControlPanelButton extends Button 
{
	
	ControlPanelButton(String s)
	{
		super(s);
		super.getAllStyles().setBgTransparency(255);
		super.getUnselectedStyle().setBgColor(ColorUtil.rgb(0, 150, 150));
		super.getAllStyles().setFgColor(ColorUtil.rgb(255, 255, 255));
		
		super.getAllStyles().setPadding(TOP, 5);
		super.getAllStyles().setPadding(BOTTOM, 5);
		
		super.getDisabledStyle().setBgTransparency(255);
		super.getDisabledStyle().setBgColor(ColorUtil.GRAY);
		super.getDisabledStyle().setFgColor(ColorUtil.rgb(255,255,255));
		
		super.getSelectedStyle().setBgTransparency(255);
		super.getSelectedStyle().setBgColor(ColorUtil.rgb(200, 0, 200));
		super.getSelectedStyle().setFgColor(ColorUtil.rgb(255, 255, 255));
		
	}
}
