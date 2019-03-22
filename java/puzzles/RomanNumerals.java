package puzzles;


public class RomanNumerals {
	 

	public static String convertToRoman(int n){

		String number = String.valueOf(n);
		StringBuilder result = new StringBuilder();
		convertToRoman(number, result, 0);
		return String.valueOf(result);
	}

	private static void convertToRoman(String number, StringBuilder result, int index){
		
		if(index == number.length()){
			return;
		} else {
			char c = number.charAt(index);
			int digit = Character.getNumericValue(c);
		
			digit =  digit* (int)(Math.pow(10, number.length() - index - 1));
			String roman = getRomanForNumber(digit);
			result.append(roman);
			convertToRoman(number, result, index + 1);
		}
	}

	private static String getRomanForNumber(int number){

		StringBuilder sb = new StringBuilder();
		int maxToBeSubtracted;
		while(number > 0){
			maxToBeSubtracted = findMaxRomanBase(number);
			while(number >= maxToBeSubtracted){
				maxToBeSubtracted = findMaxRomanBase(number);
				number = number - maxToBeSubtracted;
				sb.append(getRomanDigit(maxToBeSubtracted));
			}
			
		}
		return String.valueOf(sb);
	}

	private static int findMaxRomanBase(int number){

		if(number <= 0 || number >= 5000) throw new IllegalArgumentException("number: " + number );

		if (number == 4 || number == 9) return -1;
		if (number == 40|| number == 90) return -10;
		if (number == 400 || number == 900) return -100;

		if (number < 5) return 1;
		if (number < 10 ) return 5;
		if (number < 50) return 10;
		if (number < 100) return 50;
		if (number < 500) return 100;
		if (number < 1000) return 500;
		return 1000;
	}

	private static String getRomanDigit(int numeral){
		
		if (numeral == 1 || numeral == -1) return "I";
		if (numeral == 5) return "V";
		if (numeral == 10 || numeral == -10) return "X";
		if (numeral == 50) return "L";
		if (numeral == 100 || numeral == -100) return "C";
		if (numeral == 500) return "D";
		
		if (numeral != 1000) throw new IllegalArgumentException();
		return "M";

	}

	private static int getNumberForRoman(char roman){

		if (roman == 'M') return 1000;
		if (roman == 'D') return 500;
		if (roman == 'C') return 100;
		if (roman == 'L') return 50;
		if (roman == 'X') return 10;
		if (roman == 'V') return 5;
		if (roman == 'I') return 1;
		//throw new AssertionError("Should not reach here");
		return 0;
	}

	public static int convertToDecimal(String roman){

		int currentLargestBase = 0;
		int currentBase = 1;
		int total = 0;

		for(int i = roman.length() - 1; i >= 0; i--){
			char c = roman.charAt(i);
			currentBase = getNumberForRoman(c);
			if(currentBase < currentLargestBase){
				total = total - currentBase;
			} else {
				total = total + currentBase;
				currentLargestBase = currentBase;
			};
		}
		return total;

	}

	public static void main(String[] args){
		System.out.println(convertToRoman(2008));

	}





		
			
			
			
			
			



}