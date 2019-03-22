

/**
 * Given an array of integers, find two numbers such that they add up to a specific target number. 
 *
 * The function twoSum should return indices of the two numbers such that they add up to the target, where index1 < index2.  Please note that your returned answers (both index1 and index2 ) are not zero-based. Put both these numbers in order in an array and return the array from your function ( Looking at the function signature will make things clearer ). Note that, if no pair exists, return empty list.

If multiple solutions exist, output the one where index2 is minimum. If there are multiple solutions with the minimum index2, choose the one with minimum index1 out of them.
 *
 * 
 */

package puzzles;

import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;

public class TwoSum {

      // O(n)
	public static ArrayList<Integer> twoSum(final int[] A, final int B){

		Map<Integer, Integer> neededMap = new HashMap<>();
        ArrayList<Integer> result = new ArrayList<>();

        for(Integer i = 0; i < A.length; i++){
            Integer needed = B - A[i];
            if(!neededMap.containsKey(needed)){
                neededMap.put(needed, i );
            }           
        }
        
        int biggerIndex = A.length + 1;
        int smallerIndex = 0;
        for(int j = 0; j< A.length; j++){
            Integer element = A[j];
            if(neededMap.containsKey(element)){
                //There the value corresponding to key A[j].
                Integer otherIndex = neededMap.get(element);
                if(j < biggerIndex){
                    if(j < otherIndex){
                        biggerIndex = otherIndex+1;
                        smallerIndex = j+1;
                    } else if (j > otherIndex){
                        biggerIndex = j+1;
                        smallerIndex = otherIndex+1;
                    }
                }
            }
        }

        if(biggerIndex < A.length){
            result.add(smallerIndex);
            result.add(biggerIndex);
        } 

        return result;
    }


    /*
    The following algorithm solves the problem in  // O(nlogn):

    Sort the elements in S.
    Form the set S' of complements = target - S. If there exist two elements whose sum is target, the complement of an element in S exists in S', so there is an element that appears in both S and S'.

  A reader submitted a simpler solution that also runs in \Theta(n\lg n)Θ(nlgn) time. First, sort the elements in SS, taking \Theta(n\lg n)Θ(nlgn) time. Then, for each element yy in SS, perform a binary search in SS for x - yx−y. Each binary search takes O(\lg n)O(lgn) time, and there are are most nn of them, and so the time for all the binary searches is O(n\lg n)O(nlgn). The overall running time is \Theta(n\lg n)Θ(nlgn).
′
 .
Merge the two sorted sets SS and S&#x27;S 
′
 .
There exist two elements in SS whose sum is exactly xx if and only if the same value appears in consecutive positions in the merged output.
     */
    
    // O(n^2)
    public static int[] twoSumNaive(int[] nums, int target) {
        int[] result = new int[2];
        for(int i = 0; i < nums.length - 1; i++){
            for (int j = i+1; j < nums.length; j++){
                System.out.println("i: " + i + " j " + j + " sum: " + (nums[i] + nums[j]));
                if(nums[i] + nums[j] == target){
                    result[0] = i;
                    result[1] = j;
                    return result;
                }
                
            }
        }
        
           
        
        return result;
    }

	public static void main(String args[]){

		int[] inputArray = {2,7,11,15};
		int target = 9;
		ArrayList<Integer> twoSumIndices = twoSum(inputArray, target);
		System.out.println("Result " + twoSumIndices.toString());

	}

}