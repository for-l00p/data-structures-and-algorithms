package tests;


import org.junit.Test;
import static org.junit.Assert.*;

import puzzles.NumberChains;

public class NumberChainsTest {
	
	@Test
	public void shouldReturnCorrect(){

		int input = 123456789;
		int expected = 2;
		assertEquals(expected, NumberChains.find(input));
	}

	@Test
	public void shouldReturnCorrect2(){

		int input = 1234;
		int expected = 4;
		assertEquals(expected, NumberChains.find(input));
	}

	@Test
	public void shouldReturnCorrect3(){

		int input = 444;
		int expected = 2;
		assertEquals(expected, NumberChains.find(input));
	}







}