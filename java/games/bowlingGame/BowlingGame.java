class BowlingGame {
	
	int totalScore = 0;
	
	private BowlingGame(){	
	}

	public static int calculateScore(String input){
		Frame[] frames = parse(input);
		return calculateScore(frames);
	}

	private static Frame[] parse (String input){
		String[] frameTokens = input.split("(\\|)+");
		int n = frameTokens.length;
		Frame[] frames = new Frame[n];
		if (n < 10) throw new Error("input not parsed to 10 frames");
		for (int i = 0; i < 10; i++ ){
			Frame frame  = new Frame(frameTokens[i].toCharArray(), false);
			frames[i] = frame;
		}
		if (n == 11){
			Frame frame = new Frame(frameTokens[10].toCharArray(), true);
			frames[10] = frame;
		}

		return frames;

	}



	private static int calculateScore (Frame[] frames){
		
		int totalScore = 0;
		for (int i = 0; i < 10; i++){
		 	Frame frame = frames[i];
		 	totalScore =  totalScore + frame.score;
		 	if (frame.isStrike()){
		 		Frame nextFrame = frames[i+1];
		 		if (!nextFrame.isStrike()){
		 			toAdd = toAdd + nextFrame.score;
		 		} else {
		 			toAdd = toAdd + nextFrame.getFirstRollValue();
		 			Frame nextNextFrame = frames[i+2];
		 			toAdd = toAdd + nextNextFrame.getFirstRollValue();
		 		}
		 	}

		 	if (frame.isSpare()){
		 		Frame nextFrame = frames[i+1];
		 		toAdd = toAdd + nextFrame.getFirstRollValue();
		 	}

		 	totalScore = totalScore + toAdd;
		}
		return totalScore;

	}

	//Unit testing 

	public static void main(String[] args){

		String[] scores = {
			"X|X|X|X|X|X|X|X|X|X||XX",
			"9-|9-|9-|9-|9-|9-|9-|9-|9-|9-||",
			"5/|5/|5/|5/|5/|5/|5/|5/|5/|5/||5",
			"X|7/|9-|X|-8|8/|-6|X|X|X||81",
			"X|X|X|X|X|X|X|X|X|X||--",
			"-1|-1|-1|-1|-1|-1|-1|-1|-1|-1||",
			"11|11|11|11|11|11|11|11|11|11||",
			"--|--|--|--|--|--|--|--|--|--||"
		};

		for(String score: scores){
				System.out.println("Input: " + score + " Score: " + BowlingGame.calculateScore(score));
		}

	}
	

	


}



