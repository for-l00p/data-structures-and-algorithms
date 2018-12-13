
/*
https://stackoverflow.com/questions/2631726/how-to-determine-the-longest-increasing-subsequence-using-dynamic-programming

https://www.geeksforgeeks.org/longest-monotonically-increasing-subsequence-size-n-log-n/

----

O(n^2 algorithm):

We maintain solutions to the following subproblem: L(i) contains the length of longest increasing subsequence that ends at A[i] (including A[i]).
An element A[j] = max(A[k] + 1) where k such that A[k] < A[j]. 
The global maximal is the solution 


-----

O(nlogn) algorithm
The main idea is this:

The main problem in dynamic programming is that the new information might change the makeup of the optimal solution. The subproblem whose solution defines the optimal value might change with new information. And it is difficult to keep track of all the subproblems and compare them. 

But here, we need not keep track of the solution to ALL the subproblems. Why?  
Combinatorial insight: If we have two candidate solutions/subsequences of equal length, then we can discard the one which has a larger end element (because if the sequence with a larger end element is a subsequence of the optimal solution, then so will the one with smaller end element.) 

So we keep track of only the longest subsequences of length i with smallest end elements.  All these are candidate solutions.

When we consider an element a[j], how does it affect the candidate solutions/subproblems? 

It can lead to a longer subsequence - of a length j such that our longest candidate solutions till now had length j-1 (only if the longest candidate solution had an end element less than a[j]).  In general, it can lead to a better solution to the longest subsequence ending with an element less than a[j] - 'better' in the sense that this optimal solution has a lesser end element, and thus would be more extensible.  

Find the longest candidate solution whose last element is less than a[j]: this will be a new candidate solution. We can find this sequence with in O(logn) time, because of the following invariant:
Invariant: end element of smaller list is smaller than end elements of larger lists. So the list is sorted.
Proof: If end element of some smaller list is larger than the end elemtent of a larger list. Then a subsequence of the larger list of size smaller list is a better candidate solution than a smaller list.
--

O(n^2 algorithm)

Sort into another list. Find the LCS of the two lists. 

 */

import java.util.*;
public class LIS {

	private static int[] prev;
	private static int[] lis;
	private static int maxLength;
	private static int lastIndex;
	private static int[] indices;
	

	private LIS(){
	}


	public static String findLIS(int[] input){
		buildLIS(input);
		StringBuilder s = new StringBuilder();
		findLISHelper(input, lastIndex, s );
		return s.toString();

	}

	private static void findLISHelper(int[] input, int lastIndex, StringBuilder s){
		
		if(prev[lastIndex] != lastIndex){
			findLISHelper(input, prev[lastIndex], s);
		} 
		s.append(" -> " + input[lastIndex]);

	}

	private static void buildLIS(int[] input){
		int n = input.length;
		prev = new int[n];
		lis = new int[n]; // lis(j) contains the length of longest increasing subsequence that ends at input[j] (including input(j)).
		maxLength = 1;
		lastIndex = 0;
		for(int i = 0; i < n; i++){
			lis[i] = 1;
			prev[i] = i;
		}

		for (int i = 1; i < n; i++){
			for(int j = i-1; j >=0; j--){
				if(input[j] <= input[i] && (lis[j] + 1) > lis[i]){
					lis[i] = lis[j] + 1;
					prev[i] = j;
				}
			}

			if(lis[i] > maxLength){
				maxLength = lis[i];
				lastIndex = i;
			}
		}
		//System.out.println("LAST INDEX: " + lastIndex);
		


	}

	private static void efficientBuildLIS(int[] input){
		int n = input.length;
		prev = new int[n+1];
		lis = new int[n+1];
		indices = new int[n];

		maxLength = 0; 

		int j;

		for (int i = 0; i < n; i++){
			j = findSmallestBiggerThan(lis,input[i], 1, maxLength+1); // j >= 1
			//System.out.println("index of smaller number bigger than " + input[i] + " is " + j);
			lis[j] = input[i];
			indices[j] = i;
			//indices[j] = i;

			if(j > 1) {
				prev[i] = indices[j-1];
			} else {
				prev[i] = i;
			}

			if (j > maxLength){
				maxLength = j;
			}

			//System.out.println(Arrays.toString(lis));
		}


	}

	private static String efficientFindLIS(int[] input){
		efficientBuildLIS(input);
		StringBuilder s = new StringBuilder();
		int lastIndex = indices[maxLength];
		findLISHelper(input, lastIndex, s );
		return s.toString();
	}




	private static int findSmallestBiggerThan(int[] sequence, int element, int lower, int higher){


		int midPoint = (int) (lower + higher)/2;

		while(higher > lower){
			 if (element >= sequence[midPoint]){
			 	lower = midPoint + 1;
			 } else if (element < sequence[midPoint]){
			 	higher = midPoint;
			 }

			 midPoint = (int) (lower + higher)/2;
		}

		// LOWER == HIGHER == mixpoint here. 
		// if sequence[midpoint] > element

		// Test case: [x1, x2, x3, x4]
		// element < sequence[midpoint+1] && >= sequence[midpoint]
		
		return (midPoint);
	}






	public static void main(String[] args){

		int[] input = { 2, 5, 3, 7, 11, 8, 10, 13, 6 };
		System.out.println(findLIS(input));
		System.out.println(efficientFindLIS(input));


	}


}