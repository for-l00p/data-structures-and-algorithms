package tests;


import static org.junit.Assert.*;
import org.junit.Test;


import puzzles.InsertionSort;

import org.junit.Ignore;

public class InsertionSortTest {
	
	@Test  
	public void shouldReturnCorrect(){
		int[] input = {};
		int[] expected = {};
		InsertionSort.sort(input);
		assertArrayEquals(expected, input );
	}



	@Test 
	public void shouldReturnCorrect3(){
		int[] input = {1};
		int[] expected = {1};
		InsertionSort.sort(input);
		assertArrayEquals(expected, input );
	}

	@Test 
	public void shouldReturnCorrect4(){
		int[] input = {1,3,4,2};
		int[] expected = {1,2,3,4};
		InsertionSort.sort(input);
		assertArrayEquals(expected, input );
	}

	@Test 
	public void shouldReturnCorrect5(){
		int[] input = {1,2,3,4};
		int[] expected = {1,2,3,4};
		InsertionSort.sort(input);
		assertArrayEquals(expected, input );
	}

	@Test 
	public void shouldReturnCorrect6(){
		int[] input = {4, 3, 2, 1};
		int[] expected = {1,2,3,4};
		InsertionSort.sort(input);
		assertArrayEquals(expected, input );
	}

	@Test 
	public void shouldReturnCorrect7(){
		int[] input = {-2,3,0, -4};
		int[] expected = {-4,-2,0,3};
		InsertionSort.sort(input);
		assertArrayEquals(expected, input );
	}

	@Test 
	public void shouldReturnCorrect8(){
		int[] input = {2,2,2, 2};
		int[] expected = {2,2,2,2};
		InsertionSort.sort(input);
		assertArrayEquals(expected, input );
	}

	@Test 
	public void shouldReturnCorrect9(){
		int[] input = {1,2,7,2, 4};
		int[] expected = {1,2,2,4,7};
		InsertionSort.sort(input);
		assertArrayEquals(expected, input );
	}

	@Test 
	public void shouldReturnCorrect10(){
		int[] input = {-1, 0, 9, 2, -1, 0, 4};
		int[] expected = {-1,-1,0,0,2, 4,9};
		InsertionSort.sort(input);
		assertArrayEquals(expected, input );
	}


}