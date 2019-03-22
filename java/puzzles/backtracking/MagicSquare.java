
//package puzzles;

import java.util.Arrays;

public class MagicSquare {
	
		
	private static int total = 0;

	public static int arrange(double[] input){
		int n = (int) Math.sqrt(input.length);
		double[][] grid = new double[n][n];
		solve(grid, input, 0);
		return total;
	}

	private static void solve(double[][] grid, double[] input, int numPlace){

		if (numPlace > input.length-1){
			boolean isMagic = checkIfMagic(grid);
			if (isMagic){
				System.out.println(Arrays.deepToString(grid));
				total++;
			}
		} else {
				
			int n = input.length;
			for (int k = numPlace; k < n; k++){	
				swap(input, numPlace, k);
				double nextTry = input[numPlace];
				int i = numPlace / grid[0].length;
				int j =	numPlace % grid[0].length;
				grid[i][j] = nextTry;
				solve(grid, input, numPlace + 1);
				swap(input, numPlace, k);
				grid[i][j] = 0;
			}
		}

		
	}

	private static void swap (double[] input, int i, int j){
		double temp = input[i];
		input[i] = input[j];
		input[j] = temp;
	}


	private static boolean checkIfMagic(double[][] grid){
		int n = grid.length;
		double magic = sum(grid, "row", 0);
		double candidateSum;

		for (int i = 1; i < n; i++){
			candidateSum = sum(grid, "row", i);
			if (candidateSum != magic){
				return false;
			}
		}

		for (int j = 0; j < n; j++){
		 	candidateSum = sum(grid, "column", j);
			if (candidateSum != magic){
				return false;
			}
		}

		candidateSum = sum(grid, "slash", 0);
		if (candidateSum != magic){
				return false;
		}

		candidateSum = sum(grid, "backslash", 0);
		if (candidateSum != magic){
				return false;
		}
		return true;
	}



	private static double sum(double[][] grid, String orientation, int index){

		int n = grid.length;
		double sum = 0;

		if (orientation == "row"){
			for (int j = 0; j < n; j++){
				sum = sum + grid[index][j];
			}
		} 

		if (orientation == "column") {
			for (int i = 0; i < n; i++){
				sum = sum + grid[i][index];
			}
		}

		if (orientation == "slash"){
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




	public static int arrange2(double[] input){
		int n = (int) Math.sqrt(input.length);
		double[][] grid = new double[n][n];
		double magic = (input.length + 1)*n/2;
		solve(grid, input, 0, magic);
		return total;
	}

	private static void solve(double[][] grid, double[] input, int numPlace, double magic){

		if (numPlace > input.length-1){
			boolean isMagic = checkIfMagic(grid);
			if (isMagic){
				System.out.println(Arrays.deepToString(grid));
				total++;
			}
		} else {
				
			int n = input.length;
			for (int k = numPlace; k < n; k++){	
				swap(input, numPlace, k);
				double nextTry = input[numPlace];
				int i = numPlace / grid[0].length;
				int j =	numPlace % grid[0].length;
				grid[i][j] = nextTry;
				if (!exceedsMagic(grid, i, j, magic)){
					solve(grid, input, numPlace + 1, magic);
				}				
				swap(input, numPlace, k);
				grid[i][j] = 0;
			}
		}
		
	}

	private static boolean exceedsMagic(double[][] grid, int i, int j, double magic){


		int m = grid.length;
		int n = grid[0].length;


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

		if (rowCmp >= 0 && columnCmp >= 0 && diagCmp >= 0){
			return false;
		}

		return true;


	}



	public static void main(String[] args){

		double[] input = new double[]{1.0, 1.5, 2.0, 2.5, 3.0, 3.5, 4.0, 4.5, 5.0};
		//arrange(input);
		input = new double[]{1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0, 9.0, 10.0, 11, 12, 13,14,15, 16};
		//input = new double[]{1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0, 9.0};
		//input = new double[]{1.0, 2.0, 1.0, 2.0};
		long startTime = System.currentTimeMillis();
		System.out.println(arrange2(input));
		long endTime =  System.currentTimeMillis();
		long totalTime = (endTime - startTime);
		System.out.println("Time taken:  " + totalTime);
		


	}

 }