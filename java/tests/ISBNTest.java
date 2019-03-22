package tests;

import org.junit.Test;
import static org.junit.Assert.*;


import puzzles.ISBN;


public class ISBNTest {
	
    
            
    @Test
	public void shouldReturnCorrectForEmpty(){
		String input = "";
		assertEquals(false, ISBN.isValid10(input));
	} 

	@Test
	public void shouldReturnCorrectForLessThan10(){
		String input = "388279";
		assertEquals(false, ISBN.isValid10(input));
	}      
          

	@Test
	public void shouldReturnCorrect01(){
		String input = "0471958697";
		assertEquals(true, ISBN.isValid10(input));
	}

	@Test
	public void shouldReturnCorrect02(){
		String input = "0 471 60695 2";
		assertEquals(true, ISBN.isValid10(input));
	}

	@Test
	public void shouldReturnCorrect03(){
		String input = " 0-470-84525-2";
		assertEquals(true, ISBN.isValid10(input));
	}

	@Test
	public void shouldReturnCorrect04(){
		String input = "0-321-14653-0";
		assertEquals(true, ISBN.isValid10(input));
	}
	
            
    @Test
	public void shouldReturnCorrect(){
		String input = "";
		assertEquals(false, ISBN.isValid13(input));
	}  

	@Test
	public void shouldReturnCorrectForLessThan13(){
		String input = "12308";
		assertEquals(false, ISBN.isValid13(input));
	} 

	@Test
	public void shouldReturnCorrectForIncorrectCheckDigit(){
		String input = "9780470059022";
		assertEquals(false, ISBN.isValid13(input));
	}
     
            
            

	@Test
	public void shouldReturnCorrect11(){
		String input = "9780470059029";
		assertEquals(true, ISBN.isValid13(input));
	}

	@Test
	public void shouldReturnCorrect12(){
		String input = "978 0 471 48648 0";
		assertEquals(true, ISBN.isValid13(input));
	}

	@Test
	public void shouldReturnCorrect13(){
		String input = "978-0596809485";
		assertEquals(true, ISBN.isValid13(input));
	}

	@Test
	public void shouldReturnCorrect14(){
		String input = "978-0-13-149505-0";
		assertEquals(true, ISBN.isValid13(input));
	}

	@Test
	public void shouldReturnCorrect15(){
		String input = "978-0-262-13472-9";
		assertEquals(true, ISBN.isValid13(input));
	}


	@Test
	public void shouldReturnCorrect05(){
		String input = "0136091814";
		assertEquals(true, ISBN.isValid10(input));
	}

	@Test
	public void shouldReturnCorrect06(){
		String input = "0136091812";
		assertEquals(false, ISBN.isValid10(input));
	}

	@Test
	public void shouldReturnCorrect16(){
		String input = "9780136091813";
		assertEquals(true, ISBN.isValid13(input));
	}

	@Test
	public void shouldReturnCorrect17(){
		String input = "9780136091813";
		assertEquals(true, ISBN.isValid13(input));
	}

	@Test
	public void shouldReturnCorrect07(){
		String input = "123456789X";
		assertEquals(true, ISBN.isValid10(input));
	}



}



 