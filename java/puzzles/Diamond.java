package puzzles;

public class Diamond {
	
	private static final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

	private String[] lines;
	private int numLines;


	public Diamond(char c){
		this('A',c);
		// int charIndex = (int) c - 'A';
		// numLines = 2*charIndex + 1;
		// lines = new String[numLines];
		// makeStrings2(c);
	}


	public Diamond(char start, char end){
		int startCharIndex = (int) start - 'A';
		int endCharIndex = (int) end - 'A';
		numLines = 2*(endCharIndex - startCharIndex) + 1;
		lines = new String[numLines];
		makeStrings(end, lines);
	}




	public void print(){
		for (String line: lines){
			System.out.println(line);
		}
	}
	
	private static char charValueOf(int n){
		return ALPHABET.charAt(n);
	}


	public String[] getStrings(){
		return lines;

	}

	private String makeStringLine(char c, int leftMargin, int marginFromMiddle){
		//System.out.println("Called with: " + c + ", " + leftMargin + ", " + marginFromMiddle);
		String leftSide, centre, rightSide, line;
		line = "";

		if (marginFromMiddle == 0 && leftMargin == 0){
			 line = String.valueOf(c);
			 return line;
		}
		if (marginFromMiddle == 0 && leftMargin !=0){
			leftSide = String.format("%" + (leftMargin)+"s", "");
			centre = String.valueOf(c);
			rightSide = String.valueOf(new StringBuilder(leftSide).reverse());
			line = leftSide + centre + rightSide;
				// System.out.println("leftSide: " + leftSide);
				// System.out.println("centre: " + centre);
				// System.out.println("rightSide: " + rightSide);
			return line;

		}

		if (leftMargin == 0 && marginFromMiddle !=0){
			leftSide = String.format("%s", c) + String.format("%" + (marginFromMiddle)+"s", "");
			centre = "";
			rightSide = String.valueOf(new StringBuilder(leftSide).reverse());
			line = leftSide + centre + rightSide;

		}	

		//marginFromMiddle != 0
		if (leftMargin != 0 && marginFromMiddle != 0){
			leftSide = String.format("%" + (leftMargin)+"s", c) + String.format("%" + (marginFromMiddle)+"s", "");
			centre = "";
			rightSide = String.valueOf(new StringBuilder(leftSide).reverse());
			line = leftSide + centre + rightSide;
			// 	System.out.println("leftSide: " + leftSide);
			// System.out.println("centre: " + centre);
			// System.out.println("rightSide: " + rightSide);
			
		}
		return line;
	}

	// make lines with given character at the middle of a given array
	private void makeStrings(char middleLineChar, String[] lines){

		int n = lines.length;
		int currentCharIndex = (int) (middleLineChar - 'A');
		char currentChar;
		int middleIndex = n/2;
		int marginFromLeft, marginFromMiddle;	
		for (int currentLineIndex = middleIndex; currentLineIndex >=0; currentLineIndex--){
			marginFromLeft = middleIndex - currentLineIndex;
			marginFromMiddle = currentLineIndex;
			currentChar = charValueOf(currentCharIndex);
			String line = makeStringLine(currentChar, marginFromLeft, marginFromMiddle);
			lines[currentLineIndex] = line;
			lines[2*middleIndex - currentLineIndex] = line;			
			currentCharIndex--;
		}	
	}


	private void makeStrings2(char c) {
		int charIndex = (int) (c - 'A');
		System.out.println(charIndex);
		if (c != charValueOf(charIndex)) throw new AssertionError("Character not present in Alphabet");

		//Make first String
		int lineNumber = 1;
		int beforeSpace = charIndex + 1;
		int middleSpace = 0;
		int currentCharIndex = lineNumber-1;
		String line = String.format("%" + (beforeSpace)+"s", charValueOf(currentCharIndex)) + String.format("%" + (beforeSpace)+"s", "");
		lines[lineNumber-1] = line;
		

		while (lineNumber < charIndex){
			lineNumber++;
			currentCharIndex++;
			beforeSpace--;
			middleSpace = middleSpace + 2;
			line = String.format("%" + beforeSpace + "s", charValueOf(currentCharIndex)) + String.format("%" + middleSpace + "s", charValueOf(currentCharIndex)) + String.format("%" + beforeSpace + "s", "");
			lines[lineNumber-1] = line;
		}

		lineNumber++;
		currentCharIndex++;
		middleSpace = middleSpace + 2;
		beforeSpace--;
		line = String.format("%s", charValueOf(currentCharIndex)) + String.format("%" + middleSpace + "s", charValueOf(currentCharIndex));
		lines[lineNumber-1] = line;

		while(lineNumber < 2*(charIndex +1)-1-1){
			lineNumber++;
			beforeSpace++;
			currentCharIndex--;
			middleSpace = middleSpace - 2;
			line = String.format("%" + beforeSpace + "s", charValueOf(currentCharIndex)) + String.format("%" + middleSpace + "s", charValueOf(currentCharIndex)) + String.format("%" + beforeSpace + "s", "");
			lines[lineNumber-1] = line;
		}

		lineNumber++;
		currentCharIndex--;
		beforeSpace++;
		line = String.format("%" + (beforeSpace)+"s", charValueOf(currentCharIndex)) + String.format("%" + (beforeSpace)+"s", "");
		lines[lineNumber-1] = line;
	}
	



	public static void main(String[] args) {
		Diamond test = new Diamond('D');
		test.print();
	}


}