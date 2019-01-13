// Construct all objects for each piece
// Use each of the black and white pieces

public class pieces{
	public static void main(String[] args){
		
		// BLACK PIECES
		rank BLFlag = new rank(true,0,0,0);
		rank BLPrivate1 = new rank(true,1,1,0);
		rank BLPrivate2 = new rank(true,1,2,0);
		rank BLPrivate3 = new rank(true,1,3,0);
		rank BLPrivate4 = new rank(true,1,4,0);
		rank BLPrivate5 = new rank(true,1,5,0);
		rank BLPrivate6 = new rank(true,1,6,0);
		rank BLSpy1 = new rank(true,2,7,0);
		rank BLSpy2 = new rank(true,2,8,0);
		rank BLSergeant1 = new rank(true,3,0,1);
		rank BLSergeant2 = new rank(true,3,1,1);
		rank BL2Lieutenant = new rank(true,4,2,1);
		rank BL1Lieutenant = new rank(true,5,3,1);
		rank BLCaptain = new rank(true,6,4,1);
		rank BLColonel = new rank(true,9,5,1);
		rank BL1Star = new rank(true,10,6,1);
		rank BL2Star = new rank(true,11,7,1);
		rank BL3Star = new rank(true,12,8,1);
		rank BL4Star = new rank(true,13,0,2);
		rank BL5Star = new rank(true,14,1,2);
		
		// WHITE PIECES
		rank WHFlag = new rank(true,0,0,7);
		rank WHPrivate1 = new rank(true,1,1,7);
		rank WHPrivate2 = new rank(true,1,2,7);
		rank WHPrivate3 = new rank(true,1,3,7);
		rank WHPrivate4 = new rank(true,1,4,7);
		rank WHPrivate5 = new rank(true,1,5,7);
		rank WHPrivate6 = new rank(true,1,6,7);
		rank WHSpy1 = new rank(true,2,7,7);
		rank WHSpy2 = new rank(true,2,8,7);
		rank WHSergeant1 = new rank(true,3,0,6);
		rank WHSergeant2 = new rank(true,3,1,6);
		rank WH2Lieutenant = new rank(true,4,2,6);
		rank WH1Lieutenant = new rank(true,5,3,6);
		rank WHCaptain = new rank(true,6,4,6);
		rank WHColonel = new rank(true,9,5,6);
		rank WH1Star = new rank(true,10,6,6);
		rank WH2Star = new rank(true,11,7,6);
		rank WH3Star = new rank(true,12,8,6);
		rank WH4Star = new rank(true,13,0,5);
		rank WH5Star = new rank(true,14,1,5);
		
	}
}
