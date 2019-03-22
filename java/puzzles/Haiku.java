package puzzles;

/**
 * 
 
Haiku is an ancient form of Japanese poetry. A haiku
is a three-line poem with seventeen syllables, where
the first line must contain five syllables, the second
line must contain seven syllables, and the third line
must contain five syllables. The lines do not have to
rhyme. Here is an example, where slashes separate the
lines:

Computer programs/The bugs try to eat my code/I must
not let them.

You must write a program that will review a haiku and
check that each line contains the correct number of
syllables.

Input

The input contains one or more lines, each of which
contains a single haiku. A haiku will contain at least
three words, and words will be separated by either a
single space or a slash ('/'). Slashes also separate
the three lines of a haiku, so each haiku will contain
exactly two slashes. (The three lines of the haiku will
be contained within one physical line of the file.)

A haiku will contain only lowercase letters ('a'-'z'),
forward slashes ('/'), and spaces, and will be no more
than 200 characters long (not counting the end-of-line
characters).

Each haiku is guaranteed to contain three lines, and
each line will contain at least one word. Your job is
to determine whether each line has the correct number
of syllables (5/7/5). For the purposes of this problem,
every contiguous sequence of one or more vowels counts
as one syllable, where the vowels are
a, e, i, o, u, and y. Every word will contain at least
one syllable.

(Note that this method of counting syllables does not
always agree with English conventions. In the second
example below, your program must consider the word
'code' to have two syllables because the 'o' and the
'e' are not consecutive. However, in English the 'e'
is silent and so 'code' actually has only one syllable.)

Output

For each haiku, output a comma-separated single line
that contains the number of syllables in each haiku,
together with the letter Y if it is a haiku, or N if
it is not a haiku (see below).


Sample Input
------------
happy purple frog/eating bugs in the marshes/get indigestion
computer programs/the bugs try to eat my code/i will not let them

Sample Output
-------------
5,7,5,Yes
5,8,5,No


[Source: http://uva.onlinejudge.org/]
   

 */

import java.util.Arrays;

public class Haiku {
	

	public static String parse (String text){
		if (text.length() > 200) throw new IllegalArgumentException("Num of characters should not be greater than 200");

		String[] lines = text.split("/");

		int n = lines.length;
		if (n != 3 ) throw new IllegalArgumentException ("arguments should contain exactly 3 lines");


		int[] numSyllables = new int[n];

		for (int i = 0; i < n; i++){
			numSyllables[i] = findLineSyllables(lines[i]);
		}

		int[] haikuSyllables = new int[]{5,7,5};

		boolean isHaiku = Arrays.equals(haikuSyllables, numSyllables);

		return formDisplayString(numSyllables, isHaiku);	

	}

	private static String formDisplayString(int[] numSyllables, boolean isHaiku){

		StringBuilder sb = new StringBuilder();
		for (int num: numSyllables){
			sb.append(num);
			sb.append(",");
		}
		if (isHaiku){
			sb.append("Yes");
		} else {
			sb.append("No");
		}

		return String.valueOf(sb);

	}

	public static int findLineSyllables(String line){
		String[] words = line.split(" ");
		int numSyllables = 0;
		for (String word: words){
			numSyllables = numSyllables + countSyllables(word);
		}

		return numSyllables;
	}

	public static int countSyllables(String word){

		return countSyllables(word, 0,0);
	}

	private static int countSyllables(String word, int index, int count){

		if (index == word.length()){
			return count;
		} else {
			char c = word.charAt(index);
			if (isVowel(c)){
				if (index != word.length() - 1 && isVowel(word.charAt(index+1))){
					return countSyllables(word, index +1, count);
				} else {
					return countSyllables(word, index+1, count + 1);
				}
			}
			return countSyllables(word, index + 1, count);
		}
	}

	private static boolean isVowel(char c){
		return "aeiouy".indexOf(c) >= 0;
	}




}