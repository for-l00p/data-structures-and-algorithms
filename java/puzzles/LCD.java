package puzzles;


public class LCD {
	
	public static void print(int n){

		String[] lines = new String[3];
		int[] input = getDigits(n);

		lines[0] = formTop(input);
		lines[1] = formMiddle(input);
		lines[2] = formBottom(input);

		for(String line: lines){
			System.out.println(line);
		}
	}

	private static int[] getDigits(int n){

		int[] result = new int[Integer.toString(n).length()];
		int digit;
		int i = result.length-1;

		while (n > 0){
			digit = n%10;
			n = n/10;
			result[i] = digit; 
			i--;
		}
		
		return result;

	}

	private static String formTop(int[] input){

		StringBuilder s = new StringBuilder();
		for(int n: input){	
			char c = (char) (n+48);
			s.append(" ");
			if("02356789".indexOf(c) > -1){
				s.append("_");
			} else {
				s.append(" ");
			}
			s.append(" ");
			s.append("   ");

		}	
	
		return String.valueOf(s);
		
		

	}

	private static String formMiddle(int[] input){

		StringBuilder s = new StringBuilder();

		for(int n: input){
			char c = (char) (n+48);

			if("045689".indexOf(c) > -1){
				s.append("|");
			} else {
				s.append(" ");
			}

			if("2345689".indexOf(c) > -1){
				s.append("_");
			} else {
				s.append(" ");
			}

			if("01234789".indexOf(c) > -1){
				s.append("|");
			} else {
				s.append(" ");
			}
			s.append("   ");
			
		}
			
			return String.valueOf(s);
		
	}

	private static String formBottom(int[] input){

		StringBuilder s = new StringBuilder();
		for(int n: input){
			char c = (char) (n+48);

			if("0268".indexOf(c) > -1){
				s.append("|");
			} else {
				s.append(" ");
			}

			if("023568".indexOf(c) > -1){
				s.append("_");
			} else {
				s.append(" ");
			}

			if("013456789".indexOf(c) > -1){
				s.append("|");
			} else {
				s.append(" ");
			}
			s.append("   ");
		}
	
		return String.valueOf(s);
		
	}








	
	public static void main(String[] args){
		
		print(910);
	}

}