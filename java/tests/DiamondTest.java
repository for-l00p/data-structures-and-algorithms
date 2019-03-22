

package tests;

import org.junit.Test;
import static org.junit.Assert.*;

import puzzles.Diamond;

public class DiamondTest {
	
	

	@Test
	public void  testHookupShouldWork(){

		assertEquals(4, 2+2);
	}

	@Test 
	public void  print_ShouldReturnSingleStringA_WhenInputIsA(){

		Diamond maker = new Diamond('A');
		String[] lines = maker.getStrings();
		assertEquals("A", lines[0].trim());
	}


	// @Test 
	// public void  print_ShouldReturnCorrectly_WhenInputIsB(){

	// 	Diamond maker = Diamond.newD('B');
	// 	String[] diamond = maker.getStrings();
	// }




}