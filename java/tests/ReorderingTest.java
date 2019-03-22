package tests;

import org.junit.Test;
import static org.junit.Assert.*;


import puzzles.Reordering;


public class ReorderingTest {

	@Test
	public void shouldReturnCorrect(){
		int[] input = { 4, 2, 7, 5, 9, 8, 6, 4, 3, 2 };
		int s = 1;
		int e = 3;
		int p = 8;
		int[] output =  { 4, 9, 8, 6, 4, 2, 7, 5, 3, 2 };
		assertArrayEquals(output, Reordering.moveRange(input, s,e, p));
	}

	@Test
	public void shouldReturnCorrectFizzBuzz(){
		int[] input = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20 };
		int e = 3;
	
		int[] output =  { 1, 2, 4, 3, 5, 6, 9, 10, 12, 15, 18, 20, 7, 8, 11, 13, 14, 16, 17, 19 };
		assertArrayEquals(output, Reordering.moveFizzBuzz(input, e));

	}






}
