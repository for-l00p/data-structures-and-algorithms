package puzzles;


/**
 * 
 * Two strings are said to be anagrams of one another if you can turn the first string into
the second by rearranging its letters. For example, “table” and “bleat” are anagrams, as
are “tear” and “rate.” Your job is to write a function that takes in two strings as input and determines whether they're anagrams of one another.

Follow up questions:
- Do we need to worry about case sensitivity? Yes. the algorithm should print false when the inputs differ by case.
- Is the word the anagram of itself?
- Do we assume String input? Are all the letters from the English alphabet? Could they have something other than letters, e.g. intergers?


Method 1: find out all anagrams and check if the second input is one of them. 

Reference: https://web.stanford.edu/class/cs9/sample_probs/Anagrams.pdf

 */

import puzzles.Permutation;
import java.util.* ;

public class Anagrams{

	public static boolean isAnagram(String inputOne, String inputTwo){

		// return bruteForce(inputOne, inputTwo);
		return sort(inputOne).equals(sort(inputTwo));
	}


	private static String sort(String input){
		char[] toSort = input.toCharArray();
		//System.out.println("Sorting: " + String.valueOf(toSort));
		Arrays.sort(toSort);
		return String.valueOf(toSort);

	}

	private static boolean bruteForce(String inputOne, String inputTwo){
		List<String> anagramsOfFirst = Permutation.permute(inputOne);
		for(String anagram: anagramsOfFirst){
			if(anagram.equals(inputTwo)){
				return true;
			}
		}
		return false;
	}



	

	public static void main(String args[]){
		String inputOne = "tear";
		String inputTwo = "rate";
		System.out.println("Is " + inputOne + " an anagram of " + inputTwo + "? " + isAnagram(inputOne, inputTwo));
		inputOne = "Tear";
		inputTwo = "rate";
		System.out.println("Is " + inputOne + " an anagram of " + inputTwo + "? " + isAnagram(inputOne, inputTwo));
		inputOne = "tear";
		inputTwo = "rater";
		System.out.println("Is " + inputOne + " an anagram of " + inputTwo + "? " + isAnagram(inputOne, inputTwo));

		inputOne = "tearr1";
		inputTwo = "ra1ter";
		System.out.println("Is " + inputOne + " an anagram of " + inputTwo + "? " + isAnagram(inputOne, inputTwo));

		inputOne = "tearr1";
		inputTwo = "ra1te";
		System.out.println("Is " + inputOne + " an anagram of " + inputTwo + "? " + isAnagram(inputOne, inputTwo));

		inputOne = "tear";
		inputTwo = "tear";
		System.out.println("Is " + inputOne + " an anagram of " + inputTwo + "? " + isAnagram(inputOne, inputTwo));

		inputOne = "";
		inputTwo = "tear";
		System.out.println("Is " + inputOne + " an anagram of " + inputTwo + "? " + isAnagram(inputOne, inputTwo));
		inputOne = "ban";
		inputTwo = "banana";
		System.out.println("Is " + inputOne + " an anagram of " + inputTwo + "? " + isAnagram(inputOne, inputTwo));

		inputOne = "are";
		inputTwo = "area";
		System.out.println("Is " + inputOne + " an anagram of " + inputTwo + "? " + isAnagram(inputOne, inputTwo));
	}
}