

package tests;

import org.junit.Test;
import static org.junit.Assert.*;


import puzzles.GrayCode;

public class GrayCodeTest{
	

	@Test
	public void shouldReturnCorrect(){

		int input = 0;
		String expected = "0";
		assertEquals(expected, GrayCode.getGray(input));

	}

	@Test
	public void shouldReturnCorrect2(){

		String input = "0";
		int expected = 0;
		assertEquals(expected, GrayCode.convertGrayToArabic(input));

	}

	@Test
	public void shouldReturnCorrect3(){

		int input = 0;
		String expected = "0";
		assertEquals(expected, GrayCode.getBinary(input));

	}

	@Test
	public void shouldReturnCorrect3(){

		int input = 0;
		String expected = "0";
		assertEquals(expected, GrayCode.getBinary(input));

	}
	
	@Test
	public void shouldReturnCorrect3(){

		int input = 0;
		String expected = "0";
		assertEquals(expected, GrayCode.getBinary(input));

	}
	@Test
	public void shouldReturnCorrect3(){

		int input = 0;
		String expected = "0";
		assertEquals(expected, GrayCode.getBinary(input));

	}



}