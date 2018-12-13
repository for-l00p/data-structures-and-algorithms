
/*

A compressed trie is a trie with one additional rule: Each internal node has   ≥ 2   children. In order to enforce the above rule, the labels are generalized: Each node is labeled with a string (multiple characters). (The label used to be a single character)

For implementing iterative delete, we need parent pointer and label reference in the node. But if we implement recursive delete, we don't need to. 


Space Complexity: O(W) - total number of words. How?  A tree in which every node has atleast two children has not more than l-1 internal nodes, where l is the number of leaves (proof by induction)


 */

package ds;

import java.util.Map;
import java.util.HashMap;

public class CompressedTrie<Value>{

	private TrieNode root = new TrieNode(false);

	private class TrieNode{

		HashMap<String,TrieNode> children = new HashMap<>();
		boolean isWord = false;
		Value value;

		public TrieNode (boolean isWord){
			this.isWord = isWord;
		}

		public boolean isLeaf(){
			return children.isEmpty();
		}

	}

	//Constructor
	public CompressedTrie(){

	}


	public void insert(String word){
		insertRecursive(word, this.root);
	}

	private void insertRecursive(String word, TrieNode root){

		if (word.equals("")){
			root.isWord = true;
			return;  
		}
		Map<String, TrieNode> children = root.children;

		String label = findKeyStartswith(children, String.valueOf(word.charAt(0)));
		//System.out.println("Found label: " + label);
		TrieNode newLeafNode = new TrieNode(true);

		if(label == null){
			children.put(word, newLeafNode);
		} else {
			int j = 0;
			while(j< word.length() && j < label.length() && word.charAt(j) == label.charAt(j)){
				j++;
			}
	

			TrieNode oldChildNode = children.get(label);
	
			String commonEdgeLabel = label.substring(0,j);
			String oldEdgeLabel = label.substring(j);
			String newEdgeLabel = word.substring(j);

			if (j == label.length()){
				insertRecursive(newEdgeLabel, oldChildNode);
			} else if (j == word.length()){
				TrieNode newInternalNode = new TrieNode(true);
				children.remove(label);
				children.put(commonEdgeLabel, newInternalNode);
				newInternalNode.children.put(oldEdgeLabel, oldChildNode);
			} else {
				children.remove(label);
				TrieNode newInternalNode = new TrieNode(false);
				children.put(commonEdgeLabel, newInternalNode);
				newInternalNode.children.put(oldEdgeLabel, oldChildNode);
				newInternalNode.children.put(newEdgeLabel, newLeafNode);
			}


		}
	}



	//function to insert a word in trie
	public void insertIterative(String word){

		TrieNode currentNode = this.root;
		int i = 0;
		while(i < word.length()){
			Map<String,TrieNode> currentNodeMap = currentNode.children;
			String label = findKeyStartswith(currentNodeMap, String.valueOf(word.charAt(i)));
			TrieNode newLeafNode = new TrieNode(true);
			

			if (label == null){
				currentNodeMap.put(word.substring(i), newLeafNode);
				return;
			} else {
				
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



				if (j == label.length()){
					if (i == word.length()){
						oldChildNode.isWord = true;
						return;
					} else {
						currentNode = oldChildNode;
						continue;
					}
					
				}

				if (j != label.length()){

					currentNodeMap.put(initialEdgeLabel, newInternalNode);
					currentNodeMap.remove(label);
			    	newInternalNode.children.put(latterEdgeLabel, oldChildNode);
			    	newInternalNode.children.put(newEdgeLabel, newLeafNode);
			    	currentNode = newInternalNode;
				} 
			}
		}
	}

	private String findKeyStartswith(Map<String,TrieNode> map, String str){

		for(Map.Entry<String,TrieNode> e: map.entrySet()){
			if (e.getKey().startsWith(str)){
				return e.getKey();
			}
		}
	 	return null;
 	}


 	public void search(String word){
 		System.out.println("Searching: " + word);
 		searchRecursive(word, this.root);
 	}

 	private void searchRecursive(String word, TrieNode root){

 		if (word.equals("")){
 			if(root.isWord){
 				System.out.println("Pattern is present");
 			} else {
 				System.out.println("Pattern is present only as prefix of another word");
 			}
 			return;
 		}

 		Map<String, TrieNode> children = root.children;
 		String label = findKeyStartswith(children, String.valueOf(word.charAt(0)));

 		if (label == null){
 			System.out.println("Pattern not present");
 			return;
 		} else {
 			int j = 0;
			while(j < word.length() && j < label.length() && label.charAt(j) == word.charAt(j)){ 
				j++;
			}

			if(j == label.length()){
				TrieNode childNode = children.get(label);
				searchRecursive(word.substring(j), childNode);
			} 

			if(j != label.length()){
				if(j == word.length()){
					System.out.println("Pattern is present only as prefix of another word");
				} else {
					System.out.println("Pattern is not present");
				}
			}	
 		}

 	}


	//function to search a word in trie
	public void searchIterative(String word){

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
				int j = 0;
				while(i < word.length() && j < label.length() && label.charAt(j) == word.charAt(i)){ 
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



	public void delete (String word){
		System.out.println("Deleting: " + word);
		deleteRecursive(word, this.root);
	}



	private boolean deleteRecursive(String word, TrieNode root){

		if (word.equals("")){
			root.isWord = false;
			if (root.isLeaf()){
				return true;
			} else {
				return false;
			}
		}
		Map<String, TrieNode> children = root.children;
		String label = findKeyStartswith(children, String.valueOf(word.charAt(0)));

		if(label == null){
			System.out.println("Word not present");
		} else {
			
			TrieNode childNode = children.get(label);
			int j = 0;
			while(j < word.length() && j < label.length() && word.charAt(j) == label.charAt(j)){
				j++;
			}

			if(j == label.length()){
				if(deleteRecursive(word.substring(j), childNode)){
					children.remove(label);
					if(root.isWord == false && root.isLeaf()){
						return true;
					}
				} 
			}

			if(j != label.length()){
				if(j == word.length()){
					System.out.println("Unable to delete. Word present as prefix of another word.");
				} else {
					System.out.println("Unable to delete. Word is not present.");
				}
				
			}

			

		}		

		return false;

	}



	public static void main(String args[]){
		CompressedTrie testTrie = new CompressedTrie();
		testTrie.insert("the");
		testTrie.insert("a");
		testTrie.insert("there");
		testTrie.insert("answer");
		testTrie.insert("any");
		testTrie.insert("by");
		testTrie.insert("bye");
		testTrie.insert("their");
		testTrie.search("the");
		testTrie.search("there");
		testTrie.search("their");
		testTrie.delete("th");
		testTrie.delete("theri");
		testTrie.delete("thes");
		testTrie.search("ther");
		testTrie.delete("the");
		testTrie.search("the");
		testTrie.delete("there");
		testTrie.search("there");
		testTrie.insert("there");
		testTrie.search("there");
		testTrie.insert("the");
		testTrie.delete("a");
		testTrie.search("a");
	}

}