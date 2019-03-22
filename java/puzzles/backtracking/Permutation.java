

/**
 
Given an input string, returns a list of all permutations.

Backtracking:


 the efficiency of the backtracking algorithm depends on reject returning true for candidates that are as close to the root as possible.
 
Permutation:

- All the different algorthims: https://www.topcoder.com/blog/generating-permutations/

- Cracking the Coding Interview: https://stackoverflow.com/questions/4240080/generating-all-permutations-of-a-given-string

- Elegant Code: https://www.educative.io/page/11000001/90001

- MIT Lecture: https://www.youtube.com/watch?v=78t_yHuGg-0

- BottomUp Recursion vs Top-down: https://reginaldlong.com/f-recursion-your-guide-to-overcoming-your-worst-interview-related-fear-9/

- https://stackoverflow.com/questions/54272353/which-of-the-following-two-implementation-of-string-permutation-algorithm-is-bet

https://www.quora.com/How-would-you-explain-an-algorithm-that-generates-permutations-using-lexicographic-ordering


N QUEENS:

- DFS Vs Backtracking: https://stackoverflow.com/questions/2709030/explain-bfs-and-dfs-in-terms-of-backtracking/3156208#3156208
- http://cs.bc.edu/~alvarez/Algorithms/Notes/backtracking.html
- Dijkstra: https://www.cs.utexas.edu/users/EWD/transcriptions/EWD03xx/EWD316.9.html
- Knuth on Dijkstra: https://arxiv.org/pdf/cs/0011047.pdf

Subsequences:

- MIT Lecture: http://web.mit.edu/6.031/www/fa18/classes/14-recursion/#@think_about_several




Important concepts:

Top-Down Recursion (e.g. Memoization if some subproblems are overlapping)
Bottom-Up Recursion??( or Rewriting specification of recursion function): Solving the easiier cases and solving incrementally harder problem. 
Tail Recursion: Top-down recursion with accumulator
Bottom Up Iteration (e.g. Dynamic Programming if some problems are overlapping)

Pure functions (Functions that return something, as in functional programmng) vs Accumulator Pattern (functions that do something, as in imperative programming)

Immutable objects, Mutable objects:

	Could we use static variables partial, rest, list instead of using a helper method instead of a parameter? Static variables and aliases to mutable data are very unsafe for recursion, and lead to insidious bugs. When you’re implementing recursion, the safest course is to pass in all variables, and stick to immutable objects or avoid mutation.

	In an ideal recursive implementation, all variables are final, all data is immutable, and the recursive methods are all pure functions in the sense that they do not mutate anything. The behavior of a method can be understood simply as a relationship between its parameters and its return value, with no side effects on any other part of the program. This kind of paradigm is called functional programming, and it is far easier to reason about than imperative programming with loops and variables. In iterative implementations, by contrast, you inevitably have non-final variables or mutable objects that are modified during the course of the iteration. Reasoning about the program then requires thinking about snapshots of the program state at various points in time, rather than thinking about pure input/output behavior.

	A search through a space (like the space of assignments to a set of Boolean variables) generally proceeds by making one choice after another, and when a choice leads to a dead end, you backtrack.

	Mutable data structures are typically not a good approach for backtracking. If you use a mutable Map, say, to keep track of the current variable bindings you’re trying, then you have to undo those bindings every time you backtrack. That’s error-prone and painful compared to what you do with immutable maps — when you backtrack, you just throw the map away!

	But immutable data structures with no sharing aren’t a great idea either, because the space you need to keep track of where you are (in the case of the satisfiability problem, the environment) will grow quadratically if you have to make a complete copy every time you take a new step. You need to hold on to all the previous environments on your path, in case you need to back up.

	Immutable lists have the nice property that each step taken on the path can share all the information from the previous steps, just by adding to the front of the list. When you have to backtrack, you stop using the current step’s state — but you still have references to the previous step’s state.

	Finally, a search that uses immutable data structures is immediately ready to be parallelized. You can delegate multiple processors to search multiple paths at once, without having to deal with the problem that they’ll step on each other in a shared mutable data structure. 


Decisions to make: 

Q1. Whether to go with an iterative implementastion or a recursive one?

Q2. Which function specification to work with? The function we choose, like an abstraction or a word of a language, determines how simple, easy-to-read and natural the recursive decomposition is. It also determins the work we do in the recursve step and after the recursive step? You want to find the one that produces the simplest, most natural recursive step. 

Options a:

- Work directly with the given function. This might require to do a lot of work post-recursion
- Do the work to be done post-recursion before the actual recursion, and store the partial results in an accumulator variable. The accumulator gives the result when we reach the base case. This would be a tail-recursive implementation. The accumulator fills the role a local variabe does in an iterative implementation.

Options b:
- Return a value or not.

Q3. In an accumulator, whether to use a mutable data structure or an immutable data structure. For example, we could store the partial permutation in a mutable structure (Map (a[0] -> i),  an int[] perm where perm[i] gives the position of a[i], or a Stringbuilder. But instead we could use a String.

Q4. Which data structures to use to represent a subproblem?  StringBuilders, String + Index, Maps, etc. etc.

Q5. In permutations, do use a choose position for given character approach, or choose character for given position approach? 
- We fix a character first, permute the rest and then insert this character in all the positions position?  Post recursion work: inserting at the right position.
Or do we fix a position first (say first), and for each character that occupies the position, permute the rest. Post recursion work: prepending.



 */

package puzzles.backtracking;

import java.util.List;
import java.util.ArrayList;
import java.util.ListIterator;


public class Permutation {


	/** 

	Instead of using using the function required directly for recursion (which has a fixed specification), we build a custom recursive function based on how we are solving it. 

	How are we solving it? Bottom-up recursion (Is this any different from top-down with accumulator pattern/tail recursion?)

		- If we have a partial permutation with k letters, we can build a partial permutations (in n-k ways) of k+1 letters by choosing one of the next letters.
		- Now each of those (n-k) permutations becomes a partial permutation of k+1 letters, and we can build new permutations from each such permutation. 
			What is the work that is being repeated? Building a permutation of k+1 from k (where we pick an element from the rest) repeats the kind of work we do in the previous step, only with different inputs. The generalization of this step will characterize the recursive step and determine the specification of the recursive function:
			Thus, the inputs we need: a partial permutation, the remaining letters to pick a next letter from. And an accumulator (Can we make do with an input and index? No. Because the partial input could be selected characters from any part of the word, not necessarily contiguous )
		- We get to a permutation when we don't have any new letters to build new permutations. 

	Time Complexity:  

		How many subproblems are there? 2^n
		Solution to T(n) = n * T(n-1)

		How many times do we call the function? 
		Make the recursion tree. 
		After the initial call (Level 0), there are n calls at the first level, and then n-1 calls for each of those calls at level 2 so n(n-1) calls at Level 2. In general, at level k, there are n(n-1)(n-2)...(n-k+1) calls or n!/n-k! calls.  At the last level (level n)  there are  n! calls (each where the input "rest" is empty). 
		So the total number of calls is n!n-k!  where k from 0 to n, or (k = 0 ... n)sum (n!/k!) = n!* k = (0 ... n) sum(1/k!). But sum k = 0 ... +oo 1/k! = e = e. so you can say that the number of calls is O(e.n!) which is O(n!). 


	Space Complexity: 

		For each call to the recursive function after the initial call,  we create two new string objects and pass them as inputs. So the number of String objects created would be O(2*number of calls), though some of these will be garbage collected.

		The space would grow at n!*n rate as all the permutatons are collected and stored - each permutation is a string of length n. At level k, the permutation list has length n-k!, and since the bottom levels have been winded up, we free up space corresponding to their permutation lists.

		If the permutatons were not being stored but were being printed to the console, then the space requirement would be O(height of DFS tree*n) = O(n^2)

		From MIT: immutable data structures with no sharing aren’t a great idea either, because the space you need to keep track of where you are (in the case of the satisfiability problem, the environment) will grow quadratically if you have to make a complete copy every time you take a new step. You need to hold on to all the previous environments on your path, in case you need to back up.

	Possible optimizations: us rest.length == 1 instead of rest.isEmpty() for base case. this would save n! calls. 
		
	This permuteRest method is called a helper method. It satisfies a different spec from the original permutations, because it has a new parameter partial. This parameter fills a similar role that a local variable would in an iterative implementation. It holds temporary state during the evolution of the computation. The recursive calls steadily extend this partial permutation, selecting one of letter from the remaining word, until finally reaching the end of the word (the base case), at which point the partial permutation is returned as the only result. Then the recursion backtracks and fills in other possible permutations. 


	result is a mutable object passed in the recursive function. The rest two are two immutable string objects. This is the accumulator pattern. 
	
	

	A1: Recursion
	A2a: Redefine function. In the original, two types of work are being done post-recursion: generating each single permutation, and aggregating these single permutations into  a list of permutations. Both of these can have their own accmulator. Here we delegate the work of generating a single permutation to an accumulator variable, but not the aggregating task. So this does not convert into tail recursion.
	A2b: Use pure function. (Alterantive is to use another accumulator to accumulate all the results of the other accmulator)
	A3: Use immutable data structure as accumulator/partial solution container
	A4: Two Strings 
	A5: Subproblem: Given next position, choose a character.
	

	**/

	public static List<String> permute (String input) {	
		return permuteOneHelper("", input); 
	}



	private static List<String> permuteOneHelper(String partial, String rest){
		List<String> result = new ArrayList<>();
		if(rest.isEmpty()){
			result.add(partial); // Base case.
		} else {
			for(int j = 0; j < rest.length(); j++){ //Choose a character
				result.addAll(permuteOneHelper(partial + rest.charAt(j), rest.substring(0,j) + rest.substring(j+1)));// we create two new string objects and pass them.
				//We don't need to unchoose the character because we are not using mutable objects
			}
		}
		return result;
	}

	
	/**
	  
	The above algorithm can be seen another way: we use the function specified directly (top down recursion): assume an oracle gives us a function returning list of permutations for every subproblem of n-1 elements. We pick each of the n letters as the first element, then append it to the permutations of the remaining. This is pretty much a direct definition of n!=n × (n-1)! 
	
	A1: Recursion
	A2a: Redefine function. In the original, two types of work are being done post-recursion: generating each single permutation, and aggregating these single permutations into  a list of permutations. Both of these can have their own accmulator. Here we delegate the work of generating a single permutation to an accumulator variable, but not the aggregating task. So this does not convert into tail recursion.
	A2b: Use pure function. (Alterantive is to use another accumulator to accumulate all the results of the other accmulator)
	A3: Use immutable data structure as accumulator/partial solution container (here String input).
	A4: String and index (the same information captured by Strings partial and rest above are here captured by Strings input and index.) 
	A5: Subproblem: Given next position, choose a character.



	 */

	
	public static List<String> permuteTwo (String input) {
		return permuteTwoHelper(input, 0);
	}

	private static List<String> permuteTwoHelper(String input, int i) {
		List<String> result = new ArrayList<String>();
		if (i == input.length() - 1) {
			result.add(input);
		} else {
			for(int j = i; j < input.length(); j++){
				result.addAll(permuteTwoHelper(swap(input,i,j), i+1));
			}
		}
		return result;
	}

	private static String swap(String input, int i, int j){
		StringBuilder result = new StringBuilder(input);
		result.setCharAt(i, input.charAt(j)); 
		result.setCharAt(j, input.charAt(i));
		return result.toString();
	}



	/**

	Instead of creating new immutable Strings for every function call, we could instead have  captured the same informaton by two stringbuilders: partial/chosen and rest, a strngbuilder and an index, or with a boolean[] of whether a character is chosen or not.
	This would improve space to O(n) in cases where we don't store permutations.

	A1: Recursion
	A2a: Redefine function. In the original, two types of work are being done post-recursion: generating each single permutation, and aggregating these single permutations into  a list of permutations. Both of these can have their own accmulator. Here we delegate the work of generating a single permutation to accumulator variable StringBuilder, and  the aggregating task to accumulator list. So this is tail recursion.
	A2b: No need to return anything.
	A3: Use mutable data structure as accumulator/partial solution container (here StringBuilder input and list).
	A4: StringBuilder and index (the same information captured by StringsBuilders partial and res) 
	A5: Subproblem: Given next position, choose a character. 

	
	**/

	public static List<String> permuteThree (String input) {
		StringBuilder word = new StringBuilder(input);
		List<String> list = new ArrayList<>();
		permuteThreeHelper(word, 0, list);
		return list;
	}

	private static void permuteThreeHelper (StringBuilder input, int i, List<String> list) {
		if(i == input.length() - 1){
			list.add(input.toString());
		} else {
			for(int j = i; j < input.length(); j++){
				swap(input,i,j);
				permuteThreeHelper(input, i+1, list);
				swap(input,i,j);
			}
		}
	}

	private static StringBuilder swap (StringBuilder input, int i, int j) {
		char letter = input.charAt(i);
		input.setCharAt(i, input.charAt(j)); 
		input.setCharAt(j, letter);
		return input;
	}


	/** 

	An alternative way of looking at the problem : instead of looking at each partial permutation and the process of generating a new permutation from it, we look at a list of partial permutations and the process of generating new list of bigger permutations.

	- Thus, if we have have a list of k! partial permutations (all permutations of  k letters), we generate a new list of k+1! permutations from it by using (only) the first of the remaining letters.
	- The input we need: the k! partial permutations, and the next letter/index. We don't need an accumulator.

	Notice how different specification for the recursive steps can make recursive decomposition simpler or more complex, and changes the time complexity by orders of magnitude!

	
	Time Complexity: 
		Let it be T(k) for k elements.
		T(n) = T(n-1) + n! 
		The recursion call stack is n level deep. Most of the work is done not in recursive calls but in the work done after the recursive step in processing the result through the forloop (Hybrid of recursion and iteration?). 

		In general, at level k, the outer for loops over n-k! times and the inner for loop loops over n-k+1 times (The last level has 1 element, the second last has 2 elements each of lenth 2, and so on.) So at level k, the time take is n-k+1!, with k ranging from n to 1. 
		Time complexity = 1 + 2!*3 + 3!*4 + ....n-1!*n = 1! + 2! + 3! ...+n! = 


	Space complexity: To verify: n*n! for List<String>.


	A1: Recursion
	A2a: Use original specification. In the original, two types of work are being done post-recursion: generating each single permutation, and aggregating these single permutations into  a list of permutations. Here we do both.
	A2b: Use pure functions
	A3: Use immutable data structures.
	A4: No accumulator.
	A5: Subproblem: Given next character, choose a position.

	**/


	
	public static List<String> permuteFour (String remaining) {  
		List<String> newList = new ArrayList<>();
		if(remaining.length() == 1){
			 newList.add(remaining);
		} else {		
			List<String> partialPerms = permuteFour(remaining.substring(0, remaining.length()-1)); 
			for(String permutation: partialPerms )
				for(int j = 0; j <= permutation.length(); j++){
					newList.add(permutation.substring(0, j) + remaining.charAt(remaining.length()-1) + permutation.substring(j));
			}	
		}
		return newList;
	}


	/**
	 * 
	
	Approach: Same as above but with tail recursion.
	A1: Recursion
	A2a: Redefine function. In the original, two types of work are being done post-recursion: generating each single permutation, and aggregating these single permutations into  a list of permutations. Both of these can have their own accmulator. Here we delegate the work of generating a single permutation to accumulator variable StringBuilder, and  the aggregating task to accumulator list. So this is tail recursion.
	A2b:  No need to return anything.
	A3: Use mutable data structurs
	A4 int[] and index
	A5: Subproblem: Given next character, choose a position.

	**/


	public static List<String> permuteFive(String input){
		List<String> list = new ArrayList<>();
		List<Integer> remainingIndices = new ArrayList<>();
		int n = input.length();
		for (int i = 0; i < n; i++){
			remainingIndices.add(i);
		}

		int[] chosenPositions = new int[n];
		permuteFiveHelper(input, chosenPositions, 0, list, remainingIndices);
		return list;
	}

	private static void permuteFiveHelper(String input, int[] chosenPositions, int index, List<String> list, List<Integer> remainingIndices){

		if(index == input.length()){
			String permutation = constructPerm(input, chosenPositions);
			list.add(permutation);
		} else {
			ListIterator<Integer> it = remainingIndices.listIterator();
			while(it.hasNext()){
				Integer i = it.next();
				chosenPositions[index] = i;
				it.remove();
				permuteFiveHelper(input, chosenPositions, index + 1, list, new ArrayList<>(remainingIndices));
				it.add(i);
			}
		}

	}

	private static String constructPerm(String input, int[] chosenPositions){
		int n =  input.length();
		
		StringBuilder sb = new StringBuilder();
		sb.setLength(n);
		for (int i = 0; i < n; i++){
			sb.setCharAt(chosenPositions[i], input.charAt(i));
		}
		return String.valueOf(sb);
	}

	private static void printPerms(String input){
		List<String> permutations = permuteFive(input);
		for(String s: permutations){
			System.out.println(s);
		}
	}







	

/**
	Any optimizations from using StringBuffer, how does Itertator work. 
	
	Implementation given here:  https://www.topcoder.com/blog/generating-permutations/
	
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
		String[] A = {"abcdefghij", "bdosoeh89a", "abcaskahbc", "12719297nb"};


		long startTime, endTime, totalTime;
		totalTime = 0;
		List<String> permutations;
		String input = "abc";

		printPerms(input);
		for (int i = 1; i <= A.length; i++){
			input = A[i-1];
			startTime = System.nanoTime();
			permutations = permuteFive(input);
			endTime = System.nanoTime();
			totalTime = (totalTime + endTime - startTime);
		}

		System.out.println("Total time:"  + totalTime + ". Most work done post recursion, iteratively ");
		totalTime = 0;
		for (int i = 1; i <= A.length; i++){
			input = A[i-1];
			startTime = System.nanoTime();
			permutations = permuteFour(input);
			endTime = System.nanoTime();
			totalTime = (totalTime + endTime - startTime);
		}
	
		// for(String permutation: permutations){
		// 	System.out.println(permutation);
		// }
		
		System.out.println("Total time:"  + totalTime + ". Same as first but with Stringbuilder and accumulator" );

		totalTime = 0;
		for (int i = 1; i <= A.length; i++){
			input = A[i-1];
			startTime = System.nanoTime();
			permutations = permuteTwo(input);
			endTime = System.nanoTime();
			totalTime = (totalTime + endTime - startTime);
		}

		System.out.println("Total time:"  + totalTime + ". Two new Strings every call. Returns List and collects");

		totalTime = 0;
		for (int i = 1; i <= A.length; i++){
			input = A[i-1];
			startTime = System.nanoTime();
			permutations = permuteTwo(input);
			endTime = System.nanoTime();
			totalTime = (totalTime + endTime - startTime);
		}

		System.out.println("Total time:"  + totalTime + ". Same as first but with new String and index");

		
	}


}




