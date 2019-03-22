

package games.tictactoe;

import java.util.Scanner;



public class TicTacToe {

	// Use a non-static nested class (or inner class) if you require access to an enclosing instance's non-public fields and methods. Use a static nested class if you don't require this access.

	private TicTacToe(){
	}

	enum PlayerType {
		NOUGHT, CROSS, EMPTY
	};


	private static class Cell {

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

	private static class Board {

		private Cell[][] cells = new Cell[3][3]; //We could just do PlayerType[][] cells. But this is more modular. For example, if in the future, we do not want the players to mark O and X, but instead write down their name, we would just need to change the cell datatype (specifically, its content field.) without changing Board or GameEngine.

		public Board(){
			for (int i = 0; i < 3; i++){
				for (int j = 0; j < 3; j++){
					cells[i][j] = new Cell(i,j);
				}
			}
		}

	}

	enum GameState {
		GAME_IN_PROGRESS, NOUGHT_WIN, CROSS_WIN, DRAW
	}

	 private static class GameEngine {
		//Should this be static? 
		private PlayerType currentPlayer;
		private GameState gameState;
		private Board board; //We could have done PlayerType[][] board. But this encapsulates (abstracs away) the implementation details of the Board, making the code more modular/decreasing coupling. This reduces bugs, makes the code more extensible, and easy to understand. E.g. there is no bug introduced because of the coupling. 

		//Dependency injection:  Initializing the Board, playertype etc is somebody else's problem!
		// - Allows the single responsibility printiple: creation is separated from use. GameEngine has a single responsibility:  given a board and players, implement the rules of the game,
		//   If later we decided to change how what  dependencies a Board has (e.g. it has parameter a Board needs to be constructed (colour etc.)) , we ought not to need to change Game Engine.  Via this decoupling/modularity is achieved. 
		// Also, how is intial player decided?Makes this class more cohesive and self contained
		// (Dependency injection means giving an object its instance variables)

		
		public void init(PlayerType player, Board inputBoard) {
			gameState = GameState.GAME_IN_PROGRESS;
			currentPlayer = player;
			board = inputBoard;
		}

		//All non-static methods. Why?

		public void playerMove(PlayerType player,  int rowIndex, int columnIndex) {
		
			// if(board[rowIndex][columnIndex] != PlayerType.EMPTY){
			//  	throw new Error("Incorrect turn");
			// }
			//throw error if index is out of bounds 
			
			board.cells[rowIndex][columnIndex].setContent(player);
			updateGameState(player, rowIndex, columnIndex);
			switchCurrentPlayer();
		}


		private void switchCurrentPlayer(){
			if(currentPlayer == PlayerType.NOUGHT){
				currentPlayer = PlayerType.CROSS;
			} else {
				currentPlayer = PlayerType.NOUGHT;
			}
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
			  return (board.cells[rowIndex][0].content == player         // 3-in-the-row
                   && board.cells[rowIndex][1].content == player
                   && board.cells[rowIndex][2].content == player
              || board.cells[0][columnIndex].content == player      // 3-in-the-column
                   && board.cells[1][columnIndex].content == player
                   && board.cells[2][columnIndex].content == player
              || rowIndex == columnIndex            // 3-in-the-diagonal
                   && board.cells[0][0].content == player
                   && board.cells[1][1].content == player
                   && board.cells[2][2].content == player
              || rowIndex + columnIndex == 2  // 3-in-the-opposite-diagonal
                   && board.cells[0][2].content == player
                   && board.cells[1][1].content == player
                   && board.cells[2][0].content == player);
   		}

   		private  boolean isDraw(){
 			for (int row = 0; row < 3; ++row) {
         		for (int col = 0; col < 3; ++col) {
           			 if (board.cells[row][col].content == PlayerType.EMPTY) {
               			return false;  // an empty cell found, not draw, exit
            		}
         		}
      		}
      		return true;  
		}

	}

	public static void runGame(){

			GameEngine game = new GameEngine();
			//this still sucks because everything has to be done manually (also, because the caller can hold a reference to our internal objects and thus invalidate our state).
			game.init(PlayerType.NOUGHT, new Board());
			Scanner in = new Scanner(System.in);

			do {
				if(game.currentPlayer == PlayerType.NOUGHT){
						System.out.print("Player 'O', enter your move (row[1-3] column[1-3]): ");
				} else {
						System.out.print("Player 'X', enter your move (row[1-3] column[1-3]): ");
				}

				boolean validInput = false;

				do {
					int rowIndex = in.nextInt() - 1;
					int columnIndex = in.nextInt() - 1;

					if (rowIndex >= 0 && rowIndex < 3 && columnIndex >= 0 && columnIndex < 3 && game.board.cells[rowIndex][columnIndex].content == PlayerType.EMPTY) {
            				game.playerMove(game.currentPlayer, rowIndex, columnIndex);
           				 	validInput = true; // input okay, exit loop
         			} else {
            			System.out.println("This move at (" + (rowIndex + 1) + "," + (columnIndex + 1)+ ") is not valid. Try again...");
            		}
				} while (validInput == false);
				
				if(game.gameState == GameState.NOUGHT_WIN){
					System.out.println("NOUGHT_WON");
				} else if (game.gameState == GameState.CROSS_WIN){
					System.out.println("CROSS_WON");
				} else if (game.gameState == GameState.DRAW){
					System.out.println("GAME DRAWN");
				} 

			} while (game.gameState == GameState.GAME_IN_PROGRESS);
			
		}


	public static void main(String [] args){
		TicTacToe.runGame(); //  runGame is a static method of class TicTacToe. As part of its execution, creates a new GameEngine instance.  GameEngine in a static nested class which saves state in non-static member variables (similar to Board and Cell: both static classes which save state in non-static member variables.)  GameEngine has been made static because it does not require access to an instance (or non-static members) of its outer class TicTacToe (a TicTacToe object). It has its own Board, currentPlayer and currentState, so making it static reduces coupling and lessens complications.  Would this cause  multiple instances of TicTacToe to share state? err...Maybe No, because the member variables of GameEngine are themselves not static.
		
		//  https://softwareengineering.stackexchange.com/questions/238782/why-prefer-non-static-inner-classes-over-static-ones
	}




}