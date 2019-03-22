package puzzles;

import java.util.*;


public class NumberChains {
	
	public static int find(int input){

		
		List<Integer> listOfPrevious = new ArrayList<>();
		
		Integer current = doStep(input);
		int i = 1;
		while(!listOfPrevious.contains(current)){
			 listOfPrevious.add(current);
			 current = doStep(current); 
			 i++;
			 System.out.println("Current: " + current);
		}
		listOfPrevious.add(current);
		return i;
	

	}

	private static int doStep(int input){
		char [] intChars = String.valueOf(input).toCharArray();
		Arrays.sort(intChars);
		StringBuilder increasing = new StringBuilder(String.valueOf(intChars));
		int ascending = Integer.parseInt(String.valueOf(increasing));

		StringBuilder decreasing = increasing.reverse();
		int descending = Integer.parseInt(String.valueOf(decreasing));
		//System.out.println("decreasing: " + descending + " increasing: " + ascending);
		return (descending - ascending);

	}

	public static void main(String[] args){
		System.out.println(find(doStep(12345689)));
	}



}