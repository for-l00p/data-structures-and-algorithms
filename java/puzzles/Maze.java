package puzzles;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Queue;
import java.util.LinkedList;

public class Maze {


	private Maze(){
		
	}

	public static char[][] findPath(char[][] grid){

	
		Point s = find(grid, 's');
		Point e = find(grid, 'e');
		boolean[][] visited  = new boolean[grid.length][grid[0].length];
		Point[][] parent = new Point[grid.length][grid[0].length];
		//DFS(grid, s, e, visited, parent);
		BFS(grid, s, e, visited, parent);
		populatePath(grid, s, e, parent);
	 	System.out.println(Arrays.deepToString(grid));
		return grid;
	}

	private static Point find(char[][] grid, char c){
		Point start = null;
		for (int i = 0; i < grid.length; i++){
			for (int j = 0; j < grid[0].length; j++){
				if(grid[i][j] == c){
					 start  = new Point(i,j);
				}
			}
		}
		return start;
	}

	private static void populatePath(char[][] grid, Point source, Point destination, Point[][] parent){
	
		 Point current = destination;
		 char c;
		 while(!parent[current.rowIndex][current.columnIndex].equals(source)){
			 c = findDirection(parent[current.rowIndex][current.columnIndex], current);
		 	 current = parent[current.rowIndex][current.columnIndex];
		 	 setChar(grid, current, c );

		 }
		
	}

	private static char findDirection(Point parent, Point child){
		char c = 'x';

		if (parent.rowIndex == child.rowIndex - 1){
			c = '↓';
			return c;
		}

		if (parent.rowIndex == child.rowIndex + 1){
			c = '↑';
			return c;
		}

		if (parent.columnIndex == child.columnIndex - 1){
			c = '→';

		}

		if (parent.columnIndex == child.columnIndex + 1){
			c = '←';
		}

		return c;

	}

	private static void setChar(char[][] grid, Point p, char c){
		grid[p.rowIndex][p.columnIndex] = c;
	}

	private static boolean DFS(char[][] grid, Point source, Point destination, boolean[][] visited, Point[][] parent){


		if (sourceIsAWall(grid, source)) return false;
		if (source.equals(destination)){
			return true;
		} 

		visited[source.rowIndex][source.columnIndex] = true;

		for (Point neighbour: getNeighbours(grid, source)){
			if(!visited[neighbour.rowIndex][neighbour.columnIndex]){
				setParent(parent, neighbour, source);
				boolean checkThisBranch = DFS(grid, neighbour, destination, visited, parent);
				if (checkThisBranch) {
					return true;
				}
			}
		}
		return false;
	}


	private static void setParent(Point[][] parent, Point neighbour, Point source ){

		parent[neighbour.rowIndex][neighbour.columnIndex] = source;
	}


	private static boolean sourceIsAWall(char[][] grid, Point source){
		return grid[source.rowIndex][source.columnIndex] == '1';
	}


	static List<Point> getNeighbours(char[][] grid, Point p){

			int rowIndex = p.rowIndex;
			int columnIndex = p.columnIndex;
			List<Point> list = new ArrayList<>();

			int i = rowIndex -1;
			int j = columnIndex;
			if(liesWithinGrid(grid,i,j)){
				list.add(new Point(i, j));	
			}

			i= rowIndex;
			j = columnIndex-1;


			if(liesWithinGrid(grid,i,j)){
				list.add(new Point(i, j));	
			}

			i = rowIndex;
			j = columnIndex+1;
			
			if(liesWithinGrid(grid,i,j)){
				list.add(new Point(i, j));	
			}


			i = rowIndex+1;
			j = columnIndex;
			if(liesWithinGrid(grid,i,j)){
				list.add(new Point(i, j));	
			}
			
			return list;
	}

	

	private static boolean liesWithinGrid(char[][] grid, int i, int j){
		
		if (i < 0 || i > grid.length - 1) return false;
		if (j < 0 || j > grid[0].length - 1) return false;

		return true;
	}

	static class Point {
		int rowIndex;
		int columnIndex;

		Point(int i, int j){
			this.rowIndex = i;
			this.columnIndex = j;
		}

		@Override
		public boolean equals(Object other){
			return (other instanceof Point) && sameValue((Point) other);
		}


		boolean sameValue(Point other){
			return (other.rowIndex == this.rowIndex) && (other.columnIndex == this.columnIndex);
		}

		@Override
		public String toString(){
			return rowIndex+","+columnIndex;
		}


	}


	public static void BFS(char[][] grid, Point source, Point destination, boolean[][] visited, Point[][] parent){

		
			Queue<Point> queue = new LinkedList<>();
			queue.add(source);
			visited[source.rowIndex][source.columnIndex] = true;
			while(!queue.isEmpty()){
				Point current = queue.poll();
				
				if (current.equals(destination)) return;
				for (Point n: getNeighbours(grid, current)){
					int i = n.rowIndex;
					int j = n.columnIndex;
					if(!sourceIsAWall(grid,n) && visited[i][j] == false){
						visited[n.rowIndex][n.columnIndex] = true;
						System.out.println("Setting parent of " + n.toString() + " to " + current.toString());
						parent[i][j] = current;
						queue.add(n);
					}	
				}
			}

	}

// [[0, 0, 1, 0, s],
//  [0, 0, →, →, →], 
//  [0, 0, →, 1, 0], 
//  [1, 1, →, 1, 1], 
//  [0, 0, →, →, e]]


	//private static void BFS()

	public static void main(String[] args){


		// char[] row0 = new char[]{'0', '0', '1', '0', 's'};
		// char[] row1 = new char[]{'0', '0', '0', '0', '0'};
		// char[] row2 = new char[]{'0', '0', '0', '1', '0'};
		// char[] row3 = new char[]{'1', '1', '0', '1', '1'};
		// char[] row4 = new char[]{'0', '0', '0', '0', 'e'};
		// char[][] input = {row0, row1, row2, row3, row4};
		// Arrays.deepToString(Maze.findPath(input));


		char[] row0 = new char[]{'s', '0', '1'};
		char[] row1 = new char[]{'1', '0', '1'};
		char[] row2 = new char[]{'1', '0', 'e'};
	
		char[][] input = {row0, row1, row2};
		Arrays.deepToString(Maze.findPath(input));


	}


}