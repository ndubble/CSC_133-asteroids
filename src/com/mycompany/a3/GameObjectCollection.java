package com.mycompany.a3;

import java.util.Vector;

/**
 * Author: Nicholas Dubble
 * File: GameObjectCollection.java
 *
 * CSC-133
 * Doan Nguyen
 * Fall 2018
 */
public class GameObjectCollection implements ICollection 
{
	private Vector<GameObject> gameObjects;
	
	public GameObjectCollection()
	{
		gameObjects = new Vector<GameObject>();
	}
	
	public void add(Object newObject) 
	{
		gameObjects.addElement((GameObject) newObject);

	}

	public IIterator getIterator() 
	{
		return new GameObjectVectorIterator ();
	}
	
	private class GameObjectVectorIterator implements IIterator
	{
		private int currElementIndex;
		
		public GameObjectVectorIterator()
		{
			currElementIndex = -1;
		}
		
		public boolean hasNext()
		{
			if (gameObjects.size ( ) <= 0) 
				return false;
			if (currElementIndex == gameObjects.size() - 1 )
				return false;
			return true;
		}
		
		public Object getNext ( ) 
		{
			currElementIndex ++ ;
			return(gameObjects.elementAt(currElementIndex));
		}
		
		public Object elementAt(int i)
		{
			return gameObjects.elementAt(i);
		}
		
		public int size()
		{
			return gameObjects.size();
		}
		
	}

	public void remove(GameObject object) 
	{
		gameObjects.remove(object);	
	}
	
	public void remove(int index) 
	{
		gameObjects.remove(index);	
	}

}
