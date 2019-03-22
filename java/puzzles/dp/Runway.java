

package puzzles.dp;

import java.util.List;
import java.util.ArrayList;

/**
 * Reference: https://dev.to/nikolaotasevic/dynamic-programming--7-steps-to-solve-any-dp-interview-problem-3870
 * 
 *	Are we initially standing at 0 with Speed S? or are we going to jump into the array?
 *	Is our initial speed updatable before our initial jump?
 *
 * Let's say our initial speed S is updatable and our initial point is outside the array P.
 *
 * The subproblem corresponding to  (S-1, P) might be (0, P), in which case we are to return "false" as we havn't entered the array at all.
 * Where do we store the result of the overall problem? (S, -1) would throw array index out of bounds. 
 *
 * If our initial speed S is not updatable and we are standing outside the array, then our initial jump is fixed to A[S-1], and this reduces to veriying A[S-1] == true and the problem of an updatable initial S, a subarray A[S-1....n-1] with a given initial index S-1.
 *
 * If our initial speed S is not updatable and we are standing at index 0, then our initial jump is fixed to A[S], and this reduces to veriying A[S] == true and problem of an updatable initial S, a subarray A[S....n-1] with a given initial index S.
 *
 * 
 *
 * 
 */
class Runway {
	
	static List<Integer> positions = new ArrayList<>();
	static Action[][] cache;

	enum Action{
		SAME_SPEED("s"),
		DECREASE_SPEED("d"),
		INCREASE_SPEED("i"),
		GAME_OVER("o");

		private final String textRepresentation;

    	private Action(String textRepresentation) {
        	this.textRepresentation = textRepresentation;
   		 }

   		@Override 
   		public String toString() {
         	return textRepresentation;
    	}
	}


	public static void printPath(boolean[] track, int S){
		boolean canStop = canStop(track, S);
		if(!canStop){
			System.out.println("no path feasible");
		} else {
			//Start with cache[S][0]
			Action currentAction = cache[S][0];
			int currentSpeed = S;
			int currentPosition = 0;
			while(currentSpeed != 0){
				if(currentAction == Action.SAME_SPEED){
				} 

				if(currentAction == Action.DECREASE_SPEED){
					currentSpeed = currentSpeed - 1;				
				} 

				if(currentAction == Action.INCREASE_SPEED){
					currentSpeed = currentSpeed + 1;
				}

				if (currentAction == Action.GAME_OVER){
					throw new AssertionError("Game over");
				}
				if (currentSpeed != 0){
					currentPosition = currentPosition + currentSpeed;
					positions.add(currentPosition);
				}
				
			}
			System.out.println("Positions: " + positions.toString());

		};
		for (int i = 0; i < track.length; i++){
			System.out.printf("\n ");
			for (int j = 0; j < track.length; j++){
				System.out.printf("%8s", cache[i][j]);
			}
			System.out.printf("\n ");
		}

	}
	public static boolean canStop (boolean[] track, int S){
		int n = track.length;
		cache = new Action[n+1][n+1];
	
		boolean result = canStop(track, S, 0);
		return result;

	}

	private static boolean canStop(boolean[] track, int speed, int nextPosition){

		System.out.println("speed: " + speed + " nextPosition:" + (nextPosition));
	
		//Validate Arguments
		if (speed < 0 || speed >= track.length || nextPosition < 0) throw new AssertionError("Arguments out of bounds: " + speed + " position: " + nextPosition); //


		//BASE CASE 

		if (nextPosition >= track.length) return false;

		// assert(speed >= 0 && track.length > nextPosition >= 0)
		if (cache[speed][nextPosition] != null){
			if (cache[speed][nextPosition] == Action.GAME_OVER){
				return false;
			} else return true;
		}
		


		if (nextPosition > 0 && track[nextPosition-1] == false){
			cache[speed][nextPosition] = Action.GAME_OVER;
			return false;
		} 
	
		if (speed == 0) {
			if (nextPosition == 0) {
				cache[speed][nextPosition] = Action.GAME_OVER;
				return false; // we still have to start
			}
			else {
				if (track[nextPosition - 1]){
					cache[speed][nextPosition] = Action.SAME_SPEED;
				} else {
				 	cache[speed][nextPosition] = Action.GAME_OVER;
				}
				return track[nextPosition - 1];
			}
		}

		// RECURSVE CASE. speed > 0, nextPosition
		
		boolean one = canStop(track, speed, nextPosition + speed);
		boolean two = canStop(track, speed - 1, nextPosition + speed - 1);
		boolean three = canStop(track, speed + 1, nextPosition + speed + 1 ) ;

		int position = -1;

		if (one){
			//jumpTo = nextPosition + speed - 1;
			cache[speed][nextPosition] = Action.SAME_SPEED;

		}

		if (two){
			//jumpTo = nextPosition + speed - 2;
			cache[speed][nextPosition] = Action.DECREASE_SPEED;
		}

		if (three){
			//jumpTo = nextPosition + speed;
			cache[speed][nextPosition] = Action.INCREASE_SPEED;
		}

		//positions.add(jumpTo);
		boolean result =  one || two || three;
		
		if(!result){
			cache[speed][nextPosition] = Action.GAME_OVER;
		}

		return result ;

	}



	public static void main(String[] args){

		boolean[] input = new boolean[]{true, false, true, true, false, true, true, true, false, true, true};
		//System.out.println(canStop(input, 1));
				
		printPath(input, 3);

	}



}