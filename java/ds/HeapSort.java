
/**
 * This class presents static methods for sorting an array. This class cannot be instantiated. 
 */

import java.util.Random;
// import java.lang.reflect.Array;
import java.util.Arrays;



public class HeapSort<E extends Comparable<E>>{


    // This class should not be instantiated
	private HeapSort(){

	}

	public void sort(E[] input){
		// build minHeap
		buildMaxHeap(input);
		for(int i = input.length-1; i>=0; i--){
			swap(input, 0,i);
			maxHeapify(input, 0, i-1);
		}

	}

	private void buildMaxHeap(E[] input){

		int length = input.length;

		for (int i = (length/2 -1); i >= 0; i--){
			maxHeapify(input, i , length - 1);
		}

	}

	private void maxHeapify(E[] input, int rootIndex, int lastLeafIndex){


		int leftChild = 2*rootIndex + 1;
		int rightChild = 2*rootIndex + 2;


		int largest = rootIndex;

		if(leftChild <= lastLeafIndex && input[leftChild].compareTo(input[rootIndex]) > 0){
			largest = leftChild;
		}

		if (rightChild <= lastLeafIndex && input[rightChild].compareTo(input[largest]) > 0){
			largest = rightChild;
		}

	    
	    if(largest != rootIndex){
	    	swap(input, rootIndex, largest);
	    	maxHeapify(input, largest, lastLeafIndex);
	    }
	  


	}

	private void swap(E[] input, int firstIndex, int secondIndex){

		if(firstIndex == secondIndex){
			return;
		}

		E temp = input[firstIndex];
		input[firstIndex] = input[secondIndex];
		input[secondIndex] = temp;
	}

	
	public static void main(String[] args) {
    	final int SIZE = 25;      // size of the array to sort
   		final int RANGE = 1000;   // upper limit of random ints generated
    	Integer a[] = new Integer[SIZE];
    	Random generator = new Random();
    	HeapSort<Integer> sorter = new HeapSort<Integer>();
    	// ArrayList<Integer> list = new ArrayList<Integer>();

   		for (int i = 0; i < SIZE; i++) {
    		a[i] = new Integer(generator.nextInt(RANGE));
    		// list.add(a[i]);
   		 }

   		System.out.println(Arrays.toString(a));
    	sorter.sort(a);
   		System.out.println(Arrays.toString(a));
    
  }


	





}