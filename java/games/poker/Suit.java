//A Suit is useless without a Card, so Suit should be in the same package or a sub-package as Card. Hand Class needs to know about Suit class?
//Classes like Player, Round, Poker  should not have access to Suit class. 

package games.poker;

enum Suit {
	CLUB, 
	DIAMOND, 
	HEART, 
	SPADE;
}