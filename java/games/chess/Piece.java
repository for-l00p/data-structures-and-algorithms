//Question: All are pieces. Should piece be an abstract class or interface? 
//  Should Rook extend an abstract class Piece or should Rook implement an interface Piece?  Should Rook contain an object of class Piece? Rook is-a-piece, or Rook-behaves-like-interface Piece, or piece: has a type Rook or Rook: has a piece.
//  Since colour be an attribute common to all pieces, where should it be included? Abstract class piece? Should BlackRook extend Rook, or Should colour be a field of every Piece, or should BlackRook contain Black and a Rook objects? 
//Here we think of Piece as self-sufficient entitties existing regardless of a board.

class Piece {
	Colour colour;
	PieceType pieceType;

		//how to define moves void classicMove();
}