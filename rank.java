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
	int intRank;
	int intX;
	int intY;
	boolean blnAlive = true;
	boolean blnGameEnd = false;
	int intArrayX;
	int intArrayY;
	String strSide;
	
	
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
