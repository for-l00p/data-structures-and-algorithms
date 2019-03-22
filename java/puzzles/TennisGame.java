package puzzles;

import java.util.Map;
import java.util.HashMap;
import java.util.Random;

public class TennisGame {
	
	private final String playerOne;
	private final String playerTwo;
	private boolean isGameOver;
	
	private String readableScore;

	private int[] scores;
	private static Map<Integer, String> scoreStrings;

	static final String ZERO = "love";
	static final String ONE = "fifteen";
	static final String TWO = "thirty";
	static final String THREE = "forty";
	static final String DEUCE = "deuce";
	static final String ADVANTAGE = "advantage";

	public TennisGame (String playerOne, String playerTwo){

		this.scores = new int[2];
		this.playerOne = playerOne;
		this.playerTwo = playerTwo;
		this.isGameOver = false;
		scoreStrings = new HashMap<>();
		scoreStrings.put(0, ZERO);
		scoreStrings.put(1, ONE);
		scoreStrings.put(2, TWO);
		scoreStrings.put(3, THREE);

	}

	public String printScore(){
		return this.readableScore;
	}


	public void score (String playerName){
		if (!this.isGameOver){

			if (playerName == playerOne){
			 	scores[0]++;
			}

			if (playerName == playerTwo){
				scores[1]++;
			} 
			//System.out.println("Point " + playerName);
			calculate();
		} else {
			System.out.println("Game Already Over. Score " + this.readableScore);
		}
		
	

	}

	private void calculate(){
		int playerOneScore = scores[0];
		int playerTwoScore = scores[1];
		
		if (playerOneScore == playerTwoScore && playerOneScore >=3){
			this.readableScore = stringDeuce();
			return;
		}

		if (playerOneScore == playerTwoScore){
			this.readableScore = stringBothScores();
			return;
		}

	  	String winningPlayer; int winningScore, losingScore;

	  	if (playerOneScore > playerTwoScore){
	  		winningPlayer = playerOne;
	  		winningScore = playerOneScore;
	  		losingScore = playerTwoScore;
	  	} else {
	  		winningPlayer = playerTwo;
	  		winningScore = playerTwoScore;
	  		losingScore = playerOneScore;
	  	}

	  	if (winningScore > 4 && winningScore - losingScore > 2) throw new AssertionError("Game scores should have been unreachable");
	  	if (losingScore > winningScore) throw new AssertionError("Losing score cannot be greater than winningScore");

		if (winningScore > 4 && winningScore - losingScore == 2){
			this.isGameOver = true;
			this.readableScore = stringWin(winningPlayer);
		}

		if (winningScore > 4 && winningScore - losingScore == 1){
			this.isGameOver = false;
			this.readableScore = stringAdvantage(winningPlayer);
		}

		if (winningScore == 4 && winningScore - losingScore >= 2){
			this.isGameOver = true;
			this.readableScore = stringWin(winningPlayer);
		}

		if (winningScore == 4 && winningScore - losingScore == 1){
			this.isGameOver = false;
			this.readableScore = stringAdvantage(winningPlayer);
		}

		if (winningScore < 4){
			this.isGameOver = false;
			this.readableScore = stringBothScores();
		}

	}

	private String getScoreString(int score){
		return scoreStrings.get(score);

	}

	private String stringWin(String playerName){
		 return playerName + " WON";
	}

	private String stringDeuce(){
		return DEUCE;
	}

	private String stringAdvantage(String playerName){
		return playerName + " " + ADVANTAGE;
	}

	public String stringBothScores(){
		
		return (playerOne + " " + getScoreString(scores[0])  + "-" + getScoreString(scores[1]) + " " + playerTwo);

	}

	public static int giveRandomNextWinnerId(){
		return new Random().nextInt(2);
	}

	public static void runRandom(String playerOne, String playerTwo){

		TennisGame game = new TennisGame(playerOne, playerTwo);
		do {
			
			String nextPointWinner;
			if (giveRandomNextWinnerId() == 0){
				nextPointWinner = playerOne;
			} else {
				nextPointWinner = playerTwo;
			}
			game.score(nextPointWinner);
			System.out.println(game.printScore());

		} while (!game.isGameOver);

	}

	public static void main(String[] args){

		runRandom("Federer", "Nadal");
	}


	
}