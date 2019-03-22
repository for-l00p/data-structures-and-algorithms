package puzzles;


import java.util.regex.*;

public class Unsplice {
	

	public static String answer(String input){

		return input.replaceAll("(\\\\)+(\\n)","");

	}


	public static void main(String[] args){

		String input = "abc\\\nbc";
		System.out.println(input);
		System.out.println(answer(input));



	}
}