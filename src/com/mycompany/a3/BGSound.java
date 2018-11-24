/**
 * 
 */
package com.mycompany.a3;

import java.io.InputStream;

import com.codename1.media.Media;
import com.codename1.media.MediaManager;
import com.codename1.ui.Display;

/**
 * Author: Nicholas Dubble
 * File: BGSound.java
 *
 * CSC-133
 * Doan Nguyen
 * Fall 2018
 */

// responsible for encapsulating background music/sound that is played during the game
public class BGSound implements Runnable 
{
	private Media m;
	
	public BGSound(String fileName)
	{
		try
		{
			InputStream is = Display.getInstance().getResourceAsStream(getClass(), "/"+fileName);
			
			//attach as runnable to run when media has finished playing
			//as the last parameter
			m = MediaManager.createMedia(is, "audio/mp3", this);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void pause() {m.pause();} // pause playing the sound
	public void play() {m.play();} // continue playing from where we have left off
	
	// entered when media has finished playing

	@Override
	public void run() 
	{
		// start playing from timer zero (beginning of the sound file)
		m.setTime(0);
		m.play();
	}

}
