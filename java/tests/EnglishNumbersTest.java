package tests;

import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;

import puzzles.EnglishNumbers;


public class EnglishNumbersTest {

	@Test
	public void shouldReturnCorrect(){

		int input = 99;
		String expected = "ninety nine";
		assertEquals(expected, EnglishNumbers.spell(input));
	}

	@Test 
	public void shouldReturnCorrect2(){

		int input = 300;
		String expected = "three hundred";
		assertEquals(expected, EnglishNumbers.spell(input));
	}


	@Test 
	public void shouldReturnCorrect3(){

		int input =  310;
		String expected = "three hundred and ten";
		assertEquals(expected, EnglishNumbers.spell(input));
	}

	@Test 
	public void shouldReturnCorrect4(){

		int input = 1501;
		String expected = "one thousand, five hundred and one";
		assertEquals(expected, EnglishNumbers.spell(input));
	}

	@Test 
	public void shouldReturnCorrect55(){

		int input = 12;
		String expected = "twelve";
		assertEquals(expected, EnglishNumbers.spell(input));
	}


	@Test 
	public void shouldReturnCorrect5(){

		int input = 12609;
		String expected = "twelve thousand, six hundred and nine";
		assertEquals(expected, EnglishNumbers.spell(input));
	}
	@Test 
	public void shouldReturnCorrect6(){

		int input = 512607;
		String expected = "five hundred and twelve thousand, six hundred and seven";
		assertEquals(expected, EnglishNumbers.spell(input));
	}

	@Test 
	public void shouldReturnCorrect7(){

		int input = 43112603;
		String expected = "forty three million, one hundred and twelve thousand, six hundred and three";
		assertEquals(expected, EnglishNumbers.spell(input));
	}

	@Test 
	public void shouldReturnCorrectLeetCode(){

		int input = 123;
		String expected = "One Hundred Twenty Three";
		assertEquals(expected, EnglishNumbers.spell2(input));
	}

	@Test 
	public void shouldReturnCorrectLeetCode2(){

		int input = 12345;
		String expected = "Twelve Thousand Three Hundred Forty Five";
		assertEquals(expected, EnglishNumbers.spell2(input));
	}

	@Test 
	public void shouldReturnCorrectLeetCode3(){

		int input = 1234567;
		String expected = "One Million Two Hundred Thirty Four Thousand Five Hundred Sixty Seven";
		assertEquals(expected, EnglishNumbers.spell2(input));
	}

	@Test
	public void shouldReturnCorrectLeetCode4(){

		int input = 1234567891;
		String expected = "One Billion Two Hundred Thirty Four Million Five Hundred Sixty Seven Thousand Eight Hundred Ninety One";
		assertEquals(expected, EnglishNumbers.spell2(input));
	}

	@Test
	public void shouldReturnCorrectLeetCode5(){

		int input = 0;
		String expected = "Zero";
		assertEquals(expected, EnglishNumbers.spell2(input));
	}

	@Test
	public void shouldReturnCorrectLeetCode6(){

		int input = 1000;
		String expected = "One Thousand";
		assertEquals(expected, EnglishNumbers.spell2(input));
	}

	@Test
	public void shouldReturnCorrectLeetCode7(){

		int input = 14;
		String expected = "Fourteen";
		assertEquals(expected, EnglishNumbers.spell2(input));
	}

	@Test
	public void shouldReturnCorrectLeetCode8(){

		int input = 20;
		String expected = "Twenty";
		assertEquals(expected, EnglishNumbers.spell2(input));
	}

	@Test
	public void shouldReturnCorrectLeetCode9(){

		int input = 12345;
		String expected = "Twelve Thousand Three Hundred Forty Five";
		assertEquals(expected, EnglishNumbers.spell2(input));
	}

	@Test
	public void shouldReturnCorrectLeetCode10(){

		int input = 13;
		String expected = "Thirteen";
		assertEquals(expected, EnglishNumbers.spell2(input));
	}
	//2,147,483,647
}
 
  
