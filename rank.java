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
	public void moveForward(){
		if(strSide.equals("Black")){
			if(strBBoard[intArrayX][intArrayY + 1] == null){
				this.intY = this.intY - 70;
				this.intArrayY = this.intArrayY - 1;
				strBBoard[intArrayX][intArrayY] = rank;
			}else{
				if(strBBoard[intArrayX][intArrayY + 1].intRank == intRank){
					blnAlive = false;
					strBBoard[intArrayX][intArrayY + 1].blnAlive = false;
					strBBoard[intArrayX][intArrayY] = null;
					strBBoard[intArrayX][intArrayY + 1] = null;
				}else if(strBBoard[intArrayX][intArrayY + 1].intRank == 2 && intRank == 1){
					strBBoard[intArrayX][intArrayY + 1].blnAlive = false; //White Wins
				}else if(strBBoard[intArrayX][intArrayY + 1].intRank == 1 && intRank == 2){
					WHFlagOBJ.blnAlive = false; //Black wins
				}else if(strBBoard[intArrayX][intArrayY + 1].intRank < intRank){
					strBBoard[intArrayX][intArrayY + 1].blnAlive = false;
					this.intY = this.intY - 70;
					this.intArrayY = this.intArrayY - 1;
					strBBoard[intArrayX][intArrayY] = rank;
				}else if(strBBoard[intArrayX][intArrayY + 1].intRank > intRank){
					blnAlive = false;
					strBBoard[intArrayX][intArrayY] = null;
				}
			}
			
			//ssm.sendText();
			
		}else{ // if from White side
			if(strWBoard[intArrayX][intArrayY + 1] == null){
				this.intY = this.intY - 70;
				this.intArrayY = this.intArrayY - 1;
				strWBoard[intArrayX][intArrayY] = rank;
			}else{
				if(strWBoard[intArrayX][intArrayY + 1].intRank == intRank){
					blnAlive = false;
					strWBoard[intArrayX][intArrayY + 1].blnAlive = false;
					strWBoard[intArrayX][intArrayY] = null;
					strWBoard[intArrayX][intArrayY + 1] = null;
				}else if(strWBoard[intArrayX][intArrayY + 1].intRank == 2 && intRank == 1){
					strWBoard[intArrayX][intArrayY + 1].blnAlive = false; //Black Wins
				}else if(strWBoard[intArrayX][intArrayY + 1].intRank == 1 && intRank == 2){
					BLFlagOBJ.blnAlive = false; //White wins
				}else if(strWBoard[intArrayX][intArrayY + 1].intRank < intRank){
					strWBoard[intArrayX][intArrayY + 1].blnAlive = false;
					this.intY = this.intY - 70;
					this.intArrayY = this.intArrayY - 1;
					strWBoard[intArrayX][intArrayY] = rank;
				}else if(strWBoard[intArrayX][intArrayY + 1].intRank > intRank){
					blnAlive = false;
					strWBoard[intArrayX][intArrayY] = null;
				}
			}
		}
	}
	public void moveBackward(){
		this.intY = this.intY + 70;
		this.intArrayY = this.intArrayY + 1;
	}
	public void moveLeft(){
		this.intX = this.intX - 70;
		this.intArrayX = this.intArrayX - 1;
	}
	public void moveRight(){
		this.intX = this.intX + 70;
		this.intArrayX = this.intArrayX + 1;
	}
	public int attack(){
		//if(intRank > intEnemyRank){
			//method enemy die
		//}
		//else if(intRank < intEnemyRank){
			//method die
		//}
		//else if(intRank == 2 && intRank < intEnemyRank){
			//method kill
		//}
		//else if(intRank == 2 && intEnemyRank == 1){
			//method die
		//}
		//else if(intEnemyRank == 0){
		//	System.out.println("Congratulations! You Won!!!"); // will add the Players later
		//	blnGameEnd = true;
		//}
		return 0;
	}
	public void die(){
		blnAlive = false;
		//set array as null, i think
	}
	
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
