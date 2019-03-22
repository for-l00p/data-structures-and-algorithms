package tests;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.Ignore;

import puzzles.TennisGame;


public class TennisTest {
	
	static final String LOVE = "love";
	static final String FIFTEEN = "fifteen";
	static final String THIRTY = "thirty";
	static final String FORTY = "forty";
	static final String DEUCE = "deuce";
	static final String ADVANTAGE = "advantage";


	@Test 
	public void shouldReturnCorrectForPlayerOnePoint(){

		String playerOne = "Federer";
		String playerTwo = "Nadal";
		TennisGame game = new TennisGame(playerOne, playerTwo);
		game.score(playerOne);
		String expected = playerOne + " " + FIFTEEN + "-" + LOVE + " " + playerTwo;
		assertEquals(expected, game.printScore());	
	}

	@Test 
	public void shouldReturnCorrectForPlayerTwoPoint(){

		String playerOne = "Federer";
		String playerTwo = "Nadal";
		TennisGame game = new TennisGame(playerOne, playerTwo);
		game.score(playerTwo);
		String expected = playerOne + " " + LOVE + "-" + FIFTEEN + " " + playerTwo;
		assertEquals(expected, game.printScore());	
	}

	@Test 
	public void shouldReturnCorrectForPlayerEqual(){

		String playerOne = "Federer";
		String playerTwo = "Nadal";
		TennisGame game = new TennisGame(playerOne, playerTwo);
		
		game.score(playerOne);
		game.score(playerTwo);		

		String expected = playerOne + " " + FIFTEEN + "-" + FIFTEEN + " " + playerTwo;
		assertEquals(expected, game.printScore());	
	}


	@Test 
	public void shouldReturnCorrectForDeuce(){
		String playerOne = "Federer";
		String playerTwo = "Nadal";
		TennisGame game = new TennisGame(playerOne, playerTwo);
		for (int i = 0; i < 5; i++){
			game.score(playerOne);
			game.score(playerTwo);
		}
	
		String expected = DEUCE;
		assertEquals(expected, game.printScore());	
	}


	@Test 
	public void shouldReturnCorrectForPlayerOneAdvantage(){

		String playerOne = "Federer";
		String playerTwo = "Nadal";
		TennisGame game = new TennisGame(playerOne, playerTwo);
		for (int i = 0; i < 5; i++){
			game.score(playerOne);
			game.score(playerTwo);
		}
		game.score(playerOne);
		String expected = playerOne + " " + ADVANTAGE;
		assertEquals(expected, game.printScore());	
	}

	@Test 
	public void shouldReturnCorrectForPlayerTwoAdvantage(){

		String playerOne = "Federer";
		String playerTwo = "Nadal";
		TennisGame game = new TennisGame(playerOne, playerTwo);
		for (int i = 0; i < 5; i++){
			game.score(playerOne);
			game.score(playerTwo);
		}
		game.score(playerTwo);

		String expected = playerTwo + " " + ADVANTAGE;
		assertEquals(expected, game.printScore());	
	}


	@Test 
	public void shouldReturnCorrectForPlayerOneWin(){

		String playerOne = "Federer";
		String playerTwo = "Nadal";
		TennisGame game = new TennisGame(playerOne, playerTwo);
		for (int i = 0; i < 3; i++){
			game.score(playerOne);
			game.score(playerTwo);
		}
		game.score(playerOne);
		game.score(playerOne);

		String expected = playerOne + " WON";
		assertEquals(expected, game.printScore());	
	}


	@Test 
	public void shouldReturnCorrectForPlayerTwoWin(){

		String playerOne = "Federer";
		String playerTwo = "Nadal";
		TennisGame game = new TennisGame(playerOne, playerTwo);
		for (int i = 0; i < 3; i++){			
			game.score(playerTwo);
		}
		game.score(playerOne);
		game.score(playerTwo);

		String expected = playerTwo + " WON";
		assertEquals(expected, game.printScore());	
	}


	@Test 
	public void shouldReturnCorrectForAdvantage(){

		String playerOne = "Federer";
		String playerTwo = "Nadal";
		TennisGame game = new TennisGame(playerOne, playerTwo);
		
		for (int i = 0; i < 3; i++){			
			game.score(playerOne);
		}
		for (int i = 0; i < 3; i++){			
			game.score(playerTwo);
		}
		game.score(playerOne);
		

		String expected = playerOne + " " + ADVANTAGE;
		assertEquals(expected, game.printScore());	
	}






}