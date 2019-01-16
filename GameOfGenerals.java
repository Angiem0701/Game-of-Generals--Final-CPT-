// Game of the Generals [Demo]
// Created by: Angelica C. F. Manansala, Ivan Lau, Caitlin Kwan
// Created on: December 21, 2018
// Last Updated: January 15, 2019

// OTHER NOTES
// - figure a way to have the program automatically find the ip address - no need for manual input

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GameOfGenerals implements ActionListener, KeyListener, MouseListener, MouseMotionListener{

// - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -	
	
	// PROPERTIES
	JFrame theframe;
	GameofGeneralsAnimationPanel thepanel;
	Timer thetimer;
	
	JButton StartGameButton = new JButton();
	JButton HelpButton = new JButton();
	JButton CreateServerButton = new JButton();
	JButton JoinServerButton = new JButton();
	JButton Next1Button = new JButton();
	JButton PreviousButton = new JButton();
	JButton MainMenuButton = new JButton();
		
	JLabel ObjectiveLabel = new JLabel();
	
	JTextArea HowToPlay = new JTextArea();
	JScrollPane HTPScroll = new JScrollPane();
	
	JLabel addAddress = new JLabel();
	JTextArea addFriend = new JTextArea();
	JButton enterButton = new JButton();
	
	JTextArea chatBoxReceive = new JTextArea();
	JScrollPane chatBoxScroll = new JScrollPane();
	JTextField chatBoxSend = new JTextField();
	
	JButton sReadyButton = new JButton();
	
	SuperSocketMaster ssm;
	
	
	// Variables
	int intPageCount = 0;
	
	String strObjectiveTitle = "*** THE OBJECTIVE OF THE GAME ***";
	String strObjective = "The OBJECTIVE of the game is to ELIMINATE or CAPTURE the FLAG of your opponent. You may also win by successfully maneuvering your own FLAG to the opposite end of the board.";
	String strObjective2 = "The player's set of pieces or soldiers with the corresponding ranks and functions consists of the following 21 pieces:";
	String strPieces = "5-STAR GENERAL: Eliminates any lower ranking officer, private, and flag"+"\n"+"4-STAR GENERAL: Eliminates any lower ranking officer, private, and flag"+"\n"+"3-STAR GENERAL: Eliminates any lower ranking officer, private, and flag"+"\n"+"2-STAR GENERAL: Eliminates any lower ranking officer, private, and flag"+"\n"+"1-STAR GENERAL: Eliminates any lower ranking officer, private, and flag"+"\n"+"COLONEL: Eliminates any lower ranking officer, private, and flag"+"\n"+"LT. COLONEL: Eliminates any lower ranking officer, private, and flag"+"\n"+"MAJOR: Eliminates any lower ranking officer, private, and flag"+"\n"+"CAPTAIN: Eliminates any lower ranking officer, private, and flag"+"\n"+"1st LIEUTENANT: Eliminates any lower ranking officer, private, and flag"+"\n"+"2nd LIEUTENANT: Eliminates any lower ranking officer, private, and flag"+"\n"+"SERGEANT: private and flag"+"\n"+"SPY: Eliminates all officers (from the rank of Sergeant up to the five (5) Star General & the flag."+"\n"+"PRIVATE: Eliminates the spy and the flag"+"\n"+"FLAG: can be eliminated by any piece including the opposing flag; a flag eliminates the opposing flag when it takes aggressive action by moving into the same square occupied by the other flag";
	String strPiecesNote = "NOTE: If both soldiers are of equal ranks, BOTH are eliminated";

	String strPreparingBattleTitle = "*** PREPARING FOR BATTLE: ***";
	String strPreparingBattle = "Arrange your pieces on the first 3 rows on your end of the board. There is no predetermined place like chess does. Instead, you're free to arrange the pieces to your liking in accordance to your strategy and style.";
	String strPreparingBattleNote = "NOTE: As you arrange your pieces on the first 3 rows, you will find 6 vacant squares. This is to allow for maneuvering and freedom of movement when play begins.";
	String strMovement = "*** MOVEMENT: ***"+"\n"+"\n"+"1. Any player makes the first move - server client goes first here (lol sorry, not sorry)"+"\n"+"2. A player is allowed to move only one piece at a time."+"\n"+"3. A move consists of placing a piece to an adjacent square, either forward, backward or sideward.";
	String strChallenging = "*** CHALLENGING: ***"+"\n"+"\n"+"As the game progresses, challenges are made resulting in the elimination of soldiers. A 'challenge' is made when the soldier moves into the same square occupied by an opposing soldier. When a challenge is made, the following rules of elimination apply:";
	String strChallengingConditions = "- A higher ranked soldier eliminates a lower ranked soldier"+"\n"+"- If both soldiers are of equal ranks, they are both eliminated"+"\n"+"- A spy eliminates any officer starting from the Sergeant and above"+"\n"+"- The Flag can be eliminated or captured by any piece including the opponent's Flag"+"\n"+"- Only a Private can eliminate the Spy"+"\n"+"- The Flag that moves into the same square occupied by the other Flag wins the game";
	String strChallenging2 = "For maximum suspsense, this program includes its own arbiter (or third party) to check the challenge between the two players. However, neither ranks of any piece will be revealed during these challenges. The player will receive the outranked piece and gives it back to the player who has lost it.";
	
	String strGameEndTitle = "*** HOW THE GAME ENDS: ***";
	String strGameEnd = "1. The game ends when:"+"\n"+"- the Flag is eliminated or captured"+"\n"+"- a Flag reaches the opposite end of the board"+"\n"+"- a player resigns"+"\n"+"- both players agree on a drawn position";
	String strGameEnd2 = "2. A Flag reaching the opposite end of the board may still be eliminated by any opposing piece occupying a square adjacent to the one reached by the Flag. In order to win, the Flag should at least be two squares or two ahead of any opposing piece.";
	
	boolean blnSettingUp;
	
	String strWBoard[][];
	String strBBoard[][];
	
// - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -		
	
	// METHODS - ACTIONLISTENER (Action Performed)
	public void actionPerformed(ActionEvent evt){		
		if(evt.getSource() == thetimer){
			thepanel.repaint();
			
		}else if(evt.getSource() == StartGameButton){
			StartGameButton.setVisible(false);
			HelpButton.setVisible(false);
			CreateServerButton.setVisible(true);
			JoinServerButton.setVisible(true);
			
		}else if(evt.getSource() == CreateServerButton){
			thepanel.blnStart = true;
			ssm = new SuperSocketMaster(2468, this);
			System.out.println("My server IP is: "+ssm.getMyAddress());
			addAddress.setVisible(true);
			String strAddAddress = ("This is your IP Address: " + ssm.getMyAddress());
			addAddress.setText(strAddAddress);
			ssm.connect();
			System.out.println("Server created");
			sReadyButton.setVisible(true);
			
			CreateServerButton.setVisible(false);
			JoinServerButton.setVisible(false);
			
		}else if(evt.getSource() == JoinServerButton){
			thepanel.blnStart = true;
			String strAddAddress = ("Enter your friend's IP Address.");
			
			addAddress.setText(strAddAddress);
			addAddress.setVisible(true);
			addFriend.setVisible(true);
			enterButton.setVisible(true);
						
			CreateServerButton.setVisible(false);
			JoinServerButton.setVisible(false);
			
		}else if(evt.getSource() == HelpButton){
			intPageCount = intPageCount + 1;
			StartGameButton.setVisible(false);
			HelpButton.setVisible(false);
			ObjectiveLabel.setVisible(true);
			HowToPlay.setVisible(true);
			HTPScroll.setVisible(true);
			Next1Button.setVisible(true);
			MainMenuButton.setVisible(true);
			
		}else if(evt.getSource() == MainMenuButton){
			intPageCount = 0;
			StartGameButton.setVisible(true);
			HelpButton.setVisible(true);
			ObjectiveLabel.setVisible(false);
			HowToPlay.setVisible(false);
			HTPScroll.setVisible(false);
			Next1Button.setVisible(false);
			MainMenuButton.setVisible(false);
			PreviousButton.setVisible(false);
						
		}else if(evt.getSource() == Next1Button){
			intPageCount = intPageCount + 1;
			HowToPlay.setText(null);
			if(intPageCount == 1){
				PreviousButton.setVisible(false);
				HowToPlay.setText(strObjectiveTitle+"\n"+"\n"+strObjective+"\n"+"\n"+strObjective2+"\n"+"\n"+strPieces+"\n"+"\n"+strPiecesNote);
			}else if(intPageCount == 2){
				PreviousButton.setVisible(true);
				Next1Button.setVisible(true);
				HowToPlay.setText(strPreparingBattleTitle+"\n"+"\n"+strPreparingBattle+"\n"+"\n"+strPreparingBattleNote+"\n"+"\n"+strMovement+"\n"+"\n"+strChallenging+"\n"+"\n"+strChallengingConditions+"\n"+"\n"+strChallenging2);
			}else if(intPageCount == 3){
				HowToPlay.setText(strGameEndTitle+"\n"+"\n"+strGameEnd+"\n"+"\n"+strGameEnd2);
				Next1Button.setVisible(false);
			}else{
				intPageCount = 3;
				HowToPlay.setText(strGameEndTitle+"\n"+"\n"+strGameEnd+"\n"+"\n"+strGameEnd2);
				Next1Button.setVisible(false);
			}			
			
		}else if(evt.getSource() == PreviousButton){
			intPageCount = intPageCount - 1;				
				if(intPageCount == 1){
					PreviousButton.setVisible(false);
					HowToPlay.setText(strObjectiveTitle+"\n"+"\n"+strObjective+"\n"+"\n"+strObjective2+"\n"+"\n"+strPieces+"\n"+"\n"+strPiecesNote);					
				}else if(intPageCount == 2){
					PreviousButton.setVisible(true);
					Next1Button.setVisible(true);
					HowToPlay.setText(strPreparingBattleTitle+"\n"+"\n"+strPreparingBattle+"\n"+"\n"+strPreparingBattleNote+"\n"+"\n"+strMovement+"\n"+"\n"+strChallenging+"\n"+"\n"+strChallengingConditions+"\n"+"\n"+strChallenging2);
				}else if(intPageCount == 3){
					HowToPlay.setText(strGameEndTitle+"\n"+"\n"+strGameEnd+"\n"+"\n"+strGameEnd2);	
					Next1Button.setVisible(false);			
				}else{
					intPageCount = 3;
					HowToPlay.setText(strGameEndTitle+"\n"+"\n"+strGameEnd+"\n"+"\n"+strGameEnd2);
					Next1Button.setVisible(false);
			}
		}
		if(evt.getSource() == enterButton){
			
			String strFriendIP = addFriend.getText();
			System.out.println(strFriendIP);
			
		
			ssm = new SuperSocketMaster(strFriendIP, 2468, this);
			ssm.connect();
			System.out.println("Server joined");
			
			thepanel.blnPlay = true;
			thepanel.blnClientView = true;
			
			addAddress.setVisible(false);
			addFriend.setVisible(false);
			enterButton.setVisible(false);
			thepanel.blnStart = false;
			chatBoxReceive.setVisible(true);
			chatBoxSend.setVisible(true);
			chatBoxScroll.setVisible(true);
			
			blnSettingUp = true;
			
		}
		
		if(evt.getSource() == chatBoxSend){
			ssm.sendText("Sent: " + chatBoxSend.getText());
			chatBoxReceive.append("Me: " + chatBoxSend.getText() + "\n");
			chatBoxSend.setText("");
		}else if(evt.getSource() == ssm){
			String strData = ssm.readText();
			int intDataLength = strData.length();
			if(strData.substring(0,6).equals("Sent: ")){
				chatBoxReceive.append("Opponent: " + strData.substring(6,intDataLength) + "\n");
			}
		}
		
		if(evt.getSource() == sReadyButton){
			addAddress.setVisible(false);
			sReadyButton.setVisible(false);
			
			thepanel.blnPlay = true;
			thepanel.blnServerView = true;
			
			chatBoxReceive.setVisible(true);
			chatBoxSend.setVisible(true);
			chatBoxScroll.setVisible(true);
			
			blnSettingUp = true;
			
		}

	}
	
// - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -	
	
	// METHODS - KEYLISTENER (Key Released)
	public void keyReleased(KeyEvent evt){
	}
	
	// METHODS - KEYLISTENER (Key Pressed)
	public void keyPressed(KeyEvent evt){
		//System.out.println(evt.getKeyCode());
	}	
	
	// METHODS - KEYLISTENER (Key Typed)
	public void keyTyped(KeyEvent evt){
		
	}	
	
// - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -	
	
	// METHODS - MOUSELISTENER (Mouse Exited)
	public void mouseExited(MouseEvent evt){
	}	
	
	// METHODS - MOUSELISTENER (Mouse Entered)
	public void mouseEntered(MouseEvent evt){
		
	}
	
	// METHODS - MOUSELISTENER (Mouse Released)
	public void mouseReleased(MouseEvent evt){
		if(blnSettingUp){
			int intResultX;
			int intResultY;
			int intModX;
			int intModY;
			
			if(blnMoveWH1L){
				intResultX = evt.getX();
				intResultY = evt.getY();
				intResultX = intResultX - 10;
				intModX = intResultX/70;
				intResultX = intModX * 70 + 13;
				thepanel.WH1LieutenantOBJ.intX = intResultX;
				intResultY = intResultY - 80;
				intModY = intResultY/70;
				intResultY = intModY * 70 + 105;
				thepanel.WH1LieutenantOBJ.intY = intResultY;
				blnMoveWH1L = false;
			}
			if(blnMoveWH2L){
				intResultX = evt.getX();
				intResultY = evt.getY();
				intResultX = intResultX - 10;
				intModX = intResultX/70;
				intResultX = intModX * 70 + 13;
				thepanel.WH2LieutenantOBJ.intX = intResultX;
				intResultY = intResultY - 80;
				intModY = intResultY/70;
				intResultY = intModY * 70 + 105;
				thepanel.WH2LieutenantOBJ.intY = intResultY;
				blnMoveWH2L = false;
			}
			if(blnMoveWHCap){
				intResultX = evt.getX();
				intResultY = evt.getY();
				intResultX = intResultX - 10;
				intModX = intResultX/70;
				intResultX = intModX * 70 + 13;
				thepanel.WHCaptainOBJ.intX = intResultX;
				intResultY = intResultY - 80;
				intModY = intResultY/70;
				intResultY = intModY * 70 + 105;
				thepanel.WHCaptainOBJ.intY = intResultY;
				blnMoveWHCap = false;
			}
			if(blnMoveWHCol){
				intResultX = evt.getX();
				intResultY = evt.getY();
				intResultX = intResultX - 10;
				intModX = intResultX/70;
				intResultX = intModX * 70 + 13;
				thepanel.WHColonelOBJ.intX = intResultX;
				intResultY = intResultY - 80;
				intModY = intResultY/70;
				intResultY = intModY * 70 + 105;
				thepanel.WHColonelOBJ.intY = intResultY;
				blnMoveWHCol = false;
			}
			if(blnMoveWH1S){
				intResultX = evt.getX();
				intResultY = evt.getY();
				intResultX = intResultX - 10;
				intModX = intResultX/70;
				intResultX = intModX * 70 + 13;
				thepanel.WH1StarOBJ.intX = intResultX;
				intResultY = intResultY - 80;
				intModY = intResultY/70;
				intResultY = intModY * 70 + 105;
				thepanel.WH1StarOBJ.intY = intResultY;
				blnMoveWH1S = false;
			}
			if(blnMoveWH2S){
				intResultX = evt.getX();
				intResultY = evt.getY();
				intResultX = intResultX - 10;
				intModX = intResultX/70;
				intResultX = intModX * 70 + 13;
				thepanel.WH2StarOBJ.intX = intResultX;
				intResultY = intResultY - 80;
				intModY = intResultY/70;
				intResultY = intModY * 70 + 105;
				thepanel.WH2StarOBJ.intY = intResultY;
				blnMoveWH2S = false;
			}
			if(blnMoveWH3S){
				intResultX = evt.getX();
				intResultY = evt.getY();
				intResultX = intResultX - 10;
				intModX = intResultX/70;
				intResultX = intModX * 70 + 13;
				thepanel.WH3StarOBJ.intX = intResultX;
				intResultY = intResultY - 80;
				intModY = intResultY/70;
				intResultY = intModY * 70 + 105;
				thepanel.WH3StarOBJ.intY = intResultY;
				blnMoveWH3S = false;
			}
			if(blnMoveWH4S){
				intResultX = evt.getX();
				intResultY = evt.getY();
				intResultX = intResultX - 10;
				intModX = intResultX/70;
				intResultX = intModX * 70 + 13;
				thepanel.WH4StarOBJ.intX = intResultX;
				intResultY = intResultY - 80;
				intModY = intResultY/70;
				intResultY = intModY * 70 + 105;
				thepanel.WH4StarOBJ.intY = intResultY;
				blnMoveWH4S = false;
			}
			if(blnMoveWH5S){
				intResultX = evt.getX();
				intResultY = evt.getY();
				intResultX = intResultX - 10;
				intModX = intResultX/70;
				intResultX = intModX * 70 + 13;
				thepanel.WH5StarOBJ.intX = intResultX;
				intResultY = intResultY - 80;
				intModY = intResultY/70;
				intResultY = intModY * 70 + 105;
				thepanel.WH5StarOBJ.intY = intResultY;
				blnMoveWH5S = false;
			}
			if(blnMoveWHM){
				intResultX = evt.getX();
				intResultY = evt.getY();
				intResultX = intResultX - 10;
				intModX = intResultX/70;
				intResultX = intModX * 70 + 13;
				thepanel.WHMajorOBJ.intX = intResultX;
				intResultY = intResultY - 80;
				intModY = intResultY/70;
				intResultY = intModY * 70 + 105;
				thepanel.WHMajorOBJ.intY = intResultY;
				blnMoveWHM = false;
			}
			if(blnMoveWHF){
				intResultX = evt.getX();
				intResultY = evt.getY();
				intResultX = intResultX - 10;
				intModX = intResultX/70;
				intResultX = intModX * 70 + 13;
				thepanel.WHFlagOBJ.intX = intResultX;
				intResultY = intResultY - 80;
				intModY = intResultY/70;
				intResultY = intModY * 70 + 105;
				thepanel.WHFlagOBJ.intY = intResultY;
				blnMoveWHF = false;
			}
			if(blnMoveWHLC){
				intResultX = evt.getX();
				intResultY = evt.getY();
				intResultX = intResultX - 10;
				intModX = intResultX/70;
				intResultX = intModX * 70 + 13;
				thepanel.WHLColonelOBJ.intX = intResultX;
				intResultY = intResultY - 80;
				intModY = intResultY/70;
				intResultY = intModY * 70 + 105;
				thepanel.WHLColonelOBJ.intY = intResultY;
				blnMoveWHLC = false;
			}
			if(blnMoveWHSer){
				intResultX = evt.getX();
				intResultY = evt.getY();
				intResultX = intResultX - 10;
				intModX = intResultX/70;
				intResultX = intModX * 70 + 13;
				thepanel.WHSergeantOBJ.intX = intResultX;
				intResultY = intResultY - 80;
				intModY = intResultY/70;
				intResultY = intModY * 70 + 105;
				thepanel.WHSergeantOBJ.intY = intResultY;
				blnMoveWHSer = false;
			}
			if(blnMoveWHSpy1){
				intResultX = evt.getX();
				intResultY = evt.getY();
				intResultX = intResultX - 10;
				intModX = intResultX/70;
				intResultX = intModX * 70 + 13;
				thepanel.WHSpy1OBJ.intX = intResultX;
				intResultY = intResultY - 80;
				intModY = intResultY/70;
				intResultY = intModY * 70 + 105;
				thepanel.WHSpy1OBJ.intY = intResultY;
				blnMoveWHSpy1 = false;
			}
			if(blnMoveWHSpy2){
				intResultX = evt.getX();
				intResultY = evt.getY();
				intResultX = intResultX - 10;
				intModX = intResultX/70;
				intResultX = intModX * 70 + 13;
				thepanel.WHSpy2OBJ.intX = intResultX;
				intResultY = intResultY - 80;
				intModY = intResultY/70;
				intResultY = intModY * 70 + 105;
				thepanel.WHSpy2OBJ.intY = intResultY;
				blnMoveWHSpy2 = false;
			}
			if(blnMoveWHP1){
				intResultX = evt.getX();
				intResultY = evt.getY();
				intResultX = intResultX - 10;
				intModX = intResultX/70;
				intResultX = intModX * 70 + 13;
				thepanel.WHPrivate1OBJ.intX = intResultX;
				intResultY = intResultY - 80;
				intModY = intResultY/70;
				intResultY = intModY * 70 + 105;
				thepanel.WHPrivate1OBJ.intY = intResultY;
				blnMoveWHP1 = false;
			}
			if(blnMoveWHP2){
				intResultX = evt.getX();
				intResultY = evt.getY();
				intResultX = intResultX - 10;
				intModX = intResultX/70;
				intResultX = intModX * 70 + 13;
				thepanel.WHPrivate2OBJ.intX = intResultX;
				intResultY = intResultY - 80;
				intModY = intResultY/70;
				intResultY = intModY * 70 + 105;
				thepanel.WHPrivate2OBJ.intY = intResultY;
				blnMoveWHP2 = false;
			}
			if(blnMoveWHP3){
				intResultX = evt.getX();
				intResultY = evt.getY();
				intResultX = intResultX - 10;
				intModX = intResultX/70;
				intResultX = intModX * 70 + 13;
				thepanel.WHPrivate3OBJ.intX = intResultX;
				intResultY = intResultY - 80;
				intModY = intResultY/70;
				intResultY = intModY * 70 + 105;
				thepanel.WHPrivate3OBJ.intY = intResultY;
				blnMoveWHP3 = false;
			}
			if(blnMoveWHP4){
				intResultX = evt.getX();
				intResultY = evt.getY();
				intResultX = intResultX - 10;
				intModX = intResultX/70;
				intResultX = intModX * 70 + 13;
				thepanel.WHPrivate4OBJ.intX = intResultX;
				intResultY = intResultY - 80;
				intModY = intResultY/70;
				intResultY = intModY * 70 + 105;
				thepanel.WHPrivate4OBJ.intY = intResultY;
				blnMoveWHP4 = false;
			}
			if(blnMoveWHP5){
				intResultX = evt.getX();
				intResultY = evt.getY();
				intResultX = intResultX - 10;
				intModX = intResultX/70;
				intResultX = intModX * 70 + 13;
				thepanel.WHPrivate5OBJ.intX = intResultX;
				intResultY = intResultY - 80;
				intModY = intResultY/70;
				intResultY = intModY * 70 + 105;
				thepanel.WHPrivate5OBJ.intY = intResultY;
				blnMoveWHP5 = false;
			}
			if(blnMoveWHP6){
				intResultX = evt.getX();
				intResultY = evt.getY();
				intResultX = intResultX - 10;
				intModX = intResultX/70;
				intResultX = intModX * 70 + 13;
				thepanel.WHPrivate6OBJ.intX = intResultX;
				intResultY = intResultY - 80;
				intModY = intResultY/70;
				intResultY = intModY * 70 + 105;
				thepanel.WHPrivate6OBJ.intY = intResultY;
				blnMoveWHP6 = false;
			}
		}
		
	}
	
	boolean blnMoveWH1L = false;
	boolean blnMoveWH2L = false;
	boolean blnMoveWHCap = false;
	boolean blnMoveWHCol = false;
	boolean blnMoveWH1S = false;
	boolean blnMoveWH2S = false;
	boolean blnMoveWH3S = false;
	boolean blnMoveWH4S = false;
	boolean blnMoveWH5S = false;
	boolean blnMoveWHM = false;
	boolean blnMoveWHF = false;
	boolean blnMoveWHLC = false;
	boolean blnMoveWHSer = false;
	boolean blnMoveWHSpy1 = false;
	boolean blnMoveWHSpy2 = false;
	boolean blnMoveWHP1 = false;
	boolean blnMoveWHP2 = false;
	boolean blnMoveWHP3 = false;
	boolean blnMoveWHP4 = false;
	boolean blnMoveWHP5 = false;
	boolean blnMoveWHP6 = false;
	
	// METHODS - MOUSELISTENER (Mouse Pressed)
	public void mousePressed(MouseEvent evt){
		if(evt.getX() > thepanel.WH1LieutenantOBJ.intX && evt.getX() < thepanel.WH1LieutenantOBJ.intX + 65 && evt.getY() > thepanel.WH1LieutenantOBJ.intY && evt.getY() < thepanel.WH1LieutenantOBJ.intY + 20){
			blnMoveWH1L = true;
		}
		if(evt.getX() > thepanel.WH2LieutenantOBJ.intX && evt.getX() < thepanel.WH2LieutenantOBJ.intX + 65 && evt.getY() > thepanel.WH2LieutenantOBJ.intY && evt.getY() < thepanel.WH2LieutenantOBJ.intY + 20){
			blnMoveWH2L = true;
		}
		if(evt.getX() > thepanel.WHCaptainOBJ.intX && evt.getX() < thepanel.WHCaptainOBJ.intX + 65 && evt.getY() > thepanel.WHCaptainOBJ.intY && evt.getY() < thepanel.WHCaptainOBJ.intY + 20){
			blnMoveWHCap = true;
		}
		if(evt.getX() > thepanel.WHColonelOBJ.intX && evt.getX() < thepanel.WHColonelOBJ.intX + 65 && evt.getY() > thepanel.WHColonelOBJ.intY && evt.getY() < thepanel.WHColonelOBJ.intY + 20){
			blnMoveWHCol = true;
		}
		if(evt.getX() > thepanel.WH1StarOBJ.intX && evt.getX() < thepanel.WH1StarOBJ.intX + 65 && evt.getY() > thepanel.WH1StarOBJ.intY && evt.getY() < thepanel.WH1StarOBJ.intY + 20){
			blnMoveWH1S = true;
		}
		if(evt.getX() > thepanel.WH2StarOBJ.intX && evt.getX() < thepanel.WH2StarOBJ.intX + 65 && evt.getY() > thepanel.WH2StarOBJ.intY && evt.getY() < thepanel.WH2StarOBJ.intY + 20){
			blnMoveWH2S = true;
		}
		if(evt.getX() > thepanel.WH3StarOBJ.intX && evt.getX() < thepanel.WH3StarOBJ.intX + 65 && evt.getY() > thepanel.WH3StarOBJ.intY && evt.getY() < thepanel.WH3StarOBJ.intY + 20){
			blnMoveWH3S = true;
		}
		if(evt.getX() > thepanel.WH4StarOBJ.intX && evt.getX() < thepanel.WH4StarOBJ.intX + 65 && evt.getY() > thepanel.WH4StarOBJ.intY && evt.getY() < thepanel.WH4StarOBJ.intY + 20){
			blnMoveWH4S = true;
		}
		if(evt.getX() > thepanel.WH5StarOBJ.intX && evt.getX() < thepanel.WH5StarOBJ.intX + 65 && evt.getY() > thepanel.WH5StarOBJ.intY && evt.getY() < thepanel.WH5StarOBJ.intY + 20){
			blnMoveWH5S = true;
		}
		if(evt.getX() > thepanel.WHMajorOBJ.intX && evt.getX() < thepanel.WHMajorOBJ.intX + 65 && evt.getY() > thepanel.WHMajorOBJ.intY && evt.getY() < thepanel.WHMajorOBJ.intY + 20){
			blnMoveWHM = true;
		}
		if(evt.getX() > thepanel.WHFlagOBJ.intX && evt.getX() < thepanel.WHFlagOBJ.intX + 65 && evt.getY() > thepanel.WHFlagOBJ.intY && evt.getY() < thepanel.WHFlagOBJ.intY + 20){
			blnMoveWHF = true;
		}
		if(evt.getX() > thepanel.WHLColonelOBJ.intX && evt.getX() < thepanel.WHLColonelOBJ.intX + 65 && evt.getY() > thepanel.WHLColonelOBJ.intY && evt.getY() < thepanel.WHLColonelOBJ.intY + 20){
			blnMoveWHLC = true;
		}
		if(evt.getX() > thepanel.WHSergeantOBJ.intX && evt.getX() < thepanel.WHSergeantOBJ.intX + 65 && evt.getY() > thepanel.WHSergeantOBJ.intY && evt.getY() < thepanel.WHSergeantOBJ.intY + 20){
			blnMoveWHSer = true;
		}
		if(evt.getX() > thepanel.WHSpy1OBJ.intX && evt.getX() < thepanel.WHSpy1OBJ.intX + 65 && evt.getY() > thepanel.WHSpy1OBJ.intY && evt.getY() < thepanel.WHSpy1OBJ.intY + 20){
			blnMoveWHSpy1 = true;
		}
		if(evt.getX() > thepanel.WHSpy2OBJ.intX && evt.getX() < thepanel.WHSpy2OBJ.intX + 65 && evt.getY() > thepanel.WHSpy2OBJ.intY && evt.getY() < thepanel.WHSpy2OBJ.intY + 20){
			blnMoveWHSpy2 = true;
		}
		if(evt.getX() > thepanel.WHPrivate1OBJ.intX && evt.getX() < thepanel.WHPrivate1OBJ.intX + 65 && evt.getY() > thepanel.WHPrivate1OBJ.intY && evt.getY() < thepanel.WHPrivate1OBJ.intY + 20){
			blnMoveWHP1 = true;
		}
		if(evt.getX() > thepanel.WHPrivate2OBJ.intX && evt.getX() < thepanel.WHPrivate2OBJ.intX + 65 && evt.getY() > thepanel.WHPrivate2OBJ.intY && evt.getY() < thepanel.WHPrivate2OBJ.intY + 20){
			blnMoveWHP2 = true;
		}
		if(evt.getX() > thepanel.WHPrivate3OBJ.intX && evt.getX() < thepanel.WHPrivate3OBJ.intX + 65 && evt.getY() > thepanel.WHPrivate3OBJ.intY && evt.getY() < thepanel.WHPrivate3OBJ.intY + 20){
			blnMoveWHP3 = true;
		}
		if(evt.getX() > thepanel.WHPrivate4OBJ.intX && evt.getX() < thepanel.WHPrivate4OBJ.intX + 65 && evt.getY() > thepanel.WHPrivate4OBJ.intY && evt.getY() < thepanel.WHPrivate4OBJ.intY + 20){
			blnMoveWHP4 = true;
		}
		if(evt.getX() > thepanel.WHPrivate5OBJ.intX && evt.getX() < thepanel.WHPrivate5OBJ.intX + 65 && evt.getY() > thepanel.WHPrivate5OBJ.intY && evt.getY() < thepanel.WHPrivate5OBJ.intY + 20){
			blnMoveWHP5 = true;
		}
		if(evt.getX() > thepanel.WHPrivate6OBJ.intX && evt.getX() < thepanel.WHPrivate6OBJ.intX + 65 && evt.getY() > thepanel.WHPrivate6OBJ.intY && evt.getY() < thepanel.WHPrivate6OBJ.intY + 20){
			blnMoveWHP6 = true;
		}
	}
	
	// METHODS - MOUSELISTENER (Mouse Clicked)
	public void mouseClicked(MouseEvent evt){
		
	}
	
// - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -	
	
	// METHODS - MOUSEMOTIONLISTENER (Mouse Moved)
	public void mouseMoved(MouseEvent evt){
		
	}
	
	// METHODS - MOUSEMOTIONLISTENER (Mouse Dragged)
	public void mouseDragged(MouseEvent evt){	
		if(blnSettingUp){
			//System.out.println("Testing if the dragging is detected");
			if(blnMoveWH1L){
				thepanel.WH1LieutenantOBJ.intX = evt.getX();
				thepanel.WH1LieutenantOBJ.intY = evt.getY();
			}
			if(blnMoveWH2L){
				thepanel.WH2LieutenantOBJ.intX = evt.getX();
				thepanel.WH2LieutenantOBJ.intY = evt.getY();
			}
			if(blnMoveWHCap){
				thepanel.WHCaptainOBJ.intX = evt.getX();
				thepanel.WHCaptainOBJ.intY = evt.getY();
			}
			if(blnMoveWHCol){
				thepanel.WHColonelOBJ.intX = evt.getX();
				thepanel.WHColonelOBJ.intY = evt.getY();
			}
			if(blnMoveWH1S){
				thepanel.WH1StarOBJ.intX = evt.getX();
				thepanel.WH1StarOBJ.intY = evt.getY();
			}
			if(blnMoveWH2S){
				thepanel.WH2StarOBJ.intX = evt.getX();
				thepanel.WH2StarOBJ.intY = evt.getY();
			}
			if(blnMoveWH3S){
				thepanel.WH3StarOBJ.intX = evt.getX();
				thepanel.WH3StarOBJ.intY = evt.getY();
			}
			if(blnMoveWH4S){
				thepanel.WH4StarOBJ.intX = evt.getX();
				thepanel.WH4StarOBJ.intY = evt.getY();
			}
			if(blnMoveWH5S){
				thepanel.WH5StarOBJ.intX = evt.getX();
				thepanel.WH5StarOBJ.intY = evt.getY();
			}
			if(blnMoveWHM){
				thepanel.WHMajorOBJ.intX = evt.getX();
				thepanel.WHMajorOBJ.intY = evt.getY();
			}
			if(blnMoveWHF){
				thepanel.WHFlagOBJ.intX = evt.getX();
				thepanel.WHFlagOBJ.intY = evt.getY();
			}
			if(blnMoveWHLC){
				thepanel.WHLColonelOBJ.intX = evt.getX();
				thepanel.WHLColonelOBJ.intY = evt.getY();
			}
			if(blnMoveWHSer){
				thepanel.WHSergeantOBJ.intX = evt.getX();
				thepanel.WHSergeantOBJ.intY = evt.getY();
			}
			if(blnMoveWHSpy1){
				thepanel.WHSpy1OBJ.intX = evt.getX();
				thepanel.WHSpy1OBJ.intY = evt.getY();
			}
			if(blnMoveWHSpy2){
				thepanel.WHSpy2OBJ.intX = evt.getX();
				thepanel.WHSpy2OBJ.intY = evt.getY();
			}
			if(blnMoveWHP1){
				thepanel.WHPrivate1OBJ.intX = evt.getX();
				thepanel.WHPrivate1OBJ.intY = evt.getY();
			}
			if(blnMoveWHP2){
				thepanel.WHPrivate2OBJ.intX = evt.getX();
				thepanel.WHPrivate2OBJ.intY = evt.getY();
			}
			if(blnMoveWHP3){
				thepanel.WHPrivate3OBJ.intX = evt.getX();
				thepanel.WHPrivate3OBJ.intY = evt.getY();
			}
			if(blnMoveWHP4){
				thepanel.WHPrivate4OBJ.intX = evt.getX();
				thepanel.WHPrivate4OBJ.intY = evt.getY();
			}
			if(blnMoveWHP5){
				thepanel.WHPrivate5OBJ.intX = evt.getX();
				thepanel.WHPrivate5OBJ.intY = evt.getY();
			}
			if(blnMoveWHP6){
				thepanel.WHPrivate6OBJ.intX = evt.getX();
				thepanel.WHPrivate6OBJ.intY = evt.getY();
			}
		}
	}

// - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
	
	// CONSTRUCTOR
	public GameOfGenerals(){
		theframe = new JFrame("~ Game of the Generals ~");
		thepanel = new GameofGeneralsAnimationPanel();
		thepanel.addMouseListener(this);
		thepanel.addMouseMotionListener(this);
		thepanel.setLayout(null);
		thepanel.setPreferredSize(new Dimension(1280,720));
		theframe.setResizable(false);
		
		thetimer = new Timer(1000/60, this);
		thetimer.start();	
		
		StartGameButton = new JButton("Start Game!");
		StartGameButton.addActionListener(this);
		StartGameButton.setBounds(540,310,200,50);
		HelpButton = new JButton("How to Play");
		HelpButton.addActionListener(this);
		HelpButton.setBounds(540,360,200,50);
		
		ObjectiveLabel = new JLabel("HOW TO PLAY 'GAME OF THE GENERALS'");
		ObjectiveLabel.setBounds(510,10,300,50);
		ObjectiveLabel.setVisible(false);
		HowToPlay = new JTextArea(strObjectiveTitle+"\n"+"\n"+strObjective+"\n"+"\n"+strObjective2+"\n"+"\n"+strPieces+"\n"+"\n"+strPiecesNote);
		HowToPlay.setLineWrap(true);
		HowToPlay.setWrapStyleWord(true);
		//HowToPlay.setBounds(390,50,500,550);
		// ^^ Bounds set in Scroll pane instead
		HowToPlay.setVisible(false);
		HowToPlay.setEditable(false);
		HTPScroll = new JScrollPane(HowToPlay);
		HTPScroll.setSize(500,500);
		HTPScroll.setLocation(390,50);
		HTPScroll.setVisible(false);
		MainMenuButton = new JButton("Main Menu");
		MainMenuButton.addActionListener(this);
		MainMenuButton.setBounds(390,610,160,50);
		MainMenuButton.setVisible(false);
		Next1Button = new JButton("Next ->");
		Next1Button.addActionListener(this);
		Next1Button.setBounds(730,610,160,50);
		Next1Button.setVisible(false);
		PreviousButton = new JButton("<- Previous");
		PreviousButton.addActionListener(this);
		PreviousButton.setBounds(560,610,160,50);
		PreviousButton.setVisible(false);
		
		CreateServerButton = new JButton("Create a New Server");
		CreateServerButton.addActionListener(this);
		CreateServerButton.setBounds(540,310,200,50);
		CreateServerButton.setVisible(false);
		JoinServerButton = new JButton("Join an Existing Server");
		JoinServerButton.addActionListener(this);
		JoinServerButton.setBounds(540,360,200,50);
		JoinServerButton.setVisible(false);
		
		addAddress = new JLabel();
		addAddress.setBounds(500,300,600,100);
		addAddress.setVisible(false);
		
		addFriend = new JTextArea();
		addFriend.setBounds(500,400,600,100);
		addFriend.setVisible(false);
		
		enterButton = new JButton("Enter");
		enterButton.setBounds(500,600,100,50);
		enterButton.setVisible(false);
		enterButton.addActionListener(this);
		
		chatBoxReceive = new JTextArea();
		//chatBoxReceive.setBounds(650,450,600,180);
		// ^^ bounds set in Scroll pane
		chatBoxReceive.setVisible(false);
		chatBoxReceive.setEditable(false);

		chatBoxSend = new JTextField();
		chatBoxSend.setBounds(650,640,600,30);
		chatBoxSend.setVisible(false);
		chatBoxSend.addActionListener(this);
		
		chatBoxScroll = new JScrollPane(chatBoxReceive);
		chatBoxScroll.setSize(600,180);
		chatBoxScroll.setLocation(650,450);
		chatBoxScroll.setVisible(false);
		
		sReadyButton = new JButton("Ready");
		sReadyButton.setBounds(500,500, 200,50);
		sReadyButton.setVisible(false);
		sReadyButton.addActionListener(this);
		
		thepanel.add(StartGameButton);
		thepanel.add(HelpButton);
		thepanel.add(CreateServerButton);
		thepanel.add(JoinServerButton);
		thepanel.add(ObjectiveLabel);
		//thepanel.add(HowToPlay);
		// ^^ Scroll pane including HowToPlay is added below already
		thepanel.add(HTPScroll);
		thepanel.add(Next1Button);
		thepanel.add(MainMenuButton);
		thepanel.add(PreviousButton);
		thepanel.add(addAddress);
		thepanel.add(addFriend);
		thepanel.add(enterButton);
		//thepanel.add(chatBoxReceive);
		// scroll pane includes chatBoxReceive already
		thepanel.add(chatBoxSend);
		thepanel.add(chatBoxScroll);
		thepanel.add(sReadyButton);
		
		theframe.setContentPane(thepanel);
		theframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		theframe.pack();
		theframe.setVisible(true);	
				
		
	}
	
// - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -	
	
	// MAIN METHOD
	public static void main(String[] args){
		new GameOfGenerals();
	}	
	
}
