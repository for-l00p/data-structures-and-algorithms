package tests;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.Ignore;


import puzzles.Zeckendorf;


public class ZeckendorfTest{
	


	@Test 
	public void shouldReturnCorrect(){

		int input = 0;
		String output = "0";
		assertEquals(output, Zeckendorf.get(input));

	}

	@Test 
	public void shouldReturnCorrect2(){

		int input = 1;
		String output = "1";
		assertEquals(output, Zeckendorf.get(input));

	}

	@Test 
	public void shouldReturnCorrect3(){

		int input = 2;
		String output = "10";
		assertEquals(output, Zeckendorf.get(input));

	}


	@Test 
	public void shouldReturnCorrect4(){

		int input = 3;
		String output = "100";
		assertEquals(output, Zeckendorf.get(input));

	}


	@Test 
	public void shouldReturnCorrect5(){

		int input = 4;
		String output = "101";
		assertEquals(output, Zeckendorf.get(input));

	}


	@Test 
	public void shouldReturnCorrect6(){

		int input = 11;
		String output = "10100";
		assertEquals(output, Zeckendorf.get(input));

	}

	@Test 
	public void shouldReturnCorrect7(){

		int input = 10946;
		String output = "10000000000000000000";
		assertEquals(output, Zeckendorf.get(input));

	}
}