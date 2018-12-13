
import java.util.*;
public class Assembly{


	private int[][] processingTime; // 0 < j < n-1; i = 0, 1; processingTime[i][j] = time taken to process the chassis at station j in line i. 
	private int[][] switchingTime; //  0 < j < n-1; i = 0, 1; switchingTime[i][j] = time taken to switch from line station j-1 in line i to station j in the other line. switchingTime[i][0] = 0. 
	private int[] entryTime; 
	private int[] exitTime;


	private int n;
	private int[][] optimal;
	private int[][] parent;
	private int lastStationLine;
	private int answer;


	/* 
	Let f(i,j) denote the optimal solution of this subproblem: Starting from the start, what is the optimal time to finish proceessing at specific station S(i,j)? 
	f(0,0) = entryTime[0] + processingTime[0,0]. 
	f(1,0) = entryTime[1] + processingTime[1,0]
	 What is f(i,j)?

	 Option 1: f(i,j) = processingTime[i][j] + f(i, j-1);
	 Option 2: f(i,j) = switchingTime[i+1 mod 2 ][j] + f(i+1 mode 2, j-1), n-1>= j>0;

	 Our final answer would be min(f(0,n-1) + exitTime[0], f(1,n-1) + exitTime[1]).


	*/

	public Assembly(int[][] processingTime, int[][] switchingTime, int [] entry, int[] exit){
		createAssembly(processingTime, switchingTime, entry, exit);
		//Use of construcor to set static variables Not recommended:  static variables are independent of object instances and constructors are called when objects are created. So every time you create an Object, the static variable is set although its value is not related to the object being created
	}


	private void createAssembly(int[][] processingTime, int[][] switchingTime, int [] entry, int[] exit){
		n = processingTime[0].length;
		this.processingTime = processingTime;
		this.switchingTime = switchingTime;
		entryTime = entry;
		exitTime = exit;

		optimal = new int[n][n];
		optimal[0][0] = entryTime[0] + processingTime[0][0];
		optimal[1][0] = entryTime[1] + processingTime[1][0];

		parent = new int[n][n];
		parent[0][0] = -1;
		parent[1][0] = -1;

		calculateSubProblems();

		if(optimal[0][n-1] + exitTime[0] < optimal[1][n-1] + exitTime[1]){
			answer = optimal[0][n-1] + exitTime[0];
			lastStationLine = 0;
		} else {
			answer = optimal[1][n-1] + exitTime[1];
			lastStationLine = 1;
		}

		printPath();

	}

	private void calculateSubProblems(){

		for (int j = 1; j < n; j++){
			for (int i = 0; i <=1; i++ ){
				//System.out.println("Considering line " + i + " and station " + j);
				int optionStay = optimal[i][j-1] + processingTime[i][j];
				int optionSwitch = optimal[(i+1)%2][j-1] + switchingTime[(i+1)%2][j] + processingTime[i][j];
				//System.out.println("OptionStay " + optionOne + " optionSwitch " + optionTwo);
				if(optionStay < optionSwitch){
					optimal[i][j] = optionStay;
					parent[i][j] = i;
					System.out.println("Optimal path for line " + i + " and station " + j + " is line " + i + ". Value is " + optionStay);
				} else {
					optimal[i][j] = optionSwitch;
					parent[i][j] = (i + 1) % 2;
					//System.out.println("Optimal path for line " + i + " and station " + j + " is  line " + (i+1)%2 + ". Value is " + optionTwo);
				}
				
			}
		}
	}

	private void printPath(){

		int i = lastStationLine;
		for(int j = n-1; j >= 0; j--){
			System.out.println("Station: " + (j+1) + " Line: " + i);
			i = parent[i][j];
		}
	
	}

	public void printPathRecursive(){
		printPathRecursiveHelper(n, lastStationLine);
	}


	private void printPathRecursiveHelper(int j, int i){
		if(j == 0){
			 //System.out.println("Entered here: " + i);
			 return;
		}
		printPathRecursiveHelper(j-1, parent[i][j-1]);
		System.out.println("Station: " + (j) + " Line: " + i);

	}



	public static void main(String[] args){

		int a[][] = {{7, 9, 3, 4, 8, 4}, 
                    {8, 5, 6, 4, 5, 7}}; 
        int t[][] = {{0, 2, 3, 1, 3, 4}, 
                    {0, 2, 1, 2, 2, 1}}; 
        int e[] = {2, 4};
        int x[] = {3, 2}; 
      
        Assembly test = new Assembly(a, t, e, x); 
        System.out.println(Arrays.toString(test.optimal[0]));
        System.out.println(Arrays.toString(test.optimal[0]));
        System.out.println(Arrays.toString(test.parent[0]));
        System.out.println(Arrays.toString(test.parent[1]));
        System.out.println(test.answer);
        test.printPathRecursive();
        test.printPath();



	}
}