package puzzles;

/*

In my experience, from most to least commonly mentioned in interviews:
1. quicksort: implement it, explain it (Two ways to partition: With one pointer or two pointers. Two pointers requires less swaps, but requires care with two easy source of bug: not skipping the pivot element, or not positioning the pivot element at the right place at the end of the partition procedure)
2. mergesort: implement it, talk about space complexity as well as time complexity
3. insertion sort: explain when it can be better than the above two
4. heapsort: explain how it works, and how heaps work in general
5. bubble sort: why it's awful
6. radix/counting/bucket sort: when it's useful
7. selection sort: usually thrown in as an example when asked to list sorting algorithms you know

Best resource: https://www.toptal.com/developers/sorting-algorithms


In bubble sort in ith iteration you have n-i-1 inner iterations (n^2)/2 total, 
and in insertion sort: you have maximum i iterations on i'th step. Both sum to n.n-1/2 iterations in worst case.

But  in insertin sort, you only need i/2 iterations on average to put an element in the correc sorted order (For some you need 0, for some its i and all are equallity likely.)
on average. as you can stop inner loop earlierafter you found correct position for the current element. So you have (sum from 0 to n) / 2 which is (n^2) / 4 total.

In bubble sort, you cannot stop until you traverse to the  other end in the inner loop: because you have to find the maximum. 

The difference:  bubbling a random element in a sorted order  is an easier task, on average, than bubbling a particular element from an unsorted sequences. In the former, you are USING the structure you have already constructured: the work you have already done, to make it easier to to the next ask, in the latter you are not using that. 


Insertion sort is very similar in that after the kth iteration, the first k elements in the array are in sorted order. Insertion sort's advantage is that it only scans as many elements as it needs in order to place the k + 1st element, while selection sort must scan all remaining elements to find the k + 1st element.

Selection sort goes, at every turn for the "globally optimium choice", whereas insertion sort goes for locally optimum choice (Compare kruskal and prim). Bubble sort doesnt directly go for the optimium but decided some work that each one has to do, indirectly find a global optimum, but also doing a lot of extra work in each pass. 

Simple calculation shows that insertion sort will therefore usually perform about half as many comparisons as selection sort, although it can perform just as many or far fewer depending on the order the array was in prior to sorting. It can be seen as an advantage for some real-time applications that selection sort will perform identically regardless of the order of the array, while insertion sort's running time can vary considerably.  However, this is more often an advantage for insertion sort in that it runs much more efficiently if the array is already sorted or "close to sorted
Finally, selection sort is greatly outperformed on larger arrays by Θ(n log n) divide-and-conquer algorithms such as mergesort. However, insertion sort or selection sort are both typically faster for small arrays (i.e. fewer than 10-20 elements). A useful optimization in practice for the recursive algorithms is to switch to insertion sort or selection sort for "small enough" sublists. The only significant advantage that bubble sort has over most other implementations, even quicksort, but not insertion sort, is that the ability to detect that the list is sorted is efficiently built into the algorithm. Performance of bubble sort over an already-sorted list (best-case) is O(n). By contrast, most other algorithms, even those with better average-case complexity, perform their entire sorting process on the set and thus are more complex. . However, not only does insertion sort have this mechanism too, but it also performs better on a list that is substantially sorted (having a small number of inversions).



 */

public class InsertionSort {

	public static void sort(int[] A){

		for (int i = 1; i < A.length; i++){

			int key = A[i];
			int j = i;
			while(j > 0 && A[j-1] > A[j]){
				swap(A, j-1, j);
				j--;
			}
		}
	}

	private static void swap(int[] A, int i, int j){
		int temp = A[i];
		A[i] = A[j];
		A[j] = temp;
	}

	public static void sort2(int[] A){

		sortRecursive(A, A.length);
	}

	private static void sortRecursive(int[] A, int i){
		if(i == 1) return;

		sortRecursive(A, i-1);
		putInSortedPlace(A, i);
	}

	/**
	 * Modifies the array A  so that it is sorted between indidices 0....i. 
	 * @param A  an array of integers sorted between indices 0...i-1   
	 */
	private static void putInSortedPlace(int[] A, int i){
		
			if( i == 0 || A[i-1] <= A[i])  return;
			else {
				if (A[i-1] > A[i]){
					swap(A, i, i-1);
					putInSortedPlace(A, i-1);
				}
			}
	}


}