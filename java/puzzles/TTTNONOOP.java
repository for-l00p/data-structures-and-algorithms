
import java.util.*;

public class TTTNONOOP{

	// variables representing players
	public static final int NOUGHT = 0;
	public static final int CROSS = 1;
	public static final int EMPTY = 2;


	//varibales representing game state
	//
	public static final int DRAWN = 2;
	public static final int NOUGHT_WON = 0;
	public static final int CROSS_WON = 1;
	public static final int GAME_IN_PROGRESS = 3;


	// Representation of Board state
	 
	public static int[][] board = new int[3][3];
	public static int gameState;

	//variable to store turn state
	public static int currentPlayer;


		private static void initGame(){

			for(int i = 0; i < 3; i++){
				for (int j = 0; j < 3; j++){
					board[i][j] = EMPTY;
				}
			}

			gameState = GAME_IN_PROGRESS;
			currentPlayer = NOUGHT;

		}


		public static void playerMove(int player,  int rowIndex, int columnIndex){
		
			if(board[rowIndex][columnIndex] != EMPTY){
			 	throw new Error("Incorrect turn");
			}
			//throw error if index is out of bounds 
			board[rowIndex][columnIndex] = player;
			updateGameState(player, rowIndex, columnIndex);
		}

		//
		private static void updateGameState(int player,  int rowIndex, int columnIndex){
			//Either check the whole from scratch() or check only the rows/columns/diagnal affected by inputs
			if (hasWon(player, rowIndex, columnIndex)){
				gameState = (player == NOUGHT)? NOUGHT_WON: CROSS_WON;
			} else if (isDraw()){
				gameState = DRAWN;
			}
		}

		private static boolean hasWon(int player,  int rowIndex, int columnIndex){
			System.out.println("called with" + rowIndex+columnIndex+player);
			  return (board[rowIndex][0] == player         // 3-in-the-row
                   && board[rowIndex][1] == player
                   && board[rowIndex][2] == player
              || board[0][columnIndex] == player      // 3-in-the-column
                   && board[1][columnIndex] == player
                   && board[2][columnIndex] == player
              || rowIndex == columnIndex            // 3-in-the-diagonal
                   && board[0][0] == player
                   && board[1][1] == player
                   && board[2][2] == player
              || rowIndex + columnIndex == 2  // 3-in-the-opposite-diagonal
                   && board[0][2] == player
                   && board[1][1] == player
                   && board[2][0] == player);
   		}

   		private static boolean isDraw(){
 			for (int row = 0; row < 3; ++row) {
         		for (int col = 0; col < 3; ++col) {
           			 if (board[row][col] == EMPTY) {
               			return false;  // an empty cell found, not draw, exit
            		}
         		}
      		}
      		return true;  
		}




	public static void main(String args[]){

		Scanner in = new Scanner(System.in);
		initGame();
	
		do {
				// Prompt for currentplayer move: "Turn for: " + currentPlayer + " Please enter (row, column)"
				if(currentPlayer == NOUGHT){
					System.out.print("Player 'O', enter your move (row[1-3] column[1-3]): ");
				} else {
					System.out.print("Player 'X', enter your move (row[1-3] column[1-3]): ");
				}

				int rowIndex = in.nextInt() - 1;
				int columnIndex = in.nextInt() - 1;
				playerMove(currentPlayer, rowIndex, columnIndex);
				if(currentPlayer == NOUGHT){
					currentPlayer = CROSS;
				} else {
					currentPlayer = NOUGHT;
				}

				if(gameState == NOUGHT_WON){
					System.out.println("NOUGHT_WON");
				} else if (gameState == CROSS_WON){
					System.out.println("CROSS_WON");
				} else if (gameState == DRAWN){
					System.out.println("GAME DRAWN");
				} 
		} while (gameState == GAME_IN_PROGRESS);
			
	}

}