package puzzles;

// To try: change other()
// 
import java.util.Arrays;

public class Reversi {
	

	public static Character[][] findLegalMoves(Character[][] grid, Character playerTurn){

			for (int i = 0; i < grid.length; i++){
				for(int j = 0; j < grid.length; j++){
					if(grid[i][j].equals(playerTurn)){
						fillNeighbourhood(grid, playerTurn, i, j );
					}
				}
			}

			System.out.println(Arrays.deepToString(grid));

			return grid;
		
	}

	private static void fillNeighbourhood(Character[][] grid, Character playerTurn, int i, int j){

		System.out.println("fillNeighbourhood(" + i + "," + j + ")");
		fill(grid, playerTurn, i, j, "top");
		fill(grid, playerTurn, i, j, "bottom");
		fill(grid, playerTurn, i, j, "left");
		fill(grid, playerTurn, i, j, "right");

		fill(grid, playerTurn, i, j, "slashLeft");
		fill(grid, playerTurn, i, j, "slashRight");
		fill(grid, playerTurn, i, j, "backslashLeft");
		fill(grid, playerTurn, i, j, "backslashRight");


	}

	private static void fill(Character[][] grid, Character playerTurn, int i, int j, String orientation){

		int rowIndex = i ;
		int columnIndex = j;


		if(orientation == "top"){

			rowIndex--;
			if(rowIndex <= 0 || !grid[rowIndex][j].equals(other(playerTurn))){
				return;
			}
			//Possibly a legal move: find first non-white letter.
			
			//grid[rowIndex][j] == other AND rowIndex > 0		
			while(rowIndex > 0 && grid[rowIndex][j].equals(other(playerTurn))){
				rowIndex--;
			}
		}


		if(orientation == "bottom"){

			rowIndex++;
			if(rowIndex >= grid.length - 1 || !grid[rowIndex][j].equals(other(playerTurn))){
				return;
			}
			//Possibly a legal move: find first non-white letter.
			
			//grid[rowIndex][j] == other AND rowIndex > 0		
			while(rowIndex < grid.length - 1 && grid[rowIndex][j].equals(other(playerTurn))){
				rowIndex++;
			}
		}

		if(orientation == "left"){

			columnIndex--;
			if(columnIndex <= 0 || !grid[rowIndex][columnIndex].equals(other(playerTurn))){
				return;
			}
			//Possibly a legal move: find first non-white letter.
			
			while(columnIndex > 0 && grid[rowIndex][columnIndex].equals(other(playerTurn))){
				columnIndex--;
			}
			
		}

		if(orientation == "right"){

			columnIndex++;

			if(columnIndex >= grid.length - 1 || !grid[rowIndex][columnIndex].equals(other(playerTurn))){
				return;
			}

			while(columnIndex < grid.length - 1 && grid[rowIndex][columnIndex].equals(other(playerTurn))){
				columnIndex++;
			}
		}

		if(orientation == "slashLeft"){

			columnIndex--;
			rowIndex--;

			if(columnIndex <= 0 || rowIndex <= 0 || !grid[rowIndex][columnIndex].equals(other(playerTurn))){
				return;
			}

			while(columnIndex > 0 && rowIndex > 0 && grid[rowIndex][columnIndex].equals(other(playerTurn))){
				columnIndex--;
				rowIndex--;
			}
		}


		if(orientation == "slashRight"){

			columnIndex++;
			rowIndex++;

			if(columnIndex >= grid.length - 1  || rowIndex >= grid.length - 1 || !grid[rowIndex][columnIndex].equals(other(playerTurn))){
				return;
			}

			while(columnIndex < grid.length - 1 && rowIndex < grid.length - 1 && grid[rowIndex][columnIndex].equals(other(playerTurn))){
				columnIndex++;
				rowIndex++;
			}
		}


		if(orientation == "backslashLeft"){

			rowIndex++;
			columnIndex--;

			if(columnIndex <= 0  || rowIndex >= grid.length - 1 || !grid[rowIndex][columnIndex].equals(other(playerTurn))){
				return;
			}

			while(rowIndex < grid.length - 1 && columnIndex > 0 && grid[rowIndex][columnIndex].equals(other(playerTurn))){
				columnIndex++;
				rowIndex--;
			}
		}


		if(orientation == "backslashRight"){

			columnIndex++;
			rowIndex--;

			if(rowIndex <= 0 || columnIndex >= grid.length - 1 || !grid[rowIndex][columnIndex].equals(other(playerTurn))){
				return;
			}

			while(rowIndex > 0 && columnIndex < grid.length - 1 && grid[rowIndex][columnIndex].equals(other(playerTurn))){
				columnIndex++;
				rowIndex--;
			}
		}




		System.out.println("orientation: " + orientation + " row " + rowIndex + " col " + columnIndex);
		if(grid[rowIndex][columnIndex].equals('.')){
			grid[rowIndex][columnIndex] = '0';
		}

		
	}

	private static Character other(Character playerTurn){

		if(!playerTurn.equals('B') && !playerTurn .equals('W')) throw new IllegalArgumentException("Argument:" + playerTurn); // Fail Fast. 

		if (playerTurn.equals('B')) return 'W';

		return 'B';


	}




}