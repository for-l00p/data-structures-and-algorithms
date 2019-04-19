package tests;


import puzzles.dp.MinCoins;


import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;


public class MinCoinsTest {
	
	@Test 
	public void minCoinsShouldReturnCorrectForZero(){
		int amount = 0;
		int[] denominations = new int[]{1,5,10, 25};

		assertEquals(0, MinCoins.findMinCoinsChange(amount, denominations));
	}

	@Test @Ignore
	public void minCoinsShouldReturnCorrectForNegative(){
		int amount = -1;
		int[] denominations = new int[]{1,5,10, 25};

		assertEquals(-1, MinCoins.findMinCoinsChange(amount, denominations));
	}

	@Test 
	public void minCoinsShouldReturnCorrectForNotPossible(){
		int amount = 7;
		int[] denominations = new int[]{3};

		assertEquals(-1, MinCoins.findMinCoinsChange(amount, denominations));
	}



	@Test 
	public void minCoinsShouldReturnCorrectFor6249(){
		int amount = 6249;
		int[] denominations = new int[]{186, 419, 83, 408};

		assertEquals(20, MinCoins.findMinCoinsChange(amount, denominations));
	}

	@Test 
	public void minCoinsShouldReturnCorrectFor5(){
		int amount = 5;
		int[] denominations = new int[]{1,2,3};

		assertEquals(2, MinCoins.findMinCoinsChange(amount, denominations));
	}

	@Test 
	public void minCoinsShouldReturnCorrectFor30(){
		int amount = 30;
		int[] denominations = new int[]{25, 10, 5};

		assertEquals(2, MinCoins.findMinCoinsChange(amount, denominations));
	}

	@Test 
	public void minCoinsShouldReturnCorrectFor11(){
		int amount = 11;
		int[] denominations = new int[]{9, 6, 5, 1};

		assertEquals(2, MinCoins.findMinCoinsChange(amount, denominations));
	}

	@Test 
	public void minCoinsShouldReturnCorrectFor12(){
		int amount = 100;
		int[] denominations = new int[]{1,5,10, 25};

		assertEquals(4, MinCoins.findMinCoinsChange(amount, denominations));
	}


	

}