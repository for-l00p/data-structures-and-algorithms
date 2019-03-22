package tests;


import puzzles.dp.CoinCount;


import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;


public class CoinCountTest {
	

	@Test  
	public void shouldReturnCorrectForDollar(){

		int amount = 100;
		int[] denominations = new int[]{1, 5, 10, 25};

		assertEquals(242, CoinCount.makeChangeIterative4(amount, denominations));
	} 


	@Test  
	public void shouldReturnCorrectForLargestCoinEqualMoney(){

		int amount = 10;
		int[] denominations = new int[]{1, 10};

		assertEquals(2, CoinCount.makeChangeIterative4(amount, denominations));
	} 



	@Test 
	public void shouldReturnCorrectForZeroAmount(){
		int amount = 0;
		int[] denominations = new int[]{1,2,3};

		assertEquals(1, CoinCount.makeChangeIterative4(amount, denominations));
	} 

	@Test 
	public void shouldReturnCorrectForOneCoin(){
		int amount = 5;
		int[] denominations = new int[]{1};

		assertEquals(1, CoinCount.makeChangeIterative4(amount, denominations));
	} 

	@Test
	public void shouldReturnCorrectForTen(){
		int amount =10;
		int[] denominations = new int[]{2,5,3,6};

		assertEquals(5, CoinCount.makeChangeIterative4(amount, denominations));
	} 

	


	@Test 
	public void shouldReturnCorrectForThree(){
		int amount = 3;
		int[] denominations = new int[]{8,3,1,2};

		assertEquals(3, CoinCount.makeChangeIterative4(amount, denominations));
	} 

	@Test 
	public void shouldReturnCorrectForFive(){
		int amount = 5;
		int[] denominations = new int[]{1,2,5};

		assertEquals(4, CoinCount.makeChangeIterative4(amount, denominations));
	} 

	@Test 
	public void shouldReturnCorrectForFour(){
		int amount = 4;
		int[] denominations = new int[]{1,2,3};

		assertEquals(4, CoinCount.makeChangeIterative4(amount, denominations));
	} 



	@Test 
	public void shouldReturnCorrectWhenAmountNotDivisbleBySingleDenomination(){
		int amount = 3;
		int[] denominations = new int[]{2};

		assertEquals(0, CoinCount.makeChangeIterative4(amount, denominations));
	} 

	@Test 
	public void shouldReturnCorrectWhenAmountEqualsDenominationSingle(){
		int amount = 10;
		int[] denominations = new int[]{10};

		assertEquals(1, CoinCount.makeChangeIterative4(amount, denominations));
	} 


	


 
	@Test 
	public void minCoinsShouldReturnCorrectFor6249(){
		int amount = 6249;
		int[] denominations = new int[]{186, 419, 83, 408};

		assertEquals(20, CoinCount.findMinCoinsChange(amount, denominations));
	}

	@Test 
	public void minCoinsShouldReturnCorrectFor5(){
		int amount = 5;
		int[] denominations = new int[]{1,2,3};

		assertEquals(2, CoinCount.findMinCoinsChange(amount, denominations));
	}

	@Test 
	public void minCoinsShouldReturnCorrectFor30(){
		int amount = 30;
		int[] denominations = new int[]{25, 10, 5};

		assertEquals(2, CoinCount.findMinCoinsChange(amount, denominations));
	}

	@Test 
	public void minCoinsShouldReturnCorrectFor11(){
		int amount = 11;
		int[] denominations = new int[]{9, 6, 5, 1};

		assertEquals(2, CoinCount.findMinCoinsChange(amount, denominations));
	}


	

}