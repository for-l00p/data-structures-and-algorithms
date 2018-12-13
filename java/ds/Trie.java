/**
 *
 *


http://cglab.ca/~morin/teaching/5408/notes/strings.pdf
https://www.cise.ufl.edu/~sahni/dsaaj/enrich/c16/tries.htm


The trie data structure (abstract data type) is a specialized (very efficient) implementation of an (ordered) index for text-based keys. 

Advantages of Tries over an ordinary map
Advantages:
Looking up data in a trie is in the worst case = O(m), where m = length of the key         
Look up in a map is O(lg(n)) where n = # entries !!!
So a trie has performance levels that is similar to a hash table.

Unlike a hash table, a trie can provide an alphabetical ordering of the entries by key
       (I.e., A trie implements an ordered map while a hash table cannot !)


Tries are used in websearch. *the index of a search engine ( collection of all searchable words) can be stored as a compressed trie”. Each leaf, which corresponds to a word, stores a list of pages (URLs): the occurrence list. The trie is kept in internal memory and the occurrence list in external memory.  Boolean queries correspond to set operations on these occurrence lists. Other techniques used: elimination of words like ‘a’ ‘the’ ‘is’ (stop word elimination), treating ‘add’ ‘added’ ‘adding’ as the same word (stemming), link analysis.

Tries are used in internet routers. Each computer on the internet (host) has an I.P. address: a unique 32 bit address. Each router has a table which maps some prefixes with the next router.  When a packet comes, the router analyse  the prefix, finds the longest match in the table, and sends it to the next link. 

----
Implementation choices:

If you store the child nodes in an array, then you can look up which child to visit extremely efficiently by just indexing into the array. However, the space usage per node will be high: O(|Σ|), where Σ is the set of letters that your words can be formed from, even if most of those children are null.

If you store the child nodes in a linked list, then the time required to find a child will be O(|Σ|), since you may need to scan across all of the nodes of the linked list to find the child you want. On the other hand, the space efficiency will be quite good, because you only store the children that you're using. You could also consider using a fixed-sized array here, which has even better space usage but leads to very expensive insertions and deletions.

If you store the child nodes in a hash table (e.g. HashMap in Java), then the (expected) time to find a child will be O(1) and the memory usage will only be proportional (roughly) to the number of children you have. Interestingly, because you know in advance what values you're going to be hashing, you could consider using a dynamic perfect hash table to ensure worst-case O(1) lookups, at the expense of some precomputation.

Another option would be to store the child nodes in a binary search tree (e.g. TreeMap in Java). This gives rise to the ternary search tree data structure. This choice is somewhere between the linked list and hash table options - the space usage is low and you can perform predecessor and successor queries efficiently, but there's a slight increase in the cost of performing a lookup due to the search cost in the BST. If you have a static trie where insertions never occur, you can consider using weight-balanced trees as the BSTs at each point; this gives excellent runtime for searches (O(n + log k), where n is the length of the string to search for and k is the total number of words in the trie).

In short, the array lookups are fastest but its space usage in the worst case is the worst. A statically-sized array has the best memory usage but expensive insertions and deletions. The hash table has decently fast lookups and good memory usage (on average). Binary search trees are somewhere in the middle. I would suggest using the hash table here, though if you put a premium on space and don't care about lookup times the linked list might be better. Also, if your alphabet is small (say, you're making a binary trie), the array overhead won't be too bad and you may want to use that.

----

Interesting discussion: 
https://news.ycombinator.com/item?id=1656104

If you aren't exploiting the ordered nature of the keys within a trie - i.e. if your keys have no underlying substructure or you aren't doing linear scans through the key space - then could think of a hash table hash table.

A hash table will be fast and use less memory if your keys are unstructured.

If you are accessing completely randomly, then the cache utilisation will be similar between a good trie and a hash table. Hashtables are very cache friendly since they are essentially an array. Tries are not at all cache friendly. The result is that hashtables are faster.

If you are accessing some things more than others, then you should use a move-to-front hash table to be competitive.

----

Tries and ternary search trees represent a time/space trade off. If your alphabet has k symbols in it, then each node in a trie holds k pointers plus one extra bit for whether or not the node encodes a word. Looking up a word of length L always takes time O(L). A ternary search tree stores three pointers per node, plus one character and one bit for whether or not the node encodes a word. Looking up a word of length L takes time O(L log k). (If you have a static ternary search tree, you can build the TST using weight-balanced trees, which improves the lookup time to O(L + log k) but makes insertions prohibitively expensive.)

For cases where each node in the trie has most of its children used, the Trie is substantially more space efficient and time efficient than th ternary search tree. If each node stores comparatively few child nodes, the ternary search tree is much more space efficient. Typically speaking, tries are much, much faster than ternary search trees because fewer pointer indirections are required.

So in sort, neither structure is strictly better than the other. It depends on what words are being stored.

To mix things up a bit, succinct tries are starting to be a viable alternative to both of the above approaches. They have space usage better than tries, though the lookup time tends to be much slower. Again, it depends on the application whether they will be better or worse than the other two options.

As for how to build them - both tries and ternary search trees support efficient insertion of a single word. They don't need to be built from a fixed set of words in advance.

For ternary search tree: https://stackoverflow.com/questions/8765211/how-do-i-get-an-inner-class-to-inherit-enclosing-class-generic-type

---


Time Complexity:
Operations insert, delete and search take O(dm) time, where m is the size of the word and d is the size of the alphaber.
Space: Trie takes O(dn) space, where n is the total length of all the words, and d is the size of the alphabet


Applications: Find the first occurance of a word in the text. Find the occurence of the longest prefix of a word in a text T. 


 */

package ds;

import java.util.Iterator;
import java.util.Queue;
import java.util.LinkedList; 

public class Trie implements Iterable<String>{


	private static final int ALPHABET_SIZE = 26;


		protected class TrieNode {
			//private char _letter;
			private TrieNode[] _children =  new TrieNode[ALPHABET_SIZE];
			private boolean _isWord = false;

				protected TrieNode(){
				}

				protected TrieNode (boolean isWord){
					this._isWord = isWord;
				}

				protected TrieNode[] getChildren(){
					return _children;
				}

				protected void setWord(boolean flag){
					_isWord = flag;
				}

				protected boolean isWord(){
					return _isWord;
				}

				protected boolean isLeaf(){
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

/**
 * For comparison, here is a node class for Ternary Search Tree. (Note that unlike the array implementation, where each index corresponds to a character, we have to store the character as well - same as in LinkedList implementations,)
 * 
 *  protected class TSTNode {    
      protected char splitchar;
      protected EnumMap<Index, TSTNode> relatives;
      private T data;

      protected TSTNode(char splitchar, TSTNode parent) {
        this.splitchar = splitchar;
        // Create an EnumMap. 
        relatives = new EnumMap<Index, TSTNode>(Index.class);
        relatives.put(Index.PARENT, parent); 
     
 */

	private  TrieNode root = new TrieNode(false);
	private int size;


	//Constructor
	public Trie (){
	}

  /*************************************************************************
    Basic Methods
   ***************************************************************************/


	//function to insert a word in trie
	public void insert(String word){
		int index;
		int i;
		TrieNode currentNode = this.root;
		for(i = 0; i < word.length(); i++){
			char letter = word.charAt(i);
			TrieNode[] children = currentNode.getChildren();
			index = letter - 'a'; //Gives the index for letter
			if(children[index] == null){
				children[index] = new TrieNode(false);
			}
			currentNode = children[index];
		}
		currentNode.setWord(true);
		size++;
	}


	public void insertRecursive(String word){
		this.root = insertRecursive(word, this.root);
	}


	private TrieNode insertRecursive (String word, TrieNode root){

		if (root == null){
			root = new TrieNode(false);
		}

		if (word.equals("")){
			root.setWord(true);
			size++;
		} else {
			int index = word.charAt(0) - 'a';

			root.getChildren()[index] = insertRecursive(word.substring(1), root.getChildren()[index]);
		}

		return root;

	}


	private TrieNode get(String word, TrieNode root){


		if (root == null){
			return null;
		}

		if (word.equals("")){
			return root;
		} 
		
		return get(word.substring(1), root.getChildren()[word.charAt(0) - 'a']);
	}


	public boolean search(String word){

		System.out.println("Starting search: " + word);
		return searchRecursive(word, this.root);

	}

	private boolean searchRecursive(String word, TrieNode root){

		boolean result = false;

		if (root == null){
			System.out.println("Pattern NOT present: " + word);
			return false;

		}

		if (word.equals("")){
			result = root.isWord();
			if(root.isWord()){
				System.out.println("Pattern present as word");
			} else {
				System.out.println("Pattern present as prefix of another word");
			}
		} else {
			int index = word.charAt(0) - 'a';
			result = searchRecursive(word.substring(1), root.getChildren()[index]);
		}

		return result;


	}

	//function to search a word in trie
	public boolean searchIterative(String word){

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
	

		//Base case
		
		if (root == null){
			System.out.println("pattern seems to be not present");
			return false;
		}

		if (word.equals("")){
			root.setWord(false);
			size--;
			return root.isLeaf();	
		}
		int index = word.charAt(0) - 'a';
		TrieNode[] children = root.getChildren();
		TrieNode child = children[index];
		String substring = word.substring(1);
		if(deleteHelper(substring, child)){
			root.getChildren()[index] = null;
			size--;
			if (root.isLeaf() && !root.isWord()){
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}


	}

  /*************************************************************************
     Iterators
   ***************************************************************************/


	public Iterator<String> iterator(){

		return keysWithPrefix("").iterator();

	}


	public Iterable<String> keysWithPrefix(String prefix){

		Queue<String> keys = new LinkedList<String>();
		TrieNode prefixNode = get(prefix, this.root);
		if(prefixNode == null){
			return keys;
		}
		collect(prefixNode, new StringBuilder(prefix), keys);
		return keys;


	}


	public void collect(TrieNode root, StringBuilder prefix, Queue<String> result ){

		
		if(root.isWord()){
			result.add(prefix.toString());
		}

		TrieNode[] children = root.getChildren();

		for(int i= 0; i < children.length; i++){
			if(children[i] !=null){
				char nextLetter = (char) ('a' + i);
				// nextLetter = (char) ('a' + i);
				prefix = prefix.append(nextLetter);
				//Never use String += String. Use StringBuilder
				collect(children[i], prefix, result);
				prefix.deleteCharAt(prefix.length() - 1);
			}		
		}

	}

 	/**
     * Returns all of the keys in the set that match {@code pattern},
     * where . symbol is treated as a wildcard character.
     * @param pattern the pattern
     * @return all of the keys in the set that match {@code pattern},
     *     as an iterable, where . is treated as a wildcard character.
     */  

	public Iterable<String> keysThatMatch(String pattern){

		Queue<String> keys = new LinkedList<String>();
		StringBuilder prefix = new StringBuilder();
		collect(this.root, pattern, prefix, keys);
		return keys;


	}


	private void collect(TrieNode root, String remainingPattern, StringBuilder prefix, Queue<String> result){

		if (root == null){
			return;
		}
		// A better way to do it is to deduce remaining pattern from pattern by prefix length, and instead of passing a new remaining pattern to every recursive call, pass the whole pattern. 
		if(remainingPattern.equals("")){
			if(root.isWord()){
				result.add(prefix.toString());
			}
			return;
		}
		char nextLetter = remainingPattern.charAt(0);
		TrieNode[] children = root.getChildren();

		if(nextLetter == '.'){
			for (int i = 0; i < children.length; i++){
				if (children[i] != null){
					char actualLetter =  (char) ('a' + i);
					prefix = prefix.append(actualLetter);
					collect(children[i], remainingPattern.substring(1), prefix, result);
					prefix.deleteCharAt(prefix.length() - 1); 
				}
			}
		} else {
			int index = nextLetter - 'a';
			if(children[index] == null){
				System.out.println("Pattern not present in any key");
				; 
			} else {
				prefix = prefix.append(nextLetter);
				collect(children[index], remainingPattern.substring(1), prefix, result);
				prefix.deleteCharAt(prefix.length() - 1); 
			}
		}


	}


	public String longestPrefixOf(String query){

		int length = longestPrefixOf(this.root, query, -1);
		if (length == -1) return null;
		return query.substring(0,length + 1);
	}


	public int longestPrefixOf(TrieNode root, String query, int length){

		System.out.println("length is now: " + length);
		if (root == null){
			return length;
		}
		char nextLetter = query.charAt(length+1);
		TrieNode[] children = root.getChildren();
		int index = nextLetter - 'a';
		if(children[index] != null){
			length = longestPrefixOf(children[index], query, length + 1);
		}
		return length;
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
		// testTrie.search("the");
		// testTrie.search("there");
		// testTrie.search("their");
		// testTrie.delete("th");
		// testTrie.delete("theri");
		// testTrie.search("ther");
		// testTrie.delete("the");
		// testTrie.search("the");
		// testTrie.delete("there");
		// testTrie.search("there");
		// testTrie.insert("there");
		// testTrie.search("there");
		// testTrie.insert("the");
		// testTrie.delete("a");
		Iterator<String> iterator= testTrie.keysWithPrefix("a").iterator();
		// for(String key : testTrie.keysThatMatch("t.e..")){
		// 	System.out.println(key);
		// }
	
		for(String key : testTrie.keysWithPrefix("bad")){
			System.out.println(key);
		}


		//System.out.println(testTrie.longestPrefixOf("theremin"));
		

	}

	
}



	
