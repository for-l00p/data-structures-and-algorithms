package puzzles;



public class GrayCode {
	


	private static String getGray(int n){
	
		n = n^(n>>1);
		return Integer.toString(n,2);
	}


	/**
	 
	The following encodes what is called "binary reflected Gray code."
	Encoding (MSB is bit 0, b is binary, g is Gray code):
	 if b[i-1] = 1
     	g[i] = not b[i]
 	 else
     	g[i] = b[i]

 	Decoding (MSB is bit 0, b is binary, g is Gray code):
  		b[0] = g[0]

	for other bits:
		 b[i] = g[i] xor b[i-1]

	 */

	public static String getGray2(int n){
		String binary = getBinary(n);
		StringBuilder grayCode = new StringBuilder();
		getGrayFromBinary(binary, binary.length()-1, grayCode);
		return String.valueOf(grayCode);
	}


	// Equivalent to takinx XOrING with >> encodes the positions where bits change from 1 to 0 or from 0 to 1.  If the bits change in the original number from 1 to 0, then put 1. If they do not, then put change from 1 to 0, put 0. If they change from 0 to 1, then 

	private static void getGrayFromBinary(String binary, int i , StringBuilder grayCode){
		int n = binary.length();
		if (n == 0) throw new AssertionError("Input should not be empty");
		
		if (i == 0){
			grayCode.append(binary.charAt(i));
			return;
		} else {
			getGrayFromBinary (binary, i-1, grayCode);
			char c = binary.charAt(i-1);
			if (c == '1'){
				grayCode.append(not(binary.charAt(i)));
			} else {
				grayCode.append(binary.charAt(i));
			}
		}		
	}

	


	private static char xor (char i, char j){
		int a = Character.getNumericValue(i) ^ Character.getNumericValue(j);
		return Character.forDigit(a, 10);
	}


	private static char not(char i){
		if (i == '0') return '1';
		return '0';
	}

	public static String getBinary(int n){
		StringBuilder s = new StringBuilder();
		convertToBinary(n, s);
		return String.valueOf(s);
	}

	public static String getBinary2(int n){
		StringBuilder binary = new StringBuilder();
		String gray = getGray(n);
		binary.append(gray.charAt(0));
		return getBinaryFromGray(gray, 1,binary);
	
	}

	// Tail recursion
	private static String getBinaryFromGray(String gray, int i, StringBuilder binary){
		if (i == gray.length()) {
			return String.valueOf(binary);
		} else {
			binary.append(xor(binary.charAt(i-1),gray.charAt(i)));
			return getBinaryFromGray(gray, i+1, binary);		
		}
	}


	private static void convertToBinary (int n, StringBuilder s){
		if ( n == 0 || n == 1){
			s.append(n);
			return;
		}
		
		int lsb = n % 2;
		convertToBinary(n/2, s);
		s.append(lsb);	
	}

	public static int convertGrayToArabic(String gray){
		return 0;
	}

	public static void displayForNumBits(int n){

		for (int i = 0; i < Math.pow(2, n); i++){
			System.out.println("Number: " + i);
			System.out.println("Binary: " + getBinary(i));
			System.out.println("Binary 2: " + getBinary2(i));
			System.out.println("Gray Code: " + getGray(i));
			System.out.println("Gray Code 2: " + getGray2(i));
			System.out.println();
			
		}
	}



	public static void printGray(int n){
		printGray(n, "", 0);

	}

	private static void printGray(int n, String partial, int index){
		if (index == n+1){
			System.out.println(partial);
		} else {
			printGray(n, partial + "0", index + 1);
			printGray(n, partial + "1", index + 1);
		}
	}

	public static void main(String[] args){
		displayForNumBits(5);
		printGray(5);
	}

}