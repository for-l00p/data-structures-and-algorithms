
package puzzles;

import ds.Trie;
import java.util.*;

public class AutoComplete{
	
	private AutoComplete(){

	}

	public static Queue<String> getWords(String prefix, String[] inputWords){
		Trie testTrie = new Trie();
		for(String word: inputWords){
			testTrie.insert(word);
		}

		Queue<String> result = new LinkedList<String>();
		for(String word: testTrie.keysWithPrefix(prefix)){
			result.add(word);
		}

		return result;
	
	}


	public static void main(String[] args){

		String[] inputWords = {"bat", "batch", "baton", "the", "a", "any", "answer", "by", "bye", "there", "their" };
	
		for (String word: getWords("bat", inputWords)){
			System.out.println(word);
		}
	

	}

}