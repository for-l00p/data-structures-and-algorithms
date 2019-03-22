package tests;


import puzzles.RomanNumerals;


import static org.junit.Assert.*;
import org.junit.Test;


public class RomanNumeralsTest {
	
	
	@Test
	public void shouldReturnCorrect(){

		int input = 42;
		String expected = "XLII";
		assertEquals(expected, RomanNumerals.convertToRoman(input));
	}

	@Test
	public void shouldReturnCorrect2(){

		int input = 1990;
		String expected = "MCMXC";
		assertEquals(expected, RomanNumerals.convertToRoman(input));
	}

	@Test
	public void shouldReturnCorrect3(){

		int input = 2008;
		String expected = "MMVIII";
		assertEquals(expected, RomanNumerals.convertToRoman(input));
	}

	@Test
	public void shouldReturnCorrect4(){

		int input = 99;
		String expected = "XCIX";
		assertEquals(expected, RomanNumerals.convertToRoman(input));
	}

	@Test
	public void shouldReturnCorrect5(){

		int input = 47;
		String expected = "XLVII";
		assertEquals(expected, RomanNumerals.convertToRoman(input));
	}

	@Test
	public void shouldReturnCorrect6(){

		int input = 1994;
		String expected = "MCMXCIV";
		assertEquals(expected, RomanNumerals.convertToRoman(input));
	}
	

	@Test
	public void shouldReturnCorrect7(){

		int input = 1897;
		String expected = "MDCCCXCVII";
		assertEquals(expected, RomanNumerals.convertToRoman(input));
	}

	@Test
	public void shouldReturnCorrectForReverse(){

		int input = 1897;
		String expected = "MDCCCXCVII";
		assertEquals(input, RomanNumerals.convertToDecimal(expected));
	}


	@Test
	public void shouldReturnCorrectForReverse2(){

		int input = 42;
		String expected = "XLII";
		assertEquals(input, RomanNumerals.convertToDecimal(expected));
	}

	@Test
	public void shouldReturnCorrectForReverse3(){

		int input = 1990;
		String expected = "MCMXC";
		assertEquals(input, RomanNumerals.convertToDecimal(expected));
	}

	@Test
	public void shouldReturnCorrectForReverse4(){

		int input = 2008;
		String expected = "MMVIII";
		assertEquals(input, RomanNumerals.convertToDecimal(expected));
	}

	@Test
	public void shouldReturnCorrectForReverse5(){
		
		int input = 99;
		String expected = "XCIX";
		assertEquals(input, RomanNumerals.convertToDecimal(expected));
	}

	@Test
	public void shouldReturnCorrectForReverse6(){

		int input = 47;
		String expected = "XLVII";
		assertEquals(input, RomanNumerals.convertToDecimal(expected));
	}

	@Test
	public void shouldReturnCorrectForReverse7(){

		int input = 1994;
		String expected = "MCMXCIV";
		assertEquals(input, RomanNumerals.convertToDecimal(expected));
	}
	

	@Test
	public void shouldReturnCorrectForReverse8(){

		int input = 1897;
		String expected = "MDCCCXCVII";
		assertEquals(input, RomanNumerals.convertToDecimal(expected));
	}
	

}