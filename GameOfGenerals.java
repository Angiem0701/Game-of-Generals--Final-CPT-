import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/** 
 * Game of the Generals
 * ICS 4U1 Final CPT
 * This is a computer version of "Game of the Generals" using concepts and tools learned throughout the course
 * It enables players to play over a network through the use of Cadawas' SuperSocketMaster
 * @author Angelica Manansala, Ivan Lau, Caitlin Kwan
 * @since 2018-12-21
 * Last Updated: January 23, 2019
 */

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
	
	JButton wDoneSetUp  = new JButton();
	JButton bDoneSetUp = new JButton();
	
	JTextArea BriefRules = new JTextArea();
	JScrollPane BriefRulesScroll = new JScrollPane();
	
	SuperSocketMaster ssm;
	
	// Variables
	
	/**
	 * This counts the number of pages (or times essentially) the player either clicks the PreviousButton, NextButton, or MainMenuButton
	 * This dictates the text that will be shown on the HowToPlay Text Area
	 */
	
	int intPageCount = 0;
	
	// This is the text that will be shown in the HowToPlay Text Area in the instructions
	
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
	
	String strBriefRules = "*** THE OBJECTIVE OF THE GAME ***" + "\n" + "\n" + "ELIMINATE or CAPTURE the FLAG of your opponent OR successfully maneuver your own FLAG to the opposite end of the board." + "\n" + "\n" + "*** CHALLENGING: ***" + "\n" + "\n" + "When a challenge is made, the following rules of elimination apply: " + "\n" + "- A higher ranked soldier elimates a lower ranked soldier." + "\n" + "- If both soldiers are of equal ranks, they are both eliminated." + "\n" + "- A spy eliminates any officer starting from the Sergeant and above... BUT can be eliminated by facing an opposing Private." + "\n" + "- The Flag can be eliminated or captured by any piece, including the opponent's Flag." + "\n" + "\n" + "If you would like to offer your opponent some enccouragement or send a friendly message, feel free to use the chat box below :)" + "\n" + "\n" + "Good luck!";
	
	/**
	 * This indicates if the players are in the process of setting up their pieces 
	 */
	 
	boolean blnSettingUp;
	
	//Arrays~ one for server side and one from client side
	rank strWBoard[][] = new rank[9][8];
	rank strBBoard[][] = new rank[9][8];

	/**
	 * This is the string of text sent over the network
	 */

	String sendLine;
	
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
			//Creating a server and showing IP address so client can type on their end to connect
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
			// Connecting to server by client entering server IP
			addAddress.setText(strAddAddress);
			addAddress.setVisible(true);
			addFriend.setVisible(true);
			enterButton.setVisible(true);
						
			CreateServerButton.setVisible(false);
			JoinServerButton.setVisible(false);
			
		}else if(evt.getSource() == HelpButton){
			//Various instructions
			intPageCount = intPageCount + 1;
			StartGameButton.setVisible(false);
			HelpButton.setVisible(false);
			ObjectiveLabel.setVisible(true);
			HowToPlay.setVisible(true);
			HTPScroll.setVisible(true);
			Next1Button.setVisible(true);
			MainMenuButton.setVisible(true);
			
		}else if(evt.getSource() == MainMenuButton){
			//Displays Main menu options
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
			//When client has typed in server IP
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
			BriefRules.setVisible(true);
			BriefRulesScroll.setVisible(true);
			
			blnSettingUp = true;
			
			wDoneSetUp.setVisible(true);
			
			theframe.requestFocus();
			
		}
		
		if(evt.getSource() == chatBoxSend){
			//Text field to send messages across socket using the SuperSocketMaster
			//Networking protocol ~ distinguished by "Sent: " at the beginning of the string received
			ssm.sendText("Sent: " + chatBoxSend.getText());
			chatBoxReceive.append("Me: " + chatBoxSend.getText() + "\n");
			chatBoxSend.setText("");
		}
		
		if(evt.getSource() == ssm){
			String strData = ssm.readText();
			int intDataLength = strData.length();
			//chat system
			if(strData.substring(0,6).equals("Sent: ")){
				chatBoxReceive.append("Opponent: " + strData.substring(6,intDataLength) + "\n");
			}
			//checking when both players have set up their pieces
			else if(strData.substring(0,7).equals("Ready: ")){
				thepanel.intReady = Integer.parseInt(strData.substring(7,8));
				System.out.println("Ready: " + thepanel.intReady);
				if(thepanel.intReady == 2){
					thepanel.strWhosTurn = "White";
				}
			}else if(strData.substring(0,10).equals("WhosTurn: ")){
				//communicating which player's turn it is over the network
				thepanel.strWhosTurn = strData.substring(10,intDataLength);
			}else{
				String[] splitData = strData.split(",");
				System.out.println(strData);
				if(splitData[0].equals("Winner")){
					//communicating the winner when one has been determined
					if(splitData[1].equals("white")){
						thepanel.blnWhiteWins = true;
						thepanel.intReady = -1;
					}else{
						thepanel.blnBlackWins = true;
						thepanel.intReady = -1;
					}
				}
				if(splitData[0].equals("Setup")){
					// resetting the array every time something is moved
					int intCount1;
					int intCount2;
					for(intCount1 = 0; intCount1 <= 8; intCount1++){
						for(intCount2 = 0; intCount2 <= 7; intCount2++){
							//client array
							strWBoard[intCount1][intCount2] = null;
							//server array
							strBBoard[intCount1][intCount2] = null;
						}
					}
					//using data sent over network to update piece locations
					if(splitData[1].equals("BLFlag")){
						thepanel.BLFlagOBJ.intArrayX = 8 - Integer.parseInt(splitData[2]);
						//System.out.println(thepanel.BLFlagOBJ.intArrayX);
						thepanel.BLFlagOBJ.intArrayY = 7 - Integer.parseInt(splitData[3]);
						//System.out.println(thepanel.BLFlagOBJ.intArrayY);
						if(splitData[4].equals("true")){
							thepanel.BLFlagOBJ.blnAlive = true;
						}else{
							thepanel.BLFlagOBJ.blnAlive = false;
						}
						thepanel.BLFlagOBJ.intX = thepanel.BLFlagOBJ.intArrayX * 70 + 13;
						thepanel.BLFlagOBJ.intY = thepanel.BLFlagOBJ.intArrayY * 70 + 105;
						strWBoard[thepanel.BLFlagOBJ.intArrayX][thepanel.BLFlagOBJ.intArrayY] = thepanel.BLFlagOBJ;
					}
					if(splitData[1].equals("BLPrivate1")){
						thepanel.BLPrivate1OBJ.intArrayX = 8 - Integer.parseInt(splitData[2]);
						thepanel.BLPrivate1OBJ.intArrayY = 7 - Integer.parseInt(splitData[3]);
						if(splitData[4].equals("true")){
							thepanel.BLPrivate1OBJ.blnAlive = true;
						}else{
							thepanel.BLPrivate1OBJ.blnAlive = false;
						}
						thepanel.BLPrivate1OBJ.intX = thepanel.BLPrivate1OBJ.intArrayX * 70 + 13;
						thepanel.BLPrivate1OBJ.intY = thepanel.BLPrivate1OBJ.intArrayY * 70 + 105;
						strWBoard[thepanel.BLPrivate1OBJ.intArrayX][thepanel.BLPrivate1OBJ.intArrayY] = thepanel.BLPrivate1OBJ;
					}
					if(splitData[1].equals("BLPrivate2")){
						thepanel.BLPrivate2OBJ.intArrayX = 8 - Integer.parseInt(splitData[2]);
						thepanel.BLPrivate2OBJ.intArrayY = 7 - Integer.parseInt(splitData[3]);
						if(splitData[4].equals("true")){
							thepanel.BLPrivate2OBJ.blnAlive = true;
						}else{
							thepanel.BLPrivate2OBJ.blnAlive = false;
						}
						thepanel.BLPrivate2OBJ.intX = thepanel.BLPrivate2OBJ.intArrayX * 70 + 13;
						thepanel.BLPrivate2OBJ.intY = thepanel.BLPrivate2OBJ.intArrayY * 70 + 105;
						strWBoard[thepanel.BLPrivate2OBJ.intArrayX][thepanel.BLPrivate2OBJ.intArrayY] = thepanel.BLPrivate2OBJ;
					}
					if(splitData[1].equals("BLPrivate3")){
						thepanel.BLPrivate3OBJ.intArrayX = 8 - Integer.parseInt(splitData[2]);
						thepanel.BLPrivate3OBJ.intArrayY = 7 - Integer.parseInt(splitData[3]);
						if(splitData[4].equals("true")){
							thepanel.BLPrivate3OBJ.blnAlive = true;
						}else{
							thepanel.BLPrivate3OBJ.blnAlive = false;
						}
						thepanel.BLPrivate3OBJ.intX = thepanel.BLPrivate3OBJ.intArrayX * 70 + 13;
						thepanel.BLPrivate3OBJ.intY = thepanel.BLPrivate3OBJ.intArrayY * 70 + 105;
						strWBoard[thepanel.BLPrivate3OBJ.intArrayX][thepanel.BLPrivate3OBJ.intArrayY] = thepanel.BLPrivate3OBJ;
					}
					if(splitData[1].equals("BLPrivate4")){
						thepanel.BLPrivate4OBJ.intArrayX = 8 - Integer.parseInt(splitData[2]);
						thepanel.BLPrivate4OBJ.intArrayY = 7 - Integer.parseInt(splitData[3]);
						if(splitData[4].equals("true")){
							thepanel.BLPrivate4OBJ.blnAlive = true;
						}else{
							thepanel.BLPrivate4OBJ.blnAlive = false;
						}
						thepanel.BLPrivate4OBJ.intX = thepanel.BLPrivate4OBJ.intArrayX * 70 + 13;
						thepanel.BLPrivate4OBJ.intY = thepanel.BLPrivate4OBJ.intArrayY * 70 + 105;
						strWBoard[thepanel.BLPrivate4OBJ.intArrayX][thepanel.BLPrivate4OBJ.intArrayY] = thepanel.BLPrivate4OBJ;
					}
					if(splitData[1].equals("BLPrivate5")){
						thepanel.BLPrivate5OBJ.intArrayX = 8 - Integer.parseInt(splitData[2]);
						thepanel.BLPrivate5OBJ.intArrayY = 7 - Integer.parseInt(splitData[3]);
						if(splitData[4].equals("true")){
							thepanel.BLPrivate5OBJ.blnAlive = true;
						}else{
							thepanel.BLPrivate5OBJ.blnAlive = false;
						}
						thepanel.BLPrivate5OBJ.intX = thepanel.BLPrivate5OBJ.intArrayX * 70 + 13;
						thepanel.BLPrivate5OBJ.intY = thepanel.BLPrivate5OBJ.intArrayY * 70 + 105;
						strWBoard[thepanel.BLPrivate5OBJ.intArrayX][thepanel.BLPrivate5OBJ.intArrayY] = thepanel.BLPrivate5OBJ;
					}
					if(splitData[1].equals("BLPrivate6")){
						thepanel.BLPrivate6OBJ.intArrayX = 8 - Integer.parseInt(splitData[2]);
						thepanel.BLPrivate6OBJ.intArrayY = 7 - Integer.parseInt(splitData[3]);
						if(splitData[4].equals("true")){
							thepanel.BLPrivate6OBJ.blnAlive = true;
						}else{
							thepanel.BLPrivate6OBJ.blnAlive = false;
						}
						thepanel.BLPrivate6OBJ.intX = thepanel.BLPrivate6OBJ.intArrayX * 70 + 13;
						thepanel.BLPrivate6OBJ.intY = thepanel.BLPrivate6OBJ.intArrayY * 70 + 105;
						strWBoard[thepanel.BLPrivate6OBJ.intArrayX][thepanel.BLPrivate6OBJ.intArrayY] = thepanel.BLPrivate6OBJ;
					}
					if(splitData[1].equals("BLSpy1")){
						thepanel.BLSpy1OBJ.intArrayX = 8 - Integer.parseInt(splitData[2]);
						thepanel.BLSpy1OBJ.intArrayY = 7 - Integer.parseInt(splitData[3]);
						if(splitData[4].equals("true")){
							thepanel.BLSpy1OBJ.blnAlive = true;
						}else{
							thepanel.BLSpy1OBJ.blnAlive = false;
						}
						thepanel.BLSpy1OBJ.intX = thepanel.BLSpy1OBJ.intArrayX * 70 + 13;
						thepanel.BLSpy1OBJ.intY = thepanel.BLSpy1OBJ.intArrayY * 70 + 105;
						strWBoard[thepanel.BLSpy1OBJ.intArrayX][thepanel.BLSpy1OBJ.intArrayY] = thepanel.BLSpy1OBJ;
					}
					if(splitData[1].equals("BLSpy2")){
						thepanel.BLSpy2OBJ.intArrayX = 8 - Integer.parseInt(splitData[2]);
						thepanel.BLSpy2OBJ.intArrayY = 7 - Integer.parseInt(splitData[3]);
						if(splitData[4].equals("true")){
							thepanel.BLSpy2OBJ.blnAlive = true;
						}else{
							thepanel.BLSpy2OBJ.blnAlive = false;
						}
						thepanel.BLSpy2OBJ.intX = thepanel.BLSpy2OBJ.intArrayX * 70 + 13;
						thepanel.BLSpy2OBJ.intY = thepanel.BLSpy2OBJ.intArrayY * 70 + 105;
						strWBoard[thepanel.BLSpy2OBJ.intArrayX][thepanel.BLSpy2OBJ.intArrayY] = thepanel.BLSpy2OBJ;
					}
					if(splitData[1].equals("BLSergeant")){
						thepanel.BLSergeantOBJ.intArrayX = 8 - Integer.parseInt(splitData[2]);
						thepanel.BLSergeantOBJ.intArrayY = 7 - Integer.parseInt(splitData[3]);
						if(splitData[4].equals("true")){
							thepanel.BLSergeantOBJ.blnAlive = true;
						}else{
							thepanel.BLSergeantOBJ.blnAlive = false;
						}
						thepanel.BLSergeantOBJ.intX = thepanel.BLSergeantOBJ.intArrayX * 70 + 13;
						thepanel.BLSergeantOBJ.intY = thepanel.BLSergeantOBJ.intArrayY * 70 + 105;
						strWBoard[thepanel.BLSergeantOBJ.intArrayX][thepanel.BLSergeantOBJ.intArrayY] = thepanel.BLSergeantOBJ;
					}
					if(splitData[1].equals("BLLColonel")){
						thepanel.BLLColonelOBJ.intArrayX = 8 - Integer.parseInt(splitData[2]);
						thepanel.BLLColonelOBJ.intArrayY = 7 - Integer.parseInt(splitData[3]);
						if(splitData[4].equals("true")){
							thepanel.BLLColonelOBJ.blnAlive = true;
						}else{
							thepanel.BLLColonelOBJ.blnAlive = false;
						}
						thepanel.BLLColonelOBJ.intX = thepanel.BLLColonelOBJ.intArrayX * 70 + 13;
						thepanel.BLLColonelOBJ.intY = thepanel.BLLColonelOBJ.intArrayY * 70 + 105;
						strWBoard[thepanel.BLLColonelOBJ.intArrayX][thepanel.BLLColonelOBJ.intArrayY] = thepanel.BLLColonelOBJ;
					}
					if(splitData[1].equals("BL2Lieutenant")){
						thepanel.BL2LieutenantOBJ.intArrayX = 8 - Integer.parseInt(splitData[2]);
						thepanel.BL2LieutenantOBJ.intArrayY = 7 - Integer.parseInt(splitData[3]);
						if(splitData[4].equals("true")){
							thepanel.BL2LieutenantOBJ.blnAlive = true;
						}else{
							thepanel.BL2LieutenantOBJ.blnAlive = false;
						}
						thepanel.BL2LieutenantOBJ.intX = thepanel.BL2LieutenantOBJ.intArrayX * 70 + 13;
						thepanel.BL2LieutenantOBJ.intY = thepanel.BL2LieutenantOBJ.intArrayY * 70 + 105;
						strWBoard[thepanel.BL2LieutenantOBJ.intArrayX][thepanel.BL2LieutenantOBJ.intArrayY] = thepanel.BL2LieutenantOBJ;
					}
					if(splitData[1].equals("BL1Lieutenant")){
						thepanel.BL1LieutenantOBJ.intArrayX = 8 - Integer.parseInt(splitData[2]);
						thepanel.BL1LieutenantOBJ.intArrayY = 7 - Integer.parseInt(splitData[3]);
						if(splitData[4].equals("true")){
							thepanel.BL1LieutenantOBJ.blnAlive = true;
						}else{
							thepanel.BL1LieutenantOBJ.blnAlive = false;
						}
						thepanel.BL1LieutenantOBJ.intX = thepanel.BL1LieutenantOBJ.intArrayX * 70 + 13;
						thepanel.BL1LieutenantOBJ.intY = thepanel.BL1LieutenantOBJ.intArrayY * 70 + 105;
						strWBoard[thepanel.BL1LieutenantOBJ.intArrayX][thepanel.BL1LieutenantOBJ.intArrayY] = thepanel.BL1LieutenantOBJ;
					}
					if(splitData[1].equals("BLCaptain")){
						thepanel.BLCaptainOBJ.intArrayX = 8 - Integer.parseInt(splitData[2]);
						thepanel.BLCaptainOBJ.intArrayY = 7 - Integer.parseInt(splitData[3]);
						if(splitData[4].equals("true")){
							thepanel.BLCaptainOBJ.blnAlive = true;
						}else{
							thepanel.BLCaptainOBJ.blnAlive = false;
						}
						thepanel.BLCaptainOBJ.intX = thepanel.BLCaptainOBJ.intArrayX * 70 + 13;
						thepanel.BLCaptainOBJ.intY = thepanel.BLCaptainOBJ.intArrayY * 70 + 105;
						strWBoard[thepanel.BLCaptainOBJ.intArrayX][thepanel.BLCaptainOBJ.intArrayY] = thepanel.BLCaptainOBJ;
					}
					if(splitData[1].equals("BLColonel")){
						thepanel.BLColonelOBJ.intArrayX = 8 - Integer.parseInt(splitData[2]);
						thepanel.BLColonelOBJ.intArrayY = 7 - Integer.parseInt(splitData[3]);
						if(splitData[4].equals("true")){
							thepanel.BLColonelOBJ.blnAlive = true;
						}else{
							thepanel.BLColonelOBJ.blnAlive = false;
						}
						thepanel.BLColonelOBJ.intX = thepanel.BLColonelOBJ.intArrayX * 70 + 13;
						thepanel.BLColonelOBJ.intY = thepanel.BLColonelOBJ.intArrayY * 70 + 105;
						strWBoard[thepanel.BLColonelOBJ.intArrayX][thepanel.BLColonelOBJ.intArrayY] = thepanel.BLColonelOBJ;
					}
					if(splitData[1].equals("BL1Star")){
						thepanel.BL1StarOBJ.intArrayX = 8 - Integer.parseInt(splitData[2]);
						thepanel.BL1StarOBJ.intArrayY = 7 - Integer.parseInt(splitData[3]);
						if(splitData[4].equals("true")){
							thepanel.BL1StarOBJ.blnAlive = true;
						}else{
							thepanel.BL1StarOBJ.blnAlive = false;
						}
						thepanel.BL1StarOBJ.intX = thepanel.BL1StarOBJ.intArrayX * 70 + 13;
						thepanel.BL1StarOBJ.intY = thepanel.BL1StarOBJ.intArrayY * 70 + 105;
						strWBoard[thepanel.BL1StarOBJ.intArrayX][thepanel.BL1StarOBJ.intArrayY] = thepanel.BL1StarOBJ;
					}
					if(splitData[1].equals("BL2Star")){
						thepanel.BL2StarOBJ.intArrayX = 8 - Integer.parseInt(splitData[2]);
						thepanel.BL2StarOBJ.intArrayY = 7 - Integer.parseInt(splitData[3]);
						if(splitData[4].equals("true")){
							thepanel.BL2StarOBJ.blnAlive = true;
						}else{
							thepanel.BL2StarOBJ.blnAlive = false;
						}
						thepanel.BL2StarOBJ.intX = thepanel.BL2StarOBJ.intArrayX * 70 + 13;
						thepanel.BL2StarOBJ.intY = thepanel.BL2StarOBJ.intArrayY * 70 + 105;
						strWBoard[thepanel.BL2StarOBJ.intArrayX][thepanel.BL2StarOBJ.intArrayY] = thepanel.BL2StarOBJ;
					}
					if(splitData[1].equals("BL3Star")){
						thepanel.BL3StarOBJ.intArrayX = 8 - Integer.parseInt(splitData[2]);
						thepanel.BL3StarOBJ.intArrayY = 7 - Integer.parseInt(splitData[3]);
						if(splitData[4].equals("true")){
							thepanel.BL3StarOBJ.blnAlive = true;
						}else{
							thepanel.BL3StarOBJ.blnAlive = false;
						}
						thepanel.BL3StarOBJ.intX = thepanel.BL3StarOBJ.intArrayX * 70 + 13;
						thepanel.BL3StarOBJ.intY = thepanel.BL3StarOBJ.intArrayY * 70 + 105;
						strWBoard[thepanel.BL3StarOBJ.intArrayX][thepanel.BL3StarOBJ.intArrayY] = thepanel.BL3StarOBJ;
					}
					if(splitData[1].equals("BL4Star")){
						thepanel.BL4StarOBJ.intArrayX = 8 - Integer.parseInt(splitData[2]);
						thepanel.BL4StarOBJ.intArrayY = 7 - Integer.parseInt(splitData[3]);
						if(splitData[4].equals("true")){
							thepanel.BL4StarOBJ.blnAlive = true;
						}else{
							thepanel.BL4StarOBJ.blnAlive = false;
						}
						thepanel.BL4StarOBJ.intX = thepanel.BL4StarOBJ.intArrayX * 70 + 13;
						thepanel.BL4StarOBJ.intY = thepanel.BL4StarOBJ.intArrayY * 70 + 105;
						strWBoard[thepanel.BL4StarOBJ.intArrayX][thepanel.BL4StarOBJ.intArrayY] = thepanel.BL4StarOBJ;
					}
					if(splitData[1].equals("BL5Star")){
						thepanel.BL5StarOBJ.intArrayX = 8 - Integer.parseInt(splitData[2]);
						thepanel.BL5StarOBJ.intArrayY = 7 - Integer.parseInt(splitData[3]);
						if(splitData[4].equals("true")){
							thepanel.BL5StarOBJ.blnAlive = true;
						}else{
							thepanel.BL5StarOBJ.blnAlive = false;
						}
						thepanel.BL5StarOBJ.intX = thepanel.BL5StarOBJ.intArrayX * 70 + 13;
						thepanel.BL5StarOBJ.intY = thepanel.BL5StarOBJ.intArrayY * 70 + 105;
						strWBoard[thepanel.BL5StarOBJ.intArrayX][thepanel.BL5StarOBJ.intArrayY] = thepanel.BL5StarOBJ;
					}
					if(splitData[1].equals("BLMajor")){
						thepanel.BLMajorOBJ.intArrayX = 8 - Integer.parseInt(splitData[2]);
						thepanel.BLMajorOBJ.intArrayY = 7 - Integer.parseInt(splitData[3]);
						if(splitData[4].equals("true")){
							thepanel.BLMajorOBJ.blnAlive = true;
						}else{
							thepanel.BLMajorOBJ.blnAlive = false;
						}
						thepanel.BLMajorOBJ.intX = thepanel.BLMajorOBJ.intArrayX * 70 + 13;
						thepanel.BLMajorOBJ.intY = thepanel.BLMajorOBJ.intArrayY * 70 + 105;
						strWBoard[thepanel.BLMajorOBJ.intArrayX][thepanel.BLMajorOBJ.intArrayY] = thepanel.BLMajorOBJ;
					}
					
					if(splitData[1].equals("WHFlag")){
						thepanel.WHFlagOBJ.intArrayX = 8 - Integer.parseInt(splitData[2]);
						//System.out.println(thepanel.WHFlagOBJ.intArrayX);
						thepanel.WHFlagOBJ.intArrayY = 7 - Integer.parseInt(splitData[3]);
						//System.out.println(thepanel.WHFlagOBJ.intArrayY);
						if(splitData[4].equals("true")){
							thepanel.WHFlagOBJ.blnAlive = true;
						}else{
							thepanel.WHFlagOBJ.blnAlive = false;
						}
						thepanel.WHFlagOBJ.intX = thepanel.WHFlagOBJ.intArrayX * 70 + 13;
						thepanel.WHFlagOBJ.intY = thepanel.WHFlagOBJ.intArrayY * 70 + 105;
						strBBoard[thepanel.WHFlagOBJ.intArrayX][thepanel.WHFlagOBJ.intArrayY] = thepanel.WHFlagOBJ;
					}
					if(splitData[1].equals("WHPrivate1")){
						thepanel.WHPrivate1OBJ.intArrayX = 8 - Integer.parseInt(splitData[2]);
						thepanel.WHPrivate1OBJ.intArrayY = 7 - Integer.parseInt(splitData[3]);
						if(splitData[4].equals("true")){
							thepanel.WHPrivate1OBJ.blnAlive = true;
						}else{
							thepanel.WHPrivate1OBJ.blnAlive = false;
						}
						thepanel.WHPrivate1OBJ.intX = thepanel.WHPrivate1OBJ.intArrayX * 70 + 13;
						thepanel.WHPrivate1OBJ.intY = thepanel.WHPrivate1OBJ.intArrayY * 70 + 105;
						strBBoard[thepanel.WHPrivate1OBJ.intArrayX][thepanel.WHPrivate1OBJ.intArrayY] = thepanel.WHPrivate1OBJ;
					}
					if(splitData[1].equals("WHPrivate2")){
						thepanel.WHPrivate2OBJ.intArrayX = 8 - Integer.parseInt(splitData[2]);
						thepanel.WHPrivate2OBJ.intArrayY = 7 - Integer.parseInt(splitData[3]);
						if(splitData[4].equals("true")){
							thepanel.WHPrivate2OBJ.blnAlive = true;
						}else{
							thepanel.WHPrivate2OBJ.blnAlive = false;
						}
						thepanel.WHPrivate2OBJ.intX = thepanel.WHPrivate2OBJ.intArrayX * 70 + 13;
						thepanel.WHPrivate2OBJ.intY = thepanel.WHPrivate2OBJ.intArrayY * 70 + 105;
						strBBoard[thepanel.WHPrivate2OBJ.intArrayX][thepanel.WHPrivate2OBJ.intArrayY] = thepanel.WHPrivate2OBJ;
					}
					if(splitData[1].equals("WHPrivate3")){
						thepanel.WHPrivate3OBJ.intArrayX = 8 - Integer.parseInt(splitData[2]);
						thepanel.WHPrivate3OBJ.intArrayY = 7 - Integer.parseInt(splitData[3]);
						if(splitData[4].equals("true")){
							thepanel.WHPrivate3OBJ.blnAlive = true;
						}else{
							thepanel.WHPrivate3OBJ.blnAlive = false;
						}
						thepanel.WHPrivate3OBJ.intX = thepanel.WHPrivate3OBJ.intArrayX * 70 + 13;
						thepanel.WHPrivate3OBJ.intY = thepanel.WHPrivate3OBJ.intArrayY * 70 + 105;
						strBBoard[thepanel.WHPrivate3OBJ.intArrayX][thepanel.WHPrivate3OBJ.intArrayY] = thepanel.WHPrivate3OBJ;
					}
					if(splitData[1].equals("WHPrivate4")){
						thepanel.WHPrivate4OBJ.intArrayX = 8 - Integer.parseInt(splitData[2]);
						thepanel.WHPrivate4OBJ.intArrayY = 7 - Integer.parseInt(splitData[3]);
						if(splitData[4].equals("true")){
							thepanel.WHPrivate4OBJ.blnAlive = true;
						}else{
							thepanel.WHPrivate4OBJ.blnAlive = false;
						}
						thepanel.WHPrivate4OBJ.intX = thepanel.WHPrivate4OBJ.intArrayX * 70 + 13;
						thepanel.WHPrivate4OBJ.intY = thepanel.WHPrivate4OBJ.intArrayY * 70 + 105;
						strBBoard[thepanel.WHPrivate4OBJ.intArrayX][thepanel.WHPrivate4OBJ.intArrayY] = thepanel.WHPrivate4OBJ;
					}
					if(splitData[1].equals("WHPrivate5")){
						thepanel.WHPrivate5OBJ.intArrayX = 8 - Integer.parseInt(splitData[2]);
						thepanel.WHPrivate5OBJ.intArrayY = 7 - Integer.parseInt(splitData[3]);
						if(splitData[4].equals("true")){
							thepanel.WHPrivate5OBJ.blnAlive = true;
						}else{
							thepanel.WHPrivate5OBJ.blnAlive = false;
						}
						thepanel.WHPrivate5OBJ.intX = thepanel.WHPrivate5OBJ.intArrayX * 70 + 13;
						thepanel.WHPrivate5OBJ.intY = thepanel.WHPrivate5OBJ.intArrayY * 70 + 105;
						strBBoard[thepanel.WHPrivate5OBJ.intArrayX][thepanel.WHPrivate5OBJ.intArrayY] = thepanel.WHPrivate5OBJ;
					}
					if(splitData[1].equals("WHPrivate6")){
						thepanel.WHPrivate6OBJ.intArrayX = 8 - Integer.parseInt(splitData[2]);
						thepanel.WHPrivate6OBJ.intArrayY = 7 - Integer.parseInt(splitData[3]);
						if(splitData[4].equals("true")){
							thepanel.WHPrivate6OBJ.blnAlive = true;
						}else{
							thepanel.WHPrivate6OBJ.blnAlive = false;
						}
						thepanel.WHPrivate6OBJ.intX = thepanel.WHPrivate6OBJ.intArrayX * 70 + 13;
						thepanel.WHPrivate6OBJ.intY = thepanel.WHPrivate6OBJ.intArrayY * 70 + 105;
						strBBoard[thepanel.WHPrivate6OBJ.intArrayX][thepanel.WHPrivate6OBJ.intArrayY] = thepanel.WHPrivate6OBJ;
					}
					if(splitData[1].equals("WHSpy1")){
						thepanel.WHSpy1OBJ.intArrayX = 8 - Integer.parseInt(splitData[2]);
						thepanel.WHSpy1OBJ.intArrayY = 7 - Integer.parseInt(splitData[3]);
						if(splitData[4].equals("true")){
							thepanel.WHSpy1OBJ.blnAlive = true;
						}else{
							thepanel.WHSpy1OBJ.blnAlive = false;
						}
						thepanel.WHSpy1OBJ.intX = thepanel.WHSpy1OBJ.intArrayX * 70 + 13;
						thepanel.WHSpy1OBJ.intY = thepanel.WHSpy1OBJ.intArrayY * 70 + 105;
						strBBoard[thepanel.WHSpy1OBJ.intArrayX][thepanel.WHSpy1OBJ.intArrayY] = thepanel.WHSpy1OBJ;
					}
					if(splitData[1].equals("WHSpy2")){
						thepanel.WHSpy2OBJ.intArrayX = 8 - Integer.parseInt(splitData[2]);
						thepanel.WHSpy2OBJ.intArrayY = 7 - Integer.parseInt(splitData[3]);
						if(splitData[4].equals("true")){
							thepanel.WHSpy2OBJ.blnAlive = true;
						}else{
							thepanel.WHSpy2OBJ.blnAlive = false;
						}
						thepanel.WHSpy2OBJ.intX = thepanel.WHSpy2OBJ.intArrayX * 70 + 13;
						thepanel.WHSpy2OBJ.intY = thepanel.WHSpy2OBJ.intArrayY * 70 + 105;
						strBBoard[thepanel.WHSpy2OBJ.intArrayX][thepanel.WHSpy2OBJ.intArrayY] = thepanel.WHSpy2OBJ;
					}
					if(splitData[1].equals("WHSergeant")){
						thepanel.WHSergeantOBJ.intArrayX = 8 - Integer.parseInt(splitData[2]);
						thepanel.WHSergeantOBJ.intArrayY = 7 - Integer.parseInt(splitData[3]);
						if(splitData[4].equals("true")){
							thepanel.WHSergeantOBJ.blnAlive = true;
						}else{
							thepanel.WHSergeantOBJ.blnAlive = false;
						}
						thepanel.WHSergeantOBJ.intX = thepanel.WHSergeantOBJ.intArrayX * 70 + 13;
						thepanel.WHSergeantOBJ.intY = thepanel.WHSergeantOBJ.intArrayY * 70 + 105;
						strBBoard[thepanel.WHSergeantOBJ.intArrayX][thepanel.WHSergeantOBJ.intArrayY] = thepanel.WHSergeantOBJ;
					}
					if(splitData[1].equals("WHLColonel")){
						thepanel.WHLColonelOBJ.intArrayX = 8 - Integer.parseInt(splitData[2]);
						thepanel.WHLColonelOBJ.intArrayY = 7 - Integer.parseInt(splitData[3]);
						if(splitData[4].equals("true")){
							thepanel.WHLColonelOBJ.blnAlive = true;
						}else{
							thepanel.WHLColonelOBJ.blnAlive = false;
						}
						thepanel.WHLColonelOBJ.intX = thepanel.WHLColonelOBJ.intArrayX * 70 + 13;
						thepanel.WHLColonelOBJ.intY = thepanel.WHLColonelOBJ.intArrayY * 70 + 105;
						strBBoard[thepanel.WHLColonelOBJ.intArrayX][thepanel.WHLColonelOBJ.intArrayY] = thepanel.WHLColonelOBJ;
					}
					if(splitData[1].equals("WH2Lieutenant")){
						thepanel.WH2LieutenantOBJ.intArrayX = 8 - Integer.parseInt(splitData[2]);
						thepanel.WH2LieutenantOBJ.intArrayY = 7 - Integer.parseInt(splitData[3]);
						if(splitData[4].equals("true")){
							thepanel.WH2LieutenantOBJ.blnAlive = true;
						}else{
							thepanel.WH2LieutenantOBJ.blnAlive = false;
						}
						thepanel.WH2LieutenantOBJ.intX = thepanel.WH2LieutenantOBJ.intArrayX * 70 + 13;
						thepanel.WH2LieutenantOBJ.intY = thepanel.WH2LieutenantOBJ.intArrayY * 70 + 105;
						strBBoard[thepanel.WH2LieutenantOBJ.intArrayX][thepanel.WH2LieutenantOBJ.intArrayY] = thepanel.WH2LieutenantOBJ;
					}
					if(splitData[1].equals("WH1Lieutenant")){
						thepanel.WH1LieutenantOBJ.intArrayX = 8 - Integer.parseInt(splitData[2]);
						thepanel.WH1LieutenantOBJ.intArrayY = 7 - Integer.parseInt(splitData[3]);
						if(splitData[4].equals("true")){
							thepanel.WH1LieutenantOBJ.blnAlive = true;
						}else{
							thepanel.WH1LieutenantOBJ.blnAlive = false;
						}
						thepanel.WH1LieutenantOBJ.intX = thepanel.WH1LieutenantOBJ.intArrayX * 70 + 13;
						thepanel.WH1LieutenantOBJ.intY = thepanel.WH1LieutenantOBJ.intArrayY * 70 + 105;
						strBBoard[thepanel.WH1LieutenantOBJ.intArrayX][thepanel.WH1LieutenantOBJ.intArrayY] = thepanel.WH1LieutenantOBJ;
					}
					if(splitData[1].equals("WHCaptain")){
						thepanel.WHCaptainOBJ.intArrayX = 8 - Integer.parseInt(splitData[2]);
						thepanel.WHCaptainOBJ.intArrayY = 7 - Integer.parseInt(splitData[3]);
						if(splitData[4].equals("true")){
							thepanel.WHCaptainOBJ.blnAlive = true;
						}else{
							thepanel.WHCaptainOBJ.blnAlive = false;
						}
						thepanel.WHCaptainOBJ.intX = thepanel.WHCaptainOBJ.intArrayX * 70 + 13;
						thepanel.WHCaptainOBJ.intY = thepanel.WHCaptainOBJ.intArrayY * 70 + 105;
						strBBoard[thepanel.WHCaptainOBJ.intArrayX][thepanel.WHCaptainOBJ.intArrayY] = thepanel.WHCaptainOBJ;
					}
					if(splitData[1].equals("WHColonel")){
						thepanel.WHColonelOBJ.intArrayX = 8 - Integer.parseInt(splitData[2]);
						thepanel.WHColonelOBJ.intArrayY = 7 - Integer.parseInt(splitData[3]);
						if(splitData[4].equals("true")){
							thepanel.WHColonelOBJ.blnAlive = true;
						}else{
							thepanel.WHColonelOBJ.blnAlive = false;
						}
						thepanel.WHColonelOBJ.intX = thepanel.WHColonelOBJ.intArrayX * 70 + 13;
						thepanel.WHColonelOBJ.intY = thepanel.WHColonelOBJ.intArrayY * 70 + 105;
						strBBoard[thepanel.WHColonelOBJ.intArrayX][thepanel.WHColonelOBJ.intArrayY] = thepanel.WHColonelOBJ;
					}
					if(splitData[1].equals("WH1Star")){
						thepanel.WH1StarOBJ.intArrayX = 8 - Integer.parseInt(splitData[2]);
						thepanel.WH1StarOBJ.intArrayY = 7 - Integer.parseInt(splitData[3]);
						if(splitData[4].equals("true")){
							thepanel.WH1StarOBJ.blnAlive = true;
						}else{
							thepanel.WH1StarOBJ.blnAlive = false;
						}
						thepanel.WH1StarOBJ.intX = thepanel.WH1StarOBJ.intArrayX * 70 + 10;
						thepanel.WH1StarOBJ.intY = thepanel.WH1StarOBJ.intArrayY * 70 + 80;
						strBBoard[thepanel.WH1StarOBJ.intArrayX][thepanel.WH1StarOBJ.intArrayY] = thepanel.WH1StarOBJ;
					}
					if(splitData[1].equals("WH2Star")){
						thepanel.WH2StarOBJ.intArrayX = 8 - Integer.parseInt(splitData[2]);
						thepanel.WH2StarOBJ.intArrayY = 7 - Integer.parseInt(splitData[3]);
						if(splitData[4].equals("true")){
							thepanel.WH2StarOBJ.blnAlive = true;
						}else{
							thepanel.WH2StarOBJ.blnAlive = false;
						}
						thepanel.WH2StarOBJ.intX = thepanel.WH2StarOBJ.intArrayX * 70 + 13;
						thepanel.WH2StarOBJ.intY = thepanel.WH2StarOBJ.intArrayY * 70 + 105;
						strBBoard[thepanel.WH2StarOBJ.intArrayX][thepanel.WH2StarOBJ.intArrayY] = thepanel.WH2StarOBJ;
					}
					if(splitData[1].equals("WH3Star")){
						thepanel.WH3StarOBJ.intArrayX = 8 - Integer.parseInt(splitData[2]);
						thepanel.WH3StarOBJ.intArrayY = 7 - Integer.parseInt(splitData[3]);
						if(splitData[4].equals("true")){
							thepanel.WH3StarOBJ.blnAlive = true;
						}else{
							thepanel.WH3StarOBJ.blnAlive = false;
						}
						thepanel.WH3StarOBJ.intX = thepanel.WH3StarOBJ.intArrayX * 70 + 13;
						thepanel.WH3StarOBJ.intY = thepanel.WH3StarOBJ.intArrayY * 70 + 105;
						strBBoard[thepanel.WH3StarOBJ.intArrayX][thepanel.WH3StarOBJ.intArrayY] = thepanel.WH3StarOBJ;
					}
					if(splitData[1].equals("WH4Star")){
						thepanel.WH4StarOBJ.intArrayX = 8 - Integer.parseInt(splitData[2]);
						thepanel.WH4StarOBJ.intArrayY = 7 - Integer.parseInt(splitData[3]);
						if(splitData[4].equals("true")){
							thepanel.WH4StarOBJ.blnAlive = true;
						}else{
							thepanel.WH4StarOBJ.blnAlive = false;
						}
						thepanel.WH4StarOBJ.intX = thepanel.WH4StarOBJ.intArrayX * 70 + 13;
						thepanel.WH4StarOBJ.intY = thepanel.WH4StarOBJ.intArrayY * 70 + 105;
						strBBoard[thepanel.WH4StarOBJ.intArrayX][thepanel.WH4StarOBJ.intArrayY] = thepanel.WH4StarOBJ;
					}
					if(splitData[1].equals("WH5Star")){
						thepanel.WH5StarOBJ.intArrayX = 8 - Integer.parseInt(splitData[2]);
						thepanel.WH5StarOBJ.intArrayY = 7 - Integer.parseInt(splitData[3]);
						if(splitData[4].equals("true")){
							thepanel.WH5StarOBJ.blnAlive = true;
						}else{
							thepanel.WH5StarOBJ.blnAlive = false;
						}
						thepanel.WH5StarOBJ.intX = thepanel.WH5StarOBJ.intArrayX * 70 + 13;
						thepanel.WH5StarOBJ.intY = thepanel.WH5StarOBJ.intArrayY * 70 + 105;
						strBBoard[thepanel.WH5StarOBJ.intArrayX][thepanel.WH5StarOBJ.intArrayY] = thepanel.WH5StarOBJ;
					}
					if(splitData[1].equals("WHMajor")){
						thepanel.WHMajorOBJ.intArrayX = 8 - Integer.parseInt(splitData[2]);
						if(splitData[4].equals("true")){
							thepanel.WHMajorOBJ.blnAlive = true;
						}else{
							thepanel.WHMajorOBJ.blnAlive = false;
						}
						thepanel.WHMajorOBJ.intArrayY = 7 - Integer.parseInt(splitData[3]);
						thepanel.WHMajorOBJ.intX = thepanel.WHMajorOBJ.intArrayX * 70 + 13;
						thepanel.WHMajorOBJ.intY = thepanel.WHMajorOBJ.intArrayY * 70 + 105;
						strBBoard[thepanel.WHMajorOBJ.intArrayX][thepanel.WHMajorOBJ.intArrayY] = thepanel.WHMajorOBJ;
					}
				}
				
			}
			
		}
		
		if(evt.getSource() == sReadyButton){
			//when server has shown their friend their IP address and is ready to set up their pieces
			addAddress.setVisible(false);
			sReadyButton.setVisible(false);
			
			thepanel.blnPlay = true;
			thepanel.blnServerView = true;
			
			chatBoxReceive.setVisible(true);
			chatBoxSend.setVisible(true);
			chatBoxScroll.setVisible(true);
			
			blnSettingUp = true;
			
			bDoneSetUp.setVisible(true);
			
			BriefRules.setVisible(true);
			BriefRulesScroll.setVisible(true);
			
			theframe.requestFocus();
			
		}
		
		if(evt.getSource() == bDoneSetUp){
			//When server side has set up their pieces
			thepanel.intReady = thepanel.intReady + 1;
			ssm.sendText("Ready: " + thepanel.intReady);
			bDoneSetUp.setVisible(false);
			System.out.println("Ready: " + thepanel.intReady);
			if(thepanel.intReady == 2){
				//if both players are ready, initialize the turn to White; white plays first
				thepanel.strWhosTurn = "White";
			}
			
			blnSettingUp = false;
			
			this.sendAllBlack();
			//calling method to send all pieces server has set up
			
		}
		if(evt.getSource() == wDoneSetUp){
			//When client side has set up their pieces
			thepanel.intReady = thepanel.intReady + 1;
			ssm.sendText("Ready: " + thepanel.intReady);
			wDoneSetUp.setVisible(false);
			System.out.println("Ready: " + thepanel.intReady);
			if(thepanel.intReady == 2){
				//If both players are ready, intialize the turn to White; white plays first
				thepanel.strWhosTurn = "White";
			}
			
			blnSettingUp = false;
			
			this.sendAllWhite();
			//calling method to send all pieces client has set up
		}
		

	}
	
// - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -	
	
	// METHODS - KEYLISTENER (Key Released)
	public void keyReleased(KeyEvent evt){
	}
	
	// METHODS - KEYLISTENER (Key Pressed)
	public void keyPressed(KeyEvent evt){
		System.out.println(evt.getKeyCode());
		//When a piece has been clicked during gameplay and the client wants to move it by pressing on an arrow key
		
		if(blnClickBL1L){
			if(evt.getKeyCode() == 38){
				moveForward(thepanel.BL1LieutenantOBJ);
			}else if(evt.getKeyCode() == 37){
				moveLeft(thepanel.BL1LieutenantOBJ);
			}else if(evt.getKeyCode() == 40){
				moveBackward(thepanel.BL1LieutenantOBJ);
			}else if(evt.getKeyCode() == 39){
				moveRight(thepanel.BL1LieutenantOBJ);
			}
			blnClickBL1L = false;
		}else if(blnClickBL2L){
			if(evt.getKeyCode() == 38){
				moveForward(thepanel.BL2LieutenantOBJ);
			}else if(evt.getKeyCode() == 37){
				moveLeft(thepanel.BL2LieutenantOBJ);
			}else if(evt.getKeyCode() == 40){
				moveBackward(thepanel.BL2LieutenantOBJ);
			}else if(evt.getKeyCode() == 39){
				moveRight(thepanel.BL2LieutenantOBJ);
			}
			blnClickBL2L = false;
		}else if(blnClickBLCap){
			if(evt.getKeyCode() == 38){
				moveForward(thepanel.BLCaptainOBJ);
			}else if(evt.getKeyCode() == 37){
				moveLeft(thepanel.BLCaptainOBJ);
			}else if(evt.getKeyCode() == 40){
				moveBackward(thepanel.BLCaptainOBJ);
			}else if(evt.getKeyCode() == 39){
				moveRight(thepanel.BLCaptainOBJ);
			}
			blnClickBLCap = false;
		}else if(blnClickBLCol){
			if(evt.getKeyCode() == 38){
				moveForward(thepanel.BLColonelOBJ);
			}else if(evt.getKeyCode() == 37){
				moveLeft(thepanel.BLColonelOBJ);
			}else if(evt.getKeyCode() == 40){
				moveBackward(thepanel.BLColonelOBJ);
			}else if(evt.getKeyCode() == 39){
				moveRight(thepanel.BLColonelOBJ);
			}
			blnClickBLCol = false;
		}else if(blnClickBL1S){
			if(evt.getKeyCode() == 38){
				moveForward(thepanel.BL1StarOBJ);
			}else if(evt.getKeyCode() == 37){
				moveLeft(thepanel.BL1StarOBJ);
			}else if(evt.getKeyCode() == 40){
				moveBackward(thepanel.BL1StarOBJ);
			}else if(evt.getKeyCode() == 39){
				moveRight(thepanel.BL1StarOBJ);
			}
			blnClickBL1S = false;
		}else if(blnClickBL2S){
			if(evt.getKeyCode() == 38){
				moveForward(thepanel.BL2StarOBJ);
			}else if(evt.getKeyCode() == 37){
				moveLeft(thepanel.BL2StarOBJ);
			}else if(evt.getKeyCode() == 40){
				moveBackward(thepanel.BL2StarOBJ);
			}else if(evt.getKeyCode() == 39){
				moveRight(thepanel.BL2StarOBJ);
			}
			blnClickBL2S = false;
		}else if(blnClickBL3S){
			if(evt.getKeyCode() == 38){
				moveForward(thepanel.BL3StarOBJ);
			}else if(evt.getKeyCode() == 37){
				moveLeft(thepanel.BL3StarOBJ);
			}else if(evt.getKeyCode() == 40){
				moveBackward(thepanel.BL3StarOBJ);
			}else if(evt.getKeyCode() == 39){
				moveRight(thepanel.BL3StarOBJ);
			}
			blnClickBL3S = false;
		}else if(blnClickBL4S){
			if(evt.getKeyCode() == 38){
				moveForward(thepanel.BL4StarOBJ);
			}else if(evt.getKeyCode() == 37){
				moveLeft(thepanel.BL4StarOBJ);
			}else if(evt.getKeyCode() == 40){
				moveBackward(thepanel.BL4StarOBJ);
			}else if(evt.getKeyCode() == 39){
				moveRight(thepanel.BL4StarOBJ);
			}
			blnClickBL4S = false;
		}else if(blnClickBL5S){
			if(evt.getKeyCode() == 38){
				moveForward(thepanel.BL5StarOBJ);
			}else if(evt.getKeyCode() == 37){
				moveLeft(thepanel.BL5StarOBJ);
			}else if(evt.getKeyCode() == 40){
				moveBackward(thepanel.BL5StarOBJ);
			}else if(evt.getKeyCode() == 39){
				moveRight(thepanel.BL5StarOBJ);
			}
			blnClickBL5S = false;
		}else if(blnClickBLM){
			if(evt.getKeyCode() == 38){
				moveForward(thepanel.BLMajorOBJ);
			}else if(evt.getKeyCode() == 37){
				moveLeft(thepanel.BLMajorOBJ);
			}else if(evt.getKeyCode() == 40){
				moveBackward(thepanel.BLMajorOBJ);
			}else if(evt.getKeyCode() == 39){
				moveRight(thepanel.BLMajorOBJ);
			}
			blnClickBLM = false;
		}else if(blnClickBLF){
			if(evt.getKeyCode() == 38){
				moveForward(thepanel.BLFlagOBJ);
			}else if(evt.getKeyCode() == 37){
				moveLeft(thepanel.BLFlagOBJ);
			}else if(evt.getKeyCode() == 40){
				moveBackward(thepanel.BLFlagOBJ);
			}else if(evt.getKeyCode() == 39){
				moveRight(thepanel.BLFlagOBJ);
			}
			blnClickBLF = false;
		}else if(blnClickBLLC){
			if(evt.getKeyCode() == 38){
				moveForward(thepanel.BLLColonelOBJ);
			}else if(evt.getKeyCode() == 37){
				moveLeft(thepanel.BLLColonelOBJ);
			}else if(evt.getKeyCode() == 40){
				moveBackward(thepanel.BLLColonelOBJ);
			}else if(evt.getKeyCode() == 39){
				moveRight(thepanel.BLLColonelOBJ);
			}
			blnClickBLLC = false;
		}else if(blnClickBLSer){
			if(evt.getKeyCode() == 38){
				moveForward(thepanel.BLSergeantOBJ);
			}else if(evt.getKeyCode() == 37){
				moveLeft(thepanel.BLSergeantOBJ);
			}else if(evt.getKeyCode() == 40){
				moveBackward(thepanel.BLSergeantOBJ);
			}else if(evt.getKeyCode() == 39){
				moveRight(thepanel.BLSergeantOBJ);
			}
			blnClickBLSer = false;
		}else if(blnClickBLSpy1){
			if(evt.getKeyCode() == 38){
				moveForward(thepanel.BLSpy1OBJ);
			}else if(evt.getKeyCode() == 37){
				moveLeft(thepanel.BLSpy1OBJ);
			}else if(evt.getKeyCode() == 40){
				moveBackward(thepanel.BLSpy1OBJ);
			}else if(evt.getKeyCode() == 39){
				moveRight(thepanel.BLSpy1OBJ);
			}
			blnClickBLSpy1 = false;
		}else if(blnClickBLSpy2){
			if(evt.getKeyCode() == 38){
				moveForward(thepanel.BLSpy2OBJ);
			}else if(evt.getKeyCode() == 37){
				moveLeft(thepanel.BLSpy2OBJ);
			}else if(evt.getKeyCode() == 40){
				moveBackward(thepanel.BLSpy2OBJ);
			}else if(evt.getKeyCode() == 39){
				moveRight(thepanel.BLSpy2OBJ);
			}
			blnClickBLSpy2 = false;
		}else if(blnClickBLP1){
			if(evt.getKeyCode() == 38){
				moveForward(thepanel.BLPrivate1OBJ);
			}else if(evt.getKeyCode() == 37){
				moveLeft(thepanel.BLPrivate1OBJ);
			}else if(evt.getKeyCode() == 40){
				moveBackward(thepanel.BLPrivate1OBJ);
			}else if(evt.getKeyCode() == 39){
				moveRight(thepanel.BLPrivate1OBJ);
			}
			blnClickBLP1 = false;
		}else if(blnClickBLP2){
			if(evt.getKeyCode() == 38){
				moveForward(thepanel.BLPrivate2OBJ);
			}else if(evt.getKeyCode() == 37){
				moveLeft(thepanel.BLPrivate2OBJ);
			}else if(evt.getKeyCode() == 40){
				moveBackward(thepanel.BLPrivate2OBJ);
			}else if(evt.getKeyCode() == 39){
				moveRight(thepanel.BLPrivate2OBJ);
			}
			blnClickBLP2 = false;
		}else if(blnClickBLP3){
			if(evt.getKeyCode() == 38){
				moveForward(thepanel.BLPrivate3OBJ);
			}else if(evt.getKeyCode() == 37){
				moveLeft(thepanel.BLPrivate3OBJ);
			}else if(evt.getKeyCode() == 40){
				moveBackward(thepanel.BLPrivate3OBJ);
			}else if(evt.getKeyCode() == 39){
				moveRight(thepanel.BLPrivate3OBJ);
			}
			blnClickBLP3 = false;
		}else if(blnClickBLP4){
			if(evt.getKeyCode() == 38){
				moveForward(thepanel.BLPrivate4OBJ);
			}else if(evt.getKeyCode() == 37){
				moveLeft(thepanel.BLPrivate4OBJ);
			}else if(evt.getKeyCode() == 40){
				moveBackward(thepanel.BLPrivate4OBJ);
			}else if(evt.getKeyCode() == 39){
				moveRight(thepanel.BLPrivate4OBJ);
			}
			blnClickBLP4 = false;
		}else if(blnClickBLP5){
			if(evt.getKeyCode() == 38){
				moveForward(thepanel.BLPrivate5OBJ);
			}else if(evt.getKeyCode() == 37){
				moveLeft(thepanel.BLPrivate5OBJ);
			}else if(evt.getKeyCode() == 40){
				moveBackward(thepanel.BLPrivate5OBJ);
			}else if(evt.getKeyCode() == 39){
				moveRight(thepanel.BLPrivate5OBJ);
			}
			blnClickBLP5 = false;
		}else if(blnClickBLP6){
			if(evt.getKeyCode() == 38){
				moveForward(thepanel.BLPrivate6OBJ);
			}else if(evt.getKeyCode() == 37){
				moveLeft(thepanel.BLPrivate6OBJ);
			}else if(evt.getKeyCode() == 40){
				moveBackward(thepanel.BLPrivate6OBJ);
			}else if(evt.getKeyCode() == 39){
				moveRight(thepanel.BLPrivate6OBJ);
			}
			blnClickBLP6 = false;
		}else if(blnClickWH1L){
			if(evt.getKeyCode() == 38){
				moveForward(thepanel.WH1LieutenantOBJ);
			}else if(evt.getKeyCode() == 37){
				moveLeft(thepanel.WH1LieutenantOBJ);
			}else if(evt.getKeyCode() == 40){
				moveBackward(thepanel.WH1LieutenantOBJ);
			}else if(evt.getKeyCode() == 39){
				moveRight(thepanel.WH1LieutenantOBJ);
			}
			blnClickWH1L = false;
		}else if(blnClickWH2L){
			if(evt.getKeyCode() == 38){
				moveForward(thepanel.WH2LieutenantOBJ);
			}else if(evt.getKeyCode() == 37){
				moveLeft(thepanel.WH2LieutenantOBJ);
			}else if(evt.getKeyCode() == 40){
				moveBackward(thepanel.WH2LieutenantOBJ);
			}else if(evt.getKeyCode() == 39){
				moveRight(thepanel.WH2LieutenantOBJ);
			}
			blnClickWH2L = false;
		}else if(blnClickWHCap){
			if(evt.getKeyCode() == 38){
				moveForward(thepanel.WHCaptainOBJ);
			}else if(evt.getKeyCode() == 37){
				moveLeft(thepanel.WHCaptainOBJ);
			}else if(evt.getKeyCode() == 40){
				moveBackward(thepanel.WHCaptainOBJ);
			}else if(evt.getKeyCode() == 39){
				moveRight(thepanel.WHCaptainOBJ);
			}
			blnClickWHCap = false;
		}else if(blnClickWHCol){
			if(evt.getKeyCode() == 38){
				moveForward(thepanel.WHColonelOBJ);
			}else if(evt.getKeyCode() == 37){
				moveLeft(thepanel.WHColonelOBJ);
			}else if(evt.getKeyCode() == 40){
				moveBackward(thepanel.WHColonelOBJ);
			}else if(evt.getKeyCode() == 39){
				moveRight(thepanel.WHColonelOBJ);
			}
			blnClickWHCol = false;
		}else if(blnClickWH1S){
			if(evt.getKeyCode() == 38){
				moveForward(thepanel.WH1StarOBJ);
			}else if(evt.getKeyCode() == 37){
				moveLeft(thepanel.WH1StarOBJ);
			}else if(evt.getKeyCode() == 40){
				moveBackward(thepanel.WH1StarOBJ);
			}else if(evt.getKeyCode() == 39){
				moveRight(thepanel.WH1StarOBJ);
			}
			blnClickWH1S = false;
		}else if(blnClickWH2S){
			if(evt.getKeyCode() == 38){
				moveForward(thepanel.WH2StarOBJ);
			}else if(evt.getKeyCode() == 37){
				moveLeft(thepanel.WH2StarOBJ);
			}else if(evt.getKeyCode() == 40){
				moveBackward(thepanel.WH2StarOBJ);
			}else if(evt.getKeyCode() == 39){
				moveRight(thepanel.WH2StarOBJ);
			}
			blnClickWH2S = false;
		}else if(blnClickWH3S){
			if(evt.getKeyCode() == 38){
				moveForward(thepanel.WH3StarOBJ);
			}else if(evt.getKeyCode() == 37){
				moveLeft(thepanel.WH3StarOBJ);
			}else if(evt.getKeyCode() == 40){
				moveBackward(thepanel.WH3StarOBJ);
			}else if(evt.getKeyCode() == 39){
				moveRight(thepanel.WH3StarOBJ);
			}
			blnClickWH3S = false;
		}else if(blnClickWH4S){
			if(evt.getKeyCode() == 38){
				moveForward(thepanel.WH4StarOBJ);
			}else if(evt.getKeyCode() == 37){
				moveLeft(thepanel.WH4StarOBJ);
			}else if(evt.getKeyCode() == 40){
				moveBackward(thepanel.WH4StarOBJ);
			}else if(evt.getKeyCode() == 39){
				moveRight(thepanel.WH4StarOBJ);
			}
			blnClickWH4S = false;
		}else if(blnClickWH5S){
			if(evt.getKeyCode() == 38){
				moveForward(thepanel.WH5StarOBJ);
			}else if(evt.getKeyCode() == 37){
				moveLeft(thepanel.WH5StarOBJ);
			}else if(evt.getKeyCode() == 40){
				moveBackward(thepanel.WH5StarOBJ);
			}else if(evt.getKeyCode() == 39){
				moveRight(thepanel.WH5StarOBJ);
			}
			blnClickWH5S = false;
		}else if(blnClickWHM){
			if(evt.getKeyCode() == 38){
				moveForward(thepanel.WHMajorOBJ);
			}else if(evt.getKeyCode() == 37){
				moveLeft(thepanel.WHMajorOBJ);
			}else if(evt.getKeyCode() == 40){
				moveBackward(thepanel.WHMajorOBJ);
			}else if(evt.getKeyCode() == 39){
				moveRight(thepanel.WHMajorOBJ);
			}
			blnClickWHM = false;
		}else if(blnClickWHF){
			if(evt.getKeyCode() == 38){
				moveForward(thepanel.WHFlagOBJ);
			}else if(evt.getKeyCode() == 37){
				moveLeft(thepanel.WHFlagOBJ);
			}else if(evt.getKeyCode() == 40){
				moveBackward(thepanel.WHFlagOBJ);
			}else if(evt.getKeyCode() == 39){
				moveRight(thepanel.WHFlagOBJ);
			}
			blnClickWHF = false;
		}else if(blnClickWHLC){
			if(evt.getKeyCode() == 38){
				moveForward(thepanel.WHLColonelOBJ);
			}else if(evt.getKeyCode() == 37){
				moveLeft(thepanel.WHLColonelOBJ);
			}else if(evt.getKeyCode() == 40){
				moveBackward(thepanel.WHLColonelOBJ);
			}else if(evt.getKeyCode() == 39){
				moveRight(thepanel.WHLColonelOBJ);
			}
			blnClickWHLC = false;
		}else if(blnClickWHSer){
			if(evt.getKeyCode() == 38){
				moveForward(thepanel.WHSergeantOBJ);
			}else if(evt.getKeyCode() == 37){
				moveLeft(thepanel.WHSergeantOBJ);
			}else if(evt.getKeyCode() == 40){
				moveBackward(thepanel.WHSergeantOBJ);
			}else if(evt.getKeyCode() == 39){
				moveRight(thepanel.WHSergeantOBJ);
			}
			blnClickWHSer = false;
		}else if(blnClickWHSpy1){
			if(evt.getKeyCode() == 38){
				moveForward(thepanel.WHSpy1OBJ);
			}else if(evt.getKeyCode() == 37){
				moveLeft(thepanel.WHSpy1OBJ);
			}else if(evt.getKeyCode() == 40){
				moveBackward(thepanel.WHSpy1OBJ);
			}else if(evt.getKeyCode() == 39){
				moveRight(thepanel.WHSpy1OBJ);
			}
			blnClickWHSpy1 = false;
		}else if(blnClickWHSpy2){
			if(evt.getKeyCode() == 38){
				moveForward(thepanel.WHSpy2OBJ);
			}else if(evt.getKeyCode() == 37){
				moveLeft(thepanel.WHSpy2OBJ);
			}else if(evt.getKeyCode() == 40){
				moveBackward(thepanel.WHSpy2OBJ);
			}else if(evt.getKeyCode() == 39){
				moveRight(thepanel.WHSpy2OBJ);
			}
			blnClickWHSpy2 = false;
		}else if(blnClickWHP1){
			if(evt.getKeyCode() == 38){
				moveForward(thepanel.WHPrivate1OBJ);
			}else if(evt.getKeyCode() == 37){
				moveLeft(thepanel.WHPrivate1OBJ);
			}else if(evt.getKeyCode() == 40){
				moveBackward(thepanel.WHPrivate1OBJ);
			}else if(evt.getKeyCode() == 39){
				moveRight(thepanel.WHPrivate1OBJ);
			}
			blnClickWHP1 = false;
		}else if(blnClickWHP2){
			if(evt.getKeyCode() == 38){
				moveForward(thepanel.WHPrivate2OBJ);
			}else if(evt.getKeyCode() == 37){
				moveLeft(thepanel.WHPrivate2OBJ);
			}else if(evt.getKeyCode() == 40){
				moveBackward(thepanel.WHPrivate2OBJ);
			}else if(evt.getKeyCode() == 39){
				moveRight(thepanel.WHPrivate2OBJ);
			}
			blnClickWHP2 = false;
		}else if(blnClickWHP3){
			if(evt.getKeyCode() == 38){
				moveForward(thepanel.WHPrivate3OBJ);
			}else if(evt.getKeyCode() == 37){
				moveLeft(thepanel.WHPrivate3OBJ);
			}else if(evt.getKeyCode() == 40){
				moveBackward(thepanel.WHPrivate3OBJ);
			}else if(evt.getKeyCode() == 39){
				moveRight(thepanel.WHPrivate3OBJ);
			}
			blnClickWHP3 = false;
		}else if(blnClickWHP4){
			if(evt.getKeyCode() == 38){
				moveForward(thepanel.WHPrivate4OBJ);
			}else if(evt.getKeyCode() == 37){
				moveLeft(thepanel.WHPrivate4OBJ);
			}else if(evt.getKeyCode() == 40){
				moveBackward(thepanel.WHPrivate4OBJ);
			}else if(evt.getKeyCode() == 39){
				moveRight(thepanel.WHPrivate4OBJ);
			}
			blnClickWHP4 = false;
		}else if(blnClickWHP5){
			if(evt.getKeyCode() == 38){
				moveForward(thepanel.WHPrivate5OBJ);
			}else if(evt.getKeyCode() == 37){
				moveLeft(thepanel.WHPrivate5OBJ);
			}else if(evt.getKeyCode() == 40){
				moveBackward(thepanel.WHPrivate5OBJ);
			}else if(evt.getKeyCode() == 39){
				moveRight(thepanel.WHPrivate5OBJ);
			}
			blnClickWHP5 = false;
		}else if(blnClickWHP6){
			if(evt.getKeyCode() == 38){
				moveForward(thepanel.WHPrivate6OBJ);
			}else if(evt.getKeyCode() == 37){
				moveLeft(thepanel.WHPrivate6OBJ);
			}else if(evt.getKeyCode() == 40){
				moveBackward(thepanel.WHPrivate6OBJ);
			}else if(evt.getKeyCode() == 39){
				moveRight(thepanel.WHPrivate6OBJ);
			}
			blnClickBLP6 = false;
		}
		
		this.changeTurn();
		//calling on method to change to other player's turn
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
			//Setting up drag and drop release to follow mouse's x and y coordinates
			//Piece will "snap" to the space the cursor is in
			//Piece is then set in the array
			if(blnMoveWH1L){
				try{strWBoard[thepanel.WH1LieutenantOBJ.intArrayX][thepanel.WH1LieutenantOBJ.intArrayY] = null;
				}catch(ArrayIndexOutOfBoundsException e){
				}
				intResultX = evt.getX();
				intResultY = evt.getY();
				intResultX = intResultX - 10;
				if(intResultX >= 70){
					intModX = intResultX/70;
				}else{
					intModX = 0;
				}
				intResultX = intModX * 70 + 13;
				thepanel.WH1LieutenantOBJ.intX = intResultX;
				intResultY = intResultY - 80;
				if(intResultY >= 70){
					intModY = intResultY/70;
				}else{
					intModY = 0;
				}
				intResultY = intModY * 70 + 105;
				thepanel.WH1LieutenantOBJ.intY = intResultY;
				blnMoveWH1L = false;
				strWBoard[intModX][intModY] = thepanel.WH1LieutenantOBJ;
				thepanel.WH1LieutenantOBJ.intArrayX = intModX;
				thepanel.WH1LieutenantOBJ.intArrayY = intModY;
			}
			if(blnMoveWH2L){
				try{strWBoard[thepanel.WH2LieutenantOBJ.intArrayX][thepanel.WH2LieutenantOBJ.intArrayY] = null;
				}catch(ArrayIndexOutOfBoundsException e){
				}
				intResultX = evt.getX();
				intResultY = evt.getY();
				intResultX = intResultX - 10;
				if(intResultX >= 70){
					intModX = intResultX/70;
				}else{
					intModX = 0;
				}
				intResultX = intModX * 70 + 13;
				thepanel.WH2LieutenantOBJ.intX = intResultX;
				intResultY = intResultY - 80;
				if(intResultY >= 70){
					intModY = intResultY/70;
				}else{
					intModY = 0;
				}
				intResultY = intModY * 70 + 105;
				thepanel.WH2LieutenantOBJ.intY = intResultY;
				blnMoveWH2L = false;
				strWBoard[intModX][intModY] = thepanel.WH2LieutenantOBJ;
				thepanel.WH2LieutenantOBJ.intArrayX = intModX;
				thepanel.WH2LieutenantOBJ.intArrayY = intModY;
			}
			if(blnMoveWHCap){
				try{strWBoard[thepanel.WHCaptainOBJ.intArrayX][thepanel.WHCaptainOBJ.intArrayY] = null;
				}catch(ArrayIndexOutOfBoundsException e){
				}
				intResultX = evt.getX();
				intResultY = evt.getY();
				intResultX = intResultX - 10;
				if(intResultX >= 70){
					intModX = intResultX/70;
				}else{
					intModX = 0;
				}
				intResultX = intModX * 70 + 13;
				thepanel.WHCaptainOBJ.intX = intResultX;
				intResultY = intResultY - 80;
				if(intResultY >= 70){
					intModY = intResultY/70;
				}else{
					intModY = 0;
				}
				intResultY = intModY * 70 + 105;
				thepanel.WHCaptainOBJ.intY = intResultY;
				blnMoveWHCap = false;
				strWBoard[8-intModX][intModY] = thepanel.WHCaptainOBJ;
				thepanel.WHCaptainOBJ.intArrayX = intModX;
				thepanel.WHCaptainOBJ.intArrayY = intModY;
			}
			if(blnMoveWHCol){
				try{strWBoard[thepanel.WHColonelOBJ.intArrayX][thepanel.WHColonelOBJ.intArrayY] = null;
				}catch(ArrayIndexOutOfBoundsException e){
				}
				intResultX = evt.getX();
				intResultY = evt.getY();
				intResultX = intResultX - 10;
				if(intResultX >= 70){
					intModX = intResultX/70;
				}else{
					intModX = 0;
				}
				intResultX = intModX * 70 + 13;
				thepanel.WHColonelOBJ.intX = intResultX;
				intResultY = intResultY - 80;
				if(intResultY >= 70){
					intModY = intResultY/70;
				}else{
					intModY = 0;
				}
				intResultY = intModY * 70 + 105;
				thepanel.WHColonelOBJ.intY = intResultY;
				blnMoveWHCol = false;
				strWBoard[intModX][intModY] = thepanel.WHColonelOBJ;
				thepanel.WHColonelOBJ.intArrayX = intModX;
				thepanel.WHColonelOBJ.intArrayY = intModY;
			}
			if(blnMoveWH1S){
				try{strWBoard[thepanel.WH1StarOBJ.intArrayX][thepanel.WH1StarOBJ.intArrayY] = null;
				}catch(ArrayIndexOutOfBoundsException e){
				}
				intResultX = evt.getX();
				intResultY = evt.getY();
				intResultX = intResultX - 10;
				if(intResultX >= 70){
					intModX = intResultX/70;
				}else{
					intModX = 0;
				}
				intResultX = intModX * 70 + 13;
				thepanel.WH1StarOBJ.intX = intResultX;
				intResultY = intResultY - 80;
				if(intResultY >= 70){
					intModY = intResultY/70;
				}else{
					intModY = 0;
				}
				intResultY = intModY * 70 + 105;
				thepanel.WH1StarOBJ.intY = intResultY;
				blnMoveWH1S = false;
				strWBoard[intModX][intModY] = thepanel.WH1StarOBJ;
				thepanel.WH1StarOBJ.intArrayX = intModX;
				thepanel.WH1StarOBJ.intArrayY = intModY;
			}
			if(blnMoveWH2S){
				try{strWBoard[thepanel.WH2StarOBJ.intArrayX][thepanel.WH2StarOBJ.intArrayY] = null;
				}catch(ArrayIndexOutOfBoundsException e){
				}
				intResultX = evt.getX();
				intResultY = evt.getY();
				intResultX = intResultX - 10;
				if(intResultX >= 70){
					intModX = intResultX/70;
				}else{
					intModX = 0;
				}
				intResultX = intModX * 70 + 13;
				thepanel.WH2StarOBJ.intX = intResultX;
				intResultY = intResultY - 80;
				if(intResultY >= 70){
					intModY = intResultY/70;
				}else{
					intModY = 0;
				}
				intResultY = intModY * 70 + 105;
				thepanel.WH2StarOBJ.intY = intResultY;
				blnMoveWH2S = false;
				strWBoard[intModX][intModY] = thepanel.WH2StarOBJ;
				thepanel.WH2StarOBJ.intArrayX = intModX;
				thepanel.WH2StarOBJ.intArrayY = intModY;
			}
			if(blnMoveWH3S){
				try{strWBoard[thepanel.WH3StarOBJ.intArrayX][thepanel.WH3StarOBJ.intArrayY] = null;
				}catch(ArrayIndexOutOfBoundsException e){
				}
				intResultX = evt.getX();
				intResultY = evt.getY();
				intResultX = intResultX - 10;
				if(intResultX >= 70){
					intModX = intResultX/70;
				}else{
					intModX = 0;
				}
				intResultX = intModX * 70 + 13;
				thepanel.WH3StarOBJ.intX = intResultX;
				intResultY = intResultY - 80;
				if(intResultY >= 70){
					intModY = intResultY/70;
				}else{
					intModY = 0;
				}
				intResultY = intModY * 70 + 105;
				thepanel.WH3StarOBJ.intY = intResultY;
				blnMoveWH3S = false;
				strWBoard[intModX][intModY] = thepanel.WH3StarOBJ;
				thepanel.WH3StarOBJ.intArrayX = intModX;
				thepanel.WH3StarOBJ.intArrayY = intModY;
			}
			if(blnMoveWH4S){
				try{strWBoard[thepanel.WH4StarOBJ.intArrayX][thepanel.WH4StarOBJ.intArrayY] = null;
				}catch(ArrayIndexOutOfBoundsException e){
				}
				intResultX = evt.getX();
				intResultY = evt.getY();
				intResultX = intResultX - 10;
				if(intResultX >= 70){
					intModX = intResultX/70;
				}else{
					intModX = 0;
				}
				intResultX = intModX * 70 + 13;
				thepanel.WH4StarOBJ.intX = intResultX;
				intResultY = intResultY - 80;
				if(intResultY >= 70){
					intModY = intResultY/70;
				}else{
					intModY = 0;
				}
				intResultY = intModY * 70 + 105;
				thepanel.WH4StarOBJ.intY = intResultY;
				blnMoveWH4S = false;
				strWBoard[intModX][intModY] = thepanel.WH4StarOBJ;
				thepanel.WH4StarOBJ.intArrayX = intModX;
				thepanel.WH4StarOBJ.intArrayY = intModY;
			}
			if(blnMoveWH5S){
				try{strWBoard[thepanel.WH5StarOBJ.intArrayX][thepanel.WH5StarOBJ.intArrayY] = null;
				}catch(ArrayIndexOutOfBoundsException e){
				}
				intResultX = evt.getX();
				intResultY = evt.getY();
				intResultX = intResultX - 10;
				if(intResultX >= 70){
					intModX = intResultX/70;
				}else{
					intModX = 0;
				}
				intResultX = intModX * 70 + 13;
				thepanel.WH5StarOBJ.intX = intResultX;
				intResultY = intResultY - 80;
				if(intResultY >= 70){
					intModY = intResultY/70;
				}else{
					intModY = 0;
				}
				intResultY = intModY * 70 + 105;
				thepanel.WH5StarOBJ.intY = intResultY;
				blnMoveWH5S = false;
				strWBoard[intModX][intModY] = thepanel.WH5StarOBJ;
				thepanel.WH5StarOBJ.intArrayX = intModX;
				thepanel.WH5StarOBJ.intArrayY = intModY;
			}
			if(blnMoveWHM){
				try{strWBoard[thepanel.WHMajorOBJ.intArrayX][thepanel.WHMajorOBJ.intArrayY] = null;
				}catch(ArrayIndexOutOfBoundsException e){
				}
				intResultX = evt.getX();
				intResultY = evt.getY();
				intResultX = intResultX - 10;
				if(intResultX >= 70){
					intModX = intResultX/70;
				}else{
					intModX = 0;
				}
				intResultX = intModX * 70 + 13;
				thepanel.WHMajorOBJ.intX = intResultX;
				intResultY = intResultY - 80;
				if(intResultY >= 70){
					intModY = intResultY/70;
				}else{
					intModY = 0;
				}
				intResultY = intModY * 70 + 105;
				thepanel.WHMajorOBJ.intY = intResultY;
				blnMoveWHM = false;
				strWBoard[intModX][intModY] = thepanel.WHMajorOBJ;
				thepanel.WHMajorOBJ.intArrayX = intModX;
				thepanel.WHMajorOBJ.intArrayY = intModY;
			}
			if(blnMoveWHF){
				try{strWBoard[thepanel.WHFlagOBJ.intArrayX][thepanel.WHFlagOBJ.intArrayY] = null;
				}catch(ArrayIndexOutOfBoundsException e){
				}
				intResultX = evt.getX();
				intResultY = evt.getY();
				intResultX = intResultX - 10;
				if(intResultX >= 70){
					intModX = intResultX/70;
				}else{
					intModX = 0;
				}
				intResultX = intModX * 70 + 13;
				thepanel.WHFlagOBJ.intX = intResultX;
				intResultY = intResultY - 80;
				if(intResultY >= 70){
					intModY = intResultY/70;
				}else{
					intModY = 0;
				}
				intResultY = intModY * 70 + 105;
				thepanel.WHFlagOBJ.intY = intResultY;
				blnMoveWHF = false;
				strWBoard[intModX][intModY] = thepanel.WHFlagOBJ;
				thepanel.WHFlagOBJ.intArrayX = intModX;
				thepanel.WHFlagOBJ.intArrayY = intModY;
			}
			if(blnMoveWHLC){
				try{strWBoard[thepanel.WHLColonelOBJ.intArrayX][thepanel.WHLColonelOBJ.intArrayY] = null;
				}catch(ArrayIndexOutOfBoundsException e){
				}
				intResultX = evt.getX();
				intResultY = evt.getY();
				intResultX = intResultX - 10;
				if(intResultX >= 70){
					intModX = intResultX/70;
				}else{
					intModX = 0;
				}
				intResultX = intModX * 70 + 13;
				thepanel.WHLColonelOBJ.intX = intResultX;
				intResultY = intResultY - 80;
				if(intResultY >= 70){
					intModY = intResultY/70;
				}else{
					intModY = 0;
				}
				intResultY = intModY * 70 + 105;
				thepanel.WHLColonelOBJ.intY = intResultY;
				blnMoveWHLC = false;
				strWBoard[intModX][intModY] = thepanel.WHLColonelOBJ;
				thepanel.WHLColonelOBJ.intArrayX = intModX;
				thepanel.WHLColonelOBJ.intArrayY = intModY;
			}
			if(blnMoveWHSer){
				try{strWBoard[thepanel.WHSergeantOBJ.intArrayX][thepanel.WHSergeantOBJ.intArrayY] = null;
				}catch(ArrayIndexOutOfBoundsException e){
				}
				intResultX = evt.getX();
				intResultY = evt.getY();
				intResultX = intResultX - 10;
				if(intResultX >= 70){
					intModX = intResultX/70;
				}else{
					intModX = 0;
				}
				intResultX = intModX * 70 + 13;
				thepanel.WHSergeantOBJ.intX = intResultX;
				intResultY = intResultY - 80;
				if(intResultY >= 70){
					intModY = intResultY/70;
				}else{
					intModY = 0;
				}
				intResultY = intModY * 70 + 105;
				thepanel.WHSergeantOBJ.intY = intResultY;
				blnMoveWHSer = false;
				strWBoard[intModX][intModY] = thepanel.WHSergeantOBJ;
				thepanel.WHSergeantOBJ.intArrayX = intModX;
				thepanel.WHSergeantOBJ.intArrayY = intModY;
			}
			if(blnMoveWHSpy1){
				try{strWBoard[thepanel.WHSpy1OBJ.intArrayX][thepanel.WHSpy1OBJ.intArrayY] = null;
				}catch(ArrayIndexOutOfBoundsException e){
				}
				intResultX = evt.getX();
				intResultY = evt.getY();
				intResultX = intResultX - 10;
				if(intResultX >= 70){
					intModX = intResultX/70;
				}else{
					intModX = 0;
				}
				intResultX = intModX * 70 + 13;
				thepanel.WHSpy1OBJ.intX = intResultX;
				intResultY = intResultY - 80;
				if(intResultY >= 70){
					intModY = intResultY/70;
				}else{
					intModY = 0;
				}
				intResultY = intModY * 70 + 105;
				thepanel.WHSpy1OBJ.intY = intResultY;
				blnMoveWHSpy1 = false;
				strWBoard[intModX][intModY] = thepanel.WHSpy1OBJ;
				thepanel.WHSpy1OBJ.intArrayX = intModX;
				thepanel.WHSpy1OBJ.intArrayY = intModY;
			}
			if(blnMoveWHSpy2){
				try{strWBoard[thepanel.WHSpy2OBJ.intArrayX][thepanel.WHSpy2OBJ.intArrayY] = null;
				}catch(ArrayIndexOutOfBoundsException e){
				}
				intResultX = evt.getX();
				intResultY = evt.getY();
				intResultX = intResultX - 10;
				if(intResultX >= 70){
					intModX = intResultX/70;
				}else{
					intModX = 0;
				}
				intResultX = intModX * 70 + 13;
				thepanel.WHSpy2OBJ.intX = intResultX;
				intResultY = intResultY - 80;
				if(intResultY >= 70){
					intModY = intResultY/70;
				}else{
					intModY = 0;
				}
				intResultY = intModY * 70 + 105;
				thepanel.WHSpy2OBJ.intY = intResultY;
				blnMoveWHSpy2 = false;
				strWBoard[intModX][intModY] = thepanel.WHSpy2OBJ;
				thepanel.WHSpy2OBJ.intArrayX = intModX;
				thepanel.WHSpy2OBJ.intArrayY = intModY;
			}
			if(blnMoveWHP1){
				try{strWBoard[thepanel.WHPrivate1OBJ.intArrayX][thepanel.WHPrivate1OBJ.intArrayY] = null;
				}catch(ArrayIndexOutOfBoundsException e){
				}
				intResultX = evt.getX();
				intResultY = evt.getY();
				intResultX = intResultX - 10;
				if(intResultX >= 70){
					intModX = intResultX/70;
				}else{
					intModX = 0;
				}
				intResultX = intModX * 70 + 13;
				thepanel.WHPrivate1OBJ.intX = intResultX;
				intResultY = intResultY - 80;
				if(intResultY >= 70){
					intModY = intResultY/70;
				}else{
					intModY = 0;
				}
				intResultY = intModY * 70 + 105;
				thepanel.WHPrivate1OBJ.intY = intResultY;
				blnMoveWHP1 = false;
				strWBoard[intModX][intModY] = thepanel.WHPrivate1OBJ;
				thepanel.WHPrivate1OBJ.intArrayX = intModX;
				thepanel.WHPrivate1OBJ.intArrayY = intModY;
			}
			if(blnMoveWHP2){
				try{strWBoard[thepanel.WHPrivate2OBJ.intArrayX][thepanel.WHPrivate2OBJ.intArrayY] = null;
				}catch(ArrayIndexOutOfBoundsException e){
				}
				intResultX = evt.getX();
				intResultY = evt.getY();
				intResultX = intResultX - 10;
				if(intResultX >= 70){
					intModX = intResultX/70;
				}else{
					intModX = 0;
				}
				intResultX = intModX * 70 + 13;
				thepanel.WHPrivate2OBJ.intX = intResultX;
				intResultY = intResultY - 80;
				if(intResultY >= 70){
					intModY = intResultY/70;
				}else{
					intModY = 0;
				}
				intResultY = intModY * 70 + 105;
				thepanel.WHPrivate2OBJ.intY = intResultY;
				blnMoveWHP2 = false;
				strWBoard[intModX][intModY] = thepanel.WHPrivate2OBJ;
				thepanel.WHPrivate2OBJ.intArrayX = intModX;
				thepanel.WHPrivate2OBJ.intArrayY = intModY;
			}
			if(blnMoveWHP3){
				try{strWBoard[thepanel.WHPrivate3OBJ.intArrayX][thepanel.WHPrivate3OBJ.intArrayY] = null;
				}catch(ArrayIndexOutOfBoundsException e){
				}
				intResultX = evt.getX();
				intResultY = evt.getY();
				intResultX = intResultX - 10;
				if(intResultX >= 70){
					intModX = intResultX/70;
				}else{
					intModX = 0;
				}
				intResultX = intModX * 70 + 13;
				thepanel.WHPrivate3OBJ.intX = intResultX;
				intResultY = intResultY - 80;
				if(intResultY >= 70){
					intModY = intResultY/70;
				}else{
					intModY = 0;
				}
				intResultY = intModY * 70 + 105;
				thepanel.WHPrivate3OBJ.intY = intResultY;
				blnMoveWHP3 = false;
				strWBoard[intModX][intModY] = thepanel.WHPrivate3OBJ;
				thepanel.WHPrivate3OBJ.intArrayX = intModX;
				thepanel.WHPrivate3OBJ.intArrayY = intModY;
			}
			if(blnMoveWHP4){
				try{strWBoard[thepanel.WHPrivate4OBJ.intArrayX][thepanel.WHPrivate4OBJ.intArrayY] = null;
				}catch(ArrayIndexOutOfBoundsException e){
				}
				intResultX = evt.getX();
				intResultY = evt.getY();
				intResultX = intResultX - 10;
				if(intResultX >= 70){
					intModX = intResultX/70;
				}else{
					intModX = 0;
				}
				intResultX = intModX * 70 + 13;
				thepanel.WHPrivate4OBJ.intX = intResultX;
				intResultY = intResultY - 80;
				if(intResultY >= 70){
					intModY = intResultY/70;
				}else{
					intModY = 0;
				}
				intResultY = intModY * 70 + 105;
				thepanel.WHPrivate4OBJ.intY = intResultY;
				blnMoveWHP4 = false;
				strWBoard[intModX][intModY] = thepanel.WHPrivate4OBJ;
				thepanel.WHPrivate4OBJ.intArrayX = intModX;
				thepanel.WHPrivate4OBJ.intArrayY = intModY;
			}
			if(blnMoveWHP5){
				try{strWBoard[thepanel.WHPrivate5OBJ.intArrayX][thepanel.WHPrivate5OBJ.intArrayY] = null;
				}catch(ArrayIndexOutOfBoundsException e){
				}
				intResultX = evt.getX();
				intResultY = evt.getY();
				intResultX = intResultX - 10;
				if(intResultX >= 70){
					intModX = intResultX/70;
				}else{
					intModX = 0;
				}
				intResultX = intModX * 70 + 13;
				thepanel.WHPrivate5OBJ.intX = intResultX;
				intResultY = intResultY - 80;
				if(intResultY >= 70){
					intModY = intResultY/70;
				}else{
					intModY = 0;
				}
				intResultY = intModY * 70 + 105;
				thepanel.WHPrivate5OBJ.intY = intResultY;
				blnMoveWHP5 = false;
				strWBoard[intModX][intModY] = thepanel.WHPrivate5OBJ;
				thepanel.WHPrivate5OBJ.intArrayX = intModX;
				thepanel.WHPrivate5OBJ.intArrayY = intModY;
			}
			if(blnMoveWHP6){
				try{strWBoard[thepanel.WHPrivate6OBJ.intArrayX][thepanel.WHPrivate6OBJ.intArrayY] = null;
				}catch(ArrayIndexOutOfBoundsException e){
				}
				intResultX = evt.getX();
				intResultY = evt.getY();
				intResultX = intResultX - 10;
				if(intResultX >= 70){
					intModX = intResultX/70;
				}else{
					intModX = 0;
				}
				intResultX = intModX * 70 + 13;
				thepanel.WHPrivate6OBJ.intX = intResultX;
				intResultY = intResultY - 80;
				if(intResultY >= 70){
					intModY = intResultY/70;
				}else{
					intModY = 0;
				}
				intResultY = intModY * 70 + 105;
				thepanel.WHPrivate6OBJ.intY = intResultY;
				blnMoveWHP6 = false;
				strWBoard[intModX][intModY] = thepanel.WHPrivate6OBJ;
				thepanel.WHPrivate6OBJ.intArrayX = intModX;
				thepanel.WHPrivate6OBJ.intArrayY = intModY;
			}
			
			if(blnMoveBL1L){
				try{strBBoard[thepanel.BL1LieutenantOBJ.intArrayX][thepanel.BL1LieutenantOBJ.intArrayY] = null;
				}catch(ArrayIndexOutOfBoundsException e){
				}
				intResultX = evt.getX();
				intResultY = evt.getY();
				intResultX = intResultX - 10;
				if(intResultX >= 70){
					intModX = intResultX/70;
				}else{
					intModX = 0;
				}
				intResultX = intModX * 70 + 13;
				thepanel.BL1LieutenantOBJ.intX = intResultX;
				intResultY = intResultY - 80;
				if(intResultY >= 70){
					intModY = intResultY/70;
				}else{
					intModY = 0;
				}
				intResultY = intModY * 70 + 105;
				thepanel.BL1LieutenantOBJ.intY = intResultY;
				blnMoveBL1L = false;
				strBBoard[intModX][intModY] = thepanel.BL1LieutenantOBJ;
				thepanel.BL1LieutenantOBJ.intArrayX = intModX;
				thepanel.BL1LieutenantOBJ.intArrayY = intModY;
			}
			if(blnMoveBL2L){
				try{strBBoard[thepanel.BL2LieutenantOBJ.intArrayX][thepanel.BL2LieutenantOBJ.intArrayY] = null;
				}catch(ArrayIndexOutOfBoundsException e){
				}
				intResultX = evt.getX();
				intResultY = evt.getY();
				intResultX = intResultX - 10;
				if(intResultX >= 70){
					intModX = intResultX/70;
				}else{
					intModX = 0;
				}
				intResultX = intModX * 70 + 13;
				thepanel.BL2LieutenantOBJ.intX = intResultX;
				intResultY = intResultY - 80;
				if(intResultY >= 70){
					intModY = intResultY/70;
				}else{
					intModY = 0;
				}
				intResultY = intModY * 70 + 105;
				thepanel.BL2LieutenantOBJ.intY = intResultY;
				blnMoveBL2L = false;
				strBBoard[intModX][intModY] = thepanel.BL2LieutenantOBJ;
				thepanel.BL2LieutenantOBJ.intArrayX = intModX;
				thepanel.BL2LieutenantOBJ.intArrayY = intModY;
			}
			if(blnMoveBLCap){
				try{strBBoard[thepanel.BLCaptainOBJ.intArrayX][thepanel.BLCaptainOBJ.intArrayY] = null;
				}catch(ArrayIndexOutOfBoundsException e){
				}
				intResultX = evt.getX();
				intResultY = evt.getY();
				intResultX = intResultX - 10;
				if(intResultX >= 70){
					intModX = intResultX/70;
				}else{
					intModX = 0;
				}
				intResultX = intModX * 70 + 13;
				thepanel.BLCaptainOBJ.intX = intResultX;
				intResultY = intResultY - 80;
				if(intResultY >= 70){
					intModY = intResultY/70;
				}else{
					intModY = 0;
				}
				intResultY = intModY * 70 + 105;
				thepanel.BLCaptainOBJ.intY = intResultY;
				blnMoveBLCap = false;
				strBBoard[intModX][intModY] = thepanel.BLCaptainOBJ;
				thepanel.BLCaptainOBJ.intArrayX = intModX;
				thepanel.BLCaptainOBJ.intArrayY = intModY;
			}
			if(blnMoveBLCol){
				try{strBBoard[thepanel.BLColonelOBJ.intArrayX][thepanel.BLColonelOBJ.intArrayY] = null;
				}catch(ArrayIndexOutOfBoundsException e){
				}
				intResultX = evt.getX();
				intResultY = evt.getY();
				intResultX = intResultX - 10;
				if(intResultX >= 70){
					intModX = intResultX/70;
				}else{
					intModX = 0;
				}
				intResultX = intModX * 70 + 13;
				thepanel.BLColonelOBJ.intX = intResultX;
				intResultY = intResultY - 80;
				if(intResultY >= 70){
					intModY = intResultY/70;
				}else{
					intModY = 0;
				}
				intResultY = intModY * 70 + 105;
				thepanel.BLColonelOBJ.intY = intResultY;
				blnMoveBLCol = false;
				strBBoard[intModX][intModY] = thepanel.BLColonelOBJ;
				thepanel.BLColonelOBJ.intArrayX = intModX;
				thepanel.BLColonelOBJ.intArrayY = intModY;
			}
			if(blnMoveBL1S){
				try{strBBoard[thepanel.BL1StarOBJ.intArrayX][thepanel.BL1StarOBJ.intArrayY] = null;
				}catch(ArrayIndexOutOfBoundsException e){
				}
				intResultX = evt.getX();
				intResultY = evt.getY();
				intResultX = intResultX - 10;
				if(intResultX >= 70){
					intModX = intResultX/70;
				}else{
					intModX = 0;
				}
				intResultX = intModX * 70 + 13;
				thepanel.BL1StarOBJ.intX = intResultX;
				intResultY = intResultY - 80;
				if(intResultY >= 70){
					intModY = intResultY/70;
				}else{
					intModY = 0;
				}
				intResultY = intModY * 70 + 105;
				thepanel.BL1StarOBJ.intY = intResultY;
				blnMoveBL1S = false;
				strBBoard[intModX][intModY] = thepanel.BL1StarOBJ;
				thepanel.BL1StarOBJ.intArrayX = intModX;
				thepanel.BL1StarOBJ.intArrayY = intModY;
			}
			if(blnMoveBL2S){
				try{strBBoard[thepanel.BL2StarOBJ.intArrayX][thepanel.BL2StarOBJ.intArrayY] = null;
				}catch(ArrayIndexOutOfBoundsException e){
				}
				intResultX = evt.getX();
				intResultY = evt.getY();
				intResultX = intResultX - 10;
				if(intResultX >= 70){
					intModX = intResultX/70;
				}else{
					intModX = 0;
				}
				intResultX = intModX * 70 + 13;
				thepanel.BL2StarOBJ.intX = intResultX;
				intResultY = intResultY - 80;
				if(intResultY >= 70){
					intModY = intResultY/70;
				}else{
					intModY = 0;
				}
				intResultY = intModY * 70 + 105;
				thepanel.BL2StarOBJ.intY = intResultY;
				blnMoveBL2S = false;
				strBBoard[intModX][intModY] = thepanel.BL2StarOBJ;
				thepanel.BL2StarOBJ.intArrayX = intModX;
				thepanel.BL2StarOBJ.intArrayY = intModY;
			}
			if(blnMoveBL3S){
				try{strBBoard[thepanel.BL3StarOBJ.intArrayX][thepanel.BL3StarOBJ.intArrayY] = null;
				}catch(ArrayIndexOutOfBoundsException e){
				}
				intResultX = evt.getX();
				intResultY = evt.getY();
				intResultX = intResultX - 10;
				if(intResultX >= 70){
					intModX = intResultX/70;
				}else{
					intModX = 0;
				}
				intResultX = intModX * 70 + 13;
				thepanel.BL3StarOBJ.intX = intResultX;
				intResultY = intResultY - 80;
				if(intResultY >= 70){
					intModY = intResultY/70;
				}else{
					intModY = 0;
				}
				intResultY = intModY * 70 + 105;
				thepanel.BL3StarOBJ.intY = intResultY;
				blnMoveBL3S = false;
				strBBoard[intModX][intModY] = thepanel.BL3StarOBJ;
				thepanel.BL3StarOBJ.intArrayX = intModX;
				thepanel.BL3StarOBJ.intArrayY = intModY;
			}
			if(blnMoveBL4S){
				try{strBBoard[thepanel.BL4StarOBJ.intArrayX][thepanel.BL4StarOBJ.intArrayY] = null;
				}catch(ArrayIndexOutOfBoundsException e){
				}
				intResultX = evt.getX();
				intResultY = evt.getY();
				intResultX = intResultX - 10;
				if(intResultX >= 70){
					intModX = intResultX/70;
				}else{
					intModX = 0;
				}
				intResultX = intModX * 70 + 13;
				thepanel.BL4StarOBJ.intX = intResultX;
				intResultY = intResultY - 80;
				if(intResultY >= 70){
					intModY = intResultY/70;
				}else{
					intModY = 0;
				}
				intResultY = intModY * 70 + 105;
				thepanel.BL4StarOBJ.intY = intResultY;
				blnMoveBL4S = false;
				strBBoard[intModX][intModY] = thepanel.BL4StarOBJ;
				thepanel.BL4StarOBJ.intArrayX = intModX;
				thepanel.BL4StarOBJ.intArrayY = intModY;
			}
			if(blnMoveBL5S){
				try{strBBoard[thepanel.BL5StarOBJ.intArrayX][thepanel.BL5StarOBJ.intArrayY] = null;
				}catch(ArrayIndexOutOfBoundsException e){
				}
				intResultX = evt.getX();
				intResultY = evt.getY();
				intResultX = intResultX - 10;
				if(intResultX >= 70){
					intModX = intResultX/70;
				}else{
					intModX = 0;
				}
				intResultX = intModX * 70 + 13;
				thepanel.BL5StarOBJ.intX = intResultX;
				intResultY = intResultY - 80;
				if(intResultY >= 70){
					intModY = intResultY/70;
				}else{
					intModY = 0;
				}
				intResultY = intModY * 70 + 105;
				thepanel.BL5StarOBJ.intY = intResultY;
				blnMoveBL5S = false;
				strBBoard[intModX][intModY] = thepanel.BL5StarOBJ;
				thepanel.BL5StarOBJ.intArrayX = intModX;
				thepanel.BL5StarOBJ.intArrayY = intModY;
			}
			if(blnMoveBLM){
				try{strBBoard[thepanel.BLMajorOBJ.intArrayX][thepanel.BLMajorOBJ.intArrayY] = null;
				}catch(ArrayIndexOutOfBoundsException e){
				}
				intResultX = evt.getX();
				intResultY = evt.getY();
				intResultX = intResultX - 10;
				if(intResultX >= 70){
					intModX = intResultX/70;
				}else{
					intModX = 0;
				}
				intResultX = intModX * 70 + 13;
				thepanel.BLMajorOBJ.intX = intResultX;
				intResultY = intResultY - 80;
				if(intResultY >= 70){
					intModY = intResultY/70;
				}else{
					intModY = 0;
				}
				intResultY = intModY * 70 + 105;
				thepanel.BLMajorOBJ.intY = intResultY;
				blnMoveBLM = false;
				strBBoard[intModX][intModY] = thepanel.BLMajorOBJ;
				thepanel.BLMajorOBJ.intArrayX = intModX;
				thepanel.BLMajorOBJ.intArrayY = intModY;
			}
			if(blnMoveBLF){
				try{strBBoard[thepanel.BLFlagOBJ.intArrayX][thepanel.BLFlagOBJ.intArrayY] = null;
				}catch(ArrayIndexOutOfBoundsException e){
				}
				intResultX = evt.getX();
				intResultY = evt.getY();
				intResultX = intResultX - 10;
				if(intResultX >= 70){
					intModX = intResultX/70;
				}else{
					intModX = 0;
				}
				intResultX = intModX * 70 + 13;
				thepanel.BLFlagOBJ.intX = intResultX;
				intResultY = intResultY - 80;
				if(intResultY >= 70){
					intModY = intResultY/70;
				}else{
					intModY = 0;
				}
				intResultY = intModY * 70 + 105;
				thepanel.BLFlagOBJ.intY = intResultY;
				blnMoveBLF = false;
				strBBoard[intModX][intModY] = thepanel.BLFlagOBJ;
				thepanel.BLFlagOBJ.intArrayX = intModX;
				thepanel.BLFlagOBJ.intArrayY = intModY;
			}
			if(blnMoveBLLC){
				try{strBBoard[thepanel.BLLColonelOBJ.intArrayX][thepanel.BLLColonelOBJ.intArrayY] = null;
				}catch(ArrayIndexOutOfBoundsException e){
				}
				intResultX = evt.getX();
				intResultY = evt.getY();
				intResultX = intResultX - 10;
				if(intResultX >= 70){
					intModX = intResultX/70;
				}else{
					intModX = 0;
				}
				intResultX = intModX * 70 + 13;
				thepanel.BLLColonelOBJ.intX = intResultX;
				intResultY = intResultY - 80;
				if(intResultY >= 70){
					intModY = intResultY/70;
				}else{
					intModY = 0;
				}
				intResultY = intModY * 70 + 105;
				thepanel.BLLColonelOBJ.intY = intResultY;
				blnMoveBLLC = false;
				strBBoard[intModX][intModY] = thepanel.BLLColonelOBJ;
				thepanel.BLLColonelOBJ.intArrayX = intModX;
				thepanel.BLLColonelOBJ.intArrayY = intModY;
			}
			if(blnMoveBLSer){
				try{strBBoard[thepanel.BLSergeantOBJ.intArrayX][thepanel.BLSergeantOBJ.intArrayY] = null;
				}catch(ArrayIndexOutOfBoundsException e){
				}
				intResultX = evt.getX();
				intResultY = evt.getY();
				intResultX = intResultX - 10;
				if(intResultX >= 70){
					intModX = intResultX/70;
				}else{
					intModX = 0;
				}
				intResultX = intModX * 70 + 13;
				thepanel.BLSergeantOBJ.intX = intResultX;
				intResultY = intResultY - 80;
				if(intResultY >= 70){
					intModY = intResultY/70;
				}else{
					intModY = 0;
				}
				intResultY = intModY * 70 + 105;
				thepanel.BLSergeantOBJ.intY = intResultY;
				blnMoveBLSer = false;
				strBBoard[intModX][intModY] = thepanel.BLSergeantOBJ;
				thepanel.BLSergeantOBJ.intArrayX = intModX;
				thepanel.BLSergeantOBJ.intArrayY = intModY;
			}
			if(blnMoveBLSpy1){
				try{strBBoard[thepanel.BLSpy1OBJ.intArrayX][thepanel.BLSpy1OBJ.intArrayY] = null;
				}catch(ArrayIndexOutOfBoundsException e){
				}
				intResultX = evt.getX();
				intResultY = evt.getY();
				intResultX = intResultX - 10;
				if(intResultX >= 70){
					intModX = intResultX/70;
				}else{
					intModX = 0;
				}
				intResultX = intModX * 70 + 13;
				thepanel.BLSpy1OBJ.intX = intResultX;
				intResultY = intResultY - 80;
				if(intResultY >= 70){
					intModY = intResultY/70;
				}else{
					intModY = 0;
				}
				intResultY = intModY * 70 + 105;
				thepanel.BLSpy1OBJ.intY = intResultY;
				blnMoveBLSpy1 = false;
				strBBoard[intModX][intModY] = thepanel.BLSpy1OBJ;
				thepanel.BLSpy1OBJ.intArrayX = intModX;
				thepanel.BLSpy1OBJ.intArrayY = intModY;
			}
			if(blnMoveBLSpy2){
				try{strBBoard[thepanel.BLSpy2OBJ.intArrayX][thepanel.BLSpy2OBJ.intArrayY] = null;
				}catch(ArrayIndexOutOfBoundsException e){
				}
				intResultX = evt.getX();
				intResultY = evt.getY();
				intResultX = intResultX - 10;
				if(intResultX >= 70){
					intModX = intResultX/70;
				}else{
					intModX = 0;
				}
				intResultX = intModX * 70 + 13;
				thepanel.BLSpy2OBJ.intX = intResultX;
				intResultY = intResultY - 80;
				if(intResultY >= 70){
					intModY = intResultY/70;
				}else{
					intModY = 0;
				}
				intResultY = intModY * 70 + 105;
				thepanel.BLSpy2OBJ.intY = intResultY;
				blnMoveBLSpy2 = false;
				strBBoard[intModX][intModY] = thepanel.BLSpy2OBJ;
				thepanel.BLSpy2OBJ.intArrayX = intModX;
				thepanel.BLSpy2OBJ.intArrayY = intModY;
			}
			if(blnMoveBLP1){
				try{strBBoard[thepanel.BLPrivate1OBJ.intArrayX][thepanel.BLPrivate1OBJ.intArrayY] = null;
				}catch(ArrayIndexOutOfBoundsException e){
				}
				intResultX = evt.getX();
				intResultY = evt.getY();
				intResultX = intResultX - 10;
				if(intResultX >= 70){
					intModX = intResultX/70;
				}else{
					intModX = 0;
				}
				intResultX = intModX * 70 + 13;
				thepanel.BLPrivate1OBJ.intX = intResultX;
				intResultY = intResultY - 80;
				if(intResultY >= 70){
					intModY = intResultY/70;
				}else{
					intModY = 0;
				}
				intResultY = intModY * 70 + 105;
				thepanel.BLPrivate1OBJ.intY = intResultY;
				blnMoveBLP1 = false;
				strBBoard[intModX][intModY] = thepanel.BLPrivate1OBJ;
				thepanel.BLPrivate1OBJ.intArrayX = intModX;
				thepanel.BLPrivate1OBJ.intArrayY = intModY;
			}
			if(blnMoveBLP2){
				try{strBBoard[thepanel.BLPrivate2OBJ.intArrayX][thepanel.BLPrivate2OBJ.intArrayY] = null;
				}catch(ArrayIndexOutOfBoundsException e){
				}
				intResultX = evt.getX();
				intResultY = evt.getY();
				intResultX = intResultX - 10;
				if(intResultX >= 70){
					intModX = intResultX/70;
				}else{
					intModX = 0;
				}
				intResultX = intModX * 70 + 13;
				thepanel.BLPrivate2OBJ.intX = intResultX;
				intResultY = intResultY - 80;
				if(intResultY >= 70){
					intModY = intResultY/70;
				}else{
					intModY = 0;
				}
				intResultY = intModY * 70 + 105;
				thepanel.BLPrivate2OBJ.intY = intResultY;
				blnMoveBLP2 = false;
				strBBoard[intModX][intModY] = thepanel.BLPrivate2OBJ;
				thepanel.BLPrivate2OBJ.intArrayX = intModX;
				thepanel.BLPrivate2OBJ.intArrayY = intModY;
			}
			if(blnMoveBLP3){
				try{strBBoard[thepanel.BLPrivate3OBJ.intArrayX][thepanel.BLPrivate3OBJ.intArrayY] = null;
				}catch(ArrayIndexOutOfBoundsException e){
				}
				intResultX = evt.getX();
				intResultY = evt.getY();
				intResultX = intResultX - 10;
				if(intResultX >= 70){
					intModX = intResultX/70;
				}else{
					intModX = 0;
				}
				intResultX = intModX * 70 + 13;
				thepanel.BLPrivate3OBJ.intX = intResultX;
				intResultY = intResultY - 80;
				if(intResultY >= 70){
					intModY = intResultY/70;
				}else{
					intModY = 0;
				}
				intResultY = intModY * 70 + 105;
				thepanel.BLPrivate3OBJ.intY = intResultY;
				blnMoveBLP3 = false;
				strBBoard[intModX][intModY] = thepanel.BLPrivate3OBJ;
				thepanel.BLPrivate3OBJ.intArrayX = intModX;
				thepanel.BLPrivate3OBJ.intArrayY = intModY;
			}
			if(blnMoveBLP4){
				try{strBBoard[thepanel.BLPrivate4OBJ.intArrayX][thepanel.BLPrivate4OBJ.intArrayY] = null;
				}catch(ArrayIndexOutOfBoundsException e){
				}
				intResultX = evt.getX();
				intResultY = evt.getY();
				intResultX = intResultX - 10;
				if(intResultX >= 70){
					intModX = intResultX/70;
				}else{
					intModX = 0;
				}
				intResultX = intModX * 70 + 13;
				thepanel.BLPrivate4OBJ.intX = intResultX;
				intResultY = intResultY - 80;
				if(intResultY >= 70){
					intModY = intResultY/70;
				}else{
					intModY = 0;
				}
				intResultY = intModY * 70 + 105;
				thepanel.BLPrivate4OBJ.intY = intResultY;
				blnMoveBLP4 = false;
				strBBoard[intModX][intModY] = thepanel.BLPrivate4OBJ;
				thepanel.BLPrivate4OBJ.intArrayX = intModX;
				thepanel.BLPrivate4OBJ.intArrayY = intModY;
			}
			if(blnMoveBLP5){
				try{strBBoard[thepanel.BLPrivate5OBJ.intArrayX][thepanel.BLPrivate5OBJ.intArrayY] = null;
				}catch(ArrayIndexOutOfBoundsException e){
				}
				intResultX = evt.getX();
				intResultY = evt.getY();
				intResultX = intResultX - 10;
				if(intResultX >= 70){
					intModX = intResultX/70;
				}else{
					intModX = 0;
				}
				intResultX = intModX * 70 + 13;
				thepanel.BLPrivate5OBJ.intX = intResultX;
				intResultY = intResultY - 80;
				if(intResultY >= 70){
					intModY = intResultY/70;
				}else{
					intModY = 0;
				}
				intResultY = intModY * 70 + 105;
				thepanel.BLPrivate5OBJ.intY = intResultY;
				blnMoveBLP5 = false;
				strBBoard[intModX][intModY] = thepanel.BLPrivate5OBJ;
				thepanel.BLPrivate5OBJ.intArrayX = intModX;
				thepanel.BLPrivate5OBJ.intArrayY = intModY;			
			}
			if(blnMoveBLP6){
				try{strBBoard[thepanel.BLPrivate6OBJ.intArrayX][thepanel.BLPrivate6OBJ.intArrayY] = null;
				}catch(ArrayIndexOutOfBoundsException e){
				}
				intResultX = evt.getX();
				intResultY = evt.getY();
				intResultX = intResultX - 10;
				if(intResultX >= 70){
					intModX = intResultX/70;
				}else{
					intModX = 0;
				}
				intResultX = intModX * 70 + 13;
				thepanel.BLPrivate6OBJ.intX = intResultX;
				intResultY = intResultY - 80;
				if(intResultY >= 70){
					intModY = intResultY/70;
				}else{
					intModY = 0;
				}				
				intResultY = intModY * 70 + 105;
				thepanel.BLPrivate6OBJ.intY = intResultY;
				blnMoveBLP6 = false;
				strBBoard[intModX][intModY] = thepanel.BLPrivate6OBJ;
				thepanel.BLPrivate6OBJ.intArrayX = intModX;
				thepanel.BLPrivate6OBJ.intArrayY = intModY;
			}
			
		}
		
	}
	//Setting up booleans for dragging and dropping each piece during set up
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
	
	boolean blnMoveBL1L = false;
	boolean blnMoveBL2L = false;
	boolean blnMoveBLCap = false;
	boolean blnMoveBLCol = false;
	boolean blnMoveBL1S = false;
	boolean blnMoveBL2S = false;
	boolean blnMoveBL3S = false;
	boolean blnMoveBL4S = false;
	boolean blnMoveBL5S = false;
	boolean blnMoveBLM = false;
	boolean blnMoveBLF = false;
	boolean blnMoveBLLC = false;
	boolean blnMoveBLSer = false;
	boolean blnMoveBLSpy1 = false;
	boolean blnMoveBLSpy2 = false;
	boolean blnMoveBLP1 = false;
	boolean blnMoveBLP2 = false;
	boolean blnMoveBLP3 = false;
	boolean blnMoveBLP4 = false;
	boolean blnMoveBLP5 = false;
	boolean blnMoveBLP6 = false;
	
	// METHODS - MOUSELISTENER (Mouse Pressed)
	public void mousePressed(MouseEvent evt){
		//identifying which piece is being selected to drag and drop for set up
		if(blnSettingUp == true){
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
			
			//BLACK
			
			if(evt.getX() > thepanel.BL1LieutenantOBJ.intX && evt.getX() < thepanel.BL1LieutenantOBJ.intX + 65 && evt.getY() > thepanel.BL1LieutenantOBJ.intY && evt.getY() < thepanel.BL1LieutenantOBJ.intY + 20){
				blnMoveBL1L = true;
			}
			if(evt.getX() > thepanel.BL2LieutenantOBJ.intX && evt.getX() < thepanel.BL2LieutenantOBJ.intX + 65 && evt.getY() > thepanel.BL2LieutenantOBJ.intY && evt.getY() < thepanel.BL2LieutenantOBJ.intY + 20){
				blnMoveBL2L = true;
			}
			if(evt.getX() > thepanel.BLCaptainOBJ.intX && evt.getX() < thepanel.BLCaptainOBJ.intX + 65 && evt.getY() > thepanel.BLCaptainOBJ.intY && evt.getY() < thepanel.BLCaptainOBJ.intY + 20){
				blnMoveBLCap = true;
			}
			if(evt.getX() > thepanel.BLColonelOBJ.intX && evt.getX() < thepanel.BLColonelOBJ.intX + 65 && evt.getY() > thepanel.BLColonelOBJ.intY && evt.getY() < thepanel.BLColonelOBJ.intY + 20){
				blnMoveBLCol = true;
			}
			if(evt.getX() > thepanel.BL1StarOBJ.intX && evt.getX() < thepanel.BL1StarOBJ.intX + 65 && evt.getY() > thepanel.BL1StarOBJ.intY && evt.getY() < thepanel.BL1StarOBJ.intY + 20){
				blnMoveBL1S = true;
			}
			if(evt.getX() > thepanel.BL2StarOBJ.intX && evt.getX() < thepanel.BL2StarOBJ.intX + 65 && evt.getY() > thepanel.BL2StarOBJ.intY && evt.getY() < thepanel.BL2StarOBJ.intY + 20){
				blnMoveBL2S = true;
			}
			if(evt.getX() > thepanel.BL3StarOBJ.intX && evt.getX() < thepanel.BL3StarOBJ.intX + 65 && evt.getY() > thepanel.BL3StarOBJ.intY && evt.getY() < thepanel.BL3StarOBJ.intY + 20){
				blnMoveBL3S = true;
			}
			if(evt.getX() > thepanel.BL4StarOBJ.intX && evt.getX() < thepanel.BL4StarOBJ.intX + 65 && evt.getY() > thepanel.BL4StarOBJ.intY && evt.getY() < thepanel.BL4StarOBJ.intY + 20){
				blnMoveBL4S = true;
			}
			if(evt.getX() > thepanel.BL5StarOBJ.intX && evt.getX() < thepanel.BL5StarOBJ.intX + 65 && evt.getY() > thepanel.BL5StarOBJ.intY && evt.getY() < thepanel.BL5StarOBJ.intY + 20){
				blnMoveBL5S = true;
			}
			if(evt.getX() > thepanel.BLMajorOBJ.intX && evt.getX() < thepanel.BLMajorOBJ.intX + 65 && evt.getY() > thepanel.BLMajorOBJ.intY && evt.getY() < thepanel.BLMajorOBJ.intY + 20){
				blnMoveBLM = true;
			}
			if(evt.getX() > thepanel.BLFlagOBJ.intX && evt.getX() < thepanel.BLFlagOBJ.intX + 65 && evt.getY() > thepanel.BLFlagOBJ.intY && evt.getY() < thepanel.BLFlagOBJ.intY + 20){
				blnMoveBLF = true;
			}
			if(evt.getX() > thepanel.BLLColonelOBJ.intX && evt.getX() < thepanel.BLLColonelOBJ.intX + 65 && evt.getY() > thepanel.BLLColonelOBJ.intY && evt.getY() < thepanel.BLLColonelOBJ.intY + 20){
				blnMoveBLLC = true;
			}
			if(evt.getX() > thepanel.BLSergeantOBJ.intX && evt.getX() < thepanel.BLSergeantOBJ.intX + 65 && evt.getY() > thepanel.BLSergeantOBJ.intY && evt.getY() < thepanel.BLSergeantOBJ.intY + 20){
				blnMoveBLSer = true;
			}
			if(evt.getX() > thepanel.BLSpy1OBJ.intX && evt.getX() < thepanel.BLSpy1OBJ.intX + 65 && evt.getY() > thepanel.BLSpy1OBJ.intY && evt.getY() < thepanel.BLSpy1OBJ.intY + 20){
				blnMoveBLSpy1 = true;
			}
			if(evt.getX() > thepanel.BLSpy2OBJ.intX && evt.getX() < thepanel.BLSpy2OBJ.intX + 65 && evt.getY() > thepanel.BLSpy2OBJ.intY && evt.getY() < thepanel.BLSpy2OBJ.intY + 20){
				blnMoveBLSpy2 = true;
			}
			if(evt.getX() > thepanel.BLPrivate1OBJ.intX && evt.getX() < thepanel.BLPrivate1OBJ.intX + 65 && evt.getY() > thepanel.BLPrivate1OBJ.intY && evt.getY() < thepanel.BLPrivate1OBJ.intY + 20){
				blnMoveBLP1 = true;
			}
			if(evt.getX() > thepanel.BLPrivate2OBJ.intX && evt.getX() < thepanel.BLPrivate2OBJ.intX + 65 && evt.getY() > thepanel.BLPrivate2OBJ.intY && evt.getY() < thepanel.BLPrivate2OBJ.intY + 20){
				blnMoveBLP2 = true;
			}
			if(evt.getX() > thepanel.BLPrivate3OBJ.intX && evt.getX() < thepanel.BLPrivate3OBJ.intX + 65 && evt.getY() > thepanel.BLPrivate3OBJ.intY && evt.getY() < thepanel.BLPrivate3OBJ.intY + 20){
				blnMoveBLP3 = true;
			}
			if(evt.getX() > thepanel.BLPrivate4OBJ.intX && evt.getX() < thepanel.BLPrivate4OBJ.intX + 65 && evt.getY() > thepanel.BLPrivate4OBJ.intY && evt.getY() < thepanel.BLPrivate4OBJ.intY + 20){
				blnMoveBLP4 = true;
			}
			if(evt.getX() > thepanel.BLPrivate5OBJ.intX && evt.getX() < thepanel.BLPrivate5OBJ.intX + 65 && evt.getY() > thepanel.BLPrivate5OBJ.intY && evt.getY() < thepanel.BLPrivate5OBJ.intY + 20){
				blnMoveBLP5 = true;
			}
			if(evt.getX() > thepanel.BLPrivate6OBJ.intX && evt.getX() < thepanel.BLPrivate6OBJ.intX + 65 && evt.getY() > thepanel.BLPrivate6OBJ.intY && evt.getY() < thepanel.BLPrivate6OBJ.intY + 20){
				blnMoveBLP6 = true;
			}
		}
	}
	//Setting up booleans for when a piece is selected to move during a player's turn
	boolean blnClickWH1L = false;
	boolean blnClickWH2L = false;
	boolean blnClickWHCap = false;
	boolean blnClickWHCol = false;
	boolean blnClickWH1S = false;
	boolean blnClickWH2S = false;
	boolean blnClickWH3S = false;
	boolean blnClickWH4S = false;
	boolean blnClickWH5S = false;
	boolean blnClickWHM = false;
	boolean blnClickWHF = false;
	boolean blnClickWHLC = false;
	boolean blnClickWHSer = false;
	boolean blnClickWHSpy1 = false;
	boolean blnClickWHSpy2 = false;
	boolean blnClickWHP1 = false;
	boolean blnClickWHP2 = false;
	boolean blnClickWHP3 = false;
	boolean blnClickWHP4 = false;
	boolean blnClickWHP5 = false;
	boolean blnClickWHP6 = false;
	
	boolean blnClickBL1L = false;
	boolean blnClickBL2L = false;
	boolean blnClickBLCap = false;
	boolean blnClickBLCol = false;
	boolean blnClickBL1S = false;
	boolean blnClickBL2S = false;
	boolean blnClickBL3S = false;
	boolean blnClickBL4S = false;
	boolean blnClickBL5S = false;
	boolean blnClickBLM = false;
	boolean blnClickBLF = false;
	boolean blnClickBLLC = false;
	boolean blnClickBLSer = false;
	boolean blnClickBLSpy1 = false;
	boolean blnClickBLSpy2 = false;
	boolean blnClickBLP1 = false;
	boolean blnClickBLP2 = false;
	boolean blnClickBLP3 = false;
	boolean blnClickBLP4 = false;
	boolean blnClickBLP5 = false;
	boolean blnClickBLP6 = false;
	
	// METHODS - MOUSELISTENER (Mouse Clicked)
	public void mouseClicked(MouseEvent evt){
		//Identifying which piece was selected to move during a player's turn
		if(thepanel.intReady == 2){
			if(evt.getX() > thepanel.WH1LieutenantOBJ.intX && evt.getX() < thepanel.WH1LieutenantOBJ.intX + 65 && evt.getY() > thepanel.WH1LieutenantOBJ.intY && evt.getY() < thepanel.WH1LieutenantOBJ.intY + 20){
				blnClickWH1L = true;
			}
			if(evt.getX() > thepanel.WH2LieutenantOBJ.intX && evt.getX() < thepanel.WH2LieutenantOBJ.intX + 65 && evt.getY() > thepanel.WH2LieutenantOBJ.intY && evt.getY() < thepanel.WH2LieutenantOBJ.intY + 20){
				blnClickWH2L = true;
			}
			if(evt.getX() > thepanel.WHCaptainOBJ.intX && evt.getX() < thepanel.WHCaptainOBJ.intX + 65 && evt.getY() > thepanel.WHCaptainOBJ.intY && evt.getY() < thepanel.WHCaptainOBJ.intY + 20){
				blnClickWHCap = true;
			}
			if(evt.getX() > thepanel.WHColonelOBJ.intX && evt.getX() < thepanel.WHColonelOBJ.intX + 65 && evt.getY() > thepanel.WHColonelOBJ.intY && evt.getY() < thepanel.WHColonelOBJ.intY + 20){
				blnClickWHCol = true;
			}
			if(evt.getX() > thepanel.WH1StarOBJ.intX && evt.getX() < thepanel.WH1StarOBJ.intX + 65 && evt.getY() > thepanel.WH1StarOBJ.intY && evt.getY() < thepanel.WH1StarOBJ.intY + 20){
				blnClickWH1S = true;
			}
			if(evt.getX() > thepanel.WH2StarOBJ.intX && evt.getX() < thepanel.WH2StarOBJ.intX + 65 && evt.getY() > thepanel.WH2StarOBJ.intY && evt.getY() < thepanel.WH2StarOBJ.intY + 20){
				blnClickWH2S = true;
			}
			if(evt.getX() > thepanel.WH3StarOBJ.intX && evt.getX() < thepanel.WH3StarOBJ.intX + 65 && evt.getY() > thepanel.WH3StarOBJ.intY && evt.getY() < thepanel.WH3StarOBJ.intY + 20){
				blnClickWH3S = true;
			}
			if(evt.getX() > thepanel.WH4StarOBJ.intX && evt.getX() < thepanel.WH4StarOBJ.intX + 65 && evt.getY() > thepanel.WH4StarOBJ.intY && evt.getY() < thepanel.WH4StarOBJ.intY + 20){
				blnClickWH4S = true;
			}
			if(evt.getX() > thepanel.WH5StarOBJ.intX && evt.getX() < thepanel.WH5StarOBJ.intX + 65 && evt.getY() > thepanel.WH5StarOBJ.intY && evt.getY() < thepanel.WH5StarOBJ.intY + 20){
				blnClickWH5S = true;
			}
			if(evt.getX() > thepanel.WHMajorOBJ.intX && evt.getX() < thepanel.WHMajorOBJ.intX + 65 && evt.getY() > thepanel.WHMajorOBJ.intY && evt.getY() < thepanel.WHMajorOBJ.intY + 20){
				blnClickWHM = true;
			}
			if(evt.getX() > thepanel.WHFlagOBJ.intX && evt.getX() < thepanel.WHFlagOBJ.intX + 65 && evt.getY() > thepanel.WHFlagOBJ.intY && evt.getY() < thepanel.WHFlagOBJ.intY + 20){
				blnClickWHF = true;
			}
			if(evt.getX() > thepanel.WHLColonelOBJ.intX && evt.getX() < thepanel.WHLColonelOBJ.intX + 65 && evt.getY() > thepanel.WHLColonelOBJ.intY && evt.getY() < thepanel.WHLColonelOBJ.intY + 20){
				blnClickWHLC = true;
			}
			if(evt.getX() > thepanel.WHSergeantOBJ.intX && evt.getX() < thepanel.WHSergeantOBJ.intX + 65 && evt.getY() > thepanel.WHSergeantOBJ.intY && evt.getY() < thepanel.WHSergeantOBJ.intY + 20){
				blnClickWHSer = true;
			}
			if(evt.getX() > thepanel.WHSpy1OBJ.intX && evt.getX() < thepanel.WHSpy1OBJ.intX + 65 && evt.getY() > thepanel.WHSpy1OBJ.intY && evt.getY() < thepanel.WHSpy1OBJ.intY + 20){
				blnClickWHSpy1 = true;
			}
			if(evt.getX() > thepanel.WHSpy2OBJ.intX && evt.getX() < thepanel.WHSpy2OBJ.intX + 65 && evt.getY() > thepanel.WHSpy2OBJ.intY && evt.getY() < thepanel.WHSpy2OBJ.intY + 20){
				blnClickWHSpy2 = true;
			}
			if(evt.getX() > thepanel.WHPrivate1OBJ.intX && evt.getX() < thepanel.WHPrivate1OBJ.intX + 65 && evt.getY() > thepanel.WHPrivate1OBJ.intY && evt.getY() < thepanel.WHPrivate1OBJ.intY + 20){
				blnClickWHP1 = true;
			}
			if(evt.getX() > thepanel.WHPrivate2OBJ.intX && evt.getX() < thepanel.WHPrivate2OBJ.intX + 65 && evt.getY() > thepanel.WHPrivate2OBJ.intY && evt.getY() < thepanel.WHPrivate2OBJ.intY + 20){
				blnClickWHP2 = true;
			}
			if(evt.getX() > thepanel.WHPrivate3OBJ.intX && evt.getX() < thepanel.WHPrivate3OBJ.intX + 65 && evt.getY() > thepanel.WHPrivate3OBJ.intY && evt.getY() < thepanel.WHPrivate3OBJ.intY + 20){
				blnClickWHP3 = true;
			}
			if(evt.getX() > thepanel.WHPrivate4OBJ.intX && evt.getX() < thepanel.WHPrivate4OBJ.intX + 65 && evt.getY() > thepanel.WHPrivate4OBJ.intY && evt.getY() < thepanel.WHPrivate4OBJ.intY + 20){
				blnClickWHP4 = true;
			}
			if(evt.getX() > thepanel.WHPrivate5OBJ.intX && evt.getX() < thepanel.WHPrivate5OBJ.intX + 65 && evt.getY() > thepanel.WHPrivate5OBJ.intY && evt.getY() < thepanel.WHPrivate5OBJ.intY + 20){
				blnClickWHP5 = true;
			}
			if(evt.getX() > thepanel.WHPrivate6OBJ.intX && evt.getX() < thepanel.WHPrivate6OBJ.intX + 65 && evt.getY() > thepanel.WHPrivate6OBJ.intY && evt.getY() < thepanel.WHPrivate6OBJ.intY + 20){
				blnClickWHP6 = true;
			}
			
			//BLACK
			
			if(evt.getX() > thepanel.BL1LieutenantOBJ.intX && evt.getX() < thepanel.BL1LieutenantOBJ.intX + 65 && evt.getY() > thepanel.BL1LieutenantOBJ.intY && evt.getY() < thepanel.BL1LieutenantOBJ.intY + 20){
				blnClickBL1L = true;
			}
			if(evt.getX() > thepanel.BL2LieutenantOBJ.intX && evt.getX() < thepanel.BL2LieutenantOBJ.intX + 65 && evt.getY() > thepanel.BL2LieutenantOBJ.intY && evt.getY() < thepanel.BL2LieutenantOBJ.intY + 20){
				blnClickBL2L = true;
			}
			if(evt.getX() > thepanel.BLCaptainOBJ.intX && evt.getX() < thepanel.BLCaptainOBJ.intX + 65 && evt.getY() > thepanel.BLCaptainOBJ.intY && evt.getY() < thepanel.BLCaptainOBJ.intY + 20){
				blnClickBLCap = true;
			}
			if(evt.getX() > thepanel.BLColonelOBJ.intX && evt.getX() < thepanel.BLColonelOBJ.intX + 65 && evt.getY() > thepanel.BLColonelOBJ.intY && evt.getY() < thepanel.BLColonelOBJ.intY + 20){
				blnClickBLCol = true;
			}
			if(evt.getX() > thepanel.BL1StarOBJ.intX && evt.getX() < thepanel.BL1StarOBJ.intX + 65 && evt.getY() > thepanel.BL1StarOBJ.intY && evt.getY() < thepanel.BL1StarOBJ.intY + 20){
				blnClickBL1S = true;
			}
			if(evt.getX() > thepanel.BL2StarOBJ.intX && evt.getX() < thepanel.BL2StarOBJ.intX + 65 && evt.getY() > thepanel.BL2StarOBJ.intY && evt.getY() < thepanel.BL2StarOBJ.intY + 20){
				blnClickBL2S = true;
			}
			if(evt.getX() > thepanel.BL3StarOBJ.intX && evt.getX() < thepanel.BL3StarOBJ.intX + 65 && evt.getY() > thepanel.BL3StarOBJ.intY && evt.getY() < thepanel.BL3StarOBJ.intY + 20){
				blnClickBL3S = true;
			}
			if(evt.getX() > thepanel.BL4StarOBJ.intX && evt.getX() < thepanel.BL4StarOBJ.intX + 65 && evt.getY() > thepanel.BL4StarOBJ.intY && evt.getY() < thepanel.BL4StarOBJ.intY + 20){
				blnClickBL4S = true;
			}
			if(evt.getX() > thepanel.BL5StarOBJ.intX && evt.getX() < thepanel.BL5StarOBJ.intX + 65 && evt.getY() > thepanel.BL5StarOBJ.intY && evt.getY() < thepanel.BL5StarOBJ.intY + 20){
				blnClickBL5S = true;
			}
			if(evt.getX() > thepanel.BLMajorOBJ.intX && evt.getX() < thepanel.BLMajorOBJ.intX + 65 && evt.getY() > thepanel.BLMajorOBJ.intY && evt.getY() < thepanel.BLMajorOBJ.intY + 20){
				blnClickBLM = true;
			}
			if(evt.getX() > thepanel.BLFlagOBJ.intX && evt.getX() < thepanel.BLFlagOBJ.intX + 65 && evt.getY() > thepanel.BLFlagOBJ.intY && evt.getY() < thepanel.BLFlagOBJ.intY + 20){
				blnClickBLF = true;
			}
			if(evt.getX() > thepanel.BLLColonelOBJ.intX && evt.getX() < thepanel.BLLColonelOBJ.intX + 65 && evt.getY() > thepanel.BLLColonelOBJ.intY && evt.getY() < thepanel.BLLColonelOBJ.intY + 20){
				blnClickBLLC = true;
			}
			if(evt.getX() > thepanel.BLSergeantOBJ.intX && evt.getX() < thepanel.BLSergeantOBJ.intX + 65 && evt.getY() > thepanel.BLSergeantOBJ.intY && evt.getY() < thepanel.BLSergeantOBJ.intY + 20){
				blnClickBLSer = true;
			}
			if(evt.getX() > thepanel.BLSpy1OBJ.intX && evt.getX() < thepanel.BLSpy1OBJ.intX + 65 && evt.getY() > thepanel.BLSpy1OBJ.intY && evt.getY() < thepanel.BLSpy1OBJ.intY + 20){
				blnClickBLSpy1 = true;
			}
			if(evt.getX() > thepanel.BLSpy2OBJ.intX && evt.getX() < thepanel.BLSpy2OBJ.intX + 65 && evt.getY() > thepanel.BLSpy2OBJ.intY && evt.getY() < thepanel.BLSpy2OBJ.intY + 20){
				blnClickBLSpy2 = true;
			}
			if(evt.getX() > thepanel.BLPrivate1OBJ.intX && evt.getX() < thepanel.BLPrivate1OBJ.intX + 65 && evt.getY() > thepanel.BLPrivate1OBJ.intY && evt.getY() < thepanel.BLPrivate1OBJ.intY + 20){
				blnClickBLP1 = true;
			}
			if(evt.getX() > thepanel.BLPrivate2OBJ.intX && evt.getX() < thepanel.BLPrivate2OBJ.intX + 65 && evt.getY() > thepanel.BLPrivate2OBJ.intY && evt.getY() < thepanel.BLPrivate2OBJ.intY + 20){
				blnClickBLP2 = true;
			}
			if(evt.getX() > thepanel.BLPrivate3OBJ.intX && evt.getX() < thepanel.BLPrivate3OBJ.intX + 65 && evt.getY() > thepanel.BLPrivate3OBJ.intY && evt.getY() < thepanel.BLPrivate3OBJ.intY + 20){
				blnClickBLP3 = true;
			}
			if(evt.getX() > thepanel.BLPrivate4OBJ.intX && evt.getX() < thepanel.BLPrivate4OBJ.intX + 65 && evt.getY() > thepanel.BLPrivate4OBJ.intY && evt.getY() < thepanel.BLPrivate4OBJ.intY + 20){
				blnClickBLP4 = true;
			}
			if(evt.getX() > thepanel.BLPrivate5OBJ.intX && evt.getX() < thepanel.BLPrivate5OBJ.intX + 65 && evt.getY() > thepanel.BLPrivate5OBJ.intY && evt.getY() < thepanel.BLPrivate5OBJ.intY + 20){
				blnClickBLP5 = true;
			}
			if(evt.getX() > thepanel.BLPrivate6OBJ.intX && evt.getX() < thepanel.BLPrivate6OBJ.intX + 65 && evt.getY() > thepanel.BLPrivate6OBJ.intY && evt.getY() < thepanel.BLPrivate6OBJ.intY + 20){
				blnClickBLP6 = true;
			}
		}
	}
	
// - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -	
	
	// METHODS - MOUSEMOTIONLISTENER (Mouse Moved)
	public void mouseMoved(MouseEvent evt){
		
	}
	
	// METHODS - MOUSEMOTIONLISTENER (Mouse Dragged)
	public void mouseDragged(MouseEvent evt){
		//Setting up the piece to follow mouse when dragging to set up
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
			
			//BLACK
			if(blnMoveBL1L){
				thepanel.BL1LieutenantOBJ.intX = evt.getX();
				thepanel.BL1LieutenantOBJ.intY = evt.getY();
			}
			if(blnMoveBL2L){
				thepanel.BL2LieutenantOBJ.intX = evt.getX();
				thepanel.BL2LieutenantOBJ.intY = evt.getY();
			}
			if(blnMoveBLCap){
				thepanel.BLCaptainOBJ.intX = evt.getX();
				thepanel.BLCaptainOBJ.intY = evt.getY();
			}
			if(blnMoveBLCol){
				thepanel.BLColonelOBJ.intX = evt.getX();
				thepanel.BLColonelOBJ.intY = evt.getY();
			}
			if(blnMoveBL1S){
				thepanel.BL1StarOBJ.intX = evt.getX();
				thepanel.BL1StarOBJ.intY = evt.getY();
			}
			if(blnMoveBL2S){
				thepanel.BL2StarOBJ.intX = evt.getX();
				thepanel.BL2StarOBJ.intY = evt.getY();
			}
			if(blnMoveBL3S){
				thepanel.BL3StarOBJ.intX = evt.getX();
				thepanel.BL3StarOBJ.intY = evt.getY();
			}
			if(blnMoveBL4S){
				thepanel.BL4StarOBJ.intX = evt.getX();
				thepanel.BL4StarOBJ.intY = evt.getY();
			}
			if(blnMoveBL5S){
				thepanel.BL5StarOBJ.intX = evt.getX();
				thepanel.BL5StarOBJ.intY = evt.getY();
			}
			if(blnMoveBLM){
				thepanel.BLMajorOBJ.intX = evt.getX();
				thepanel.BLMajorOBJ.intY = evt.getY();
			}
			if(blnMoveBLF){
				thepanel.BLFlagOBJ.intX = evt.getX();
				thepanel.BLFlagOBJ.intY = evt.getY();
			}
			if(blnMoveBLLC){
				thepanel.BLLColonelOBJ.intX = evt.getX();
				thepanel.BLLColonelOBJ.intY = evt.getY();
			}
			if(blnMoveBLSer){
				thepanel.BLSergeantOBJ.intX = evt.getX();
				thepanel.BLSergeantOBJ.intY = evt.getY();
			}
			if(blnMoveBLSpy1){
				thepanel.BLSpy1OBJ.intX = evt.getX();
				thepanel.BLSpy1OBJ.intY = evt.getY();
			}
			if(blnMoveBLSpy2){
				thepanel.BLSpy2OBJ.intX = evt.getX();
				thepanel.BLSpy2OBJ.intY = evt.getY();
			}
			if(blnMoveBLP1){
				thepanel.BLPrivate1OBJ.intX = evt.getX();
				thepanel.BLPrivate1OBJ.intY = evt.getY();
			}
			if(blnMoveBLP2){
				thepanel.BLPrivate2OBJ.intX = evt.getX();
				thepanel.BLPrivate2OBJ.intY = evt.getY();
			}
			if(blnMoveBLP3){
				thepanel.BLPrivate3OBJ.intX = evt.getX();
				thepanel.BLPrivate3OBJ.intY = evt.getY();
			}
			if(blnMoveBLP4){
				thepanel.BLPrivate4OBJ.intX = evt.getX();
				thepanel.BLPrivate4OBJ.intY = evt.getY();
			}
			if(blnMoveBLP5){
				thepanel.BLPrivate5OBJ.intX = evt.getX();
				thepanel.BLPrivate5OBJ.intY = evt.getY();
			}
			if(blnMoveBLP6){
				thepanel.BLPrivate6OBJ.intX = evt.getX();
				thepanel.BLPrivate6OBJ.intY = evt.getY();
			}
			
		}
	}
	
	/**
	 * Changes the turn after a player has moved a piece
	 * Sends indication of a turn change over the network
	 */
	 
	public void changeTurn(){
		//Method to change turn after player has moved a piece
		if(thepanel.strWhosTurn.equals("White")){
			thepanel.strWhosTurn.equals("Black");
		}else{
			thepanel.strWhosTurn.equals("White");
		}
		ssm.sendText("WhosTurn: " + thepanel.strWhosTurn);
		//Sending change turn indicator over the network
	}
	
	/**
	 * Sends array coordinates, screen coordinates, and status (alive or not) of all white pieces across the network
	 */
	 
	public void sendAllWhite(){
		//Sending array coordinates, x and y coordinates on the screen and status (alive or not) for each white piece over the network
		sendLine = "Setup" + "," + "WHFlag" + "," + ""+thepanel.WHFlagOBJ.intArrayX + "," + ""+thepanel.WHFlagOBJ.intArrayY + "," +thepanel.WHFlagOBJ.blnAlive;
		ssm.sendText(sendLine);
		sendLine = "Setup" + "," + "WHPrivate1" + "," + ""+thepanel.WHPrivate1OBJ.intArrayX + "," + ""+thepanel.WHPrivate1OBJ.intArrayY + "," +thepanel.WHPrivate1OBJ.blnAlive;
		ssm.sendText(sendLine);
		sendLine = "Setup" + "," + "WHPrivate2" + "," + ""+thepanel.WHPrivate2OBJ.intArrayX + "," + ""+thepanel.WHPrivate2OBJ.intArrayY + "," +thepanel.WHPrivate2OBJ.blnAlive;
		ssm.sendText(sendLine);
		sendLine = "Setup" + "," + "WHPrivate3" + "," + ""+thepanel.WHPrivate3OBJ.intArrayX + "," + ""+thepanel.WHPrivate3OBJ.intArrayY + "," +thepanel.WHPrivate3OBJ.blnAlive;
		ssm.sendText(sendLine);
		sendLine = "Setup" + "," + "WHPrivate4" + "," + ""+thepanel.WHPrivate4OBJ.intArrayX + "," + ""+thepanel.WHPrivate4OBJ.intArrayY + "," +thepanel.WHPrivate4OBJ.blnAlive;
		ssm.sendText(sendLine);
		sendLine = "Setup" + "," + "WHPrivate5" + "," + ""+thepanel.WHPrivate5OBJ.intArrayX + "," + ""+thepanel.WHPrivate5OBJ.intArrayY + "," +thepanel.WHPrivate5OBJ.blnAlive;
		ssm.sendText(sendLine);
		sendLine = "Setup" + "," + "WHPrivate6" + "," + ""+thepanel.WHPrivate6OBJ.intArrayX + "," + ""+thepanel.WHPrivate6OBJ.intArrayY + "," +thepanel.WHPrivate6OBJ.blnAlive;
		ssm.sendText(sendLine);
		sendLine = "Setup" + "," + "WHSpy1" + "," + ""+thepanel.WHSpy1OBJ.intArrayX + "," + ""+thepanel.WHSpy1OBJ.intArrayY + "," +thepanel.WHSpy1OBJ.blnAlive;
		ssm.sendText(sendLine);
		sendLine = "Setup" + "," + "WHSpy2" + "," + ""+thepanel.WHSpy2OBJ.intArrayX + "," + ""+thepanel.WHSpy2OBJ.intArrayY + "," +thepanel.WHSpy2OBJ.blnAlive;
		ssm.sendText(sendLine);
		sendLine = "Setup" + "," + "WHSergeant" + "," + ""+thepanel.WHSergeantOBJ.intArrayX + "," + ""+thepanel.WHSergeantOBJ.intArrayY + "," +thepanel.WHSergeantOBJ.blnAlive;
		ssm.sendText(sendLine);
		sendLine = "Setup" + "," + "WHLColonel" + "," + ""+thepanel.WHLColonelOBJ.intArrayX + "," + ""+thepanel.WHLColonelOBJ.intArrayY + "," +thepanel.WHLColonelOBJ.blnAlive;
		ssm.sendText(sendLine);
		sendLine = "Setup" + "," + "WH2Lieutenant" + "," + ""+thepanel.WH2LieutenantOBJ.intArrayX + "," + ""+thepanel.WH2LieutenantOBJ.intArrayY + "," +thepanel.WH2LieutenantOBJ.blnAlive;
		ssm.sendText(sendLine);
		sendLine = "Setup" + "," + "WH1Lieutenant" + "," + ""+thepanel.WH1LieutenantOBJ.intArrayX + "," + ""+thepanel.WH1LieutenantOBJ.intArrayY + "," +thepanel.WH1LieutenantOBJ.blnAlive;
		ssm.sendText(sendLine);
		sendLine = "Setup" + "," + "WHCaptain" + "," + ""+thepanel.WHCaptainOBJ.intArrayX + "," + ""+thepanel.WHCaptainOBJ.intArrayY + "," +thepanel.WHCaptainOBJ.blnAlive;
		ssm.sendText(sendLine);
		sendLine = "Setup" + "," + "WHColonel" + "," + ""+thepanel.WHColonelOBJ.intArrayX + "," + ""+thepanel.WHColonelOBJ.intArrayY + "," +thepanel.WHColonelOBJ.blnAlive;
		ssm.sendText(sendLine);
		sendLine = "Setup" + "," + "WH1Star" + "," + ""+thepanel.WH1StarOBJ.intArrayX + "," + ""+thepanel.WH1StarOBJ.intArrayY + "," +thepanel.WH1StarOBJ.blnAlive;
		ssm.sendText(sendLine);
		sendLine = "Setup" + "," + "WH2Star" + "," + ""+thepanel.WH2StarOBJ.intArrayX + "," + ""+thepanel.WH2StarOBJ.intArrayY + "," +thepanel.WH2StarOBJ.blnAlive;
		ssm.sendText(sendLine);
		sendLine = "Setup" + "," + "WH3Star" + "," + ""+thepanel.WH3StarOBJ.intArrayX + "," + ""+thepanel.WH3StarOBJ.intArrayY + "," +thepanel.WH3StarOBJ.blnAlive;
		ssm.sendText(sendLine);
		sendLine = "Setup" + "," + "WH4Star" + "," + ""+thepanel.WH4StarOBJ.intArrayX + "," + ""+thepanel.WH4StarOBJ.intArrayY + "," +thepanel.WH4StarOBJ.blnAlive;
		ssm.sendText(sendLine);
		sendLine = "Setup" + "," + "WH5Star" + "," + ""+thepanel.WH5StarOBJ.intArrayX + "," + ""+thepanel.WH5StarOBJ.intArrayY + "," +thepanel.WH5StarOBJ.blnAlive;
		ssm.sendText(sendLine);
		sendLine = "Setup" + "," + "WHMajor" + "," + ""+thepanel.WHMajorOBJ.intArrayX + "," + ""+thepanel.WHMajorOBJ.intArrayY + "," +thepanel.WHMajorOBJ.blnAlive;
		ssm.sendText(sendLine);
	}
	
	/**
	 * Sends array coordinates, screen coordinates, and status (alive or not) of all black pieces across the network
	 */
	
	public void sendAllBlack(){
		//Sending array coordinates, x and y coordinates on the screen and status (alive or not) for each black piece over the network
		sendLine = "Setup" + "," + "BLFlag" + "," + ""+thepanel.BLFlagOBJ.intArrayX + "," + ""+thepanel.BLFlagOBJ.intArrayY + "," +thepanel.BLFlagOBJ.blnAlive;
		ssm.sendText(sendLine);
		sendLine = "Setup" + "," + "BLPrivate1" + "," + ""+thepanel.BLPrivate1OBJ.intArrayX + "," + ""+thepanel.BLPrivate1OBJ.intArrayY + "," +thepanel.BLPrivate1OBJ.blnAlive;
		ssm.sendText(sendLine);
		sendLine = "Setup" + "," + "BLPrivate2" + "," + ""+thepanel.BLPrivate2OBJ.intArrayX + "," + ""+thepanel.BLPrivate2OBJ.intArrayY + "," +thepanel.BLPrivate2OBJ.blnAlive;
		ssm.sendText(sendLine);
		sendLine = "Setup" + "," + "BLPrivate3" + "," + ""+thepanel.BLPrivate3OBJ.intArrayX + "," + ""+thepanel.BLPrivate3OBJ.intArrayY + "," +thepanel.BLPrivate3OBJ.blnAlive;
		ssm.sendText(sendLine);
		sendLine = "Setup" + "," + "BLPrivate4" + "," + ""+thepanel.BLPrivate4OBJ.intArrayX + "," + ""+thepanel.BLPrivate4OBJ.intArrayY + "," +thepanel.BLPrivate4OBJ.blnAlive;
		ssm.sendText(sendLine);
		sendLine = "Setup" + "," + "BLPrivate5" + "," + ""+thepanel.BLPrivate5OBJ.intArrayX + "," + ""+thepanel.BLPrivate5OBJ.intArrayY + "," +thepanel.BLPrivate5OBJ.blnAlive;
		ssm.sendText(sendLine);
		sendLine = "Setup" + "," + "BLPrivate6" + "," + ""+thepanel.BLPrivate6OBJ.intArrayX + "," + ""+thepanel.BLPrivate6OBJ.intArrayY + "," +thepanel.BLPrivate6OBJ.blnAlive;
		ssm.sendText(sendLine);
		sendLine = "Setup" + "," + "BLSpy1" + "," + ""+thepanel.BLSpy1OBJ.intArrayX + "," + ""+thepanel.BLSpy1OBJ.intArrayY + "," +thepanel.BLSpy1OBJ.blnAlive;
		ssm.sendText(sendLine);
		sendLine = "Setup" + "," + "BLSpy2" + "," + ""+thepanel.BLSpy2OBJ.intArrayX + "," + ""+thepanel.BLSpy2OBJ.intArrayY + "," +thepanel.BLSpy2OBJ.blnAlive;
		ssm.sendText(sendLine);
		sendLine = "Setup" + "," + "BLSergeant" + "," + ""+thepanel.BLSergeantOBJ.intArrayX + "," + ""+thepanel.BLSergeantOBJ.intArrayY + "," +thepanel.BLSergeantOBJ.blnAlive;
		ssm.sendText(sendLine);
		sendLine = "Setup" + "," + "BLLColonel" + "," + ""+thepanel.BLLColonelOBJ.intArrayX + "," + ""+thepanel.BLLColonelOBJ.intArrayY + "," +thepanel.BLLColonelOBJ.blnAlive;
		ssm.sendText(sendLine);
		sendLine = "Setup" + "," + "BL2Lieutenant" + "," + ""+thepanel.BL2LieutenantOBJ.intArrayX + "," + ""+thepanel.BL2LieutenantOBJ.intArrayY + "," +thepanel.BL2LieutenantOBJ.blnAlive;
		ssm.sendText(sendLine);
		sendLine = "Setup" + "," + "BL1Lieutenant" + "," + ""+thepanel.BL1LieutenantOBJ.intArrayX + "," + ""+thepanel.BL1LieutenantOBJ.intArrayY + "," +thepanel.BL1LieutenantOBJ.blnAlive;
		ssm.sendText(sendLine);
		sendLine = "Setup" + "," + "BLCaptain" + "," + ""+thepanel.BLCaptainOBJ.intArrayX + "," + ""+thepanel.BLCaptainOBJ.intArrayY + "," +thepanel.BLCaptainOBJ.blnAlive;
		ssm.sendText(sendLine);
		sendLine = "Setup" + "," + "BLColonel" + "," + ""+thepanel.BLColonelOBJ.intArrayX + "," + ""+thepanel.BLColonelOBJ.intArrayY + "," +thepanel.BLColonelOBJ.blnAlive;
		ssm.sendText(sendLine);
		sendLine = "Setup" + "," + "BL1Star" + "," + ""+thepanel.BL1StarOBJ.intArrayX + "," + ""+thepanel.BL1StarOBJ.intArrayY + "," +thepanel.BL1StarOBJ.blnAlive;
		ssm.sendText(sendLine);
		sendLine = "Setup" + "," + "BL2Star" + "," + ""+thepanel.BL2StarOBJ.intArrayX + "," + ""+thepanel.BL2StarOBJ.intArrayY + "," +thepanel.BL2StarOBJ.blnAlive;
		ssm.sendText(sendLine);
		sendLine = "Setup" + "," + "BL3Star" + "," + ""+thepanel.BL3StarOBJ.intArrayX + "," + ""+thepanel.BL3StarOBJ.intArrayY + "," +thepanel.BL3StarOBJ.blnAlive;
		ssm.sendText(sendLine);
		sendLine = "Setup" + "," + "BL4Star" + "," + ""+thepanel.BL4StarOBJ.intArrayX + "," + ""+thepanel.BL4StarOBJ.intArrayY + "," +thepanel.BL4StarOBJ.blnAlive;
		ssm.sendText(sendLine);
		sendLine = "Setup" + "," + "BL5Star" + "," + ""+thepanel.BL5StarOBJ.intArrayX + "," + ""+thepanel.BL5StarOBJ.intArrayY + "," +thepanel.BL5StarOBJ.blnAlive;
		ssm.sendText(sendLine);
		sendLine = "Setup" + "," + "BLMajor" + "," + ""+thepanel.BLMajorOBJ.intArrayX + "," + ""+thepanel.BLMajorOBJ.intArrayY + "," +thepanel.BLMajorOBJ.blnAlive;
		ssm.sendText(sendLine);
	}
	
	/**
	 * Method to move a piece forward for both black and white (or client and server)
	 * Considers all possibilities for the potential scenarios (example: if both pieces are of same rank, they both die, etc.)
	 */
	 
	public void moveForward(rank piece){
		//Move forward method
		if(piece.strSide.equals("Black")){
			//For server side
			if(strBBoard[piece.intArrayX][piece.intArrayY - 1] == null){
				//If approaching space is empty
				piece.intY = piece.intY - 70;
				piece.intArrayY = piece.intArrayY - 1;
				strBBoard[piece.intArrayX][piece.intArrayY] = piece;
			}else{
				if(strBBoard[piece.intArrayX][piece.intArrayY - 1].intRank == piece.intRank){
					//If piece and opponent piece have the same rank
					piece.blnAlive = false;
					strBBoard[piece.intArrayX][piece.intArrayY - 1].blnAlive = false;
					strBBoard[piece.intArrayX][piece.intArrayY] = null;
					strBBoard[piece.intArrayX][piece.intArrayY - 1] = null;
				}else if(strBBoard[piece.intArrayX][piece.intArrayY - 1].intRank == 2 && piece.intRank == 1){
					//If piece is a private that beats the opponent's spy
					strBBoard[piece.intArrayX][piece.intArrayY - 1].blnAlive = false;
				}else if(strBBoard[piece.intArrayX][piece.intArrayY + 1].intRank == 1 && piece.intRank == 2){
					//If opponent has a private and your piece is a spy
					piece.blnAlive = false;
				}else if(strBBoard[piece.intArrayX][piece.intArrayY - 1].intRank < piece.intRank){
					//If opponent piece has a lower rank than yours
					strBBoard[piece.intArrayX][piece.intArrayY - 1].blnAlive = false;
					piece.intY = piece.intY - 70;
					piece.intArrayY = piece.intArrayY - 1;
					strBBoard[piece.intArrayX][piece.intArrayY] = piece;
					if(strBBoard[piece.intArrayX][piece.intArrayY - 1].intRank == 0){
						//If opponent piece has been defeated and it was the flag
						thepanel.blnBlackWins = true;
						ssm.sendText("Winner,black");
					}
				}else if(strBBoard[piece.intArrayX][piece.intArrayY - 1].intRank > piece.intRank){
					//If your piece has a lower rank than opponent's
					piece.blnAlive = false;
					strBBoard[piece.intArrayX][piece.intArrayY] = null;
					if(piece.intRank == 0){
						//If your flag has just been defeated
						thepanel.blnWhiteWins = true;
						ssm.sendText("Winner,white");
					}
				}
			}
			
			//Send all white and black array coordinates, screen coordinates and status (alive/not)
			sendAllWhite();
			sendAllBlack();
			
		}else{ // if from White side; same thing as from Black side except colours switch
			if(strWBoard[piece.intArrayX][piece.intArrayY - 1] == null){
				piece.intY = piece.intY - 70;
				piece.intArrayY = piece.intArrayY - 1;
				strWBoard[piece.intArrayX][piece.intArrayY] = piece;
			}else{
				if(strWBoard[piece.intArrayX][piece.intArrayY - 1].intRank == piece.intRank){
					piece.blnAlive = false;
					strWBoard[piece.intArrayX][piece.intArrayY - 1].blnAlive = false;
					strWBoard[piece.intArrayX][piece.intArrayY] = null;
					strWBoard[piece.intArrayX][piece.intArrayY - 1] = null;
				}else if(strWBoard[piece.intArrayX][piece.intArrayY - 1].intRank == 2 && piece.intRank == 1){
					strWBoard[piece.intArrayX][piece.intArrayY - 1].blnAlive = false;
				}else if(strWBoard[piece.intArrayX][piece.intArrayY - 1].intRank == 1 && piece.intRank == 2){
					piece.blnAlive = false;
				}else if(strWBoard[piece.intArrayX][piece.intArrayY - 1].intRank < piece.intRank){
					strWBoard[piece.intArrayX][piece.intArrayY - 1].blnAlive = false;
					piece.intY = piece.intY - 70;
					piece.intArrayY = piece.intArrayY - 1;
					strWBoard[piece.intArrayX][piece.intArrayY] = piece;
					if(strWBoard[piece.intArrayX][piece.intArrayY - 1].intRank == 0){
						thepanel.blnWhiteWins = true;
						ssm.sendText("Winner,white");
					}
				}else if(strWBoard[piece.intArrayX][piece.intArrayY - 1].intRank > piece.intRank){
					piece.blnAlive = false;
					strWBoard[piece.intArrayX][piece.intArrayY] = null;
					if(piece.intRank == 0){
						thepanel.blnBlackWins = true;
						ssm.sendText("Winner,black");
					}
				}
			}
			//If either flag has reached the other side
			if(thepanel.WHFlagOBJ.intArrayY == 0){
				thepanel.blnWhiteWins = true;
				ssm.sendText("Winner,white");
			}else if(thepanel.BLFlagOBJ.intArrayY == 7){
				thepanel.blnBlackWins = true;
				ssm.sendText("Winner,black");
			}
			
			sendAllWhite();
			sendAllBlack();
		}
	}
	
	/**
	 * Method to move a piece backward for both black and white (or client and server)
	 * Considers all possibilities for the potential scenarios (example: if both pieces are of same rank, they both die, etc.)
	 */
	
	public void moveBackward(rank piece){
		//Method to move backward
		//See moveForward() method for similar specifics
		if(piece.strSide.equals("Black")){
			if(strBBoard[piece.intArrayX][piece.intArrayY - 1] == null){
				piece.intY = piece.intY - 70;
				piece.intArrayY = piece.intArrayY - 1;
				strBBoard[piece.intArrayX][piece.intArrayY] = piece;
			}else{
				if(strBBoard[piece.intArrayX][piece.intArrayY - 1].intRank == piece.intRank){
					piece.blnAlive = false;
					strBBoard[piece.intArrayX][piece.intArrayY - 1].blnAlive = false;
					strBBoard[piece.intArrayX][piece.intArrayY] = null;
					strBBoard[piece.intArrayX][piece.intArrayY - 1] = null;
				}else if(strBBoard[piece.intArrayX][piece.intArrayY - 1].intRank == 2 && piece.intRank == 1){
					strBBoard[piece.intArrayX][piece.intArrayY - 1].blnAlive = false;
				}else if(strBBoard[piece.intArrayX][piece.intArrayY - 1].intRank == 1 && piece.intRank == 2){
					piece.blnAlive = false;
				}else if(strBBoard[piece.intArrayX][piece.intArrayY - 1].intRank < piece.intRank){
					strBBoard[piece.intArrayX][piece.intArrayY - 1].blnAlive = false;
					piece.intY = piece.intY - 70;
					piece.intArrayY = piece.intArrayY - 1;
					strBBoard[piece.intArrayX][piece.intArrayY] = piece;
					if(strBBoard[piece.intArrayX][piece.intArrayY - 1].intRank == 0){
						thepanel.blnBlackWins = true;
						ssm.sendText("Winner,black");
					}
				}else if(strBBoard[piece.intArrayX][piece.intArrayY - 1].intRank > piece.intRank){
					piece.blnAlive = false;
					strBBoard[piece.intArrayX][piece.intArrayY] = null;
					if(piece.intRank == 0){
						thepanel.blnWhiteWins = true;
						ssm.sendText("Winner,white");
					}
				}
			}
			
			sendAllWhite();
			sendAllBlack();
			
		}else{ // if from White side
			if(strWBoard[piece.intArrayX][piece.intArrayY - 1] == null){
				piece.intY = piece.intY - 70;
				piece.intArrayY = piece.intArrayY - 1;
				strWBoard[piece.intArrayX][piece.intArrayY] = piece;
			}else{
				if(strWBoard[piece.intArrayX][piece.intArrayY - 1].intRank == piece.intRank){
					piece.blnAlive = false;
					strWBoard[piece.intArrayX][piece.intArrayY - 1].blnAlive = false;
					strWBoard[piece.intArrayX][piece.intArrayY] = null;
					strWBoard[piece.intArrayX][piece.intArrayY - 1] = null;
				}else if(strWBoard[piece.intArrayX][piece.intArrayY - 1].intRank == 2 && piece.intRank == 1){
					strWBoard[piece.intArrayX][piece.intArrayY - 1].blnAlive = false;
				}else if(strWBoard[piece.intArrayX][piece.intArrayY - 1].intRank == 1 && piece.intRank == 2){
					piece.blnAlive = false;
				}else if(strWBoard[piece.intArrayX][piece.intArrayY - 1].intRank < piece.intRank){
					strWBoard[piece.intArrayX][piece.intArrayY - 1].blnAlive = false;
					piece.intY = piece.intY - 70;
					piece.intArrayY = piece.intArrayY - 1;
					strWBoard[piece.intArrayX][piece.intArrayY] = piece;
					if(strWBoard[piece.intArrayX][piece.intArrayY - 1].intRank == 0){
						thepanel.blnWhiteWins = true;
						ssm.sendText("Winner,white");
					}
				}else if(strWBoard[piece.intArrayX][piece.intArrayY - 1].intRank > piece.intRank){
					piece.blnAlive = false;
					strWBoard[piece.intArrayX][piece.intArrayY] = null;
					if(piece.intRank == 0){
						thepanel.blnBlackWins = true;
						ssm.sendText("Winner,black");
					}
				}
			}
			
			if(thepanel.WHFlagOBJ.intArrayY == 0){
				thepanel.blnWhiteWins = true;
				ssm.sendText("Winner,white");
			}else if(thepanel.BLFlagOBJ.intArrayY == 0){
				thepanel.blnBlackWins = true;
				ssm.sendText("Winner,black");
			}
			
			sendAllWhite();
			sendAllBlack();
		}
	}
	
	/**
	 * Method to move a piece a tile to the left for both black and white (or client and server)
	 * Considers all possibilities for the potential scenarios (example: if both pieces are of same rank, they both die, etc.)
	 */
	
	public void moveLeft(rank piece){
		//Method to move left
		//See moveForward() method for similar specifics
		if(piece.strSide.equals("Black")){
			if(strBBoard[piece.intArrayX - 1][piece.intArrayY] == null){
				piece.intX = piece.intX - 70;
				piece.intArrayX = piece.intArrayX - 1;
				strBBoard[piece.intArrayX][piece.intArrayY] = piece;
			}else{
				if(strBBoard[piece.intArrayX - 1][piece.intArrayY].intRank == piece.intRank){
					piece.blnAlive = false;
					strBBoard[piece.intArrayX - 1][piece.intArrayY].blnAlive = false;
					strBBoard[piece.intArrayX][piece.intArrayY] = null;
					strBBoard[piece.intArrayX - 1][piece.intArrayY] = null;
				}else if(strBBoard[piece.intArrayX - 1][piece.intArrayY].intRank == 2 && piece.intRank == 1){
					strBBoard[piece.intArrayX - 1][piece.intArrayY].blnAlive = false;
				}else if(strBBoard[piece.intArrayX - 1][piece.intArrayY].intRank == 1 && piece.intRank == 2){
					piece.blnAlive = false;
				}else if(strBBoard[piece.intArrayX - 1][piece.intArrayY].intRank < piece.intRank){
					strBBoard[piece.intArrayX - 1][piece.intArrayY].blnAlive = false;
					piece.intX = piece.intX - 70;
					piece.intArrayX = piece.intArrayX - 1;
					strBBoard[piece.intArrayX][piece.intArrayY] = piece;
					if(strBBoard[piece.intArrayX - 1][piece.intArrayY].intRank == 0){
						thepanel.blnBlackWins = true;
						ssm.sendText("Winner,black");
					}
				}else if(strBBoard[piece.intArrayX - 1][piece.intArrayY].intRank > piece.intRank){
					piece.blnAlive = false;
					strBBoard[piece.intArrayX][piece.intArrayY] = null;
					if(piece.intRank == 0){
						thepanel.blnWhiteWins = true;
						ssm.sendText("Winner,white");
					}
				}
			}
			
			sendAllWhite();
			sendAllBlack();
			
		}else{ // if from White side
			if(strWBoard[piece.intArrayX - 1][piece.intArrayY] == null){
				piece.intX = piece.intX - 70;
				piece.intArrayX = piece.intArrayX - 1;
				strWBoard[piece.intArrayX][piece.intArrayY] = piece;
			}else{
				if(strWBoard[piece.intArrayX - 1][piece.intArrayY].intRank == piece.intRank){
					piece.blnAlive = false;
					strWBoard[piece.intArrayX - 1][piece.intArrayY].blnAlive = false;
					strWBoard[piece.intArrayX][piece.intArrayY] = null;
					strWBoard[piece.intArrayX - 1][piece.intArrayY] = null;
				}else if(strWBoard[piece.intArrayX - 1][piece.intArrayY].intRank == 2 && piece.intRank == 1){
					strWBoard[piece.intArrayX - 1][piece.intArrayY].blnAlive = false;
				}else if(strWBoard[piece.intArrayX - 1][piece.intArrayY].intRank == 1 && piece.intRank == 2){
					piece.blnAlive = false;
				}else if(strWBoard[piece.intArrayX - 1][piece.intArrayY].intRank < piece.intRank){
					strWBoard[piece.intArrayX - 1][piece.intArrayY].blnAlive = false;
					piece.intX = piece.intX - 70;
					piece.intArrayX = piece.intArrayX - 1;
					strWBoard[piece.intArrayX][piece.intArrayY] = piece;
					if(strWBoard[piece.intArrayX - 1][piece.intArrayY].intRank == 0){
						thepanel.blnWhiteWins = true;
						ssm.sendText("Winner,white");
					}
				}
			}
			
			if(thepanel.WHFlagOBJ.intArrayY == 0){
				thepanel.blnWhiteWins = true;
				ssm.sendText("Winner,white");
			}else if(thepanel.BLFlagOBJ.intArrayY == 0){
				thepanel.blnBlackWins = true;
				ssm.sendText("Winner,black");
			}
			
			sendAllWhite();
			sendAllBlack();
		}
	}
	
	/**
	 * Method to move a piece a tile to the right for both black and white (or client and server)
	 * Considers all possibilities for the potential scenarios (example: if both pieces are of same rank, they both die, etc.)
	 */
	
	public void moveRight(rank piece){
		//Method to move right
		//See moveForward() method for similar specifics
		if(piece.strSide.equals("Black")){
			if(strBBoard[piece.intArrayX + 1][piece.intArrayY] == null){
				piece.intX = piece.intX + 70;
				piece.intArrayX = piece.intArrayX + 1;
				strBBoard[piece.intArrayX][piece.intArrayY] = piece;
			}else{
				if(strBBoard[piece.intArrayX + 1][piece.intArrayY].intRank == piece.intRank){
					piece.blnAlive = false;
					strBBoard[piece.intArrayX + 1][piece.intArrayY].blnAlive = false;
					strBBoard[piece.intArrayX][piece.intArrayY] = null;
					strBBoard[piece.intArrayX + 1][piece.intArrayY] = null;
				}else if(strBBoard[piece.intArrayX + 1][piece.intArrayY].intRank == 2 && piece.intRank == 1){
					strBBoard[piece.intArrayX + 1][piece.intArrayY].blnAlive = false;
				}else if(strBBoard[piece.intArrayX + 1][piece.intArrayY].intRank == 1 && piece.intRank == 2){
					piece.blnAlive = false;
				}else if(strBBoard[piece.intArrayX + 1][piece.intArrayY].intRank < piece.intRank){
					strBBoard[piece.intArrayX + 1][piece.intArrayY].blnAlive = false;
					piece.intX = piece.intX + 70;
					piece.intArrayX = piece.intArrayX + 1;
					strBBoard[piece.intArrayX][piece.intArrayY] = piece;
					if(strBBoard[piece.intArrayX + 1][piece.intArrayY].intRank == 0){
						thepanel.blnBlackWins = true;
						ssm.sendText("Winner,black");
					}
				}else if(strBBoard[piece.intArrayX + 1][piece.intArrayY].intRank > piece.intRank){
					piece.blnAlive = false;
					strBBoard[piece.intArrayX][piece.intArrayY] = null;
					if(piece.intRank == 0){
						thepanel.blnWhiteWins = true;
						ssm.sendText("Winner,white");
					}
				}
			}
			
			sendAllWhite();
			sendAllBlack();
			
		}else{ // if from White side
			if(strWBoard[piece.intArrayX + 1][piece.intArrayY] == null){
				piece.intX = piece.intX + 70;
				piece.intArrayX = piece.intArrayX + 1;
				strWBoard[piece.intArrayX][piece.intArrayY] = piece;
			}else{
				if(strWBoard[piece.intArrayX + 1][piece.intArrayY].intRank == piece.intRank){
					piece.blnAlive = false;
					strWBoard[piece.intArrayX + 1][piece.intArrayY].blnAlive = false;
					strWBoard[piece.intArrayX][piece.intArrayY] = null;
					strWBoard[piece.intArrayX + 1][piece.intArrayY] = null;
				}else if(strWBoard[piece.intArrayX + 1][piece.intArrayY].intRank == 2 && piece.intRank == 1){
					strWBoard[piece.intArrayX + 1][piece.intArrayY].blnAlive = false;
				}else if(strWBoard[piece.intArrayX + 1][piece.intArrayY].intRank == 1 && piece.intRank == 2){
					piece.blnAlive = false;
				}else if(strWBoard[piece.intArrayX + 1][piece.intArrayY].intRank < piece.intRank){
					strWBoard[piece.intArrayX + 1][piece.intArrayY].blnAlive = false;
					piece.intX = piece.intX + 70;
					piece.intArrayX = piece.intArrayX + 1;
					strWBoard[piece.intArrayX][piece.intArrayY] = piece;
					if(strWBoard[piece.intArrayX + 1][piece.intArrayY].intRank == 0){
						thepanel.blnWhiteWins = true;
						ssm.sendText("Winner,white");
					}
				}
			}
			
			if(thepanel.WHFlagOBJ.intArrayY == 0){
				thepanel.blnWhiteWins = true;
				ssm.sendText("Winner,white");
			}else if(thepanel.BLFlagOBJ.intArrayY == 0){
				thepanel.blnBlackWins = true;
				ssm.sendText("Winner,black");
			}
			
			sendAllWhite();
			sendAllBlack();
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
		
		wDoneSetUp = new JButton("Ready");
		wDoneSetUp.setBounds(211,686,65,20);
		wDoneSetUp.setVisible(false);
		wDoneSetUp.addActionListener(this);
		
		bDoneSetUp = new JButton("Ready");
		bDoneSetUp.setBounds(211,686,65,20);
		bDoneSetUp.setVisible(false);
		bDoneSetUp.addActionListener(this);
		
		BriefRules = new JTextArea(strBriefRules);
		BriefRules.setLineWrap(true);
		BriefRules.setWrapStyleWord(true);
		BriefRules.setVisible(false);
		BriefRules.setEditable(false);
		
		BriefRulesScroll = new JScrollPane(BriefRules);
		BriefRulesScroll.setSize(300,400);
		BriefRulesScroll.setLocation(960,40);
		BriefRulesScroll.setVisible(false);
		
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
		thepanel.add(wDoneSetUp);
		thepanel.add(bDoneSetUp);
		thepanel.add(BriefRulesScroll);
		
		theframe.addKeyListener(this);
		
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
