package tests;

import static org.junit.Assert.*;
import org.junit.Test;

import puzzles.Unsplice;

public class UnspliceTest {
	


	@Test
	public void shouldReturnCorrectForTwoStrippedOut(){

		String input = "ab\\\ncd\\\nef";
		String expected = "abcdef";
		assertEquals(expected, Unsplice.answer(input));
	}

	@Test
	public void shouldReturnCorrectForOneStrippedOut(){

		String input = "abc\\\ndef";
		String expected = "abcdef";
		assertEquals(expected, Unsplice.answer(input));
	}

	@Test
	public void shouldReturnCorrectForWrongOrder(){

		String input = "abc\n\\def";
		String expected = "abc\n\\def";
		assertEquals(expected, Unsplice.answer(input));
	}

	@Test
	public void shouldReturnCorrectNoNewLine(){

		String input = "abc\\def";
		String expected = "abc\\def";
		assertEquals(expected, Unsplice.answer(input));
	}

	@Test
	public void shouldReturnCorrectForNoSlash(){

		String input = "abc\ndef" ;
		String expected = "abc\ndef";
		assertEquals(expected, Unsplice.answer(input));
	}


	@Test
	public void shouldReturnCorrectForNormal(){

		String input =  "abcdef"   ;
		String expected =  "abcdef" ;
		assertEquals(expected, Unsplice.answer(input));
	}
}


