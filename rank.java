//made by Ivan on Jan 8, 2019
//updated on Jan 9, 2019
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
	int intEnemyRank;
	
	//methods
	public int moveforwards(){
		intY = intY - 1;
		return intY;
	}
	public int movebackwards(){
		intY = intY + 1;
		return intY;
	}
	public int moveleft(){
		intX = intX - 1;
		return intX;
	}
	public int moveright(){
		intX = intX + 1;
		return intX;
	}
	public int attack(){
		if(intRank > intEnemyRank){
			//method enemy die
		}
		else if(intRank < intEnemyRank){
			//method die
		}
		else if(intRank == 2 && intRank < intEnemyRank){
			//method kill
		}
		else if(intRank == 2 && intEnemyRank == 1){
			//method die
		}
		else if(intEnemyRank == 0){
			System.out.println("Congratulations! You Won!!!"); // will add the Players later
			blnGameEnd = true;
		}
		return 0;
	}
	public void die(){
		blnAlive = false;
		//set array as null, i think
	}
	public void placepiece(){
	}
	//constructor
	public rank(boolean blnAlive, int intRank, int intX, int intY){
		this.blnAlive = blnAlive;
		this.intRank = intRank;
		this.intX = intX;
		this.intY = intY;
	}
}
