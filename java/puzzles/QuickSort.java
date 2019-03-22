package puzzles;

import java.util.Arrays;
import java.util.Random;
// Buggy: 3,2,1 j = 0 => 
public class QuickSort {
	

//https://cs.stackexchange.com/questions/11458/quicksort-partitioning-hoare-vs-lomuto
	public static void sort(int[] A){
		quickSort(A, 0, A.length -1 );
	}

	private static void quickSort(int[] A, int start, int end){
		if (start < end){
			Random rand = new Random();
			int randomPivot = start + rand.nextInt(1)*(end-start+1);
			int pivotIndex = randomPivot;
			pivotIndex = partitionAroundPivot(A, pivotIndex, start, end);
			quickSort(A, start, pivotIndex - 1);
			quickSort(A, pivotIndex + 1, end);
		}
	}

	//inferior to Hoare's scheme because it does three times more swaps on average and degrades to O(n2) runtime when all elements are equal.
	private static int partitionAroundPivot1(int[] A, int pivotIndex, int start, int end){
		
		int k = start;
		int pivot = A[pivotIndex];
		for (int i = start; i <= end; i++){
			if(A[i] <= pivot){
				swap(A, i, k);
				if(i == pivotIndex){
					pivotIndex = k;
				}
				k++;
			} 
		}
		swap(A, k-1, pivotIndex);
		return k-1;
	}


	/** 
	
	 Hoare's partitioning scheme (two pointers for leftEnd and rightEnd)
	 The main source of error to be careful about:
	 
	 It can be tempting to think that we just need to scan the array from left to right, maintainng the invariant that elements starting....i-1 are less or equal to than pivot, and elements j+1.. are greatr than pivot, and do nothing else. But we need to do more...we need to reason about index i = j. We cannot say anythng about A[i] = A[j] because it could be that it is smaller than the pivot and  we did not need to call the swap, or it could be that it is larger than the pivot and we called the swap and it swapped with itself. 
	
	 We can't just leave it.
	 *Example:
	 
	 Let's run the loop on [4,3,5,1] with 1 as pivot. 
	 [4,3,5,1], i = 0, j = 3 --> [1,3,5, 4} i = 0, j = 2 --> {1,3, 5, 4}, i = 1, j = 2. 3 is greater than 1 --> {1, 5, 3, 4} i = 1, j = 1. Stop. 
	 Partition at index = 1
	 Sort {1} and {3,4}. Already sorted. Result: {1,5,3,4}
	 
	 We cant put it in the left partition.
		Sort {1,5} and {3,4}. Already sorted. Result: {1,5,3,4}
	 
	 Putting it in the right partition would not solve the problem. 
	 No! This might lead to the right partition contaning an element that is way smaller than the pivot element. 
	 [4,3,0,1] --> {1, 0, 3, 4} i = j = 1. Then sort on {1} and {0,3,4} --> {1,0,3,4}

	It important that whatever lies at i = j, it allows us to satisfy this property "after the executiton of organizedAroundPivot, the element at partitionIndex (i = j) is in its correct, sorted order." In general, i = j does not end up at the pivotElement, so we cannot guarantee ths property is satisfied unless we put the pivot element in it.

	To solve this, we need to do this at the end of the loop:
	
	Put the pivot element at i=j. 
	
	But to do this we need two things:
	  - know where the pivot element is (it could have moved from our initially chosen index) during the scan if we dont explicitly skip it.
	 - know where to put the i = j element: in the right partition or left partition. 

	Lets skip the pivot element in our scan, so we know it is at the pivotIndex, always. Now we just need to see if we can swap the element with pivotIndex. A problem arises if the pivotIndex is in the opposite partition as to what the element rightly belongs to. If A[i] < pivot and pivotIndex > i, then we better swap pivot with i+1.  Similarly, if A[i] > pivot, and pivotIndex < i, then we better swap pivot with i-1. Otherwise, we can swap directly.
	
	Sinve the choice of a pivot is free to us, we can choose to make our implmentation much easier:

	We just choose the first or lastIndex as the pivotIndex. In which case our code simpliefies: we just adjust the loop terminal conditions instead of telling it explicitly to skip the index, and we KNOW that the pivot element is always in the right partition (if we choose the lastIndex as pivotIndex, say).

	*/

	private static int partitionAroundPivot(int[] A, int pivotIndex, int start, int end){

		int i = start;
		int j = end;
		int pivot = A[pivotIndex];
		System.out.println("pivot: " + pivot);
		while(i < j){

			//Always leave the pivotIndex out of the loop! This is easier if we decide that pivotIndex will always be the end. 
			if(i != pivotIndex && j != pivotIndex){
				if(A[i] <= pivot){
					i++;
				} else {
				//A[i] > pivot
					swap(A, i, j);
					j--;
				} 
			} else {
				if (i == pivotIndex){
					i++;
				} else {
					j--;
				}
			}			
		}

		if(i < pivotIndex && A[i] < pivot){
			swap(A, i+1, pivotIndex );
			return i+1;
		} else if (i > pivotIndex && A[i] > pivot){
			swap(A, i-1, pivotIndex);
			return i-1;
		} 
		swap(A, i, pivotIndex);	
		return i;
	}

	private static void swap(int[] A, int i , int j){
		int temp = A[i];
		A[i] = A[j];
		A[j] = temp;	
	}

	public static void main(String[] args){
		
		int[] input = {3,4};
		QuickSort.partitionAroundPivot(input, 1, 0, 1);
	}



}