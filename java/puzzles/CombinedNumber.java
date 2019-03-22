

package puzzles;

import java.util.Arrays;
import java.util.Comparator;

public class CombinedNumber {

	public static String combine (int[] input){

		Integer[] arr = new Integer[input.length];
		for (int i = 0; i < input.length; i++){
			arr[i] = input[i];
		}

		Arrays.sort(arr, new Comparator<Integer>(){

			@Override
			public int compare (Integer i, Integer j) {
				String firstNum = i.toString();
				String secondNum = j.toString();			
				Integer compareOne = Integer.parseInt(firstNum + secondNum);
				Integer compareTwo = Integer.parseInt(secondNum + firstNum);
				return compareTwo.compareTo(compareOne);
			}

		});

		StringBuilder sb = new StringBuilder();
		for (int i: arr){
			sb.append(i);
		}
		return String.valueOf(sb);

	}


	// public static String combine2 (int[] input){

	// 	Integer[] arr = Arrays.stream(input).boxed().mapToInt(i -> i).sorted((i, j) -> {
	// 		String firstNum = i.toString();
	// 		String secondNum = j.toString();			
	// 		Integer compareOne = Integer.parseInt(firstNum + secondNum);
	// 		Integer compareTwo = Integer.parseInt(secondNum + firstNum);
	// 		return compareTwo.compareTo(compareOne);
	// 	}).toArray();

		
	// 	StringBuilder sb = new StringBuilder();
	// 	for (int i: arr){
	// 		sb.append(i);
	// 	}
	// 	return String.valueOf(sb);

	// }





}


