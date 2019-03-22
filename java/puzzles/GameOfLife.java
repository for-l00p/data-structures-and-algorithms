package puzzles;

import java.util.Arrays;

/**
 * In principle, the Life field is infinite, but computers have finite memory. This leads to problems when the active area encroaches on the border of the array. Programmers have used several strategies to address these problems. The simplest strategy is simply to assume that every cell outside the array is dead. This is easy to program but leads to inaccurate results when the active area crosses the boundary. A more sophisticated trick is to consider the left and right edges of the field to be stitched together, and the top and bottom edges also, yielding a toroidal array. The result is that active areas that move across a field edge reappear at the opposite edge. Inaccuracy can still result if the pattern grows too large, but there are no pathological edge effects. Techniques of dynamic storage allocation may also be used, creating ever-larger arrays to hold growing patterns. Life on a finite field is sometimes explicitly studied; some implementations, such as Golly, support a choice of the standard infinite field, a field infinite only in one dimension or a finite field, with a choice of topologies such as a cylinder, a torus or a Möbius strip.
 */
public class GameOfLife {
	
	public static int[][] next(int[][] input){
		int n = input[0].length;
		int m = input.length;
		if (n == 1 || m == 1){
			return input;
		}

		for (int i = 1; i < m-1; i++){
			for (int j = 1; j < n-1; j++){
				int numAliveNeighbours = countAliveNeighbours(i, j, input);
				if (input[i][j] > 0){
					if (numAliveNeighbours == 0){
						input[i][j] = 1;
					} else {
						input[i][j] = numAliveNeighbours;
					}			
				} else {
					input[i][j] = -1*numAliveNeighbours;
				}
			}
		}

		for (int i = 1; i < m-1; i++){
			for (int j = 1; j < n-1; j++){
				if (shouldLive(input[i][j])){
					input[i][j] = 1;
				} else {
					input[i][j] = 0;
				}
			}
		}



		System.out.println(Arrays.deepToString(input));
		return input;
	}

	private static boolean shouldLive(int numAlive){

		
		if (numAlive < 2 && numAlive > 0) return false;
		if (numAlive > 3 && numAlive > 0) return false;
		// numAlive is 2 or 3 or less than equal to 0. 
		if (numAlive == 2 || numAlive == 3) return true;
		if (numAlive == -3) return true;
		return false;
	}

	private static int isAlive (int i){
		if (i > 0) return 1;
		return 0;
	}

	private static int countAliveNeighbours(int i, int j, int[][] input){

		if (j < 0 || j > input[0].length || i < 0 || i > input.length) throw new AssertionError("Arguments out of bounds. i: " + i + " j: " + j );

		int numAlive = 0;
		numAlive = isAlive(input[i-1][j-1]) + isAlive(input[i-1][j]) + isAlive(input[i-1][j+1]) + isAlive(input[i][j-1]) + isAlive(input[i][j+1]) + isAlive(input[i+1][j-1])  + isAlive(input[i+1][j]) + isAlive(input[i+1][j+1]);
		return numAlive;

	}
}