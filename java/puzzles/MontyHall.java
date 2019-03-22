package puzzles;


import java.util.Random;


public class MontyHall {
	
	private int[] doors;
	private int indexOfPrize;
	private int wins;


	public MontyHall(int n){
		initDoors(n);
	}

	public void initDoors(int n){
		doors  = new int[n];
		Random ran = new Random();
		indexOfPrize = ran.nextInt(n);
		doors[indexOfPrize] = 1;
		for (int i = 1; i < n; i++){
			doors[(indexOfPrize + i) % n]  = 0;	
		}
		
	}


	private void openDoor(int chosenDoor){

		int n = doors.length;
		if(chosenDoor == indexOfPrize){
			Random ran = new Random();
			int offset = ran.nextInt(n-1) + 1;
			doors[(chosenDoor + offset) % n] = -1;
		} else {
			for (int offset = 1; offset < n; offset++){
				if(doors[(chosenDoor + offset)%n] == 0){
					doors[(chosenDoor + offset) % n] = -1;
				}
			}
		}
	}

	private int findNonChosenNotOpenDoor(int chosenDoor){
		for (int i = 0; i < doors.length; i++){
			if(doors[i] != -1 && i != chosenDoor){
				return i;
			}
		}
		return -1;
	}





	private static boolean playOneRound(int doors, String strategy){
		MontyHall game = new MontyHall(doors);
		Random ran = new Random();
		int chosenDoor = ran.nextInt(game.doors.length);
		game.openDoor(chosenDoor);
		if(strategy == "stay"){
			return (game.doors[chosenDoor] == 1);
		} 

		int doorToSwitch = game.findNonChosenNotOpenDoor(chosenDoor);
		return (game.doors[doorToSwitch] == 1);
		

		
		
		

	}

	private static void runSimulation(int numTimes, String strategy){

		int total = 0;
		boolean didIWin = false;
		for (int i = 0; i < numTimes; i++){
			if(strategy != "mix"){
				didIWin = playOneRound(3, strategy);
			} else {
				if(i % 2 == 0){
					didIWin = playOneRound(3, "stay");
				} else {
					didIWin = playOneRound(3, "switch");
				}
			}
			if (didIWin) total++;
			
		}

		double result = total*100/numTimes;
		System.out.println("Winning percentage with strategy: " + strategy  + " is " + result);
	}



	public static void main(String[] args){

		runSimulation(1000, "stay");
		runSimulation(1000, "switch");
		runSimulation(1000, "mix");

	}





}