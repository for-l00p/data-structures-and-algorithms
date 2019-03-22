package tests;

import org.junit.Test;
import static org.junit.Assert.*;
import puzzles.Maze;
import java.util.Arrays;

public class MazeTest {
	


	@Test
	public void shouldReturnPath(){

		char[] row0 = new char[]{'0', '0', '1', '0', 's'};
		char[] row1 = new char[]{'0', '0', '0', '0', '0'};
		char[] row2 = new char[]{'0', '0', '0', '1', '0'};
		char[] row3 = new char[]{'1', '1', '0', '1', '1'};
		char[] row4 = new char[]{'0', '0', '0', '0', 'e'};
		char[][] input = {row0, row1, row2, row3, row4};

		char[] expectedRow0 = new char[]{'0', '0', '1', '0', 'x'};
		char[] expectedRow1 = new char[]{'0', '0', 'x', 'x', 'x'};
		char[] expectedRow2 = new char[]{'0', '0', 'x', '1', '0'};
		char[] expectedRow3 = new char[]{'1', '1', 'x', '1', '1'};
		char[] expectedRow4 = new char[]{'0', '0', 'x', 'x', 'x'};
		char[][] output = {expectedRow0, expectedRow1, expectedRow2, expectedRow3, expectedRow4};

		assertTrue(Arrays.deepEquals(output, Maze.findPath(input)));
	}

}