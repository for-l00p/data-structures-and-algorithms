
import java.util.*;
// https://stackoverflow.com/questions/2894443/8-queens-algorithm-example
// 
public class NQueens{

	private static int[] q;
	private static int[] columns;
	private static int[] up;
	private static int[] down;
	private static int n;
	private static int count = 0;


	private NQueens(){

	}




	public static void solveNQueens(int n){
		
		NQueens.q = new int[n];
		NQueens.columns = new int[n];
		NQueens.up = new int[2*n+1];
		NQueens.down = new int [2*n+1];
		NQueens.n = n;

		//int queenNumber = 0;

		generate(0);
		System.out.println("Number of solutions with " + n + " queens: " + count);
		
	}

	/*
		Time complexity: Bounded by O(n!) because for each row, we only recursively search via columns which have not already been covered by previous queens. Mathmematically, let T(k) be the running time on k queens. Foe each such functions we run the loop n times, and we call recursively the function for the remaining (k-1) queens  k-1 times. 
		T(k) = O(k) + (k-1)T(k-1). 

		Other approaches: https://introcs.cs.princeton.edu/java/23recursion/Queens.java.html

		Instead of keeping track of column, up and down arrays, we can use our q array.

		public static boolean isConsistent(int[] q, int n) {
        for (int i = 0; i < n; i++) {
            if (q[i] == q[n])             return false;   // same column
            if ((q[i] - q[n]) == (n - i)) return false;   // same major diagonal
            if ((q[n] - q[i]) == (n - i)) return false;   // same minor diagonal
        }
        return true;
    }
	 */

	private static void generate(int queenNumber){
		//System.out.println("Called generate on row: " + queenNumber + " column: " + columnNumber);
		//
		//

		if(queenNumber == n){
			//printQueens();
			count++;
			return;
		}



	for(int columnNumber = 0; columnNumber < n; columnNumber++){;
		if (isFree(queenNumber, columnNumber) == false){ // executed n times for each row.
			continue;
		} else {
			q[queenNumber] = columnNumber;
			//System.out.println("Placed queen on row: " + queenNumber + " column: " + columnNumber);
			update(queenNumber, columnNumber, true); 
			generate(queenNumber + 1);
			update(queenNumber, columnNumber, false); 
		}

	}
	}

	private static boolean isFree(int rowNumber, int columnNumber){

		if(columns[columnNumber] > 0) return false;
		if (up[rowNumber + columnNumber] > 0) return false;
		if (down[columnNumber - rowNumber + n] > 0) return false;

		return true;



	}

	private static void update(int rowNumber, int columnNumber, boolean add){

		if(add){
			columns[columnNumber]++;
			up[rowNumber + columnNumber]++;
			down[columnNumber - rowNumber + n]++;
		} else {
			columns[columnNumber]--;
			up[rowNumber + columnNumber]--;
			down[columnNumber - rowNumber + n]--;
		}

	}

	private static void printQueens(){
		System.out.println(Arrays.toString(q));
	}

	public static void main(String[] args){
		solveNQueens(5);
	}





}