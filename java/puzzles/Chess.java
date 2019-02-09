


class Chess{


/**
 *
 * Refereces:


https://stackoverflow.com/questions/4168002/object-oriented-design-for-a-chess-game
 
NOTES:

Approach One: List all use cases. Think of entities involved: each excapsulating state and behavior. Then make classes.

I think of objects, their state, and behavior. so "move" is a behavior that an object "a piece" does, which involves state change of the board. So here a a piece hasreference to a square it is on, as well as the board.

 class Piece {
	- square
	- colour
	- board

	void setSquare(Square s)
	virtual void TryToMove(Square s)
 }

Rook, King, Queen, Pawn etc. inherits from Piece and implement their own TryToMove methods

Rook extends Piece {
	
	implements TrytoMove()
}


Approach Two: TDD https://codereview.stackexchange.com/questions/71790/design-a-chess-game-using-object-oriented-principles

API: 

Game (with its board, castle flags, currentPlayer) etc. simply store the state of the game. You can manipulate them to e.g. set up a position, if that's what you want. 

Game.move(Square s, Square t).

// what does this need?:
is move valid? One way to do it is to implement Piece.isValid(board, squareOne, squareTwo). We could implement this as a pure method Piece.isValid(squareOne, squareTwo) without the board, but we need to kno if any piece of the path is obstructing the move. For that, we need to find the Path. A good way to solve this problem woudl be: Pieces should not handle move. Pieces can provide list of possible moves to reach the destination path but board should choose a valid path.
ifNewSpot contains a piece,
Board.isChecked()






Here we think of Piece as self-sufficient entitties existing regardless of a board.






Who is responsbible for what? How do objects pass messages?


Could a move can be thought of as a first-class object of its own. 

	//
Should a player class hold a reference to all the pieces it owns?

 */
	enum Colour {
		WHITE, BLACK
	}

	enum PieceType {
		EMPTY, ROOK, QUEEN, KING, PAWN, KNIGHT, CASTLE
	}

	class Square {
		Piece piece;
		Colour colour;
	}

/**GameRules interface that is responsible for:

Determining what moves are valid, including castling and en passant.
Determining if a specific move is valid.
Determining when players are in check/checkmate/stalemate.
Executing moves.
Separating the rules from the game/board classes also means you can implement variants relatively easily. All methods of the rules interface take a Game object which they can inspect to determine which moves are valid.

**/

	interface Rules{


		// List<Moves> findElegibleMoves (Game game, Piece piece)
	}


	class Move{

	}
	

	/**
	 * 
	 */

	class Board{
		Square[][] squares = new Square[8][8];

		public Board(){
			for (int i = 0; i < 8; i++ ){
				for (int j = 0; j < 8; j++){
					if (i & 1 == j & 1){
						squares[i][j].colour = Colour.WHITE;
					} else {
						squares[i][j].colour = Colour.BLACK;
					}			
				}
			}
		}
	}



//Question: All are pieces. Should piece be an abstract class or interface? 
//  Should Rook extend an abstract class Piece or should Rook implement an interface Piece?  Should Rook contain an object of class Piece? Rook is-a-piece, or Rook-behaves-like-interface Piece, or piece: has a type Rook or Rook: has a piece.
//  Since colour be an attribute common to all pieces, where should it be included? Abstract class piece? Should BlackRook extend Rook, or Should colour be a field of every Piece, or should BlackRook contain Black and a Rook objects? 




	class Piece {
		Colour colour;
		PieceType pieceType;

		//how to define moves void classicMove();
	}


	

	//ChessGame needs a board (aggregation)
	class Game {
		Board board;
		Colour currentPlayer;
		GameStatus gameStatus;


		//castled? variables

		enum GameStatus {
			WHITE_WON, BLACK_WON, GAME_IN_PROGRESS, DRAW
		}
		public Game(Board board){
			this.board = board;
			currentPlayer = Colour.WHITE;
			gameStatus = GameStatus.GAME_IN_PROGRESS;

		}






	}






}