
package tests;

import static org.junit.Assert.*;
import org.junit.Test;

import puzzles.CombinedNumber;


public class CombinedNumberTest {
	
	@Test
	public void shouldReturnCorrect1(){
		int[] input = new int[]{50, 2, 1, 9};
		String expected = "95021";
		assertEquals(expected, CombinedNumber.combine(input));
	}

	@Test
	public void shouldReturnCorrect2() {
		int[] input = new int[]{5, 50, 56};
		String expected = "56550";
		assertEquals(expected, CombinedNumber.combine(input));
	}

	@Test
	public void shouldReturnCorrect3(){
		int[] input = new int[]{420, 42, 423};
		String expected = "42423420";
		assertEquals(expected, CombinedNumber.combine(input));
	}

	@Test
	public void shouldReturnCorrect4(){
		int[] input = new int[]{3,30,34,5,9};
		String expected = "9534330";
		assertEquals(expected, CombinedNumber.combine(input));
	}

	@Test
	public void shouldReturnCorrect5(){
		int[] input = new int[]{10,2};
		String expected = "210";
		assertEquals(expected, CombinedNumber.combine(input));
	}
}


