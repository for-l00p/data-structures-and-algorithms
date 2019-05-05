

package puzzles;


public class ActivitySelection {
	

	private static void validate (int[] startTimes, int[] finishTimes){

		int n = startTimes.length;
		for (int i = 0; i < n; i++){
			if (startTimes[i] > finishTimes[i]){
				throw new IllegalArgumentException("Start time for index " + i + " is greater than finishTime");
			}
		}
	}

	private static boolean allZero(int[] input){
		for (int i = 0; i < input.length; i++){
			if (input[i] == 1){
				return false;
			}
		}
		return true;
	}

	public static int solve(int[] startTimes, int[] finishTimes){

		validate(startTimes, finishTimes);
		int n = startTimes.length;
		int[] toInclude = new int[n];
		for (int i = 0; i < n; i++){
			toInclude[i] = 1;
		}

		return solve(startTimes, finishTimes, toInclude);
	}
	private static int solve(int[] startTimes, int[] finishTimes, int[] toInclude){

		
		/**
		 *	 Select an activity ak
		 *	 Assume a is part of optimal solution
		 *	 Form two sets: Set of activities that finish before a starts (A1) and set of activities that start after a finishes (A2).
		 *	 solve(A) =  min over k (solve(Ak) and solve (Aj) 
		
		**/


		int n = startTimes.length;
		int max = 0;

		if (allZero(toInclude)){
			return max;
		}

		for (int i = 0; i < n; i++){
			if (toInclude[i] == 1){
				int[] compatibles = findCompatible(i, startTimes, finishTimes, toInclude);
				int subsolution = solve(startTimes, finishTimes, compatibles);
				if (max < subsolution + 1){
					max = subsolution + 1;
				}
			}
			
		}

		return max;


	}

	//Given an activity, find the indicies of activities whose finishiing times are less than the start time. If we require the finshTimes to be sorted, then this is easier: scan the array till you reach j such that finishTime[j] > startTimes[index].
	public static int[] findCompatible(int index, int[] startTimes, int[] finishTimes, int[] toInclude){

		int n = startTimes.length;
		int[] result = new int[n];

		int startBenchmark = startTimes[index];
		int finishBenchmark = finishTimes[index];

		for (int i = 0; i < n; i++){

			if (startTimes[i] > finishBenchmark || finishTimes[i] < startBenchmark){
				if(toInclude[i] == 1){
					result[i] = 1;
				}
			}
		}

		return result;

	}


	public static void main(String[] args){

		int[] startTimes = new int[]{1,3,0, 5,3,5,6,8,8,2,12};
		int[] finishTimes = new int[]{4,5,6,7,8,9,10,11,12,13,14};
		int result = solve(startTimes, finishTimes);
	
		System.out.println("result: " + result);

	}

}