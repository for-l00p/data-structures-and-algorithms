
package tests;

import static org.junit.Assert.*;
import org.junit.Test;

import puzzles.WordWrap;

/** Assume single spaces. Width will be greater than or equal to one

Write a function that takes in a string, inserts newline characters, and adds a hyphen on the end of a line if a word is continued on the next line.
If possible, line breaks should not split words. However, if a single word is longer than the limit, it obviously has to be split. In this case, the line break should be placed after the first part of the word (see examples below). Really long words may need to be split multiple times.

("test", 7) -> "test"
("hello world", 7) -> "hello--world"
("a lot of words for a single line", 10) -> "a lot of--words for--a single--line"
("this is a test", 4) -> "this--is a--test"
("a longword", 6) -> "a long--word"
("areallylongword", 6) -> "areall--ylongw--ord"

var input = "The quick brown fox jumped over the lazy developer.";
var expectedResult ="The quick brown fox jump-\ned over the lazy develop-\ner.";
var result = wordWrap(input);
Test.expect(result === expectedResult);

**/

public class WordWrapTest {
	
	
	@Test
	public void shouldReturnEmptyForEmpty(){
		String text = "";
		String expected = "";
		int width = 1;
		assertEquals(expected, WordWrap.wrap(text, width));
	}

	@Test
	public void shouldReturnCorrectForSingleLetter(){
		String text = "a";
		String expected = "a";
		int width = 1;
		assertEquals(expected, WordWrap.wrap(text, width));
	}


	@Test
	public void shouldReturnCorrectForMultipleSpaces(){
		String text = "aa  b";
		String expected = "aa \n b";
		int width = 3;
		assertEquals(expected, WordWrap.wrap(text, width));
	}

	@Test
	public void shouldReturnCorrectForLongWords(){
		String text = "abcd";
		String expected = "abc\nd";
		int width = 3;
		assertEquals(expected, WordWrap.wrap(text, width));
	}

	@Test
	public void shouldReturnCorrectForManySpaces(){
		String text = "aabc  aab    a b  ";
		String expected = "aabc\n  \naab \n   a\n b  ";
		int width = 4;
		assertEquals(expected, WordWrap.wrap(text, width));
	}

	@Test
	public void shouldReturnCorrectForLongText(){
		String text = "my name is Piyush. I am supposedly a good programmer.";
		String expected = "my name\n is \nPiyush.\n I am s\nupposed\nly a \ngood pr\nogramme\nr.";
		int width = 7;
		assertEquals(expected, WordWrap.wrap(text, width));
	}



	;
}