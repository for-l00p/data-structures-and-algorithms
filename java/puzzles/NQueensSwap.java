import java.util.*;

/*
Resources: 
https://introcs.cs.princeton.edu/java/23recursion/Queens.java.html
https://introcs.cs.princeton.edu/java/23recursion/Queens2.java.html
https://introcs.cs.princeton.edu/java/23recursion/

 */

public class NQueensSwap{

	public static int count = 0;
	public static void solveNQueens(int n){

		int queenNumber = 0;
		boolean[] up = new boolean[2*n];
		boolean[] down = new boolean[2*n];
		int[] q = new int[n];

		for(int i = 0; i < n; i++){
			q[i] = i;
		}

		solveNQueensHelper(q, queenNumber, up, down, n);
		System.out.println("Number of solutions with " + n + " queens: " + count);


	}

	private static void solveNQueensHelper(int[] q, int queenNumber, boolean[] up, boolean[] down, int n){

		if(queenNumber == n){
			//printQueens(q);
			count++;

		} else {
			for(int columnToTry = queenNumber ; columnToTry < n; columnToTry++){
				//System.out.println("Trying:  " + queenNumber + " and column: " + columnToTry);
				swap(q, queenNumber, columnToTry); // Swapping because to try a column, we must remove that column's blocking queen first. 
				if(up[queenNumber + q[queenNumber]] == false && down[queenNumber - q[queenNumber] + n] == false){
					//System.out.println("Putting queen at row:  " + queenNumber + " and column: " + columnToTry);
					up[queenNumber + q[queenNumber]] = true;
					down[queenNumber - q[queenNumber] + n] = true;
					solveNQueensHelper(q, (queenNumber+1), up, down, n);
					up[queenNumber+q[queenNumber]] = false;
					down[queenNumber - q[queenNumber] + n] = false;
				}
				swap(q, queenNumber, columnToTry);
			}
		}


	}

	private static void printQueens(int[] q){
		System.out.println(Arrays.toString(q));
	}


	private static void swap(int[] a, int i, int j){
		int temp = a[i];
		a[i] = a[j];
		a[j] = temp;
	}

	
	public static void main(String args[]){
		long startTime = System.currentTimeMillis();
		solveNQueens(24);
		long endTime   = System.currentTimeMillis();
		long totalTime = endTime - startTime;
		System.out.println("Time taken by swap algorithm: " + totalTime);
		long startTime2 = System.currentTimeMillis();
		NQueens.solveNQueens(24);
		long endTime2   = System.currentTimeMillis();
		long totalTime2 = endTime2 - startTime2;
		
		System.out.println("Time taken by dikstra's algorithm: " + totalTime2);
		

	}
	
}