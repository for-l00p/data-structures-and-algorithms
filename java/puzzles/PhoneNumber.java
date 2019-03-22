package puzzles;

import java.util.Map;
import java.util.HashMap;


public class PhoneNumber {
	

	/**
	 * This method is O(n^3) in time complexity, where n is number of entries in the directory. For each pair of entries , it takes O(n) time to check if one is the prefix of another. There are n(n+1)/2 such pairs.
	 *
	 * Space requirement: O(1) extra space. create two new strings for each pair at runtime, but they are garbage collected and at any time we only have two string objects. 
	 *
	 * We can do better by using a trie data structure for the directory instead of a map. THe space complexity would be the same O(number of words). This will reduce the time complexity of search to O(n).
	 */

	public static boolean areConsistent2(Map <String, String> directory){

		// get all (number, name) entries
		// for each pair of entries, call isPrefix
		boolean result = true;
		for (String key1: directory.keySet()){
			for(String key2: directory.keySet()){
				if(key1 != key2){
					String firstNumber = clean(directory.get(key1));
					String secondNumber = clean(directory.get(key2));
					if (isPrefix(firstNumber, secondNumber)){
						System.out.println(key1 + "'s number "  + firstNumber + " and " + key2 + "'s number " + secondNumber + " are inconsistent");
						result = false;
					}
				}
			}
		}

		return result;
	}



	private static boolean isPrefix(String first, String second){
		return (first.startsWith(second) || second.startsWith(first));
	}


	private static String clean (String input){
		input = input.replace(" ", "");
		input = input.replace("-", "");
		return input;
		// remove anything but digits - whitespaeces,hypens etc.
	}


	public static boolean areConsistent(Map<String, String> directory){


		Trie trie = new Trie();

		boolean makesInconsistent = false;

		for(String key: directory.keySet()){
			boolean current = trie.reportPrefixAndInsert(clean(directory.get(key)), key);
			if (current){
				makesInconsistent = true;
			}
		}

		return !makesInconsistent;
	}

	static class Trie {

		private String name;
		private boolean isEndOfWord = false;
		Trie[] children = new Trie[10];

		public boolean isLeaf(){
			for(Trie child : children){
				if(child != null) return false;
			}
			return true;
		}

		public boolean isEndOfWord(){
			return isEndOfWord;
		}	

		public boolean reportPrefixAndInsert(String number, String name){

			boolean result = false;
			int n = number.length();
			if (n == 0){
				if (!isLeaf()){
					result = true;
					System.out.println(name + "'s number " + number + "is a prefix");
				}
				isEndOfWord = true;
				this.name = name;
			} else {
				int index = Character.getNumericValue(number.charAt(0));
			
				if (children[index] == null){
					children[index] = new Trie();

				}
				
				result = children[index].reportPrefixAndInsert(number.substring(1), name);
			}
			return result;		
		}
	}


	public static void main(String[] args){

		// Trie test = new Trie();
		// test.reportPrefixAndInsert("91", "me", false);
		// System.out.println(test);

		Map<String, String> directory = new HashMap<>();
		directory.put("Bob", "91 12 54 26");
		directory.put("Alice", "97 625 992");
		directory.put("Emergency", "911");
		System.out.println(areConsistent(directory));

	}
	







}