package tests;


import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;

import puzzles.Haiku;

public class HaikuTest {
	
	
	@Test(expected = IllegalArgumentException.class) 
	public void shouldReturnCorrect0(){

		String input = "";
		Haiku.parse(input);
	}

	@Test(expected = IllegalArgumentException.class) 
	public void shouldReturnCorrect00(){

		String input = "hey/ this is less";
		Haiku.parse(input);
	}

	@Test (expected = IllegalArgumentException.class)
	public void shouldReturnCorrect000(){

		String input = "/me/ba/this is more than four lines";
		Haiku.parse(input);
	}

 
	@Test
	public void shouldReturnCorrectEmptyLine(){

		String input = "/ba/a";
		String output = "0,1,1,No";
		assertEquals(output, Haiku.parse(input));
	}





	@Test @Ignore
	public void shouldReturnCorrect1(){

		String input = "happy purple frog/eating bugs in the marshes/get indigestion";
		String output = "5,7,5,Yes";
		assertEquals(output, Haiku.parse(input));
	}

	@Test  @Ignore
	public void shouldReturnCorrect2(){

		String input = "computer programs/the bugs try to eat my code/i will not let them";
		String output = "5,8,5,No";
		assertEquals(output, Haiku.parse(input));
	}







}