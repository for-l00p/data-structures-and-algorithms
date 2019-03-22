package puzzles;


import java.util.Map;
import java.util.HashMap;

public class EnglishNumbers {
	

	static Map<Integer, String> digitWords= new  HashMap<>();
	static Map<Integer, String> tensPlacePrefix = new  HashMap<>();

	static {
		digitWords.put(1, "one");
		digitWords.put(2, "two");
		digitWords.put(3, "three");
		digitWords.put(4, "four");
		digitWords.put(5, "five");
		digitWords.put(6, "six");
		digitWords.put(7, "seven");
		digitWords.put(8, "eight");
		digitWords.put(9, "nine");

	}


	static {
		tensPlacePrefix.put(2, "twen");
		tensPlacePrefix.put(3, "thir");
		tensPlacePrefix.put(4, "for");
		tensPlacePrefix.put(5, "fif");
		tensPlacePrefix.put(6, "six");
		tensPlacePrefix.put(7, "seven");
		tensPlacePrefix.put(8, "eigh");
		tensPlacePrefix.put(9, "nine");
	}

	
	public static String spell(int n){
		return (spellRecursive(n, 0));
	}

	private static String spellRecursive(int n, int depth){

		int nextThousandLevel = n/1000;
		int thisLevel = n%1000;
		StringBuilder s = new StringBuilder();
		if (nextThousandLevel != 0){
			String fromNextLevel = spellRecursive(nextThousandLevel, depth + 1);
			s.append(fromNextLevel);
			//Comma is not needed in cases lke 10000;
			if (thisLevel != 0){
				s.append(",");
				s.append(" ");
			}
			
		}

		// If number does not have next level, then space is redudant
		if (thisLevel != 0){
			s.append(spellLessThanThree(thisLevel));
			s.append(getRadixString(depth));	
		}
		return String.valueOf(s);
		

	}



	private static String spellLessThanThree(int n){
		StringBuilder s = new StringBuilder();
		int numHundreds = n/100;
		int remainderWithHundred = n % 100;
		if (numHundreds != 0){
			s.append(spellSingle(numHundreds));
			s.append(" ");
			s.append("hundred");
			
			if(remainderWithHundred != 0){
				s.append(" ");
				s.append("and");
				s.append(" ");//s.append(getRadix(1));
			}
		} 

		if(remainderWithHundred != 0){
			s.append(spellDoubleDigit(remainderWithHundred));
		}
		return String.valueOf(s);
	}

	private static String spellDoubleDigit(int n){
		int tensPlace = n/10;
		int onesPlace = n%10;
		//System.out.println("spellDoubleDigit: " + n);
		StringBuilder s = new StringBuilder();


		if(tensPlace > 1){
			//System.out.println("tensPlace: " + tensPlace);
			s.append(tensPlacePrefix.get(tensPlace));
			s.append("ty");
			s.append(" ");
		} else if (tensPlace == 1){

			switch(onesPlace){
				case 0: 
					s.append("ten");
					break;
				case 1:
					s.append("eleven");
					break;
				case 2: 
					s.append("twelve");
					break;
				default:
					s.append(tensPlacePrefix.get(onesPlace));
					s.append("teen");
			}
			onesPlace = 0;
		}



		if (onesPlace > 0){
			//System.out.println("onesPlace: " + onesPlace);
			//
			s.append(spellSingle(onesPlace));
		}
		//System.out.println("S: " + s);
		return String.valueOf(s);
	}

	private static String spellSingle(int n){
		return digitWords.get(n);
	}

	private static String getRadixString(int level){
		if(level == 0) return "";
		if(level == 1) return " thousand";
		if(level == 2) return " million";
		if(level == 3) return " billion";
		return "";
	}


	public  static String spell3(int n){
		 String s = spell(n);
		 s = s.replace(",", "");
		 return s; 
	}


	static Map<Integer, String> digitWords2 = new  HashMap<>();
	static Map<Integer, String> tensPlacePrefix2 = new  HashMap<>();

	static {
		digitWords2.put(1, "One");
		digitWords2.put(2, "Two");
		digitWords2.put(3, "Three");
		digitWords2.put(4, "Four");
		digitWords2.put(5, "Five");
		digitWords2.put(6, "Six");
		digitWords2.put(7, "Seven");
		digitWords2.put(8, "Eight");
		digitWords2.put(9, "Nine");

	}


	static {
		tensPlacePrefix2.put(2, "Twen");
		tensPlacePrefix2.put(3, "Thir");
		tensPlacePrefix2.put(4, "For");
		tensPlacePrefix2.put(5, "Fif");
		tensPlacePrefix2.put(6, "Six");
		tensPlacePrefix2.put(7, "Seven");
		tensPlacePrefix2.put(8, "Eigh");
		tensPlacePrefix2.put(9, "Nine");
	}

	
	public static String spell2(int n){
		return (spellRecursive2(n, 0));
	}

	private static String spellRecursive2(int n, int depth){

		int nextThousandLevel = n/1000;
		int thisLevel = n%1000;
		StringBuilder s = new StringBuilder();

		if (nextThousandLevel != 0 && thisLevel != 0){
			String fromNextLevel = spellRecursive2(nextThousandLevel, depth + 1);
			s.append(fromNextLevel);
			s.append(" ");
			s.append(spellLessThanThree2(thisLevel));
			s.append(getRadixString2(depth));	
			//Comma is not needed in cases lke 10000;		
			
		}

		if (nextThousandLevel != 0 && thisLevel == 0){
			String fromNextLevel = spellRecursive2(nextThousandLevel, depth + 1);
			s.append(fromNextLevel);
		}

		if (nextThousandLevel == 0 && thisLevel != 0){
			s.append(spellLessThanThree2(thisLevel));
			s.append(getRadixString2(depth));	
			
		}

		if (nextThousandLevel == 0 && thisLevel == 0){
			s.append("Zero");
		
			
		}

		
		
		// if (thisLevel == 0){
		// 	return "zero";
		// }
		return String.valueOf(s);
		

	}



	private static String spellLessThanThree2(int n){
		StringBuilder s = new StringBuilder();
		int numHundreds = n/100;
		int remainderWithHundred = n % 100;
		if (numHundreds != 0){
			s.append(spellSingle2(numHundreds));
			s.append(" ");
			s.append("Hundred");
			
			if(remainderWithHundred != 0){
				s.append(" ");
			}
		} 

		if(remainderWithHundred != 0){
			s.append(spellDoubleDigit2(remainderWithHundred));
		}
		return String.valueOf(s);
	}

	private static String spellDoubleDigit2(int n){
		int tensPlace = n/10;
		int onesPlace = n%10;
		//System.out.println("spellDoubleDigit: " + n);
		StringBuilder s = new StringBuilder();


		if(tensPlace > 1 && onesPlace == 0){
			s.append(tensPlacePrefix2.get(tensPlace));
			s.append("ty");
		} 

		if(tensPlace > 1 && onesPlace != 0){	
			s.append(tensPlacePrefix2.get(tensPlace));
			s.append("ty");
			s.append(" ");
		} 

		if (tensPlace > 1 && onesPlace > 0){	
			s.append(spellSingle2(onesPlace));
		}


		if (tensPlace == 1){

			switch(onesPlace){
				case 0: 
					s.append("Ten");
					break;
				case 1:
					s.append("Eleven");
					break;
				case 2: 
					s.append("Twelve");
					break;
				case 4: 
					s.append("Fourteen");
					break;
				default:
					s.append(tensPlacePrefix2.get(onesPlace));
					s.append("teen");
			}
		}



		if (tensPlace == 0 && onesPlace > 0){	
			s.append(spellSingle2(onesPlace));
		}

		return String.valueOf(s);
	}

	private static String spellSingle2(int n){
		return digitWords2.get(n);
	}

	private static String getRadixString2(int level){
		if(level == 0) return "";
		if(level == 1) return " Thousand";
		if(level == 2) return " Million";
		if(level == 3) return " Billion";
		return "";
	}






	
}