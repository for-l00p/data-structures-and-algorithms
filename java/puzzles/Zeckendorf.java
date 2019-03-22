package puzzles;

import java.util.List;
import java.util.ArrayList;

public class Zeckendorf {
	

	public static String get1(int n){
		 String result = z(n,1);
		 return cleanLeadingZeroes(result);
	}




	private static String z(int n, int k){
		//System.out.println("n: " + n + " k:" + k);
		int lowestBase  = fib(k);
		int secondLowestBase = fib(k+1);
		int thirdLowestBase = fib(k+2);

		if (n == 0) return "0";

		if (n < lowestBase) return "-1";

		//if (n == lowestBase) return "10";

		String result = "-1";
	
		String candidateOne = z(n - lowestBase, k+2);
		String candidateTwo = z(n, k+1);

		//if(candidateOne != "-1" && candidateTwo != "-1") throw new AssertionError("Non unique representation. " + candidateOne + ", " + candidateTwo );

		if (candidateOne != "-1"){
			result =  (candidateOne + "01");
		} else if (candidateTwo != "-1"){
			result =  (candidateTwo + "0");
		}

		//if (result == "-1") throw new AssertionError("Non unique representation. " + candidateOne + ", " + candidateTwo );

		return result;



	}


	private static String cleanLeadingZeroes(String s){

		return s.replaceFirst("^0+(?!$)", "");
	}


	private static int fib(int k){
		if(k == 1) return 1;
		if (k == 2) return 2;
		return fib(k-1) + fib(k-2);
	}


	public static String get(int n){
		List<String> output = new ArrayList<String>();

		String partial = "";
		z2(n, 1, partial, output);
		if (output.size() > 1){
			for (String s: output){
				System.out.println(s);
			}
			throw new AssertionError("more than one representation");
		}
		return output.get(0);
		
	}

	private static void z2(int n, int k, String partial, List<String> output){
		int lowestBase  = fib(k);
		int secondLowestBase = fib(k+1);
		int thirdLowestBase = fib(k+2);


		if (n < lowestBase && n > 0) return;
		if (n == 0) {
			partial = "0" + partial;
			output.add(cleanLeadingZeroes(partial));
			return;
		}
		z2(n - lowestBase, k+2, "01" + partial, output);
		z2(n, k+1, "0" + partial, output);

	
	}


	public static String get3(int n){
		List<String> output = new ArrayList<String>();
		StringBuilder partial = new StringBuilder();
		z3(n, 1, partial, output);
		if (output.size() > 1){
			for (String s: output){
				System.out.println(s);
			}
			throw new AssertionError("more than one representation");
		}
		return output.get(0);	
	}

	// Inserting in a stringbuilder repeatedly is an O(n^2) operation. Appending and then reversing makes it O(n);
	private static void z3(int n, int k, StringBuilder partial, List<String> output){

		int lowestBase  = fib(k);
		int secondLowestBase = fib(k+1);
		int thirdLowestBase = fib(k+2);

		if (n < lowestBase && n > 0) return;

		if (n == 0) {
			partial.append("0");
			output.add(cleanLeadingZeroes(String.valueOf(partial.reverse())));
			return;
		}

		partial.append("10");
		z3(n - lowestBase, k+2, partial, output);
		partial.delete(partial.length() - 2, partial.length());

		partial.append("0");
		z3(n, k+1,  partial, output);
		partial.delete(partial.length()-2, partial.length() - 1);
	}


	public static String get4(int n){
		
		Map<String> cache = new HashMap<>();
		StringBuilder partial = new StringBuilder();
		z4(n, 1, partial, output);

	}

	// Inserting in a stringbuilder repeatedly is an O(n^2) operation. Appending and then reversing makes it O(n);
	private static void z4(int n, int k, StringBuilder partial,  Map<String, String> cache, String originalArgs){

		String currentArgs = n + "," + k;
		if (cache.containsKey(currentArgs)){
			StringBuilder result = new StringBuilder(cache.get(currentArgs));
			if(result == "-1"){
				//do nothing
			} else {
				cache.put(cleanLeadingZeroes(String.valueOf(result.append(partial))))
				return;
			}
		}

		int lowestBase  = fib(k);
		int secondLowestBase = fib(k+1);
		int thirdLowestBase = fib(k+2);

		if (n < lowestBase && n > 0) cache.put(currentArgs, "-1");

		if (n == 0) {
			cache.put(currentArgs, "0");
			cache.put(originalArgs, cleanLeadingZeroes(String.valueOf(partial)));
			return;
		}

		partial.append("10");
		z4(n - lowestBase, k+2, partial, output);
		partial.delete(partial.length() - 2, partial.length());

		partial.append("0");
		z4(n, k+1,  partial, output);
		partial.delete(partial.length()-2, partial.length() - 1);
	}








}