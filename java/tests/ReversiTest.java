package tests;


import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.Ignore;

import puzzles.Reversi;
import java.util.Arrays;

public class ReversiTest{
	
	@Test 
	public void shouldReturnCorrect(){

		Character[] row0 = new Character[]{'.', '.', '.', '.', '.', '.', '.', '.'};
		Character[] row1 = new Character[]{'.', '.', '.', '.', '.', '.', '.', '.'};
		Character[] row2 = new Character[]{'.', '.', '.', '.', '.', '.', '.', '.'};
		Character[] row3 = new Character[]{'.', '.', '.', 'B', 'W', '.', '.', '.'};
		Character[] row4 = new Character[]{'.', '.', '.', 'W', 'B', '.', '.', '.'};
		Character[] row5 = new Character[]{'.', '.', '.', '.', '.', '.', '.', '.'};
		Character[] row6 = new Character[]{'.', '.', '.', '.', '.', '.', '.', '.'};
		Character[] row7 = new Character[]{'.', '.', '.', '.', '.', '.', '.', '.'};
		Character[][] grid = {row0, row1, row2, row3, row4, row5, row6, row7};

		Character[] expectedRow0 = new Character[]{'.', '.', '.', '.', '.', '.', '.', '.'};
		Character[] expectedRow1 = new Character[]{'.', '.', '.', '.', '.', '.', '.', '.'};
		Character[] expectedRow2 = new Character[]{'.', '.', '.', '.', '0', '.', '.', '.'};
		Character[] expectedRow3 = new Character[]{'.', '.', '.', 'B', 'W', '0', '.', '.'};
		Character[] expectedRow4 = new Character[]{'.', '.', '0', 'W', 'B', '.', '.', '.'};
		Character[] expectedRow5 = new Character[]{'.', '.', '.', '0', '.', '.', '.', '.'};
		Character[] expectedRow6 = new Character[]{'.', '.', '.', '.', '.', '.', '.', '.'};
		Character[] expectedRow7 = new Character[]{'.', '.', '.', '.', '.', '.', '.', '.'};
		Character[][] expectedGrid = {expectedRow0, expectedRow1, expectedRow2, expectedRow3, expectedRow4, expectedRow5, expectedRow6, expectedRow7};

		Character playerTurn = 'B';
		assertTrue(Arrays.deepEquals(expectedGrid, Reversi.findLegalMoves(grid, playerTurn )));
	}

	@Test
	public void shouldReturnCorrect2(){

		Character[] row0 = new Character[]{'.', '.', '.', '.', '.', '.', '.', '.'};
		Character[] row1 = new Character[]{'.', '.', '.', '.', '.', '.', '.', '.'};
		Character[] row2 = new Character[]{'.', '.', '.', 'B', '.', '.', '.', '.'};
		Character[] row3 = new Character[]{'.', '.', '.', 'B', 'B', '.', '.', '.'};
		Character[] row4 = new Character[]{'.', '.', '.', 'B', 'W', '.', '.', '.'};
		Character[] row5 = new Character[]{'.', '.', '.', '.', '.', '.', '.', '.'};
		Character[] row6 = new Character[]{'.', '.', '.', '.', '.', '.', '.', '.'};
		Character[] row7 = new Character[]{'.', '.', '.', '.', '.', '.', '.', '.'};
		Character[][] grid = {row0, row1, row2, row3, row4, row5, row6, row7};

		Character[] expectedRow0 = new Character[]{'.', '.', '.', '.', '.', '.', '.', '.'};
		Character[] expectedRow1 = new Character[]{'.', '.', '.', '.', '.', '.', '.', '.'};
		Character[] expectedRow2 = new Character[]{'.', '.', '0', 'B', '0', '.', '.', '.'};
		Character[] expectedRow3 = new Character[]{'.', '.', '.', 'B', 'B', '.', '.', '.'};
		Character[] expectedRow4 = new Character[]{'.', '.', '0', 'B', 'W', '.', '.', '.'};
		Character[] expectedRow5 = new Character[]{'.', '.', '.', '.', '.', '.', '.', '.'};
		Character[] expectedRow6 = new Character[]{'.', '.', '.', '.', '.', '.', '.', '.'};
		Character[] expectedRow7 = new Character[]{'.', '.', '.', '.', '.', '.', '.', '.'};
		Character[][] expectedGrid = {expectedRow0, expectedRow1, expectedRow2, expectedRow3, expectedRow4, expectedRow5, expectedRow6, expectedRow7};

		Character playerTurn = 'W';
		assertTrue(Arrays.deepEquals(expectedGrid, Reversi.findLegalMoves(grid, playerTurn )));
	}
	



}