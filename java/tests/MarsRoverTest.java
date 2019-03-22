package tests;

import static org.junit.Assert.*;
import org.junit.Test;

import puzzles.MarsRover;

public class MarsRoverTest {
	

	@Test
	public void shouldReturnCorrect(){

		String[] input = new String[]{"55", "12N", "LMLMLMLMM", "33E", "MMRMMRMRRM"};
		String[] expected = new String[]{"13N", "51E"};
		assertArrayEquals(expected, MarsRover.rove(input));
	}




}