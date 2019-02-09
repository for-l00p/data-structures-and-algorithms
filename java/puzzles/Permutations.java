package puzzles;




/**
 * Given a string, find all permutations of the string. 
 *
 * References: https://stackoverflow.com/questions/4240080/generating-all-permutations-of-a-given-string
 *
 * 
 */

import java.util.List;
import java.util.ArrayList;

public class Permutations{
	
	/**
	 * A basic backtracking algorithm for permutations: Remove each element from the n elements one at a time, then append it to the (n-1)! remaining permutations. This is pretty much a direct definition of n!=n × (n-1)! 
	 */
	public static List<String> permute(String s){

		StringBuilder word = new StringBuilder(s);
		List<String> permutationList = new ArrayList<>();
		permute(word, 0, permutationList);
		return permutationList;
	}
	//https://www.educative.io/page/11000001/90001
	private static void permute(StringBuilder s, int i, List<String> list ){
		if(i == s.length() - 1 ){
			list.add(new String(s)); //  If we've chosen all the characters then we're done
		} else {
			//Otherwise, we've chosen characters [0] to [j-1]
			for(int j = i; j < s.length(); j++){//so let's try all possible characters for a[j]
				swap(s,i,j);//Choose which one out of a[j] to a[n] you will choose
				permute(s,i+1,list);//// Choose the remaining letters
				swap(s,i,j);// // Undo the previous swap so we can choose the next possibility for a[j]
			}
		}
	}

	private static void permute2(StringBuilder s, boolean[] used, List<String> list ){
		if(i == s.length() - 1 ){

			list.add(new String(s)); //  If we've chosen all the characters then we're done
		} else {
			//Otherwise, we've chosen characters [0] to [j-1]
			for(int j = i; j < s.length(); j++){//so let's try all possible characters for a[j]
				swap(s,i,j);//Choose which one out of a[j] to a[n] you will choose
				permute(s,i+1,list);//// Choose the remaining letters
				swap(s,i,j);// // Undo the previous swap so we can choose the next possibility for a[j]
			}
		}
	}

	//Instead of swapping, we could use a boolean[] to indicate if an element is freed up or not, similar to placing a queen in N Queens. 

	private static void swap(StringBuilder word, int indexOne, int indexTwo){
		char temp = word.charAt(indexOne);
		word.setCharAt(indexOne, word.charAt(indexTwo)); 
		word.setCharAt(indexTwo,  temp);
	}



	public static void main(String args[]){
		String test = "abb";
		List<String> permutations = permute(test);
		for(String element: permutations){
			System.out.println(element);
		}

	}	
}