
package puzzles;
/**
 * Given an input string, returns a list of all permutations.



 https://www.topcoder.com/blog/generating-permutations/
 https://www.educative.io/page/11000001/90001

 */


import java.util.*;


public class Permutation{
	

	static int countOne = 0;
	static int countTwo = 0;


		// Function specification
	public static List<String> permute(String input){	

		return permuteOneHelper("", input); // result is a mutable object passed in the recursive function. the rest two are two immutable string objects. This is the accumulator pattern. 
	}

	/** Instead of using using the function required directly for recursion (which has a fixed specification), we build a custom recursive function based on how we are solving it. 


	How are we solving it? 
		- If we have a partial permutation with k letters, we can build a partial permutations (in n-k ways) of k+1 letters by choosing one of the next letters.
		- Now each of those (n-k) permutations becomes a partial permutation of k+1 letters, and we can build new permutations from each such permutation. 
			What is the work that is being repeated? Building a permutation of k+1 from k (where we pick an element from the rest) repeats the kind of work we do in the previous step, only with different inputs. The generalization of this step will characterize the recursive step and determine the specification of the recursive function:
			Thus, the inputs we need: a partial permutation, the remaining letters to pick a next letter from. And an accumulator (Can we make do with an input and index? No. Because the partial input could be selected characters from any part of the word, not necessarily contiguous )
		- We get to a permutation when we don't have any new letters to build new permutations. 

		Time Complexity:  
		Solution to T(n) = n * T(n-1).
		How many times do we call the function? 
		Make the recursion tree (which here is just the call stack) .
		After the initial call (Level 0), there are n calls at the first level, and then n-1 calls for each of those calls at level 2 so n(n-1) calls at Level 2. In general, at level k, there are n(n-1)(n-2)...(n-k+1) calls or n!/n-k! calls.  At the last level (level n)  there are  n! calls (each where the input "rest" is empty). 

		So the total number of calls is n!n-k!  where k from 0 to n, or (k = 0 ... n)sum (n!/k!) = n!* k = (0 ... n) sum(1/k!). But sum k = 0 ... +oo 1/k! = e = e. so you can say that the number of calls is O(e.n!) which is O(n!).

		Space Complexity: 
		For each call to the recursive function after the initial call,  we create two new string objects and pass them as inputs. So the number of String objects created would be O(2*number of calls.)  


		Possible optimizations: us rest.length == 1 instead of rest.isEmpty() for base case. this would save n! calls. 
		
		This permuteRest method is called a helper method. It satisfies a different spec from the original permutations, because it has a new parameter partial. This parameter fills a similar role that a local variable would in an iterative implementation. It holds temporary state during the evolution of the computation. The recursive calls steadily extend this partial permutation, selecting one of letter from the remaining word, until finally reaching the end of the word (the base case), at which point the partial permutation is returned as the only result. Then the recursion backtracks and fills in other possible permutations. 

		Could we use static variables partial, rest, list instead of using a helper method instead of a parameter? Static variables and aliases to mutable data are very unsafe for recursion, and lead to insidious bugs. When you’re implementing recursion, the safest course is to pass in all variables, and stick to immutable objects or avoid mutation.

		 In an ideal recursive implementation, all variables are final, all data is immutable, and the recursive methods are all pure functions in the sense that they do not mutate anything. The behavior of a method can be understood simply as a relationship between its parameters and its return value, with no side effects on any other part of the program. This kind of paradigm is called functional programming, and it is far easier to reason about than imperative programming with loops and variables. In iterative implementations, by contrast, you inevitably have non-final variables or mutable objects that are modified during the course of the iteration. Reasoning about the program then requires thinking about snapshots of the program state at various points in time, rather than thinking about pure input/output behavior.

	**/

	private static List<String> permuteOneHelper(String partial, String rest){
		List<String> result = new ArrayList<>();
		if(rest.isEmpty()){
			countOne++;
			result.add(partial); // Base case.
		} else {
			for(int j = 0; j < rest.length(); j++){;
				countOne++;
				result.addAll(permuteOneHelper(partial + rest.charAt(j), rest.substring(0,j) + rest.substring(j+1)));// we create two new string objects and pass them.
			}
		}
		return Collections.unmodifiableList(result);
	}

	
	/** An alternative way of looking at the problem: instead of looking at each partial permutation and the process of generating a new permutation from it, we look at a list of partial permutations and the process of generating new list of bigger permutations. 
	- Thus, if we have have a list of k! partial permutations (all permutations of  k letters), we generate a new list of k+1! permutations from it by using (only) the first of the remaining letters.
	- The input we need: the k! partial permutations, and the next letter/index. We don't need an accumulator.
	Notice how different specification for the recursive steps can make recursive decomposition simpler or more complex, and changes the time complexity by orders of magnitude!

	To check: 
	Time Complexity: Let it be T(k) for k elements.
	T(n) = T(n-1) + n! 
	Here the recursion call stack is only n level deep. Most of the work is done not in recursive calls but in the work done after the recursive step in processing the result through the for loop. In general, at level k, the outer for loops over n-k! times and the inner for loop loops over n-k+1 times (The last level has 1 element, the second last has 2 elements each of lenth 2, and so on.) So at level k, the time take is n-k+1!, with k ranging from n to 1. 
	Time complexity = 1 + 2!*3 + 3!*4 + ....n-1!*n = 1! + 2! + 3! ...+n! = 


	Space complexity: To verify: n*n! for List<String> (From https://stackoverflow.com/questions/40583612/whats-the-space-complexity-of-this-permutations-algorithm
	https://stackoverflow.com/questions/4240080/generating-all-permutations-of-a-given-string (CTCI answer)
	) 

	Any optimizations from using StringBuffer, how does Itertator work. 

	**/


	
	public static List<String> permuteTwo(String remaining){  //  This is NOT the accumulator pattern. We pass an immutable string object.
		List<String> newList = new ArrayList<>();
		if(remaining.length() == 1){
			 countTwo++;
			 newList.add(remaining);
		} else{		
			List<String> partialPerms = permuteTwo(remaining.substring(0, remaining.length()-1)); 

			for(String permutation: partialPerms )
				for(int j = 0; j <= permutation.length(); j++){
					countTwo++;
					newList.add(permutation.substring(0, j) + remaining.charAt(remaining.length()-1) + permutation.substring(j));
			}	
		}
		return newList;
	}


	/**
	 * we could alternatively use the function specified directly (top down recursion): assume we have a list of permutations for n-1 elements. We pick each of the n letters as the first element, then append it to the permutations of the remaining. This is pretty much a direct definition of n!=n × (n-1)! 
	 */
	
	
	public static List<String> permuteThree(String input){
		return permuteThree(input, 0);
	}

	private static List<String> permuteThree(String input, int i){
		List<String> result = new ArrayList<String>();
		if(i == input.length() - 1){
			result.add(input);
		} else {
			for(int j = i; j < input.length(); j++){
				result.addAll(permuteThree(swap(input,i,j), i+1));
			}
		}
		return Collections.unmodifiableList(result);
	}

	private static String swap(String input, int i, int j){
		StringBuilder result = new StringBuilder(input);
		result.setCharAt(i, input.charAt(j)); 
		result.setCharAt(j, input.charAt(i));
		return result.toString();
	}


	public static List<String> permuteFour(String input){
		StringBuilder word = new StringBuilder(input);
		List<String> list = new ArrayList<>();
		permuteFour(word, 0, list);
		return list;
	}

	private static void permuteFour(StringBuilder input, int i, List<String> list){
		if(i == input.length() - 1){
			list.add(input.toString());
		} else {
			for(int j = i; j < input.length(); j++){
				swap(input,i,j);
				permuteFour(input, i+1, list);
				swap(input,i,j);
			}
		}
		;
	}

	private static StringBuilder swap(StringBuilder input, int i, int j){
		char letter = input.charAt(i);
		input.setCharAt(i, input.charAt(j)); 
		input.setCharAt(j, letter);
		return input;
	}






	

/**
	Note that if you don't actually need a List<String>, it might be better to wrap things up as a custom Iterator<String> implementation, so that you don't need to keep all permutations in memory at once, and don't need to pre-calculate all permutations before you start doing anything with any of them. (Of course, that's a bit trickier to implement, so it's not worth it if the major use of the Iterator<String> will just be to pre-populate a List<String> anyway.)
	 
	 private static PermutationIterator<String> permuteThree(String input){  //  This is NOT the accumulator pattern. We pass an immutable string object.
		List<String> newList = new ArrayList<>();
		if(input.length() == 1){
			 countTwo++;
			 newList.add(input);
		} else{		
			Iterator<String> partialPermsIterator = permuteThree(input.substring(0, input.length()-1)); 

			while(partialPermsIterator.hasNext()){
				String permutation = partialPermsIterator.next();
				for(int j = 0; j <= permutation.length(); j++){
					countTwo++;
					newList.add(permutation.substring(0, j) + input.charAt(input.length()-1) + permutation.substring(j));
				}
			}
		}
		return newList.iterator();
	}

	private class PermutationIterator implements Iterator<String>{

		@Override hasNext(){

		}


	}


**/



	public static void main(String args[]){
		String input = "abcdefghi";
		long startTime = System.nanoTime(); 
		List<String> permutations = permute(input);
		long endTime   = System.nanoTime();
		long totalTime = endTime - startTime;
		System.out.println("Number of Permutation: " + permutations.size() + ". Total time first:"  + totalTime + " . Count: " + countOne);

		startTime = System.nanoTime();
		permutations = permuteTwo(input);
		endTime   = System.nanoTime();
		totalTime = endTime - startTime;
		System.out.println("Number of Permutation: " + permutations.size() + ". Total time first:"  + totalTime + " . Count: " + countTwo);

		startTime = System.nanoTime();
		permutations = permuteThree(input);
		endTime   = System.nanoTime();
		totalTime = endTime - startTime;
		// for(String permutation: permutations){
		// 	System.out.println(permutation);
		// }
		System.out.println("Number of Permutation: " + permutations.size() + ". Total time third:"  + totalTime);

		startTime = System.nanoTime();
		permutations = permuteFour(input);
		endTime   = System.nanoTime();
		totalTime = endTime - startTime;
		// for(String permutation: permutations){
		// 	System.out.println(permutation);
		// }
		System.out.println("Number of Permutation: " + permutations.size() + ". Total time fourt:"  + totalTime);

	}


}




