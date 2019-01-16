// Game of the Generals [Demo]
// Created by: Angelica C. F. Manansala, Ivan Lau, Caitlin Kwan
// Created on: December 21, 2018
// Last Updated: January 15, 2019

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
		rank BLFlagOBJ = new rank(true,0,345,642);
		rank BLPrivate1OBJ = new rank(true,1,77,664);
		rank BLPrivate2OBJ = new rank(true,1,144,664);
		rank BLPrivate3OBJ = new rank(true,1,211,664);
		rank BLPrivate4OBJ = new rank(true,1,278,664);
		rank BLPrivate5OBJ = new rank(true,1,345,664);
		rank BLPrivate6OBJ = new rank(true,1,412,664);
		rank BLSpy1OBJ = new rank(true,2,10,686);
		rank BLSpy2OBJ = new rank(true,2,546,664);
		rank BLSergeantOBJ = new rank(true,3,479,664);
		rank BLLColonelOBJ = new rank(true,3,479,642);
		rank BL2LieutenantOBJ = new rank(true,4,77,642);
		rank BL1LieutenantOBJ = new rank(true,5,10,642);
		rank BLCaptainOBJ = new rank(true,6,144,642);
		rank BLColonelOBJ = new rank(true,9,211,642);
		rank BL1StarOBJ = new rank(true,10,10,664);
		rank BL2StarOBJ = new rank(true,11,77,686);
		rank BL3StarOBJ = new rank(true,12,144,686);
		rank BL4StarOBJ = new rank(true,13,412,642);
		rank BL5StarOBJ = new rank(true,14,278,642);
		rank BLMajorOBJ = new rank(true,15,546,642);
		
		// WHITE PIECES
		rank WHFlagOBJ = new rank(true,0,345,642);
		rank WHPrivate1OBJ = new rank(true,1,77,664);
		rank WHPrivate2OBJ = new rank(true,1,144,664);
		rank WHPrivate3OBJ = new rank(true,1,211,664);
		rank WHPrivate4OBJ = new rank(true,1,278,664);
		rank WHPrivate5OBJ = new rank(true,1,345,664);
		rank WHPrivate6OBJ = new rank(true,1,412,664);
		rank WHSpy1OBJ = new rank(true,2,10,686);
		rank WHSpy2OBJ = new rank(true,2,546,664);
		rank WHSergeantOBJ = new rank(true,3,479,664);
		rank WHLColonelOBJ = new rank(true,3,479,642);
		rank WH2LieutenantOBJ = new rank(true,4,77,642);
		rank WH1LieutenantOBJ = new rank(true,5,10,642);
		rank WHCaptainOBJ = new rank(true,6,144,642);
		rank WHColonelOBJ = new rank(true,9,211,642);
		rank WH1StarOBJ = new rank(true,10,10,664);
		rank WH2StarOBJ = new rank(true,11,77,686);
		rank WH3StarOBJ = new rank(true,12,144,686);
		rank WH4StarOBJ = new rank(true,13,412,642);
		rank WH5StarOBJ = new rank(true,14,278,642);
		rank WHMajorOBJ = new rank(true,15,546,642);
		
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
			g.drawImage(WH2Lieutenant,WH2LieutenantOBJ.intX,WH2LieutenantOBJ.intY,65,20,null);
			g.drawImage(WHCaptain,WHCaptainOBJ.intX,WHCaptainOBJ.intY,65,20,null);
			g.drawImage(WHColonel,WHColonelOBJ.intX,WHColonelOBJ.intY,65,20,null);
			g.drawImage(WH5Star,WH5StarOBJ.intX,WH5StarOBJ.intY,65,20,null);
			g.drawImage(WHFlag,WHFlagOBJ.intX,WHFlagOBJ.intY,65,20,null);
			g.drawImage(WH4Star,WH4StarOBJ.intX,WH4StarOBJ.intY,65,20,null);
			g.drawImage(WHLColonel,WHLColonelOBJ.intX,WHLColonelOBJ.intY,65,20,null);
			g.drawImage(WHMajor,WHMajorOBJ.intX,WHMajorOBJ.intY,65,20,null);
			
			g.drawImage(WH1Star,WH1StarOBJ.intX,WH1StarOBJ.intY,65,20,null);
			g.drawImage(WHPrivate,WHPrivate1OBJ.intX,WHPrivate1OBJ.intY,65,20,null); //private 1
			g.drawImage(WHPrivate,WHPrivate2OBJ.intX,WHPrivate2OBJ.intY,65,20,null); //private 2
			g.drawImage(WHPrivate,WHPrivate3OBJ.intX,WHPrivate3OBJ.intY,65,20,null); //private 3
			g.drawImage(WHPrivate,WHPrivate4OBJ.intX,WHPrivate4OBJ.intY,65,20,null); //private 4
			g.drawImage(WHPrivate,WHPrivate5OBJ.intX,WHPrivate5OBJ.intY,65,20,null); //private 5
			g.drawImage(WHPrivate,WHPrivate6OBJ.intX,WHPrivate6OBJ.intY,65,20,null); //private 6
			g.drawImage(WHSergeant,WHSergeantOBJ.intX,WHSergeantOBJ.intY,65,20,null);
			g.drawImage(WHSpy,WHSpy2OBJ.intX,WHSpy2OBJ.intY,65,20,null); //spy 2
			
			g.drawImage(WHSpy,WHSpy1OBJ.intX,WHSpy1OBJ.intY,65,20,null); //spy 1
			g.drawImage(WH2Star,WH2StarOBJ.intX,WH2StarOBJ.intY,65,20,null);
			g.drawImage(WH3Star,WH3StarOBJ.intX,WH3StarOBJ.intY,65,20,null);
			
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
			g.drawImage(BL1Lieutenant,BL1LieutenantOBJ.intX,BL1LieutenantOBJ.intY,65,20,null);
			g.drawImage(BL2Lieutenant,BL2LieutenantOBJ.intX,BL2LieutenantOBJ.intY,65,20,null);
			g.drawImage(BLCaptain,BLCaptainOBJ.intX,BLCaptainOBJ.intY,65,20,null);
			g.drawImage(BLColonel,BLColonelOBJ.intX,BLColonelOBJ.intY,65,20,null);
			g.drawImage(BL5Star,BL5StarOBJ.intX,BL5StarOBJ.intY,65,20,null);
			g.drawImage(BLFlag,BLFlagOBJ.intX,BLFlagOBJ.intY,65,20,null);
			g.drawImage(BL4Star,BL4StarOBJ.intX,BL4StarOBJ.intY,65,20,null);
			g.drawImage(BLLColonel,BLLColonelOBJ.intX,BLLColonelOBJ.intY,65,20,null);
			g.drawImage(BLMajor,BLMajorOBJ.intX,BLMajorOBJ.intY,65,20,null);
			
			g.drawImage(BL1Star,BL1StarOBJ.intX,BL1StarOBJ.intY,65,20,null);
			g.drawImage(BLPrivate,BLPrivate1OBJ.intX,BLPrivate1OBJ.intY,65,20,null); //private 1
			g.drawImage(BLPrivate,BLPrivate2OBJ.intX,BLPrivate2OBJ.intY,65,20,null); //private 2
			g.drawImage(BLPrivate,BLPrivate3OBJ.intX,BLPrivate3OBJ.intY,65,20,null); //private 3
			g.drawImage(BLPrivate,BLPrivate4OBJ.intX,BLPrivate4OBJ.intY,65,20,null); //private 4
			g.drawImage(BLPrivate,BLPrivate5OBJ.intX,BLPrivate5OBJ.intY,65,20,null); //private 5
			g.drawImage(BLPrivate,BLPrivate6OBJ.intX,BLPrivate6OBJ.intY,65,20,null); //private 6
			g.drawImage(BLSergeant,BLSergeantOBJ.intX,BLSergeantOBJ.intY,65,20,null);
			g.drawImage(BLSpy,BLSpy2OBJ.intX,BLSpy2OBJ.intY,65,20,null); //spy 2
			
			g.drawImage(BLSpy,BLSpy1OBJ.intX,BLSpy1OBJ.intY,65,20,null); //spy 1
			g.drawImage(BL2Star,BL2StarOBJ.intX,BL2StarOBJ.intY,65,20,null);
			g.drawImage(BL3Star,BL3StarOBJ.intX,BL3StarOBJ.intY,65,20,null);
			
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
