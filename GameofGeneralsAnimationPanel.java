// Game of Generals [Demo]
// Created by: Angelica C. F. Manansala, Ivan Lau, Caitlin Kwan
// Created on: December 21, 2018
// Last Updated: January 14, 2019

import javax.swing.*;
import java.awt.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;

public class GameofGeneralsAnimationPanel extends JPanel{
	
	// PROPERTIES
	BufferedImage MainMenuScreen;
	BufferedImage gameBoard;
	
	BufferedImage WH1Lieutenant;
	BufferedImage WH2Lieutenant;
	BufferedImage WHCaptain;
	BufferedImage WHColonel;
	BufferedImage WH5Star;
	BufferedImage WHFlag;
	BufferedImage WH4Star;
	BufferedImage WHLColonel;
	BufferedImage WHMajor;
	BufferedImage WH1Star;
	BufferedImage WHPrivate;
	BufferedImage WHSergeant;
	BufferedImage WHSpy;
	BufferedImage WH2Star;
	BufferedImage WH3Star;
	
	BufferedImage BL1Lieutenant;
	BufferedImage BL2Lieutenant;
	BufferedImage BLCaptain;
	BufferedImage BLColonel;
	BufferedImage BL5Star;
	BufferedImage BLFlag;
	BufferedImage BL4Star;
	BufferedImage BLLColonel;
	BufferedImage BLMajor;
	BufferedImage BL1Star;
	BufferedImage BLPrivate;
	BufferedImage BLSergeant;
	BufferedImage BLSpy;
	BufferedImage BL2Star;
	BufferedImage BL3Star;
	
	BufferedImage Black;
	BufferedImage White;
	
	boolean blnStart = false;
	boolean blnPlay = false;
	
	boolean blnClientView = false;
	boolean blnServerView = false;
// - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -	


	// PIECES
			// BLACK PIECES
		//rank BLFlag = new rank(true,0,0,0);
		//rank BLPrivate1 = new rank(true,1,0,0);
		//rank BLPrivate2 = new rank(true,1,0,0);
		//rank BLPrivate3 = new rank(true,1,0,0);
		//rank BLPrivate4 = new rank(true,1,0,0);
		//rank BLPrivate5 = new rank(true,1,0,0);
		//rank BLPrivate6 = new rank(true,1,0,0);
		//rank BLSpy1 = new rank(true,2,0,0);
		//rank BLSpy2 = new rank(true,2,0,0);
		//rank BLSergeant = new rank(true,3,0,0);
		//rank BLLColonel = new rank(true,3,0,0);
		//rank BL2Lieutenant = new rank(true,4,0,0);
		//rank BL1Lieutenant = new rank(true,5,0,0);
		//rank BLCaptain = new rank(true,6,0,0);
		//rank BLColonel = new rank(true,9,0,0);
		//rank BL1Star = new rank(true,10,0,0);
		//rank BL2Star = new rank(true,11,0,0);
		//rank BL3Star = new rank(true,12,0,0);
		//rank BL4Star = new rank(true,13,0,0);
		//rank BL5Star = new rank(true,14,0,0);
		//rank BLMajor = new rank(true,15,0,0);
		
		// WHITE PIECES
		//rank WHFlag = new rank(true,0,0,0);
		//rank WHPrivate1 = new rank(true,1,0,0);
		//rank WHPrivate2 = new rank(true,1,0,0);
		//rank WHPrivate3 = new rank(true,1,0,0);
		//rank WHPrivate4 = new rank(true,1,0,0);
		//rank WHPrivate5 = new rank(true,1,0,0);
		//rank WHPrivate6 = new rank(true,1,0,0);
		//rank WHSpy1 = new rank(true,2,0,0);
		//rank WHSpy2 = new rank(true,2,0,0);
		//rank WHSergeant = new rank(true,3,0,0);
		//rank WHLColonel = new rank(true,3,0,0);
		//rank WH2Lieutenant = new rank(true,4,0,0);
		rank WH1LieutenantOBJ = new rank(true,5,10,642);
		//rank WHCaptain = new rank(true,6,0,0);
		//rank WHColonel = new rank(true,9,0,0);
		//rank WH1Star = new rank(true,10,0,0);
		//rank WH2Star = new rank(true,11,0,0);
		//rank WH3Star = new rank(true,12,0,0);
		//rank WH4Star = new rank(true,13,0,0);
		//rank WH5Star = new rank(true,14,0,0);
		//rank WHMajor = new rank(true,15,0,0);
		
		// END PICES
	
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
			g.drawImage(gameBoard,10,80,630,560,null);
		}
		
		if(blnClientView){
			g.drawImage(WH1Lieutenant,WH1LieutenantOBJ.intX,WH1LieutenantOBJ.intY,65,20,null);
			g.drawImage(WH2Lieutenant,77,642,65,20,null);
			g.drawImage(WHCaptain,144,642,65,20,null);
			g.drawImage(WHColonel,211,642,65,20,null);
			g.drawImage(WH5Star,278,642,65,20,null);
			g.drawImage(WHFlag,345,642,65,20,null);
			g.drawImage(WH4Star,412,642,65,20,null);
			g.drawImage(WHLColonel,479,642,65,20,null);
			g.drawImage(WHMajor,546,642,65,20,null);
			
			g.drawImage(WH1Star,10,664,65,20,null);
			g.drawImage(WHPrivate,77,664,65,20,null);
			g.drawImage(WHPrivate,144,664,65,20,null);
			g.drawImage(WHPrivate,211,664,65,20,null);
			g.drawImage(WHPrivate,278,664,65,20,null);
			g.drawImage(WHPrivate,345,664,65,20,null);
			g.drawImage(WHPrivate,412,664,65,20,null);
			g.drawImage(WHSergeant,479,664,65,20,null);
			g.drawImage(WHSpy,546,664,65,20,null);
			
			g.drawImage(WHSpy,10,686,65,20,null);
			g.drawImage(WH2Star,77,686,65,20,null);
			g.drawImage(WH3Star,144,686,65,20,null);
			
			g.drawImage(Black,10,10,65,20,null);
			g.drawImage(Black,77,10,65,20,null);
			g.drawImage(Black,144,10,65,20,null);
			g.drawImage(Black,211,10,65,20,null);
			g.drawImage(Black,278,10,65,20,null);
			g.drawImage(Black,345,10,65,20,null);
			g.drawImage(Black,412,10,65,20,null);
			g.drawImage(Black,479,10,65,20,null);
			g.drawImage(Black,546,10,65,20,null);
			
			g.drawImage(Black,10,32,65,20,null);
			g.drawImage(Black,77,32,65,20,null);
			g.drawImage(Black,144,32,65,20,null);
			g.drawImage(Black,211,32,65,20,null);
			g.drawImage(Black,278,32,65,20,null);
			g.drawImage(Black,345,32,65,20,null);
			g.drawImage(Black,412,32,65,20,null);
			g.drawImage(Black,479,32,65,20,null);
			g.drawImage(Black,546,32,65,20,null);
			
			g.drawImage(Black,10,54,65,20,null);
			g.drawImage(Black,77,54,65,20,null);
			g.drawImage(Black,144,54,65,20,null);
			
		}else if(blnServerView){
			g.drawImage(BL1Lieutenant,10,642,65,20,null);
			g.drawImage(BL2Lieutenant,77,642,65,20,null);
			g.drawImage(BLCaptain,144,642,65,20,null);
			g.drawImage(BLColonel,211,642,65,20,null);
			g.drawImage(BL5Star,278,642,65,20,null);
			g.drawImage(BLFlag,345,642,65,20,null);
			g.drawImage(BL4Star,412,642,65,20,null);
			g.drawImage(BLLColonel,479,642,65,20,null);
			g.drawImage(BLMajor,546,642,65,20,null);
			
			g.drawImage(BL1Star,10,664,65,20,null);
			g.drawImage(BLPrivate,77,664,65,20,null);
			g.drawImage(BLPrivate,144,664,65,20,null);
			g.drawImage(BLPrivate,211,664,65,20,null);
			g.drawImage(BLPrivate,278,664,65,20,null);
			g.drawImage(BLPrivate,345,664,65,20,null);
			g.drawImage(BLPrivate,412,664,65,20,null);
			g.drawImage(BLSergeant,479,664,65,20,null);
			g.drawImage(BLSpy,546,664,65,20,null);
			
			g.drawImage(BLSpy,10,686,65,20,null);
			g.drawImage(BL2Star,77,686,65,20,null);
			g.drawImage(BL3Star,144,686,65,20,null);
			
			g.drawImage(White,10,10,65,20,null);
			g.drawImage(White,77,10,65,20,null);
			g.drawImage(White,144,10,65,20,null);
			g.drawImage(White,211,10,65,20,null);
			g.drawImage(White,278,10,65,20,null);
			g.drawImage(White,345,10,65,20,null);
			g.drawImage(White,412,10,65,20,null);
			g.drawImage(White,479,10,65,20,null);
			g.drawImage(White,546,10,65,20,null);
			
			g.drawImage(White,10,32,65,20,null);
			g.drawImage(White,77,32,65,20,null);
			g.drawImage(White,144,32,65,20,null);
			g.drawImage(White,211,32,65,20,null);
			g.drawImage(White,278,32,65,20,null);
			g.drawImage(White,345,32,65,20,null);
			g.drawImage(White,412,32,65,20,null);
			g.drawImage(White,479,32,65,20,null);
			g.drawImage(White,546,32,65,20,null);
			
			g.drawImage(White,10,54,65,20,null);
			g.drawImage(White,77,54,65,20,null);
			g.drawImage(White,144,54,65,20,null);
			
			
		}
		
		
		
		
		
	}
	
	
// - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -	
	
	// CONSTRUCTOR
	public GameofGeneralsAnimationPanel(){
		super();
		try{
			MainMenuScreen = ImageIO.read(new File("GoGBackground.png"));
			gameBoard = ImageIO.read(new File("Board.png"));
			
			WH1Lieutenant = ImageIO.read(new File("WH1Lieutenant.png"));
			WH2Lieutenant = ImageIO.read(new File("WH2Lieutenant.png"));
			WHCaptain = ImageIO.read(new File("WHCaptain.png"));
			WHColonel = ImageIO.read(new File("WHColonel.png"));
			WH5Star = ImageIO.read(new File("WH5Star.png"));
			WHFlag = ImageIO.read(new File("WHFlag.png"));
			WH4Star = ImageIO.read(new File("WH4Star.png"));
			WHLColonel = ImageIO.read(new File("WHColonel.png"));
			WHMajor = ImageIO.read(new File("WHMajor.png"));
			WH1Star = ImageIO.read(new File("WH1Star.png"));
			WHPrivate = ImageIO.read(new File("WHPrivate.png"));
			WHSergeant = ImageIO.read(new File("WHSergeant.png"));
			WHSpy = ImageIO.read(new File("WHSpy.png"));
			WH2Star = ImageIO.read(new File("WH2Star.png"));
			WH3Star = ImageIO.read(new File("WH3Star.png"));
			
			BL1Lieutenant = ImageIO.read(new File("BL1Lieutenant.png"));
			BL2Lieutenant = ImageIO.read(new File("BL2Lieutenant.png"));
			BLCaptain = ImageIO.read(new File("BLCaptain.png"));
			BLColonel = ImageIO.read(new File("BLColonel.png"));
			BL5Star = ImageIO.read(new File("BL5Star.png"));
			BLFlag = ImageIO.read(new File("BLFlag.png"));
			BL4Star = ImageIO.read(new File("BL4Star.png"));
			BLLColonel = ImageIO.read(new File("BLColonel.png"));
			BLMajor = ImageIO.read(new File("BLMajor.png"));
			BL1Star = ImageIO.read(new File("BL1Star.png"));
			BLPrivate = ImageIO.read(new File("BLPrivate.png"));
			BLSergeant = ImageIO.read(new File("BLSergeant.png"));
			BLSpy = ImageIO.read(new File("BLSpy.png"));
			BL2Star = ImageIO.read(new File("BL2Star.png"));
			BL3Star = ImageIO.read(new File("BL3Star.png"));
			
			Black = ImageIO.read(new File("Black.png"));
			White = ImageIO.read(new File("White.png"));
			
		}catch(IOException e){
			System.out.println("Unable to load image");
		
		}
	}	
}
