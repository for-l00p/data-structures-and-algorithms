/**
 
 
Refereces:


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



// what does this need?:
is move valid? One way to do it is to implement Piece.isValid(board, squareOne, squareTwo). We could implement this as a pure method Piece.isValid(squareOne, squareTwo) without the board, but we need to kno if any piece of the path is obstructing the move. For that, we need to find the Path. A good way to solve this problem woudl be: Pieces should not handle move. Pieces can provide list of possible moves to reach the destination path but board should choose a valid path.
ifNewSpot contains a piece,
Board.isChecked()


Could a move can be thought of as a first-class object of its own. 
Should a player class hold a reference to all the pieces it owns?

Determining what moves are valid, including castling and en passant.
Determining if a specific move is valid.
Determining when players are in check/checkmate/stalemate.
Executing moves.
Separating the rules from the game/board classes also means you can implement variants relatively easily. All methods of the rules interface take a Game object which they can inspect to determine which moves are valid.

 */


package games.chess;


final class Chess {

	interface Rules {
		// List<Moves> findElegibleMoves (Game game, Piece piece)
	}

	class Move {

	}
	
	
}