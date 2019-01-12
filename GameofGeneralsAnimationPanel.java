// Game of Generals [Demo]
// Created by: Angelica C. F. Manansala
// Created on: December 21, 2018
// Last Updated: --

import javax.swing.*;
import java.awt.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;

public class GameofGeneralsAnimationPanel extends JPanel{
	
	// PROPERTIES
	BufferedImage MainMenuScreen;
<<<<<<< HEAD
	BufferedImage gameBoard;
=======
	BufferedImage Player1Tile;
	BufferedImage Player2Tile;
	BufferedImage gameBackground;
>>>>>>> parent of 1c3a13a... Working on chat system and adding graphics
	
	boolean blnStart = false;
	boolean blnPlay = false;
// - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -	
	
	// METHODS
	public void paintComponent(Graphics g){
		g.drawImage(MainMenuScreen,0,0,1280,720,null);
		
		if(blnStart){
			g.setColor(Color.GRAY);
			g.fillRect(0,0,1280,720);
		}
		
		if(blnPlay){
			g.setColor(new Color(165, 178, 198));
			g.fillRect(0,0,1280,720);
		}
		
	}
	
	
// - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -	
	
	// CONSTRUCTOR
	public GameofGeneralsAnimationPanel(){
		super();
		try{
<<<<<<< HEAD
			MainMenuScreen = ImageIO.read(new File("GoGBackground.jpg"));
			gameBoard = ImageIO.read(new File("Board.png"));
			
=======
			MainMenuScreen = ImageIO.read(new File("GoGBackground1.jpg"));
			//Player1Tile = ImageIO.read(new File("Player1Tile.png"));
			//Player2Tile = ImageIO.read(new File("Player2Tile.png"));
>>>>>>> parent of 1c3a13a... Working on chat system and adding graphics
		}catch(IOException e){
			System.out.println("Unable to load image");
		
		}
	}	
}
