package tests;

import org.junit.Test;
import static org.junit.Assert.*;

import puzzles.PrimitiveMaze;

import java.util.Arrays;

public class PrimitiveMazeTest {
	
	@Test
	public void shouldReturnPathForSimpleExample(){

		char[] row0 = new char[]{'s', '0', '1'};
		char[] row1 = new char[]{'1', '0', '1'};
		char[] row2 = new char[]{'1', '0', 'e'};
		char[][] input = {row0, row1, row2};

		char[] expectedRow0 = new char[]{'x', 'x', '1'};
		char[] expectedRow1 = new char[]{'1', 'x', '1'};
		char[] expectedRow2 = new char[]{'1', 'x', 'x'};
		char[][] output = {expectedRow0, expectedRow1, expectedRow2};

		assertTrue(Arrays.deepEquals(output, PrimitiveMaze.findPath(input)));
	}

	


}