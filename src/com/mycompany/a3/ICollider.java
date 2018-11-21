/**
 * 
 */
package com.mycompany.a3;

/**
 * Author: Nicholas Dubble
 * File: ICollider.java
 *
 * CSC-133
 * Doan Nguyen
 * Fall 2018
 */
public interface ICollider 
{
	public boolean collidesWith(ICollider otherObject); // detection algorithm
	public void handleCollision(ICollider otherObject); // response algorithm
	public void collidesWithWall();
}
