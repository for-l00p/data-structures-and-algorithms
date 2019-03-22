package tests;


import puzzles.HarryPotter;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.Ignore;



public class HarryPotterTest {
	
	

	@Test
	public void shouldReturnCorrectForEmptyBasket(){

		int[] input = new int[5];
		double expected = 0;
		assertEquals(expected, HarryPotter.cartTotal(input),0);
	}

	@Test 
	public void shouldReturnCorrectForSingleBooks(){

		int[] input = new int[]{0,0,1,0,0};
		double expected = 8;
		assertEquals(expected, HarryPotter.cartTotal(input),0);
	}


	@Test 
	public void shouldReturnCorrectForTwoBooks(){

		int[] input = new int[]{0,1,1,0,0};
		double expected = 15.2;
		assertEquals(expected, HarryPotter.cartTotal(input),0);
	}

	@Test 
	public void shouldReturnCorrectForThreeBooks(){

		int[] input = new int[]{0,1,1,1,0};
		double expected = 21.6;
		assertEquals(expected, HarryPotter.cartTotal(input),0);
	}

	@Test 
	public void shouldReturnCorrectForFourBooks(){

		int[] input = new int[]{1,1,1,0,1};
		double expected = 25.6;
		assertEquals(expected, HarryPotter.cartTotal(input),0);
	}




	@Test 
	public void shouldReturnCorrectForAllBooks(){

		int[] input = new int[]{1,1,1,1,1};
		double expected = 30;
		assertEquals(expected, HarryPotter.cartTotal(input),0);
	}


	@Test 
	public void shouldReturnCorrect3(){

		int[] input = new int[]{2,2,2,1,1};
		double expected = 51.20;
		assertEquals(expected, HarryPotter.cartTotal(input),0);
	}

	@Test 
	public void shouldReturnCorrect4(){

		int[] input = new int[]{3,2,4,2,1};
		double expected = 78.8;
		assertEquals(expected, HarryPotter.cartTotal(input),0);
	}

	@Test 
	public void shouldReturnCorrect6(){

		int[] input = new int[]{1,2,3,4,5};
		double expected = 100;
		assertEquals(expected, HarryPotter.cartTotal(input),0);
	}


	// @Test
	// public void shouldReturnCorrect7(){

	// 	int[] input = new int[]{12,19,11,12,15};
	// 	System.out.println(HarryPotter.cartTotal(input));
	// }



}