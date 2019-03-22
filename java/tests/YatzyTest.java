package tests;


import static org.junit.Assert.*;
import org.junit.Test;

import puzzles.Yatzy;


public class YatzyTest {
	


	@Test
	public void shouldReturnCorrectForChance1(){

		int[] input = {1,1,3,3,6};
		String category = "chance";
		int output = 14;
		assertEquals(output, Yatzy.calc(input, category));
	}

	@Test
	public void shouldReturnCorrectForChance2(){

		int[] input = {4,5,5,6,1};
		String category = "chance";
		int output = 21;
		assertEquals(output, Yatzy.calc(input, category));
	}

	@Test
	public void shouldReturnCorrectForYatzy1(){

		int[] input = {1,1,1,1,1};
		String category = "yatzy";
		int output = 50;
		assertEquals(output, Yatzy.calc(input, category));
	}
	@Test
	public void shouldReturnCorrectForYatzy2(){

		int[] input = {5,5,5,5,5};
		String category = "yatzy";
		int output = 50;
		assertEquals(output, Yatzy.calc(input, category));
	}
	@Test
	public void shouldReturnCorrectForYatzy3(){

		int[] input = {1,1,1,2,1};
		String category = "yatzy";
		int output = 0;
		assertEquals(output, Yatzy.calc(input, category));
	}

	@Test
	public void shouldReturnCorrectForFours(){

		int[] input = {1,1,2,4,4};
		String category = "fours";
		int output = 8;
		assertEquals(output, Yatzy.calc(input, category));
	}
	@Test
	public void shouldReturnCorrectForTwos(){

		int[] input = {2,3,2,5,1};
		String category = "twos";
		int output = 4;
		assertEquals(output, Yatzy.calc(input, category));
	}
	@Test
	public void shouldReturnCorrectForOnes(){

		int[] input = {3,3,3,4,5};
		String category = "ones";
		int output = 0;
		assertEquals(output, Yatzy.calc(input, category));
	}
	@Test
	public void shouldReturnCorrectForPair(){

		int[] input = {3,3,3,4,4};
		String category = "pair";
		int output = 8;
		assertEquals(output, Yatzy.calc(input, category));
	}

	@Test
	public void shouldReturnCorrectForPair2(){

		int[] input = {1,1,6,2,6};
		String category = "pair";
		int output = 12;
		assertEquals(output, Yatzy.calc(input, category));
	}

	@Test
	public void shouldReturnCorrectForPair3(){

		int[] input = {3,3,3,4,1};
		String category = "pair";
		int output = 0;
		assertEquals(output, Yatzy.calc(input, category));
	}

	@Test
	public void shouldReturnCorrectForPair4(){

		int[] input = {3,3,3,3,1};
		String category = "pair";
		int output = 0;
		assertEquals(output, Yatzy.calc(input, category));
	}
	@Test
	public void shouldReturnCorrectForTwoPairs(){

		int[] input = {1,1,2,3,3};
		String category = "two pairs";
		int output = 8;
		assertEquals(output, Yatzy.calc(input, category));
	}

	@Test
	public void shouldReturnCorrectForTwoPairs2(){

		int[] input = {1,1,2,3,4};
		String category = "two pairs";
		int output = 0;
		assertEquals(output, Yatzy.calc(input, category));
	}
	@Test
	public void shouldReturnCorrectForTwoPairs3(){

		int[] input = {1,1,2,2,2};
		String category = "two pairs";
		int output = 0;
		assertEquals(output, Yatzy.calc(input, category));
	}
	@Test
	public void shouldReturnCorrectForThreeOfAKind(){

		int[] input = {3,3,3,4,5};
		String category = "three of a kind";
		int output = 9;
		assertEquals(output, Yatzy.calc(input, category));
	}

	@Test
	public void shouldReturnCorrectForThreeOfAKind2(){

		int[] input = {3,3,4,5,6};
		String category = "three of a kind";
		int output = 0;
		assertEquals(output, Yatzy.calc(input, category));
	}

	@Test
	public void shouldReturnCorrectForThreeOfAKind3(){

		int[] input = {3,3,3,3,1};
		String category = "three of a kind";
		int output = 0;
		assertEquals(output, Yatzy.calc(input, category));
	}
	@Test
	public void shouldReturnCorrectForFourOfAKind(){

		int[] input = {2,2,2,2,5};
		String category = "four of a kind";
		int output = 8;
		assertEquals(output, Yatzy.calc(input, category));
	}

	@Test
	public void shouldReturnCorrectForFourOfAKind2(){

		int[] input = {2,2,2,5,5};
		String category = "four of a kind";
		int output = 0;
		assertEquals(output, Yatzy.calc(input, category));
	}

	@Test
	public void shouldReturnCorrectForFourOfAKind3(){

		int[] input = {2,2,2,2,2};
		String category = "four of a kind";
		int output = 0;
		assertEquals(output, Yatzy.calc(input, category));
	}
	@Test
	public void shouldReturnCorrectForSmallStraight(){

		int[] input = { 1,2,3,4,5};
		String category = "small straight";
		int output = 15;
		assertEquals(output, Yatzy.calc(input, category));
	}

	@Test
	public void shouldReturnCorrectForSmallStraight2(){

		int[] input = { 1,2,4,5,6};
		String category = "small straight";
		int output = 0;
		assertEquals(output, Yatzy.calc(input, category));
	}

	@Test
	public void shouldReturnCorrectForSmallStraight4(){

		int[] input = { 1,2,3,4,6};
		String category = "small straight";
		int output = 0;
		assertEquals(output, Yatzy.calc(input, category));
	}


	@Test
	public void shouldReturnCorrectForLargeStraight(){

		int[] input = {2,3,4,5,6};
		String category = "large straight";
		int output = 20;
		assertEquals(output, Yatzy.calc(input, category));
	}

	@Test
	public void shouldReturnCorrectForLargeStraight2(){

		int[] input = {1,2,3,4,5};
		String category = "large straight";
		int output = 0;
		assertEquals(output, Yatzy.calc(input, category));
	}

	@Test
	public void shouldReturnCorrectForLargeStraight3(){

		int[] input = {2,3,4,5,1};
		String category = "large straight";
		int output = 0;
		assertEquals(output, Yatzy.calc(input, category));
	}

	@Test
	public void shouldReturnCorrectForFullHouse(){

		int[] input = {1,1,2,2,2};
		String category = "full house";
		int output = 8;
		assertEquals(output, Yatzy.calc(input, category));
	}

	@Test
	public void shouldReturnCorrectForFullHouse2(){

		int[] input = {2,2,3,3,4};
		String category = "full house";
		int output = 0;
		assertEquals(output, Yatzy.calc(input, category));
	}

	@Test
	public void shouldReturnCorrectForFullHouse3(){

		int[] input = {4,4,4,4,4};
		String category = "full house";
		int output = 0;
		assertEquals(output, Yatzy.calc(input, category));
	}

}