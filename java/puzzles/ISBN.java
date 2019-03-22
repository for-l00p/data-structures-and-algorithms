package puzzles;

import java.util.Arrays;

public class ISBN {
	

	public static boolean isValid13 (String input){
		input = clean(input);
		if (input.length() != 13) return false;
		int[] digits = getDigits(input);
		int sumOfProducts = calculateProductWithPositionsFor13(digits);
		sumOfProducts = sumOfProducts % 10;
		sumOfProducts = 10 - sumOfProducts;
		sumOfProducts = sumOfProducts % 10;
		

		return (sumOfProducts == digits[digits.length - 1]);
	}

	private static int calculateProductWithPositionsFor13(int[] digits){
		int total = 0;
		for (int i = 1; i <= digits.length - 1; i++){
			if (i % 2 == 1){
				total = total + digits[i-1];
			} else {
				total = total + 3*digits[i-1];
			}
			
		}
		return total;
	}


	public static boolean isValid10 (String input){
		input = clean(input);
		if (input.length() != 10) return false;
		int[] digits = getDigits(input);
		int sumOfProducts = calculateProductWithPositionsFor10(digits);
		sumOfProducts = sumOfProducts % 11;
		
		return (sumOfProducts  == digits[digits.length - 1]);
	}

	private static int calculateProductWithPositionsFor10(int[] digits){
		int total = 0;
		for (int i = 1; i <= digits.length - 1; i++){
			total = total + i*digits[i-1];
		}
		return total;
	}




	private static String clean(String input){
		String output = input.replaceAll("-|\\s", "");
		return output;
	}

	private static int[] getDigits(String input){
		
		
		int[] result = new int[input.length()];
		for(int i = 0; i < input.length(); i++){
			char c = input.charAt(i);
			if(c == 'X'){
				result[i] = 10;
			} else {
				result[i] = Character.getNumericValue(c);
			}
			
			
		}
		
		return result;
	}


	public static void main(String[] args){
		// int i = Integer.parseInt("0");
		// System.out.println(i);
		String input = "12345678Z";
		//System.out.println(clean(input));
		System.out.println(Arrays.toString(getDigits(clean(input))));
	}




}