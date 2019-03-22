package tests;

import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;

import puzzles.Minesweeper;

import java.util.Arrays;


public class MinesweeperTest {

	@Test
	public void shouldReturnCorrect(){

		char[] row0 = new char[]{'M', 'E', 'E', 'E'};
		char[] row1 = new char[]{'E', 'E', 'M', 'E'};
		char[] row2 = new char[]{'E', 'E', 'E', 'E'};
		
		char[][] input = {row0, row1, row2};

		char[] expectedRow0 = new char[]{'M', '2', '1', '1'};
		char[] expectedRow1 = new char[]{'1', '2', 'M', '1'};
		char[] expectedRow2 = new char[]{'B', '1', '1', '1'};
		
		char[][] output = {expectedRow0, expectedRow1, expectedRow2};
		assertTrue(Arrays.deepEquals(output, Minesweeper.reveal(input)));
	}


	@Test 
	public void shouldReturnCorrect2(){

		char[] row0 = new char[]{'E', 'E', 'E', 'E', 'E'};
		char[] row1 = new char[]{'E', 'E', 'M', 'E', 'E'};
		char[] row2 = new char[]{'E', 'E', 'E', 'E', 'E'};
		char[] row3 = new char[]{'E', 'E', 'E', 'E', 'E'};
		
		char[][] input = {row0, row1, row2, row3};
		int[] click = {3,0};
		char[] expectedRow0 = new char[]{'B', '1', 'E', '1', 'B'};
		char[] expectedRow1 = new char[]{'B', '1', 'M', '1', 'B'};
		char[] expectedRow2 = new char[]{'B', '1', '1', '1', 'B'};
		char[] expectedRow3 = new char[]{'B', 'B', 'B', 'B', 'B'};
		char[][] output = {expectedRow0, expectedRow1, expectedRow2, expectedRow3};
		assertTrue(Arrays.deepEquals(output, Minesweeper.reveal(input, click)));
	}

	@Test 
	public void shouldReturnCorrect3(){

		char[] row0 = new char[]{'B', '1', 'E', '1', 'B'};
		char[] row1 = new char[]{'B', '1', 'M', '1', 'B'};
		char[] row2 = new char[]{'B', '1', '1', '1', 'B'};
		char[] row3 = new char[]{'B', 'B', 'B', 'B', 'B'};
		
		char[][] input = {row0, row1, row2, row3};
		int[] click = {1,2};
		char[] expectedRow0 = new char[]{'B', '1', 'E', '1', 'B'};
		char[] expectedRow1 = new char[]{'B', '1', 'X', '1', 'B'};
		char[] expectedRow2 = new char[]{'B', '1', '1', '1', 'B'};
		char[] expectedRow3 = new char[]{'B', 'B', 'B', 'B', 'B'};
		char[][] output = {expectedRow0, expectedRow1, expectedRow2, expectedRow3};
		assertTrue(Arrays.deepEquals(output, Minesweeper.reveal(input, click)));
	}









}