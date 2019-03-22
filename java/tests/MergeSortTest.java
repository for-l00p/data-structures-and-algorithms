package tests;


import static org.junit.Assert.*;
import org.junit.Test;


import puzzles.MergeSort;

import org.junit.Ignore;

public class MergeSortTest {
	
	@Test  
	public void shouldReturnCorrect(){
		int[] input = {};
		int[] expected = {};
		MergeSort.mergeSort(input);
		assertArrayEquals(expected, input );
	}



	@Test 
	public void shouldReturnCorrect3(){
		int[] input = {1};
		int[] expected = {1};
		MergeSort.mergeSort(input);
		assertArrayEquals(expected, input );
	}

	@Test 
	public void shouldReturnCorrect4(){
		int[] input = {1,3,4,2};
		int[] expected = {1,2,3,4};
		MergeSort.mergeSort(input);
		assertArrayEquals(expected, input );
	}

	@Test
	public void shouldReturnCorrect5(){
		int[] input = {1,2,3,4};
		int[] expected = {1,2,3,4};
		MergeSort.mergeSort(input);
		assertArrayEquals(expected, input );
	}

	@Test 
	public void shouldReturnCorrect6(){
		int[] input = {4, 3, 2, 1};
		int[] expected = {1,2,3,4};
		MergeSort.mergeSort(input);
		assertArrayEquals(expected, input );
	}

	@Test 
	public void shouldReturnCorrect7(){
		int[] input = {-2,3,0, -4};
		int[] expected = {-4,-2,0,3};
		MergeSort.mergeSort(input);
		assertArrayEquals(expected, input );
	}

	@Test 
	public void shouldReturnCorrect8(){
		int[] input = {2,2,2, 2};
		int[] expected = {2,2,2,2};
		MergeSort.mergeSort(input);
		assertArrayEquals(expected, input );
	}

	@Test 
	public void shouldReturnCorrect9(){
		int[] input = {1,2,7,2, 4};
		int[] expected = {1,2,2,4,7};
		MergeSort.mergeSort(input);
		assertArrayEquals(expected, input );
	}

	@Test 
	public void shouldReturnCorrect10(){
		int[] input = {-1, 0, 9, 2, -1, 0, 4};
		int[] expected = {-1,-1,0,0,2, 4,9};
		MergeSort.mergeSort(input);
		assertArrayEquals(expected, input );
	}


}