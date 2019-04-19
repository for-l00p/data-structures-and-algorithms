package puzzles;

import java.util.Deque;
import java.util.ArrayDeque;


/**
 * Observations For Approach 1: If you push opening braces on as stack, then when every time youu encounter a closing brace, the topmost disk should be its corresponding opening brace.
 * O(n) space. O(n) running time

 * Observation for Approach 2: For a valid parenthesis, there exists atleast one brace pair with no braces in between. And this must correspond to the rightmost opening brace.
 * O(n^2) running time. O(1) space if original string not need, O(n) otherwise for copying the string.
 */
public class Parenthesis {
	


	public static boolean isValid2(String input){

		Deque<Character> stack = new ArrayDeque<>();
		for (int i = 0; i < input.length(); i++){
			char currentChar = input.charAt(i);
			if (isOpening(currentChar)){
				stack.push(currentChar);
			} else {
				if(!stack.peek().equals(opening(currentChar))){
					return false;
				}
				stack.pop(); 
			}
		}
		return stack.isEmpty();

	}

	public static boolean isValid(String input){

		StringBuilder current = new StringBuilder(input);
		int n = input.length();
		boolean sizeDecreased = true;
		while(current.length() > 1 && sizeDecreased){
			sizeDecreased = false;
			for (int i = 0; i <= current.length() - 2; i++){		
				char c =  current.charAt(i);
				char next = current.charAt(i+1);
				if (isOpening(c) && closing(c) == next){
					current = current.delete(i, i+2);
					sizeDecreased = true;
				}
			}
		}
		
		return current.length() == 0;

	}





	public static boolean isValidRecursive(String input){

		return isValid(input, 0, input.length() -1);

	}

	private static boolean isValid(String input, int start, int end){

		if (start == end) return true;

		for (int i = start; i < end; i++){
			char currentChar = input.charAt(i);
			if (isOpening(currentChar)){
				int currentCharEndIndex = findParenthesisEnd(input, currentChar, i, end);
				if (currentCharEndIndex == -1){
					return false;
				}

				if (!isValid(input, i+1, currentCharEndIndex)){
					return false;
				}
			} 	
		}
		return true;

	}

	private static int findParenthesisEnd(String input, char opening, int start, int end){
		
		if (input.charAt(start) != opening) throw new AssertionError("Character at start does not correspond to braces being searched");

		int count = 1;
		int currentIndex = start;
		while(count != 0 && currentIndex != end){
			currentIndex++;
			if (input.charAt(currentIndex) == opening){
				count++;
			}

			if (input.charAt(currentIndex) == closing(opening)){
				count--;
			}
		}

		if (count == 0){
			return currentIndex;
		} else {
			return -1;
		}

	}

	private static boolean isOpening(char c){

		if (c == '{' || c == '[' || c== '(') return true;
		return false;

	}

	private static char opening(char c){

		char opening;

		if (c == '}'){
			opening =  '{';
			return opening;
		}

		if (c == ']'){
			opening = '[';
			return opening;
		}

		if (c == ')' ){
			opening = '(';
			return opening;
		}

		throw new AssertionError("called closing on non-braces");

	}


	private static char closing(char opening){

		char closing;

		if (opening == '{'){
			closing =  '}';
			return closing;
		}

		if (opening == '['){
			closing = ']';
			return closing;
		}

		if (opening == '(' ){
			closing = ')';
			return closing;
		}

		throw new AssertionError("called closing on non-braces");




	}




}