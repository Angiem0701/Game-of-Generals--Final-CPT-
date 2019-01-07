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
	BufferedImage Player1Tile;
	BufferedImage Player2Tile;
	
// - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -	
	
	// METHODS
	public void paintComponent(Graphics g){
		g.drawImage(MainMenuScreen,0,0,1280,720,null);
		g.drawImage(Player1Tile,0,0,64,36,null);
		g.drawImage(Player2Tile,100,100,64,36,null);
		
		if(bln)
		g.setColor(Color.WHITE);
		g.fillRect(0,0,1280,720);
	}
	
	
// - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -	
	
	// CONSTRUCTOR
	public GameofGeneralsAnimationPanel(){
		super();
		try{
			MainMenuScreen = ImageIO.read(new File("GoGBackground1.jpg"));
			//Player1Tile = ImageIO.read(new File("Player1Tile.png"));
			//Player2Tile = ImageIO.read(new File("Player2Tile.png"));
		}catch(IOException e){
			System.out.println("Unable to load image");
		
		}
	}	
}
