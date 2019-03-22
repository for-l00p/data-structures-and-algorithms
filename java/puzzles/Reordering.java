package puzzles;

import java.util.*;
public class Reordering {
	
	// Space complexity: in place. Time complexity: O(n^2)
	public static int[] moveRange2(int[] input, int start, int end, int position){
		int temp;
		for (int i = end; i >= start; i--){
			for(int j = i; j < position - 1 - (end-i); j++){
				temp = input[j+1];
				input[j+1] = input[j];
				input[j] = temp;
			}

		}
		return input;

	}

	//Space complexity: O(2*range). Time complexity: O(n)
	//
	public static int[] moveRange(int[] input, int start, int end, int position){
		int temp;
		List<Integer> list = new ArrayList<>();
		for (int i = start; i <= end; i++){
			list.add(input[i]);
		}

		for (int i = end + 1; i < position; i++){
			input[i - (end-start + 1)] = input[i];
		}

		for (int i = 0; i <= end-start; i++){
			input[position - (end-start) + i - 1 ] = list.get(i);
		}

		System.out.println(Arrays.toString(input));
		return input;

	}

	//Space complexity: in place. Time complexity: O(n^2)

	public static int[] moveFizzBuzz2(int[] input, int indexOfStart){
	


		int count = 0;

		int i = 0;
	 	while(i < input.length) {
	 	
	 		System.out.println("i: " + i + " Array: " + Arrays.toString(input));
	 		
	 		if (i == indexOfStart){
				i = indexOfStart + count + 1;
				continue;
			}

	 		if (isFizzBuzz(input[i])){
	 			
				if(i < indexOfStart){
					bubble(i, indexOfStart + count, input);
					indexOfStart--;		
				} 

				if (i > indexOfStart){
					bubble(i, indexOfStart + count + 1, input);
					i++;	
				}

				count++;
					
	 		} else {
	 			i++;
	 		}

	 	}
	 	return input;
		
	}

	private static boolean isFizzBuzz(int n){
		boolean divisibleByThree = false;
		boolean divisibleByFive = false;
		if(n % 3 == 0){
			divisibleByThree = true;
		}

		if(n % 5 == 0){
			divisibleByFive = true;
		}

		return divisibleByThree || divisibleByFive;
	}


	private static void bubble(int from, int to, int[] A){
		System.out.println("From:" + from + " to: " + to);
		int temp;
		int current = from;
		if(from < to){
			while(current != to){
				current++;
				temp = A[current];
				A[current] = A[current-1];
				A[current-1] = temp;
				
			}
		}

		if (from > to){
			while(current != to){
				current--;
				temp = A[current];
				A[current] = A[current+1];
				A[current+1] = temp;
				
			}
		}
		
	}

	public static int[] moveFizzBuzz(int[] input, int index){
		
		


		int[] result = new int[input.length];
		boolean[] isFizzBuzz = new boolean[input.length];

		for (int i = 0; i < input.length; i++){
			if(isFizzBuzz(input[i])){
				isFizzBuzz[i] = true;
			} else {
				isFizzBuzz[i] = false;
			}
		}

		int j = 0;
		for(int i = 0; i <= index; i++){
			if(!isFizzBuzz[i]) {
				result[j] = input[i];
				j++;
			}
		}

		for(int i = 0; i < input.length; i++){
			if(isFizzBuzz[i]){
				result[j] = input[i];
				j++;
			}
			
		}

		for(int i = index + 1; i < input.length; i++){
			if(!isFizzBuzz[i]){
				result[j] = input[i];
				j++;
			}
		
		}

		return result;


	}

}