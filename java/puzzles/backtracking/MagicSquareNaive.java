
package puzzles;

import java.util.Arrays;


public class MagicSquareNaive {
	
		
	public static int arrange(double[] input){
		int n = (int) Math.sqrt(input.length);
		double[][] grid = new double[n][n];
		return solve(grid, input,0, Double.MAX_VALUE, 0);
	
	}

	private static int solve(double[][] grid, double[] input, int numPlace, double magic, int total){

		total = 0;
		if(numPlace > input.length-1){
			boolean isMagic = checkFull(grid);
			if (isMagic){
				System.out.println(Arrays.deepToString(grid));
				total = 1;
			}
		} else {
			// if (numPlace > 8){
			// 	System.out.println(Arrays.deepToString(grid));
			// }		
			int n = input.length;
			for (int k = numPlace; k < n; k++){	
				swap(input, numPlace, k);
				double nextTry = input[numPlace];
				System.out.println("Trying:" + nextTry);
				int i = numPlace / grid[0].length;
				int j =	numPlace % grid[0].length;
				//System.out.println("i: " + i + " j: " + j);
				double[][] clone = cloneArray(grid);
				grid[i][j] = nextTry;
				//System.out.println(Arrays.deepToString(clone));
				if (numPlace == grid[0].length - 1){
					magic = sum(grid, "row", 0);

					System.out.println("found magic: " + magic);

				}
				if (numPlace > grid[0].length - 1){
					int valid = check(grid, i, j, magic);
					if (valid == -1){		
						System.out.println("Not valid, backtracking");
						swap(input, numPlace, k);
						grid[i][j] = 0;
						continue;
					} else {
						System.out.println("valid try, going to level " + (numPlace + 1));
					}
				}
				total = total + solve(grid, input, numPlace + 1, magic, total);
				swap(input, numPlace, k);
				grid[i][j] = 0;
			}
		}

		return total;
	}

	private static double[][] cloneArray(double[][] old){
		int m = old.length;
		int n = old[0].length; 

		double[][] current = new double[m][n];

		for(int i=0; i< m; i++)
  			for(int j=0; j<n; j++){
    			current[i][j] = old[i][j];
  			}
		return current;
	}

	private static double sum(double[][] grid, String orientation, int index){
		int m = grid.length;
		int n = grid[0].length;

		double sum = 0;

		if (orientation == "row"){
			for (int j = 0; j < n; j++){
				sum = sum + grid[index][j];
			}
		} 

		if (orientation == "column") {
			for (int i = 0; i < m; i++){
				sum = sum + grid[i][index];
			}
		}

		if (orientation == "slash"){
			//i = j
			if(m != n) throw new IllegalArgumentException("diagnol orientation not defined for rectangle");

			for(int i = 0; i < n; i++){
				sum = sum + grid[i][i];
			}
			
		}

		if (orientation == "backslash"){

			for(int i = 0; i < n; i++){
				sum = sum + grid[i][n- 1-i];
			}
		}

		return sum;
		
	}

	private static int check(double[][] grid, int i, int j, double magic){


		int m = grid.length;
		int n = grid[0].length;

		int cmp = 1;

		int rowCmp, columnCmp;
		int diagCmp = 0;


		double rowSum = sum(grid, "row", i);
		rowCmp =  Double.compare(magic, rowSum);

		double columnSum = sum(grid, "column", j);
		columnCmp =  Double.compare(magic, columnSum);	
	
		if (i == j && m == n){
			double diagnalSum = sum(grid, "slash", i);
			diagCmp =  Double.compare(magic, diagnalSum);;
		}

		if (i == (j+1) % n && m == n){
			double diagnalSum = sum(grid, "backslash", i);
			diagCmp =  Double.compare(magic, diagnalSum);
		}

		if (rowCmp == 0 && columnCmp == 0 && diagCmp == 0){
			cmp = 0;
		}

		if (rowCmp == -1 || columnCmp == -1 || diagCmp == -1){
			cmp = -1;
		}

		return cmp;


	}

	private static void swap (double[] input, int i, int j){
		double temp = input[i];
		input[i] = input[j];
		input[j] = temp;
	}


	private static boolean checkFull(double[][] grid){
		int n = grid.length;

		double magic = sum(grid, "row", 0);
		for(int i=0; i< n; i++){
  			for(int j=0; j<n; j++){
    			if (check(grid, i, j, magic) != 0){
    				return false;
    			}
  			}
		}
		return true;
	}

		
	public static void main(String[] args){

		double[] input = new double[]{1.0, 1.5, 2.0, 2.5, 3.0, 3.5, 4.0, 4.5, 5.0};
		//arrange(input);
		//input = new double[]{1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0, 9.0, 10.0, 11, 12, 13,14,15};
		input = new double[]{1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0, 9.0};
		//input = new double[]{1.0, 2.0, 3.0, 4.0};
		System.out.println(arrange(input));
		


	}

 }