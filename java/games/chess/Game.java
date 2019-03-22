/**
 * API: 

Game (with its board, castle flags, currentPlayer) etc. simply store the state of the game. You can manipulate them to e.g. set up a position, if that's what you want. 

Game.move(Square s, Square t).
 */

class Game {
	Board board;
	Colour currentPlayer;
	GameStatus gameStatus;

		//castled? variables
	public Game(Board board){
		this.board = board;
		currentPlayer = Colour.WHITE;
		gameStatus = GameStatus.GAME_IN_PROGRESS;
	}

	
}