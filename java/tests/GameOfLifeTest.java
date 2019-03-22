

package tests;

import java.util.Arrays;

import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;

import puzzles.GameOfLife;

public class GameOfLifeTest {
	
	@Test 
	public void shouldReturnCorrect1(){

		int[] row0 = new int[]{0};
		int[][] input = {row0};
		int[] expectedrow0 = new int[]{0};
		int[][] expected = {expectedrow0};
		assertTrue(Arrays.deepEquals(expected, GameOfLife.next(input)));
	}

	@Test
	public void shouldReturnCorrectForLeetCodeExample(){
		 int[] row = new int[]{0,0,0,0,0};
		int[] row0 = new int[]{0,0,1,0,0};
		int[] row1 = new int[]{0,0,0,1,0};
		int[] row2 = new int[]{0,1,1,1,0};
		int[] row3 = new int[]{0,0,0,0,0};
		int[] row4 = new int[]{0,0,0,0,0};
		int[][] input = {row, row0, row1, row2, row3, row4};
		int[] expectedrow = new int[]{0,0,0,0,0};
		int[] expectedrow0 = new int[]{0,0,0,0,0};
		int[] expectedrow1 = new int[]{0,1,0,1,0};
		int[] expectedrow2 = new int[]{0,0,1,1,0};
		int[] expectedrow3 = new int[]{0,0,1,0,0};
		int[] expectedrow4 = new int[]{0,0,0,0,0};
		int[][] expected = {expectedrow,expectedrow0, expectedrow1, expectedrow2, expectedrow3,expectedrow4};

		assertTrue(Arrays.deepEquals(expected, GameOfLife.next(input)));
	}

	@Test 
	public void shouldReturnCorrectForCyberDojoExample(){

		int[] row0 = new int[]{0,0,0,0,0,0,0,0};
		int[] row1 = new int[]{0,0,0,0,1,0,0,0};
		int[] row2 = new int[]{0,0,0,1,1,0,0,0};
		int[] row3 = new int[]{0,0,0,0,0,0,0,0};
		int[][] input = {row0, row1, row2, row3};

		int[] expectedrow0 = new int[]{0,0,0,0,0,0,0,0};
		int[] expectedrow1 = new int[]{0,0,0,1,1,0,0,0};
		int[] expectedrow2 = new int[]{0,0,0,1,1,0,0,0};
		int[] expectedrow3 = new int[]{0,0,0,0,0,0,0,0};
		int[][] expected = {expectedrow0, expectedrow1, expectedrow2, expectedrow3};
		assertTrue(Arrays.deepEquals(expected, GameOfLife.next(input)));
	}

	@Test 
	public void shouldReturnCorrectForBlinker(){

		int[] row0 = new int[]{0,0,0,0,0};
		int[] row1 = new int[]{0,0,0,0,0};
		int[] row2 = new int[]{0,1,1,1,0};
		int[] row3 = new int[]{0,0,0,0,0};
		int[] row4 = new int[]{0,0,0,0,0};

		int[][] input = {row0, row1, row2, row3, row4};

		int[] expectedrow0 = new int[]{0,0,0,0,0};
		int[] expectedrow1 = new int[]{0,0,1,0,0};
		int[] expectedrow2 = new int[]{0,0,1,0,0};
		int[] expectedrow3 = new int[]{0,0,1,0,0};
		int[] expectedrow4 = new int[]{0,0,0,0,0};
		int[][] expected = {expectedrow0, expectedrow1, expectedrow2, expectedrow3,expectedrow4};
		assertTrue(Arrays.deepEquals(expected, GameOfLife.next(input)));
	}



	@Test 
	public void shouldReturnCorrectForBlinkerReverse(){

		int[] row0 = new int[]{0,0,0,0,0};
		int[] row1 = new int[]{0,0,1,0,0};
		int[] row2 = new int[]{0,0,1,0,0};
		int[] row3 = new int[]{0,0,1,0,0};
		int[] row4 = new int[]{0,0,0,0,0};

		int[][] input = {row0, row1, row2, row3, row4};

		int[] expectedrow0 = new int[]{0,0,0,0,0};
		int[] expectedrow1 = new int[]{0,0,0,0,0};
		int[] expectedrow2 = new int[]{0,1,1,1,0};
		int[] expectedrow3 = new int[]{0,0,0,0,0};
		int[] expectedrow4 = new int[]{0,0,0,0,0};

		int[][] expected = {expectedrow0, expectedrow1, expectedrow2, expectedrow3,expectedrow4};
		assertTrue(Arrays.deepEquals(expected, GameOfLife.next(input)));
	}

	@Test 
	public void shouldReturnCorrectForToad(){

		int[] row0 = new int[]{0,0,0,0,0,0};
		int[] row1 = new int[]{0,0,0,1,0,0};
		int[] row2 = new int[]{0,1,0,0,1,0};
		int[] row3 = new int[]{0,1,0,0,1,0};
		int[] row4 = new int[]{0,0,1,0,0,0};
		int[] row5 = new int[]{0,0,0,0,0,0};

		int[][] input = {row0, row1, row2, row3, row4, row5};

		int[] expectedrow0 = new int[]{0,0,0,0,0,0};
		int[] expectedrow1 = new int[]{0,0,0,0,0,0};
		int[] expectedrow2 = new int[]{0,0,1,1,1,0};
		int[] expectedrow3 = new int[]{0,1,1,1,0,0};
		int[] expectedrow4 = new int[]{0,0,0,0,0,0};
		int[] expectedrow5 = new int[]{0,0,0,0,0,0};
		int[][] expected = {expectedrow0, expectedrow1, expectedrow2, expectedrow3,expectedrow4, expectedrow5};
		assertTrue(Arrays.deepEquals(expected, GameOfLife.next(input)));
	}

	@Test 
	public void shouldReturnCorrectForToadReverse(){

		int[] row0 = new int[]{0,0,0,0,0,0};
		int[] row1 = new int[]{0,0,0,0,0,0};
		int[] row2 = new int[]{0,0,1,1,1,0};
		int[] row3 = new int[]{0,1,1,1,0,0};
		int[] row4 = new int[]{0,0,0,0,0,0};
		int[] row5 = new int[]{0,0,0,0,0,0};

		int[][] input = {row0, row1, row2, row3, row4, row5};

		int[] expectedrow0 = new int[]{0,0,0,0,0,0};
		int[] expectedrow1 = new int[]{0,0,0,1,0,0};
		int[] expectedrow2 = new int[]{0,1,0,0,1,0};
		int[] expectedrow3 = new int[]{0,1,0,0,1,0};
		int[] expectedrow4 = new int[]{0,0,1,0,0,0};
		int[] expectedrow5 = new int[]{0,0,0,0,0,0};
		int[][] expected = {expectedrow0, expectedrow1, expectedrow2, expectedrow3,expectedrow4, expectedrow5};
		assertTrue(Arrays.deepEquals(expected, GameOfLife.next(input)));
	}

	@Test 
	public void shouldReturnCorrectForBeacon(){

		int[] row0 = new int[]{0,0,0,0,0,0};
		int[] row1 = new int[]{0,1,1,0,0,0};
		int[] row2 = new int[]{0,1,0,0,0,0};
		int[] row3 = new int[]{0,0,0,0,1,0};
		int[] row4 = new int[]{0,0,0,1,1,0};
		int[] row5 = new int[]{0,0,0,0,0,0};

		int[][] input = {row0, row1, row2, row3, row4, row5};

		int[] expectedrow0 = new int[]{0,0,0,0,0,0};
		int[] expectedrow1 = new int[]{0,1,1,0,0,0};
		int[] expectedrow2 = new int[]{0,1,1,0,0,0};
		int[] expectedrow3 = new int[]{0,0,0,1,1,0};
		int[] expectedrow4 = new int[]{0,0,0,1,1,0};
		int[] expectedrow5 = new int[]{0,0,0,0,0,0};
		int[][] expected = {expectedrow0, expectedrow1, expectedrow2, expectedrow3,expectedrow4, expectedrow5};
		assertTrue(Arrays.deepEquals(expected, GameOfLife.next(input)));
	}

	@Test 
	public void shouldReturnCorrectForBeaconReverse(){

		int[] row0 = new int[]{0,0,0,0,0,0};
		int[] row1 = new int[]{0,1,1,0,0,0};
		int[] row2 = new int[]{0,1,1,0,0,0};
		int[] row3 = new int[]{0,0,0,1,1,0};
		int[] row4 = new int[]{0,0,0,1,1,0};
		int[] row5 = new int[]{0,0,0,0,0,0};

		int[][] input = {row0, row1, row2, row3, row4, row5};

		int[] expectedrow0 = new int[]{0,0,0,0,0,0};
		int[] expectedrow1 = new int[]{0,1,1,0,0,0};
		int[] expectedrow2 = new int[]{0,1,0,0,0,0};
		int[] expectedrow3 = new int[]{0,0,0,0,1,0};
		int[] expectedrow4 = new int[]{0,0,0,1,1,0};
		int[] expectedrow5 = new int[]{0,0,0,0,0,0};
		int[][] expected = {expectedrow0, expectedrow1, expectedrow2, expectedrow3,expectedrow4, expectedrow5};
		assertTrue(Arrays.deepEquals(expected, GameOfLife.next(input)));
	}




	@Test 
	public void shouldReturnCorrectForStillLifeBoat(){

		int[] row0 = new int[]{0,0,0,0,0};
		int[] row1 = new int[]{0,1,1,0,0};
		int[] row2 = new int[]{0,1,0,1,0};
		int[] row3 = new int[]{0,0,1,0,0};
		int[] row4 = new int[]{0,0,0,0,0};

		int[][] input = {row0, row1, row2, row3, row4};

		int[] expectedrow0 = new int[]{0,0,0,0,0};
		int[] expectedrow1 = new int[]{0,1,1,0,0};
		int[] expectedrow2 = new int[]{0,1,0,1,0};
		int[] expectedrow3 = new int[]{0,0,1,0,0};
		int[] expectedrow4 = new int[]{0,0,0,0,0};
		int[][] expected = {expectedrow0, expectedrow1, expectedrow2, expectedrow3,expectedrow4};
		assertTrue(Arrays.deepEquals(expected, GameOfLife.next(input)));
	}

	@Test
	public void shouldReturnCorrectForStillLifeTub(){

		int[] row0 = new int[]{0,0,0,0,0};
		int[] row1 = new int[]{0,0,1,0,0};
		int[] row2 = new int[]{0,1,0,1,0};
		int[] row3 = new int[]{0,0,1,0,0};
		int[] row4 = new int[]{0,0,0,0,0};

		int[][] input = {row0, row1, row2, row3, row4};

		int[] expectedrow0 = new int[]{0,0,0,0,0};
		int[] expectedrow1 = new int[]{0,0,1,0,0};
		int[] expectedrow2 = new int[]{0,1,0,1,0};
		int[] expectedrow3 = new int[]{0,0,1,0,0};
		int[] expectedrow4 = new int[]{0,0,0,0,0};
		int[][] expected = {expectedrow0, expectedrow1, expectedrow2, expectedrow3,expectedrow4};
		assertTrue(Arrays.deepEquals(expected, GameOfLife.next(input)));
	}

}


