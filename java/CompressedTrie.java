
//A compressed trie is a trie with one additional rule: Each internal node has   ≥ 2   children. In order to enforce the above rule, the labels are generalized: Each node is labeled with a string (multiple characters). (The label used to be a single character)


//For implementing iterative delete, we need parent pointer and label reference in the node. But if we implement recursive delete, we don't need to. 

import java.util.Map;
import java.util.HashMap;

public class CompressedTrie {

	private TrieNode root = new TrieNode(false);

	//Node class
	private class TrieNode {

		HashMap<String,TrieNode> children = new HashMap<>();
		boolean isWord = false;

		public TrieNode (boolean isWord){
			this.isWord = isWord;
		}

	}

	//Constructor
	public CompressedTrie (){

	}


	//function to insert a word in trie
	public void insert(String word){

		TrieNode currentNode = this.root;
		int i = 0;
		while(i < word.length()){
			HashMap<String,TrieNode> currentNodeMap = currentNode.children;
			String label = findKeyStartswith(currentNodeMap, String.valueOf(word.charAt(i)));
			TrieNode newLeafNode = new TrieNode(true);
			

			if (label == null){
				currentNodeMap.put(word.substring(i), newLeafNode);
				//System.out.println("Inserted: " + word.substring(i));
				return;
			} else {
				//System.out.println("Label found: " + label + " for word " + word.substring(i));
				int j = 0; // atleast one character matches
				while(i < word.length() && j < label.length() && label.charAt(j) == word.charAt(i)){ 
					j++;  
					i++;
				}
				// j = number of characters matched (not index). So if 3 words match, j = 3. 
				// i = index 
			
				TrieNode oldChildNode = currentNodeMap.get(label);
				TrieNode newInternalNode = new TrieNode(false);
			
				String initialEdgeLabel = label.substring(0,j);
				String latterEdgeLabel = label.substring(j);
				String newEdgeLabel = word.substring(i);


				// System.out.println("initialEdgeLabel: " + initialEdgeLabel + " latterEdgeLabel: " + latterEdgeLabel + " newEdgeLabel: " + newEdgeLabel);

				if (j == label.length()){
					if (i == word.length()){
						//oldChildNode.children.put(newEdgeLabel, newLeafNode);
						oldChildNode.isWord = true;
						return;
					} else {
						currentNode = oldChildNode;
						//System.out.println("Current Node updated" );
						continue;
					}
					
				}

				if (j != label.length()){

					currentNodeMap.put(initialEdgeLabel, newInternalNode);
					//System.out.println("Inserted: " + initialEdgeLabel);
					currentNodeMap.remove(label);
					//System.out.println("Removed: " + label);
			    	newInternalNode.children.put(latterEdgeLabel, oldChildNode);
			    	//System.out.println("Inserted: " + latterEdgeLabel);
			    	newInternalNode.children.put(newEdgeLabel, newLeafNode);
			    	currentNode = newInternalNode;
			    	//System.out.println("Inserted: " + newEdgeLabel);

				} 

				// System.out.println("Current Node updated" );
			   

			}


		}

	}

	private String findKeyStartswith(HashMap<String,TrieNode> map, String str){

		for(Map.Entry<String,TrieNode> e: map.entrySet()){
			//System.out.println("Searching key:" + e.getKey());
			if (e.getKey().startsWith(str)){
				return e.getKey();
			}

		}
	 	//System.out.println("Key not found:" + str)	;
	 	return null;
 	}


	//function to search a word in trie
	public void search(String word){

		TrieNode currentNode = this.root;
		int i = 0;
		while(i < word.length()){

			HashMap<String,TrieNode> currendNodeMap = currentNode.children;
			String label = findKeyStartswith(currendNodeMap, String.valueOf(word.charAt(i)));
			TrieNode oldChildNode = currendNodeMap.get(label);
			if (label == null){
				System.out.println("This word seems to be not present");
				return;
			} else {
				//System.out.println("Label found: " + label + " for word " + word.substring(i));
				int j = 0;
				//System.out.println(String.valueOf(word.charAt(j)) + " and " + String.valueOf(word.charAt(i)) );
				while(i < word.length() && j < label.length() && label.charAt(j) == word.charAt(i)){ 
					//System.out.println(String.valueOf(word.charAt(i)) + " matched. j will now be now: " + (j+1));
					j++;
					i++;

				}

				if (j == label.length()){

					if (i == word.length()){

						if (oldChildNode.isWord){
							System.out.println("The word is present");
							return;
						} else {

							System.out.println("The pattern is present only as a prefix ");
							// TrieNode leaf = oldChildNode.children.get("");
							// if (leaf != null && leaf.isWord){
							// 	System.out.println("The word is present and is also the prefix of another");
							// 	return;
							// }

							// if (leaf == null){
							// 	System.out.println("The pattern is present only as a prefix ");
							// 	return;
							// }
					    }	 
					}

					if (i != word.length()){
						currentNode = oldChildNode;
						continue;
					}

				}

				if (j != label.length()){
					if (i == word.length()){
						System.out.println("The pattern is present only as a prefix ");;
					} else {
						System.out.println("The word is not present at all");
					}
					
					return;
				}						
			}
		}


	}

	public void insertRecursive(String word){

	}

	public void delete (String word){

	}

	public static void main(String args[]){
		CompressedTrie testTrie = new CompressedTrie();
		
		testTrie.insert("a");
		testTrie.insert("there");
		testTrie.insert("answer");
		testTrie.insert("any");
		testTrie.insert("ancestor");
		testTrie.insert("by");
		testTrie.insert("bye");
		testTrie.insert("their");
		testTrie.insert("the");
		//System.out.println(testTrie.toString());
		testTrie.search("the");
		testTrie.search("there");
		testTrie.search("their");
		testTrie.search("thei");
		testTrie.search("thie");
	}

}