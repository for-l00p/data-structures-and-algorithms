

public class Solution {

	static final String BIGALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	static final String SMALLALPHABET = "abcdefghijklmnopqrstuvwxyz";

	public static String encode(String message, int rotations){

		int n = message.length();
		StringBuilder s = new StringBuilder();

		for (int i = 0; i < n; i++){
			char currentChar = message.charAt(i);
			int index = BIGALPHABET.indexOf(currentChar);
			if (index > 0){
				int newCharIndex = (index + rotations) % 26;
				char newChar = BIGALPHABET.charAt(newCharIndex);
				s.append(newChar);

			} else {
				index = SMALLALPHABET.indexOf(currentChar);
				if(index > 0){
					int newCharIndex = (index + rotations) % 26;
					char newChar =  SMALLALPHABET.charAt(newCharIndex);
					s.append(newChar);
				} else {
					s.append(currentChar);
				}
			} 

		}

		return s.toString();


	}


	public static String decode(String message, int rotations){

		int n = message.length();
		StringBuilder s = new StringBuilder();

		for (int i = 0; i < n; i++){
			char currentChar = message.charAt(i);
			int index = BIGALPHABET.indexOf(currentChar);
			if (index > 0){
				int newCharIndex = (index - rotations) % 26;
				if (newCharIndex < 0){
					newCharIndex = newCharIndex + 26;
				}
				char newChar = BIGALPHABET.charAt(newCharIndex);
				s.append(newChar);

			} else if (index < 0){
				index = SMALLALPHABET.indexOf(currentChar);
				if(index > 0){
					int newCharIndex = (index - rotations) % 26;
					if (newCharIndex < 0){
						newCharIndex = newCharIndex + 26;
					}
					char newChar =  SMALLALPHABET.charAt(newCharIndex);
					s.append(newChar);
				} else {
					s.append(currentChar);
				}
			} 

		}

		return s.toString();


	}





	public static void main(String[] args){
;
		System.out.println("Encoding for HELLO " + encode("HELLO", 5));
		 System.out.println("Decoding for mjqqt " + decode("mjqqt", 5));

		System.out.println("Encoding for innoWake rules " + encode("innoWake rules", 9));
		System.out.println("Decoding for IUHUR " + decode("IUHUR", 6));

		

	}



}