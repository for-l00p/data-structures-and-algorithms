

import java.util.*;


public class LCS{

	static int[][] length; // Shared amongst all instances of class!
	private static String[][] sequence;

	private LCS(){
	}

	private static void buildLCS(String firstInput, String secondInput){
		int m = firstInput.length();
		int n = secondInput.length();
		length = new int[m+1][n+1];
		sequence = new String[m+1][n+1];

		for(int i = 0; i <= m; i++){
			length[i][0] = 0;
		}

		for(int j = 0; j <=n; j++){
			length[0][j] = 0;
		}

		for (int i = 1; i <=m; i++){
			for (int j = 1; j<=n; j++){
				if(firstInput.charAt(i-1) == secondInput.charAt(j-1)){
					length[i][j] = length[i-1][j-1] + 1;
					sequence[i][j] = "takeFromBoth";
					
				} else {

					if(length[i-1][j] >= length[i][j-1]){
						length[i][j] = length[i-1][j];
						sequence[i][j] = "removeFromFirst";
					} else {
						length[i][j] = length[i][j-1];
						sequence[i][j] = "removeFromSecond";
					}	
					
				}
			}
		}

		//return length[m][n];

	}


	private static int memoizedBuildLCS(String firstInput, String secondInput){

		int m = firstInput.length();
		int n = secondInput.length();
		length = new int[m+1][n+1];
		//sequence = new String[m+1][n+1];
		for(int i = 0; i <= m; i++){
			for(int j = 0; j <=n; j++){
				length[i][j] = -1;
			}
		}


		memoizedLCSHelper(firstInput, secondInput, m,n);
	    return length[m][n];

	}

	private static int memoizedLCSHelper(String firstInput, String secondInput,int i,int j){
	


		if(length[i][j] != -1){
			return length[i][j];
		}

		int lcs;

		if (i == 0 || j == 0){
			lcs = 0;
			length[i][j] = lcs;
			return lcs;
		}

		if (firstInput.charAt(i-1) == secondInput.charAt(j-1)){
			lcs = memoizedLCSHelper( firstInput,  secondInput, i-1, j-1) + 1;
			length[i][j] = lcs;
		} else {
			
			int lcs1 = memoizedLCSHelper(firstInput, secondInput, i-1, j);
			int lcs2 = memoizedLCSHelper(firstInput, secondInput, i, j-1);
			lcs = Math.max(lcs1, lcs2);
			length[i][j] = lcs;
		}

		return lcs;
	}




	public static String findLCS(String firstInput, String secondInput){
		buildLCS(firstInput, secondInput);
		StringBuilder s = new StringBuilder();
		reconstructLCSHelper(firstInput, firstInput.length(), secondInput.length(), s);
		return s.toString();
	}

	public static String memoizedFindLCS(String firstInput, String secondInput){
		memoizedBuildLCS(firstInput, secondInput);
		StringBuilder s = new StringBuilder();
		reconstructLCSHelperSpaceOptimized(firstInput, secondInput, firstInput.length(), secondInput.length(), s);
		return s.toString();
	}



	private static void reconstructLCSHelper(String firstInput, int i, int j, StringBuilder s){

		if(i == 0 || j == 0){
			return;
		}

		if (sequence[i][j] == "takeFromBoth"){
			reconstructLCSHelper(firstInput, i-1, j-1, s);
			s.append(firstInput.charAt(i-1));
		} else if (sequence[i][j] == "removeFromFirst"){
			reconstructLCSHelper(firstInput, i-1, j, s);
		} else {
			reconstructLCSHelper(firstInput, i, j-1, s);
		}

	}

/**
 * The following function reconstructs LCS in O(m+n) time without using the sequence table.
 * @param firstInput [description]
 * @param i          [description]
 * @param j          [description]
 * @param s          [description]
 */

/*

THIS IS INCORRECT (Exercise: Find out why?)

Answer: Reconsreucts sequence with incrrect criteria. Test case: abac and ba. It thinks we reached optimal solution 2 via subproblem LCS(aba, b) + 1, when infact we reached it via  2 = subproblem (aba, ba).

	private static void reconstructLCSHelper2(String firstInput, int i, int j, StringBuilder s){


		//System.out.println("Called on i: " + i + " and j: " + j);	
		if(i == 0 || j == 0){
			return;
		}


		if (length[i][j] == length[i-1][j-1] + 1){
			reconstructLCSHelper2(firstInput, i-1, j-1, s);
			s.append(firstInput.charAt(i-1));
		} else if (length[i][j] == length[i-1][j]){
			reconstructLCSHelper2(firstInput, i-1, j, s);
		} else if (length[i][j] == length[i][j-1]) {
			reconstructLCSHelper2(firstInput, i, j-1, s);
		}

	}
*/

	private static void reconstructLCSHelperSpaceOptimized(String firstInput, String secondInput, int i, int j, StringBuilder s){

		if(i == 0 || j == 0){
			return;
		}


		if (firstInput.charAt(i-1) == secondInput.charAt(j-1)){
			reconstructLCSHelperSpaceOptimized(firstInput, secondInput, i-1, j-1, s);
			s.append(firstInput.charAt(i-1));
		} else if (length[i][j] == length[i-1][j]){
			reconstructLCSHelperSpaceOptimized(firstInput, secondInput, i-1, j, s);
		} else if (length[i][j] == length[i][j-1]) {
			reconstructLCSHelperSpaceOptimized(firstInput,secondInput, i, j-1, s);
		}

	}


	private static int findLengthOnly(String firstInput, String secondInput){
		/*
		Invariant: At the end of j'th iteration of the ith lopp, array[n] stores length[i-1][j-1]. array[k] stores length[i][k] for k <= j, and length[i-1][k] for n > k > j.
		 */
		int m = firstInput.length();
		int n = secondInput.length();
		int[] array = new int[n+1];
		int temp = 0;
	
		for(int j = 0; j < n+1; j++){
			array[j] = 0;
		}

		for (int i = 0; i < m; i++){
			array[n] = 0; 
			for (int j = 0; j < n; j++){
				temp = array[j];
				if(firstInput.charAt(i) == secondInput.charAt(j)){
					array[j] = temp + 1;
					//temp = array[j] -1 ;
				} else {
					if(j>0){
						//temp = array[j];
						array[j] = Math.max(array[j-1], array[j]);
					}
				}

				array[n] = temp;

			}
		}
		return array[n-1];

	}

	public static void printGrid(){

   		for(int i = 0; i < length.length; i++){
      		for(int j = 0; j < length[0].length; j++){
        		 System.out.printf("%5d ", length[i][j]);
      		}
      		System.out.println();
   		}
	}

/*
Unit tests LCS
 */

	public static void main(String[] args){
		String firstInput = "ABCDGH";
		String secondInput = "AEDFHR";
		System.out.println(findLCS(firstInput, secondInput));
		System.out.println(findLengthOnly(firstInput, secondInput));

	}
 		

}