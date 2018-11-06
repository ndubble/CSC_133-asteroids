package com.mycompany.a3;

/**
 * Author: Nicholas Dubble
 * File: IIterator.java
 *
 * CSC-133
 * Doan Nguyen
 * Fall 2018
 */
public interface IIterator {
	public boolean hasNext();
	public Object getNext();
	public Object elementAt(int i);
	public int size();	
}
