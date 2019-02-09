import java.util.*;



public class TicTacToe{

	// Use a non-static nested class (or inner class) if you require access to an enclosing instance's non-public fields and methods. Use a static nested class if you don't require this access.

	enum PlayerType {
		NOUGHT, CROSS, EMPTY
	};

	private class Cell{

		private PlayerType content;
		int rowIndex, columnIndex;

		public Cell(int rowIndex, int columnIndex){
			this.rowIndex = rowIndex;
			this.columnIndex = columnIndex;
			content = PlayerType.EMPTY;
		}

		public void setContent(PlayerType player){
			this.content = player;
		}
	}

	class Board {

		private Cell[][] cells = new Cell[3][3];
		public Board(){
			for (int i = 0; i < 3; i++){
				for (int j = 0; j < 3; j++){
					cells[i][j] = new Cell(i,j);
					cells[i][j].content = PlayerType.EMPTY;
				}
			}
		}
	}

	class GameEngine {
		//Should these be static? 
		private PlayerType currentPlayer;
		private GameState gameState;
		private Board board;

		enum GameState {
			GAME_IN_PROGRESS, NOUGHT_WIN, CROSS_WIN, DRAW
		}
		
		//Is this a factory method pattern? Should this be sttic
		public void init(){
			gameState = GameState.GAME_IN_PROGRESS;
			currentPlayer = PlayerType.NOUGHT;
			board = new Board();

		}

		public void playerMove(PlayerType player,  int rowIndex, int columnIndex){
		
			if(board[rowIndex][columnIndex] != PlayerType.EMPTY){
			 	throw new Error("Incorrect turn");
			}
			//throw error if index is out of bounds 
			
			board[rowIndex][columnIndex].setContent(player);
			updateGameState(player, rowIndex, columnIndex);
		}

		//
		private void updateGameState(PlayerType player,  int rowIndex, int columnIndex){
			//Either check the whole from scratch() or check only the rows/columns/diagnal affected by inputs
			if (hasWon(player, rowIndex, columnIndex)){
				gameState = (player == PlayerType.NOUGHT)? GameState.NOUGHT_WIN: GameState.CROSS_WIN;
			} else if (isDraw()){
				gameState =  GameState.DRAW;
			}
		}

		private boolean hasWon(PlayerType player,  int rowIndex, int columnIndex){
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

   		private  boolean isDraw(){
 			for (int row = 0; row < 3; ++row) {
         		for (int col = 0; col < 3; ++col) {
           			 if (board[row][col] == PlayerType.EMPTY) {
               			return false;  // an empty cell found, not draw, exit
            		}
         		}
      		}
      		return true;  
		}


		public void runGame(){

			GameEngine.init();
			Scanner in = new Scanner(System.in);

			do {
				if(GameEngine.currentPlayer == PlayerType.NOUGHT){
						System.out.print("Player 'O', enter your move (row[1-3] column[1-3]): ");
				} else {
						System.out.print("Player 'X', enter your move (row[1-3] column[1-3]): ");
				}

				boolean validInput = false;

				do {
					int rowIndex = in.nextInt() - 1;
					int columnIndex = in.nextInt() - 1;

					if (rowIndex >= 0 && row < 3 && columns >= 0 && columnIndex < 3 && board.cells[rowIndex][columnIndex].content == PlayerType.EMPTY) {
            				GameEngine.playerMove(GameEngine.currentPlayer, rowIndex, columnIndex);
           				 	validInput = true; // input okay, exit loop
         			} else {
            			System.out.println("This move at (" + (rowIndex + 1) + "," + (columnIndex + 1)+ ") is not valid. Try again...");
            		}
				} while (validInput == false);
				
				if(GameEngine.currentPlayer == PlayerType.NOUGHT){
						GameEngine.currentPlayer = PlayerType.CROSS;
					} else {
						GameEngine.currentPlayer = PlayerType.NOUGHT;
					}

				if(GameEngine.gameState == NOUGHT_WON){
					System.out.println("NOUGHT_WON");
				} else if (GameEngine.gameState == CROSS_WON){
					System.out.println("CROSS_WON");
				} else if (GameEngine.gameState == DRAWN){
					System.out.println("GAME DRAWN");
				} 

			} while (GameEngine.gameState == GameState.GAME_IN_PROGRESS);
			
		}
	}


	public static void main(String [] args){
		GameEngine game = new GameEngine();
		game.runGame();
	}




}