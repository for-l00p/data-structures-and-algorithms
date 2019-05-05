

package puzzles;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

/**

Three approaches to activity selection:

firstApproach (naive, top-down, recursive, backtracking):

1. Assume a particular activity a is part of the optimal solution
2. Solve the subproblem of solving for {Activities - allImcompatibleWith(a)}.
3. Loop over all a and choose maximum. 

secondApproach(top-down, recursive, backtracking):
1. Assume a particular activity a is part of optimal solution
2. Solve the subproblems: (1) activity that finish before a starts (2) activity that start after a finishes. Let S(i, j) refer to the activities that lie between activities i and j (start after i and end before j). Then S(i,j) characterises the subproblems. Then S for a(k),
S(i,j) = max S(i,k) + S(k+i) loop over j-i values for k
3. Loop over a and choose maximum. 

thirdApproach (memoize the secondApproach: as it has overlapping subproblems)

fourthApproach (bottom-up DP):

1. Characterize the problem space: S(i,j); i = 0,1,....n+1, j = 0,1,...n+1.
2. Fill with neutral values = 0
3. Fill base cases: 
c(i,j) = 0 for i >= j
c(i,j) = 1 for i = j-1 
c(i,j) =  for i >= j 



**/

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

	public static int[] solve(int[] startTimes, int[] finishTimes){

		validate(startTimes, finishTimes);
		int n = startTimes.length;
		int[] toInclude = new int[n];
		for (int i = 0; i < n; i++){
			toInclude[i] = 1;
		}
		int[] optimal = new int[n];
		return solve(startTimes, finishTimes, toInclude);
	
	}
	private static int[] solve(int[] startTimes, int[] finishTimes, int[] toInclude){

		
		/**
		 *	 Select an activity ak
		 *	 Assume a is part of optimal solution
		 *	 Form two sets: Set of activities that finish before a starts (A1) and set of activities that start after a finishes (A2).
		 *	 solve(A) =  min over k (solve(Ak) and solve (Aj) 
		
		**/



		int n = startTimes.length;
		int max = 0;
		int chosen = -1;

		int[] optimal = new int[n];
		if (allZero(toInclude)){
			return optimal;
		}

		for (int i = 0; i < n; i++){
			if (toInclude[i] == 1){
				int[] compatibles = findCompatible(i, startTimes, finishTimes, toInclude);
				int[] temp  = solve(startTimes, finishTimes, compatibles);
				int subsolution = temp.length;
				if (max < subsolution + 1){
					max = subsolution + 1;
					chosen = i;
					optimal = temp;
				}
			}
			
		}

		//System.out.println("Solution for " + Arrays.toString(toInclude) + " Adding to chosen" + chosen);
		optimal[chosen] = 1;
		return optimal;
	}

	//Given an activity, find the indicies of activities whose finishiing times are less than the start time. If we require the finshTimes to be sorted, then this is easier: scan the array till you reach j such that finishTime[j] > startTimes[index].
	public static int[] findCompatible(int index, int[] startTimes, int[] finishTimes, int[] toInclude){

		int n = startTimes.length;
		int[] result = new int[n];

		int startBenchmark = startTimes[index];
		int finishBenchmark = finishTimes[index];

		for (int i = 0; i < n; i++){

			if (startTimes[i] >= finishBenchmark || finishTimes[i] < startBenchmark){
				if(toInclude[i] == 1){
					result[i] = 1;
				}
			}
		}

		return result;

	}

	public static int solveIterative(int[] startTimes, int[] finishTimes){

		int n = startTimes.length;
		
		

		int[] temp = new int[n+2];
		temp[0] = 0;
		temp[n+1] = Integer.MAX_VALUE;
		for (int i = 1;  i <= n; i++){
			temp[i] = startTimes[i-1];
		}

		startTimes = temp;

		temp = new int[n+2];
		temp[0] = 0;
		temp[n+1] = Integer.MAX_VALUE;
		for (int i = 1; i <= n; i++){
			temp[i] = finishTimes[i-1];
		}

		finishTimes = temp;

		int[][] maxValueForSubproblems = new int[n+2][n+2];
		int[][] chosenActivities = new int[n+2][n+2];
		for (int i = 0; i < n + 2; i++){
			maxValueForSubproblems[i][i] = 0;
		}

		/**
		 * maxValueForSubproblems[i][j] stores the max number of compatible sets that lie between activities i and j: i.e. activities k for which finishTimes[k] < startTimes[j] and startTimes[k] > finishTimes[i]. 
		 */
		for (int d = 1; d <= n+1; d++){
			for (int i = 0; i + d <= n+1; i++){
				int j = i + d;
				int max = 0;
				//To find the value of  maxValueForSubproblems[i][j], we need to find the activities that lie between i and j and loop over them. If the input is in increasing order of finishing times, the sets i+1, i+2,...j- all finish before j finishes, but they might start before i finishes or finish after a starts.  So we need only find the activities 
				for (int k = i+1; k < j; k++){
					//Find the sets S(i,k) and S(k,j). What do they correspond to? For example, if the input is in increasing order of finishing times, though the sets i+1, i+2,...k-1 all finish before k finishes. But which sets finish before k starts?
					if (startTimes[k] > finishTimes[i] && finishTimes[k] < startTimes[j]){
						int candidateMax = maxValueForSubproblems[i][k] + maxValueForSubproblems[k][j] + 1;
				 		if (candidateMax > max){
				 			max = candidateMax;
				 			chosenActivities[i][j] = k;
				 		}
					}
					
				}

				maxValueForSubproblems[i][j] = max;
			}
		}

		//System.out.println(Arrays.deepToString(maxValueForSubproblems));

		printActivities(maxValueForSubproblems, chosenActivities, 0, n+1);
		return maxValueForSubproblems[0][n+1];

	}


	public static void printActivities(int[][] opt , int[][] chosenActivities, int i, int j){

		if (opt[i][j] > 0 ){
			int k = chosenActivities[i][j];
			System.out.println("Activity Chosen:" + k);
			printActivities(opt, chosenActivities, i, k );
			printActivities(opt, chosenActivities, k, j );
		}
	}


	public static int solveGreedy(int[] startTimes, int[] finishTimes){

		int n = startTimes.length;

		int finishTimeOfLastActivityAdded = 0;
		int max = 0;
		List<Integer> chosenActivities = new ArrayList<>();

		for (int i = 0; i < n; i++){
			if (startTimes[i] >= finishTimeOfLastActivityAdded){
				max++;
				finishTimeOfLastActivityAdded = finishTimes[i];
				chosenActivities.add(i);
			}
		}
		System.out.println("Chosen Activities:" + chosenActivities.toString());
		return max;

	}








	public static void main(String[] args){

		int[] startTimes = new int[]{1,3,0,5,3,5,6,8,8,2,12};
		int[] finishTimes= new int[]{4,5,6,7,8,9,10,11,12,13,14};
		int[] result = solve(startTimes, finishTimes);
		int result2  =solveGreedy(startTimes, finishTimes);
		System.out.println("result: " + Arrays.toString(result));
		System.out.println("result2: " + result2);

	}

}