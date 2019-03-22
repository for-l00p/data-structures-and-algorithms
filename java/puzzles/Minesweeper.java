package puzzles;



public class Minesweeper {
	
	public static char[][] reveal(char[][] input){
		int m = input.length;
		int n = input[0].length;

		for (int i = 0; i < m; i++){
			for (int j = 0; j < n; j++){
				if(input[i][j] != 'M'){
					input[i][j] = "B123456789".charAt(countMinesInNeighbourhood(input, i, j));
				}
			}
		}
		return input;
	}



	private static int countMinesInNeighbourhood(char[][] input, int row, int column){

		if (input[row][column] == 'M'){
			throw new AssertionError("Called countMinesInNeighbourhood on a mine");
		}
		int count = 0;
		for (int i = row - 1; i <= row + 1; i++){
			for (int j = column - 1; j <= column + 1; j++){
				if(liesWithinGrid(input, i, j) && input[i][j] == 'M'){
					count++;
				}
			}
		}
		return count;
	}

	private static boolean liesWithinGrid(char[][] input, int row, int column){
		int m = input.length;
		int n = input[0].length;
		if(row < 0 || row > m - 1) return false;
		if (column < 0 || column > n - 1) return false;
		return true;
	}

	public static char[][] reveal(char[][] input, int[] click){

		int row = click[0];
		int column = click[1];
		char c = input[row][column];
		if(c == 'M'){
			input[row][column] = 'X';
			return input;
		}

		int n = countMinesInNeighbourhood(input, row, column);

		if (c == 'E' && n > 0){	
			input[row][column] =  "B123456789".charAt(n);
			return input;
		} 

		if (c == 'E' && n == 0){	
			input[row][column] = 'B';
			for (int i = row - 1; i <= row + 1; i++){
				for (int j = column - 1; j <= column + 1; j++){
					if((i != row || j != column) && liesWithinGrid(input, i,j)){
						reveal(input, new int[]{i,j});
					}
				}
			}
		} 

		return input;
	}

}