package com.mycompany.a3;

/**
 * Author: Nicholas Dubble
 * File: ICollection.java
 *
 * CSC-133
 * Doan Nguyen
 * Fall 2018
 */
public interface ICollection {
	public void add(Object newObject);
	public IIterator getIterator();
	public void remove(GameObject object);

}
