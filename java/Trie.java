/**
 * 
Advantages of Tries over an ordinary map
Advantages:
Looking up data in a trie is in the worst case = O(m), where m = length of the key         
Look up in a map is O(lg(n)) where n = # entries !!!
So a trie has performance levels that is similar to a hash table !!!

Unlike a hash table, a trie can provide an alphabetical ordering of the entries by key
       (I.e., A trie implements an ordered map while a hash table cannot !)


Tries are used in websearch. *the index of a search engine ( collection of all searchable words) can be stored as a compressed trie”. Each leaf, which corresponds to a word, stores a list of pages (URLs): the occurrence list. The trie is kept in internal memory and the occurrence list in external memory.  Boolean queries correspond to set operations on these occurrence lists. Other techniques used: elimination of words like ‘a’ ‘the’ ‘is’ (stop word elimination), treating ‘add’ ‘added’ ‘adding’ as the same word (stemming), link analysis.

Tries are used in internet routers. Each computer on the internet (host) has an I.P. address: a unique 32 bit address. Each router has a table which maps some prefixes with the next router.  When a packet comes, the router analyse  the prefix, finds the longest match in the table, and sends it to the next link. 



To do:

Delete in compressed Tries
InserRecursive
SearchRecursive https://www.youtube.com/watch?v=AXjmTQ8LEoI

https://stackoverflow.com/questions/7449822/which-node-data-structure-to-use-for-a-trie
http://cglab.ca/~morin/teaching/5408/notes/strings.pdf
https://www.cise.ufl.edu/~sahni/dsaaj/enrich/c16/tries.htm
https://www.geeksforgeeks.org/pattern-searching-using-trie-suffixes/
https://www.geeksforgeeks.org/pattern-searching-set-8-suffix-tree-introduction/
 * // The trie data structure (abstract data type) is an specialized (very efficient) implementation of an (ordered) index for text based keys
 */

public class Trie {

	//private static final int ALPHABET_SIZE = 26;
	private  TrieNode root = new TrieNode(false);


	//Constructor
	public Trie (){
	}


	//function to insert a word in trie
	public void insert(String word){
		System.out.println("Starting insert:" + word);
		int index;
		int i;
		TrieNode currentNode = this.root;
		for(i = 0; i < word.length(); i++){
			char letter = word.charAt(i);
			TrieNode[] children = currentNode.getChildren();
			index = letter - 'a';
			if(children[index] == null){
				children[index] = new TrieNode(false);
				currentNode = children[index];
			} else {
				currentNode = children[index];
			}
		}
		currentNode.setWord(true);

	}

	//function to search a word in trie
	public boolean search(String word){

		TrieNode currentNode = this.root;
		int index, i;
		for( i = 0; i < word.length(); i++){
			char letter = word.charAt(i);
			TrieNode[] children = currentNode.getChildren();
			 index = letter - 'a';
			if(children[index] == null){
				System.out.println(word + ": pattern seems to be not present");
				return false;
			} else {
				currentNode = children[index];
			}
		}
		if (currentNode.isWord()){
				System.out.println(word + ": pattern present as a word");
		} else {
			System.out.println(word + ": pattern present as prefix of another word");
		}
	
		return currentNode.isWord();

	}

	public void delete(String word){
		System.out.println("Deleting:" + word);
		deleteHelper(word,(this.root));


	}

	public boolean deleteHelper(String word, TrieNode root){
		//System.out.println("Calling delete on word:" + word);

		//Base case
		if (root == null){
			System.out.println("pattern seems to be not present");
			return false;
		}

		if (word.equals("")){
			root.setWord(false);
			return true;
		}
		int index = word.charAt(0) - 'a';
		TrieNode[] children = root.getChildren();
		TrieNode child = children[index];
		String substring = word.substring(1);
		if(deleteHelper(substring, child)){
			if (root.isLeaf() && !root.isWord()){
				root = null;
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}


	}

	class TrieNode {
	//private char _letter;
	private TrieNode[] _children = new TrieNode[26];
	private boolean _isWord = false;

		public TrieNode(){		
		}

		public TrieNode (boolean isWord){
			this._isWord = isWord;
		}

		public TrieNode[] getChildren(){
			return _children;
		}

		public void setWord(boolean flag){
			_isWord = flag;
		}

		public boolean isWord(){
			return _isWord;
		}

		public boolean isLeaf(){
			boolean empty = true;
			for(int i =0; i < 26; i++){
				if (_children[i] != null){
					empty = false;
					break;
				}
			}
			return empty;
		}
	}


	public static void main(String args[]){
		Trie testTrie = new Trie();
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
		testTrie.search("ther");
		testTrie.delete("the");
		testTrie.search("the");
		testTrie.delete("there");
		testTrie.search("there");
		testTrie.insert("there");
		testTrie.search("there");
		testTrie.insert("the");
		testTrie.delete("a");
		

	}

	
}



	
