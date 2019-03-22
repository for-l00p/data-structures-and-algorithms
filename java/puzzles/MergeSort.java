package puzzles;

import java.util.Arrays;

public class MergeSort {
	

	public static void mergeSort(int[] A){
		mergeSort(A, 0, A.length - 1);
	}


	private static void mergeSort(int[] A, int start, int end){
		if (start < end){
			int partition = (end+start)/2; //If we do not chose the mid point as partition, this reduces to insertion sort and hence O(n^2) complexity. By choosing the middle, we bring it down to O(nlogn)
			mergeSort(A, start, partition); 
			mergeSort(A, partition + 1, end);
			mergeRecursive(A, start, partition, end);
		}
	}

	/*
		This function is a recursive, in-place application of merge (Given two sorted subarrays, merge them so the combined array is sorted). 
		We compare the fist elements of the sorted array to get their minimum.

		Instead of using an auxilliary array, we put the element in the slot emptied by the minimum in the array. But since this might break the precondition of the subarrays being sorted, we do a bubble procedure to place the element in the correct position. 
		(shift the elements to their correct position)
		The time complexity for array of lengths m and n is O(n+m)*max(m,n) = O(n^2). 
		With auxilarry space usage of O(n) we can reduce this to O(n).

	 */
	private static void mergeRecursive(int[] A, int start, int partition,  int end){
		
		if (start > partition || partition + 1 > end ){
			return;
		}			
		// start <=end < end AND start2<end2
		if(A[start] > A[partition + 1]){
				swap(A, start, partition + 1);
				for(int i = partition + 1; i < end; i++){
					if(A[i] > A[i+1]){
						swap(A, i, i+1);
					}
				}		
		} 
		mergeRecursive(A, start + 1, partition, end );	
	}

	

	private static void swap(int[] A, int i, int j){
		int temp = A[i];
		A[i] = A[j];
		A[j] = temp;
	}



	private static void mergeSort2(int[] A, int start, int end){
		if (start < end){

			int partition = (end+start)/2; //If we do not chose the mid point as partition, this reduces to insertion sort and hence O(n^2) complexity. By choosing the middle, we bring it down to O(nlogn)
			mergeSort2(A, start, partition); //00 1 | 23
			mergeSort2(A, partition + 1, end); 
			mergeIterative(A, start, partition, end);
		}
	}

	private static void mergeIterative(int[] A, int start, int partition , int end){
		
		int[] result = new int[end-start+1];
		int i = start;
		int j = partition + 1;
	
		while(i <= partition && j <= end){

			if(A[i] <= A[j]){
				result[i + j - (start + partition + 1)] = A[i];
				i++;
			} else {
				result[i + j - (start + partition + 1)] = A[j];
				j++;
			}
		}

		if (i > partition){
			while(j <= end){
				result[(i - start) + (j  - (partition + 1))] = A[j];
				j++;
			}
		}

		if (j > end){
			while(i <= partition){
				result[(i - start) + (j  - (partition + 1))] = A[i];
				i++;
			}
		}

		for (int k = 0; k < result.length; k++){
			A[start + k] = result[k];
		}
		
	}

	//https://stackoverflow.com/questions/43560711/how-to-merge-sort-a-linked-list-with-onlogn-time-and-o1-space-complexity

	public static void mergeSortIterative(int[] A){
		for(int i = 2; i <= 2*A.length; i = i*2){
			for (int j = 0; j < A.length - 1; j = j+i){
				int mid = (j + j + i -1)/2;
				int rightEnd = Math.min(A.length - 1, j+i-1);
				mergeIterative(A, j, mid, rightEnd);
			}
		}
	}

	public static void main(String[] args){
		int[] input = {4,3,2,1};
		mergeSort(input);
		System.out.println(Arrays.toString(input));
		
	}
}