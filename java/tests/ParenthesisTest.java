package tests;

import puzzles.Parenthesis;

import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;


public class ParenthesisTest{
	

	@Test 
	public void shouldReturnFalseForWrongOrderCurvy(){

		String input = "{{)	(}}";
		assertEquals(false, Parenthesis.isValid(input));
	}

	@Test 
	public void shouldReturnFalseForIncorrectWithinAnother(){

		String input = "({)}";
		assertEquals(false, Parenthesis.isValid(input));
	}


	@Test 
	public void shouldReturnTrueForBalanced(){

		String input = "[({})]";
		assertEquals(true, Parenthesis.isValid(input));
	}

	@Test 
	public void shouldReturnTrueForBalanced2(){

		String input = "{}([])";
		assertEquals(true, Parenthesis.isValid(input));
	}

	@Test 
	public void shouldReturnTrueForBalanced3(){

		String input = "{()}[[{}]]";
		assertEquals(true, Parenthesis.isValid(input));
	}


	@Test 
	public void shouldReturnTrueForEmpty(){

		String input = "()";
		assertEquals(true, Parenthesis.isValid(input));
	}


	@Test 
	public void shouldReturnFalseForInconsistentOverlappingEmpty(){

		String input = "([)]";
		assertEquals(false, Parenthesis.isValid(input));
	}



	@Test  
	public void shouldReturnFalseForEmptyDifferent(){

		String input = "(]";
		assertEquals(false, Parenthesis.isValid(input));
	}


	@Test 
	public void shouldReturnTrueForNonOverlappingEmptyBalanced(){

		String input = "()[]{}";
		assertEquals(true, Parenthesis.isValid(input));
	}

	@Test 
	public void shouldReturnTrueForOverlappingEmptyBalanced(){

		String input = "{[]}";
		assertEquals(true, Parenthesis.isValid(input));
	}



}