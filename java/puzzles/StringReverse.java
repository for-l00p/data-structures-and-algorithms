



public class StringReverse{
	
	public static String reverseString(String input){
		char[] x = input.toCharArray();
		int i = 0, j = x.length - 1;
		while( i < j){
			x = swap(x, i, j);
			i++;
			j--;
		}

		return String.valueOf(x);

	}

	private static char[] swap (char[] x, int i, int j){
		char temp = x[i];
		x[i] = x[j];
		x[j] = temp;
		return x;
	}


	public static String reverseSentence(String sentence){

		String[] words = sentence.split(" ");
		int i = 0, j = words.length - 1;
		while( i < j){
			words = swap(words, i, j);
			i++;
			j--;
		}
		StringBuilder result = new StringBuilder();
		for(String word: words){
			result = result.append(" ").append(word);
		}
		result.deleteCharAt(0);

		return String.valueOf(result);


	}

	private static String[] swap (String[] x, int i, int j){
		String temp = x[i];
		x[i] = x[j];
		x[j] = temp;
		return x;
	}



	public static void main(String[] args){

		String input;

		input = "abc";
		System.out.println("Reverse of " + input + " is " + reverseString(input));


		input = "abca";
		System.out.println("Reverse of " + input + " is " + reverseString(input));

		input = "aba";
		System.out.println("Reverse of " + input + " is " + reverseString(input));

		input = "";
		System.out.println("Reverse of " + input + " is " + reverseString(input));

		input = "Aba";
		System.out.println("Reverse of " + input + " is " + reverseString(input));

		input = "hello world";
		System.out.println("Reverse of " + input + " is " + reverseSentence(input));


	}



}