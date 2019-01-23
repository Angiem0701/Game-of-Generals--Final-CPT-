// Game of the Generals [Demo]
// Created by: Angelica C. F. Manansala, Ivan Lau, Caitlin Kwan
// Created on: January 8, 2018
// Last Updated: January 19, 2019

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class rank{

	//properties
	//rank
	
	/**
	 * This represents the rank of the piece
	 */
	 
	int intRank;
	
	/**
	 * This represents the x-coordinate of the piece 
	 */
	
	int intX;
	
	/**
	 * This represents the y-coordinate of the piece 
	 */
	
	int intY;
	
	/**
	 * This indicates whether the piece has been eliminated or not
	 */
	
	boolean blnAlive = true;
	
	/**
	 *  This represents the x-coordinate of the piece on the array, itself
	 */
		
	int intArrayX;
	
	/**
	 *  This represents the x-coordinate of the piece on the array, itself
	 */
	
	int intArrayY;
		
	/**
	 *  This represents the y-coordinate of the piece on the array, itself
	 */
	 	
	String strSide;
		
	/**
	 *  This indicates what side the piece is on (White or Black)
	 */
	
	//methods
	
	//constructor
	public rank(boolean blnAlive, int intRank, int intX, int intY, int intArrayX, int intArrayY, String strSide){
		this.blnAlive = blnAlive;
		this.intRank = intRank;
		this.intX = intX;
		this.intY = intY;
		this.intArrayX = intArrayX;
		this.intArrayY = intArrayY;
		this.strSide = strSide;
		
	}
}
