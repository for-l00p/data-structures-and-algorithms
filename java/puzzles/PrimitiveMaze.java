package puzzles;


public class PrimitiveMaze {
	

	public static char[][] findPath(char[][] grid){

		boolean isPathFromStartToMiddle = fillWithPath(grid, 0, grid.length - 1);
	
		return grid;
	
	}

	private static boolean fillWithPath(char[][] grid, int start, int end){		

		//To do: assert start <= end
		
		boolean isPathFromStartToEnd = false;

		if(start == end ){
			isPathFromStartToEnd =  (grid[start][start] != '1');
			return isPathFromStartToEnd;
		}

		if(start == end - 1){

			if (grid[start][start] != '1' && grid[end][end] != '1'){
				if (grid[start][start +1] == '0'){
					grid[start][start] = 'x';
					grid[start][start+1] = 'x';
					grid[end][end] = 'x';
					isPathFromStartToEnd = true;
				} else if (grid[start + 1][start] == '0'){
					grid[start][start] = 'x';
					grid[start + 1][start] = 'x';
					grid[end][end] = 'x';
					isPathFromStartToEnd = true;
				}
			}

			return isPathFromStartToEnd;
				
		}

		boolean isPathFromStartToMiddle = fillWithPath(grid, start, end/2);		
		boolean isPathFromMiddleToEnd = fillWithPath(grid, (end/2), end);
		if (isPathFromStartToMiddle && isPathFromMiddleToEnd){
				isPathFromStartToEnd = true;
		} 

		return isPathFromStartToEnd;
	}


	











}